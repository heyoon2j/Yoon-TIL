# Network

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



---
## Network Performance and Optimization
* Bandwidth : 초당 전송 가능한 비트
* Latency : 첫 패킷이 두 지점간의 통신하는데 걸리는 시간
* Jitter : Variation in inter-packet delays. 패킷 간의 지연 시간
* Throughput(bps) : 초당 전송한 비트. 전송한 패킷량, Latency, Packet loss 등이 있기 때문에 Bandwidth와 동일하게 나오지 않는다!
* PPS : Packet Per Second, 네트워크 장비의 초당 패킷 처리량. 컴퓨팅 리소스 파워, Throughput도 영향을 준다(여기서 Packet은 Frame 단위를 의미하여 최소 84 byte 크기를 처리할 수 있다)
    - PPS을 기준으로 보는 이유는 결국 네트워크 장비는 비트가 아닌 패킷을 처리하게 된다. 그렇기 때문에 같은 Source가 같은 Throughput을 가지고 비트를 전송해도 네트워크 장비에 따라 처리하는 패킷 크기가 다르기 때문에 PPS가 달라진다.
    - PPS 계산법
        ```
        1 Packet == 최소 84 byte == 최소 672 bit 
        1 packet/sec == 672 bit/sec
        1 pps == 672 bps
        1/672 pps == 1 bps
        1.488 pps == 1 Kbps
        1488 pps == 1 Mbps
        ``` 
        1) 처리할 수 있는 Packet 크기를 측정 (최대 패킷: MTU)
        2) Second 나누기
        3) 1 bps 에 대한 pps 값을 구한다!
        4) PPS = 1 / Packet_Size * 처리 능력(속도 등)
    > 1 Packet의 Byte 수는 MTU의 영향을 받는다!
* MTU : Maximum Transmission Unit. 보낼 수 있는 가장 큰 패킷
    - 일반적으로 인터넷은 MTU : 1500 bytes 이다.
    - Jumbo Frames : 1500 bytes 보다 큰 프레임
        1) 한번 보낼때 크게 보낼 수 있으므로 적은 패킷량으로 통신 가능
        2) Throughput 증가
        3) AWS 서비스를 사용하는 것들은 Jumbo Frame 지원 (VPN과 같이 인터넷으로 가는 서비스는 불가능)
    > Bandwidth, Latency, Jitter -> Throughput -> PPS, MTU

    > MTU를 통해 얼만큼의 Bandwidth와 Throughput이 필요한지 알 수 있다.

    - Check 방법 : ```$ sudo ip link chow eth0```

* Delay가 길어지는 경우
    - 두 네트워크 사이의 

### Optimization
- Over 1M PPS(1초당 1 백만개 이상의 패킷을 전송)를 의미. 
- High Network Bandwidth and Throughput, Low Laytency
- High I/O performance and Low CPU Utilization
1. Network Device level network optimization
    1) using Jumbo Frame
