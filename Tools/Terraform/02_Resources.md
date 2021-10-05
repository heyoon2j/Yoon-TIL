# Resources


## Resource Syntax
* Syntax
  * ```resource "<RESOURCE TYPE>" "<LOCAL NAME>"```
* Resource 사용 방법
  * ```<RESOURCE TYPE>.<NAME>.<ATTRIBUTE>```
* 일반적으로 리소스 종속성은 자동으로 처리된다. 그렇기 때문에 수동으로 지정할 필요가 없지만 일부 종속성은 구성에서 명시적으로 지정해야 한다(```depends_on```)
* Example
  ```shell script
  # HCL
  resource "aws_instance" "web" {
    ami           = "ami-a1b2c3d4"
    instance_type = "t2.micro"
  }

  ```
</br>

## Resource Type
1. Providers
2. Resource Arguments
3. Documentation for Resource Types
</br>

## Meta-Arguments
## depends_on
* 자동으로 유추할 수 없는 숨겨진 리소스 또는 모듈 종속성을 처리할 때 사용
* __리소스 또는 모듈이 다른 리소스의 동작에 의존하지만 인수에서 해당 리소스의 데이터에 액세스하지 않는 경우에만 사용! 거의 쓰이지 않는다.__
* Example 
  ```
  resource "aws_iam_role" "example" {
    name = "example"

    # assume_role_policy is omitted for brevity in this example. See the
    # documentation for aws_iam_role for a complete example.
    assume_role_policy = "..."
  }

  resource "aws_iam_instance_profile" "example" {
    # Because this expression refers to the role, Terraform can infer
    # automatically that the role must be created first.
    role = aws_iam_role.example.name
  }

  resource "aws_iam_role_policy" "example" {
    name   = "example"
    role   = aws_iam_role.example.name
    policy = jsonencode({
      "Statement" = [{
        # This policy allows software running on the EC2 instance to
        # access the S3 API.
        "Action" = "s3:*",
        "Effect" = "Allow",
      }],
    })
  }

  resource "aws_instance" "example" {
    ami           = "ami-a1b2c3d4"
    instance_type = "t2.micro"

    # Terraform can infer from this that the instance profile must
    # be created before the EC2 instance.
    iam_instance_profile = aws_iam_instance_profile.example

    # However, if software running in this EC2 instance needs access
    # to the S3 API in order to boot properly, there is also a "hidden"
    # dependency on the aws_iam_role_policy that Terraform cannot
    # automatically infer, so it must be declared explicitly:
    depends_on = [
      aws_iam_role_policy.example,
    ]
  }
  ```
  * EC2 인스턴스에서 실행되는 프로그램이 제대로 부팅하기 위해 S3 API에 액세스해야하는 경우, 해당 예제에서는 "aws_iam_role_policy"가 먼저 적용될지 확실할 수 없다. 그렇기 때문에 종속성을 명시적으로 함으로써 먼저 생성되도록 해줘야 한다.
</br>

## count
* Resource Block을 같은 구성으로 여러 개 만들고 싶을 때, 각 각 별도의 Block을 작성하지 않고 관리하고 싶을 때 사용.
* ```count```는 index를 이용할 때, ```for_each```는 set을 이용할 때 사용
* Count Index 사용방법
  * ```count.index```
* Example
  ```
  variable "subnet_ids" {
    type = list(string)
  }

  resource "aws_instance" "server" {
    # Create one instance for each subnet
    count = length(var.subnet_ids)

    ami           = "ami-a1b2c3d4"
    instance_type = "t2.micro"
    subnet_id     = var.subnet_ids[count.index]

    tags = {
      Name = "Server ${count.index}"
    }
  }
  ```
</br>

## for_each
* Resource Block을 같은 구성으로 여러 개 만들고 싶을 때, 각 각 별도의 Block을 작성하지 않고 관리하고 싶을 때 사용.
* 여러 개의 리소스의 경우, ```for_each```와 ```each```를 같이 사용.
  1) Map
    ```
    resource "azurerm_resource_group" "rg" {
      for_each = {
        a_group = "eastus"
        another_group = "westus2"
      }
      name     = each.key
      location = each.value
    }
    ```
  2) Set
    ```
    locals {
      subnet_ids = toset([
        "subnet-abcdef",
        "subnet-012345",
      ])
    }

    resource "aws_instance" "server" {
      for_each = local.subnet_ids

      ami           = "ami-a1b2c3d4"
      instance_type = "t2.micro"
      subnet_id     = each.key # note: each.key and each.value are the same for a set

      tags = {
        Name = "Server ${each.key}"
      }
    }
    ```
* Example
  ```
  # my_buckets.tf
  module "bucket" {
   for_each = toset(["assets", "media"])
    source   = "./publish_bucket"
    name     = "${each.key}_bucket"
  }

  # publish_bucket/bucket-and-cloudfront.tf
  variable "name" {} # this is the input parameter of the module

  resource "aws_s3_bucket" "example" {
    # Because var.name includes each.key in the calling
    # module block, its value will be different for
    # each instance of this module.
    bucket = var.name

    # ...
  }

  resource "aws_iam_user" "deploy_user" {
    # ...
  }
  ```
  * assets, media Module의 Resource는 동일하므로 위의 예제처럼 ```for_each``` 구문을 이용
* 1개 리소스만 사용하는 경우, ```<TYPE>.<NAME>[<key>]``` 사용
  ```
  # Variable
  netVpc = {
      name = "vpc-y2net-an2"
      cidr = "10.20.0.0/24"
  } 

  # Resource
  tags = {
      Name : var.netVpc["name"]
  }  
  ```
</br>


## lifecycle
* 해당 Resource의 라이프사이클을 설정할 수 있다.
* 종속성에 영향을 주기 때문에 잘 설정해야 한다.
* Arguments
  1) ```create_before_destroy```: Bool Type, 원래는 기존 객체를 파괴한 다음 새로 구성된 인수로 새 대체 객체를 생성한다. True로 설정하면 새로운 객체가 생성된 후 기존 객체가 파괴된다.
  2) ```prevent_destroy```: Bool Type, True로 설정하면 객체 파괴 계획을 오류 발생과 함께 거부한다.DB와 같이 재생산에 비용이 많이 드는 경우.
  3) ```ignore_changes```: 기본적으로 변경에 대해 감지하고 차이를 업데이트한다. 하지만 해당 인자를 사용하게 되면, 해당 리스트에 있는 값들은 업데이트될 때 무시된다.
* Example
  ```
  resource "azurerm_resource_group" "example" {
    # ...

    lifecycle {
      create_before_destroy = true
    }
  }
  ```
</br>


