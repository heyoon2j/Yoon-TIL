# Backup
* 자료를 보호하는 방법 중 하나로 복사본을 만들어 놓고, 장애 발생 시 대체
* 고려 사항
    1) 백업 대상 : 쉽게 설치 가능하거나, 자동으로 생성되는 데이터들은 백업이 불필요
    2) 백업 종류 : 전체 백업, 증분 백업, 차등 백업
    3) 백업 주기 : 얼마나 자주 할지 결정 (ex> 주중 증분 백업, 주말 전체 백업 등)
    4) 백업 매체 : 경제성, 효율성, 신뢰성, 처리 속도, 가용 성을 고려하여 매체 선택
    5) 백업 방법 : tar, dd, dump, cpio, rsync


## 백업 종류
* ref: https://www.techtarget.com/searchdatabackup/tip/Data-backup-types-explained-Full-incremental-differential-and-incremental-forever-backup

### Full Backup (전체 백업)
![FullBackup](../img/FullBackup.png)
* 전체 데이터의 복사해 둔다.
* 장점
    1) 전체 데이터를 복사하기 때문에 최고의 보호 기능을 제공
* 단점
    1) 백업하는데 시간이 오래 걸린다.
    2) 많은 저장 공간이 필요하다.
    3) 비용이 많이 든다.
> 가장 좋은 방법이지만, 비용, 시간 등으로 인해 매일 같이 사용되지 않는다.
</br>


### Incremental Backup (증분 백업)
![IncrementalBackup](../img/IncrementalBackup.png)
* 전체 데이터를 한 번 복사한 뒤, 변경된 데이터만 복사해 둔다.
* 장점
    1) 백업 속도가 빠르다.
    2) Full Backup보다 저장 공간이 많이 필요하지 않다.
* 단점 
    1) 많은 데이터 세트를 순차적으로 복원해야되기 때문에, 복원하는데 시간이 오래 걸린다.
    2) 증분 백업 데이터가 누락되거나 손상된 경우 불완전한 데이터 복구가 발생한다.
</br>


### Differential Backup (차등 백업)
![DifferentialBackup](../img/DifferentalBackup.png)
* 전체 데이터를 한 번 복사한 뒤, 변경된 데이터만 복사해 둔다.
* 증분 백업과의 차이는 마지막 Full Backup 이후에 변경된 모든 데이터가 포함된다.
* 장점
    1) 증분 백업보다 복원 속도가 빠르다.
    2) 차등 백업 데이터는 백업 데이터 세트가 적다.
* 단점
    1) 차등 백업 데이터를 순차적으로 복원해야되기 때문에, 복원하는데 시간이 오래 걸린다.
    2) 증분 백업 데이터가 누락되거나 손상된 경우 불완전한 데이터 복구가 발생한다.
</br>
</br>


## 백업 주기
* Full Backup : 일주일 / 한달 - 1 회
* Incremental Backup or Differential Backup : 1일 - 1회
> 삭제도 생각해야 됨!!!

</br>
</br>


## 백업 매체
* 경제성, 효율성, 신뢰성, 편리성, 처리속도, 가용성 등을 고려
* HDD / SSD / USB / 
</br>
</br>



## 백업 방법
## cpio
* __copy in and out__ 전체 파일 시스템을 복사하는 방법
* 
* 
</br>

### Example
```
# 


# 
find . -depth -print | cpio -pamVd /data2

# 

```
</br>
</br>


## tar
* q
* w
* e
* 
</br>

### Example
```
```
</br>
</br>


## rsync
* q
* w
* e
* 
</br>

### Example
```
```
</br>
</br>



### Reference
* https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=p_rain&logNo=220703521025