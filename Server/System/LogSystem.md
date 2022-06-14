## System Log
* systemd-journald :부팅이 시작되는 순간부터 로그 수집 데몬
* rsyslogd : syslog를 사용하여 각 파일 별로 로그 수집 데몬
* ```/var/log/dmesg``` : 부팅 시 시스템 로그 기록
* ```/var/log/utmp``` : 현재 로그인한 사용자 상태 정보 기록
* ```/var/log/wtmp``` : 사용자 Login, Logout, 시스템 재부팅 기록
* ```/var/log/btmp``` : 실패한 로그인 정보 기록
</br>
</br>


## systemd-journald
* 부팅이 시작되는 순간부터 발생하는 모든 이벤트를 수집해서 바이너리 형태로 저장
* 바이너리이기 때문에 ```journalctl``` 명령을 이용
* ```/run/log/journal```에 저장되며 재부팅시 데이터가 삭제됨(캐시 데이터)
</br>
</br>


## rsyslog
* syslog를 사용하여 각 파일 별로 로그 수집
* rsyslog 관련 파일
    * ```/sbin/rsyslogd``` : 실행 파일(데몬)
    * ```/etc/rc.d/init.rsyslog``` : rsyslogd daemon 실행 스크립트
    * ```/etc/rsyslog.conf``` : rsyslogd 환경 설정
    * ```/etc/sysconfig/rsyslog``` : rsyslogd 실행 관련 옵션 설정
    * ```/var/log/``` : 기본 Log 파일 위치
* Ref: https://m.blog.naver.com/sunchan683/221511250171
* Ref: https://haker.tistory.com/52
</br>

### 수집하는 Log 파일 위치 (기본 값)
| Path | Description | Facility |
|------|-------------|----------|
| /var/log/messages | 전체 시스템 로그 |  |
| /var/log/boot.log | 부팅 과정 로그 |  |
| /var/log/secure | 인증 관련 로그 | |    
| /var/log/maillog | 메일 관련 로그 |  |
| /var/log/cron | 스케줄링 관련 로그 |  |

### Facility
* Log Message를 발생시키는 메시지 타입
    | Type | Description |     |
    |------|-------------|-----|
    | * | 모든 서비스 메시지  |   |
    | kern | 커널 메시지 |  |
    | user | 유저 레벨 메시지 |  |
    | deamon | 시스템 데몬 메시지 | telnet / ftp |
    | auth / security | 보안 및 인증 메시지 | login |
    | authpriv | 보안 및 인증 메시지 | ssh |
    | cron | 스케줄 작업 메시지 | cron / at |
    | mail | 메일 관련 메시지 |  |
    | syslog | syslogd에 의해 내부적으로 생성되는 메시지 |  |
    | lpr | 프린트 관련 메시지 |  |
</br>

### Priority
* Log Level (위험도 우선 수위)
    | Code | Priority | Description |
    |------|----------|-------------|
    | 0 | emerg | 시스템 사용 불가 |
    | 1 | alert | 즉각 조치 필요 |
    | 2 | crit | 치명적인 상태 발생 |
    | 3 | error | 에러 발생 |
    | 4 | warning | 주의가 필요한 경고 발생 |
    | 5 | notice | 일반적이지만 중요 메시지 |
    | 6 | info | 기본 정보 메시지 |
    | 7 | debug | 디버깅 메시지 |
    |  | none | 지정한 Facility 제외, 즉 해당 로그는 남기지 않겠다는 의미 |
</br>

### Action
* Log Message 처리 방법
    | Action | Description | Example |
    |--------|-------------|---------|
    | file | 지정한 파일에 로그 기록 | /var/log/secure |
    | -file | 메모리 버퍼에 저장한 후 자원이 여유로울 때 로그 기록 | -/var/log/maillog |
    | /dev/console | 콘솔 출력 |  |
    | @host | 지정한 호스트로 메시지 전달 | @172.16.3.2 |
    | user | 지정된 사용자의 스크린으로 메시지 전달 | |
    | * | 현재 로그인 되어있는 모든 사용자 스크린에 전달 | |


