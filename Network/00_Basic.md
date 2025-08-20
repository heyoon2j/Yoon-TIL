## Device
- Hub : 연결된 모든 포트에 패킷을 전달
    - MAC Address를 저장하지 않음
    - 트래픽은 모든 포트로 전달되기 때문에 모든 포트가 하나의 Collision Domain 안에 있다.
- Switch : 
    - MAC Address를 저장함
    - 특정 포트와 MAC Address가 매핑되어 있기 때문에 특정 포트로만 통신을 한다. 그렇기 때문에 각 포트가 Collision Domain이 된다.

## Domain
- Broadcast Domain
    - LAN 상에서 어떤 단말이 Broadcast Packet을 송출할 때, '해당 Packet을 수신할 수 있는 단말들의 집합'을 의미
    - VLAN, CIDR 등에 결정됨
- Collision Domain
    - LAN 상에서 전송 매체를 공유하고 있는 경우, 여러 단말들이 동시에 Packet을 송출하면 충돌이 나는데 이 충돌에 영향을 받게 되는 영역을 의미. 하나의 단말이 통신을 하고 있는 경우, 다른 단말은 통신할 수 없다 (장비 성능에 영향을 많이 받음)
    > 허브는 CSMA/CD를 통해 충돌을 처리하고, 스위치는 충돌을 미연에 발생하지 않게 하기 위해 MAC 주소 필터링과 버퍼링 방법을 사용.

    > 리피터와 허브는 충돌을 전파하지만, Switch, Router는 forwarding을 통해 충돌 영향이 없다. Collision Domain을 Switch와 Router는 분할할 수 있다.


## Link-local address (169.254.0.0/16)
IP를 DHCP 서버를 통해 할당받지 못하거나, Static IP를 통해 설정되지 않는 경우, 해당 IP를 임시로 할당 받는다. 즉, 임시 방편용 IP라고 생각하면 된다.

* 호스트에 연결되어 있는 Broadcast Domain을 통해서만 통신할 수 있음
    > 즉, 물리적(논리적)으로 연결된 네트워크에 대해서 통신이 됨
* 클라우드에서는 이를 고정 IP로 사용된다. 이유는 다음과 같다라고 추측한다!
    1) 기존에 사용되어지는 기술. 즉, 다른 기술이 추가되는 것이 아니라 기존 기술들을 가지고 호환이 가능하다는 점 (단순화)
    2) Overlay Network에서 사용되지 않는 대역. 즉, 변화가 있어도 상관이 없는 대역 (신뢰성)
    3) 1, 2번에 의해 모든 인스턴스는 동일한 설정하에 특정 서버들과(169.254.0.0/16 대역의 서버) 통신이 가능 (표준성)


---
## Port
* 운영체제 통신의 종단점.
* Port 번호는 다음 세가지로 나눌 수 있다.
    * 0 ~ 1023번: well-known port
    * 1024 ~ 49151번: registered port
    * 49152 ~ 65535번: dynamic port


## TCP/UDP Port List
| Port Number | TCP/UDP | Description                                     |
| ----------- | ------- | ----------------------------------------------- |
| 20          | TCP     | FTP, File Transfer Protocol. Data               |
| 21          | TCP     | FTP, File Transfer Protocol. Control            |
| 22          | TCP     | SSH, Secure SHell                               |
| 23          | TCP     | Telnet                                          |
| 25          | TCP     | SMTP, Simple Mail Transfer Protocol             |
| 53          | TCP/UDP | DNS, Domain Name System                         |
| 80          | TCP     | HTTP, Hyper Text Transfer Protocol              |
| 109         | TPC     | POP2, Post Office Protocol version 2            |
| 110         | TCP     | POP3, Post Office Protocol version 3            |
| 111         | TCP/UDP | RPC, Remote Procedure Call                      |
| 123         | UDP     | NTP, Network Transfer Protocol                  |
| 143         | TCP     | IMAP4, Internet Message Access Protocol 4       |
| 161         | UDP     | SNMP, Simple Network Management Protocol. Agent |
| 162         | UDP     | SNMP. Manager                                   |
| 220         | TCP     | IMAP3                                           |
| 443         | TCP     | HTTPS                                           |
| 445         | TCP     | MS Active Directory                             |
| 990         | TCP     | SSL 위의 FTP                                    |
| 992         | TCP     | SSL 위의 Telnet                                 |
| 993         | TPC     | SSL 위의 IMAP4                                  |
| 995         | TPC     | SSL 위의 POP3                                   |
|             |         |                                                 |