2. Instance level network optimization
    * Enhanded Networking (Bandwidth & Throughput & Latency)
        1) SR-IOV with PCI passthrough : Physical NIC(Server) 와 Virtual NIC(Hypervisor) 간의 통신을 향상

            ![SR-IOV_PCI : https://learn.microsoft.com/ko-kr/windows-hardware/drivers/network/single-root-i-o-virtualization--sr-iov--interface](../AWS/img/SR-IOV_PCI.png)
            - SR-IOV : Single Root I/O virtualization. 개별 서버의 I/O 자원을 가상화하는데 사용하는 I/O 가상화 인터페이스 또는 어댑터.
            - PCI : Peripheral Component Interconnect. 여기서는 네트워크 인터페이스를 연결하는 인터페이스를 의미한다고 생각하면 된다.
            - SR-IOV 어댑터를 통해 물리적 PCIe(PF: Physical Function)를 여러 개의 가상화 PCIe(VF: Virtual Function)로 분할할 수 있다.
                > Virtualization Layer가 따로 있는 것이 아닌 Network Interface 내부 안에 들어가게 된다. 이를 통해 패킷 프로세싱 Overhead를 줄여 Latency를 줄인다.
            - VM은 기본적으로 통신을 하기 위해 가상화 레이어를 거쳐 물리적 통신을 하게 되는데 이 과정을 간소화시켜준다.
            - 사용 방법은 다음과 같다(드라이버 확인 방법 : ```ethtool -i eth0```)
                - Intel ixgbevf : 10 Gbps
                - Elastic Network Adatpter(ENA) : 100 Gbps / P4d는 400 Gbps
        2) EFA (AWS Service)
    * Increase Throughput (Bandwidth & Throughput & Latency)
        1) EBS Optimized Instance : EBS is not a physical drive. Network drive. EBS와의 통신으로 전용 네트워크를 사용함을써 다른 네트워크와 경쟁하지 않음
        2) 배치 그룹 : EC2 인스턴스끼리 서로 가까운 곳에 둠으로써 네트워크 홉 수 등을 줄임
        3) Network I/O Credit Type 선택
        4) 보안 프로토콜(HTTPS, SSH 등) 미사용 : 보안 프로토콜을 사용하면 패킷을 한번 더 감싸기 때문에 Latency가 발생
3. Operating system level network optimization
    * Enhanded Networking (I/O & CPU)
        1) DPDK (Intel Data Plane Development Kit) library 사용 : Kernel Library로, 원래는 Kernel이 중간에서 네트워크 관련 코드/요청에 대한 Translation이 이루어지지만, 해당 라이브러리를 통해 Translation 없이 다이렉트로 네트워크 장비에 요청함으로써 Overhead를 줄여 OS 내부 패킷 처리 향상시킨다.
            - Kernel bypass
            - Packet Processing 제어
            - 작은 CPU Overhead
</br>


* Bandwidth limits
    1. AWS Network Service Internal (with region)
        - VPC : No limits
        - Internet Gateway : No limits
        - VPC Peering : No limits
    2. AWS Network Service External
        - VPN : 1.25 Gbps
        - DX : 1 Gbps / 10 Gbps / 100 Gbps
    3. Instance
        - NAT Gateway : 45 Gbps 
        - EC2 : 기본적으로 인스턴스 타입마다 제공되는 Bandwidth가 다름. 다른 리전이거나 Internet Gateway를 통과하거나 DX를 통과하는 경우, 32 vCPU 이상 사용하는 EC2 기준으로 제공된 인스턴스 타입 Bandwidth의 50% 까지만 사용 가능. 32 vCPU 미만인 경우 5 Gbps.
            1) One flow limit with Intel 82599 VF interface : 5 Gbps
            2) One flow limit With ENA not in placement : 5 Gbps 
            3) One flow limit with ENA in placement : 10 Gbps



when pps is bottleneck, increased MTU provieds more than throughput






---
## Service
### VPC
* Architecture
* Feature
* 








### VPC Network
* VPC
    - 5개의 IPv4 CIDR Block + 1 IPv6 CIDR Block을 가질 수 있음
    - RFC 1918에 의해 같은 대역대만 추가 가능 (172.16.0.0/24를 가지고 있으면 172.16.0.0/12 범위 내에서 만 추가할 수 있다)
    - RFC 1918에서 정의한 CIDR 범위를 넘기면 안됨 (10.0.0.0/8, 172.16.0.0/12, 196.168.0.0/16)
* IPv6
    - 128 bit = 8 block * 16 bit / 16진수 사용
    - Public IP만 사용 가능!
    - Amazon DNS에서 제공하지 않음
    - NAT Gateway는 지원 / NAT Instance는 지원 X
