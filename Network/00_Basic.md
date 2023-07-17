## Link-local address
기존에는 장치가 연결해야할 서버를 찾지 못하거나 연결하지 못했을 때를 위해
* 호스트에 연결되어 있는 Subnetwork(Broadcast Domain)를 통해서만 통신할 수 있는 주소
* 예약된 CIDR 값 : 169.254.0.0/16
* Lik-local address는 Stateless address autoconfiguration or Link-local address autoconfiguration 를 통해서 할당 받는다 (보통 DHCP 서버 통해서)
* 라우터를 통해서 통신하게 된다.
