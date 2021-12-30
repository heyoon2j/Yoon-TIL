## Basic
* virginia / ireland가 가장 오래되었고, 모든 서비스가 가장 먼저 나온다. 그렇기 때문에 가격이 가장 싸서, Test하는 경우, 해당 Region에서 하는 것이 효율적이다.
* DR (장애 복구): 은행권에서는 DR에 대해, 메인 서비스에 70% 가용률을 가지고 있게 사용한다. 정보보호법에 의해서 IDC 간에는 몇 km 떨어져 있는지가 있어 확인하는 것이 좋다.
    * KT 목동(a), SK브로드밴드 일산(b), 현대정보기술 데이터센터 용인(c), LG U+ 평촌 IDC
    * AZ는 100KM 위치 이내에 만들어짐
    * 목동 - 일산 : 18km
    * 목동 - 용인 : 42.8km
    * 일산 - 용인 : 60.4km
    * 목동 - 평촌 : 17.7km
    * 일산 - 평촌 : 36.2km
    * 보통 HA 구성하기 위해 선택할 가용영역 (일산(), 용인(c))
* AWS backbone 망을 잘 사용해야 된다. 그러면 글로벌 연결을 쉽게 할 수 있다.
* AWS 보안: 책임 공유 모델
    * SA에 대한 모든 것은 AWS에 책임
    * 하지만 그 위에 올라가는 서비스들은 우리 책임
* AWS 이점: 보안, 안정성, 성능, 비용 절감, 운영 우수성

* https://docs.aws.amazon.com/ko_kr/AWSEC2/latest/UserGuide/concepts.html

## Network
* EC2의 IP는 고정 IP가 아니다. 그렇기 때문에 
* 나중에 TCP의 경우, 한쪽 방향을 Traffic을 제어할 필요가 있어서 NAT 게이트웨이가 아닌 다른 통신을 이용하여, 한쪽 방향으로 흘릴 필요가 있다 (Landing Zone, 내가 Traffic을 제어하겠다, 통과할 수 있는 곳으로 가게 하겠다)
* 요즘에는 VPC의 CIDR를 추가할 수 있기 때문에, 처음에 작게 만들어도 된다. 하지만 줄일 수는 없다. 이유는 기존에 IP 기준으로 Network ID, Broadcast IP 등을 이미 잡고 있는데, AS, ELB 등이 해당 IP를 어떤 것으로 잡고 있는지 모르기 때문이다.
* B Class는 너무 크다. 나중에 IDC와 연결된다고 하면, 둘이 충돌날 가능성이 있다. 그렇기 때문에 CIDR를 잡을때 신중하게 잡아야 한다. Editor가 생겼기 때문에 C Class 정도. 하지만 Auto Scaling, Docker를 사용해야 되는 경우는 IP가 많이 필요하기 때문에 B Class 정도로 잡아도 된다. MAX치를 이용해서 잡아야 한다 (26bit?)
* RFC 1918에 정의된 사설 IP 대역 사용 권고, 하지만 보통 IDC와읭 연동을 위해 10.0.0.0을 많이 사용한다.
* CIDR를 잡을 때는 IDC와 VPN과의 연동을 잡아야 한다.
* 그렇기 때문에 처음에 SA가 나가면 __IDC/VPN__을 잡아야 한다.
* VPC 내에 생성된 모든 서브넷은 기본적으로 local Router를 통하여 상호 통신이 가능하다.
* Internet Gateway: NAT
* NAT Gateway에 Elasic IP가 있어야 되는 이유는 고정 IP를 당연히 써야되지 않을까?
* AWS VPN, Direct Connect(전용선)
* Direct Connect 사업하는 곳은 망구축 사업을 하는 곳은 KINX, LG U+이다. 그렇기 때문에 이쪽에 연락해야 된다.
* VPN은 홉이 하나이고, DX는 라우팅에 연결되기 때문에 여러 개로 통신할 수 있다.
* Landing이 가능한 이유는 TGW와 VGW 때문인데, TGW는 1GB 이상이어야 사용할 수 있다.
* Transit VPC는 기존 마켓플레이스에서 Cisco의 제품을 구입하여 EC2에 올리면 이게 Transit VPC로 사용됨. 그리고 이렇게 하나에서 나머지가 다 연결된 상태가 Transit GW로 사용됨.
* Endpoint라는 Domain 형식의 naming이 붙는다. 모든 것은 도메인으로 붙는다. 이유는 AS 등 사용할 때 IP로 하면 장애 발생 시, 처리할 수 없기 때문에 도메인 호출로 사용한다(Auto Fix).
    * VPC 밖은 전부 인터넷이다. 하지만 이렇게 되면 돈이기 때문에, 이거를 처리할 수 있게 endpoint를 만들었다.
    * 그렇기 때문에 이거를 내부 네트워크로 변경하였고, DNS를 이용하여 연결 한다.





