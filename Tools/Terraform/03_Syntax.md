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









