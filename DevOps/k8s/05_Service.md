# Service
서비스는 외부와 Kubernetes Cluster 네트워크 연결을 위한 점점(서비스)이다.
* Service Type은 다음과 같다.
    1) ClusterIP
    2) NodePort
    3) LoadBalancer (가장 많이 사용 될거라고 보임)
    4) ExternalName
* 보조 기능
    1) Ingress
    2) MetalLB
    3) HPA
* 서비스를 생성하면 해당 DNS 엔트리가 생성된다.
    - FQDN : ```<서비스-이름>.<네임스페이스-이름>.svc.cluster.local```

---
## ClusterIP
Cluster 내에서만 유효한 Cluster IP를 제공한다. 그렇기 때문에 Pod간의 통신을 위해 사용된다!
* 외부에서 kube-proxy를 통해서 접근할 수 있다 (Client --> kube-proxy --> Service:ClusterIP --> Pod)


---
## NodePort 서비스
가장 쉬운 방법으로 모든 Worker Node의 특정 포트를 열고, 해당 포트로 들어오는 모든 요청은 NodePort 서비스로 전달되고 이를 연결된 Pod로 전달한다.
* Node IP를 빌려 외부 User가 Pod에 접근할 수 있도록 한다. (Node Port ---> Target Port로 변환)
    - Node Port : Node Port
    - Port : NodePort 서비스 Port (기본적으로 Target Port와 같은 값으로 설정)
    - Target Port : Pod Port
* 1개의 NodePort는 1개의 Deployment만 할당할 수 있다.
* 부하분산 기능도 가지고 있다.
    ```
    $ kubectl expose deplyment np-pods --type=NodePort --name=np-svc-v2 --prot=80
    ```
    * expose로도 생성 가능하나 NodePort를 지정할 수 없다. 임의로 포트가 지정됨 (30000 ~ 32767)
</br>




---
## LoadBalancer
Load Balancer를 사용하려면 Load Balancer를 구현해 둔 외부 서비스 업체의 도움을 받아야 한다!
* 내부적으로 NodePort 서비스를 사용하며, 이를 클라우드 제공자의 LoadBalancer를 통해 외부에 노출시킨다.






</br>
</br>



---
## ExternalName



</br>
</br>



---
## Ingress
외부에서 내부로 접근하는 요청을 어떻게 처리할 정해놓은 규칙
> Ingress를 사용하는 이유는 여러가지 기능을 사용하기 위해서 Rule, 인증 등등
* 기본적으로 Ingress Controller + Ingress Object를 생성해야 하며, Pod와 직접 통신할 수 없어 "NodePort" 또는 "LoadBalancer˚와 연동해야 한다!!
    * Ingress Conroller Pod/Deployment : Controller 생성
    * Ingress Object : 규칙 정의
    * NodePort or LoadBalancer Service : Service 생성
* 여러 개의 Deployment가 있을 때, 그 수만큼 노드포트 서비스를 구동해야 하는 경우 Ingress를 사용한다. (결국 NodePort Group을 만들다고 생각하면 될꺼 같다. 그렇기 때문에 NodePort와 동일한 통신 구조를 가지고 있다)
* 고유한 주소 제공
* 트래픽에 대한 L4/L7 Loadbalancer 제공
* 보안 인증서 처리
</br>

### Ingress Controller
Ingress 동작을 수행하기 위해서는 Controller 설치가 필요하다!
* K8s에서 제공하는 Ingress Controller (타사 프로젝트 Controller가 있긴 하다)
    * AWS
    * GCE
    * nginx
* 여러의 Ingress Controller를 사용할 수 있으며, 원하는 Conroller를 IngressClass를 통해 선택하면 된다.
</br>



---
## MetalLB
On-premise에서 Load Balancer를 제공하고 싶을 때 사용한다(Bare metal 환경에서 로드 벨런서를 사용할 수 있게 고안된 오픈소스 프로젝트)
* L2 네트워크(ARP/NDP)와 L3 네트워크(BGP)로 구현됨
* MetalLB Contoller Pod 와 MetalLB Speaker DaemonSet을 구성한다.
> 필요시 나중에 오픈소스 프로젝트 확인 필요!

</br>
</br>

---
## HPA (Horizontal Pod Autoscaler)
Auto Scaling 기능을 가진 Object
* metrics-server가 필요 (해당 서버는 Auto Scaling을 위한 용도로 데이터를 kube-apiserver로 전달하기 때문에 실제 모니터링를 하는 용도와는 거리가 멀다!!)
* metrics-server는 kubelet으로부터 데이터를 수집, 통합하여 kube-apiserver로 전달
* metrics-server 설정
    - TLS 인증 : kubelet과 통신할 때 CA 인증서가 필요
    ```
    $ kubectl autoscale deployment hpa-hname-pods --min=1 --max=30 --cpu-percent=50
    ```








