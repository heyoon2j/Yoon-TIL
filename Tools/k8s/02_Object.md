# Object
Kubernetes에서 Cluster의 상태를 나타내는 단위로, Resource의 상태를 Object로 정의해 관리한다. 그리고 이를 명세해 놓은 것을 Object spec이라고 한다.
</br>

---
## Object 종류


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
    * 보통 Node를 관리하는 Pod인 경우 사용 (kube-proxy, calico, metalLB 등)
* StatefulSet
    * 이전 상태를 기억하는 세트라는 의미. 생성되는 Pod가 고정된 이름, 볼륨, 설정등을 가지도록 할 수 있다 (순서 및 고유성 보장)
    * 좋은 구조가 아니기 때문에 요구 사항에 맞게 사용 (ex> Master-Slave 구조 : Redis, Zookeeper, Cassandra, MongoDB 등)
    * Headless Service로 노출가능 (IP가 할당되지 않으며, DNS 통해서만 접근가능)


### Volume 관련
* Volume
    * Pod가 생성될 때 Pod에서 사용할 수 있는 Directory를 제공
    * 영속적인 것이 아닌 임시로 Directory를 사용
* PersistentVolume (PV)
    * Pod가 지워져도 삭제되지 않는 볼륨 생성
    * PersistentVolume을 사용하려면 관련된 Helper Program이 필요하다
    * Volume Mode
        1) Filesystem : Directory에 Mount하는 형태로 사용 (미리 File System이 만들어져있어야 한다)
        2) Block : Block Storage로 연결 (File System이 없어야 한다)
    * Access Mode
        * ReadWriteOnce (RWO) : 하나의 Node에서만 연결 가능하면, 동일 Node에 한해서 Multi Pod 연결 가능 (Read/Write 가능)
        * ReadOnlyMany (ROX) : Multi Node 연결 가능 (Read만 가능)
        * ReadWriteMany (RWX) : Multi Node 연결 가능 (Read/Write 가능)
        * ReadWriteOncePod (RWOP) : 하나의 Pod에만 연결할 수 있다 (Read/Write 가능)
        > 볼륨 리스트에 따라 허용되는 Access Mode가 있다!!!
    * NFS의 경우, 사이즈를 제한하는 방법은 다음과 같다.
        1) kind : LimitRange
        2) kind : ResourceQuota
    * 상태
        - Available: 사용 가능한 상태
        - Bound: PVC 요청에 의해 Pod에 연결된 상태
        - Released: PVC 삭제 후, Retain 정책에 의해 사용은 불가하지만 PV를 보존한 상태 (다시 재사용할려면 재생성 작업이 필요)
        - Fail: 문제가 생긴 상태
    * 반환 정책
        - Retain : 수동 반환. PVC가 삭제되면 PV를 Released 상태로 만들어 PV를 보존. PV 내 데이터는 유지되지만 PV를 재사용할 수 없다. 삭제 또는 재사용하기 위해서는 수동으로 작업해야 한다.
        - Delete : 자원 삭제. 동적으로 프로비저닝된 볼륨은 스토리지클래스의 반환 정책을 상속하며 기본값이 Delete 이다.
        - Recycle : 재활용 정책이나 더 이상 사용하지 않는다! (deprecated)
* PersistentVolumeClaim (PVC)
    * 생성된 PV를 사용하기 위해 일정 공간을 할당 해달라고 요청하는 Object
    * 요청에는 정적 요청과 동적 요청이 있다.
        * 정적 : 미리 생성되어진 PV를 요청하는 것 (Storage Class : "")
        * 동적 : 요청 시 PV를 자동으로 생성하는 것 (Storage Class : <sc_name>)
    > PVC는 Namespace 안에 있고, PV는 Cluster에 선언되는 Object이다. 그렇기 때문에 PVC에 연결되는 Pod는 같은 Namespace 안에 있어야 한다!!!!! (+ PV는 namespace를 선언하지 못한다)
* StorageClass
    * Storage Profile : 스토리지에 대한 정책
    * Dynamic PV를 사용하기 위해서는 StorageClass를 선언해야 된다.



### Network 관련
* Service
    * Pod는 Cluster 내에서 유동적으로 움직이기 때문에 접속 정보가 고정일 수 없다. 따라서 Pod의 통신을 안정적으로 유지하기 위해 Service 사용
    * Service는 새로 Pod가 생성될 때 부여되는 새로운 IP를 기존에 제공하던 기능과 연결시켜 줌
    * Service 종류
        1) ClusterIP
        2) NodePort
        3) 
* SessionAffinity
    - Stickness 설정 Object
* Ingress
* IngressClass
* IngressClassParams
</br>

### Scheduler 관련
* Job
    * 1회성으로 처리하고 종료하는 Application인 경우 사용  
* CronJob
    * 주기적으로 처리하고 종료하는 Application인 경우 사용
</br>


### Authentication 관련
* Policy
* Role
* ClusterRole
* RoleBinding
* ClusterRoleBinding


### Config
* ConfigMap
    - Config를 목적으로 가지는 Object
    - 환경 변수 우선 순위 : 하드코딩 --> OS환경 변수 --> Dockerfile --> Kubernetes Configmap
    - 1 MiB를 초과할 수 없음
* Secret
    - 암호화가 필요한 내용을 저장할 때 사용하는 Object
    - Secret이지만 실제로는 인코딩만되고 암호화되지 않는다 (https://cwal.tistory.com/47)
    - 암호화 방법 : https://kubernetes.io/docs/tasks/administer-cluster/encrypt-data/
        1) EncryptionConfiguration 구성 : etcd 저장 시에 암호화하도록 적용시킨다.
        2) Authentication & Athorization을 통해 Secret에 접근을 제한한다.
    > 하지만 암호화 방법도 etcd에 저장할때만 암호화되므로, API Server가 해킹당하면 말짱 꽝이다. (보안 적용 필요!!!)
* EncryptionConfiguration
    - etcd에 저장 시 암호화
    - 암호화할 Object 지정 가능
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


