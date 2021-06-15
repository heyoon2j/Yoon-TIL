# Provider
* Terraform은 "Provider" 플러그인을 사용하여 원격 시스템과 상호작용을 한다.
* 

## Requiring Providers
* ```required_providers``` 블럭에 선언한다.
* 해당 블럭은 ```terrafrom``` 블럭의 top-level 안에 선언되야 한다.
    ```
    terraform {
      required_providers {
        mycloud = {
          source  = "mycorp/mycloud"
          version = "~> 1.0"
        }
      }
    }
    ```
    * ```local_name```: Provider에서 사용될 이름. ex> mycloud
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
        
        
    
    
    