# AWS DMS
* AWS Data Migration Service
</br>

## Basic
* Replication Instance : Database 마이그레이션을 진행할 서버
* Endpoint : Source, Target을 나타내는 엔드포인트
* Database migration task : 어떻게 마이그렝션 할지에 대한 테스크
</br>


## Security
* https://docs.aws.amazon.com/dms/latest/userguide/CHAP_Security.html
</br>

### DMS를 사용하는데 필요한 IAM
* 기본적으로 DMS를 사용하려면 Role IAM이 필요하다.
* ```dms-vpc-role```, ```dms-cloudwatch-logs-role```
</br>
</br>


## 동작 과정
* Phase 1 : Full load
* Phase 2 : The application of cached changes
* Phase 3 : Ongoing replication
</br>

### Phase 1. The full load of existing data
1) Source Data Store에서 특정 Table에 대한 트랜잭션 로그 변경 사항을 캡처하기 시작한다. 변경 사항은 Replication Instance에 캐시된다.
2) 특정 Table 전체를 Target Data Store로 Load 한다.
    * Replication Instance Storage(디스크)에 임시 파일을 생성하고 Table 관련 정보를 저장(Query)
    * 임시 파일에 저장된 내용을 기반으로 Target에 Load
    * 임시 파일 삭제
</br>

### Phase 2. The application of cached changes
* Full load를 완료한 후, 캡쳐한 변경 사항을 Target Data Store에 적용시킨다(CDC 적용 방식 선택 가능).
* 적용이 완료되면, Table은 트랜잭션에서 일관성을 가진다.
</br>

### Phase 3. Ongoing replication
* 모든 변경 사항이 Target Data Store에 적용되면, 트랜잭션으로 일관된 상태가 된다.
* 해당 시점부터 트랜잭션 적용 방식으로써 변경 사항을 적용한다.
> Application of cached changes와 Ongoing replication 단계 모두 CDC 과정으로 동작은 똑같은 거 같고, 시점적인 차이가 있는거 같다.

</br>
</br>

### CDC
* CDC 과정은 다음과 같다(The application of cached changes + Ongoing replication)
    1) 데이터베이스 트랜잭션 로그에서 변경 사항을 수집한다(ex> MySQL : binlogs).
    2) 먼저 캡쳐한 변경 사항은 Memory에 저장한다(MemoryKeepTime: 60s / MemoryLimitTotal: 1GiB). 그리고 정해둔 기준을 초과하면 Storage로 Swap한다.
    > Storage로 넘어가면 디스크를 읽는 시간이 메모리에서 읽는 시간보다 크기 때문에 지연시간이 발생할 수 있다.

    > 트랜잭션 로그를 Target에 적용하는데에는 Commit 과정이 들어가기 때문에 오래 걸린다.
    
* Replication Instance Storage는 주로 진행 중인 복제를 위해 수집되는 작업 로그 및 캐시된 변경 사항에 사용된다.
    * Disk : Full Load Data(임시) + 변경 사항(제한 값을 초과한 데이터) + DMS 작업 Log
    * Memory : 주요 3개의 Memory Buffer를 사용한다(Sorter Memory, Incoming Stream, Outgoing Stream Buffer)
        1) Sorter Buffer : 커밋 순서 정렬
        2) Incoming Stream Buffer : 수신 데이터 스트림 버퍼
        3) Outgoing Stream Buffer : 발신 데이터 스트림 버퍼
* 상황에 맞게 CDC 적용 방법
    * https://aws.amazon.com/ko/premiumsupport/knowledge-center/dms-batch-apply-cdc-replication/
    1) 트랜잭션 적용
    2) 일괄 적용
</br>
</br>


## Task table preparation mode
* Do nothing : Target에 테이블이 미리 생성되어 있으면 다른 동작을 하지 않는다. 하지만 테이블이 비어 있지 않으면 충돌이 발생하여 DMS 작업 오류가 발생할 수 있다. 테이블이 생성되어 있지 않으면 생성하고 작업을 진행한다.
    > "Do nothing"은 대상 테이블이 채워져 있는 경우, CDCD 전용 작업에 적합
