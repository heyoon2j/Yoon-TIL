# Provider
* Terraform은 "Provider" 플러그인을 사용하여 원격 시스템과 상호작용을 한다.
* 모든 Resource Type과 Data source들은 Provider에 의해 구현된다(implements)
* Provider는 루트 Terraform 모듈에서만 정의할 수 있습니다.

## Requiring Providers
* Provider requirements가 선언되며, ```required_providers``` 블럭에 선언한다.
* 해당 블럭은 ```terrafrom``` 블럭의 top-level 안에 선언되야 한다.
    ```
    terraform {
      required_providers {
        <local_name> = {
          source = "<path>"
          version = "<version>"
        }
      }
    }

    ex>
    terraform {
      required_providers {
        mycloud = {
          source  = "mycorp/mycloud"
          version = "~> 1.0"
          configuration_aliases = [ mycloud.alter ]
        }
      }
    }
    ```
    * ```local_name```: Provider에서 사용될 이름(unique). ex> mycloud
    * ```source```: 전역 식별자이며, Terraform이 다운로드할 수 있는 기본 위치로 지정한다.
        * ```[<HOSTNAME/>]<NAMESPACE>/<TYPE>``` 순으로 구성
        * HOSTNAME: Provider를 배포하는 Terraform 레지스트리의 호스트 이름.
         생략가능하며, 기본 Public Terraform Registry는 ```registry.terraform.io```이다.
        * NAMESPACE: 지정된 레지스트리 내의 네임 스페이스.
        * TYPE: Provider가 관리하는 Platform 또는 System의 짧은 이름. 이름은 고유해야 한다.
        * 기본 레지스트리인 경우, ```NAMESPACE/TYPE```은 https://registry.terraform.io/ 확인 가능
    * ```version```: 모듈이 호환되는 사용 가능한 Provider 버전.
        * ```=```: 정확한 버전 번호를 하나만 허용
        * ```!=```: 정확한 버전 번호를 제외
        * ```>```, ```>=```, ```<```, ```<=```: 지정된 버전 번호와 비교
        * ```~>```: 오직 가장 오른쪽 버전 구성 요소만 증가할 수 있다. 예로 ```~>1.0.4```의 경우, ```1.0.5```, ```1.0.10```는 허용하지만 ```1.1.0```는 허용하지 않는다.
    * ```configuration_aliases```: Alias를 사용하는 경우, 사용할 Alias를 명시적으로 선언해 줘야한다.
        
</br>        
</br>

## Provider Configuration
* ```required_providers```에서 입력한 Provider에 대해 필요한 설정을 작성한다.
* 동일한 Provider에서 설정을 나누려면 ```alias```를 사용하면 된다.
  ```
  terraform {
    required_providers {
      mycloud = {
        source  = "mycorp/mycloud"
        version = "~> 1.0"
        configuration_aliases = [ mycloud.test1, mycloud.test2 ]
      }
    }
  }

  # Default Provider
  provider "mycloud" {
    region = "ap-northeast-2"
  }

  # Alias
  provider "mycloud" {
    alias = "test1"
    region = "ap-northeast-2"
  }

  # Alias
  provider "mycloud" {
    alias = "test2"
    region = "us-west-2"
  } 

  module "mycloud_vpc" {
    source = "./mycloud_vpc"
    providers = {
      aws = mycloud.test1
    }
  }
  ```
  * Provdier 선택하는 방법은 ```<provider name>.<alias>```를 이용하여 선택
  * Provder가 하나인 경우, Module은 암시적으로 상속을 받는다. 여러 개인 경우, 명시적 상속을 선언해야 한다.

</br>

### Plugin Caching





---
## Installation Provider (Airgab)
기본적인 Public Registry(registry.terraform.io)에 있는 Provider를 설치하는 것이 아닌 폐쇄망에서 설치할 시에 해당 방법을 사용한다.

```
provider_installation {
  filesystem_mirror {
    path    = "/usr/share/terraform/providers"
    include = ["example.com/test/*"]
  }
  direct {
    exclude = ["example.com/test/vpc"]
  }
}

```
* 설치하는 주소가 "example.com/\*/\*"인 경우, mirror site 주소는 "/usr/share/terraform/providers" 이다.
* exclude와 같이 사용된다면, 우선순위는 exclude > include이기 때문에 "example.com/test/vpc"를 제외한 "example.com/test/*"에 접근할 수 있다.



