# Terraform Start

## Basic
1. **Provider**
    * Terraform과 외부 서비스를 연결해주는 기능을 하는 모듈
    * AWS, Azure, GCP와 같은 Public Cloud 뿐만 아니라, MySQL, Docker와 같은 로컬 서비스 등을 지원
    * 서비스 종류는 공식 문서에서 확인 가능
        * https://www.terraform.io/docs/providers/index.html#inner
2. **HCL**
    * Hashicorp Configuration Language 약어
    * Terraform에서 사용하는 설정 언어
    * HCL 파일의 확장자는 .tf를 사용
3. __Init__
    - Provider Plugin을 자동으로 찾아서 다운로드하고 설치
    - Backend 설정 확인
    - 모듈 확인 후 복사
4. **Plan**
    * Terraform Project Directory 아래의 모든 .tf 파일의 내용을 실제로 적용 가능한지 확인하는 작업.
    * **terraform plan** 명령어를 제공하며, 해당 명령어가 실행 시 어떤 리소스가 생성, 수정, 삭제될지를 보여준다.
    
5. **Apply**
    * Terraform Project Directory 아래의 모든 .tf 파일의 내용대로 리소스를 생성, 수정, 삭제하는 일을 적용이라 한다.
    * **terraform apply** 명령어를 제공한다.


## Terraform Execution 과정
* 동작을 위한 기본 Step은 다음과 같다.
1) HCL을 이용하여 .tf 파일에 Infra Resources을 정의한다.
2) __terraform init__
    * provider, module, state 파일을 읽어 초기화 작업 진행
    * 초기화에 대한 라이브러리 등이 .terraform Directory에 저장
    * 인프라 관련 동시성처리를 위한 .terraform.lock.hcl 파일 생성
    * 기존에 .tfstate 파일이 정의되어 있다면, 현재 상태와 Backend Storage와 Sync를 맞춘다.
3) __terraform plan__
    * 작업한 코드에 대한 결과를 예측하여 작업할 내용 출력
4) __terraform apply__
    * 실제 환경에 인프라 구성 작업 진행
    * 작업 결과에 대하여 .tftate file에 저장(Local/Backend Storage 모두 저장)
</br>
</br>


## Terraform File
* ```.terraform```
    - 인프라를 프로비저닝 하는데 사용되는 모듈과 플러그인 등이 포함되어 있다.
* ```terraform.tfstate``` 및 ```terraform.tfstate.backup```
    - Terraform 상태를 포함하며, Terraform의 구성과 프로비저닝된 인프라 간의 관계를 추적할 수 있다.
    - 기본적으로 .terraform Directory에 저장된다.
* ```.terraformrc```(Linux) 및 ```terraform.rc / terraform.rc.txt```(Windows)
    - Terraform CLI 구성 파일
    - CLI 동작에 대한 설정을 구성할 수 있다!
</br>
</br>


### Step 1. Provider Definition & Initialization
* Terraform이 어떤 서비스와 연결할지 Provider를 정의를 해야 된다.
* 공식 문서 : https://registry.terraform.io/providers/hashicorp/aws/latest/docs#argument-reference

1) Make Project Directory
    * 먼저 Project에 사용될 Directory 생성
    ```shell script
    mkdir ~/terraform_aws
    ```

