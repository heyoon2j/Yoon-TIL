# ARP Protocol
* Address Resolution Protocol
* 주소 결정 프로토콜. 네트워크 상에서 IP 주소를 물리적 네트워크 주소로 Bind 시키기 위해 사용되는 프로토콜이다.
    * 즉, Layer 3 Address(IP) -> Layer 2 Address(MAC) 변환하는 기능 제공
</br>
</br>


## Why network uses ARP protocol
* 왜 ARP protocol이 사용되는가? 이를 위해서는 IP, MAC Address를 정확히 알 필요가 있다. 정의는 다음과 같다.
    * IP Address : 라우팅을 하기 위해 사용. 즉, Target에 대한 네트워크를 추적하고, 최단 경로를 선택하기 위해서 사용.
    * MAC Address : 이더넷을 이용하기 위해 사용. 같은 네트워크에서의 통신을 위해 사용.
> 같은 네트워크를 사용해도 IP를 사용하여 설정하기 때문에, 우리는 IP를 Computer의 주소로 생각하지만 IP는 NIC(Network Interface Card)에 연결되어 있는 회선 주소이다. 실질적으로 같은 네트워크에서 통신을 할때는 MAC Address를 기준으로 통신하게 된다. 그렇기 때문에 ARP protocol을 통해 IP Address는 대응되는 MAC Address로 변환 작업이 필요하다(또는 확인 작업이 필요하다).
> 그렇기 때문에 이러한 목적에 의해 보통 MAC Address를 모르는 Target의 경우, ARP packet을 broadcasting 한다. 그리고 해당 정보는 APR Cache Memory에 Table 형태로 저장된다. MAC Address는 알지만 IP Address를 모르는 경우 __RARP Procotol__ 을 사용한다.
</br>

### GARP (Gratuitous ARP)
자신의 IP를 타켓으로 ARP 요청하는 것을 의미한다. 이유는 아래와 같다.
1. IP 주소 충돌 감지
2. ARP Table 갱신 : 동일 서브넷에 존재하는 라우터의 ARP Talbe을 갱신(Refresh)하기 위해 사용.
3. VRRR/HSRP 프로토콜에 사용


### Reference
* https://www.stevenjlee.net/2020/06/07/%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0-arp-address-resolution-protocol-%ED%94%84%EB%A1%9C%ED%86%A0%EC%BD%9C/