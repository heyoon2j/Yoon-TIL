# Routing

## Routing Path 결정 순서
* 물리적 장치 (Routing Rule) / Longest Match Rule -> AD -> Metric

### 물리적 장치(Routing Rule)
* 물리적 장치에서 Virual 등 설정으로 우선순위를 결정할 수 있다.
</br>

### Longest Match Rule
* IP 패킷의 목적지 IP 주소가 라우팅 테이블에 있는 수많은 목적지 IP 주소 중 일치하는 부분이 가장 긴 곳으로 라우팅하는 규칙
* Example
    ```
    # IP Route
    10.0.0.0/8 s0/0
    10.20.0.0/16 s0/1
    10.20.192.0/24 s0/2
    ```
    * Destination IP가 10.20.192.12 일때, 일치하는 부분이 가장 긴 곳인 s0/2를 선택
  > 가장 길다는 의미는 가장 자세하게 설정된 위치를 의미한다.
</br>

### AD (Administrative Distance)
* Router 간의 약속된 거리 값을 의미한다. 라우팅 정보 소스의 신뢰성에 대하여 정해 놓은 비율이다.
* 여러 개의 라우팅 프로토콜을 운영할 경우 동일 목적지에 대하여 여러 개의 경로 중 어떤 프로토콜을 우선 순위로 둘 것인지 AD를 기준으로 판단한다.
* __값이 낮을 수록 우선 순위가 높아지고, 전송 속도도 빠르다. 값은 변경이 가능하다.__
* Table

    | Route Source | Default Distance Values |
    |--------------|-------------------------| 
    | Connected interface | 0 |
    | Static route | 1 |
    | Enhanced Interior Gateway Routing Protocol (EIGRP) summary route | 5 |
    | External Border Gateway Protocol (BGP) | 20 |
    | Internal EIGRP | 90 |
    | IGRP | 100 |
    | OSPF | 110 |
    | Intermediate System-to-Intermediate System (IS-IS) | 115 |
    | Routing Information Protocol (RIP) | 120 |
    | Exterior Gateway Protocol (EGP) | 140 |
    | On Demand Routing (ODR) | 160 |
    | External EIGRP | 170 |
    | Internal BGP | 200 |
    | Unknown* | 255 |
* Example
    ```
    R   10.20.192.0/24 [120/5]  via ...
    ```
</br>


### Metric 값
* AD값 옆에 있는 값이 Metric 값으로, AD 값을 몇 번 측정했는가를 나타낸다.
* 즉 Source에 Target까지 가기 위한 거리 비용이라고 생각하면 되고, 이는 프로토콜마다 달라진다.
* 동일 라우팅 프로토콜 내에서 경로가 여러 개인 경우, __값이 작을수록 우선 순위가 높다.__
</br>
</br>


## ASN
* Autonomous System Number
* 하나의 네트워크 관리자에 의해 관리되는 라우터 집단(VLAN ID와 비슷한 개념인거 같다)
* 보통 보면 서로 다른 네트워크끼리 연결할 때(VPN 등) 필요하다.

