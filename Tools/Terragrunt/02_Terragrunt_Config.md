# Terragrunt Configuration Blocks
Terragrunt에서 제공하는 Block들을 이용하여 Terragrunt 사용을 위한 구성들을 설정할 수 있다. 제공하고 있는 Block들을 다음과 같다.
1) terraform {}
2) generate {}
3) dependency {}
4) dependencies {}
5) locals {}
6) include {}
7) remote_state {} (여기서는 다루지 않는다. 자세한 내용은 03 Backedn_DRY.md에서)


---
## terraform Block
Terraform과 상호작용하는 방식을 구성하는데 사용되고, 정의되는 내용은 다음과 같다.
1. Module Source 설정
   * Terraform Registry를 사용할때만 구문이 다르다(확인 필요)
2. Working Directory에 저장할 파일 설정
3. CLI Flag 설정
   * CLI 사용 시 Terraform Locking: ```-lock-timeout=20m```
   * 
   * 입력 값을 위한 tfvar 파일 설정
   * etc
4. Hooking 설정
   * 기본적을 명령어 실행 전, 실행 후, 에러 발생에 대한 Hook 사용 가능
   * 특수한 Hook으로 terragrunt-read-config, init-from-module 이 있다.
   * 기본 Working Directory는 terragrunt.hcl가 존재하는 디렉토리이다!
