# Cloud에 필요한 지식

## Virtualization 기술
1. Virtualization
    * 컴퓨터 리소스의 추상화를 의미
    * 물리적 시스템을 논리적으로 여러 개의 시스템으로 분할하여 사용하는 기술
    
2. Hypervisor
    * Computer(Host)에서 다수의 OS(Guest)를 동시에 실행하기 위한 논리적 플랫폼
    * Computer에 가상화를 구현하려면 Virtual Machine Monitor(VMM)라고 하는 Software를 
    설치해야 하는데 이 Software를 지칭하기도 한다.
    * Virtualization을 이용
    
3. Virtual Machine
    * 컴퓨팅 환경을 소프트웨어로 구현한 것.
    * Hypervisor를 이용하여 Hardware에서 리소스를 분리하여 적절히 VM을 프로비저닝할 수 있다.
    
4. Cloud Computing
    * 정보를 자신의 컴퓨터가 아닌 클라우드(네트워크)에 연결된 다른 컴퓨터로 처리하는 기술
    
5. Container
    * OS의 가상화



## x86 x64 ARM
1. CISC CPU (Complex Instruction Set Computer)
    * 복잡한 명령어 체계를 사용해 필요한 회로도 복잡하다.
    * 복잡한 계산 구조로 속도가 느림
    * 고호율에 필요한 곳에 사용되고, 높은 전력을 사용
    * Intel x86 (32bit) / Intel x64 (64bit) / AMD 64 (64bit)
    * 일반적으로 PC에 사용
2. RISC CPU (Reduced Instruction Set Computer)
    * CISC 구조보다는 비교적 간단한 명령어 체계를 사용.
    * 간단한 계산 구조로 속도가 빠름
    * 안정적으로 사용되는 곳에 쓰이고, 전력 소모가 적다(저전력)
    * ARM (Advanced RISC Machine)
    * IoT, Mobile, Modem 등에 사용
* 참고
    * https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=scw0531&logNo=220601865340
    * https://itfix.tistory.com/908



## HVM vs PV
* 기본적으로 Type 1인 Bare Metal 형이다.
* HVM (Hypervisor Virtual Machine): 전가상화, OS Call에 Binary Translation이 발생
* PV (Para Virtualization): 반가상화, Kernel 수정을 통해 OS Call에 따른 Binary Translation 불필요
* 요즘에는 PV와 HVM의 성능의 차이가 줄어듬에 따라, AWS에서도 PV 대신 거의 HVM으로 제공하고 있다.


## Block Storage vs Object Storage
* Block
    * Volumn 단위로 저장, 실제 물리적 디스크로 생각하면 편하다.
* Object
    * Object(File) 단위로 저장, 내용을 수정하기 위해서는 파일 자체를 변경해야 한다.



## IasS, PaaS, SaaS, DaaS
1. IaaS (Infrastructure as a Service)
   * IaaS는 일반적으로 가상화된 환경의 컴퓨팅 리소스를 말한다. 프로세스 메모리, 스토리지 및 네트워크 등의 Hardware 자원을 클라우드 서비스로 제공한다.

2. PaaS (Platform as a Service)
   * PaaS는 데이터베이스와 같은 서드파티 서비스를 관리하고 운영하는 것에 신경쓸 필요없이 자신의 서비스를 빌드하는 데 도움을 줄 수 있는 많은 준비된 응용프로그램을 제공한다.

3. SaaS (Software as a Service)
   * SaaS는 서비스 관리 또는 모니터링과 같이 특정 목적을 위한 완벽한 솔루션을 제공한다.


## CI/CD
1. CI: Continuous Integration
    * CI 파이프라인을 구축하여, 변경된 코드에 대해 자동 및 연속적인 테스트를 수행할 수 있다.
    * 

2. CD: Continuous Delivery
    * CD 파이프라인을 구축하여, CI 파이프라인을 거쳐간 후에는 CD 파이프라인을 통해 신규 코드를 자동으로 배포할 수 있다.


## Tenancy
* 단인 테넌시(Single Tenancy) : 전용 하드웨어에서 실행할 수 있다.
 하드웨어가 민감하다면 사용하면 좋다.
* 멀티 테넌시(Multi Tenancy)

