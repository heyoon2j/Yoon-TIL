# AWS Systems Manager
* AWS Resource를 제어하기 위해 사용되는 서비스
* AWS Resource에 대한 운영 테스크를 자동화할 수 있다.
> Ansible 기능을 가졌다고 생각하면 된다.
</br>

---
# Automation
AWS 리소스 전체에 대한 자동화
</br>

## Component
* __Automation runbook__ : AWS 리소스에 대한 자동화 작업들을 정의된 자동화 문서
* __Automation step__ : runbook에는 하나 이상의 단계가 포함되며, 각 단계에는 특정 작업에 대한 내용이 정의.
* __Automation action__ : 해당 단계에 대한 입력, 동작 및 출력을 결정하는 작업 정의.
* __Resource Group__ : runbook이 적용될 Resources 집합
* __Automation quota__ : 각 AWS 계정은 100개의 자동화를 동시에 실행할 수 있다 (동시 실행에 대한 개수 제한)
* __Automation queue quota__ : 동시 자동화 제한보다 많은 자동화를 실행하려면 후속 자동화 대기열에 추가된다 (1000개의 자동화 대기열)
* __Rate control automation quota__ : 각 AWS 계정은 25개의 속도 제어 자동화를 동시에 실행할 수 있다 (동시 실행 시, 각 자동화 순서 제어)
* __Rate control automation queue quota__ : 동시 속도 제어 자동화 제한보다 많은 자동화를 실행하려면 후속 자동화 대기열에 추가된다 (1000개의 자동화 대기열)
</br>


### vs Ansible
* Playbook : 구성, 배포 및 오케스트레이션 등이 포함된 파일
* Module : Ansible이 정의해둔 실행 단위로 실행할 수 있는 라이브러리
* Inventory : 관리할 대상 IP Address 목록
* Role : 작업들에 대한 모듈화(모음)
* Ansible Config : Ansible 환경변수 정의 파일
* 비교 표
    | SSM Automation     | Ansible       |
    | ------------------ | ------------- |
    | Runbook            | Playbook/Role |
    | Action             | Module        |
    | Resource Group/TAG | Inventory     |
</br>
</br>




## IAM Setting
* Systems Manager Automation은 기본적으로 Lambda를 사용하여 동작하며, Automation을 위해서는 아래와 같은 Role/Policy가 필요하다.
1. IAM User Role
    1) ```AmazonSSMFullAccess``` 또는 유사한 Policy : Systems Manager를 사용할 IAM User/Group/Role에 추가
    2) ```iam:PassRole``` Policy : Systems Manager Automation Role을 SSM Automation에 전달해야 하므로 필요. 허용할 Resource로 1번 SSM Automation Role ARN 추가. 
2. Systems Manager Automation Role  
    1) ```AmazonSSMAutomationRole```
      * AWS 관리형 정책으로 SSM Automation이 기본적으로 동작하는데 필요한 정책들이 포함되어 있다. 
      * SSM에서 Lambda를 사용하는 경우, Lambda Naming은 __Automation__으로 시작한다. ARN 형식은 다음과 같다(```"arn:aws:lambda:*:*:function:Automation*"```)
    2) ```AWSLambdaRole``` : Naming이 다른 Lambda를 사용해야 되는 경우 필요
    3) __다른 AWS 서비스를 호출하기 위한 Policy__
    4) ```iam:PassRole``` Policy : 자동화를 실행할 때, 역할을 다른 서비스 또는 Systems Manager 기능에 전달 (Instance Proflie 추가 등)
       * 정책에서 허용할 Resource로 생성한 SSM Role ARN 추가하여, 자기한테 적용된 Role을 전달할 수 있다.
3. EventBridge Role
    * 신뢰관계 : events.amazonaws.com
    * ```iam:PassRole``` : 1번 SSm ROle ARN 추가
    * ```ssm:StartAutomationExecution``` : 사용할 자동화문서 실행 정책
> User(iam:PassRole) -> Systems Manager(iam:PassRole) -> AWS Resource (Lambda, EC2 등). IAM USER가 PassRole을 가지고 있어야 Resource가 해당 Role을 필요로 할 때 전달할 수 있다.

