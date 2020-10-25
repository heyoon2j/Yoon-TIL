#SQL
## Database Modeling
1. 개념적 모델링
    * 업무 분석을 통해 핵심 데이터의 집합을 정의
2. 논리적 모델링
    * 개념적 모델링을 상세화하는 과정
    * 상세하게 분리한다.
3. 물리적 모델링
    * 논리적 모델링 DBMS에 추가하기 위해 구체화 되는 과정
 
## Engine 종료
1. InnoDB - Row 단위로 락이 걸림, 속도가 느림
- 요즘에는 거의 InnoDB 사용

2. MyISM - Table 단위로 락이걸림, 속도가 빠름
data Type이 TEXT 256자 이상

HIbernate
MyBatis

## SQL 문 종류
1. DML (Data Manipulation Language)
    * 데이터 조작어
    * 데이터 검색, 삽입, 수정, 삭제 등
    * SELECT, INSERT, UPDATE, DELETE
    * 트랜잭션이 발생하는 SQL문
2. DDL (Data Definition Language)
    * 데이터 정의어
    * 데이터 베이스, 테이블, 뷰, 인덱스 등의 데이터 베이스 개체를 생성, 삭제, 변경에 사용
    * CREATE, DROP, ALTER, TRUNCATE
3. DCL (Data Control Language)
    * 데이터 제어어
    * 사용자의 권한을 부여하거나 빼앗을 때 사용
    * GRUNT, REVORKE, DENY

#### 트랙잭션 Transaction

* USE 구문
    * TABLE 섵개
```
USE table_name;
```

* SELECT 구문
    * 데이터 검색
```
SELECT select_expr
    [FROM table_references]
    []
    []
    []
    [];

ex>
SELECT code, name
    FROM county;
``` 

## SSH 접속 방법
* SSH : Secure Shell. 보안 채널을 형성하여, 안전한 데이터 통신을 하게 해주는 프로그램 또는 프로토콜
* SSH 접속을 하기 위해서는 아래와 같은 조건을 만족해야 한다.
    1. 22번 TCP 포트가 Open되어 있어야 한다.
    2. 접속할 Server에 **SSH Server** 프로그램이 설치 및 실행되고 있어야 한다.
    3. SSH 프로토콜로 접속하기 위해서는 **SSH Client**가 있어야 한다.

### Install SSH Server
1. Ubuntu

2. CentOS


## MySQL 접속