### __vCPU__
* 인스턴스 유형을 선택할 때, vCPU 당 ECU(EC2 Computing Unit), 실제 CPU가 어떤 것인지, 성능은 어떤지, 코어당 쓰레드 갯수 등 계산할 필요가 있다.
* vCPU 단위는 T2를 제외하고 "CPU Core * Thread". 즉, 총 쓰레딩 개수라고 생각하면 될 듯
* https://docs.aws.amazon.com/ko_kr/AWSEC2/latest/UserGuide/instance-optimize-cpu.html#instance-cpu-options-rules
* http://www.studyforcloud.com/ecuwa-vcpue-daehae-alabogi/


### __Core, vCPU, GiB__
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
 Thread는 Software 단에서의 논리적 작업 처리 단위이다


## HTTP/1.1, HTTP/2 , gRPC
* https://chacha95.github.io/2020-06-15-gRPC1/
1. __HTTP/1.1__
    * Connection당 하나의 Request/Response를 처리하기 때문에, 하나의 Req/Res 마다 Connection 하기위한 3-way handshake, 4-way handshake가 발생해 RTT가 증가한다(Overhead가 발생)
    * 이를 해결하기 위해 하나의 Connection으로 다수의 Req/Res를 받는 Pipelining 기법이 제안되었지만, HOLB(Head Of Line Blocking) 현상이 발생한다 (다음 Res는 현재 Req에 대한 Res가 완료될 때까지 대기해야 된다. 결국 속도가 지연된다)
2. __HTTP/2__
    * 하나의 Connection으로 다수의 Req/Res를 받지만, Res의 순서는 상관없이 Stream으로 주고 받는다.
3. __gRPC__
    * Google에서 개발한 RPC(Remote Procedure Call) 시스템.
    * 정보를 주고받는 규칙(Protocol)으로 HTTP/2를사용한다.
    * 정보를 저장하는 방식(IDL)으로는 Proto 사용.
    * Speed : Proto > JSON > XML
    * https://real-dongsoo7.tistory.com/131

### RPC
* Remote Procedure Call
* 원격지의 프로세스에 접근하여 프로시저 또는 함수를 호출하여 사용하는 방법
* 


## 용어 및 정리
* Immutable Infrastructure
    * 컨테이너에 대한 특성
    * 변경 가능한 인프라
        1) 서버 컴포넌트들을 배포한 후에도 변경되도록 설계되었다.
        2) 기존에는 서버를 교체하는 비용이 너무 많이 들기 때문에, 3~10년 등의 유지 보수 기간을 설정하고 사용하였다.
        3) 유지 보수 기간동안 OS, App에 맞춘 퍼포먼스 튜닝을 수행하며, 여러가지 구성 요소를 변경하며 운영 관리한다.
    * 변경 불가능한 인프라
        1) 배포된 서버 환경을 변경하지 않고 궁극적으로는 기존 것은 버리고 새 것으로 대체하는 것이다.
        2) 서버를 일회용으로 취급하는 개념으로 항상 운영가능한 상태이고, 대체 불가능한 유일한 시스템으로 다룬다.
        3) 한번 쓰고 버리기 때문에 구매하는데 부담스럽지 않다.
        4) 장점
            * 서버의 변경이 필요하게 되면 모든 서버의 이미지를 새로 만들기 때문에 모든 서버를 동일한 상태로 유지.
            Ansible/Chef 와 같은 도구를 사용하여 오랜시간 동안 업데이트와 패치로 모든 서버들의 상태가
             동일한 상태로 유지된다는 것을 보장할 수 없다.
            * 언제든지 즉시 운영 가능한 서버 환경을 구축.
            아무리 클라우드 환경이라도 하드웨어를 사용하는 것이므로 물리적인 고장이 발생할 수 있다.
            * 환경은 최초 한번만 변경된다. 그렇기 때문에 멱등성이 불필요하다.
            * 테스트의 편이성. 개발/스테이징/운영 단계에서 모든 서버 상태가 동일하게 보장되기 때문에 테스트 신뢰도가 높아진다.

* 컴포넌트(Component)
    * 독립적인 단위모듈이다.
    * 하드웨어 부품들은 각각의 독립된 기능을 가진 모듈로 만들어진다. 그렇기 때문에 회사와 상관없이 서로 조합하여 새로운 제품을 만들 수 있고, 특정 부품을 다른 부품으로 바꾸더라도 제품에는 문제가 발생하지 않는다.
    * 소프트웨어에서는 이를 위해 인터페이스로 제공해주고, 사용자는 제공받은 인터페이스를 구현하게 된다. 이렇게되면 인터페이스를 구현한 클래스를 바꾸더라도 정상적으로 작동하게 된다(하드웨어처럼 인터페이스도, 클래스도 각각 정상적으로 구현했을 때)





