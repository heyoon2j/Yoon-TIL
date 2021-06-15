# AWS

# VPC
## NAT Gateway
* 5Gbps의 대역폭, 최대 45Gbps 까지 자동 확장한다 (Gbps = Gigabit per second, 초당 전송속도 )
* 하나의 EIP만 열결할 수 있고, 연결된 후에는 연결을 끊을 수 없다.
* 네트워크 ACL(Access Control List) 만을 사용하여 트래픽 제어
* 포트는 1024 - 65535
* 서브넷의 IP 주소 범위에 속하는 Private IP 주소가 자동으로 할당된 네트워크 인터페이스를 받는다.

## NAT Instance
* Instance 유형에 따라 대역폭이 달라지며, 인스턴스기 때문에 확장 및 장애 조치가 필요
* EIP or Public IP를 사용할 수 있고, Public IP의 경우 변경 가능하다.
* 보안 그룹과 네트워크 ACL(Access Control List)을 사용하여 트래픽 제어


## Internet Gateway
* 2가지 기능이 있다.
    1) 인터넷 라우팅 기능
    2) Public IPv4 Address가 할당된 인스턴스에 대해 NAT를 수행
* 네트워크 트래픽에 가용성 위험이나 대역폭 제약이 발생하지 않는다.



## Security Group vs Network ACL
1. Operation
    * SG: Instance Level
    * ACL: Subnet Level
2. Allow/Deny
    * SG: allow rules only
    * ACL: allow and deny rules
3. State
    * SG: Stateful
        * Inbound를 허용하면 자동적으로 Outbound 허용 
        * 기본적으로 모든 Inbound는 차단이며, 모든 Outbound는 허용되어 있다.
    * ACL: Stateless
        * Inbound, Outbound 모두 명시적으로 적용해야 됨
        * 기본적으로 모든 Inbound, Outbound Traffic은 허용되어 있다.
4. Process Order
    * SG: Traffic을 허용하는지 모든 rule을 비교한다.
    * ACL: 기본적으로 선언한 순서대로 우선순위를 가지며, 그 후에는 Number Order에 의해 우선순위가 결정된다.




# EC2
## Instance
* 단인 테넌시(Tenancy) : 전용 하드웨어에서 실행할 수 있다.
 하드웨어가 민감하다면 사용하면 좋다.

## vCPU
* 인스턴스 유형을 선택할 때, vCPU 당 ECU(EC2 Computing Unit), 실제 CPU가 어떤 것인지, 성능은 어떤지, 코어당 쓰레드 갯수 등 계산할 필요가 있다.
* vCPU 단위는 T2를 제외하고 "CPU Core * Thread". 즉, 총 쓰레딩 개수라고 생각하면 될 듯
* https://docs.aws.amazon.com/ko_kr/AWSEC2/latest/UserGuide/instance-optimize-cpu.html#instance-cpu-options-rules
* http://www.studyforcloud.com/ecuwa-vcpue-daehae-alabogi/


## Core, vCPU, GiB 
* GiB: Gibibyte or Giga binary byte, 기비바이트 또는 기가 이진 바이트
    * 1,073,741,824 byte (=2^30 =1024^3)
    * GB는 10^9 이다. 혼동하면 안된다.
* Bare Metal: Software가 아무것도 설치되어 있지 않은 상태의 컴퓨터
    * 베어메탈 환경에 OS를 설치해야 그때부터 '서버 컴퓨터' 또는 '컴퓨터 노드' 라고 한다.
* CPU: 명령어들을 처리하고 제어하기 위한 논리회로
    * 크게 신호를 보내는 제어장치(Control Unit)와 연산을 담당하는(ALU, Arithmetic Logic Unit)으로 구성된다.
* Core: 실제 물리 코어, CPU가 하는 일을 분담해서 처리한다.
* vCPU: CPU를 Hyper-Threading 기술이 적용하여 n배로 만든 가상 코어
    * Hypervisor가 CPU Time Slice를 어떻게 스케줄링하냐에 따라 분리할 수 있는 개수가 정해진다.
    * http://cloudrain21.com/terms-about-virtualization
* Thread: 코어는 Hardware 단에서의 구성 단위이며,
 Thread는 Software 단에서의 논리적 작업 처리 단위이다.


# 종료 방식: stop vs terminate
* stop: 인스턴스를 중지하면 바로 종료된다.
* terminate: 인스턴스를 중지해도 종료되지 않는다. 중지 후에 종료해야 된다.



