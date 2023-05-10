# Systems Manager Automation

## Systems Manager Document Syntax

## Top-level elements
### ```schemaVersion``` (required)
* 해당 Document Schema Version
</br>

### ```description``` (option)
* 해당 Document에 대한 설명
</br>

### ```parameters``` (option)
* 해당 Document가 허용하는 파마리터 정의.
* 구조는 다음과 같다.
1. ```type``` (required)
    * Parameter Store Parameter 참조 가능 : ```{{ssm:parameter-name}}```
    * https://docs.aws.amazon.com/ko_kr/systems-manager/latest/userguide/sysman-doc-syntax.html#top-level-properties-type
    * ```String``` : ```"abc123"```
    * ```StringList``` : ```["cd ~”, “pwd”]``` (쉼표로 구분)
    * ```Integer``` : ```123``` ("123" 허용 안함)
    * ```Boolean``` : ```true / false``` ("true" 또는 0 허용 안함)
    * ```Map``` :
        ```
        RuntimeParameters:
            parameter1: value1
            parameter2: value2        
        ```
    * ```StringMap``` : ```{"Env”: “Prod”}``` (Key는 문자열만 가능)
        ```
        Target:
          abcd: 'efgh'
        ```
    * ```MapList``` : StringMap의 List
        ```
        Targets:
          - Key: ResourceGroup1
            Values:
              - '{{ runbookGroupName1 }}'
          - Key: ResourceGroup2
            Values:
              - '{{ runbookGroupName2 }}'
        ```
2. ```default``` (option)
    * Parameter의 기본값
3. ```description``` (option)
    * Parameter에 대한 설명
4. ```allowedValues``` (option)
    * Parameter에 허용된 값의 배열. 허용되지 않은 값을 입력하면 실행되지 않는다.
    ```
    DirectoryType:
    type: String
    description: "(Required) The directory type to launch."
    default: AwsMad
    allowedValues:
      - AdConnector
      - AwsMad
      - SimpleAd
    ```
5. ```allowedPattern``` (option)
    * Parameter 패턴에 대한 정규 표현식. 허용된 패턴과 일치하지 않으면 실행이 시작되지 않는다.
    ```
    InstanceId:
        type: String
        description: "(Required) The instance ID to target."
        allowedPattern: "^i-[a-z0-9]{8,17}$"
        default: ''
    ```
6. ```displayType``` (option)
    * AWS Management Console를 표시하는 데 사용. textfield는 한 줄 텍스트 상자이고, textarea는 여러 줄 텍스트 상자이다.
    * ```textfield``` 또는 ```textarea```
7. ```minItems``` (option)
    * 허용되는 최소 항목 수
8. ```maxItems``` (option)
    * 허용되는 최대 항목 수
9.  ```minChars``` (option)
    * 허용되는 파라미터 문자 최소 개수
10. ```maxChars``` (option)
    * 허용되는 파라미터 문자 최대 개수
</br>

### ```mainSteps``` (required)
* 여러 Step(플러그인)을 포함할 수 있는 객체
</br>

### ```outputs``` (option)
* 다른 Step에서 사용할 수 있는 데이터
</br>

### ```files``` (option)
*  Document에 첨부되어 Automation 실행 중에 실행되는 스크립트 파일(및 해당 체크섬). ```aws:executeScript`` 작업을 포함하고 하나 이상의 Step에서 첨부 파일이 지정된 Document에서만 적용됩니다.
```
---
files:
  launch.py:
    checksums:
      sha256: 18871b1311b295c43d0f...[truncated]...772da97b67e99d84d342ef4aEXAMPLE

