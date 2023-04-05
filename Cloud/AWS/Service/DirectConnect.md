# Direct Connect
AWS와 On-premise 간의 전용 네트워크를 제공하는 서비스.
</br>

## 구성 요소
* Connect : 물리적인 전용선 (KINX 등 통해 전달받음)에 대한 Object
* Direct Connect Gateway : AWS의 DX용 Gateway
* Virtual Interface : AWS와 외부 네트워크 간의 통신을 위한 네트워크 인터페이스
    * AWS 측의 연결 정보 설정이라고 생각하면 된다.
    * DX Gateway <---> Virtual Interface <---> Connect
    * VLAN / BGP ASN 확인 필요
