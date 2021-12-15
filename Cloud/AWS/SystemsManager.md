# AWS Systems Manager
* AWS Resource를 보고 제어하기 위해 사용되는 서비스
* AWS Resource에 대한 운영 테스크를 자동화할 수 있다.
> Ansible 기능을 가졌다고 생각하면 된다.
</br>





## Automation

</br>

## Component
* __Automation runbook__ : AWS 리소스에 대한 자동화 작업들을 정의
* __Automation action__ : runbook에 작성된 하나의 작업 단계
    * __aws:executeAutomation__ : action 중에 Sub 자동화 문서를 실행 가능 (이를 활용하면 Role처럼 사용가능)
* __Automation quota__ : 각 AWS 계정은 100개의 자동화를 동시에 실행할 수 있다
* __Automation queue quota__ : 동시 자동화 제한보다 많은 자동화를 실행하려면 후속 자동화 대기열에 추가된다 (1000개의 자동화 대기열)
* __Rate control automation quota__ : 각 AWS 계정은 25개의 속도 제어 자동화를 동시에 실행할 수 있다.
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
    4) ```iam:PassRole``` Policy : 자동화를 실행할 때, 역할을 다른 서비스 또는 Systems Manager 기능에 전달
       * 정책에서 허용할 Resource로 생성한 SSM Role ARN 추가.
2. IAM User Role
    1) ```AmazonSSMFullAccess``` 또는 유사한 Policy : Systems Manager를 사용할 IAM User/Group/Role에 추가
    2) ```iam:PassRole``` Policy : Systems Manager Role을 SSM에 전달해야 되므로 필요. 허용할 Resource로 1번 SSM Role ARN 추가. 
> User -> Systems Manager -> AWS Resource (Lambda, EC2 등). IAM USER가 PassRole을 가지고 있어야 Resource가 해당 Role을 필요로 할 때 가주갈 수 있다.


## Automation


### Automation actions reference
* 여기서 사용할 action 찾기
* aws:approve : 수동 승인을 위한 자동화 일시 중지
* aws:asserawsResourceProperty : AWS 리소스 상태 또는 이벤트 상태 
* aws:branch : 
* aws:createStack : CloudFormation Stack 생성
* aws:deleteStack : CloudFormation Stack 삭제
* aws:executeAutomation : 다른 automation 실행
* aws:executeAwsApi : Call and run AWS API
* aws:invokeLambdaFunction : Lambda 실행
* 
* https://docs.aws.amazon.com/systems-manager/latest/userguide/automation-action-copyimage.html