```
</br>



## 옵션 사용
* ```onFailure```: 이 옵션은 실패 시 자동화가 중지되어야 하는지, 계속되어야 하는지 또는 다른 단계로 이동해야 하는지를 나타냅니다. 이 옵션의 기본값은 ```Abort``` 이다.
* ```nextStep```: 이 옵션은 한 단계를 성공적으로 완료한 후 처리할 자동화의 단계를 지정합니다.
* ```isEnd```: 이 옵션은 특정 단계 종료 시 자동화를 중지합니다. 이 옵션의 기본값은 false입니다.
* ```isCritical```: 이 옵션은 자동화의 성공적 완료에 대해 단계를 심각으로 지정합니다. 이 지정이 있는 단계가 실패하면 Automation은 자동화의 최종 상태를 Failed로 보고합니다. 이 옵션의 기본값은 true입니다.
</br>

### onFail
* ```onFailure: Abort```: 실패시, 자동화 중지
* ```onFailure: step:step_name```: "step_name" 단계로 이동
```
---
schemaVersion: '0.3'
description: Install MSI package and run validation.
assumeRole: "{{automationAssumeRole}}"
parameters:
  automationAssumeRole:
    type: String
    description: "(Required) Assume role."
  packageName:
    type: String
    description: "(Required) MSI package to be installed."
  instanceIds:
    type: String
    description: "(Required) Comma separated list of instances."
mainSteps:
- name: InstallMsiPackage
  action: aws:runCommand
  maxAttempts: 2
  onFailure: Abort
  inputs:
    InstanceIds:
    - "{{instanceIds}}"
    DocumentName: AWS-RunPowerShellScript
    Parameters:
      commands:
      - msiexec /i {{packageName}}
- name: TestInstall
  action: aws:invokeLambdaFunction
  maxAttempts: 1
  timeoutSeconds: 500
  inputs:
    FunctionName: TestLambdaFunction
...
```
</br>

### nextStep / isEnd
* ```nextStep: step_name```: "step_name" 단계로 이동
* ```isEnd: true``` : 자동화 중지 (true, false)
```
mainSteps
- name: InstallMsiPackage
  action: aws:runCommand
  onFailure: step:PostFailure
  maxAttempts: 2
  inputs:
    InstanceIds:
    - "{{instanceIds}}"
    DocumentName: AWS-RunPowerShellScript
    Parameters:
      commands:
      - msiexec /i {{packageName}}
  nextStep: TestInstall
- name: TestInstall
  action: aws:invokeLambdaFunction
  maxAttempts: 1
  timeoutSeconds: 500
  inputs:
    FunctionName: TestLambdaFunction
  isEnd: true
- name: PostFailure
  action: aws:invokeLambdaFunction
  maxAttempts: 1
  timeoutSeconds: 500
  inputs:
    FunctionName: PostFailureRecoveryLambdaFunction
  isEnd: true
...
```
</br>

### isCritical
* ```isCritical: true```: 해당 단계를 중요 단계로 정의함으로써 ```onFailure: step:stepName```으로 실패시 넘어가 단계를 통과하더라도 실패로 처리한다.
```
---
name: InstallMsiPackage
action: aws:runCommand
onFailure: step:VerifyDependencies
isCritical: false
maxAttempts: 2
inputs:
  InstanceIds:
  - "{{instanceIds}}"
  DocumentName: AWS-RunPowerShellScript
  Parameters:
    commands:
    - msiexec /i {{packageName}}
nextStep: TestPackage
...
```


## 시스템 변수
* 다음은 간단한 작성 예시이다.
* https://docs.aws.amazon.com/ko_kr/systems-manager/latest/userguide/automation-variables.html
```
---
description: Sample runbook using AWS API operations
schemaVersion: '0.3'
assumeRole: "{{ AutomationAssumeRole }}"
parameters:
  AutomationAssumeRole:
    type: String
    description: "(Optional) The ARN of the role that allows Automation to perform the actions on your behalf."
    default: ''
  ImageName:
    type: String
    description: "(Optional) Image Name to launch EC2 instance with."
    default: "Windows_Server-2016-English-Full-Base-2018.07.11"
