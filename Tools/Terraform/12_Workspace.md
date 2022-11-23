# Workspace
개발, 스테이징, 운영에 따라 구성 환경이 다르기 때문에 각기 다른 구축환경이 필요하다. 즉 구성 상태 파일을 다르게 관리해야 한다. 이를 위해 사용되는 것이 Workspace 이다.
</br>

## Workspace 사용법 정리
구성 상태 파일의 경우, Backend를 통해 위치를 지정하지 않으면 모든 Workspace의 상태 파일이 동일한 디렉토리에 저장된다. 그렇기 때문에 상태 파일 격리를 위해서는 Backend와 같이 사용해야 한다.
* Workspace CLI 사용법
    ```bash
    # 새 워크스페이스 생성
    $ terraform workspace new test0

    # 현재 사용중인 워크스페이스 출력
    $ terraform workspace show

    # 워크스페이스 리스트 출력
    $ terraform workspace list

    # 현재 사용중인 워크스페이스 교체
    $ terraform workspace select <workspace_name>

    # 해당 워크스페이스 삭제
    $ terraform workspace delete test0
    ```

* .tf 파일에서 Workspace 활용 방법
    ```
    resource "aws_instance" "example" {
    count = "${terraform.workspace == "default" ? 5 : 1}"

    # ... other arguments
    }


    resource "aws_instance" "example" {
    tags = {
        Name = "web - ${terraform.workspace}"
    }

    # ... other arguments
    }


    ```
