# Migration


- Dabase 생성/설치
- DB Drop
- SCT Schema 복제
    - Trigger Disable
    - 
    - FK 


1. 전략 빌드
    - Migration Tool
        1) 사용자 권한 : Superuser 권한
        2) 스키마 복제 방법 : AWS SCT (Schema Conversion Tool)
        3) 데이터 복제 방법 : AWS DMS (Data Migartioin Service)
    - DB 간의 데이터 타입 비교 및 변환 방법
        1) 지원되지 않는 타입
        2) 정밀도 차이
        3) 마이그레이션 도구에 따른 데이터 변환 방법 (ex> AWS DMS: bool 타입 --> varchar(5) 기본 변환)
    - 검증 방법
        1) DBA 진행
2. Source Scheme --> Target Scheme 변환 작업 (Scheme Migration)
    - AWS SCT Installer 설치 : https://docs.aws.amazon.com/dms/latest/userguide/CHAP_GettingStarted.SCT.html
    - 생성 방법 : https://docs.aws.amazon.com/dms/latest/userguide/CHAP_GettingStarted.SCT.html
3. DMS Replication Instance 생성 (Server Spec)
    - Instance Class 
    - Storage Size (Only gp2 type)
        > IOPS의 영향을 받기 때문에 gp2 type이 사용된다!
    - Multi AZ (Active - Standby) : No Multi AZ
4. Database 작업 (Source DB)
    - DB 계정 및 권한 생성 (for Load & CDC) : 마스터 계정 사용
     	1) 마이그레이션 용 계정 (Master 계정을 사용하지 않는 경우)
     	2) rds_superuser 역할 (Master 계정을 사용하지 않는 경우)
     	3) rds_replication 역할 (Master 계정을 사용하지 않는 경우)
    - DDL Capture를 위한 아티펙트 생성 (Master 계정은 자동으로 생성)
       	1) Table 생성
       	2) Function 생성
       	3) Trigger 생성
    - DB 설정 (Parameter/Config)
5. Database 작업 (Target DB)
    - DB 계정 및 권한 생성 (for Load & CDC)
    - 

6. Endpoint 생성 (마이그레이션 도구에 따른 설정) 
    - Endpoint Option 적용
7. Task 설정
    - Migration Type 지정
    - 
8. Task 수행



1. Source Scheme --> Target Scheme 변환(Scheme Migration) 작업
    - AWS SCT Installer 설치 (Schema Conversion Tool) : https://docs.aws.amazon.com/dms/latest/userguide/CHAP_GettingStarted.SCT.html
    - 생성 방법 : https://docs.aws.amazon.com/dms/latest/userguide/CHAP_GettingStarted.SCT.html

2. DMS Replication Instance 생성
    - Instance Class : r5.
    - Storage Size : 100 GIB
    - Multi AZ (Active - Standby) : No Multi AZ

3. Database 작업
(Source)
    - DB 계정 및 권한 생성 (for Load & CDC)
     	1) 마이그레이션 용 계정 (Master 계정을 사용하지 않는 경우)
     	2) rds_superuser 역할 (Master 계정을 사용하지 않는 경우)
     	3) rds_replication 역할 (Master 계정을 사용하지 않는 경우)
    - DDL Capture를 위한 아티펙트 생성 (Master 계정은 자동으로 생성 - 코드 작성)
       	1) Table 생성
       	2) Function 생성
       	3) Trigger 생성
    - Parameter/Config 파일 (On-premise 인 경우)
	1) pg_hba.conf : 복제 소켓 활성화
	2) postgresql.conf : 기본 설정
		- wal_level = logical				// 논리적 복사를 위해
		- max_replication_slots = 2			// (Task 수) WAL Log 파일이 쌓이는 슬롯
		-  max_wal_senders = 2			// (Task 수) 동시에 실행할 수 있는 Task 수
		- wal_sender_timeout = 0 			// Idle Timeout, 기본값 : 60000ms (== 60s) / DMS 사용시는 0으로 설정 가능 (Timeout이 없어짐), DMS 동작에는 기본 10000ms가 필요 (5분 미만)
		- max_wal_size = 
		- max_connection = 수정 X			// 최대 연결 갯수 
		- max_worker_processes  >= max_logical_replication_workers + autovacuum_max_workers, max_parallel_workers
		-  idle_in_transaction_session_timeout = -1		// 사용하는 경우 DMS 작업 중 종료될 수 있다.

		- rds.logical_replication  = 1


