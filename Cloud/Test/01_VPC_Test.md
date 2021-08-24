# VPC Test
## Test Scenario
1. __VPC 3개 생성__
    1) __Network VPC__
        * Security Zone
        * Secure VPC: IPS, WAF 등 보안 프로그램을 통해 Virus 및 Hacking 방지 VPC, Public Subnet 2개, NAT Gateway 1개, WAF 사용할 수 있으면 사용(비용 Check 후 사용하기!!)
        * ACL VPC: Firewall 등으로 Traffic Filtering VPC, Private Subnet 2개 NAT Instance 1개, ACL 등록하여 Filtering 작업
    2) __DMZ VPC__
        * DMZ
        * AZ 2개(ap-northeast-2a, 2c), 각 AZ에 private Subnet 1개씩(DMZ), EC2 1개 생성
    3) __Prod / Dev VPC__
        * Application Zone
        * Production VPC: AZ 2개(ap-northeast-2a, 2c), 각 AZ에 private Subnet 3개씩(ELB, APP, DB), APP쪽에 EC2 1개 생성
        * Develop VPC: VPC 생성까지만
2. __Tansit Gateway 연결__
    1) Transit Gateway 생성
    2) Transit Gateway와 각 VPC Routing Table 작업
3. __접속 Test__
   1) 외부 IP(Internet)로 DMZ의 EC2에 SSH 접속
   2) DMZ의 EC2에서 Prod의 EC2에 접속

## Architecture



## Test
## 1. VPC 생성


## 2. Transit Gateway 연결



## 3. 접속 Test

