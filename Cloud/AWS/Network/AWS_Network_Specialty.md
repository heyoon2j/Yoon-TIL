## 시험 정리

## 키워드 공부
### 네트워크 고성능 처리
-  EBS Optimized Instance
-  배치 그룹
- AWS EFS (Elastic Fabric Adapter) : 소프트웨어가 직접 HW


### TGW 라우팅 분석
    - 글로벌 통신 라우팅 분석
    - TWG 라우팅 변경 확인 및 알람 가능


### 인증서
- EC2에는 ACM의 인증서를 적용할 방법이 없다. Nitro Enclave에 연결된 EC2 인스턴스 가능
- ACM 인증서 생성 : CloudFront / ALB


### 트래픽 증가
- TGW <---> Site-to-Site VPN ECMP 구성


### VPN ECMP support (Tunneling)
- TGW - Site-to-Site VPN만 지원
- VGW - Site-to-Site VPN은 미지원
- 각 VPN 터널은 동일한 BGP를 보내게 되는데, 이때 비대칭 라우팅이 발생할 수 있다!! ---> 이를 위해 고객 게이트웨이의 가상 터널 인터페이스에서 비대칭 라우팅이 활성화
- ECMP가 없고 Active/Active 형태로 쓰고 싶다면, Prefix 수정, AS_PATH, MED 설정



### 암호화 / SSL / TLS
- Client <---> Backend 암호화 유지 : NLB / TCP / Backend에서 인증서 처리 (ACM 사용 불가)
- IPSec : Site-to-Site VPN 
    - VGW - Site-to-Site VPN -CGW
    - Private VIF는 실질적으로 IPSec VPN이 아니다! NONONO
    - VGW - Site-to-Site VPN - Public VIF(DX Connect) - CGW
    - TGW - Site-to-Site VPN - Public VIF(DX Connect) - CGW
    - TGW - Site-to-Site VPN - DXGW - Transit VIF(DX Connect) - CGW
- MACSec : 
    - 새로운 Connection / LAG 생성 필요
    - 연결 키 (CAK)와 연결 키 이름(CKN)을 연결


### 리소스 규정 준수 / 기록
- AWS Config + SSM Automation Runbook


### 알람
- CloudWatch 지표 + 알람 --> EvnetBridge --> Lambda
- CloudWatch Logs + 지표 필터 + Lambda
- VPC Reachability + SNS + Lambda
- GuardDuty + Lambda


### DDoS
- AWS Shield


### Traffic 검사
- 여러 가용 영역에 대한 간혈적 연결 문제 : 비대칭 라우팅 문제

- 트래픽 미러링(Traffic Mirroring) : 
    - ENI로 들어오는 트래픽을 설정한 대상(ENI or NLT or GWLB)에게로 트래픽을 전달
    - VXLAN 프로토콜을 사용하여 트래픽을 전달
    - ENI / NLB / GWLB
- GWLB
- WAF : 
    - AWS CloudFront
    - AWS API Gateway
    - AWS Application Load Balancer
    - AWS AppSync GraphQL API
    - AWS Cognito user pool
    - AWS App Runner service
    - AWS Verified Access instance


### 비대칭 라우팅
- GWLB는 자동으로 고정세션이 설정되어 있어 비대칭 라우팅을 해소할 수 있다.
- TGW Appliance mode 활성화
- 그 외 설정에 대해서는 라우팅 시, BGP 설정을 통해 비대칭 라우팅을 막아야 한다.


### 온프레미스 네트워크가 승인된 IP에 대한 액세스를 제한하는 경우에도 사용
- Private NAT Gateway
    - VGW / TWG 로 연결되어 있어야 한다.
    - 고유 IP 존재ㅈ


### 전세계 빠르게 전달
- AWS 서비스 : Global Accelerator / CloudFront / Route53
    - TCP : GA / HTTP : CF
    - IP 주소 : GA
    - 캐싱/보안 : CF
- Global Accelerator vs CloudFront
    - TCP, IP 기반 : Global Accelerator
    - HTTP/HTTPS, 캐싱/보안 : CloudFront
