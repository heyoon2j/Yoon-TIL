# Modules
* 함께 사용되어지는 여러 Resource들의 컨테이너이다.
</br>

## Calling a Child Module
* Child Module을 호출할 때, Resource들의 Input 값을 설정할 수 있으며, Calling 방법은 아래와 같다.
    ```
    module "servers" {
      source = "./app-cluster"

      servers = 5
    }
    ```
</br>

## Accessing Module Output Values
* 다른 모듈의 Ouput 값을 액세스하는 방법은 아래와 같다.
    ```
    resource "aws_elb" "example" {
      # ...

      instances = module.servers.instance_ids
    }
    ```
    * ```module.<MODULE NAME>.<OUTPUT NAME>```
</br>

## Version
* Module Registry에 저장되어 있는 모듈을 사용해야하는데, 특정 허용 버전을 사용해야 되는 경우.
    ```
    module "consul" {
      source  = "hashicorp/consul/aws"
      version = "0.0.5"

      servers = 3
    }
    ```
</br>

## Meta-arguments
* ```count```
* ```for_each```
* ```providers```
* ```depends_on```
</br>

## Module Sources
* Chile Module의 Code 위치

### __1. Local Paths__
* 로컬에 저장되어 있으므로 바로 사용할 수 있다. 즉, 설치되어 있지 않다.
* Example
    ```
    module "consul" {
      source = "./consul"
    }
    ```
</br>

### __2. Terraform Registry__
* Public Terraform Registry에서 참조
* 참조 주소는 ```<NAMESPACE>/<NAME>/<PROVIDER>``` 형식 사용
* Example
    ```
    module "consul" {
      source = "hashicorp/consul/aws"
      version = "0.1.0"
    }
    ```
</br>

### __3. GitHub__
* GitHub URL 사용
* Example
    ```
    module "consul" {
      source = "github.com/hashicorp/example"
    }
    ```
</br>

### __4. Bitbucket__
</br>

### __5. HTTP URLs__
</br>

### __6. S3 buckets__
</br>

### __7. GCS buckets__
