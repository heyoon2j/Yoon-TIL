# VLAN
가상 랜. 컴퓨터 네트워크에서 여러 개의 구별되는 Broadcast Domain을 만들기 위해 단일 2 계층 네트워크를 분할할 수 있는데, 이렇게 분리되면 Packet들은 하나 이상의 네트워크 장비를 통해서만 이동할 수 있다.
> Broadcast Domain을 이해하려면 ARP Protocol을 이해하면 된다!!
* 보통 서로 다른 PC 정보를 Broadcasting을 통해 알 수 있기 때문에, 해당 Domain에 따라 Network를 분리할 수 있다(guess)
* Switch Tunneling을 사용하여 VLAN을 구분지어 통신한다.
</br>
</br>

* Broadcast Domain : LAN 상에서 어떤 단말이 Broadcast Packet을 송출할 때, '해당 Packet을 수신할 수 있는 단말들의 집합'을 의미
* Collision Domain : LAN(Switch, Hub)에서 전송 매체를 공유하고 있는 여러 단말들이 통신할 때 서로 경쟁함으로써 충돌이 발생하는데, 이 충돌한 프레임이 전파되어 영향을 받게 되는 영역을 의미. 리피터와 허브는 충돌을 전파하지만, Switch, Router는 forwarding을 통해 충돌 영향이 없다. Collision Domain을 Switch와 Router는 분할할 수 있다
> ref : https://ja-gamma.tistory.com/entry/VLAN-%EA%B0%9C%EB%85%90-%EB%8F%99%EC%9E%91%EC%9B%90%EB%A6%AC-%EC%82%AC%EC%9A%A9%EC%9D%B4%EC%9C%A0

---
## Tunneling
Source와 Destination에서만 사용하고 중간 네트워크에서 사용하지 않는 프로토콜을 사용하여 데이터를 전송하는 과정
* 전송할 때 패킷을 캡슐화하여 전송한다(캡슐화는 패킷을 다른 패킷 내부에 래핑하는 것을 의미)
* 기존 패킷을 Payload에 두고, Tunneling 정보를 Header로 추가하여 캡슐화한다.
</br>

## Protocol 종류
* Layer 3 (네트워크 계층)
    * IPSec 
    * GRE
    * IP-in-IP
* Layer 7 (애플리케이션 계층)
    * SSH
