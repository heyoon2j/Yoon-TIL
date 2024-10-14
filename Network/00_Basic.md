## Link-local address
기존에는 장치가 연결해야할 서버를 찾지 못하거나 연결하지 못했을 때를 위해
* 호스트에 연결되어 있는 Subnetwork(Broadcast Domain)를 통해서만 통신할 수 있는 주소
* 예약된 CIDR 값 : 169.254.0.0/16
* Lik-local address는 Stateless address autoconfiguration or Link-local address autoconfiguration 를 통해서 할당 받는다 (보통 DHCP 서버 통해서)
* 라우터를 통해서 통신하게 된다.



## Port
* 운영체제 통신의 종단점.
* Port 번호는 다음 세가지로 나눌 수 있다.
    * 0 ~ 1023번: well-known port
    * 1024 ~ 49151번: registered port
    * 49152 ~ 65535번: dynamic port


## TPC/UDP Port List
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