2) Make Provider.tf
    * Provider 정의
        ```shell script
        touch provider.tf
        // 내용
        terraform {
         required_providers {
           <PROVIDER_NAME> = {
            source  = "<SOURCE_PATH>"
            version = "~> 3.0"
          }
         }
        }
        provider "<PROVIDER_NAME>" {
         <ATTR_NAME> = "<ATTR_VALUE>"
        }
        ```
        
    * example
       ```shell script
       terraform {
         required_providers {
           aws = {
             source  = "~/terraform_aws"
             version = "~> 3.0"
           }
         }
       }
        provider "aws" {
         access_key = "<AWS_ACCESS_KEY>"
         secret_key = "<AWS_SECRET_KEY>"
         region = "ap-northeast-2"
        }
        ```
   
    * AWS 설정 값은 보통 Provider에 쓰지 않고, 환경변수 또는 파일 설정을 통해서도 가능하다.
        1) 환경변수 설정
            ```shell script
            #export AWS_ACCESS_KEY_ID="<AWS_ACCESS_KEY_ID>"         // access_key
            #export AWS_SECRET_ACCESS_KEY="<AWS_SECRET_ACCESS_KEY>" // secret_key
            #export AWS_DEFAULT_REGION="ap-northeast-2"             // region
            ```
        2) 파일 설정
            ```shell script
            vi ~/.aws/config
            [default]
            region = ap-northeast-2
            
            vi ~/.aws/credentials
            [default]
            aws_secret_access_key = <SECRET_KEY>
            aws_access_key_id = <KEY_ID>
            ```
4) Terraform Project Initialization
    * 이제 Provider를 기준으로 Project를 초기화시켜준다.
    * **terraform init** 명령어 사용


### Step 2. Write Modules
* Terraform에서는 Directory 하나가 모듈의 단위가 되기 때문에,
공용으로 사용되는 Infra 구성은 모듈화하여 관리한다.
* 모듈화하면, 재사용이 용이해진다.
* 공식 문서 : https://www.terraform.io/docs/modules/index.html

1) __Module의 기본 구조__
    * 기본적으로 Module은 "Root Module"과 "Child Module"이 있다.
    * Root Module에서는 다른 Child Module을 호출하여 출력 값을 다른 Child Module의 입력 값으로 전달하여 Module들을 연결시킬 수 있다.
    * 중첩 Module의 경우는 ```modules/```의 하위에 위치해야 한다.
        1. __Input variables__
            * 다른 Module로부터 입력받을 입력 값들을 관리
            * variables.tf 파일에 저장
        2. __Output variables__
            * 다른 Module에게 반환할 출력 값들을 관리
            * output.tf 파일에 저장
        3. __Resources__
            * Infrastructure Object들을 관리
            * main.tf 파일에 저장
        4. ```terraform.tfstate``` 및 ```terraform.tfstate.backup```
            * Terraform 상태를 포함하며, Terraform의 구성과 프로비저닝 된 인프라 간의 관계를 추적할 수 있다.
        5. ```.terraform```
            * 인프라를 프로비저닝 하는데 사용되는 모듈과 플러그인이 포함되어 있다.
            * .tf 파일에 정의된 인프라의 구성이 아닌 Terraform의 특정 인스턴스에 한정된다.
        6. ```*.tfvars```
        
    * Directory Structure 
        ```shell script
        $ tree complete-module/
        .
        ├── README.md
        ├── main.tf
        ├── variables.tf
        ├── outputs.tf
        ├── ...
        ├── modules/
        │   ├── nestedA/
        │   │   ├── README.md
        │   │   ├── variables.tf
        │   │   ├── main.tf
        │   │   ├── outputs.tf
        │   ├── nestedB/
        │   ├── .../
        ├── examples/
        │   ├── exampleA/
        │   │   ├── main.tf
        │   ├── exampleB/
        │   ├── .../

        ============================================================
        https://www.digitalocean.com/community/tutorials/how-to-structure-a-terraform-project
        .
        └── tf/
            ├── modules/
            │   ├── network/
            │   │   ├── main.tf
            │   │   ├── dns.tf
            │   │   ├── outputs.tf
            │   │   └── variables.tf
            │   └── spaces/
            │       ├── main.tf
            │       ├── outputs.tf
            │       └── variables.tf
            └── applications/
                ├── backend-app/
                │   ├── env/
                │   │   ├── dev.tfvars
                │   │   ├── staging.tfvars
                │   │   ├── qa.tfvars
                │   │   └── production.tfvars
                │   └── main.tf
                └── frontend-app/
                    ├── env/
                    │   ├── dev.tfvars
                    │   ├── staging.tfvars
                    │   ├── qa.tfvars
                    │   └── production.tfvars
                    └── main.tf

        
        ```
    