mainSteps:
  - name: getImageId
    action: aws:executeAwsApi
    inputs:
      Service: ec2
      Api: DescribeImages
      Filters:  
      - Name: "name"
        Values: 
        - "{{ ImageName }}"
    outputs:
    - Name: ImageId
      Selector: "$.Images[0].ImageId"
      Type: "String"
  - name: launchOneInstance
    action: aws:executeAwsApi
    inputs:
      Service: ec2
      Api: RunInstances
      ImageId: "{{ getImageId.ImageId }}"
      MaxCount: 1
      MinCount: 1
    outputs:
    - Name: InstanceId
      Selector: "$.Instances[0].InstanceId"
      Type: "String"
  - name: waitUntilInstanceStateRunning
    action: aws:waitForAwsResourceProperty
    # timeout is strongly encouraged for action - aws:waitForAwsResourceProperty
    timeoutSeconds: 60
    inputs:
      Service: ec2
      Api: DescribeInstanceStatus
      InstanceIds:
      - "{{ launchOneInstance.InstanceId }}"
      PropertySelector: "$.InstanceStatuses[0].InstanceState.Name"
      DesiredValues:
      - running
  - name: assertInstanceStateRunning
    action: aws:assertAwsResourceProperty
    inputs:
      Service: ec2
      Api: DescribeInstanceStatus
      InstanceIds:
      - "{{ launchOneInstance.InstanceId }}"
      PropertySelector: "$.InstanceStatuses[0].InstanceState.Name"
      DesiredValues:
      - running
  outputs:
  - "launchOneInstance.InstanceId"
...
```
* ```"{{ parameter }}"``` : 파라미터 값 사용
* ```"{{ stepName.output }}"``` : 다른 Step의 Output 값 사용
    * 기본적으로 Output Value
* ```{{automation:EXECUTION_ID}}``` : 자동화 실행서에 대한 변
* ```Selector : $.<>.<>.<>"``` : aws:executeAwsApi의 경우 사용 가능
    * Python Boto3의 Response 값
    * AWS는 기본적으로 Python boto3 으로 구성되어 있다.
* ```PropertySelector``` : aws:assertAwsResourceProperty 및 aws:waitForAwsResourceProperty의 경우 사용 가능
* `````` : 
* `````` : 
</br>
</br>


## aws:branch
</br>

## 연산자 사용
### And 연산자
```
mainSteps:
- name: switch2
  action: aws:branch
  inputs:
    Choices:
    - And:
      - Variable: "{{GetInstance.pingStatus}}"
        StringEquals: running
      - Variable: "{{GetInstance.platform}}"
        StringEquals: Windows
      NextStep: runPowerShellCommand
    - And:
      - Variable: "{{GetInstance.pingStatus}}"
        StringEquals: running
      - Variable: "{{GetInstance.platform}}"
        StringEquals: Linux
      NextStep: runShellCommand
    Default:
      sleep3
```
</br>

### Or 연산자
```
- Or:
  - Variable: "{{parameter1}}"
    StringEquals: Windows
  - Variable: "{{BooleanParam1}}"
    BooleanEquals: true
  NextStep: RunPowershellCommand
  
- Or:
  - Variable: "{{parameter2}}"
    StringEquals: Linux
  - Variable: "{{BooleanParam2}}"
    BooleanEquals: true
  NextStep: RunShellScript

```
</br>

### Not 연산자
```
mainSteps:
- name: switch
  action: aws:branch
  inputs:
    Choices:
    - NextStep: sleep2
      Not:
        Variable: "{{testParam}}"
        StringEquals: Linux
    - NextStep: sleep1
      Variable: "{{testParam}}"
      StringEquals: Windows
    Default:
      sleep3
```
</br>

### Operation 종류
1. String operations
  * StringEquals
  * EqualsIgnoreCase
  * StartsWith
  * EndsWith
  * Contains
2. Numeric operations
  * NumericEquals
  * NumericGreater
  * NumericLesser
  * NumericGreaterOrEquals
  * NumericLesser
  * NumericLesserOrEquals
3. Boolean operation
  * BooleanEquals
</br>
</br>


## 옵션 사용
* ```onFailure```: 이 옵션은 실패 시 자동화가 중지되어야 하는지, 계속되어야 하는지 또는 다른 단계로 이동해야 하는지를 나타냅니다. 이 옵션의 기본값은 ```Abort``` 이다.
* ```nextStep```: 이 옵션은 한 단계를 성공적으로 완료한 후 처리할 자동화의 단계를 지정합니다.
* ```isEnd```: 이 옵션은 특정 단계 종료 시 자동화를 중지합니다. 이 옵션의 기본값은 false입니다.
* ```isCritical```: 이 옵션은 자동화의 성공적 완료에 대해 단계를 심각으로 지정합니다. 이 지정이 있는 단계가 실패하면 Automation은 자동화의 최종 상태를 Failed로 보고합니다. 이 옵션의 기본값은 true입니다.
</br>

