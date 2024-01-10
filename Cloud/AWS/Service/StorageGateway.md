# Storage Gateway
* NFS(Network File System) 및 SMB(Server Message Block) 같은 업계 표준 파일 프로토콜을 사용하여 Amazon S3에서 객체를 저장하고 검색할 수 있다.
</br>


## Storage Gateway 구조
* 기본적으로 Storage Gateway는 해당 서비스가 설치된 EC2 Instance(서버)이다 (== DMS의 복제 인스턴스와 같은 개념)
* On-premise
    * Cache Storage :
        - 실질적인 데이터는 S3에 기록하고, 자주 액세스하는 데이터는 해당 Cache Storage에 저장(성능 극복을 위해).
        - Upload Buffer에서 S3로 업로드되기 까지 기다리는 데이터 대한 내구성 저장소. 그렇기 때문에 기본적으로 Upload Buffer의 데이터를 보관할 수 있을만큼 Cache Storage가 Upload Buffer보다 커야됨
        - 일반적으로 Cache Storage는 기존 파일 저장소의 20% 이상 할당하는 것을 권장
    * Upload Buffer : S3에 업로드하기 전에 데이터를 저장해 놓는 영역
* AWS
    * Cache Storage (Cache Storage + Upload Buffer이지 않을까 싶다) :
        - S3 Storage Gateway는 볼륨을 인벤토리 캐시로 사용한다. 파일은 실질적으로 S3에 기록되고, 인벤토리 캐시만 볼륨에 저장되는 형태이다.
        - 변경사항을 저장하면 이후 변경사항에 대해서만 S3 업데이트를 진행한다.
        - Client -> Cache -> S3 순서로 데이드가 흘러가기 때문에, Client가 아닌 S3에 파일을 추가/삭제하는 경우 충돌이 밣생하거나 예상치 못한 동작을 할 수 있다(ex> S3로 올림 파일이 Client에 보이지 않음 등). 이를 해결하는 방법으로는 Cache Refreshing을 진행한다.
</br>

### 동작 과정
1) NFS 클라이언트에서 파일을 File Gateway에 기록 --> File Gateway가 파일의 데이터를 Amazon S3 업로드 (S3 Object 생성)
2) 데이터를 먼저 업로드한 다음 메타데이터(소유권, 타임스탬프 등)가 업데이트 (이때 기존 S3 Object가 새로 만들어진다)
> 즉, 이 과정에서 다른 버전을 작성하여 두 가지 버전의 객체를 만듭니다. S3 버전 관리가 활성화되어 있으면 두 버전이 모두 저장된다.
</br>


## Amazon S3 File Gateway 기능
* Upload Alarm : 특정한 상황 아니면 잘 안 쓰인다 (ex> 업로드 시, Cache refresh 실행과 같은 Hooking)
* Directory Permission : Mount 된 Path 안의 계정 및 권한 관련 설정 (NFS 와 S3의 서로 다른 형태를 맞추기위해 S3의 메타데이터에 UNIX 권한 관련 정보를 저장)
* Squash Level : Client UID로 파일을 전송했을 때, 파일의 UID가 어떤식으로 저장될지 결정 (마운트되어 있지만 실질적으로는 상대방의 Server에 파일을 저장하는 것이므로 계정 및 권한문제가 발생할 수 있다!)
</br>


* 그렇기 때문에 하다.
    - IAM : 자체적으로 생성됨
    - Security Group
        1) TCP/2049 - NFS
        2) TCP/111 - NFS
        3) TCP/20048 - NFS
        4) TCP/80 - Storage Gateway Activation
        5) TCP/22 - SSH



## 생성 과정
기본적으로 __Storage Gateway에 적용할 IAM__ 과 __Instance에 적용할 SG__ 가 필요 (== DMS의 복제 인스턴스와 같은 개념)

### Step 1. Gateway 생성
1. Storage Gateway Instance 생성
    * 기본적으로 다음과 같은 포트 요구
        * NFS : 2049, 111, 20048
        * Gateway 정품 Key를 얻기 위한 Port : 80
        * SSH : 22
    * Cahe or Upload Buffer로 사용할 EBS를 추가로 붙인다(최소 150 GiB)
        * 비동기식으로 작업당 Buffer를 나눠놓은거 같다!
2. Storage Interface Endpoint 생성
    * 필요한 Port : 443, 1026, 1027, 1028, 1031, 2222
3. Gateway 정품 인증 키 받기
    * curl 명령어
        * https://docs.aws.amazon.com/ko_kr/storagegateway/latest/userguide/get-activation-key.html
        * ```$ curl "http://VM IP ADDRESS/?gatewayType=FILE_S3&activationRegion=REGION&vpcEndpoint=VPCEndpointDNSname&no_redirect"```
    * EC2 Instance에 접속
        ![GetActivationKey](../img/GetActivationKey.png)

        * ```$ ssh -i test.pem admin@1.1.1.1```
        * ```$ su admin```
        * "0" 선택
4. Gateway Activation
    * 인증 키 입력
5. Logging 구성
</br>
</br>

### Step 2. 파일 공유 생성
1. Directory Permission
2. 
 







