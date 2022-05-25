# AWS DMS
* AWS Data Migration Service


## Security
* https://docs.aws.amazon.com/dms/latest/userguide/CHAP_Security.html
</br>

### DMS를 사용하는데 필요한 IAM
* 기본적으로 DMS를 사용하려면 Role IAM이 필요하다.
* ```dms-vpc-role```, ```dms-cloudwatch-logs-role```
* 




* Phase 1 : Full load
* Phase 2 : The application of cached changes
* Phase 3 : Ongoing replication




* Full load Phase: Source에서 트랜잭션 로그에서 변경 사항을 캡처하기 시작한다. 특정 Table 전체를 Target으로 Load 한다.
    * Replication Instance에 임시 파일을 생성하고 Table 관련 정보를 저장(Query)
    * 임시 파일에 저장된 내용을 기반으로 Target에 Load
    * 임시 파일 삭제
* Application of cached changes Phase: Full load를 완료한 후, 캡쳐한 변경 사항을 Target에 적용시킨다(CDC 적용 방식 선택 가능).
* Ongoing replication Phase: 모든 변경 사항이 Target에 적용되면, 트랜잭션으로 일관된 상태가 된다. 해당 시점부터 트랜잭션 적용 방식으로써 변경 사항을 적용한다.
> Application of cached changes와 Ongoing replication 단계 모두 CDC 과정으로 동작은 똑같은 거 같고, 시점적인 차이가 있는거 같다.

* CDC 과정은 다음과 같다(The application of cached changes + Ongoing replication)
    * 데이터베이스 트랜잭션 로그에서 변경 사항을 수집한다(ex> MySQL : binlogs).
    * 먼저 캡쳐한 변경 사항은 Memory에 저장한다(MemoryKeepTime: 60s / MemoryLimitTotal: 1GiB). 그리고 정해둔 기준을 초과하면 Storage로 Swap한다.

> Storage로 넘어가면 디스크를 읽는 시간이 메모리에서 읽는 시간보다 크기 때문에 지연시간이 발생할 수 있다.
> 트랜잭션 로그를 Target에 적용하는데에는 Commit 과정이 들어가기 때문에 오래 걸린다.


* Replication Instance Storage는 주로 진행 중인 복제를 위해 수집되는 작업 로그 및 캐시된 변경 사항에 사용된다.
* 주요 3개의 Memory Buffer를 사용한다(CDC Memory, Incoming Stream, Outgoing Stream Buffer)
    * CDC Memory Buffer : 변경 사항 저장
    * Incoming Stream Buffer : 수신 데이터 스트림 버퍼
    * Outgoing Stream Buffer : 발신 데이터 스트림 버퍼

* 상황에 맞게 CDC 적용 방법
    * https://aws.amazon.com/ko/premiumsupport/knowledge-center/dms-batch-apply-cdc-replication/
    1) 트랙잭션 적용
    2) 일괄 적용