# VLAN
가상 랜. 컴퓨터 네트워크에서 여러 개의 구별되는 Broadcast Domain을 만들기 위해 단일 2 계층 네트워크를 분할할 수 있는데, 이렇게 분리되면 Packet들은 하나 이상의 네트워크 장비를 통해서만 이동할 수 있다.
> Broadcast Domain을 이해하려면 ARP Protocol을 이해하면 된다!!

* 보통 서로 다른 PC 정보를 Broadcasting을 통해 알 수 있기 때문에, 해당 Domain에 따라 Network를 분리할 수 있다(guess)
* Switch Tunneling을 사용하여 VLAN을 구분지어 통신한다.
</br>
</br>

* Broadcast Domain
    - LAN 상에서 어떤 단말이 Broadcast Packet을 송출할 때, '해당 Packet을 수신할 수 있는 단말들의 집합'을 의미
    - VLAN, CIDR 등에 결정됨
* Collision Domain
    - LAN 상에서 전송 매체를 공유하고 있는 경우, 여러 단말들이 동시에 Packet을 송출하면 충돌이 나는데 이 충돌에 영향을 받게 되는 영역을 의미. 하나의 단말이 통신을 하고 있는 경우, 다른 단말은 통신할 수 없다 (장비 성능에 영향을 많이 받음)
    > 허브는 CSMA/CD를 통해 충돌을 처리하고, 스위치는 충돌을 미연에 발생하지 않게 하기 위해 MAC 주소 필터링과 버퍼링 방법을 사용.

    > 리피터와 허브는 충돌을 전파하지만, Switch, Router는 forwarding을 통해 충돌 영향이 없다. Collision Domain을 Switch와 Router는 분할할 수 있다.

    > ref : https://ja-gamma.tistory.com/entry/VLAN-%EA%B0%9C%EB%85%90-%EB%8F%99%EC%9E%91%EC%9B%90%EB%A6%AC-%EC%82%AC%EC%9A%A9%EC%9D%B4%EC%9C%A0

---
## Tunneling
다른 프로토콜로 캘슙화하여 데이터를 전송하는 기술
* 캡슐화는 패킷을 다른 패킷 내부에 래핑하는 것을 의미
* 기존 패킷을 Payload에 두고, Tunneling 정보를 Header로 추가하여 캡슐화한다.
</br>

### Protocol 종류
* Layer 3 (네트워크 계층)
    * IPSec 
    * GRE
    * IP-in-IP
* Layer 7 (애플리케이션 계층)
    * SSH
