# Network

## Basic
## IOPS vs Throughput vs Latency
* __Bandwidth__ : 대역폭. 네트워크를 통해 특정 시간 내에 전송할 수 있는 패킷 수. 클 수록 많은 양의 데이터를 동시에 보낼 수 있다.
* __Throughput(MB/s)__: 처리량. 시간 단위 내에 송수신되는 데이터의 양. IOPS 보다는 Throughput이 더 정확한 성능 측정 기준.
* __Latency__ : 대기 시간. 네트워크를 통해 패킷이 대상에 도달하는 데 걸리는 시간.
* Bandwidth는 이상적인 데이터 전송량이고, Throughput은 실제로 초당 전송되는 데이터 양이다. 실제 네트워크 상에서는 Packet loss 등으로 이상적으로 데이터를 전송할 수 없다.
* Bandwidth, Throughput이 클 수록, Latency가 작을 수록 좋다.
> 도로(Bandwidth)가 넓어도, 사고 등의 문제들로(Packet loss) 있다면 제공하는 도로 크기만큼의 차(Packet)가 이동할 수 없고(Throughput), 정체되는 시간이(Latency) 길어진다.

### Example

* 가정
    * 네트워크를 느리게 하는 요소 모두 제외
    * 
    * Packet 크기가 Bandwidth 크기와 동일
    * A Task는 작업
> 일반적으로 Latency가 작을 수록 Throughput이 크다. 하지만 멀티 태스킹 환경에서는 달라질 수 있다! Latency 증가하더라도, 증가된 시간 비례 더 많은 양을 보낼 수 있게 되기 때문에 Throughput이 크게 증가할 수 있다.  



## Network Tool
* Wireshark : 패킷 분석
* Nmap : 네트워크 매핑
* Infection Monkey : 침투 테스트
* fprove : 성능 테스트
*ㅊ Cacti : 시각화 툴