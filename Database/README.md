# Database
* 목적에 맞는 데이터를 유기적으로 결합하여 저장한 데이터의 집합

### DBMS
* Database Management System
* 데이터 베이스를 관리하는 미들웨어 시스템, 데이터 베이스 관리 시스템

### RDBMS
* Relational Database Management System
* Oracle, Mysql, PostgreSQL, SQLite
* 데이터를 Table, Row Column 형태로 저장하며, 각 테이블이 서로 관계를 가지고 있는 데이터 베이스를 관리

### Non-RDBMS
* MongoDB, Hbase, Cassandra
* 데이터 사이의 관계가 없으므로 복잡성이 작고 많은 데이터의 저장이 가능

### Schema
* 데이터베이스의 구조와 제약 조건에 관해 전반적인 명세를 기술한 것.


## SQL vs NoSQL (RDBMS vs Non-RDBMS)
1. 데이터 저장 형태
    1) SQL
        * Row, Column 형태로 저장
        * 스키마가 고정되어 있다.
            * Join 등 복잡한 Query로 속도 저하 -> 관계 설정으로 인한 Insert가 느리고, 똑같은 데이터를 저장한다고 하면 데이터도 크다.
            대신 검색 속도가 빠르다.
            * 데이터 중복 X, 데이터의 무결성 보장 -> 데이터 공간 절약
    2) NoSQL
        * Key-Value 형태로 저장(요즘에는 JSON 형식으로 저장)
        * 스키마가 자유롭다.
            * Application에 맞는 스키마로 인해 속도 향상 가능 -> Insert가 빠르다. 대신 검색이 느리다.
            * 데이터 중복 가능 -> 중복 데이터의 경우, 같이 업데이트 필요, 데이터 공간 낭비

2. 확장/처리 방식
    1) SQL
        * 수직적 확장 (Scale Up/Down)
        * 기본저으로 하나의 서버가 처리
    2) NoSQL
        * 수평적 확장 (Scale In/Out)
        * 분산처리 시스템으로 데이터를 분산시켜 처리
            * Name Node가 Query를 받아 Data Node에 Query를 던진다.




#### ETC
* URL vs URI

* redirection
    * 브라우저의 쿠키 또는 캐쉬를 이용해
    
    

table / row / value

collection
document
key-value

Fair Programming 하는바법
C1 ------- S1
(S1에 Session s1을 만듬)
s1|s2

C2 ------- S2
s2|s1