# AWS DMS
* AWS Data Migration Service


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

### The full load of existing data
* Source Data Store에서 트랜잭션 로그에서 변경 사항을 캡처하기 시작한다.
* 특정 Table 전체를 Target Data Store으로 Load 한다.
    * Replication Instance에 임시 파일을 생성하고 Table 관련 정보를 저장(Query)
    * 임시 파일에 저장된 내용을 기반으로 Target에 Load
    * 임시 파일 삭제
</br>

### The application of cached changes
* Full load를 완료한 후, 캡쳐한 변경 사항을 Target Data Store에 적용시킨다(CDC 적용 방식 선택 가능).
* Full load가 완료되면, DMS는 캐시된 변경 사항을 Target Data Store에 적용하기 시작한다.
* 적용이 완료되면, Table은 트랜잭션에서 일관성을 가진다.
</br>

### Ongoing replication
* 모든 변경 사항이 Target Data Store에 적용되면, 트랜잭션으로 일관된 상태가 된다.
* 해당 시점부터 트랜잭션 적용 방식으로써 변경 사항을 적용한다.
> Application of cached changes와 Ongoing replication 단계 모두 CDC 과정으로 동작은 똑같은 거 같고, 시점적인 차이가 있는거 같다.
</br>

### CDC
* CDC 과정은 다음과 같다(The application of cached changes + Ongoing replication)
    1) 데이터베이스 트랜잭션 로그에서 변경 사항을 수집한다(ex> MySQL : binlogs).
    2) 먼저 캡쳐한 변경 사항은 Memory에 저장한다(MemoryKeepTime: 60s / MemoryLimitTotal: 1GiB). 그리고 정해둔 기준을 초과하면 Storage로 Swap한다.
    > Storage로 넘어가면 디스크를 읽는 시간이 메모리에서 읽는 시간보다 크기 때문에 지연시간이 발생할 수 있다.
    > 트랜잭션 로그를 Target에 적용하는데에는 Commit 과정이 들어가기 때문에 오래 걸린다.
* Replication Instance Storage는 주로 진행 중인 복제를 위해 수집되는 작업 로그 및 캐시된 변경 사항에 사용된다.
    * Disk : Full Load Data + 변경 사항(제한 값을 초과한 데이터)
    * Memory : 주요 3개의 Memory Buffer를 사용한다(CDC Memory, Incoming Stream, Outgoing Stream Buffer)
        1) CDC Memory Buffer : 변경 사항 저장
        2) Incoming Stream Buffer : 수신 데이터 스트림 버퍼
        3) Outgoing Stream Buffer : 발신 데이터 스트림 버퍼
* 상황에 맞게 CDC 적용 방법
    * https://aws.amazon.com/ko/premiumsupport/knowledge-center/dms-batch-apply-cdc-replication/
    1) 트랙잭션 적용
    2) 일괄 적용
</br>

### 조심해야 할 점
* AWS DMS는 Minimalist 접근 방식을 취해 Primary Key, Unique Index는 생성하지만, Non-Primary Key, Secondary Index 등 불필요한 것들은 생성하지 않는다.
* Reading form source is paused. Total Storage used by swap files exceeded the limit 1048576000 bytes (Memory 1 GiB를 초과했을 때 나오는 로그)
* [ERROR] duplicate key value violates unique constraint, DETAIL: Key (acc_t_f) = (210000) already exists (마이그레이션 도중 Sequence 객체와 Table Key 값이 차이가 발생해서 나오는 로그. 동기화가 제대로 적용되지 않아서로 이 경우에는 수동으로 동기화시켜야 한다)

