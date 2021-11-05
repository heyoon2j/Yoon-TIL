# DB Backup
* crontab을 이용하여 Backup을 주기적으로 진행하게 한다.

## Crontab
## 1. Setting
### time zone 변경
* 기본적으로 Linux는 한국 시간이 아니기 때문에 변경이 필요하다.
* ```timedatectl``` 을 이용하며, 방법은 아래와 같다.
    * 현재 사용 시간 확인
    ```
    $ timedatectl
    ```
    
    * 사용하는 파일 심볼릭 링크 확인
    ``` 
    # 일반적으로 Etc/UTC를 가리키고 있을 것이다.
    $ ls -l /etc/localtime
    ```

    * 사용할 수 있는 타임존 확인
    ```
    $ timedatectl list-timezones | grep Asia
    ```

    * 타임존 변경
    ```
    # 변경하게 되면 자동으로 /etc/localtime의 링크를 변경시켜준다.
    # /etc/localtime : Local timezone configuration file
    $ sudo timedatectl set-timezone Asia/Seoul
    ``` 



## 2. Crontab 명령어
### 2.1. crontab 명령 작성
* crontab 작성, 처음에는 editor를 설정하게 된다.
* 명령어
    ```
    $ crontab -e
    ```

### 2.2. Check list
* 스케줄러 리스트 확인
* 명령어
    ```
    $ crontab -l
    ```

## 3. crontab 작성 방법
* 기본 작성 방법은 다음과 같다.
    * "*" 의 의미는 순서대로 분(0-59) 시간(0-23) 일(1-31) 월(1-12) 요일(0-7) 을 의미한다.
    * 요일의 0, 7은 일요일을 의미한다.
    ```
    # 기본
    *         *          *         *        * {command}
    분(0-59)  시간(0-23)  일(1-31)  월(1-12)  요일(0-7)
    ```

* Example
    ```
    # 매분 실행
    *   *   *   *   * ls -al
  
    # 2분 간격으로 실행
    */2 *   *   *   * python3 /home/ubuntu/time.py >> time.txt
  
    # 매시 10분에 실행
    10  *   *   *   * ls -al
    
    # 매시 10분과 20분에 실행
    10,20   *   *   *   * cat text
  
    # 매일 5시 10분과 20분에 실행
    10,20 5 * * * python3 /home/ubuntu/time.py >> time.txt
  
    # 일요일 5시 10분과 20분에 실행
    10,20 5 * * 0 python3 /home/ubuntu/time.py >> time.txt
  
    # 매일 5시에서 10시까지 5분마다 실행
    */5 5-10 * * 0 python3 /home/ubuntu/time.py >> time.txt
    ```

## 4. Crontab Log
### 4.1. Log 확인
* 아래의 명령으로 crontab의 시스템 로그를 확인할 수 있다.
    ```
    $ grep CRON /var/log/syslog
    ```

### 4.2. Log 메일로 보내는 방법
* mailutils를 이용
* https://jiheahihi.tistory.com/99

### 4.3. 다른 파일에 Logging 하는 방법
```
$ crontab -e

0 0 30 * * ~/test.sh >> ~/test.log 2>&1
```
* 2>&1 : 2(표준 에러)를 1로 출력하겠다는 의미이다.
* 1>/dev/null : /dev/null은 널 장치로 1(표준 출력)을 버리겠다는 의미이다.


## 5. ETC
* Crontab에서는 .bash_profile이 실행되지 않기 때문에 pyenv 환경이 적용되지 않는다.
    * 그렇기 때문에 사용하기 위해서는 시스템 변수로 적용하거나, crontab 파일에 Path를 써줘야 된다.
</br>
</br>
        
# Backup
## 1. Backup 종류
## 1.1. Hot Backup
* 데이터베이스를 중지하지 않은 상태로 데이터 백업
    * Oracle DBMS의 대표적인 방법은 "Begin Backup", "End Backup"
    * MySQL의 대표적인 방법은 "mysqlbackup"
    * Open Source 백업 솔루션으로 Percona XtraBackup이 있다.