- Global Accelerator vs Route53
    - Application 속도 최적화
    - 좀더 빠르고, 가용성이 높은거는 GA라는 얘기네
    - 사용자 경험을 최적화하는 데 사용



### 중앙 공유 서비스
- PrivateLink : Only TCP / NLB, GWLB 연결 가능


### IPv6
- Egress-Only Internget Gateway : IPv6 아웃바운드 트래픽만 허용하고 싶을때 사용
- VPC : IPv4/IPv6 모두 지원. 기존 VPC에 IPv6 CIDR을 추가 필요 (새로 생성 필요 X)
- TGW : IPv4/IPv6 모두 지원
- Site-To-Site VPN : IPv4, IPv6 모두 지원 불가. VPN에서 IPv4 또는 IPv6 중에 하나를 선택해야 한다!
    - VGW <---> VPN 자체로는 IPv6 지원 불가
    - TGW <---> VPN 에 대해서만 IPv6 지원 가능
- DX Transit VIF : 피어링에 IPv6 추가 가능. 그 외의 설정에 대해서는 전부 새로 생성해야 한다


### Log
- 보통 로그는 다음에서 설정 가능. S3 / CloudWatch Logs / Kinesis Firehose + OpenSearch
- ALB : S3 (액세스 로그 / 연결 로그)
- CloudTrail : S3 / CloudWatch
- VGW / CGW : Log가 남지 않는다. VPC Flow Logs


### 네트워크 서비스 비교
- Peering : 125 VPC (같은 AZ 기준으로 비용이 저렴)
- PrivateLink : N >= 1000 VPC
- TGW : 5000 VPC
- DXGW : 100 VGW


### BGP 개수
- VPC : 100
- TGW : 5000
- DX : 100


### 라우팅 테이블 개수
- VPC : 50 ~ 100
- TGW : 10000 ~ 20000
- DX : 100


### 모니터링
- VPC Flow Logs
    - VPC, Subnet
    - S3 / CloudWatch Logs / Kinesis Firehose + OpenSearch
    - IP만 검색 가능
    - pkt-srcaddr는 NAT 적용 전의 원래 주소, srcaddr는 NAT 적용 후의 주소를 나타냄
- VPC Traffic Mirroring : 패킷 분석
- Reachability Analyzer : 특정 대상 : Web server
    - Instance
    - ENI
    - IGW
    - TGW
    - TGW Attachment
    - VPC Endpoint
    - VPC Peering
    - VGW
- Network Access Analyzer : 특정 자원 : EC2 instance
- Network Manager Route Analyzer :  TGW 라우팅 변경에 따른 알람


### 보안
* Security Groups : Instance
* Network ACL : Subnet
* Network Firewall : Layer 3-7 - AWS Network Appliance
    - Stateful Firewall : TCP connection의 상태를 보고 판단하는 방화벽
    - Stateless Firewall : 
    - 특징
        - Stateless/ACL L3 rule
        - Statefule/L4 rule
        - IPS-IDS/L7 rule
        - FQDN Filtering
        - Protocol detection
        - Large IP block/allow list
        > Deep packet inspection : 즉 packet의 Header / Payload 등 정보를 확인할 수 있다!
    - 구성 요소
        - Firewall
        - Firewall policy : Firewall 정책. 각 특성에 해당하는 정책 Rule을 하나의 그룹으로 묶고(Rule Group), 원하는 Rule Group들을 선택하여 하나의 Policy 생성
        - Rule Group : Stateless 또는 Stateful 규칙들의 모음
    - Log : S3 / CloudWatch Logs / Kinesis Firehose
* Gateway Load Balancer : 3rd-party - Network Appliance 사용을 위한 LB
    - Layer 3 동작 (Network Layer)
    - GENEVE 프로토콜을 통한 캡슐화 (UDP port 6081)
    - 모든 IP와 모든 포트에 대하여 허용 (즉, SG 지원 X)
    - Public DNS를 지원 X
    - Only IPv4 지원
    - MTU size of 8500 byte
* Shield : DDoS 방어
    - Stardard
        - 기본적으로 Standard는 모든 AWS에 무료로 지원
    - Advance
        - $3000 per moneth per organization
        - AWS SRT팀에 Support를 받을 수 있다.
        - 