---
# Storage
* Storage Drivers
    - AUFS / ZFS / BTRFS / Device Mapper / Overlay / Overlay2
* Volume Drivers
    - Local / Azure File Storage / AWS EBS / Convoy / RexRay 등

## Sotrage in Docker
```sh
./var/lib/docker
├── aufs
├── containers
├── image
└── volumes         # Dokcer에서 생성된 모든 볼륨은 해당 폴더에 저장
    └── data_volumes 
```

```
# Container Layer
Layer 6. Container Layer

# Image Layers
Layer 5. Update Entrypoint with "" command
Layer 4. Source code
Layer 3. Chages in piop packages
Layer 2. Chages in apt packages
Layer 1. Base Ubuntu Layer
```
* Image Layer : 
    - Build 시 생성
    - Read Only
    - 모든 컨테이너가 공유하는 레이어
    - 해당 레이어의 파일은 기본적으로 수정이 불가하며, 수정 시 수정 사항이 컨테이너 레이어 생성된다.
* Container Layer : 
    - Run 시 생성
    - Read / Write
    - 각 각 생성된 컨테이너가 가지는 레이어
    - 파일을 생성하거나 수정을 하게 되면 해당 레이어에 저장된다.


> 실행중인 컨테이너를 삭제하게 되면 컨테이너 레이어만 삭제된다. 그렇기 때문에 컨테이너 레이어의 내용을 남기기 위해서는 볼륨을 만들어야 한다!





## CSI (Container Storage Interface)





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
    * 반환 정책 (claimPolicy)
        - Retain : 수동 반환. PVC가 삭제되면 PV를 Released 상태로 만들어 PV를 보존. PV 내 데이터는 유지되지만 PV를 재사용할 수 없다. 수동으로 삭제해야되며, 안의 내용은 계속 살아있다.
        - Delete : 자원 삭제. 동적으로 프로비저닝된 볼륨은 스토리지클래스의 반환 정책을 상속하며 기본값이 Delete 이다.
        - Recycle : 재활용 정책이나 더 이상 사용하지 않는다! (deprecated)
* PersistentVolumeClaim (PVC)
    * 생성된 PV를 사용하기 위해 일정 공간을 할당 해달라고 요청하는 Object
    * 요청에는 정적 요청과 동적 요청이 있다.
        * 정적 : 미리 생성되어진 PV를 요청하는 것 (Storage Class : "")
        * 동적 : 요청 시 PV를 자동으로 생성하는 것 (Storage Class : <sc_name>)
    * 옵션
        - volumeMode
        - access Mode
        - storageClassName
        - volumeName (Option)
    > PVC는 Namespace 안에 있고, PV는 Cluster에 선언되는 Object이다. 그렇기 때문에 PVC에 연결되는 Pod는 같은 Namespace 안에 있어야 한다!!!!! (+ PV는 namespace를 선언하지 못한다)
* StorageClass
    * Storage Profile : 스토리지에 대한 정책
    * Dynamic PV를 사용하기 위해서는 StorageClass를 선언해야 된다.

> PV 통해 볼륨 그룹을 만들고, PVC를 통해 여러 PV 중에 spec에 적합합 PV를 동적으로 선택한다. 즉, 자동으로 더 효율적인 선택을 할 수 있으며, 확장성과 유연성을 유지할 수 있게 해준다.

> SC를 통해 여러 플랫폼에서 동적 볼륨을 만들고, PVC를 통해 Pod와 연결시킨다! 


내용은 인스턴스 ID를 받으면 API 호출을 통해 인스턴스 정보를 Output으로 내보내는 건데 아래 코드를 사용해서 수정하고 만들어줘

읿력 값: 인스턴스 ID
스텝 코드 : 
  - name: GetInstance
    action: 'aws:executeAwsApi'
    inputs:
      Service: ec2
      Api: DescribeInstances
      Filters:
        - Key: InstanceIds
          Values:
            - '{{ InstanceId }}'
    outputs:
      - Name: myInstance
        Selector: '$.Reservations[0].Instances[0].InstanceId'
        Type: String
      - Name: platform
        Selector: '$.Reservations[0].Instances[0].PlatformDetails'
        Type: Strin줘
      - Name: PingStatus
        Selector: '$.Reservations[0].Instances[0].State.Name'
        Type: String
      - Name: ResourceType
        Selector: '$.Reservations[0].Instances[0].ResourceType'
        Type: String