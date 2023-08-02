# Function
Terragrunt에서 지원하는 내장 함수는 다음과 같다 (https://terragrunt.gruntwork.io/docs/reference/built-in-functions/)


##  find_in_parent_folders(NAME)
상위 디렉토리들에서 파일을 찾아 구성을 가지고 온다.
* Default는 ```terragrunt.hcl``` 이다.
    ```
    
    ```
* 
    ```
    ```

---
## path_relative_to_include()
* 해당 파일 위치와 상위 디렉토리에 있는 파일의 상대 경로를 반환한다.

---
##
* 
* Virtual Private Gateway : AWS의 VPN용 Gateway

---
## get_env(NAME, DEFAULT)
* NAME이라고 설정된 환경 변수 값을 가지고 온다. 없는 경우 DEFAULT가 반환된다.
* 환경 변수 설정은 다음과 같다(export TF_NAME='envVar')
```
export TF_NAME='envVar'
```


---
## get_parent_terragrunt_dir(NAME)
* 상위 terragrunt.hcl 파일이 있는 Directory의 절대 경로 반환
    ```
    path = get_parent_terragrunt_dir()
    ```
* include의 파일 위치의 부모 Directory를 찾아서 반환
    ```
    include "root" {
        path = find_in_parent_folders()
    }


    terraform {
        source = "${get_parent_terragrunt_dir("root")}/modules/vpc"
    }
    ```



---
##


---
##
* 