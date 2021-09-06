# Route 53
* Domain Name System 서비스

## Route53 기능
1. __Domain 등록__
    * 도메인을 구입 후, Route53에 도메인을 등록시킨다.
    * 등록이 끝나면, AWS는 도메인 등옥 대행자에게 사용자의 정보를 전송한다.
    * 등록 대행자는 사용자 정보를 도메인의 등록 기관으로 전송. 등록 기관은 .com과 같은 하나 이상의 최상위 도메인의 도메인 등록을 판매하는 회사이다.
    * 등록 기관은 자체 데이터베이스에 사용자의 도메인에 관한 정보를 저장하고 일부 정보는 퍼블릭 WHOIS 데이터베이스에도 저장한다.
3. __Resource Rouing__
4. __Health Check__
</br>


## Domain 등록
* __Domain name__
    * 사용자가 웹 사이트 또는 웹 애플리케이션에 액세스하기 위해 웹 브라우저의 주소 표시줄에 입력하는 이름.
    * example.com과 같은 이름.
* __Domain registrar__
    * 도메인 등록 대행자.
    * 국제 인터넷 주소 관리기구(ICANN)가 인증한 특정 최상위 도메인(TLD) 등록을 처리하는 회사로, 리지스트리의 데이터베이스에 등록 데이터를 등록한다.
    * 예를 들어 Amazon Registrar, Inc.는 .com, .net, .org 도메인의 등록 대해자이다. Amazon의 등록 대행 협력사인 Gandi는 .apartments, .boutique, .camera 등 수백 가지 TLD의 도메인 등록 대해자이다.
* __Domain registry__
    * 도메인 레지스트리.
    * 특정 최상위 도메인을 가진 도메인을 판매할 권리를 소유한 회사로, 등록된 도메인의 데이터베이스를 유지 관리하는 기관.
    * 등록된 도메인이 인터넷 상에서 접속될 수 있도록 최상위 도메인(TLD)의 도메인 네임 서버(DNS)를 운용하고 있다.
* __Domain reseller__
    * 도메인 대리점.
    * Registrar를 경유하여 도메인 등록 업무를 대행하는 업자로 Route 53이 해당한다.
    * 리셀러는 SRS(Shared Registry System)이라는 도메인 관리 시스템의 접속 권한이 없기 때문에 반드시 레지스트라를 경유하여 도메인을 관리하게 된다.
* __Domain TLD__
    * top-level domain
    * .com, .org 또는 .ninja 등 도메인 이름의 마지막 부분으로 최상위 도메인을 의미.
    * TLD 유형
        1) __일반적인 최상위 도메인__
            * 해당 TLD를 보면 해당 웹사이트가 어떤 사이트인지 알려준다.
            * 예를 들면 .bike라는 TLD를 가진 도메인은 모터사이클 또는 자전거 업체나 조직의 웹 사이트와 연관된 경우가 많다.
        2) __지리적 최상위 도메인__
            * 해당 TLD는 국가나 도시같은 지리적 영역과 연관된다.
</br>

### Domain 등록 과정
1. 도메인 

</br>


## Record 작업




## Global Accelerator
* DNS 장애조치를 빠르게 제공하여, 제공하는 네트워크에 뛰어난 복원력을 제공한다.
* 또한 사용자와 애플리케이션의 캐싱 문제를 방지하고 거의 즉각적으로 트래픽을 정상적인 엔트포인트로 리디렉션한다.
* Anycast부터 AWS 엣지 로케이션까지 정적 IP 주소를 사용하는 Global Accelerator는 고정 진입점 주소를 제공하여 사용자와 가까운 엣지 로케이션에서 트래픽을 수신하도록 한다.



## Routing option
1. __Simple Routing Policy__
    * 도메인에 대해 특정 기능을 수행하는 하나의 리소스만 있는 경우에 사용
    * 단일 리소스로 라우팅
2. __Weighted Routing Policy__
    * 사용자가 지정하는 비율에 따라 여러 리소스로 트래픽을 라우팅하려는 경우
    * 주로 A/B 배포 전략과 같은 테스트에서 사용될거 같다.
3. __Latency Routing Policy__
    * AWS Region에 트래픽을 라우팅하고 왕복 시간이 짧은 Region을 사용하여 최상의 지연 시간을 제공하는 Region으로 라우팅하려는 경우
4. __Failover Routing Policy__
    * Active-Pasive 장애 조치를 구성하려는 경우 사용
5. __Geolocation Routing Policy__
    * 지리 위치 라우팅 정책
    * 사용자의 위치에 기반하여 트래픽을 라우팅하려는 경우에 사용
6. __Geoproximity Routing Policy__
    * Traffic Flow Only
    * 지리 근접 라우팅 정책
    * 리소스의 위치를 기반으로 트래픽을 라우팅하고 필요에 따라 한 위치의 리소스에서 다른 위치의 리소스로 트랙픽을 보내려는 경우에 사용
7. __Multivalue Answer Routing Policy__
    * 다중값 응답 라우팅 정책
    * Route53이 무작위적으로 선택된 최대 8개의 정상 레코드로 DNS 쿼리를 응답하게 하려는 경우
</br>

### Route53 vs ELB
1. ELB는 여러 AZ 간에 트래픽을 분산하지만, 여러 리전에는 분산하지 않는다. Route53은 여러 리전에 트래픽을 분산할 수 있다.
2. Route53과 ELB 모두 상태 확인을 수행하고 정상적인 리소스로만 트래픽을 라우팅한다. 그러나 DNS는 캐시되어 unhealty 대상이 한동안 방문자 캐시에 남아 있게 된다. 반면에 ELB는 캐시되지 않으며 대상 그룹에서 unhealty 대상을 즉시 제거합니다.
> Region, AZ 기준으로 Traffic 분산을 한다.