# Object
Kubernetes에서 Resource는 Object로 관리되어진다. 그리고 이를 명세해 놓은 것을 Object spec이라고 한다.
</br>

---
## Object 종류
* Namespace
    * Cluster에서 사용되는 리소스들을 구분해서 관리하는 그룹
    * default (기본), kube-system (k8s system), metallb-system 이 기본으로 등록되어 있음

### Pod 관련
* Pod
    * Kubernetes에서 실행되는 최소 단위
    * 독립적인 공간과 사용 가능한 IP를 가짐
    * 1개 이상의 컨테이너를 가질 수 있지만, 일반적으로 1개의 Pod에는 1개의 컨테이너를 적용
* ReplicaSet
    * Pod 수를 보장하는 기능을 가짐
    * 하지만 Pod 수만 보장하는 기능을 가지고 있기 때문에, 롤링 업데이트 등 다른 기능을 위해서는 Deployment를 사용
* Deployment
    * 기본 Object만으로는 한계가 있어 이를 좀 더 효율적으로 작동하도록 조합하고 추가해 구현한 것이 Deployment이다. Pod Object + ReplicaSet Object + 추가 기능을 합쳐 놓은 형태이다.
* DaemonSet
    * Deployment의 replicas가 Node 수만큼 정해져 있는 형태로, Node 하나당 Pod 한개를 생성하는 경우
    * 보통 Node를 관리하는 Pod인 경우 (kube-proxy, calico, metalLB 등)

### Volume 관련
* Volume
    * Pod가 생성될 때 Pod에서 사용할 수 있는 Directory를 제공
    * 영속적인 것이 아닌 임시로 Directory를 사용
* PersistentVolume
    * 
    * 
    * 
* PersistentVolumeClaim
    * 
    * 


### Network 관련
* Service
    * Pod는 Cluster 내에서 유동적으로 움직이기 때문에 접속 정보가 고정일 수 없다. 따라서 Pod의 통신을 안정적으로 유지하기 위해 Service 사용
    * Service는 새로 Pod가 생성될 때 부여되는 새로운 IP를 기존에 제공하던 기능과 연결시켜 줌

* ConfigMap
    * 
    * 
    * 
* StatefulSet
    * 
    * 
    * 

### Scheduler 관련
* CronJob
    * 
    * 
</br>
</br>


---
## Object spec 구조
```yaml
apiVersion: app/v1
kind: Deployment
metadata:
    name: dpy-test
    labels:
        app: nginx
spec:
    #...
```
* apiVersion : API 버전
* kind : Object 종류
* metatdata : Object 메타 데이터 정의
* spec : Object 기능 정의
</br>
</br>


