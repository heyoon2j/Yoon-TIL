# AWS Systems Manager
* AWS Resource를 보고 제어하기 위해 사용되는 서비스
* AWS Resource에 대한 운영 테스크를 자동화할 수 있다.
> Ansible 기능을 가졌다고 생각하면 된다.
</br>





## Automation

</br>

## Component
* __Automation runbook__ : AWS 리소스에 대한 자동화 작업들을 정의 (자동화 문서)
* __Automation action__ : runbook에 작성된 하나의 작업 단계 (AWS Resource에 대한 작업)
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
</br>
</br>




## IAM Setting
* Systems Manager Automation은 기본적으로 Lambda를 사용하여 동작하며, Automation을 위해서는 아래와 같은 Role/Policy가 필요하다.
1. Systems Manager Automation Role  
    1) ```AmazonSSMAutomationRole``` : 동작은 Lambda를 사용하여 동작하며, Lambda Naming은 __Automation__으로 시작한다. ARN 형식은 다음과 같다(```"arn:aws:lambda:*:*:function:Automation*"```)
    2) ```AWSLambdaRole``` : Naming이 다른 Lambda를 사용해야 되는 경우 필요
    3) __다른 AWS 서비스를 호출하기 위한 Policy__
    4) ```iam:PassRole``` Policy : 자동화를 실행할 때, 역할을 다른 서비스 또는 Systems Manager 기능에 전달 (Instance Proflie 추가 등)
       * 정책에서 허용할 Resource로 생성한 SSM Role ARN 추가.
2. IAM User Role
    1) ```AmazonSSMFullAccess``` 또는 유사한 Policy : Systems Manager를 사용할 IAM User/Group/Role에 추가
    2) ```iam:PassRole``` Policy : Systems Manager Role을 SSM에 전달해야 되므로 필요. 허용할 Resource로 1번 SSM Role ARN 추가. 
> User -> Systems Manager -> AWS Resource (Lambda, EC2 등). IAM USER가 PassRole을 가지고 있어야 Resource가 해당 Role을 필요로 할 때 가주갈 수 있다.
3. EventBridge Role
    * 신뢰관계 : events.amazonaws.com
    * ```iam:PassRole``` : 1번 SSm ROle ARN 추가
    * ```ssm:StartAutomationExecution``` : 사용할 자동화문서 실행 정책


## Automation Resource Group
* Automation runbook에 대한 Target Group을 설정할 수 있다.
* https://docs.aws.amazon.com/systems-manager/latest/userguide/automation-working-targets.html#automation-working-targets-resource-groups
1. Tag 설정
    * Key / Value를 통해 사용
    * Example
        ```
        aws ssm start-automation-execution \
        --document-name AWS-DeleteSnapshot \
        --targets Key=tag:Name,Values=January2018Backups \
        --target-parameter-name VolumeId
        ```
2. Parameter 값 입력
    * 직접 Target들을 적는다.
    * 
        ```
        aws ssm start-automation-execution 
        --document-name AWS-CreateImage \
        --target-parameter-name InstanceId \
        --targets Key=ParameterValues,Values=i-02573cafcfEXAMPLE,i-0471e04240EXAMPLE
        ```
    * Target Map 사용
        ```

        ```
3. 전체 인스턴스
4. d
5. 


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
* aws:runCommand : 간리형 인스턴스에서 명령 실행
* aws:sleep : 지정된 시간 동안 자동화를 지연
* aws:waitForAwsResourceProperty : AWS 리소스 상태 또는 이벤트 상태가 특정 상태가 될 때까지 대기
* https://docs.aws.amazon.com/systems-manager/latest/userguide/automation-action-copyimage.html


### 작업 순서
1. Resource Group 생성
    * Resource Group에 대하여 자식 자동화(Child Automation)를 실행 (Multi Thread? Process? 형태로 작동한다는 의미인듯)
    * https://docs.aws.amazon.com/systems-manager/latest/userguide/automation-working-targets.html#automation-working-targets-resource-groups
2. Lambda 작성
3. Automation runbook 작성
4. 실행