* DHCP Option Set
    - 기본 DHCP Option Set은 수정이 불가!!!!!!!
    - Domain name server, Domain name, NetBIOS 타입 등의 설정을 정의
    - DHCP Option Set 변경 후, Refres가 되어야 한다. 보통 몇시간 후에 자동 Refresh되나 수동으로도 가능 (명령어 : $ sudo dhclient -r eth0)
    - Route53 Hostzone을 사용하기 위해서는 enableDnsSupport & enableDnsHostname  모두 활성화 필요
* DNS Resolver Server
    - (VPC Base + 2)'s IP
    - 169.254.169.253 : DNS Resolver Server IP의 가상 IP
    - 해당 DNS Resolver Server는 Route53으로 쿼리를 보낸다.
    - 오직 VPC내에서만 접근 가능.
    - Route53은 RDS 등의 AWS 서비스에 대해 활성화된 AZ의 리소스에 대해 자동으로 IP Refresh한다



* InternetGateway
    - 
* Egress-only Internet gateway :
    - 송신만 가능
* NAT Gateway
    - IPv4/IPv6 모두 지원
    - Public Subnet에만 생성 가능
    - Security Group을 가지고 있지 않는다. 오직 NACL로만 가능
* NAT Instance
* Load Balancer : 
    - Application : 7 Layer. HTTP / HTTPS
    - Network : 4 Layer. TCP / UDP
    - Gateway : 3 Layer. IP

* ENI
    - MAC Address를 가지고 있다.
    - Dual-home : 하나의 서버에 네트워크가 다른 ENI를 붙인다.
    - Cross-account : 여기서 말하는 다른 계정은 AWS에서 관리하고 있는 ENI를 의미한다.
* EIP
    - 기본적으로 Public IP는 변경이 된다. 이를 해결하기 위해 EIP를 사용한다
    - BYOIP : Bring Your own IP. 기존에 쓰고 있던 IP들을 가지고 올 수 있다! 대신 차단도니 IP와 같은 이상한 이력이 있으면 안된다. 이를 위해 우리는 ROA(Root Origin Authorization)
* Security Group
    - 
* Network ACL
    - 




---
### Internal Network
* VPC Endpoint
    - Gateway Type : S3 / DynamoDB
    - Interface Type : 그 외 서비스
    - 같은 Region 서비스만 접근 가능!
    - DNS 활성화 (VPC내의 Route 53에만 적용됨)
        1) Region
        2) AZ
        3) Private Service DNS
* PrivateLink (Endpoint Service) : VPC를 비공개로 연결하는 서비스
    - Only TCP
    - 단, NLB, GWLB에만 연결할 수 있다.
    - 수천개의 연결이 가능하며, 높은 보안성을 가지고 있다.


### Other Network
* VPC Peering : 
    - VPC <---> VPC 간의 1:1 관계이고, 전이적 관계를 지원한지 않음
    - VPC 피어링 연결을 통한 모든 데이터 전송은 무료이며, AZ를 가로지르는 VPC 피어링 연결을 통한 모든 데이터 전송은 계속 리전 내 표준 데이터 전송 요금이 청구된다.
    - 최대 125개의 피어링 연결이 가능
