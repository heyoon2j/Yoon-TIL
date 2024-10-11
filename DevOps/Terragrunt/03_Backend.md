# Backend DRY
* Backend 설정을 통해 Terraform의 State file(.tfstate file)을 저장하는 위치를 정의할 수 있다.
* Local Storage가 아닌 Remot Storage에 저장함으로써 상태 데이터를 공유할 수 있고, 이를 통해 다수가 작업할 수 있게 된다.
* 각 환경, 각 프로젝트 서비스 마다 상태파일 저장이 필요한데, Terraform ```backend {}```에는 변수가 입력이 불가하다보니 Hard Coding을 해야한다. 이를 Terragrunt를 사용하여 해결한다.


## Terraform 구조
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
* Terraform ```backend {}```에는 변수가 입력이 불가

</br>
</br>


## Terragrunt 구조
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
## remote_state
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

## generate
generate "backend" {
    path      = "backend.tf"
    if_exists = "overwrite_terragrunt"
    contents = <<EOF
terraform {
    backend "s3" {
        bucket         = "my-terraform-state"
        key            = "${path_relative_to_include()}/terraform.tfstate"
        region         = "us-east-1"
        encrypt        = true
        dynamodb_table = "my-lock-table"
    }
}
EOF
}

```
```
# stage/mysql/terragrunt.hcl, stage/frontend-app/main.tf
include "root" {
    path = find_in_parent_folders()
}
```
* ```path_relative_to_include()```를 사용하여 Path를 변수로 사용할 수 있다.
    > Root Directory가 기준이다!!
* ```include "root" {}```는 Root의 구성을 상속받는다.
* ```find_in_parent_folders()``` Helper는 자동으로 Root terragrunt.hcl을 찾아 구성을 업데이트 한다는 의미이다.
</br>


---
### generate vs remote_state 
구성 시, 둘 중 하나만 선택 가능하다. 그렇기 때문에 제약사항에 따라 선택할 필요가 있다. 차이점은 아래와 같다.
1. 지원 Backend
    * ```remote_state```의 경우, 지원되는 Backend는 S3, GCS만 가능하다.
    * ```generate```의 경우, 모든 Bckend를 지원한다.
2. 리소스 생성 
    * ```remote_state```의 경우, 지원되는 Backend는 S3, GCS만 가능하다.
    * ```generate```의 경우, 모든 Bckend를 지원한다.
3. 그 외
    * 자세한 사항은 공식 사이트 확인.
> 개인적인 견해로는 generate를 쓰는게 좋다고 생각한다. 이유는 코드 분석 때문이다. 리소스 생성과 백엔드 설정이 섞여 있다보니 해당 코드만으로 리소스 생성이 포함되어 있는 건지 판단이 어렵고, 리소스 생성이 포함되어 있다면 다른 설정에서 해당 리소스를 사용할 수 있음으로 인한 변경이 힘들 수 있다!

> Terragrunt에서 제공하는 특별한 기능을 사용하기 위해서라면 remote_state를 사용하는 것이 좋아 보인다(근데 특별한 기능이 있나..?) 