</br>

## Automation Resource Group
* Automation runbook의 Parameter에 대하여 Target Group을 설정할 수 있다.
* Resource Group 생성 방법으로 4가지가 있다.
* https://docs.aws.amazon.com/systems-manager/latest/userguide/automation-working-targets.html#automation-working-targets-resource-groups
1. Tag 지정
    * 대상으로 지정할 Instances의 Tag Key/Value 지정
    * Example
        ```
        aws ssm start-automation-execution \
        --document-name AWS-DeleteSnapshot \
        --targets Key=tag:Name,Values=January2018Backups \
        --target-parameter-name VolumeId
        ```
2. Parameter 값 입력
    * 직접 Target들을 적는다.
    * Document에서 리소스 타입을 지정해두고 해당하는 Resource의 Parameter 값을 입력
        ```
        aws ssm start-automation-execution 
        --document-name AWS-CreateImage \
        --target-parameter-name InstanceId \
        --targets Key=ParameterValues,Values=i-02573cafcfEXAMPLE,i-0471e04240EXAMPLE
        ```
    * Target Map 사용
        ```

        ```
3. 모든 인스턴스
   *  현재 AWS 계정 및 AWS 리전의 모든 관리형 인스턴스에 대해 자동화를 실행
4. Resource Group
   * AWS Resource Group에서 지정한 Resource Group 사용


## Automation actions reference
* aws:invokeLambdaFunction : Lambda 실행
* aws:approve : 수동 승인을 위해 자동화 일시 중지 동작
* aws:assertAwsResourceProperty : AWS 리소스 상태 또는 이벤트 상태가 특정 상태로 될 때까지 대기
* aws:branch : 여러 선택 항목을 평가한 다음 결과에 따라 특정 단계로 이동하여 action수행
* aws:createStack : CloudFomration Stack 생성
* aws:createTags : EC2 인스턴스의 Tag 생성
* aws:executeAutomation : 또 다른 자동화 실행
* aws:executeAwsApi : AWS API 호출 및 실행
* aws:executeScript : 지정된 런타임 및 핸들러를 사용하여 실행 (Lambda 없이 실행)
* aws:invokeLambdaFunction : AWS Lambda 호출
* aws:pause : 자동화 일시 중지, “SendAutomationSignal” API를 사용하여 신호를 주어야 재개
* aws:runCommand : 관리형 인스턴스에서 명령 실행
* aws:sleep : 지정된 시간 동안 자동화를 지연
* aws:waitForAwsResourceProperty : AWS 리소스 상태 또는 이벤트 상태가 특정 상태가 될 때까지 대기
* https://docs.aws.amazon.com/systems-manager/latest/userguide/automation-action-copyimage.html
</br>

### 작업 순서
1. Resource Group 생성
    * Resource Group에 대하여 자식 자동화(Child Automation)를 실행 (Multi Thread? Process? 형태로 작동한다는 의미인듯)
    * https://docs.aws.amazon.com/systems-manager/latest/userguide/automation-working-targets.html#automation-working-targets-resource-groups
2. Lambda 작성
3. Automation runbook 작성
4. 실행
</br>
</br>




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
* ```Selector : $..."``` : aws:executeAwsApi의 경우 사용 가능
    * Python Boto3의 Response 값
    * AWS는 기본적으로 Python boto3 으로 구성되어 있다.
* ```PropertySelector``` : aws:assertAwsResourceProperty 및 aws:waitForAwsResourceProperty의 경우 사용 가능
* `````` : 
* `````` : 
</br>



---
# Node Management
AWS Computing Service(EC2/ECS) 관리에 대한 자동화
</br>

## Service
서비스 종류는 다음과 같다.
* Fleet Manager
* Session Manager
* Run Command
* Patch Manager
* etc