* Drop tables on target : Target에서 테이블을 삭제 하고 다시 생성한다. AWS DMS는 데이터(테이블, 기본 키, 일부 경우 고유 인덱스 등)를 효율적으로 마이그레이션하는 데 필요한 객체만 생성한다.
* Truncate : Target에서 테이블에 있는 데이터를 자른다. 테이블이 생성되어 있지 않으면 생성한다.
    > 대상 스키마가 미리 생성된 전체 로드 또는 전체 로드와 CDC 마이그레이션에 적합

</br>

## LOB column settingsInfo
* Don't include LOB columns : LOB Colume은 제외 시킨다.
* Full LOB mode : LOB 데이터를 Chunk 사이즈로 분할하여 마이그레이션한다. (속도가 느려진다)
* Limited LOB mode : 최대 사이즈를 정하고, 최대 사이즈를 넘으면 LOB 사이즈를 잘라서 마이그레이션한다. (누가 쓰지?)

</br>


### 조심해야 할 점
* AWS DMS는 Minimalist 접근 방식을 취해 Primary Key, Unique Index는 생성하지만, Non-Primary Key, Secondary Index 등 불필요한 것들은 생성하지 않는다.
* DMS는 소스 데이터베이스 스키마를 대상 데이터베이스와 호환되는 형식으로 자동 변환을 할 수 있는데, 자동 변환한지 않는 모든 개체는 표시되게 되며, 해당 개체들은 수동으로 변환해야 한다.
* Reading form source is paused. Total Storage used by swap files exceeded the limit 1048576000 bytes (Memory 1 GiB를 초과했을 때 나오는 로그)
* [ERROR] duplicate key value violates unique constraint, DETAIL: Key (acc_t_f) = (210000) already exists (마이그레이션 도중 Sequence 객체와 Table Key 값이 차이가 발생해서 나오는 로그. 동기화가 제대로 적용되지 않아서로 이 경우에는 수동으로 동기화시켜야 한다)
</br>
</br>


## Migration 과정
1. 전략 빌드
    - 어떤 방법을 사용할지 도구 채택 (Migration Tool)
        1) 사용자 권한
            - 기본 : Superuser 권한
            - Only Load Dtaa : SELECT 권한
        2) 스키마 복제 방법 (소스 객체, 테이블, 인덱스, 뷰, 트리거 및 기타 시스템 객체를 대상 데이터 정의 언어(DDL) 형식으로 변환)
            - AWS SCT (Schema Conversion Tool)
            - 수동 복제
        3) 데이터 복제 방법
            - AWS DMS (Data Migartioin Service)
            - 수동 복제
    - 제약 사항 확인
        * 도구 채택에 따른 제약 사항 
        * DB 간의 데이터 타입 비교 및 변환 방법
            1) 지원되지 않는 타입
            2) 정밀도 차이
            3) 마이그레이션 도구에 따른 데이터 변환 방법 (ex> AWS DMS: bool 타입 --> varchar(5) 기본 변환)
    - 검증 방법
        1) Source와 Target의 전체 Row Count 비교
        2) 1,000개 단위로 Row를 Sampling하고 Source와 Target에 대한 Row Diff x 만족할 때 까지 반복
        3) 서비스의 Connection을 Target 데이터베이스로 변경한 후 Unit 테스트 진행
        4) 전체 QA 및 통합 테스트 진행
2. Source Scheme --> Target Scheme 변환 작업 (Scheme Migration)
    - AWS SCT Installer 설치 : https://docs.aws.amazon.com/dms/latest/userguide/CHAP_GettingStarted.SCT.html
    - 생성 방법 : https://docs.aws.amazon.com/dms/latest/userguide/CHAP_GettingStarted.SCT.html
3. DMS Replication Instance 생성 (Server Spec)
    - Instance Class 
    - Storage Size (Only gp2 type)
        > IOPS의 영향을 받기 때문에 gp2 type이 사용된다!
    - Multi AZ (Active - Standby) : No Multi AZ
