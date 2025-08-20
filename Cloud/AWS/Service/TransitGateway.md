# Transit Gateway

## 네트워크 장비 구축시 생각해야 될 것들
* Network Flow
* Connetion
* Routing Table
    - Routing Table Module : Source/Destination에 따른 Table 분리
    - Routing Rule : 어떤 대역을 Static으로 정하고, 어떤 대역을 Propagation 받아 정할지 결정
    - Propagation : 어떤 Network Device에 네트워크 정보를 전달받을지
    - Advertise : 다른 Network Device에게 어떤 정보를 전달할지
        > 해당 장비에 연결되어 있는 다른 네트워크 장비에게 Routing Table에 등록되어 있는 대역을 전파한다.

        > ex) 'A' Network ---> 'B' Routing Device ---> 'C' Network (설정: 10.10.0.0/26 - 'C' Network 대역)
        > BGP 전파에 의해 'A' Network의 Local Routing Device에는 10.10.0.0/26인 경우 'B' Routing Device로 보내라는 Rule을 전달받는다!!
    - Routing Path Decision: Longest Match / Administrative Distance / Metric

---
## 구축 순서
1. Create TGW 
2. Create Attachment
    * TGW에 다른 네트워크 연결 (VPC, TGW Peering, DXG, VGW)
3. Create TGW Routing Table
    1) 각 Source 별 Routing Table 생성
    2) Routing Table을 사용할 Attachment 연결 (Source가 된다!)
    3) 전파받을 네트워크 Propagation 설정
    4) Static Routing Path 설정
4. Share TGW using Resource Access Manager
    * 다른 Account 공유
</br>
</br>

---
## Routing Architecture
* Study
    - route-secure : north --> south 트래픽 구간 (인터넷 통신 구간으로 Firewall, WAF 등 존재) 
    - route-acl : east <--> west 트래픽 중 ACL 서버들의 트래픽 구간 (모든 트래픽 통과 구간으로 FIrewall, DBSafer 등 존재)
    - route-applications : east <--> west 트래픽 중 Application 서버들의 트래픽 구간 (Application 서버들 존재)
    - route-shared : east <--> west 트래픽 중 공용 Application 서버들의 트래픽 구간 (공용 Application 서버들 존재, dmz, common, shared)
    - route-spoke : south --> north 트래픽 구간 (다른 센터 통신 구간으로 Gateway 존재)
* Architecture
    1) Flat Network : 모든 네트워트가 하나의 Routing Domain 안에서 모두 통신이 가능한 형태 (Full Connectivity)
    2) Segmented Network : 각각의 VPC는 서로 통신하지 못하며, On-premise와만 통신.
    3) Centralized egress to internet with NAT gateway 
    4) Centralized traffic inspection : 어플라이언스 장비쪽으로 트래픽이 모두 거쳐가도록 통신
    5) Centralized interface endpoint
</br>

### Routing Priority
* https://docs.aws.amazon.com/ko_kr/vpc/latest/tgw/how-transit-gateways-work.html