(Target)
    - DB 계정 및 권한 생성 (for Load & CDC)
	1) 마이그레이션 용 계정 (Master 계정을 사용하지 않는 경우)
	2) 
    - Parameter/Config 수정
    - Trigger Disable : session_replication_role=replica



4. Endpoint 생성 (마이그레이션 도구에 따른 설정) 
    - Source Endpoint Option 설정
	1) CaptureDDLs  = true
	2) fetchCacheSize = 10000
	3) MapBooleanAsBoolean
	4) MapJsonbAsClob
	5) MapLongVarcharAs
	6) MapUnboundedNumericAsString = 정밀도를 위해 String으로 변환 후 다시 숫자로 변환
	7) (기존 CDC 를 사용하고 싶을 때) SlotName = xxxx 

    - Target Endpoint Option 설정
	1) 모든 트리거를 임시로 비활성화
	2) session_replication_role=replica Parameter 수정

	3) 대상 테이블 준비 모드를 DO_NOTHING 설정
	4) 
	5) 



5. Task 설정
    - Migration Mode 지정 : Full Load / Full Load + CDC / CDC
    - 
    - Table Mapping
    - 
    - 필터 적용
    - 



6. Task 수행
    - 1) 마이그레이션 전 테스크 평가 실행
    - 
    - 
	1) Migration 전 테스크 평가 실행
	2) 
	3) 데이터 유효성 검사


7. (CDC 수행 시), CDC 적용 (트랜잭션 적용 or 일괄 적용)


8. 자동으로 변환되지 않는 모든 개체를 수동으로 변환
    - 지원되지 않는 데이터 형식
    - 정밀도를 가진 데이터 형식
    - (CDC 기준) 테이블에 프라이머리 키가 없는 경우, 미리 쓰기 로그(WAL)에 데이터베이스 행의 이전 이미지가 포함되지 않기 때문에 DMS는 테이블을 업데이트할 수 없다.
9. Database 아티펙트 제거 및 확인
    ```
    drop event trigger awsdms_intercept_ddl;

    drop function {AmazonRDSMigration}.awsdms_intercept_ddl()
    drop table {AmazonRDSMigration}.awsdms_ddl_audit
    drop schema {AmazonRDSMigration}
    ```
10. 데이터 검증




AWS DMS는 프라이머리 키가 있는 PostgreSQL 테이블의 변경 데이터 캡처(CDC)를 지원!
그렇다는 거는 Primary Key가 없는 경우에는 테이블 업데이트가 불가능하다는 의미






하지만 PostgreSQL에서는 트리거를 사용하여 외래 키(참조 무결성 제약)가 구현




테이블에 프라이머리 키가 없는 경우, 미리 쓰기 로그(WAL)에 데이터베이스 행의 이전 이미지가 포함되지 않습니다. 이 경우 DMS는 테이블을 업데이트할 수 없습니다. 


* PostgreSQL NUMERIC(p,s) 데이터 형식이 정밀도와 스케일을 지정하지 않는 경우도 있습니다. DMS 버전 3.4.2 이하 버전에서 DMS는 기본적으로 정밀도 28과 스케일 6인 NUMERIC(28,6)을 사용합니다. 예를 들어 소스의 0.611111104488373 값은 PostgreSQL 대상에서 0.611111로 변환됩니다.
* ARRAY 데이터 형식이 있는 테이블에는 프라이머리 키가 있어야 합니다. 프라이머리 키가 없는 ARRAY 데이터 형식이 있는 테이블은 전체 로드 중에 일시 중단됩니다.



