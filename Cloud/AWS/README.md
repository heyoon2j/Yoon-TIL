# Amazon Web Services

## AWS Global Infra
* 전 세계 여러 
1. Region (리전)
   * 두 개 이상의 AZ로 구성
2. Available Zone (가용 영역)
   * 하나 이상의 DataCenter로 구성 (최대 6개)
   * 내결함성을 갖도록 설계
   * 다른 AZ의 장애로부터 격리되어 있지만(물리적 격리), Private Link를 통해 다른 가용 영역과 상호 연결된다.
3. DataCenter (데이터 센터)
   * 전 세계 여러 리전에 Cluster 형태로 구축


## Region


# On-premise / AWS 비교
| On-premise           | AWS           | 비고                                                                       |
| -------------------- | ------------- | -------------------------------------------------------------------------- |
| VM (Vertual Machine) | EC2           | HCI(hypervisor converged infrastructure), Hypervisor 등을 이용하여 VM 생성 |
| 
|                      |               |                                                                            |
| HIWARE, Active Directory freeNAC      | IAM           | 통합 접근 및 계정권한 관리                                                 |
| HIWARE, Active Directory               | Organizations | 중앙 집중식 환경 관리 및 규제                                              |
| Firewall(Hardware) | ACL | 트래픽 필터링, 하드웨어 방화벽 |
| Firewall(Software) | SG | 트래픽 필터링, 소프트웨어 방화벽 |


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


## 종료 방식: stop vs terminate
* stop: 인스턴스를 중지하면 바로 종료된다.
* terminate: 인스턴스를 중지해도 종료되지 않는다. 중지 후에 종료해야 된다.