* Transit Gateway :
    - 주의 사항
        - 활성화한 AZ에서만 통신이 가능해 진다 (ex> Attachment를 A-zone 생성 시, A-zone에서만 통신이 가능)
    - Architecture
        1) Flat Network : 모든 네트워트가 하나의 Routing Domain 안에서 모두 통신이 가능한 형태(Full Connectivity)
        2) Segmented Network : 각각의 VPC는 On-premise와만 통신 
        3) Centralized traffic inspection
        4) egress
        5) interface endpoint
    - Attachment
        1) One or more VPCs
        2) Another TGW (peering connection)
        3) VPN
            - TGW를 사용하는 경우, Global Accelerator(Edge Location)와 함께 쓰일 수 있다!!
        4) DX
        5) Connect Attachement(SD-WAN/third-party network device) nbvh
                    - Attachment Mechanism
                * Underlying transport mechanism : 네트워크에 붙이는 방식(기본)
                * Generic Routing Encapsulation Tunnel (GRE Tunnel) : Appliance 장비 네트워크 인터페이스에 GRE 터널링을 만들어 직접 연결하는 방식
                    1) Connect attachment (연결) / Connect Peer (연결 내부 터널링)
                    2) BGP만 가능
                    3) 최대 5 Gbps per GRE Tennel(Connect Peer), Connect 당 최대 4개의 Peer 생성 가능. 즉, 한 개의 Connect 당 최대 20 Gbps bandwidth를 가짐 (4 peer * 5 Gbps) 

    - Multicast support
    - MTU
    - AZ consideratioin
        1) AZ affinity : 기본적으로 트래픽을 보낸 ENI의 AZ로 트래픽을 송신하는 것을 유지하려 한다(선호).
        2) Appliance Mode : 전달할 때 위치한 트래픽의 AZ로 다시 돌아온다. 기본적으로는 AZ affinity에 의해 트래픽을 보낸 ENI의 AZ로 오지만 이렇게 되는 경우, Asymmetric Routing이 발생할 수 있다!!
    - TGW Peering : Peering은 Static Routing만 가능(BGP 차단)
    - TGW Sharing
    - VPN ECMP support
    - Routing Domain : 
Full mash



* CGW : Customer Gateway
* VGW : Virtual Gateway
* Site-to-Site VPN : VPN Service
* Client VPN : Router/Switch가 아닌 Client에 직접 붙게하는 서비스
* Direct Connect
    - 기본 IPSec VPN 지원 MACsec은 설정으로 가능 (switch or router가 지원해야함)
    - MACsec 보안(IEEE 802.1AE) : Layer 2 보안 (ref : https://aws.amazon.com/ko/blogs/networking-and-content-delivery/adding-macsec-security-to-aws-direct-connect-connections/)
    - IPsec VPN : Layer 3 보안




### Others
* Route53 : DNS
    - Route53은 RDS의 AWS 서비스에 대해 활성화된 AZ의 리소스에 대해 자동으로 IP Refresh한다 모두 
    - Route53 Hostzone을 사용하기 위해서는 enableDnsSupport & enableDnsHostname  모두 활성화 필요
* CloudFront : CDN 서비스
    - 
* Global Accelator : 
    - Public IP를 기본적으로 가지고 있다. 그렇기 때문에 서버는 InternetGateway가 필요없으나 인터넷 통신이 흐르고 있다는 의미에서 InternetGateway를 추가한다.


### Monitoring
* VPC Flow Logs
    - VPC Flow Logs vs VPC Traffic Mirroring : Flow Logs는 확인하는 용도이고, Traffic Mirroring은 잠재적인 네트워크 & 보안을 위해 분석하기 위한 용도
    - 저장 장소 : S3 / CloudWatch Logs / Kinesis Firehose
* VPC Traffic Mirroring
    - ENI로 들어오는 트래픽을 설정한 대상(ENI or NLB)에게로 트래픽을 전달
    - VXLAN 프로토콜을 사용하여 트래픽을 전달
* Reachability Analyzer
    - Source ---> Destination 통신 가능 여부 확인
    - hop-by-hop에 대한 세부 정보 확인 (Hop 정보, 보안 정책 등)
    - 테스트 및 트러블슈팅
* Network Access Analyzer
    - Source ---> Destination 모든 경로에 대하여 네트워크 & 보안 분석
    - 확인되지 않은 네트워크 경로를 발견하기 위한 용도



* Tool
    - Wireshark / tcpdump : Packet Capture
    - traceroute : Check routing hop
    - telnet : Check for TCP/UDP traffic
    - nslookup : Resolve the hostnames
    - ping : Check for ICPM traffic



* Kinesis Data Firehose
    - S3 / Redshift / OpenSearch / API Gateway 등으로부터 로그를 가지고 온다.


---
## Security 
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