### onFail
* ```onFailure: Abort```: 실패시, 자동화 중지
* ```onFailure: step:step_name```: "step_name" 단계로 이동
```
---
schemaVersion: '0.3'
description: Install MSI package and run validation.
assumeRole: "{{automationAssumeRole}}"
parameters:
  automationAssumeRole:
    type: String
    description: "(Required) Assume role."
  packageName:
    type: String
    description: "(Required) MSI package to be installed."
  instanceIds:
    type: String
    description: "(Required) Comma separated list of instances."
mainSteps:
- name: InstallMsiPackage
  action: aws:runCommand
  maxAttempts: 2
  onFailure: Abort
  inputs:
    InstanceIds:
    - "{{instanceIds}}"
    DocumentName: AWS-RunPowerShellScript
    Parameters:
      commands:
      - msiexec /i {{packageName}}
- name: TestInstall
  action: aws:invokeLambdaFunction
  maxAttempts: 1
  timeoutSeconds: 500
  inputs:
    FunctionName: TestLambdaFunction
...
```
</br>

### nextStep / isEnd
* ```nextStep: step_name```: "step_name" 단계로 이동
* ```isEnd: true``` : 자동화 중지 (true, false)
```
mainSteps
- name: InstallMsiPackage
  action: aws:runCommand
  onFailure: step:PostFailure
  maxAttempts: 2
  inputs:
    InstanceIds:
    - "{{instanceIds}}"
    DocumentName: AWS-RunPowerShellScript
    Parameters:
      commands:
      - msiexec /i {{packageName}}
  nextStep: TestInstall
- name: TestInstall
  action: aws:invokeLambdaFunction
  maxAttempts: 1
  timeoutSeconds: 500
  inputs:
    FunctionName: TestLambdaFunction
  isEnd: true
- name: PostFailure
  action: aws:invokeLambdaFunction
  maxAttempts: 1
  timeoutSeconds: 500
  inputs:
    FunctionName: PostFailureRecoveryLambdaFunction
  isEnd: true
...
```
</br>

### isCritical
* ```isCritical: true```: 해당 단계를 중요 단계로 정의함으로써 ```onFailure: step:stepName```으로 실패시 넘어가 단계를 통과하더라도 실패로 처리한다.
```
---
name: InstallMsiPackage
action: aws:runCommand
onFailure: step:VerifyDependencies
isCritical: false
maxAttempts: 2
inputs:
  InstanceIds:
  - "{{instanceIds}}"
  DocumentName: AWS-RunPowerShellScript
  Parameters:
    commands:
    - msiexec /i {{packageName}}
nextStep: TestPackage
...
```



##

AWS Systems Manager Run Command 
* 신청해야 할 IAM Policy
  * EventBridge ---> SSM
  * SSM ---> EC2
    1) EC2 관리
       * AmazonSSMManagedEC2InstanceDefaultPolicy
    2) EC2에 Run Command 수행
        ```
        {
            "Version":"2012-10-17",
            "Statement":[
                {
                "Effect":"Allow",
                "Action":[
                    "ssm:SendCommand"
                ],
                "Resource":[
                    "arn:aws:ssm:*:*:document/*"
                ]
                },
                {
                "Effect":"Allow",
                "Action":[
                    "ssm:SendCommand"
                ],
                "Resource":[
                    "arn:aws:ec2:*:*:instance/*"
                ],
                "Condition":{
                    "StringLike":{
                        "ssm:resourceTag/Finance":[
                            "WebServers"
                        ]
                    }
                }
                }
            ]
        }
        ```
    3) SSM ---> CloudWhatch 
        ```
        {
            "Effect": "Allow",
            "Action": "logs:DescribeLogGroups",
            "Resource": "*"
        },
        {
            "Effect":"Allow",
            "Action":[
                "logs:CreateLogGroup",
                "logs:CreateLogStream",
                "logs:DescribeLogStreams",
                "logs:PutLogEvents"
            ],
            "Resource":"arn:aws:logs:*:*:log-group:/aws/ssm/*"
        }
        ```



  * VPC Endpoint
    * ssm
    * ssmmessages
    * ec2messages
    * logs
    * s3

