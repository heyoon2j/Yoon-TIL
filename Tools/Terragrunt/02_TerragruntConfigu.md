# Terrgrunt Config File
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


## Funtion






wlm
workspace management 

특정 Table에 대해서 ETL 에서 작업할 때 Truncate 에 대한 Exclusive locak

=> delete 하면 vacumn 이 안됨 

성능 이슈

각 큐별로 나뉘어서 query 분리 
제한 시간 : statement_timeout = 0 옵션 


```
└── live
    ├── terragrunt.hcl
    ├── prod
    │   ├── app
    │   │   └── terragrunt.hcl
    │   ├── mysql
    │   │   └── terragrunt.hcl
    │   └── vpc
    │       └── terragrunt.hcl
    ├── qa
    │   ├── app
    │   │   └── terragrunt.hcl
    │   ├── mysql
    │   │   └── terragrunt.hcl
    │   └── vpc
    │       └── terragrunt.hcl
    └── stage
        ├── app
        │   └── terragrunt.hcl
        ├── mysql
        │   └── terragrunt.hcl
        └── vpc
            └── terragrunt.hcl
└── modules
    ├── app
    │   └── main.tf
    ├── mysql
    │   └── main.tf
    └── vpc
        └── main.tf
```