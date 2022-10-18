# Terraform Start

## Install
1) Download Terraform
    * https://www.terraform.io/downloads.html
    * 해당 OS에 맞는 버전 설치
    ```shell script
    wget https://releases.hashicorp.com/terraform/0.13.3/terraform_0.13.3_linux_amd64.zip
    unzip terraform_0.13.3_linux_amd64.zip
    mv terraform /usr/bin/terraform && sudo chmod +x
    vi ~/.profile
       export PATH="$PATH:/usr/bin/terraform"
    ```
    * Can customize Path

2) Version Check
    ```shell script
    terraform --version
    ```

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

3. **Plan**
    * Terraform Project Directory 아래의 모든 .tf 파일의 내용을 실제로 적용 가능한지 확인하는 작업.
    * **terraform plan** 명령어를 제공하며, 해당 명령어가 실행 시 어떤 리소스가 생성, 수정, 삭제될지를 보여준다.
    
4. **Apply**
    * Terraform Project Directory 아래의 모든 .tf 파일의 내용대로 리소스를 생성, 수정, 삭제하는 일을 적용이라 한다.
    * **terraform apply** 명령어를 제공한다.


## Using Terraform
* 동작을 위한 기본 Step은 다음과 같다.
    1) HCL을 이용하여 .tf 파일에 Infra Resources을 정의한다.
    2) **terraform plan** 명령어를 통해 Resources를 확인한다.
    3) **terraform apply** 명령어를 통해 Resources를 적용시킨다.


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