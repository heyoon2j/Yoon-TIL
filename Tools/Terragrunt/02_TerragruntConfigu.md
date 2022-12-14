# Terrgrunt Config File
Terragrunt 구성은 ```terragrunt.hcl``` 파일에 정의된다.
* Terragrunt 구성파일 위치 검색 우선순위
    1. ```--terragrunt-config``` 옵션 지정
    2. ```TERRAGRUNT_CONFIG``` 환경 변수 값
    3. 현재 작업 디렉토리에 있는 ```terragrunt.hcl``` 파일
    4. 현재 작업 디렉토리에 있는 ```terragrunt.hcl.json``` 파일
    5. 아무것도 없는 경우 오류 발생
</br>


## Syntax
* Terragrunt 구성 구문 분석 우선순위
    1. ```include {}```
    2. ```locals {}``` 
    3. ```iam_role``` 정의
    4. ```dependencies {}```
    5. ```dependency {}```
    6. 그 외 모든 것
    7. ```include {}```에서 참조하는 구성
    8. ```include {}```에서 참조하는 구성과 현재 구성 간의 병합 작업

* 트리에서 아래 순서대로 구성에 대한 모듈 종속성 그래프를 작성한다.
    1. ```include``` 트리의 모든 구성 블록
    2. ```locals``` 트리의 모든 구성 블록
    3. ```dependency``` 트리의 모든 구성 블록
    4. ```terrafrom``` 트리의 모든 구성 블록
    5. ```dependencies``` 트리의 모든 구성 블록

</br>
</br>


## Terragrunt Execution 과정
* 동작을 위한 기본 Step은 다음과 같다.
1) Terragrunt Config 파일에 ```source```가 선언되어 있고 ```terragrunt <command>```를 실행하게 되면, 코드를 스크래치 디렉터리에 다운로드하고(Default: .terragrunt-cache file) 현재 작업 디렉터리의 코드를 동일한 스크래치 디렉터리에 복사한 다음 ```terraform <command>```를 실행한다.


2) HCL을 이용하여 .tf 파일에 Infra Resources을 정의한다.
3) __terraform init__
    * provider, module, state 파일을 읽어 초기화 작업 진행
    * 초기화에 대한 라이브러리 등이 .terraform Directory에 저장
    * 인프라 관련 동시성처리를 위한 .terraform.lock.hcl 파일 생성
    * 기존에 .tfstate 파일이 정의되어 있다면, 현재 상태와 Backend Storage와 Sync를 맞춘다.
4) __terraform plan__
    * 작업한 코드에 대한 결과를 예측하여 작업할 내용 출력
5) __terraform apply__
    * 실제 환경에 인프라 구성 작업 진행
    * 작업 결과에 대하여 .tftate file에 저장(Local/Backend Storage 모두 저장)
</br>
</br>






## Configuration Block
### terraform Block
Terraform과 상호작용하는 방식을 구성하는데 사용되고, 정의되는 내용은 다음과 같다.
1. 
* Template은 다음과 같다.
    ```
    terraform {
        # Module 위치 설정
        source = "<module_path>"

        # ???
        include_in_copy "" {
        }

        # CLI에 대한 인자 설정
        extra_arguments "" {
            # Terragrunt 명령어에 대한 옵션 추가
            commands = []
            arguments = []
            env_vars = []
            required_var_files = []
            optional_var_files = []
        }

        # Hooking 
        before_hook "" {
            commands = []
            execute = []
            working_dir = ""
            run_on_error = true or none
        }

        after_hook "" {

        } 

        error_hook "" {
            # be executed after before_hook/after_hook
        }
    }
    ```

* Example : https://terragrunt.gruntwork.io/docs/reference/config-blocks-and-attributes/#terraform



---
### generate




---
### include



---
### locals


---
### dependency


---
### dependencies