#### 대상으로 사용하고 있는 경우


AWS Database Migration Service에서 PostgreSQL을 대상으로 사용할 때 적용되는 제한 사항

다음 제한 사항은 AWS DMS에서 PostgreSQL 데이터베이스를 대상으로서 사용할 때 적용됩니다.
* 이기종 마이그레이션의 경우 내부적으로 JSON 데이터 형식이 네이티브 CLOB 데이터 형식으로 변환됩니다.
* Oracle을 PostgreSQL로 마이그레이션 하는 경우, Oracle의 열에 NULL 문자(hex value U+0000)가 포함되어 있다면 AWS DMS는 NULL 문자를 공백(hex value U+0020)으로 변환합니다. 이는 PostgreSQL 제한 사항 때문입니다.
* AWS DMS는 coalesce 함수로 만든 고유 인덱스가 있는 테이블로의 복제를 지원하지 않습니다.
* 테이블에서 시퀀스를 사용하는 경우, 소스 데이터베이스에서 복제를 중지한 후 대상 데이터베이스의 각 시퀀스에 대한 NEXTVAL 값을 업데이트하십시오. AWS DMS는 소스 데이터베이스의 데이터를 복사하지만 복제가 진행 중인 동안에는 시퀀스를 대상으로 마이그레이션하지 않습니다.



## Information
* DDL (Data Definiation Language) : 데이터 정의어, 데이터의 규격/골격을 정의하는데 사용
    - CREATE : Object 생성
    - ALTER : Object 변환/수정
    - DROP : Object 삭제
    - TRUNCATE : Object 초기화 / Column은 그래로 두고, Row 데이터만 삭격
* DML (Data Manipulation Language) : 데이터 조작어
    - SELECT : 데이터 조회
    - INSERT : 데이터 삽입
    - UPDATE : 업데이트
    - DELETE : 삭제
* DCL (Data Control Language) : Object에 대한 접근 권한 정의하는데 사용
    - GRANT : 사용자 권한 승인
    - REVOKE : 사용자 권한 취소
* TCL (Transaction Control Language) : 트랜잭션을 제어하는데 사용
    - COMMIT : 트랜잭션 작업 저장
    - ROLLBACK : 트랜잭션 작업 복구
    - SAVEPOINT : 특정 시점까지 지정
* Other
    * DELETE FROM TABLE vs TRUNCATE TABLE
    * DISTINCT 대신 GROUP BY를 사용하자


* Storage Object :
    - Database : Schema 집합
    - Schema : Table + Foreign tables + Views + Collations + Operators + Rules + FTS Configurations + FTS Dictionaries + Functions + Trigger functions + Procedures + Sequences + User defined types + Domains + Materialized Views + Aggregates
    - Table
    - Constraint
    - Sequence
* Code Object : 
    - View
    - Procedure
    - Function




* WAL Level
    - replica : For read-only queries on a standby server
    - minimal : The inforamtion to recover from a crash or immediate shut-down
    - logical : 논리적 디코딩을 위한 로깅 데이터
* LSN : Log Sequence Number : 예전에 마이그레이션할때 LSN이 달라서 복구가 정상적으로 수행되지 않음

* 



### Object
* OID : Objec ID, 객체 식별자
* 



### Issue
- Banksalad's DMS story : https://blog.banksalad.com/tech/dms/
- NDS's DMS story : https://tech.cloud.nongshim.co.kr/2020/02/12/aws-migration-%EC%82%AC%EB%A1%80-%EC%86%8C%EA%B0%9C-1-%EA%B5%AD%EB%82%B4-n%EC%82%AC/
- 
- https://kimdubi.github.io/postgresql/pg_slot_issue/