* WAF : Web Application Firewall / Layer 7 방화벽
    - AWS CloudFront
    - AWS API Gateway
    - AWS Application Load Balancer
    - AWS AppSync GraphQL API
    - AWS Cognito user pool
    - AWS App Runner service
    - AWS Verified Access instance
    - 보안 규칙
        - Whitelist
        - Blacklist
        - SQL Injectioin
        - XSS
        - HTTP Flood
        - Scanner & Probe
        - IP Reputation Lists
        - Bad Bot
    - 
    - WCU 
* Firewall Management : AWS 보안 서비스 관리. 여러 계정에 대하여 동일한 거버넌스를 세우기 위해 사용
    - 서비스
        - WAF
        - Shield Advanced
        - Security Group
        - Network Firewall
        - Resolver DNS Firewall
        - 3rd-party solution (Marketplace)
    - 미리 활성화가 필요한 서비스 : Organization, Config, Resource Access Manager(RAM)

* AWS ACM : 공용 인증서 관리
    - Amazon Trust Service에 의해서 인증받고 있음
    - 서비스 : CloudFront, ELB, API Gateway, ElasticBeanstalk
    - CloudFront만 us-east-1
    > 인증서 자동 업데이트 필요!
* AWS Private CA : 내부용 인증서 관리
* DNSSEC validation : DNS 인증서 관리
* AWS Inspector : 평가 대상(EC2 Instance, ECS Container, AMI, S3)을 계속 모니터링하면서 "애플리케이션"의 보안 및 규정 준수 요구사항을 평가
    - OS 취약점
    - 소프트웨어 취약점
    - 네트워크 취약점
* AWS GuardDuty
    - 로그 분석
    - 악성 및 비정상 활동 탐지
    - 보안 경고 생성 및 대응 조치 권고


### Bandwidth limits
1. AWS Network Service Internal (with region)
    - VPC : No limits
    - Internet Gateway : No limits
    - VPC Peering : No limits
2. AWS Network Service External
    - VPN (per Tunnel) : 1.25 Gbps
    - DX : 1 Gbps / 10 Gbps / 100 Gbps (포트를 여러개 둠으로써 1 ~ 300 Gbps 선택 가능)
3. Instance
    - NAT Gateway : 3 ~ 45 Gbps 
    - EC2 : 기본적으로 인스턴스 타입마다 제공되는 Bandwidth가 다름. 다른 리전이거나 Internet Gateway를 통과하거나 DX를 통과하는 경우, 32 vCPU 이상 사용하는 EC2 기준으로 제공된 인스턴스 타입 Bandwidth의 50% 까지만 사용 가능. 32 vCPU 미만인 경우 5 Gbps.
        1) One flow limit with Intel 82599 VF interface : 5 Gbps
        2) One flow limit With ENA not in placement : 5 Gbps 
        3) One flow limit with ENA in placement : 10 Gbps


---
## 네트워크 서비스

### VPC
- NAT Gateway는 Log 설정이 없다
- Egress-Only Internget Gateway : IPv6 아웃바운드 트래픽만 허용하고 싶을때 사용
- BYOIP Pool은 Cloud-init을 통해서만 연결 가능..



### Peering
- 동일한 AZ인 경우 Peering은 무료로 제공된다
- 그렇기 때문에 VPC 통신 중에 가장 저렴하다!!!



### NAT
- NAT Instance IPv6 지원 X

### NAT Gateway
- 액세스 로그 설정은 없다!!!! 아무 설정이 없어, 그냥 가는 거야
- Security Group을 가지고 있지 않는다. 오직 NACL로만 가능
- IPv4/IPv6 모두 지원
- Public Subnet에만 생성 가능
- 350s Idle-timeout이 있으므로, 인스턴스에서 350초 미만의 값으로 TCP keepalive를 활성화


### Private NAT Gateway
- 온프레미스 네트워크가 승인된 IP에 대한 액세스를 제한하는 경우에도 사용
- VGW / TWG 로 연결되어 있어야 한다.
- 고유 IP 존재



