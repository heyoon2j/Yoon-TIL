# Database
* 목적에 맞는 데이터를 유기적으로 결합하여 저장한 데이터의 집합
</br>

* 기본 용어
    - OLTP : Online Transaction Processing. 발생한 트랜잭션을 DB가 처리하고 결과를 요청한 Client에게 결과값을 되돌려주는 과정
    - OLAP : Online Analytical Processing. DB에 저장되어 있는 데이터를 분석하고, 데이터 분석을 통해 사용자에게 유의미한 정보를 제공해주는 처리 방법
    - Transaction : 한꺼번에 수행되어야 하는 일련의 연산들. 원자성, 독립성, 지속성, 일관성
    - Character Set : 문자 인코딩 방식
    - Collation : 문자 정렬 기준 
    - Commit : 하나의 트랜잭션이 성공적으로 끝나 일관성 있는 상태임을 의미
    - Rollback : 하나의 트랜잭션 처리가 비정상적으로 종료 되었을 때의 상태임을 의미

* Database 종류
    - 크게 저장 방식에 따라 2가지로 분류할 수 있다.
    1. Database : Row 기반, 하나의 테이블이 가지고 있는 모든 값에 관심 (일반적인 경우에 사용)
    2. Data Warehouse : Column 기반, 특정 항목의 값들에 대해 관신 (Big Data, 분석 분야에 사용)

---
## Database
DBMS (Database Management System). 데이터 베이스를 관리하는 미들웨어 시스템, 데이터 베이스 관리 시스템

### RDBMS (Relational Database Management System)
* Oracle, Mysql, PostgreSQL, SQLite
* 데이터를 Table, Row, Column 형태로 저장하며, 각 테이블이 서로 관계를 가지고 있는 데이터 베이스를 관리
* Insert가 느리지만, 검색 속도가 빠르다. 
    * Login Data

### Non-RDBMS
* MongoDB, Hbase, Cassandra
* 데이터를 Collection, Document, Key-Value 형태로 저장한다.
* 데이터 사이의 관계가 없으므로 복잡성이 작고 많은 데이터의 저장이 가능
* Insert가 빠르지만, 검색 속도가 느리다.
    * Log Data


---
## Schema
* 데이터베이스의 구조와 제약 조건에 관해 전반적인 명세를 기술한 것.

## SQL vs NoSQL (RDBMS vs Non-RDBMS)
1. 데이터 저장 형태
    1) SQL
        * Row, Column 형태로 저장
        * 스키마가 고정되어 있다.
            * 관계 설정으로 인한 Insert가 느리고, 똑같은 데이터를 저장한다고 하면 데이터도 크다 -> 검색 속도가 빠르다. 하지만 Join 등 복잡한 Query로 속도 저하 유발 가능성이 있다 
            * 데이터 중복 X, 데이터의 무결성 보장 -> 데이터 공간 절약
    2) NoSQL
        * Key-Value 형태로 저장(요즘에는 JSON 형식으로 저장)
        * 스키마가 자유롭다.
            * Application에 맞는 스키마로 인해 속도 향상 가능 -> Insert가 빠르다. 대신 검색이 느리다.
            * 데이터 중복 가능 -> 데이터 공간 낭비. 중복 데이터의 경우, 같이 업데이트 필요. 

2. 확장/처리 방식
    1) SQL
        * 수직적 확장 (Scale Up/Down)
        * 기본저으로 하나의 서버가 처리
    2) NoSQL
        * 수평적 확장 (Scale In/Out)
        * 분산처리 시스템으로 데이터를 분산시켜 처리
            * Name Node가 Query를 받아 Data Node에 Query를 던진다.

3. DB 결정
    1) SQL
        * 
        *  
    2) NoSQL





---
## Datawarehouse