* Hot Backup은 백업하는 동안 데이터가 변경되는 경우, 완전한 백업이 안 될 수 있다.
    * Hot Backup 시, DB의 가용성을 지켜야하기 때문에 변경 사항들을 **Redo Log**에 쌓았다가 Backup이 끝나면 Data File에 내려쓰는 구조로 동작한다. 그렇기 때문에 Archive Log의 사용은 필수다.
    * 주의해야 될 것은 트랜잭션이 많은 경우, 로그가 엄청 쌓여 느려질 수 있기 때문에 그런 시간대는 피해야 된다.
* __TABLESPACE__ 단위로 백업 진행
</br>
</br>

## 1.2. Cold Backup
* 데이터베이스를 중지한 상태로 데이터 백업
* 안정적으로 백업이 가능하다.
* 모든 데이터 백업 가능
</br>
</br>

## 1.3. Logical Backup
* Logical Backup은 SQL문으로 백업한다(.sql 파일로 저장) 
* **장점**
    * SQL 문으로 저장하기 때문에 디스크 용량은 적게 사용
    * SQL 문으로 저장하기 때문에 서버 OS 호환이 잘 됨
    * 복원 작업이 수월하며, Physical Backup에 비해 복원시 데이터 손상을 막아주고 문제 발생 시 원인 파악 및 해결이 수월해 진다.
* **단점**
    * SQL문은 DB에서 실질적으로 바이너리로 돌아가기 때문에, 바이너리에서 SQL문으로의 변환 작업이 필요해서 속도가 느리다.
    * 작업시 시스템 자원을 많이 사용.
    * 부동 소수점 데이터의 백업의 경우 정확성을 잃을 수 있다.
</br>
</br>

### Logical Backup 방법
* MySQL: "mysqldump" 이용
    ```
    # mysqldump -u root -p{password} {db_name} > {file_name}
    $ mysqldump -u root -prada test > test_backup.sql
    ```

## 1.4. Physical Backup
* 파일 자체를 백업(바이너리 형태)
* Data File, Control File, Archive redo log File
* **장점**
    * 바이너리 형태로 저장하기 때문에 변환할 필요가 없어, 속도가 빠르다.
    * 작업시 시스템 자원을 적게 사용
* **단점**
    * 바이너리로 저장하기 때문에 디스크 용량을 많이 사용(그냥 파일 자체를 저장).
    * 파일 시스템에 영향을 받기 때문에 서버 OS 호환이 잘 안 될 수 있다.
    * 그렇기 때문에 백업해야되는 양이 너무 크면 어쩔 수 없이 physical, 그렇지 않으면 안정적인 Logical이 좋다.
</br>
</br>

## 2. Backup 하는 방법
* MySQL: https://dev.mysql.com/doc/mysql-backup-excerpt/5.6/en/innodb-backup.html
</br>

## 2.1. Hot Logical Backup
* InnoDB Table만 mysqldump로 Hot Backup 가능!
    * https://dev.mysql.com/doc/mysql-backup-excerpt/5.6/en/backup-methods.html
    * https://dev.mysql.com/doc/mysql-backup-excerpt/5.6/en/backup-policy.html
* MyISAM 테이블이 있는 경우, --lock-tables를 이용하여 Table을 잠그고 해야된다.
    * https://dev.mysql.com/doc/refman/5.6/en/mysqldump.html#option_mysqldump_lock-tables
