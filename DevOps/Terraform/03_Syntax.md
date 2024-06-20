# Syntax
## JSON
* HCL
    ```
    variable "example" {
      default = "hello"
    }
    
    resource "aws_instance" "example" {
      instance_type = "t2.micro"
      ami           = "ami-abc123"
    }
    ```
* JSON
    ```
    {
      "variable": {
        "example": {
          "default": "hello"
        }
      }
    }
    
    {
      "resource": {
        "aws_instance": {
          "example": {
            "instance_type": "t2.micro",
            "ami": "ami-abc123"
          }
        }
      }
    }
    ```  
https://www.terraform.io/docs/configuration/syntax.html

</br>
</br>


---
## Types and Values
Terraform language에서 사용되는 데이터 타입은 다음과 같다.
* ```string```: "cloud"
* ```number```: 3.14523 or 50
* ```bool```: true or false
* ```list(<TYPE>)```: ["us-west-1a", "us-west-1c"]
* ```map(<TYPE>)```: {"name" = "abc", "age" = "32"}
* ```object({<ATTR NAME> = <TYPE>, ... })```: list(object({ internal = number, external = number, protocol = string }))
* ```tuple([<TYPE>, ...])```
* ```null```: null

</br>

---
## Strings and Templates
documents the syntaxes for string literals, including interpolation sequences and template directives.

---
## Expression
1. 조건문
    ```
    condition ? true_val : false_val

    ex> var.example ? tostring(12) : "hello"
    ```
2. 반복문
    ```
    [ for <output> in <input> : <result> if <condition>]

    [for s in var.list : upper(s)]

    [ for i, v in var.list: " $ { i } is $ { v } " ]

    [for s in var.list : upper(s) if s != ""]
    ```
    * in에는 list, set, tuple, map, object가 될 수 있습니다.
    * 출력 값이 조건에 맞다면 결과 값에 추가


3. 반복문 요약
    ```
    [for o in var.list : o.id]

    var.list[*].id
    ```
    ```
    variable "website_setting" {
        type = object({
            index_document = string
            error_document = string
        })
        default = null
    }

    resource "aws_s3_bucket" "example" {
    # ...

        dynamic "website" {
            for_each = var.website_setting[*]
            content {
                index_document = website.value.index_document
                error_document = website.value.error_document
            }
        }
    }

    ```




References to Values documents how to refer to named values like variables and resource attributes.

Operators documents the arithmetic, comparison, and logical operators.

Function Calls documents the syntax for calling Terraform's built-in functions.

Conditional Expressions documents the <CONDITION> ? <TRUE VAL> : <FALSE VAL> expression, which chooses between two values based on a bool condition.

For Expressions documents expressions like [for s in var.list : upper(s)], which can transform a complex type value into another complex type value.

Splat Expressions documents expressions like var.list[*].id, which can extract simpler collections from more complicated expressions.

Dynamic Blocks documents a way to create multiple repeatable nested blocks within a resource or other construct.

Type Constraints documents the syntax for referring to a type, rather than a value of that type. Input variables expect this syntax in their type argument.

Version Constraints documents the syntax of special strings that define a set of allowed software versions. Terraform uses version constraints in several places.











