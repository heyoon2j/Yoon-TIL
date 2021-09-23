# EBS
* Amazon EC2 Instance를 위한 안정적이고 분리 가능한 블록 수준 스토리지.
* EBS는 한 번에 한 인스턴스에만 열결할 수 있어야 하며, 동일한 가용영역에 있어야 한다.
* 빠르게 액세스해야 하고 장기적인 지속성이 필요한 데이터에는 Amazon EBS를 권장한다.
</br>


## __Volume Type__
1. SSD - IOPS
    1) 범용 SSD(__gp2__ 및 __gp3__)
        * 대부분의 워크로드에 사용
        * 1GB 당 3IOPS로 계산되므로 300 IOPS가 필요하려면 Size를 100GB로 늘려야 한다.
    2) 프로비저닝된 IOPS SSD(__io1__ 및 __io2__)
        * 대규모 데이터베이스 워크로드에 사용
        * Size와 IOPS를 설정할 수 있다.
2. HDD - MB/s
    1) 처리량 최적화 HDD(__st1__)
        * 자주 액세스하고 처리량 집약적인 워크로드에 사용
        * 빅데이터, 스트리밍, 로그 처리 등
    2) 콜드 HDD(__sc1__)
        * 자주 액세스하지 않는 워크로드에 사용
        * 최저 비용의 HDD 볼륨으로 비용이 최대한 낮아야 하는 시나리오에 사용
</br>


## Amazon EBS 최적화 인스턴스 (EBS Optimization)
* EBS 전용 네트워크 선을(전용 대역폭을 사용) 사용함으로써 다른 서비스들의 네트워크와 경합하지 않게 된다.
* 해당 기능은 EBS에서 지원하는 것이 아닌 전용 인스턴스의 유형을 선택해야 한다.
* Amazon EBS I/O를 위한 추가 전용 용량을 제공한다.
</br>


## __Snapshot__
![SnapshotWork](../img/SnapshotWork.png)
* 특정 시점을 스냅샷 찍어 S3에 저장할 수 있다.
* 이를 통해 백업으로 사용되며, 요금은 저장된 데이터의 양을 기준으로 지불한다.
* 리전 간에 스냅샷을 복사하여 지리적 확장할 수 있다.
* __스냅샷의 작동 방식은 증분식(incremental backups)이다.__ 그렇기 때문에 해당 스냅샷은 가장 최근에 변경된 장치의 블록만 저장한다. 삭제하려면 전체를 삭제해야 한다.
</br>


## __암호화__
* 암호화 작업은 AWS KMS를 사용한다. 그리고 암호화는 EC2 Instance에서 EBS Storage로 전송 중인 데이터의 암호화를 제공한다.
* 암호화된 볼륨의 스냅샷은 자동으로 암호화된다. 
* 암호화된 스냅샷에서 생성한 볼륨은 자동으로 암호화된다.
* EBS는 업계 표준 AES-256 알고리즘을 사용하여 데이터 키로 볼륨을 암호화한다. __데이터 키는 암호화된 데이터와 함께 디스크에 저장된다.__
* 작동 방식
    1) KMS 키를 지정하여 AWS KMS에 GenerateDataKeyWithoutPlaintext 요청을 보낸다
    2) AWS KMS는 새 데이터 키를 생성하고 볼륨 암호화를 위해 선택한 KMS 키로 암호화하고 암호화된 데이터 키를 Amazon EBS로 전송하여 볼륨 메타데이터와 함께 저장한다
    3) 암호화된 볼륨을 인스턴스에 연결하면 Amazon EC2가 데이터 키를 해독할 수 있도록 AWS KMS에 CreateGrant 요청을 보낸다.
    4) 그리고나서 EC2가 Decrypt 요청을 보내면, AWS KMS는 암호화된 데이터 키를 해독하고 Amazon EC2로 보낸다
    5) Amazon EC2는 하이퍼바이저 메모리의 일반 텍스트 데이터 키를 사용하여 볼륨에 대한 디스크 I/O를 암호화한다. 일반 텍스트 데이터 키는 볼륨이 인스턴스에 연결되어 있는 한 메모리에 유지된다
</br>
</br>



