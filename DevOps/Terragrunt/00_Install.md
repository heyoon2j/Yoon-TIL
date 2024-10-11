# Install
1) Download Terragrunt
    * https://terragrunt.gruntwork.io/docs/getting-started/install/
    * 해당 OS에 맞는 버전 설치
    ```shell script
    $ wget https://github.com/gruntwork-io/terragrunt/releases/download/v0.53.3/terragrunt_linux_amd64
    $ mv terragrunt_linux_amd64 terragrunt
    $ chmod u+x terragrunt
    $ mv terragrunt /usr/local/bin/terragrunt
    $ vi ~/.profile
       export PATH="$PATH:/usr/local/bin/terragrunt"
    ```
    * Can customize Path

2) Version Check
    ```shell script
    terragrunt --version
    ```