4. Database 작업 (Source DB)
    - DB 계정 및 권한 생성 (for Load & CDC / Master 계정을 사용하지 않는 경우)
     	1) 마이그레이션 용 계정 생성
     	2) rds_superuser 역할 할당 : 완전 Admin은 아니고 AWS에서 제공할 수 있는 최고 권한일거 같다.
     	3) rds_replication 역할 할당 :  논리적 슬롯을 관리하고 논리적 슬롯을 사용하여 데이터를 스트리밍할 수 있는 권한
    - DDL Capture를 위한 아티펙트 생성 (Master 계정은 자동으로 생성)
       	1) Table 생성
       	2) Function 생성
       	3) Trigger 생성
    - DB 설정 (Parameter/Config)
        1) rds.logical_replication_slots = 1 : 활성화
        2) wal_level = logical
        3) max_reaplication_slots > 1 : Task 갯수만큼 생성 / 추후 삭제 필요
        4) max_wal_senders > 1 : Task 갯수만큼 생성 / 동시 작업 개수
        5) wal_sender_timeout > 10,000ms (10s) && < 5m : 복제 작업이 수행되지 않으면 삭제
        6) max_worker_processes > max_logical_replication_workers + autovacuum_max_workers + max_parallel_workers : max_worker_processes 는 성능에 영향을 크게 주므로 모니터링이 필수
        7) max_slot_wal_keep_size : 기본 값이 아닌 값으로 설정하는 것이 , 복제 슬롯의 restart_lsn이 현재 LSN보다 
5. Database 작업 (Target DB) : 테이블 단위로 복사 진행되며 한번에 하나씩 각 테이블을 로드!
    - DB 계정 및 권한 생성 (for Load & CDC)
    - Trigger 비활성화
        1) 수동으로 바활성화
        2) session_replication_role = replica 변경
        - Foreign Key 비활성화


6. Endpoint 생성 (마이그레이션 도구에 따른 설정) 
    - Endpoint Option 적용
7. Task 설정
    - Migration Type 지정
    - 
8. Task 수행
9. (CDC 수행 시), CDC 적용 (트랜잭션 적용 or 일괄 적용)


10. 제약 사항 처리 (테이블 및 프라이머리 키 생성을 포함하여 기본 스키마 마이그레이션을 지원 / 그 외는 수동처리)
    * 보조 인덱스, 외래 키, 사용자 계정 수동으로 생성
    * 
    * 자동으로 변환되지 않는 모든 개체를 수동으로 변환
        - 지원되지 않는 데이터 형식
        - 정밀도를 가진 데이터 형식
        - (CDC 기준) 테이블에 프라이머리 키가 없는 경우, 미리 쓰기 로그(WAL)에 데이터베이스 행의 이전 이미지가 포함되지 않기 때문에 DMS는 테이블을 업데이트할 수 없다.
    * 
    * AWS DMS는 통합 기능으로 생성된 고유 인덱스가 있는 테이블에 대한 복제를 지원
    * 테이블에서 시퀀스를 사용하는 경우 NEXTVAL 소스 데이터베이스에서 복제를 중지한 후 대상 데이터베이스의 각 시퀀스에 대한 값을 업데이트합니다. AWS DMS는 소스 데이터베이스에서 데이터를 복사하지만 진행 중인 복제 중에는 시퀀스를 대상으로 마이그레이션하지 않습니다.
11. 성능 개선을 위한 처리
    - 소스의 리소스 가용성
    - 가용 네트워크 처리량
    - 복제 서버의 리소스 용량
    - 대상이 변경 사항을 수집하는 능력
    - 소스 데이터의 형식 및 분포
    - 마이그레이션할 객체의 수
12. Database 아티펙트 제거 및 확인
    ```
    drop event trigger awsdms_intercept_ddl;

    drop function {AmazonRDSMigration}.awsdms_intercept_ddl()
    drop table {AmazonRDSMigration}.awsdms_ddl_audit
    drop schema {AmazonRDSMigration}
    ```
13. 데이터 검증







### Example : From PostgreSQL To PostgreSQL
Size : 17 TiB


https://blog.banksalad.com/tech/dms/

1. 사용자가 비교적 적을 야간에 서비스 점검을 진행하여 모든 트래픽을 차단 (데이터 변경 방지)
2. Source와 Target의 전체 Row Count 비교
3. 1,000개 단위로 Row를 Sampling하고 Source와 Target에 대한 Row Diff x 만족할 때 까지 반복
4. 서비스의 Connection을 Target 데이터베이스로 변경한 후 Unit 테스트 진행
5. 전체 QA 및 통합 테스트 진행