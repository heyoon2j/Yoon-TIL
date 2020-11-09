# Master & Slave Replication
* Replication은 단방향으로 Master가 변경 정보를 Slave가 받아 동기화된다.
 그렇기 때문에 Slave가 변경된다고 Master가 변경되지 않는다!!!
* 서로 동기화를 시키려면 반대 방향으로 Replication 해야 된다.

  
## Replication Setting
### 1. Setting Master Server
* Master Server 설정
    ```
    # MySQL 설정 파일
    $ sudo vi /etc/mysql/mysql.conf.d/mysqld.cnf
    -------------------------------------------------
    server_id = 1
    log_bin = /var/log/mysql/mysql-bin.log
    binlog_do_db = mydb1
    -------------------------------------------------

    # 설정을 적용시키기 위해 MySQL 재시작 
    $ sudo service mysql restart

    # Table 생성
    $ mysql -u root -p
    mysql> GRANT REPLICATION SLAVE ON *.* TO 'root'@'%' IDENTIFIED BY 'rada';
    mysql> FLUSH PRIVILEGES;
    mysql> CREATE DATABASE mydb1;
    mysql> show databases;
    mysql> use mydb1
  
    # 데이터 베이스 백업을 위해 테이블에 LOCK을 걸어둔다
    mysql> FLUSH TABLES WITH READ LOCK;
  
    # Master 의 상태를 확인
    # Positon Number를 기억해 둔다. Slave 설정에 사용해야 한다.
    mysql> SHOW MASTER STATUS;
  
    # mydb1 데이터 베이스를 mydb1.sql 파일로 백업
    $ mysqldump -u root -p --opt mydb1 > mydb1.sql
  
    # 데이터 베이스의 LOCK을 해제
    $ mysql -u root -p
    mysql> use mydb1;
    mysql> UNLOCK TABLES;
    ```

### 2. Setting Slave Server
* Slave Server 설정
    ```
    # mydb1 데이터 베이스 생성
    $ mysql -u root -p
    mysql> CREATE DATABASE mydb1;
    mysql> exit
  
    # mydb1 복원(restore)
    $ mysql -u root -p mydb1 < mydb1.sql
  
    # 설정 파일 변경
    $ sudo vi /etc/mysql/mysql.conf.d/mysqld.cnf
    —————————————————————————————————————
    server_id = 2
    relay-log = /var/log/mysql/mysql-relay-bin.log
    log_bin = /var/log/mysql/mysql-bin.log
    binlog_do_db = mydb1
    —————————————————————————————————————
  
    # mysql 서버 재시작
    $ sudo service mysql restart
    # Replication Setup on Slave
    mysql> CHANGE MASTER TO MASTER_HOST='13.125.213.230',
    MASTER_USER='root', MASTER_PASSWORD='rada', MASTER_LOG_FILE='mysqlbin.
    000001', MASTER_LOG_POS=589;
  
    # 동기화 시작
    mysql> START SLAVE;
  
    # 동기화 상태 확인
    mysql> SHOW SLAVE STATUS\G
    ```

* Slave에 동기화가 깨졌을 경우
    ```
    # Slave에 동기화가 깨졌을때 마스터 데이터베이스의 쿼리 스킵
    mysql> SET GLOBAL SQL_SLAVE_SKIP_COUNTER=1;
  
    # slave 설정 초기화
    mysql> reset slave all;
  
    # 데이터 베이스의 프로세스 리스트를 확인 : master의 업데이트 대기중 확인
    mysql> show processlist;
    ``` 

### 3. 두 개의 데이터 베이스를 서로 Master, Slave로 설정하는 방법
```
# Slave Server의 Slave 중지
mysql> STOP SLAVE;

# Slave Server 계정 생성 ( mysql shell 에서 실행 )
mysql> GRANT REPLICATION SLAVE ON *.* TO 'root'@'%' IDENTIFIED BY ‘rada';

# Slave 서버의 File, Position 확인
mysql> SHOW MASTER STATUS;

# Master 서버 Slave 설정
mysql> CHANGE MASTER TO
MASTER_HOST='13.124.234.223',
MASTER_USER='root',
MASTER_PASSWORD='rada',
MASTER_LOG_FILE='mysql-bin.000002',
MASTER_LOG_POS=589;

# Master Server의 Slave 시작
mysql> START SLAVE;

# Slave Server의 Slave 시작
mysql> START SLAVE;
```


#### Reference
* https://www.bogotobogo.com/DevOps/AWS/aws-MySQL-Replication-Master-Slave.php
* Docker로 할 경우 읽으면 좋을거 같다: https://kamang-it.tistory.com/entry/MySQLReplication%EC%9C%BC%EB%A1%9C-DB-Master-Slave%EA%B5%AC%EC%A1%B0-%EB%A7%8C%EB%93%A4%EC%96%B4%EC%84%9C-%EB%8D%B0%EC%9D%B4%ED%84%B0-%EB%8F%99%EA%B8%B0%ED%99%94-%ED%95%98%EA%B8%B0feat-docker-shell-script



