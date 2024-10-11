# Storage
볼륨은 크게 Pod Lifecycle과 함께하는 Volume과 영구적으로 데이터를 저장할 수 있는 Storage로 구분할 수 있다.
* Volume Drivers
    - AUFS / ZFS / BTRFS / Device Mapper / Overlay / Overlay2
* Storage Drivers
    - Local / Azure File Storage / AWS EBS / Convoy / RexRay 등
</br>

---
## Storage in Docker
```sh
./var/lib/docker
├── aufs
├── containers
├── image
└── volumes     # Dokcer에서 생성된 모든 볼륨은 해당 폴더에 저장
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
* Image Layer 
    - Build 시 생성
    - Read Only
    - 모든 컨테이너가 공유하는 레이어
    - 해당 레이어의 파일은 기본적으로 수정이 불가하며, 수정시 수정 사항이 컨테이너 레이어 생성된다.
* Container Layer
    - Run 시 생성
    - Read / Write
    - 각 각 생성된 컨테이너가 가지는 레이어
    - 파일을 생성하거나 수정을 하게 되면 해당 레이어에 저장된다.
> 실행중인 컨테이너를 삭제하게 되면 컨테이너 레이어만 삭제된다. 그렇기 때문에 컨테이너 레이어의 내용을 남기기 위해서는 볼륨을 만들어야 한다!

</br>


---
## CSI (Container Storage Interface)
</br>
</br>


---
## Volume 관련 Object
* Volume
    - Pod가 생성될때 Pod에서 사용할 수 있는 Directory를 제공
    - 영속적인 것이 아닌 임시로 Directory를 사용
* PersistentVolume (PV)
    * Pod가 지워져도 삭제되지 않는 볼륨 생성
    * PersistentVolume을 사용하려면 관련된 Helper Program이 필요하다
    * Volume Mode
        1) Filesystem : Directory에 Mount하는 형태로 사용 (미리 File System이 만들어져있어야 한다)
        2) Block : Block Storage로 연결 (File System이 없어야 한다)
    * Access Mode
        - ReadWriteOnce (RWO) : 하나의 Node에서만 연결 가능하면, 동일 Node에 한해서 Multi Pod 연결 가능 (Read/Write 가능)
        - ReadOnlyMany (ROX) : Multi Node 연결 가능 (Read만 가능)
        - ReadWriteMany (RWX) : Multi Node 연결 가능 (Read/Write 가능)
        - ReadWriteOncePod (RWOP) : 하나의 Pod에만 연결할 수 있다 (Read/Write 가능)
        > 볼륨 리스트에 따라 허용되는 Access Mode가 있다!!!
    * 반환 정책 (claimPolicy)
        - Retain : 수동 반환. PVC가 삭제되면 PV를 Released 상태로 만들어 PV를 보존. PV 내 데이터는 유지되지만 PV를 재사용할 수 없다. 수동으로 삭제해야되며, 안의 내용은 계속 살아있다.
        - Delete : 자원 삭제. 동적으로 프로비저닝된 볼륨은 스토리지클래스의 반환 정책을 상속하며 기본값이 Delete 이다.
        - Recycle : 재활용 정책이나 더 이상 사용하지 않는다! (deprecated)
    * NFS의 경우, 사이즈를 제한하는 방법은 다음과 같다.
        1) kind : LimitRange
        2) kind : ResourceQuota
    * 상태
        - Available: 사용 가능한 상태
        - Bound: PVC 요청에 의해 Pod에 연결된 상태
        - Released: PVC 삭제 후, Retain 정책에 의해 사용은 불가하지만 PV를 보존한 상태 (다시 재사용할려면 재생성 작업이 필요)
        - Fail: 문제가 생긴 상태

* PersistentVolumeClaim (PVC)
    - 생성된 PV를 사용하기 위해 일정 공간을 할당 해달라고 요청하는 Object
    - 요청에는 정적 요청과 동적 요청이 있다.
        * 정적 : 미리 생성되어진 PV를 요청하는 것 (Storage Class : "")
        * 동적 : 요청 시 PV를 자동으로 생성하는 것 (Storage Class : <sc_name>)
    - 옵션
        - volumeMode
        - access Mode
        - storageClassName
        - volumeName (Option)
    > PVC는 Namespace 안에 있고, PV는 Cluster에 선언되는 Object이다. 그렇기 때문에 PVC에 연결되는 Pod는 같은 Namespace 안에 있어야 한다!!!!! (+ PV는 namespace를 선언하지 못한다)
* StorageClass
    - Storage Profile : 스토리지에 대한 정책
    - Dynamic PV를 사용하기 위해서는 StorageClass를 선언해야 된다.

> PV 통해 볼륨 그룹을 만들고, PVC를 통해 여러 PV 중에 spec에 적합합 PV를 동적으로 선택한다. 즉, 자동으로 더 효율적인 선택을 할 수 있으며, 확장성과 유연성을 유지할 수 있게 해준다.

> SC를 통해 여러 플랫폼에서 동적 볼륨을 만들고, PVC를 통해 Pod와 연결시킨다! 

