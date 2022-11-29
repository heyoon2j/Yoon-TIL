# Terraform Code DRY
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
* ``````
* ``````







