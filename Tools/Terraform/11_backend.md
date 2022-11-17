# Terraform Backend
* Terraform의 State file(.tfstate file)을 저장하는 위치를 정의
* Local Storage가 아닌 Remote Storage에 저장함으로써 상태 데이터를 공유할 수 있고, 이를 통해 다수가 작업할 수 있게 된다.
</br>

## Backend 구성
GitOps 형태로 구성 : Github Action + S3(+DynamoDB) 사용하여 구성
* https://velog.io/@xgro/terraform-infra-provisioning
* Github Action
    * Push - Pull request - Github Action Trigger 발생 (Terraform 실행)
* Terraform
    * terraform init : 상태 파일 체크 (S3 + DynamoDB 확인)
    * terraform plan : 인프라 이상 유무 체크
    * terraform apply -auto-approve : 인프라 구축 적용 및 상태 파일 저장
</br>
</br>


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
    ``$$`
    * Can customize Path

2) Version Check
    ```shell script
    terraform --version
    ```