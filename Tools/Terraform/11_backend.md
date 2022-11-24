# Terraform Backend
* Terraform의 State file(.tfstate file)을 저장하는 위치를 정의
* Local Storage가 아닌 Remot Storage에 저장함으로써 상태 데이터를 공유할 수 있고, 이를 통해 다수가 작업할 수 있게 된다.
> 현재 Backend를 Workspace 별로 구분하기 위해서는 Config 파일과 CLI를 써야 된다. 
</br>

## Backend 구성 방식
GitOps 형태로 구성 : Github Actions + S3(+DynamoDB) 사용하여 구성
* https://velog.io/@xgro/terraform-infra-provisioning
* Github Action
    * Push - Pull request - Github Action Trigger 발생 (Terraform 실행)
* Terraform
    * terraform init : 상태 파일 체크 (S3 + DynamoDB 확인)
    * terraform plan : 인프라 이상 유무 체크
    * terraform apply -auto-approve : 인프라 구축 적용 및 상태 파일 저장
</br>
</br>


## Backend 설정


### .tf 
```
terraform {
  backend "remote" {
    organization = "example_corp"

    workspaces {
      name = "my-app-prod"
    }
  }
}

```
* 하나의 백엔드 블록만 제공 가능
* 백엔드 블록은 변수를 참조할 수 없다


```

```
* ```*.backendname.tfbackend``` (e.g. config.s3.tfbackend) 파일 명을 추천하다.

