# Network

## Basic
## Address
* IP : NIC(Network Interface Controller) 주소. 어떤 MAC Address를 가진 장비로 라우팅을 해야되는지 분별하기 위한 주소
* MAC Address : 실제 장비의 고유 주소
> 즉, IP 기준으로 어떤 장비에게 통신할지 결정된다.

---
## Bandwidth vs Throughput vs Latency
* __Bandwidth__ : 대역폭. 네트워크를 통해 특정 시간 내에 전송할 수 있는 패킷 수. 클 수록 많은 양의 데이터를 동시에 보낼 수 있다. 기본 단위는 bps(bit per second)이다.
* __Throughput(MB/s)__: 처리량. 시간 단위 내에 송수신되는 데이터의 양.
* __Latency__ : 지연 시간. 네트워크에서 하나의 데이터 패킷이 다른 지점에 도착하는데 소요되는 시간.
* Bandwidth는 이상적인 데이터 전송량이고, Throughput은 실제로 초당 전송되는 데이터 양이다. 실제 네트워크 상에서는 Packet loss 등으로 이상적으로 데이터를 전송할 수 없다.
* Bandwidth, Throughput이 클 수록, Latency가 작을 수록 좋다.
> 도로(Bandwidth)가 넓어도, 사고 등의 문제들로(Packet loss) 있다면 제공하는 도로 크기만큼의 차(Packet)가 이동할 수 없고(Throughput), 정체되는 시간이(Latency) 길어진다.

### Example
* 가정
    * 네트워크를 느리게 하는 요소 모두 제외
    * Packet 크기가 Bandwidth 크기와 동일
    * A Task는 작업
> 일반적으로 Latency가 작을 수록 Throughput이 크다. 하지만 멀티 태스킹 환경에서는 달라질 수 있다! Latency 증가하더라도, 증가된 시간 비례 더 많은 양을 보낼 수 있게 되기 때문에 Throughput이 크게 증가할 수 있다.  
</br>


---
## 네트워크 장비
### Gateway
* 다른 Network로 들어가는 입구 역할을 하는 네트워크 포인트

### Router
* 서로 다른 Network의 통신을 가능하게 해준다.
* Packet을 Destination IP Address와 일치하는 경로를 Routing Table에서 검색하여 최적의 경로로 보내준다.

### Hub
* Frame을 동일 네트워크에 전부 뿌려준다.
* 동일한 네트워크에서 Node를 확장한다.

### Switch
* Frame을 MAC Address Table에 의해서 최적의 경로로 보내준다.
* MAC Address를 학습할 수 있다.
* 동일한 네트워크에서 Node를 확장한다.
</br>
</br>


---
## Network Tool
* Wireshark : 패킷 분석
* Nmap : 네트워크 매핑
* Infection Monkey : 침투 테스트
* fprove : 성능 테스트
* Cacti : 시각화 툴


## ETC (용어)
* Clock Rate: 데이터 전송속도(bit)
* DTE(Data Terminal Equipment): PC, Server, Router
* DCE(Data Communication Equipment): DSU, CSU
* 모뎀: Digital Signal -> Analogue Signal / Analogue Signal -> Digital Signal로 변환시켜준다. 변환시키는 이유는 Digital Signal은 거리가 짧기 때문이다.
* CIDR: Classless Inter-Domain Routing, Class 없는 Domain 간 Routing 기법으로 VLSM을 사용하여 표현
* VLSM: Variable Length Subnet Masks, 가변길이 서브넷 마스크
* North-South : Data Center-Client, Internet-Data Center 간의 통신을 의미
* East-West : Server-Server. Data Center 내부에서 발생되는 트래픽을 의미
* Broadcast Domain : LAN 상에서 어떤 단말이 Broadcast Packet을 송출할 때, '해당 Packet을 수신할 수 있는 단말들의 집합'을 의미
* Collision Domain : LAN 상에서 전송 매체를 공유하고 있는 경우, 여러 단말들이 동시에 Packet을 송출하면 충돌이 나는데 이 충돌에 영향을 받게 되는 영역을 의미. 하나의 단말이 통신을 하고 있는 경우, 다른 단말은 통신할 수 없다(기본적으로 통신 불가가 됨)
    > 허브는 CSMA/CD를 통해 충돌을 처리하고, 스위치는 충돌을 미연에 발생하지 않게 하기 위해 MAC 주소 필터링과 버퍼링 방법을 사용.
    > 리피터와 허브는 충돌을 전파하지만, Switch, Router는 forwarding을 통해 충돌 영향이 없다. Collision Domain을 Switch와 Router는 분할할 수 있다.