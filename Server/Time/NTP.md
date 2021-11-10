# NTP
* Network Time Protocol
* 컴퓨터 시스템 간시간 동기화를 위한 네트워크 프로토콜이다.
</br>
</br>

## NTP Server
* 전에는 ntpd(NTP Daemon)를 이용하여 시간 동기화를 하였지만, 요즘에는 Chrony Server/Client를 이용하여 동기화를 한다.


## Chrony
## Install
* ntpd가 설치되어 있는 경우 제거하고 설치진행
    ```
    $ systemctl status ntpd
    $ systemctl stop ntpd
    $ systemctl disable ntpd
    $ yum remove ntpd
    ```
1. CentOS
    * 설치 명령어
    ```
    $ yum install -y chrony
    ```
2. MacOS

</br>

## How to using
1. 환경설정 변경
    * ```/etc/chrony.conf``` : Chrony 설정파일
    * 
2. a
3. b
4. c
5. d
6. e
7. f
