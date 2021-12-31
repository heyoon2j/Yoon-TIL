# Structure

## Basic
* DB Server는 크게 Memory / Redo Log(Transaction) /  Disk 로 구분할 수 있다.
* Memory에서 명령을 처리하고, 이를 Redo Log에 기록 -> 기록을 이용하여 Disk에 Data 저장
    * Memory, Redo Log / Disk로 분리함으로써, Disk에 저장시  에러가 발생해도 Redo Log를 통해 Rollback이 가능해진다. 
    * RW와 다르게 RO는 
</br>
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


## File

## Binary Log vs Redo Log
* https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=parkjy76&logNo=220918956412
> 찾아본 결과 엔진에 따라 이름이 다르게 부르는 거 같다. 엔진이 달라서 특징이 다를 수 있지만 결국 Rollback과 Replication, Recovery에 사용되는 Log 들이다.

|        | Redo Log | BinLog |
|--------|------------|----------|
| 출력원 | Engine Level | Server Level |
| 구분 | 물리적 로그 | SQL에 대응하는 논리적인 로그 |
| 용도 | Engine의 갱신 정보의 일시적 보존. ACID를 보장. Recovery | DB의 데이터 복원. Replication |

</br>