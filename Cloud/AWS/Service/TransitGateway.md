# Transit Gateway

## 네트워크 장비 구축시 생각해야 될 것들
* Network Flow
* Connetion
* Routing Table
    * Routing Table Module : Source/Destination에 따른 Table 분리
    * Routing Rule : 어떤 대역을 Static으로 정하고, 어떤 대역을 Propagation 받아 정할지 결정
    * Propagation : 어떤 Network Device에 네트워크 정보를 전달받을지
    * Advertise : 다른 Network Device에게 어떤 정보를 전달할지
        > 해당 장비에 연결되어 있는 다른 네트워크 장비에게 Routing Table에 등록되어 있는 대역을 전파한다.

        > ex) 'A' Network ---> 'B' Routing Device ---> 'C' Network (설정: 10.10.0.0/26 - 'C' Network 대역)
        > BGP 전파에 의해 'A' Network의 Local Routing Device에는 10.10.0.0/26인 경우 'B' Routing Device로 보내라는 Rule을 전달받는다!!
    * Routing Path Decision: Longest Match / Administrative Distance / Metric


## 구축 순서
1. Create TGW 
2. Create Attachment
    * TGW에 다른 네트워크 연결 (VPC, TGW Peering, DXG, VGW)
3. Create TGW Routing Table
    1) 각 Source 별 Routing Table 생성
    2) Routing Table을 사용할 Attachment 연결
    3) 전파받을 네트워크 Propagation 설정
    4) Path 설정
4. Share TGW using Resource Access Manager
    * 다른 Account 공유

</br>

## Routing Priority
* https://docs.aws.amazon.com/ko_kr/vpc/latest/tgw/how-transit-gateways-work.html