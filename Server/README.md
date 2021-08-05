# Server
For Server

## Server 구축 시, 고민할 필요 요소.
1. CPU
2. Memory
3. Storage & I/O
4. Kernel & Driver
5. Network
6. Security
7. Application


## Server Resource 산정방법
* 한국정보통신기술협회 기준 (TTA)
* https://www.cisp.or.kr/wp-content/uploads/2013/11/%EC%A0%95%EB%B3%B4%EC%8B%9C%EC%8A%A4%ED%85%9C_%ED%95%98%EB%93%9C%EC%9B%A8%EC%96%B4_%EA%B7%9C%EB%AA%A8%EC%82%B0%EC%A0%95_%EC%A7%80%EC%B9%A8_TTAK.KO-10.0292_R1_2017.06.28.pdf


## 규모산정 개념
| 구분     | 정의                                                                                                                  | 관점         | 시간성 |
| -------- | --------------------------------------------------------------------------------------------------------------------- | ------------ | ------ |
| 용량관리 | 비즈니스 요구사항을 충족시키기 위한 현재와 미래의 용량 계획을 수립하고 비용(Cost)과 용량(Capacity)의 균형을 맞추는 것 | 조직         | 지속정 |
| 용량계획 | 개략적인 시스템 아키텍처와 응용 업무를 기반으로 시스템에 요구되는 성능 요구사항과 성능을 결정하기 위한 계획           | 조직, 시스템 | 지속성 |
| 규모산정 | 기본적인 용량과 성능 요구사항이 제시되었을 때, 그것을 시스템 요구사항으로 변환하는 것                                 | 시스템       | 일시적 |

* 용량관리: 시스템, 네트워크 등 조직 내의 하드웨어 자원만을 국한하는 것이 아니라 전사적인 자원을 관리 대상으로 하며, 일시적이 아닌 지속적인 관리에 중점을 둔다.
* 용량계획: 클라이언트 애플리케이션의 형태, 동작 특성, 이들에 접근하는 사용자의 수, 서버 시스템에 대응하는 오퍼레이션의 형태, 서버시스템에 접속하는 동시 접속자 수, 서버시스템에 의해서 수행되어야 하는 피크율, 피크타임 하에서의 여유율 등을 결정힌디.
* 규모산정: 서버의 CPU 형태나 수, 디스크 크기나 형태, 메모리 크기, 네트워크의 용량 등의 요소를 들 수 있다.


## 규모산정 방법
1. 어떻게 용량관리를 할지 결정한다.
	* 현재와 미래에 대한 비용과 용량 계획을 세운다.
	* 예산은 어떻게 되는지?
	* On-premise? Public? Hybird?
2. 적합한 용량 계획을 세운다.
	* 서비스 측면에서의 용량 계획과 리소스 측면에서의 용량 계획을 나누어 계획을 세운다.
	* 비즈니스의 확장성을 고려
	* Server, DB, Network, 보안, 응용프로그램 등과 같은 리소스 영역을 고려
	* 인증, 콘텐츠과 같은 논리 영역에 대한 검사도 병행해야 한다.
	* Application 형태, Application 동작 특성, 사이트 사용 대상자, 사이트 접속자 수, 사이트 동시 접속자수, 피크율, 피크 타임 하에서의 여유율 등 결정
	* 이들을 종합하며 아키텍처를 그린다.
3. 용략 계획을 기준으로 규모산정
	* 산정방식
		1) 수식계산법: 계산법이 있지만, 거의 맞지 않는다.
			* 그래서 각 HW에서 제공하는 tpmC를 기준으로 보정치를 확인해서 계산한다.
			* 기본적으로 Hypervisor를 사용하는 경우, OS Call 등으로 인해 100% HW 성능을 사용할 수 없다. 이러한 여러 이유 들로 인해 보정치가 필요하다.
			* 공식적인 업체에서 제공하는 TMPC 값은 공식적이지만, 보정치는 정해야되기 때문에 적절한 값이 필요한다 (근거 제시 미약일 수 있으니까 물어보자)
		2) 참조법: 업무량(사용자 수, DB 크기)에 따라, 기본 데이터를 토대로 대략적인 시스템 규모를 비교하여 비슷한 규모를 산정
			* 비교에 의한 것이므로 근거 제시 미약
		3) 시뮬레이션법: 대상업무에 대한 작업부하를 모델링하고 이를 시뮬레이션하여 규모를 산정
			* 테스트부터 하는 것은 실질적으로 힘들다.

## 규모 산정 대상
* CPU: 해당 업무를 처리하기 위한 CPU 규모를 계산
* Memory: CPU 규모산정에 따른 서버 구성방안에 의거하여, 서버별 시스템 S/W, 응용 프로그램 등의 메모리 사용량을 산정한다.
* Disk: CPU 규모산정에 따른 서버 구성방안에 의거하여, 서버별 OS, 시스템 S/W, DB의 데이터, DB의 Archive 및 백업 영역 등의 디스크 사용량을 산정한다.
* Storage: CPU를 기준으로 산정된 서버 규모에 따라 필요한 스토리지의 규모를 산정한다.


## 규모 산정 기준
![BaseBalue](img/baseValue.png)

### CPU
1. Used as a Application Server
   * TPC-C 기준을 참조하여 tmpC 값을 사용
   * TPC-C: Transaction Processing Performance Council
   * tpmC: TPC-C 벤치마크 시나리오에 대한 1분당 최대처리 건수(분당 트랜잭션 수)를 나타내는 수치
   * 

### Storage
* SPC-1 기준을 참조하여 IOPS(초당 I/O 동작 처리건수)를 사용


### Memory


### I/O


### Kernel Driver


### Networking


### Security




### 용어정리
* Transaction(트랜잭션): 컴퓨터 과학분야에서는 "쪼개질 수 없는 업무처리의 단위"를 의미. 이러한 표현 때문에 원자성(Atomicity)을 보장해야 한다라고 얘기한다. DB에서는 하나의 작업을 수행하기 위해 필요한 DB 연산들을 모아놓은 것으로, 논리적인 작업의 단위를 의미.
* Service Response Time(응답 시간): 응답 시간은 "Client Time + Server Time". 예로 Server Time은 [Web Server 수행시간 + WAS Server 수행시간 + DB 수행시간 + 서버간 통신시간], Client Time은 [Data Network 전송시간 + Client 수행시간] 이다.
* IOPS: Input/Output operation per second
* ISP: Information Strategy Planning
* OLTP: On-Line Transaction Process
* ops: operations per second
* SPC: Storage Performance Council
* RAID: Redundant Array of Independent Disks
* TPM: Transaction Per Minute
* WAS: Web Application Server
* TPS: Transaction per Second, 초당 트랜잭션 개수