### Rotation
* ```logrotate``` 유틸리티를 사용하여 로그 파일 크기 제한, 파일 생성 등의 순환 과정을 정의한다.
* ```/etc/logrotate.conf``` : 전체 로그에 대한 순환 설정
* ```/etc/logrotate.d/``` : 개별 로그에 대한 순환 설정







## systemd-journald
* 부팅이 시작되는 순간부터 발생하는 모든 이벤트를 수집해서 바이너리 형태로 저장
* 바이너리이기 때문에 ```journalctl``` 명령을 이용
* ```/run/log/journal```에 저장되며 재부팅시 데이터가 삭제됨(캐시 데이터)
</br>
</br>



## rsyslog
* syslog를 사용하여 각 파일 별로 로그 수집
* rsyslog 관련 파일
    * ```/sbin/rsyslogd``` : 실행 파일(데몬)
    * ```/etc/rc.d/init.rsyslog``` : rsyslogd daemon 실행 스크립트
    * ```/etc/rsyslog.conf``` : rsyslogd 환경 설정
    * ```/etc/sysconfig/rsyslog``` : rsyslogd 실행 관련 옵션 설정
    * ```/var/log/``` : 기본 Log 파일 위치
* Ref: https://m.blog.naver.com/sunchan683/221511250171
* Ref: https://haker.tistory.com/52
</br>

### 수집하는 Log 파일 위치 (기본 값)
| Path | Description | Facility |
|------|-------------|----------|
| /var/log/messages | 전체 시스템 로그 |  |
| /var/log/boot.log | 서비스 부팅 로그 |  |
| /var/log/secure | 인증 관련 로그 | |    
| /var/log/maillog | 메일 관련 로그 |  |
| /var/log/cron | 스케줄링 관련 로그 |  |

### Facility
* Log Message를 발생시키는 메시지 타입
    | Type | Description |     |
    |------|-------------|-----|
    | * | 모든 서비스 메시지  |   |
    | kern | 커널 메시지 |  |
    | user | 유저 레벨 메시지 |  |
    | deamon | 시스템 데몬 메시지 | telnet / ftp |
    | auth / security | 보안 및 인증 메시지 | login |
    | authpriv | 보안 및 인증 메시지 | ssh |
    | cron | 스케줄 작업 메시지 | cron / at |
    | mail | 메일 관련 메시지 |  |
    | syslog | syslogd에 의해 내부적으로 생성되는 메시지 |  |
    | lpr | 프린트 관련 메시지 |  |
</br>

### Priority
* Log Level (위험도 우선 수위)
    | Code | Priority | Description |
    |------|----------|-------------|
    | 0 | emerg | 시스템 사용 불가 |
    | 1 | alert | 즉각 조치 필요 |
    | 2 | crit | 치명적인 상태 발생 |
    | 3 | error | 에러 발생 |
    | 4 | warning | 주의가 필요한 경고 발생 |
    | 5 | notice | 일반적이지만 중요 메시지 |
    | 6 | info | 기본 정보 메시지 |
    | 7 | debug | 디버깅 메시지 |
    |  | none | 지정한 Facility 제외, 즉 해당 로그는 남기지 않겠다는 의미 |
</br>

### Action
* Log Message 처리 방법
    | Action | Description | Example |
    |--------|-------------|---------|
    | file | 지정한 파일에 로그 기록 | /var/log/secure |
    | -file | 메모리 버퍼에 저장한 후 자원이 여유로울 때 로그 기록 | -/var/log/maillog |
    | /dev/console | 콘솔 출력 |  |
    | @host | 지정한 호스트로 메시지 전달 | @172.16.3.2 |
    | user | 지정된 사용자의 스크린으로 메시지 전달 | |
    | * | 현재 로그인 되어있는 모든 사용자 스크린에 전달 | |


### Rotation
* ```logrotate``` 유틸리티를 사용하여 로그 파일 크기 제한, 파일 생성 등의 순환 과정을 정의한다.
* ```/etc/logrotate.conf``` : 전체 로그에 대한 순환 설정
* ```/etc/logrotate.d/``` : 개별 로그에 대한 순환 설정
