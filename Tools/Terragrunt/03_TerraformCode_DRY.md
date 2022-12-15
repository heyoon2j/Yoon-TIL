# Terraform Code DRY
* Resource를 구성하기 위해서는 Provider, Module, Input/Output 등의 구성에 대한 정의가 필요하다.
* 각 환경, 각 프로젝트 서비스 마다 구성에 대한 설정이 필요한데, Terraform을 통해서 구성은 가능하나 복잡한 디렉터리 구조 안에서 여러 개의 구성 파일을 관리하는 기능이 없어, 변경 시 모든 구성 파일의 수정이 필요하다. 이를 Terragrunt를 사용하여 해결한다.
1) Provider
2) Backend (여기서는 다루지 않는다. 자세한 내용은 03 Backedn_DRY.md에서)
2) Module
3) Input/Output


## Provider
### Terraform
```

```

### Terragrunt
```

```


## Root HCL File

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



## Child HCL File
```
include "root" {
  path = find_in_parent_folders()
}

terraform {
  source = "github.com/<org>/modules.git//app?ref=v0.1.0"
}

dependency "vpc" {
  config_path = "../vpc"
}

dependency "mysql" {
  config_path = "../mysql"
}

inputs = {
  env            = "qa"
  basename       = "example-app"
  vpc_id         = dependency.vpc.outputs.vpc_id
  subnet_ids     = dependency.vpc.outputs.subnet_ids
  mysql_endpoint = dependency.mysql.outputs.endpoint
}
```



## Provider DRY
* Terraform 구조
    ```
    # stage/frontend-app/main.tf
    variable "assume_role_arn" {
        description = "Role to assume for AWS API calls"
    }

    provider "aws" {
        assume_role {
            role_arn = var.assume_role_arn
        }
    }
    ```
    * var 변수를 사용하여 리소스에 맞게 같은 변수 명을 이용하여 설정할 수 있다. 입렵 값을 다르게 넣으면 되기 때문이다.  


```

```



```

```



</br>


## Input
### Terraform 구조
```
# account.tfvars
account_id     = "123456789012"
account_bucket = "my-terraform-bucket"    
```
```
# region.tfvars
aws_region = "us-east-2"
foo        = "bar"    
```
```
$ terraform apply \
-var-file=../../common.tfvars \
-var-file=../region.tfvars
```
* ```-var-file``` 인수를 활용하여 variable 파일 위치를 선언해줘야 하기 때문에, CLI 인수를 항상 기억해야 한다.
</br>

### Terragrunt 구조
```
# Root HCL File
generate "provider" {
    path = "provider.tf"
    if_exists = "overwrite_terragrunt"
    contents = <<EOF
provider "aws" {
    assume_role {
        role_arn = "arn:aws:iam::0123456789:role/terragrunt"
    }
}
EOF
}
```
```
# Child HTCL File
include "root" {
    path = find_in_parent_folders()
}
```
* ```extra_arguments {}```을 통해 Terraform CLI에 대한 동작 방식을 지정할 수 있다.
* ```get_terraform_commands_that_need_vars()```는 ```-var-file``` 또는 ```-var``` 옵션을 수락하는 모든 명령 목록을 가지고 올 수 있다.
</br>
</br>


## ENV 




</br>


## Input
### Terraform 구조
```
 
```
* 
</br>

# Terragrunt 구조
```

```
* 
</br>
</br>

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
            run_on_error = true / false(Defalse)
        }

        after_hook "" {

        } 

        error_hook "" {
            # be executed after before_hook/after_hook
        }
    }
    ```


### Config 
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



---
## dependency Block


---
## dependencies Block


---
## locals Block

---
## include Block

