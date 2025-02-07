# NetBox
자산관리를 위한 모니터링 도구
* 관리자/담당자 - 자원들을 매핑하는 구조를 가진다.


## 관리
* 인적 정보
    - Tenants: 여러 사용자를 그룹핑 관리 => Contacts와 연결
    - Contacts: 개인 정보 관리
        - Contacts: 개인 정보
        - Contacts Groups: 개인 정보에 대한 그룹핑
        - Contacts Roles: 직책

* 장비 정보
    - Organization
        - Region: 국가, 도시, 캠퍼스 등 넓은 범위의 영역
        - Site: 특정 건물, 데이터 센터, 사무실, 층 등 구체적인 물리적 위치
        - Location: 구역 위치
    - Device
        - Racks
        - Device
            - Device Roles: 장비 역할 (Router, Firewall, Load Balancer, Server 등)
            - Platforms: 장비 시스템/OS (Linux, ESXi 등)
            - Manufactures: 제조사 (Cisco, Arista, Ahnlab 등)
            - Device Types: 장비 종류 (AIR-CP1800S-K-K9 등)
        - Virtualization
            - Virtual Machines: 가상 머신
            - Interfaces: 인터페이스
            - Virtual Disks: 가상 디스크
            - Cluster: VM 그룹핑 (Cloud.AWS.PojectA)
            - Cluster Group: Cluster에 대한 그룹핑 (Cloud, On-prem)
            - Cluster Type: Cluster Type (AWS, VMWare)
* Custom
    - Custom Fields: 미제공 필드 정정 (Instance Type)
    - Custem Field Choices: 미제공 필드에 대한 값들 지정 (t2.large, m5.xlarge 등)



### 예시
- 예시1 (AWS)
    * 인적 정보
        - Tenants: System_Team
        - Contacts
            - Contacts Groups: Cloud_Part
            - Contacts
                - suzin
                - yuzin
            - Contacts Roles: CloudEngineer
    * 장비 정보
        - Region: AWS
        - Site:
            - AWS-Seoul
            - AWS-Tokyo
        - Location: x
        - Device
            - Device Roles: Server.VM
            - Platforms: Linux/Unix, AmazonLinux2
        - Vitaulization
            - Cluster: Cloud.AWS.PojectA
            - Cluster Group: Cloud
            - Cluster Type: AWS
- 예시2 (On-prm)
    * 인적 정보
        - Tenants: System_Team
        - Contacts
            - Contacts Groups: SE_Team
            - Contacts
                - sangzin
                - mizin
            - Contacts Roles: SystemEngineer 
    * 장비 정보
        - Region: 가산 IDC.LG U+
        - Site:
            - A-01
            - B-03
        - Location: A-01-cc
        - Device
            - Device Roles: Sever.Chassis
            - Platforms: Appliance
            - Device Type