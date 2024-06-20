# CLI Configuration
Terraform CLI 구성 파일인 ```~/.terraformrc```(Linux) 및 ```terraform.rc / terraform.rc.txt```(Windows)에 해당 설정들을 넣어줘야 한다.

기본 설정 파일이 아닌 사용자용 설정 파일을 만드려면 이름 규칙은 다음과 같이 지정해야 하며(```*.tfrc```), 파일 위치는 ```TF_CLI_CONFIG_FILE``` 환경 변수를 통해서 설정할 수 있다.


## CLI Config Options
* credentials- Terraform Cloud 또는 Terraform Enterprise와 함께 사용할 자격 증명을 구성합니다. 자세한 내용은 아래 자격 증명을 참조하세요.
    ```
    ```
* credentials_helper- Terraform Cloud 또는 Terraform Enterprise에 대한 자격 증명 저장 및 검색을 위한 외부 도우미 프로그램을 구성합니다. 자세한 내용은 아래 자격 증명 도우미를 참조하세요 .
    ```
    ```
* disable_checkpoint— 로 설정하면 HashiCorp 제공 네트워크 서비스에 연결해야 하는 업그레이드 및 보안 게시판 확인이true 비활성화됩니다 .
    ```
    ```
* disable_checkpoint_signature— 로 설정하면 true위에 설명된 업그레이드 및 보안 공지 확인이 허용되지만 경고 메시지 중복을 제거하는 데 사용되는 익명 ID의 사용이 비활성화됩니다.
    ```
    ```
* plugin_cache_dir : Plugin Caching을 활성화하고, Plugin cache directory 지정
    ```
    plugin_cache_dir = "$HOME/.terraform.d/plugin-cache"
    ```
* provider_installation : terraform init 시, Provider Plugin을 설치하는데, 설치 방법을 정의
    ```
    provider_installation {
        filesystem_mirror {
            path    = "<absolute_path>/providers"
            include = ["registry.terraform.io/hashicorp/*"]
        }
        direct {
            exclude = ["github.io/*"]
        }
    }
    ```
