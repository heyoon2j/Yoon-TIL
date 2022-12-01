# Terraform Code DRY
```
# Indicate where to source the terraform module from.
terraform {
  source = "tfr:///terraform-aws-modules/vpc/aws?version=3.5.0"
}

# Indicate what region to deploy the resources into
generate "provider" {
  path = "provider.tf"
  if_exists = "overwrite_terragrunt"
  contents = <<EOF
provider "aws" {
  region = "us-east-1"
}
EOF
}

# Indicate the input values to use for the variables of the module.
inputs = {
  name = "my-vpc"
  cidr = "10.0.0.0/16"

  azs             = ["us-east-1a", "us-east-1b", "us-east-1c"]
  private_subnets = ["10.0.1.0/24", "10.0.2.0/24", "10.0.3.0/24"]
  public_subnets  = ["10.0.101.0/24", "10.0.102.0/24", "10.0.103.0/24"]

  enable_nat_gateway = true
  enable_vpn_gateway = false

  tags = {
    Terraform = "true"
    Environment = "dev"
  }
}
```
* ```terraform {}```은 Terragrunt가 Terraform과 상호 작용하는 방식을 구성하는데 사용
    * Public Terraform Registry에서 코드를 가지고 올 때에 ```tfr://REGISTRY_DOMAIN/MODULE?version=VERSION```를 사용
* ```generate {}```는 Provider를 활성화하는 데 사용
* ```inputs {}```는 변수 값을 전달하는데 사용
* ```remote_state {}```는 Backend 설정하는데 사용
* ``````



## Backend DRY
* Terraform 구조
    ```
    stage
    ├── frontend-app
    │   └── main.tf
    └── mysql
        └── main.tf
    ```
    ```
    # stage/frontend-app/main.tf
    terraform {
        backend "s3" {
            bucket         = "my-terraform-state"
            key            = "stage/frontend-app/terraform.tfstate"
            region         = "us-east-1"
            encrypt        = true
            dynamodb_table = "my-lock-table"
        }
    }
    ```
    ```
    # stage/mysql/main.tf
    terraform {
        backend "s3" {
                bucket         = "my-terraform-state"
                key            = "stage/mysql/terraform.tfstate"
                region         = "us-east-1"
                encrypt        = true
                dynamodb_table = "my-lock-table"
        }
    }
    ```
    * 각 서비스, 리소스마다 비슷한 형태의 상태파일 설정이 필요
    * ```backend {}```에는 변수 입력이 불가

* Terragrunt 구조
    ```
    stage
    ├── terragrunt.hcl
    ├── frontend-app
    │   ├── main.tf
    │   └── terragrunt.hcl
    └── mysql
        ├── main.tf
        └── terragrunt.hcl
    ```
    ```
    # stage/terragrunt.hcl
    remote_state {
        backend = "s3"
        generate = {
            path      = "backend.tf"
            if_exists = "overwrite_terragrunt"
        }
        config = {
            bucket = "my-terraform-state"

            key = "${path_relative_to_include()}/terraform.tfstate"
            region         = "us-east-1"
            encrypt        = true
            dynamodb_table = "my-lock-table"
        }
    }
    ```
    ```
    # stage/mysql/terragrunt.hcl
    include "root" {
        path = find_in_parent_folders()
    }
    ```
    * ```path_relative_to_include()```를 사용하여 Path를 변수로 사용할 수 있다.
    * ```include "root" {}```
    * ```find_in_parent_folders()``` Helper는 자동으로 Root terragrunt.hcl을 찾아 구성을 업데이트 한다는 의미이다.
</br>

## Provider DRY




</br>


## Terraform CLI DRY




</br>




## ENV 


</br>