### 로드밸런서 (LB)
- 경로(Path), 호스트 기반 라우팅 : ALB
- 사용자 IP 유지 : ALB - X-Forward-For / NLB - Client IP 보존
- 

### NLB


### ALB
* 보안 정책 : 
    - TLS : 일반적인 네트워크 암호화 통신
    - FIPS : 미국 연방 정부의 정보 처리 시스템을 위한 보안 표준
    - FS Security Policy (순방향 보안 정책) : 세션 키 보안

### GWLB
- 고정 세션을 기본적으로 제공한다!
- GENEVE 캡슐화 사용
- GWLB의 Security Group(SG)는 없다.



### TGW
- Network Manager를 사용하여 TGW에서 발생하는 이벤트를 CloudWatch로 전달 가능!!!
- Connect Attachment 
    - SD-WAN 연결
    - GRE & BGP (Onlye BGP만 가능!! Static 불가능!)
    - 
- Multicast : TCP가 아닌 UDP 통신
    - 정적 소스 도메인 지원
    - IGMPv2 지원 : Internet Group Management Protocol. 동적 소스 도메인 지원 프로토콜

- Accelerator VPN
    - 생성 시에만 설정 가능!!
    - TGW Attachment 타입에 대해서만 적용 가능


### DX
- Connection은 생성후 포트 속도 변경 불가능 X. 그 외 모든 설정은 전부 새로 생성
- SLA 99.9%를 위해서는 2개의 DX Location 사용 필요
- VIF
    - Prefix 개수 : Public 1000 / Private 100 / Transit : 100
- LAG
    - VIF 신규 생성 필요
    - 

### DXGW
- Private VIF와 Transit VIF는 동일한 DXGW에 같이 연결할 수 없다.



### VPC Endpoint
- VPC 엔드포인트 통합시, Private DNS 비활성화 필요
- Route53 Private Hosted Zone 생성 필요


### Route53
- 상태 확인
    - Private Zone : EC2 StatusCheckFailed 지표의 상태를 확인하는 CloudWatch 지표를 설정하고 지표에 경보를 추가한 다음 경보 상태를 모니터링하는 상태 확인을 구성합
    - 상태 확인이 없는 기록은 항상 건강한 것으로 간주
    - Endpoint 상태 체크 : Public IP 사용하는 경우 사용 가능
    - CloudWatch : Private IP인 경우, CloudWatch 지표 및 경보 추가 필요

- 정책
    - 지리적 : 해당 위치에 있는 곳을 찾아감 (각 지역마다 특정 서비스를 제공할때 사용)
    - 지연시간 : 낮은 지연시간을 제공하는 위치로 찾아감
- Logging
    1) DNS Query Logging : Public Hosted Zone에서 발생한 쿼리에 대한 로그
    2) Resolver Query Logging : Private Hosted Zone. VPC 내에서 만들어지는 모든 쿼리에 대한 로그
    3) CloudWatch - Public Hosted zone : AWS/Route53 - DNSQueries(쿼리수) / DNSSEC InternalFailure(1)
- 규칙
    - 전달 규칙 : 특정 도메인에 대해서는 특정 IP로 쿼리를 전달한다
    - 시스템 규칙 : 특정 도메인에 대해서는 시스템 Route53을 사용한다



### Global Accelerator
- 기본적으로 클라이언트 IP를 보존하므로, 인터넷에 대한 모든 IP를 Open, 아닌 경우 GA의 Endpoint 적용
- Target : ALB / NLB / EC2 Instance / EIP
- 



### CloudFront
- 통신 순서 : (CloudFront Function) - Edge Location - (Lambda@Edge) - Origin



### EC2
- EC2에 대한 인증서는 ACM을 통해 생성 불가
- 시작 템플릿에서 BYOIP 선택 불가능


==========================================================================
틀린 문제!

# 85번 문제
- old -> new 프로토콜로 마이그레이션 중, 가동중지 시간 없이 프로토콜을 사용하는지 확인하는 방법은?
    - VPC Flow Logs + Athena 조합
    - AWS Inspector + Network Reachability