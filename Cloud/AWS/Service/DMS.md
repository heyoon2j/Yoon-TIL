# AWS DMS
* AWS Data Migration Service


## Security
* https://docs.aws.amazon.com/dms/latest/userguide/CHAP_Security.html
</br>

### DMS를 사용하는데 필요한 IAM
* 기본적으로 DMS를 사용하려면 Role IAM이 필요하다.
* ```dms-vpc-role```, ```dms-cloudwatch-logs-role```




## 동작 과정
### The full load of existing data
* Source Data Store에 저장되어 있는 Table로부터 모든 데이터를 Target Data Store에 로드한다. 
* Full load 중에 해당 Table에 변경 사항이 발생하면 복제 인스턴스에 캐시된다(캐시된 변경 사항).
* Full load 완료.

### The application of cached changes
* Full load가 완료되면, DMS는 캐시된 변경 사항을 Target Data Store에 적용하기 시작한다.
* 적용이 완료되면, Table은 트랜잭션에서 일관성을 가진다.


### Ongoing replication
* 트랜잭션의 백로그를 통해 업데이트 진행(이때 지연시간을 유발할 수 있음)


### 조심해야 할 점
* AWS DMS는 Minimalist 접근 방식을 취해 Primary Key, Unique Index는 생성하지만, Non-Primary Key, Secondary Index 등 불필요한 것들은 생성하지 않는다.