### vs Ansible
* Playbook : 구성, 배포 및 오케스트레이션 등이 포함된 파일
* Module : Ansible이 정의해둔 실행 단위로 실행할 수 있는 라이브러리
* Inventory : 관리할 대상 IP Address 목록
* Role : 작업들에 대한 모듈화(모음)
* Ansible Config : Ansible 환경변수 정의 파일
* 비교 표
    | SSM Node Management     | Ansible       |
    | ------------------ | ------------- |
    | Run Command/Patch Manager          | Playbook/Role |
    | Action             | Module        |
    | Resource Group/TAG | Inventory     |
</br>
</br>





---
## Patch Management
보안 관련 및 기타 유형의 업데이트 패치에 대한 프로세스를 자동화한다.
</br>

## 사전 조건
* SSM Agent Version : 2.0.834.0 이상
* Python Version :
    * 대부분의 리눅스 : 2.6 ~ 3.9 지원
* 지원되는 OS Version
    * Amazon Linux 2 2~2.0
    * CentOS 6.5~7.9, 8.0~8.5
    * CentOS Stream 8
    * Windows (R2 버전을 포함해 Windows Server 2008부터 Windows Server 2022)
</br>

## 작동 방식
1. 패치 기준 생성
    * 패키지 릴리즈 기준 지정 (ex> 일정 기간 기준으로 릴리즈된 패키지)
    * 승인/거부할 패키지 지정
    * (Optino) 대체 소스 Repository 지정
    * 
2. 패치 그룹 추가 or 태그 추가
3. 패치 적용 구성
4. 패치 작업 모니터링
    * 작업 상태
    * 패치 기준 준수 여부
</br>
</br>


---
## IAM Setting
1. User Role
    * SSM 서비스를 사용하기 위한 권한 필요 (기본 사항)
        1) ```AmazonSSMFullAccess``` 또는 유사한 권한
        2) ```iam:PassRole``` Policy
        3) S3 버킷 액세스 권한 (VPC Endpoint를 사용하는 경우 필수)
    * 기본 호스트 관리 구성 활성화 권한 (자동 관리가 필요할 시 사용)
        1) 기본 호스트 관리 구성 설정 권한
        2) ```iam:PassRole``` Policy : 기본 호스트 관리 구성 설정 시, 역할 전달 필요 (== 2번 Role)
        > 기본 호스트 관리 구성을 활성해서 사용하는 경우 2번, 아닌 경우 3번 정책으로 구성하면 된다
    * Maintenance Windows 권한
        1) Maintenance Windows 사용을 위한 정책
        2) ```iam:PassRole``` Policy : 유지 관리에 작업 등록 시, 권한 필요
    * Parameter Store 권한
        1) Parameter Store 등록을 위한 정책
    * Automation 권한
        1) Automation 사용을 위한 정책
        2) ```iam:PassRole``` Policy : Automation Document 생성 시 필요
2. SSM Fleet Manager 기본 호스트 관리 서비스를 통해 인스턴스를 관리할 경우 사용할 Role (Passive?)
    * ```AmazonSSMManagedEC2InstanceDefaultPolicy ``` : SSM과의 통신을 위한 정책
    * ```CloudWatchAgentServerPolicy``` : CloudWatch 모니터링을 위한 정책
    * ```S3 Bucket Access 정책```
3. 각 각의 EC2 Instance에서 SSM과 통신하여 관리되어질 경우 사용할 Role (Active?)
    * ```AmazonSSMManagedInstanceCore``` : SSM과의 통신을 위한 정책
    * ```CloudWatchAgentServerPolicy``` : CloudWatch 모니터링을 위한 정책
4. SSM Role
    1) Maintenance Windows 사용을 위한 정책
        * Maintenance Windows에 작업을 등록하기 위한 기본 정책 (https://docs.aws.amazon.com/ko_kr/systems-manager/latest/userguide/sysman-maintenance-perm-console.html)
    2) Parameter Store 액세스를 위한 정책
        * 기본 호스트 관리 구성을 활성해서 사용하는 경우 2번, 아닌 경우 3번 역할에 정책 추가
        * (특정) 파라미터 접근 권한
        * KMS 복호화 권한
    3) Run Command & Automation 권한
        * 문서에서 접근하는 리소스에 대한 권한 필요
    4) Patch Manager 권한


