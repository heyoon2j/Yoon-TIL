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
    *         *          *         *        ? {command}
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