# EFS
* 공유 네트워크 파일 스토리지 (NAS: Network Attach Storage)
* Linux 워크로드, NFSv4.0 및 NFSv4.1 파일 시스템
* 해당 파일 시스템 프로토콜을 제공하는 EC2 AMI를 선택해야 되거나, 일부 AMI의 경우 NFS Client를 설치해야 한다.
</br>

## EFS Class
1. 표준 스토리지 클래스
    * 다중 AZ 복원력과 최고 수준의 내구성 및 가용성을 제공하는 EFS Standard 및 EFS Standard–Infrequent Access(Standard–IA).
    * AWS 리전 기준
2. One ZOne 스토리지 클래스
    * EFS One Zone 및 EFS One Zone–Infrequent Access(EFS One Zone–IA)는 고객이 단일 AZ에 데이터를 저장하도록 선택하여 추가 비용 절감을 선택할 수 있도록 한다.
    * Availability Zone 기준
</br>
</br>


## EFS Performance
1. Performance Mode
    * 변경 불가능
    * 범용 모드(General Purpose): 범용 웹 서비스 환경, 콘텐츠 관리 시스템, 홈 디렉토리 및 일반 파일 서비스와 같이 지연 시간에 민감한 사용 사례
    * 최대 I/O 성능 모드(Max I/O): 높은 수준의 집계 처리량 및 초당 작업에 사용. 빅 데이터 분석, 미디어 처리 및 게놈 분석과 같은 고도의 병렬화된 애플리케이션 및 워크로드
2. Throughput Mode
    * 24시간 뒤에 변경 가능
    * 버스트(Bursting): 파일 시스템 크기가 커짐에 따라 확장
    * 프로비저닝된(Provisioned): 즉시 프로비저닝된 처리량을 제공받는다(비용이 비쌈!!!!)
* 계산: https://docs.aws.amazon.com/efs/latest/ug/performance.html#throughput-modes
</br>
</br>



## EFS Work
![EFSWork](../img/EFSWork.png)
1. Amazon EFS를 사용하여 파일 시스템 생성
2. 마운트를 위한 VPC Endpoint 생성
3. Amazon EC2 인스턴스에 파일 시스템을 탑재한 다음 파일 시스템에서 데이터를 읽고 쓸 수 있다.
    * NFSv4.0, NFSv4.1 프로토콜을 통해 통신
    * 통신 시 ```amazon-efs-utils``` 도구 사용
4. NFS를 지원하지 않는 AMI의 경우, NFS Client를 설치해야 한다.
</br>
</br>


## EFS Security
* Amazon EFS는 전송 데이터 암호화와 저장 데이터 암호화라는 두 가지 형식의 파일 시스템 암호화를 지원한다.
* EFS 파일 시스템을 생성할 때 저장 데이터 암호화를 활성화할 수 있다.
* 파일 시스템을 마운트할 때 전송 중인 데이터의 암호화를 활서화할 수 있다.
* 암호화 과정
    1) 파일 데이터는 파일 시스템을 생성할 때 지정한 CMK를 사용하여 저장 시 암호화된다.
    2) 파일 이름, 디렉터리 이름 및 디렉터리 콘텐츠인 메타데이터는 EFS에서 관리하는 키로 암호화된다.
</br>
</br>


## Network Access Control
* Interface Endpoint를 이용하여 EFS와 Instance의 Private Connection을 설정
* Reference: https://docs.aws.amazon.com/efs/latest/ug/efs-vpc-endpoints.html
</br>
</br>


## Backup
* AWS Backup은 EFS 파일 시스템의 증분식 백업을 수행한다.
* https://docs.aws.amazon.com/ko_kr/efs/latest/ug/awsbackup.html
</br>
</br>


## Amazon FSx
* Winodws 워크로드, NTFS 파일 시스템
</br>
</br>


## Cost
* 
</br>
</br>


## EBS vs EFS vs S3
* Block vs File vs Object
* EBS
    * 가용 영역 기준
    * 하나의 인스턴스에 붙을 수 있다.
* EFS
    * 가용 영역 기준
    * 여러 개의 인스턴스에 붙을 수 있다.
* S3
    * 리전 기준
    * 여러 개의 인스턴스와 연동할 수 있다.
