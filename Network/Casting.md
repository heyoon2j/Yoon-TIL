# Casting
* Unicast
* Multicast
* Broadcast
* Anycast

## Unicast
* Basic
    1) Packet을 전송하기 위해 Frame에 자신의 MAC Address와 Target의 MAC Address를 감싸서 전송한다.
    2) Target의 Local Network의 Network Device(Lan Card, Router, Switch)들은 해당 Packet을 받아 MAC Address를 비교하여(ARP Table) 정보가 있으면 해당 Device로 전달한다. 없으면 차단한다.
    3) Unicast를 받은 Lan Card는 CPU에 인터럽트를 발생시켜 Packet을 처리한다.
    4) MAC Address를 모르는 Unicast Packet이 들어오는 경우, 해당 Packet은 Switch에 연결되어 있는 모든 포트로 Broadcast 된다(__Unicast flood__)
* Process
    * Network Device에서 MAC Address를 비교하고 처리한다
* Transmission (전파 대상)
    * ```One-to-One```
* Bandwidth (대역폭)
    * 여러 대상에게 보내려면 각 각에 Unicast를 생성해서 보내야 되므로, 대역폭을 많이 차지할 수 있다.
* Traffic (트래픽)
    * Target이 지정되어 있어 트래픽이 통제된다.
* https://ko.wikipedia.org/wiki/%EC%9C%A0%EB%8B%88%EC%BA%90%EC%8A%A4%ED%8A%B8?msclkid=efbe0e3ab9fb11ecb78c27c2dd663e7b
</br>

## Multicast
* Basic
    1) 
* Transmission (전파 대상)
    * ```One-to-Many (Group)```
* Process

* Bandwidth (대역폭)
    * Group을 지정함으로써 대역폭이 효율적으로 사용된다.
* Traffic (트래픽)
    * Group을 지정함으로써 트래픽이 통제된다.
    * 트래픽이 통제가 되기 때문에 빠르다.
</br>


## Broadcast
* Basic
    1) 
* Transmission (전파 대상)
    * ```One-to-All```
* Management (관리)

* Bandwidth (대역폭)

* Traffic (트래픽)
    * 불필요한 트래픽이 생성된다.
    * 너무 많은 트래픽이 생성되면 느려진다.
</br>


## Anycast
* Basic
    1) 
* Transmission (전파 대상)
    * ``````
* Management (관리)

* Bandwidth (대역폭)

* Traffic (트래픽)

* Process Speed (속도)


</br>
</br>



### Reference
* https://techdifferences.com/difference-between-broadcast-and-multicast.html#:~:text=Broadcasting%20allows%20the%20transmission%20of%20the%20packet%20to,The%20relationship%20between%20source%20and%20destination%20is%20one-to-many.?msclkid=3d091947b96c11ecb33573fecd99d4e5
* https://m.blog.naver.com/wnrjsxo/221250742423?msclkid=1c2d5dc4b97111ec8f8ace189f6166ff