* Pseudo-code 다음과 같다(Example : https://terragrunt.gruntwork.io/docs/reference/config-blocks-and-attributes/#terraform)
    ```
    terraform {
        # Module 위치 설정
        source = "<module_path>"

        # ???
        include_in_copy "<name>" {
        }

        # CLI에 대한 인자 설정
        extra_arguments "<name>" {
            # Terragrunt 명령어에 대한 옵션 추가
            commands = []
            arguments = []
            env_vars = []
            required_var_files = []
            optional_var_files = []
        }

        # Hooking 
        before_hook "<name>" {
            commands = []
            execute = []
            working_dir = ""
            run_on_error = true / false(Defalse)
        }

        after_hook "<name>" {

        } 

        error_hook "<name>" {
            # be executed after before_hook/after_hook
        }
    }
    ```


### Config Template
```
terraform {
  # source = "../modules/networking/vpc"
  source = "git::git@github.com:acme/infrastructure-modules.git//networking/vpc?ref=v0.0.1"


  #######################################################
  # CLI Flag
  extra_arguments "retry_lock" {
    commands  = get_terraform_commands_that_need_locking()
    arguments = ["-lock-timeout=20m"]
  }

  extra_arguments "custom_vars" {
    commands = [
      "apply",
      "plan",
      "import",
      "push",
      "refresh"
    ]

    required_var_files = [
      "${get_parent_terragrunt_dir()}/terraform.tfvars"
    ]

    optional_var_files = [
      "${get_parent_terragrunt_dir()}/${get_env("TF_VAR_env", "dev")}.tfvars",
      "${get_parent_terragrunt_dir()}/${get_env("TF_VAR_region", "us-east-1")}.tfvars",
      "${get_terragrunt_dir()}/${get_env("TF_VAR_env", "dev")}.tfvars",
      "${get_terragrunt_dir()}/${get_env("TF_VAR_region", "us-east-1")}.tfvars"
    ]
  }

  #######################################################
  # Hooking
  before_hook "before_hook_1" {
    commands     = ["apply", "plan"]
    execute      = ["echo", "########## Execute Terragrunt command for changing infra (Before Hook) ##########"]
    #run_on_error = true
  }

  after_hook "after_hook_1" {
    commands     = ["apply", "plan"]
    execute      = ["echo", "########## End Terragrunt command for changing infra (After Hook) ##########"]
    run_on_error = true
  }

  # after any error, with the ".*" expression.
  error_hook "error_hook_1" {
    commands  = ["apply", "plan"]
    execute   = ["echo", "########## Error Hook executed ##########"]
    on_errors = [
      ".*",
    ]
  }

  # A special after hook to always run after the init-from-module step of the Terragrunt pipeline. In this case, we will
  # copy the "foo.tf" file located by the parent terragrunt.hcl file to the current working directory.
  after_hook "init_from_module" {
    commands = ["init-from-module"]
    execute  = ["cp", "${get_parent_terragrunt_dir()}/foo.tf", "."]
  }

  # A special after_hook. Use this hook if you wish to run commands immediately after terragrunt finishes loading its
  # configurations. If "terragrunt-read-config" is defined as a before_hook, it will be ignored as this config would
  # not be loaded before the action is done.
  after_hook "terragrunt-read-config" {
    commands = ["terragrunt-read-config"]
    execute  = ["bash", "script/get_aws_credentials.sh"]
  }
}
```


---
## generate Block
Terragrunt working directory에 파일을 생성하는데 사용된다. terraform block에서 설정할 수 없는 구성들을 설정한다. generate block으로 생성하는 내용은 다음과 같다.
1. Provider
2. Backend
* Pseudo-code 다음과 같다(Example : https://terragrunt.gruntwork.io/docs/reference/config-blocks-and-attributes/#terraform)
  ```
  generate "<name>" {
    path      = "<relative_path>" # 파일 생성 경로
    if_exists = "overwrite" # 파일 존재 시 동작 방식(overwrite / overwrite_terragrunt / skip / error)
    contents = <<EOF
  # contents 입력
  EOF
  }
  ```

### Config Template
```
generate "provider" {
  path      = "provider.tf"
  if_exists = "overwrite"
  contents = <<EOF
provider "aws" {
  region              = "us-east-1"
  version             = "= 2.3.1"
  allowed_account_ids = ["1234567890"]
}
EOF
}
```
</br>
</br>



---
## dependency Block
모듈 종속성을 구성하는 데 사용되며, 참조하고 있는 모듈의 출력 값을 가지고 올 수 있다. depencies block은 ```run-all``` 명령어 실행 시, 어떤 순서대로 작업을 진행할지 지정하기 위한 구성 블럭이다.
* Pseudo-code 다음과 같다(Example : https://terragrunt.gruntwork.io/docs/reference/config-blocks-and-attributes/#terraform)
  ```
  dependency "<name>" {
    # 출력 값을 가지고 와야하는 모듈 위치
    config_path = "<relative_path>"

    # mock_* 은 쓰지 않는 것이 좋다고 한다.
  }

  inputs = {
    vpc_id = dependency.<name>.outputs.output_var
  }
  ```

### Config Template
```
dependency "vpc" {
  config_path = "../vpc"
}

dependency "rds" {
  config_path = "../rds"
}

inputs = {
  vpc_id = dependency.vpc.outputs.vpc_id
  db_url = dependency.rds.outputs.db_url
}
```
</br>
</br>


---
## dependencies Block
```run-all``` 명령어 실행 시, 어떤 순서대로 작업을 진행할지 지정하기 위한 구성 블럭이다.
* Pseudo-code 다음과 같다(Example : https://terragrunt.gruntwork.io/docs/reference/config-blocks-and-attributes/#terraform)
  ```
  dependencies {
    # 의존성이 필요한 모듈 위치
    paths = ["<module_relative_path1>", "<module_relative_path2>", ...]
  }
  ```

### Config Template
```
dependencies {
  paths = ["../vpc", "../rds"]
}
```
</br>
</br>


---
## locals Block
해당 파일에서만 사용가능한 변수를 선언하는 블럭이다.
</br>

### Config Template
```
locals {
  aws_region = "us-east-1"
}

inputs = {
  region = local.aws_region
  name   = "${local.aws_region}-bucket"
}
```
</br>
</br>


---
## include Block
Terragrunt 구성 파일의 상속을 사용하기 위해 사용. Included config는 현재 구성과 통합된다.
* 기본적으로 얕은 병합을 진행하며, 깊은 병합 / 병합 안함도 가능하다.
* ```expose = true```일 시 변수로 사용할 수 있는 설정으로는 inputs, dependency 등이 있다.
* dependency의 경우 깊은 병합을 진행하거나, 각 구성마다 따로 설정한다(dependency tree를 생각하며)
* locals는 병합 불가능
* Pseudo-code 다음과 같다(Example : https://terragrunt.gruntwork.io/docs/reference/config-blocks-and-attributes/#terraform)
  ```
  include "<name>" {
    # 설정을 포함시킬 구성 위치
    path   = "<path>"
    expose = true     # 변수로 사용할지 여부. true / false
    merge_strategy =  # no_merge (do not merge the included config) / shallow (do a shallow merge - default)/ deep (do a deep merge of the included config)
  }
  ```

### Config Template
```
# ├── terragrunt.hcl
# ├── region.hcl
# └── child
#     └── terragrunt.hcl
include "remote_state" {
  path   = find_in_parent_folders()
  expose = true
}

include "region" {
  path           = find_in_parent_folders("region.hcl")
  expose         = true
  merge_strategy = "no_merge"
}

inputs = {
  remote_state_config = include.remote_state.remote_state
  region              = include.region.region
}
```
</br>
</br>
