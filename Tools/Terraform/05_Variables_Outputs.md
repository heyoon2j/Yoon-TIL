# Varialbes and Outputs

## Input Variables
* 다른 구성의 변수를 해당 Module의 Input Parameter로 받을 수 있다.
* 함수 인수와 같다.
* Input variable 사용 방법
    * ```var.<INPUT NAME>```
* Arguments
    * ```default```: 변수의 기본값을 설정
    * ```type```: 변수의 허용되는 유형 지정
        * ```string```: "cloud"
        * ```number```: 3.14523
        * ```bool```: true or false
        * ```list(<TYPE>)```: ["us-west-1a", "us-west-1c"]
        * ```map(<TYPE>)```: {"name" = "abc", "age" = "32"}
        * ```set(<TYPE>)```: ("name")
        * ```object({<ATTR NAME> = <TYPE>, ... })```: list(object({ internal = number, external = number, protocol = string }))
        * ```tuple([<TYPE>, ...])```
    * ```description```: 변수에 대한 설명
    * ```validation```: 유효성 검사 규칙을 정의
        * ```condition```: 유효성 검사의 충족 조건
        * ```error_message```: 조건을 충족시키지 못할 때, 출력되는 에러 메시지
    * ```sensitive```: 변수가 구성에서 사용될때 Terraform UI 출력을 제한
* Example
    ```
    varialbe "subnet_id" {
        type = string
        description = "Subnet ID of VPC"

        validation {
            condition = length(var.subnet_id) > 4 && substr(var.subnet_id, 0, 4) == "sub-"
            error_message = "The subnet_id value must be a valid Subnet ID, starting with \"sub-\"."
        }

        sensitive = true
    }
    ```
</br>

### Variable 사용방법
```
resource "aws_vpc" "vpc-proj" {
    cidr_block = var.cidr_block

    ipv6_cidr_block = var.ipv6_cidr_block

    instance_tenancy = var.instance_tenancy

    enable_dns_hostnames = var.enable_dns_hostnames
    enable_dns_support = var.enable_dns_support

    # enable_classiclink = "false"
    # enable_classiclink_dns_support = "false"

    tags = {
        Name = "vpc-${var.proj_name}"
    }
}
```
* 기본적으로 ```var.```을 이용하여 변수를 사용할 수 있다.
* 문자열(String)에서 사용하기 위해서는 ```"${}"```을 이용하면 사용할 수 있다.
</br>

### Variable 우선순위


</br>
</br>



## Output Variables
* 해당 Module에 대한 Output Parameter를 지정하여, 다른 구성간에 공유할 수 있다.
* 함수의 반환 값과 같다.
* Child Module의 Output Variables에 접근하는 방법
    * ```module.<MODULE NAME>.<OUTPUT NAME>```
* Arguments
    * ```value```: 출력 변수의 값 선언
    * ```description```: 출력 변수에 대한 설명
    * ```sensitive```: Terraform UI 출력을 제한
    * ```depends_on```: 다른 모듈에 정의된 리소스 간의 종속성을 올바르게 결정할 있다.
* Example
    ```
    output "instance_ip_addr" {
        value = aws_instance.server.privaet_ip
        description = "The private IP address of the main server instance"
        sensitive = true

        depends_on = [
            # Security group rule must be created before this IP address could
            # actually be used, otherwise the services will be unreachable
            aws_security_group_rule.local_access,
        ]
    }
    ```
</br>

## Local Values
* 임시 지역 변수
* 구성에서 동일한 값이나 표현식을 여러 번 반복하는 것을 방지하는데 도움이 될 수 있지만 과도하게 사용하면 향후 유지 관리가 힘들 수 있다.
* 지역 변수 사용하는 방법
    * ```local.<NAME>```
* Example
    ```
    locals {
        service_name = "forum"
        owner = "Community Team"
    }

    locals {
        common_tags = {
            Service = local.service_name
            Owner = local.owner
        }
    }

    resource "aws_instance" "example" {
        ...

        tags = local.common_tags
    }
    ```

## 


### Reference
* https://spacelift.io/blog/terraform-tfvars