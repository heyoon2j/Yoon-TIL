# CDK for Terraform
* CDK(Cloud Development Kit)를 이용하여 Terraform을 Python, Typescript, Java 등으로 개발할 수 있다.
* 공식
    * https://www.hashicorp.com/blog/cdk-for-terraform-enabling-python-and-typescript-support
    * https://github.com/hashicorp/terraform-cdk/blob/main/docs/getting-started/python.md
    * https://learn.hashicorp.com/tutorials/terraform/cdktf-build-python?in=terraform/cdktf
* Github : https://github.com/hashicorp/terraform-cdk/tree/main/examples


## Step 0. Download CDK for Terraform
* CDK를 사용하기 위해서는 필요한 두가지.
1) cdktf-cli : Terraform CDK를 사용하기 위한 CLI
    * 명령어 : ```npm install -g cdktf-cli```
2) cdktf : Terraform resource들이 정의되어 있는 라이브러리
    * ```cdktf init``` 시에 설치된다.

## Step 1. Provider Definition & Project Initialization
1) 프로젝트 초기화
    * 명령어 : ```cdktf init --template="python" --local```
    * ```--local``` 옵션을 사용하면, 상태 관리에 Terraform Cloud를 사용하지 않을 수 있다.

2) 프로젝트에 필요한 Provider와 Module 등 CDK 구성을 정의한다. 
    * ```cdktf.json``` 파일을 수정한다.
    * 사양은 다음 페이지에서 확인할 수 있다.
        * https://github.com/hashicorp/terraform-cdk/blob/main/docs/working-with-cdk-for-terraform/cdktf-json.md
    * Example
        ```json
        {
          "language": "python",
          "app": "pipenv run python main.py",
          "terraformProviders": ["hashicorp/aws@~>3.0"],
          "terraformModules": ["terraform-aws-modules/vpc/aws@~>3.0"],
          "codeMakerOutput": "imports"
        }
        ```
        * Terraform Provider Schema
            * ```source@version``` 구조를 가진다.

3) 정의한 구성들을 생성한다.
    * 명령어 : ```cdktf get```


## Step 2. Resource Definition using develop language
* 자신이 사용하는 개발 언어를 이용하여 리소스 코드를 개발한다.


## Step 3. Synthesize Resource Code to Terraform Configuration
* 개발한 코드를 Terraform 구성에 합성시킨다.
    * 명령어 : ```cdktf synth```
    * 해당 명령어를 통해서 Terraform JSON 파일이 ```cdktf.out``` 디렉터리에 생성된다.
    * ```--json``` 옵션을 이용하면 Terminal 창에 출력할 수 있다.


## Step 4. Deploy & Destroy
* Deploy와 Destroy 경우, terrafrom 명령어를 이용한다.
1) ```cdktf.out``` 디텍터리로 이동 후, 안에 변경된 사항을 Terraform workflow에 적용시키기 위해서는 다음 명령어를 사용한다.
    * 명령어 : 
        ```
        cd cdktf.out
        terraform init
        terraform plan
        terraform apply
        ```
2) 적용시켰던 모든 사항들을 삭제하려면 루트 디렉터리에서 다음 명령어를 사용한다.
    * 명령어 : ```terraform destroy```


## 사용법
* Module 사용법
    * https://github.com/hashicorp/terraform-cdk/blob/main/docs/working-with-cdk-for-terraform/using-providers-and-modules.md
    * ```TerraformHclModule``` 사용
    ```
    const provider = new TestProvider(stack, 'provider', {
        accessKey: 'key',
        alias: 'provider1'
    });

    const module = new TerraformHclModule(stack, 'test', {
        source: './foo',
        variables: {
          param1: 'value1'
        },
        providers: [provider]
    });

    new TestResource(stack, 'resource', {
        name: module.getString('name')
    });
    
    ```


