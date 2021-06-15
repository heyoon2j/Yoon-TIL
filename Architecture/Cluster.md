# HA
* High Availability, 고가용성
* 시스템 에러가 발생 시, 얼마나 빠른 시간 내에 복구되어 정상적으로 동작할 수 있는지를 
분석하는 척도
* 고가용성 구현하기 위한 기술 & 방법
    * RAID
    * Load Balancing
    * Auto Scaling
    * Clustering

## RAID


## Clustering
* 여러 개의 컴퓨터를 연결한 병렬 시스템으로 마치 하나의 컴퓨터처럼 사용하는 것
* 클러스터링은 기본적으로 Virtual IP를 기반으로 구현되고, 서비스를 제공하는 실제 장비가 물리적인 IP를 가진다.
    * 데이터의 처리는 Virtual IP를 사용함으로써, 내부 시스템의 통신을 가린다.


## Load Balancing
* 부하 분산을 위해 여러 서버에 트래픽을 분배하는 기능
* 복잡함이 늘어나기 때문에 필요 시에 사용되야 한다.

### 주요 기술
* NAT (Network Address Translation)
    * Private IP를 Public IP로 변환시켜 준다.
    * A 네트워크 망 IP <-> B 네트워크 망 IP 변환
* DSR (Dynamic Source Routing Protocol)
* Tunneling
    * 네트워크 상에서 가상의 통로를 생성해 서로 통신할 수 있게 하는 기술
    * Encapsulation한 데이터를 전송하고, 도착 후 데이터를 Decapsulation하는 전체 통신 과정
        * 주의해야 될 것은 기존 OSI 7 계층에서 사용하는 캡슐화와 터널링은 차이가 있다는 것이다.
         캡슐화는 OSI 7 계층을 참조하기위해 상위 계층 데이터를 하위 계층에서 포장하는 개념이고,
          터널링은 프로토콜을 숨기기 위해 사용된다.

    
### LB 사용 시, 해결해야 할 문제
* 세션 데이터를 관리
    * 해결방법
        1. 세션을 고정
        2. 쿠키를 사용
        3. 캐시를 이용



#### Reference
* https://asfirstalways.tistory.com/320
* https://m.blog.naver.com/ccw928/220610767590
* https://blog.naver.com/pyoyr/50031514702