1. Backup shell script 작성(backup.sh)
    ```
    # backup 파일을 저장할 스크립트 작성
    $ mkdir ~/backup

    $ vi backup.sh
    #!/bin/bash

    BD='date +%Y%m%d_%H%M --date=today'
    FILE=${BD}.sql

    cd backup
    # 기존 Logical Backup, mysqldump -u root -prada test > $FILE
    # Hot으로 하기 위해서는 옵션을 붙여줘야 된다. --single-transaction or --lock-tables
    mysqldump --master-data --single-transaction --routines --triggers -u root -prada test > backup_sunday_1_PM.sql
    ```
    * DB=``는 따옴표아니다!
    * ``를 넣어주면 쉘에서 실행한 명령어를 저장해준다.
    * ```--single-transaction```: 트랜잭션 격리모드.
    * ```--routines```: 모든 저장 프로시저 및 저장 함수를 덤프한다
    * ```--triggers```: 트리거가 있는 각 테이블에 대한 모든 트리거를 덤프한다.
    
2. backup.sh 스크립트 실행
    ```
    $ /bin/bash backup.sh
    ```
    
3. crontab을 이용하여 주기적으로 명령을 실행 : 5분에 한 번씩 백업
    ```
    $ crontab -e
    # /usr/bin : mysqldump를 실행
    # /bin : /bin/bash를 실행
    PATH=/usr/bin:/bin:/home/ubuntu/.pyenv/versions/python3/bin
    */5 * * * * /bin/bash /home/ubuntu/backup.sh
    ```
    * "/usr/bin", "/bin" 을 추가하는 이유는 crontab에는 system에는 bin shell이 없기 때문이다.
   
4. 복원할 데이터 베이스 생성
    ```
    $ mysql -u root -p
    mysql> create database backupdb default character set utf8 collate utf8_general_ci;
    ```
   
5. 복원
    ```
    # mysql -u root -p (데이터베이스 이름) < (백업 파일)
    $ mysql -u root -p backupdb < 20200412_0203.sql
    ```
</br>
</br>

## 2.2. Cold Physical Backup
1. 백업할 파일 위치 확인
    ```
    # 데이터가 저장되는 디렉토리 확인
    mysql> show variables like 'datadir';
    /var/lib/mysql/
    ```
    * /var/lib/mysql 에 바이너리 파일이 있다.

2. 서버 중지
    ```
    $ sudo systemctl stop mysql
    ```

3. backup 디렉토리 생성하여 backup 파일을 이동
    ```
    $ mkdir backup
    $ sudo su
    $ cd /var/lib/mysql
    $ cp -r * /home/ubuntu/backup
    ```

4. backup 파일의 권한을 변경, 파일 이동시 권한을 가지고 있어야 함
    ```
    $ sudo chown -R ubuntu: /home/ubuntu/backup
    ```
    * Cyberduck 등을 이용하여 local pc로 backup 디렉토리 파일 이동

5. DB에 백업 파일 복구하기위해 DB 정지 후, 기존에 있던 DB 파일을 임시 디렉토리로 이동
    ```
    $ sudo systemctl stop mysql
    $ mkdir tmp
    $ sudo su
    $ cd /var/lib/mysql
    $ cp -r * /home/ubuntu/tmp
    ```

6. 백업 파일을 DB 저장 위치에 복사
    ```
    $ cp -r /home/ubuntu/backup/* ./
    ```

7. 백업 파일의 모든 권한을 mysql로 변경 및 mysql 시작
    ```
    $ sudo chown -R mysql: /var/lib/mysql
   
    $ sudo systemctl start mysql
    ```
</br>
</br>

## 2.3. Replication을 이용한 백업
* https://dev.mysql.com/doc/mysql-backup-excerpt/5.6/en/replication-solutions-backups.html
* Replication을 이용하기 위해서는 먼저 Replication의 복사를 중지하거나, 서버를 중지해야 된다.
* 복제가 있는 경우는 복제를 이용한 백업이 좋은 거 같다.
1. Hot Logical Backup
    * https://dev.mysql.com/doc/mysql-backup-excerpt/5.6/en/replication-solutions-backups-mysqldump.html
2. Cold Physical Backup
    * https://dev.mysql.com/doc/mysql-backup-excerpt/5.6/en/replication-solutions-backups-rawdata.html
