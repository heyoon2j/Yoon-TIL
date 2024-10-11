# Pull Mode
* ```ansible-pull``` 명령어를 이용하여 Romote Repository에서 코드를 가지고 온 후 동작하게 된다.

## Push vs Pull
* 어떤 거를 선택할지 현재 시스템을 생각하며 선택하는 것이 좋다.
    * 통신 보안 정책을 확인해야 한다.
    * 시스템 아키텍처를 확인해야 한다.

### Push
* Cotroller Node가 SSH를 통해 Managed Node에 원격 작업을 수행한다.
* Pros
    * Managed Node에 추가 Software 설치가 필요하지 없다.
* Cons
    * 너무 많은 Managed Node를 동시에 Provisioning하는 경우 병목 현상을 발생시킬 수 있다.

### Pull
* 각 Node가 Repository에서 코드를 가지고와 작업을 수행한다.
* Remote Repository를 사용하기 때문에, Public한 Repository를 사용하면 안된다.
* Pros
    * 각 Node에서 Provisioning이 되기 때문에 병렬 처리가 빠르다. 
* Cons
    * 각 Node에 코드를 가지고 오기 위한 Software 설치가 필요하다.


## Use Pull Mode
## 1. Install VCS
* Repository에서 코드를 가지고 오기 위해 VSC를 설치한다.


## 2. 



## 3. 
    


## 4. 



## 5. 












### Reference
* https://jpmens.net/2012/07/14/nsible-pull-instead-of-push/
