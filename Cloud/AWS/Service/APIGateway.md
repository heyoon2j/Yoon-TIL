# API Gateway
HTTP API 백엔드 서비스를 제공하는 AWS 서비스. 


## REST API
* RESTful API 서비스를 제공
* Lambda 함수와 기타 AWS 서비스와 통합하여 사용 가능


## HTTP API
* RESTful API 서비스를 제공
* Lambda 함수와 통합하여 사용 가능
* REST API 보다 최소한의 기능으로 설계되어 비용이 저렴하며, 기타 AWS 서비스와 통합하여 사용 불가


## WebSocket API
* WebSocket 서비스를 제공
* Lambda 함수와 기타 AWS 서비스와 통합하여 사용 가능


### REST vs HTTP API Diff

| 항 목 | REST API | HTTP API |
| 엔드포인트 | Edge, Region, Private | Region |
| 보안 | TLS 인증, 백엔드 인증서, WAF | TLS 인증 |
| 액세스 제어 | IAM, 리소스 정책, Cognito, Lambda 사용자 지정 | IAM, Cognito, Lambda 사용자 지정, JWT |
|  |  |  |
|  |  |  |





## Private API Gateway 생성 과정
1. VPC Endpoint
    - SG 생성 (HTTPS:443)
    - execute-api 엔드포인트 생성


2. API Gateway
    - IAM 권한 (Lambda 실행, Log 기록)
        * Lambda 실행
        ```
        {
            "Version": "2012-10-17",
            "Statement": [
                {
                "Sid": "",
                "Effect": "Allow",
                "Principal": {
                    "Service": "apigateway.amazonaws.com"
                },
                "Action": "sts:AssumeRole"
                }
            ]
        }
        {
            "Version": "2012-10-17",
            "Statement": [
                {
                    "Effect": "Allow",
                    "Action": "lambda:InvokeFunction",
                    "Resource": "arn:aws:lambda:ap-south-1:111111111111:function:S3Object_Read"
                }
            ]
        }      
        ```
        * 관리형 : AmazonAPIGatewayPushToCloudWatchLogs
    - Resource 정책 ()
    - 설정 : CloudWatch log role ARN 설정
    - 리소스 생성 : context (like "/test", "/service/file")
    - 메서드 생성 : GET / POST
    - API 배포


3. Lambda
    - IAM 생성 (Lambda Log 기록, GetObject:S3)
        * Lambda Basic : Log 기록
        ```
        {
            "Version": "2012-10-17",
            "Statement": [
                {
                    "Effect": "Allow",
                    "Principal": {
                        "Service": "lambda.amazonaws.com"
                    },
                    "Action": "sts:AssumeRole"
                }
            ]
        }
        {
            "Version": "2012-10-17",
            "Statement": [
                {
                    "Effect": "Allow",
                    "Action": "logs:CreateLogGroup",
                    "Resource": "arn:aws:logs:ap-south-1:11111111111:*"
                },
                {
                    "Effect": "Allow",
                    "Action": [
                        "logs:CreateLogStream",
                        "logs:PutLogEvents"
                    ],
                    "Resource": [
                        "arn:aws:logs:ap-south-1:11111111111:log-group:/aws/lambda/S3Object_Read:*"
                    ]
                }
            ]
        }        
        ```
        * GetObject : S3
        ```
        {
            "Version": "2012-10-17",
            "Statement": [
                {
                    "Sid": "VisualEditor0",
                    "Effect": "Allow",
                    "Action": [
                        "s3:PutObject*",
                        "s3:ListBucket",
                        "s3:GetObject*",
                        "s3:DeleteObject*",
                        "s3:GetBucketLocation"
                    ],
                    "Resource": [
                        "arn:aws:s3:::s3-proj-test*",
                        "arn:aws:s3:::s3-proj-test"
                    ]
                },
                {
                    "Sid": "VisualEditor1",
                    "Effect": "Allow",
                    "Action": [
                        "kms:Decrypt",
                        "kms:Encrypt",
                        "kms:DescribeKey",
                        "kms:ReEncrypt*",
                        "kms:GenerateDataKey*"
                    ],
                    "Resource": [
                        "arn:aws:kms:ap-south-1:11111111111:key/zzzzzzzzzzz-zzzzzzzzzzz"
                    ]
                }
            ]
        }
        ```
    - 코드 작성

4. Server
    - IAM 권한 (API Gateway 호출)
    - 
