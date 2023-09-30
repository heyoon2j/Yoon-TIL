# Namespace
Resource를 논리적으로 나눈 공간
* Namespace 목록 화인
    ```sh
    # 네임스페이스에 속하는 리소스
    kubectl api-resources --namespaced=true

    # 네임스페이스에 속하지 않는 리소스
    kubectl api-resources --namespaced=false
    ```


## Scope
![Namespace_Scope](Namespace_Scope)
</br>


## Basic
### Basic Namespace
Cluster의 기본 Namespace로는 4가지가 있다.
* default
    - Namespace를 지정하지 않은 경우 기본으로 할당되는 Namespace
* kube-system
    - Kubernetes System에 의해 생성되는 Object를 관리하는 영역
    - apiserver, coreDNS, kube-controller, kube-scheduler, kube-proxy등이 포함
* kube-public
    - Cluster 내 모든 사용자로부터 접근 가능한 Object를 관리하는 영역
* kube-node-lease
    - Node 연결 정보를 관리하기 위한 영역
</br>
</br>


### Namespace 접근 방식
다른 Namespace의 서비스에 접근하기 위해서는 FQDN을 통해 접근한다.
```sh
$ curl -v <service>.<namespace>.svc.cluster.local:<port>
$ curl -v <service>.<namespace>.svc:<port>
$ curl -v <service>:<port>
```
* Ref : https://velog.io/@pinion7/Kubernetes-%EB%A6%AC%EC%86%8C%EC%8A%A4-Namespace%EC%97%90-%EB%8C%80%ED%95%B4-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B3%A0-%EC%8B%A4%EC%8A%B5%ED%95%B4%EB%B3%B4%EA%B8%B0


---
## Annotaion
Kubernetes System에 필요한 정보를 표시
* 문법 에러를 체크, 빌드나 배치시 코드를 자동으로 생성, 실행 시 특정 기능을 실행하도록 정보 제공
