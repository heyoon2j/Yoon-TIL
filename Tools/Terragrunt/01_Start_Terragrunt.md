# Start Terragrunt
* 인프라는 혼자 구축하지 않고, 여러 사람들과의 협업이 필요하다. 그렇기 때문에 현재 인프라 상태를 나타내는 상태파일을(.tfstate) 공유할 필요가 있다.
    * Backend Block을 사용하여 외부 Storage에 저장
    * Locking의 경우, S3 + DynamoDB 등을 통해 구현 
* Dev, Stg, Prd 등의 각 환경이 있지만, 아키텍처가 다른 것이 아닌 거의 동일한 구조의 아키텍처를 가진다. 즉, 서버 갯수, 특정 정책 및 규제에 따른 설정은 다를 수 있지만, 동일한 서비스를 제공하기 위해 같은 리소스를 사용하게 되어 동일한 코드의 반복이 생긴다는 의미이다.
    * Resource들에 대하여 Module화를 통해 같은 코드를 공유
* 같은 Module을 사용하나 각 환경 별로 설정 값들은 다를 수 있다. 그렇기 때문에 환경 별로 상태파일, 설정파일(.tfvars)을 따로 관리해야 된다.
    * CLI를 통해 상태파일, 설정파일을 설정
* 인프라 구조가 거대지면서 모든 리소스에 대한 상태와 설정을 하나의 파일에 담기 힘들다. 그렇기 때문에 각 서비스 각 리소스당 당을 필요가 있다.
    * 현재 Terraform 기능만으로는 지원하지 않는다.
    * CLI는 복잡성을 증가 시킨다.
> Terragrunt라는 Tool을 사용하여 관리한다!!!

## Terragrunt Execution 과정
* 동작을 위한 기본 Step은 다음과 같다.
1) Terragrunt Config 파일에 ```source```가 선언되어 있고 ```terragrunt <command>```를 실행
2) terragrunt init 자동으로 실행(auto-init 기능이 존재, 비활성 가능 : https://terragrunt.gruntwork.io/docs/features/auto-init/)
3) 코드를 스크래치 디렉터리에 다운로드하고(Default: .terragrunt-cache file) 현재 작업 디렉터리의 코드를 동일한 스크래치 디렉터리에 복사한 다음 ```terraform <command>```를 실행한다.
4) ```apply``` 명령어 시에 error가 발생한 것으로 간주되면, 자동으로 재실행을 시도(auto-retry 기능이 존재, 비활성 가능 : https://terragrunt.gruntwork.io/docs/features/auto-retry/)
</br>
</br>

---
## Terragrunt 구축 순서
1. Project's Infra Structure 확인
2. Directory Structure 생성
3. 각 서비스의 종속성 그래프 그리기
4. hcl 파일 구성
5. Terragrunt 실행
</br>

---
## Terrgrunt Config File
Terragrunt 구성은 ```terragrunt.hcl``` 파일에 정의된다.
* Terragrunt 구성파일 위치 검색 우선순위
    1. ```--terragrunt-config``` 옵션 지정
    2. ```TERRAGRUNT_CONFIG``` 환경 변수 값
    3. 현재 작업 디렉토리에 있는 ```terragrunt.hcl``` 파일
    4. 현재 작업 디렉토리에 있는 ```terragrunt.hcl.json``` 파일
    5. 아무것도 없는 경우 오류 발생
</br>


## Syntax
* Terragrunt 구성 구문 분석 우선순위
    1. ```include {}```
    2. ```locals {}``` 
    3. ```iam_role``` 정의
    4. ```dependencies {}```
    5. ```dependency {}```
    6. 그 외 모든 것
    7. ```include {}```에서 참조하는 구성
    8. ```include {}```에서 참조하는 구성과 현재 구성 간의 병합 작업

* 트리에서 아래 순서대로 구성에 대한 모듈 종속성 그래프를 작성한다.
    1. ```include``` 트리의 모든 구성 블록
    2. ```locals``` 트리의 모든 구성 블록
    3. ```dependency``` 트리의 모든 구성 블록
    4. ```terrafrom``` 트리의 모든 구성 블록
    5. ```dependencies``` 트리의 모든 구성 블록

</br>
</br>


---
## Structure
```script
.
├── provider.hcl
├── remote_state.hcl
├── terragrunt.hcl
├── code
│   ├── tgw_routing.py
│   └── vpc_routing.py
├── policy
│   ├── assumeRole_apigw.json
│   ├── assumeRole_ec2.json
│   ├── policy_bucket_policy.json
│   └── policy_lambda_InvokeFunction.json
├── modules
│   ├── computing
│   │   ├── alb
│   │   │   ├── main.tf
│   │   │   ├── outputs.tf
│   │   │   └── variables.tf
│   │   ├── eks
│   │   ├── nlb
│   │   │   ├── main.tf
│   │   │   ├── outputs.tf
│   │   │   └── variables.tf
│   │   └── sg
│   │       ├── main.tf
│   │       ├── outputs.tf
│   │       └── variables.tf
│   ├── networking
│   │   ├── tgw
│   │   │   ├── main.tf
│   │   │   ├── outputs.tf
│   │   │   └── variables.tf
│   │   └── vpc
│   │       ├── main.tf
│   │       ├── outputs.tf
│   │       └── variables.tf
│   ├── security
│   │   └── iam
│   │       ├── main.tf
│   │       ├── outputs.tf
│   │       └── variables.tf
│   └── storage
│       └── s3
│           ├── main.tf
│           ├── outputs.tf
│           └── variables.tf
├── network-proto
│   ├── acl
│   │   ├── config.hcl
│   │   └── prd
│   │       └── tgw
│   │           └── terragrunt.hcl
│   └── secure
│       ├── config.hcl
│       └── prd
│           ├── iam
│           │   └── terragrunt.hcl
│           └── vpc
│               └── terragrunt.hcl
└── proto
    ├── config.hcl
    ├── dev
    │   ├── efs
    │   ├── s3
    │   └── vpc
    │       └── terragrunt.hcl
    ├── prd
    └── stg
```
* provider.hcl : Terraform Provider 정보
* remote_state.hcl : 상태 정보에 대한 원격 저장 위치 정보
* terragrunt.hcl : Terragrunt 설정 정보
* code : Terraform으로 구현하지 못하는 내용에 대해서는 스크립트로 적용하기 코드
* policy : IAM Policy 코드
* modules : Terraform Module 코드
