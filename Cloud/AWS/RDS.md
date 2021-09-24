# Amazon RDS
* 관계형 데이터베이스 서비스
* DB 인스턴스를 기준으로 시간을 동기화하는데 NTP를 사용
</br>


## DB Instance Class
* EC2
</br>


## DB Instance Storage
* EBS
</br>


## Processor
* 워크로드에 맞게 CPU와 Thread 수를 제어할 수 있다.
* 단 Oracle DB의 인스턴스만 제어할 수 있다.
* https://docs.aws.amazon.com/AmazonRDS/latest/UserGuide/Concepts.DBInstanceClass.html
</br>

## RDS DB Instance 수정
* RDS DB Instacne 구성 설정을 변경할 수 있다.
* 수정 사항은 즉시 적용도 가능하고, 관리 기간을 연기할 수 있다. 일부 목록은 재부팅해야 된다.
* 변경 가능 목록
1. 스토리지 추가
    * 중단 X
    * 늘릴 수 있으나, 줄일 수는 없다
    * 최적화하는데 몇시간이 걸릴 수 있다
2. DB 인스턴스 클래스 변경
3. 다중 AZ 배포
4. 읽기 전용 복제본
    * 단일 AZ인 경우, DB 스냅샷 생성시 잠시 I/O가 중단된다
    * 
</br>
</br>


## High Availability 
### 다중 AZ 배포
* 다중 AZ 배포에서 Amazon RDS는 다른 가용 영역에 __동기식 대기 복제본을__ 자동으로 프로비저닝하고 유지 관리한다.

### 읽기 전용 복제본
* DB 엔진의 기본 제공 복제 기능을 사용하여 원본 DB 인스턴스에서 읽기 전용 복제본이라는 특수한 유형의 DB 인스턴스를 생성.
* DB 인스턴스에 대한 업데이트는 읽기 전용 복제본에 비동기식으로 복사된다.


## DB Backing up & Restoring
### Multi-AZ
* 고가용성을 위한 다중 AZ
* 다중 AZ를 통해 DB 인스턴스에 대한 고가용성 및 장애 조치 지원을 제공
* 

</br>


### Read Replicas
* 읽기 전용 복제


</br>
</br>




# Amazon DynamoDB
* 비관계형 데이터베이스 서비스
* 