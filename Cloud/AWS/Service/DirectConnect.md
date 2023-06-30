# Direct Connect
AWS와 On-premise 간의 전용 네트워크를 제공하는 서비스.
</br>

## 구성 요소
* Connect : 물리적인 전용선에 대한 Object
    > 제공 업체와 협의를 통해 진행
* Direct Connect Gateway : AWS의 DX용 Gateway
* Virtual Interface : AWS와 외부 네트워크 간의 통신을 위한 네트워크 인터페이스
    * AWS 측의 연결 정보 설정이라고 생각하면 된다.
    * DX Gateway <---> Virtual Interface <---> Connect(전용선 제공 업체)
    * VLAN / BGP ASN 확인 필요
    > KINX 등 제공 업체에게 설정한 VLAN, BGP 전달
