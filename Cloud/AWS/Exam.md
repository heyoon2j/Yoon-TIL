## 틀린거/못푼거 정리
* #1
* #3
* #4
* #6
* #7
* #8
* 


> 색인 문제 : #10

## 자주 나오는 항목
* URL : Host가 아닌 Path 기반을 의미
* 



## Service


* InternetGateway
* Egress-only Internet gateway :
    - 송신만 가능
* NAT Gateway
    - IPv4/IPv6 모두 지원

* Load Balancer : 


* Global Accelator : 
    - Public IP를 기본적으로 가지고 있다. 그렇기 때문에 서버는 InternetGateway가 필요없으나 인터넷 통신이 흐르고 있다는 의미에서 InternetGateway를 추가한다.


* Direct Connect
    - 기본 IPSec VPN 지원 MACsec은 설정으로 가능 (switch or router가 지원해야함)
    - MACsec 보안(IEEE 802.1AE) : Layer 2 보안 (ref : https://aws.amazon.com/ko/blogs/networking-and-content-delivery/adding-macsec-security-to-aws-direct-connect-connections/)
    - IPsec VPN : Layer 3 보안


* CDN : 

* PrivateLink (Endpoint Service) : VPC를 비공개로 연결하는 서비스
    - 단, 뒤쪽에 LB(ALB, NLB, GWLB)가 있어야 한다.



### Security 
* WAF : Web Application Firewall
    - AWS CloudFront
    - AWS API Gateway
    - AWS Application Load Balancer
    - AWS AppSync GraphQL API
    - AWS Cognito user pool
    - AWS App Runner service
    - AWS Verified Access instance
* Shield : DDoS 방어
    - WAF와 같이 구성될 필요 없다
    - 
* Network Firewall : Network
    - S3 / CloudWatch Logs / Kinesis Firehose


### IPv6
* VPC & Sunbet 활성화
* NAT Gateway


### Monitoring
* VPC Flow Logs
    - VPC Flow Logs vs Traffic Mirroring : 패킷 내부 정보까지 알 수 있냐 없냐 차이
    - S3 / CloudWatch Logs / Kinesis Firehose
* Traffic Mirroring
    - TCP dump
    - WireShark


* Kinesis Data Firehose
    - S3 / Redshift / OpenSearch / API Gateway 등으로부터 로그를 가지고 온다.




### Layer


* Layer 7
    - Application Protocol : HTTP / FTP / SSH /
    - Security Protocol : HTTPS(HTTP) / FTPS(FTP) / SFTP(SSH) / 
    - Routing Protocol : ARP / DHCP / BGP / LLDP
* Layer
* Layer 4 
    - Security Protocol : IPsec VPN
    - Routing Protocol : ARP / DHCP / BGP / LLDP    
* Layer 3 
    - Security Protocol : IPsec VPN
    - Routing Protocol : ARP / DHCP / BGP / LLDP
* Layer 2
    - Security Protocol : MACsec
    - 
* Layer 1 : 



### Information 
* LAG : 링크 집계 그룹. 