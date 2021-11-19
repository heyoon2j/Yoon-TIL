# NTP
* Network Time Protocol
* 컴퓨터 시스템 간시간 동기화를 위한 네트워크 프로토콜이다.
</br>
</br>

## Architecture
* NTP는 Stratum 구조로 피라미드 형식이다.
* Stratum 0는 피라미드 꼭대기로 "primary reference clock" 이라고 부른다. 해당 장비로는 GPS, 세슘 원자 시계 등이 있다고 한다. 보통 Stratum 1의 서버들은 primary reference clock에서 시간을 동기화하여 서비스를 하며, NTP에서 최상위층이라고 생각하면 된다.
* 보통 부하 등의 문제로 Stratum 2, 3으로 구성되어 있는 pool의 서버에서 시간 동기화를 한다.
* https://joungkyun.gitbook.io/annyung-3-user-guide/chapter6/chapter6-chrony


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
	# server HOST [OPTION]
    # pool HOST_POOL [OPTION]

	server 10.0.0.152 prefer iburst minpoll 4 maxpoll 4 port 11123
    
    pool 0.amazon.pool.ntp.org iburst maxsources 1
    pool 1.amazon.pool.ntp.org iburst maxsources 1
    pool 2.amazon.pool.ntp.org iburst maxsources 2
	```
	* minpoll : 서버에 전송된 요청 간의 폴링  최소 간격으로 ^2초. 예로 minpoll 5인 경우, 폴링 간격은 32초보다 떨어지면 안 된다(Default = 6).
	* maxpoll : 폴링 최대 간격. 예로 maxpoll 5인 경우, 폴링 간격은 512초 이하여야 한다(Default = 9)
	* iburst : 첫 번째 4-8 요청에 대해서는 빠르게 업데이트를 빠르게 시작.
	* prefer : 여러 서버 중 해당 설정을 선호한다는 의미(Default 설정 느낌)
	* port : NTP에 대한 UDP 포트(Default = 123).
	* maxsource : pool option으로 응답을 얻을 때까지 시도하는 횟수(Default = 4).
    
> 내 생각으로는 기본적으로 pool이 형성되어 있으면 pool, 아니면 server를 사용. pool이 형성되어 있다는 거는 dns 서버 등으로 endpoint가 구성되어 있다는 것을 의미. 그게 아니라고 하면 server로 여러 개 지정.
</br>

2. Log File
    * Chrony는 다음과 같은 Log File을 저장할 수 있다.
    ```
    # driftfile file
    driftfile /var/lib/chrony/drift

    # logdir directory
    logdir /var/log/chrony    

    # dumpdir directory
    dumpdir /var/run/chrony

    # sourcedir dircetory
    sourcedir /var/run/chrony-dhcp
    ```
    * __driftfile__: System Clock이 실제 시간과의 오차 비율을 계산하여 기록하는 파일
    * __logdir__: 
    * __dumpdir__: 
    * __sourcedir__:
</br>

3. Client Access
    * Local Network에 대한 Access를 허용하기 위해서는 다음과 같이 정의한다.
    ```
    # allow []
    ```
    * ```allow all```은 이전 지시문에 대한 효과를 재정의한다.
        ```
        allow 1.2.3.4
        deny 1.2.3.0/24
        allow all 1.2.0.0/16
        ```
        * 1.2.0.0/16에 대해 
</br>

1. ETC Setting
    ```

    ```
    * a
    * b
    * c




</br>


### Example
* 예시는 기본 Amazon-Linux2의 chrony.conf
```
# use the Amazon Time Sync Service (if available)
server 169.254.169.123 prefer iburst minpoll 4 maxpoll 4

# Use public servers from the NTP Pool Project project.
# Please consider joining the pool (http://www.pool.ntp.org/join.html).

# This will use (up to):
# - 1 source from [0-1].amazon.pool.ntp.org each (IPv4 only atm)
# - 2 sources from 2.amazon.pool.ntp.org which is IPv6 enabled as well
# This means by default, up to 2 dual-stack and up to 2 additional IPv4-only
# sources will be used.
pool 0.amazon.pool.ntp.org iburst maxsources 1
pool 1.amazon.pool.ntp.org iburst maxsources 1
pool 2.amazon.pool.ntp.org iburst maxsources 2

# Record the rate at which the system clock gains/losses time.
driftfile /var/lib/chrony/drift

# Allow the system clock to be stepped in the first three updates
# if its offset is larger than 1 second.
makestep 1.0 3

# Enable kernel synchronization of the real-time clock (RTC).
rtcsync

# Allow NTP client access from local network.
#allow 192.168.0.0/16

# Specify file containing keys for NTP authentication.
keyfile /etc/chrony.keys

# Specify directory for log files.
logdir /var/log/chrony

# Select which information is logged.
#log measurements statistics tracking

# save data between restarts for fast re-load
dumponexit
dumpdir /var/run/chrony

# use a key to secure communication between chronyc and the daemon
#commandkey key1

# Use NTP servers from DHCP.
sourcedir /run/chrony-dhcp
```
