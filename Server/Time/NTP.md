# NTP
* Network Time Protocol
* 컴퓨터 시스템 간시간 동기화를 위한 네트워크 프로토콜이다.
</br>
</br>

## Chrony
* 전에는 ntpd(NTP Daemon)를 이용하여 시간 동기화를 하였지만, 요즘에는 Chrony Server/Client를 이용하여 동기화를 한다.
</br>

## How to use Chrony
## __Install__
* ntpd가 설치되어 있는 경우 제거하고 설치진행
    ```
    $ systemctl status ntpd
    $ systemctl stop ntpd
    $ systemctl disable ntpd
    $ yum remove ntpd
    ``
</br>

1. CentOS
	```
	$ sudo yum install -y chrony
	```
2. Ubuntu
	```
	$ sudo apt install chrony
	```
</br>

## __Config__
* 설정 파일 수정 (PATH = ```/etc/chrony.conf```) 
* 공식 문서: https://chrony.tuxfamily.org/doc/4.1/chrony.conf.html
0. 서비스 구동
	```
	$ sudo systemctl enable chronyd
	$ sudo systemctl start chronyd
	```

1. Time source를 사용할 NTP Server 설정
	* 여러 번 사용하여 여러 서버를 지정할 수 있다.
	```
	server [HOST] 

	server 10.0.0.152 prefer iburst minpoll 4 maxpoll 4
    server 10.0.0.153 iburst port 11123
	```
	* minpoll : 서버에 전송된 요청 간의 폴링  최소 간격으로 ^2초. 예로 minpoll 5인 경우, 폴링 간격은 32초보다 떨어지면 안 된다(Default = 6).
	* maxpoll : 폴링 최대 간격. 예로 maxpoll 5인 경우, 폴링 간격은 512초 이하여야 한다(Default = 9)
	* iburst : 첫 번째 4-8 요청에 대해서는 빠르게 업데이트를 빠르게 시작.
	* prefer : 여러 서버 중 해당 설정을 선호한다는 의미.
	* port : NTP에 대한 UDP 포트(Default = 123).
	* 
