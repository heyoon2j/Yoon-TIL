# Install
1) Download Terraform
    * https://www.terraform.io/downloads.html
    * 해당 OS에 맞는 버전 설치
    ```shell script
    $ wget https://releases.hashicorp.com/terraform/0.13.3/terraform_0.13.3_linux_amd64.zip
    $ unzip terraform_0.13.3_linux_amd64.zip
    $ mv terraform /usr/bin/terraform && sudo chmod +x
    $ vi ~/.profile
       export PATH="$PATH:/usr/bin/terraform"
    ```
    * Can customize Path

2) Version Check
    ```shell script
    terraform --version
    ```