2) Root Module
    * 필수 요소인 Module
    * 파일의 위치는 Project Root Directory에 위치해야 된다.
    * Root Module은 다른 Module의 기본 진입점이다.
    * main.tf에는 Child Module의 Source 위치 또는 Module들의 연결을 정의한다.

3) Child Module 
    * Resource들을 정의한다.
    * main.tf Example
        * 다른 Resource의 Argument에 접속하기 위해서는 <RESOURCE_TYPE>.<NAME>.<ATTRIBUTE>로 접근
        ```shell script
        # Syntax
        resource "<RESOURCE_TYPE>" "<NAME>"{
          <ATTRIBUTE> = <VALUE>
        }
        
        # Example
        resource "aws_vpc" "myVPC" {
          cidr_block = "${var.vpc_cidr}"
          tags = {
            Name = "${var.name}"
          }
          enable_dns_hostnames = "true"
        }
        ```
    * variables.tf Example
        ```shell script
        variable "name" {
          description = "VPC Name"
          type = "string"
        }
        ```
    * output.tf Example
        ```shell script
        output "myPub-id" {
          value = "${aws_subnet.myPubC.id}"
        }
        ```

### Step 3. Calling Modules
* Calling Child Module
* Child Module을 추가한 후에는 ```terraform init``` 명령어를 통해 프로젝트를 초기화해줘야 된다.
    * 자식 모듈 호출 방법
    ```shell script
    # INPUT_VARIABLE은 Module의 variables.tf 파일에 정의되어 있어야 한다.
    # 다른 Module의 값을 사용하기 위해서는 다른 Module의 output.tf 파일에 정의되어 있거나, 해당 파일에 정의되어 있어야 한다. 
    module "<MODULE_NAME>" {
     source = "<SOURCE_PATH>"
     <INPUT_VARIABLE> = "<STRING_VALUE>"
     <INPUT_VARIABLE> = <INTEGER_VALUE>
    }
    
    # example
    module "ec2" {
     source = "./modules/ec2"
    
     vpc_id = "${module.vpc.name}"
    }
    module "vpc" {
     source = "./modules/vpc"
    
     name = "spoonVPC"
     vpc_cidr = "192.168.0.0/16"
    }
    ```


### Step 4. Terraform Plan & Apply
1) terraform plan 명령어
    * terraform plan 명령어를 실행하면, 
    Terraform Code와 현재 Infra 상태를 비교하여 생성/변경/삭제할 Resource 항목들을 나열한다.
    * 원하는 대로 결과가 출력이 되지 않으면 Code를 수정한다.
    ```shell script
    $ terraform paln
    ```

2) terraform apply 명령어
    * terraform plan 명령어를 통해 원하는 결과가 확인되면, 결과를 현재 Infra에 적용해야 한다. 
    * terraform apply 명령어를 실행하면,
    생성/변경/삭제할 Resource 항목들을 현재 Infra에 적용한다.
    ```shell script
    $ terraform apply
    ```


### Step 5. Terraform Destroy
* terraform destroy 명령어
    * 해당 명령어를 통해 Infra를 삭제한다.
    ```
    $ terraform destroy [options] [dir]
    ```

### Reference
* https://www.44bits.io/ko/post/terraform_introduction_infrastrucute_as_code
* Terraform 공식 사이트 : https://www.terraform.io/
</br>
</br>


## Terraform 전체 아키텍처
* 코드를 저장할 Remote Repository 구축
* 상태 파일을 격리시켜 저장할 Remote Storage 구축 (AWS S3, DynomoDB)
* Terraform 인프라 구축을 위한 Directory 구조 설계
* Terraform Workspace 생성
* Terraofrm Module, Resource 등의 코드 작성
* 실행 (+ GitHub Actions 등과 같은 람다를 활용하여 자동화 구축)
</br>


#### 참고하기 좋은 사이트
* https://github.com/futurice/terraform-examples

