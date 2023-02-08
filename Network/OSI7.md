# OSI 7 Layer
![[그림출처] https://www.lifewire.com/osi-model-reference-guide-816289](img/OSI7Layer.png)

## Physical Layer (1 Layer)
* 전기적인 접속. 통신 매체에 접근하는데 필요한 기계적이고 전기적인 기능과 절차를 규정한다.
* 장비: 케이블, Voltage 등
</br>


## Data Link Layer (2 Layer)
* 물리적 주소(MAC 주소)를 기반으로 노드 대 노드(Point-to-Point)로 데이터 전송 형태를 결정하는 계층.
* 기기간 처리, Error Control(물리계층에서 발생하는 Error), Flow Control(Ex> 스케줄링).
* 장비 : Switch, Hub
* 프로토콜: Ethernet, PPP 등
</br>


## Network Layer (3 Layer)
* 논리적 주소(IP주소)를 기반으로 출발지에서 목적지까지 가는 네트워크 경로를 제어하는 계층.
* Path Selection, Routing을 하게 된다.
* 장비 : Router
* 프로토콜: IP, ICMP, ARP 등
* Source IP Address, Destination IP Address
</br>


## Transport Layer (4 Layer)
* 종단 간의 통신(End to End)을 제어하는 계층(데이터 전송 관리)
* 데이터 전송의 신뢰성을 보장. 에러 제어, 혼잡 제어, 흐름 제어.
* 프로토콜:  TCP, UDP 등
* Source Port Number, Destination Port Number, Seq, Ack
</br>


## Session Layer (5 Layer)
* 종단 간의 응용 프로세스가 통신을 관리하는 계층(데이터 교환 관리)
* 프로세스 간에 연결을 맺거나 끊기, 연결을 유지하는 기능을 담당하고, 포트(Port) 연결이라고도 한다.
* 프로토콜: SSH, TLS, RPC 등
</br>


## Prensentation Layer (6 Layer)
* 시스템 간의 데이터 형식상 차이를 다루는 계층.
* 데이터 표현 양식 제공, 데이터 형태(구조) 통일화/변환(Big/Small Radian, 확장자, 암호화, 압축, 인코딩 등)
* 프로토콜: JPEG, MPEG, AFP, MIME 등
</br>


## Application Layer (7 Layer)
* 사용자가 네트워크에 접근할 수 있도록 해주는 계층.
* 사용자에게 보이는 부분으로 서비스를 제공하는 사용자 프로그램을 말한다.
* 프로토콜: DHCP, HTTP, DNS, FTP, SMTP 등
</br>



### Reference
* https://darrengwon.tistory.com/1309
