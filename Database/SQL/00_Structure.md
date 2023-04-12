# Structure

## Basic

* DB Server는 크게 Memory/Disk 로 구분할 수 있다. Memory/Disk로 분리함으로써, Disk에 저장 시 에러가 발생해도 Log를 통해 Rollback이 가능해진다.
* WAL(Write Ahead Log) : 가장 중요한 요소 중 하나로, 데이터 파일에 대한 모든 변경 사항은 WAL(InnoDB에서는 redo 로그를 의미)에 기록된다(Query이 기록됨)
* 데이터 업데이트 순서는 다음과 같다.
    1) User Thread(Query 처리하는 쓰레드)에서 Buffer Pool에 원하는 Data Page를 검색. 없는 경우 디스크의 Tablespace에 액세스하여 원하는 Page를 Buffer Pool에 캐싱한다.
    2) Buffer Pool(memory)에서 업데이트 내용(수정한 Page)을 저장
    3) Buffer Pool에 저장되어 있는 내용을 Log Buffer(memory)에 저장
    4) Commit 시 or Log Buffer가 Full인 경우 or 일정 주기마다(1s) Log Buffer에 있는 내용을 Log File(disk, WAL)에 저장한다. 이 때 Checkpoint 발생한다(Check Point가 발생한다는 것은 DB 파일이 새로 써졌다는 의미)
        > CDC는 이를 기준으로 Squence를 확인하여 실행한다(정확하지 않다!!!)
    5) Checkpoint가 발생하면, 먼저 디스크로 flush할 때(테이블에 저장할 때) partial page writes(시스템 장애 등으로 덮씌워지는 경우)로 인한 충돌을 방지하기 위해 Background로 Dubblewrite_buffer에 저장한다.
    6) 그 후에, 디스크에 저장한다.
* RW와 다르게 RO는 Redo Log 작업이 필요가 없기 때문에 같은 성능인 경우 Read에 대해 더 빠를 수 있다.
</br>

### Reference
* https://stackoverflow.com/questions/56823591/mysql-innodb-differences-between-wal-double-write-buffer-log-buffer-redo-log
* https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=parkjy76&logNo=220918956412
* https://qiita.com/yoheiW@github/items/8a5326a516ec4452e774#%E6%9B%B4%E6%96%B0%E5%87%A6%E7%90%86
* https://blog.ex-em.com/1700
</br>
 

## Transaction / REDO / UNDO
* Database Transaction: ACID
    * 한꺼번에 수행되어야 하는 일련의 연산들. 한꺼번에 완료가 되지 않으면 한꺼번에 취소되어야하는 원자성을 가지고 있다.
    * 완료가 되면 __COMMIT__ 호출.
    * 취소가 되거나 문제가 발생하면 __ROLLBACK__ 호출.
* 어떤 작업을 하든지 모두 REDO, UNDO에 기록되며 복구에 사용. 복구 시 차이가 나는데 __REDO는 사용자가 작업한 순서대로 다시 복구__ 되지만, __UNDO는 사용자가 작업한 순서 반대로 복구 진행__ 된다.
* __ROLLBACK__ 시, __UNDO__ 를 이용하여 ROllBACK 진행.
* __UNDO__ 는 __REDO__ 가 복구 가능(이말은 REDO는 ROLLBACK 문도 저장하는 건가?).
</br>
</br>


## 저장 방식
* File : 


## Binary Log vs Redo Log
|        | Redo Log | BinLog |
|--------|------------|----------|
| 출력원 | Engine Level | Server Level |
| 구분 | 물리적 로그 | SQL 문을 기록하는 논리적인 로그 |
| 용도 | Engine의 갱신 정보의 일시적 보존. ACID를 보장. Recovery | DB의 데이터 복원. Replication |
* 각 Log는 CREATE, DROP, ALTER 등과 같은 변경사항이 발생할 때마다 변화된 이벤트를 기록한다.
> 찾아본 결과 전체적이 생성 위치나 특징이 다를 수 있지만, 결국 Rollback과 Replication, Recovery에 사용되는 Log 들이다.
</br>
</br>



## Character Set & Collation
### Character Set
* 문자 저장시, 인코딩 방식
* 일반적으로 다음과 같은 방식 사용
    1) UTF-8
    2) UTF-16
    3) ASCII
    4) EUC-KR
* 데이터를 저장하는 관점
    1) Server-Level
    2) Database-Level
    3) Table-Level
    4) Column-Level
* Server와 Client 간의 관점
    1) character_set_client: Client -> Server로 넘어올 때, Client가 넘겨줄 때의 방식
    2) character_set_connection: Client -> Server로 넘어올 때, Server가 해석하는 방식
    3) character_set_results: Server -> Client로 응답을 넘겨줄 때의 방식

> 인코딩 방식에 대해서 확인할 때, 데이터를 저장하는 관점 4개와 Server-Client 간의 관점 3개 모두 확인해봐야 한다.

### Collation
* 데이터베이스에 저장된 값들을 검색하거나 정렬 등의 작업을 위해 문자들간에 서로 비교할 때 사용하는 규칙들의 집합
* CHAR, VARCHAR, TEXT와 같은 데이터 타입을 가지는 Column에 대해서만 적용된다 (주로 대소문자 구별)

* https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=cambo95&logNo=100106067950

