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





