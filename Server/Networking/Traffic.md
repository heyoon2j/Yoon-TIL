# Traffic Check

## TCPDump / WinDump
* 네트워크 인터페이스를 통해 송수신 되는 패킷을 캡쳐하는 SW
* Body 등을 확인하려면 WireShark 같은 Tool을 사용해야 한다.
* Linux : TCPDump / Windows : WinDump

### 사용법
* 명령어 : ```tcpdump [option] [expression]```
* 옵션
    | 구분 | 설명 | 예시 |
    |------|------|-----|
    | -A | ASCII 형태로 패킷 출력 | tcpdump -A |
    | -c count | 패킷 갯수 지정 | tcpdump -c 10 |
    | -D | Network Interface 리스트 출력(Windows에서만 사용) | windump -D |
    | -i interface | 네트워크 인터페이스 지정 | tcpdump -i eth0 |
    | -n | hostname -> ip address, protocol -> port 출력  | tcpdump -n |
    | -r file | 캡쳐한 파일 내용 읽기 | tcpdump -r ~/test.plog |
    | -tttt | Timestamp 형태 출력 |
    | -v | verbose, 상세 내역 확인 | tcpdump -v |
    | -vv | 더 상세한 내역 확인 (암호화 X) | tcpdump -vv |
    | -vvv | 더 상세한 상세 내역 확인 (TTL 포함) | tcpdump -vvv |
    | -w file | 캡쳐한 내용을 파일에 저장 | tcpdump -w ~/test.log |

* 표현식 (조건문)
    | 구분 | 설명 | 예시 |
    |------|-----|------|
    | and / && | A B 모두 만족하는 조건 | tcpdump src 10.0.0.7 && dst 192.168.10.100 |
    | or / \|\| | A 또는 B 중 하나라도 만족하는 조건 | tcpdump src 10.0.0.7 \|\| dst 192.168.10.100 |
    | not | ! | tcpdump src 10.0.0.7 ! port 443 |

* 표현식 (값)
    | 구분 | 설명 | 예시 |
    |------|------|-----|
    | network, mask | CIDR | tcpdump network 10.0.0.50 mask 255.255.255.0 |
    | src | 출발지 | tcpdump src 10.0.0.8 |
    | dst | 목적지 | tcpdump src 10.0.0.20 |
    | port | 포트 | tcpdump port 80 |
    | src port | Source 도메인 | tcpdump src hsot naver.com |
    | dst host | Destination 도메인 | tcpdump dst host naver.com |    
    | host | 도메인 | tcpdump host test.com |
    | src host | Source 도메인 | tcpdump src hsot naver.com |
    | dst host | Destination 도메인 | tcpdump dst host naver.com |

</br>

### 결과 확인
```
[Time] [Source IP] > [Destinationo IP]: Flags [], seq, ack, win options [], length 
```
* IP : Source IP
* TTL : Time TO Live
* seq : Sequence 번호
* ack : ACK 번호
* win : Windows size

* 헤더 플래그
    | S |  |  |
* 플래그
    | Flag | 설 명 | 비 고 |
    |------|-------|------|
    | S | SYN 요청 | 세션 연결 요청 (3-way handshaking) |
    | ACK | 요청에 대한 응답 |  |
    | F | FIN 요청 | 연결 종료 요청 (4-way handshaking) |
    | R | RST(Reset) | 비정상적인 종료를 위한 Packet |
    | P | PSH(Push) | 빠른 응답을 위하여 OSI 7 Application Layer로 즉시 전송하도록 하는 Flag. (버퍼가 채워져야 데이터를 읽기 때문에 느릴 수 있다) |
    | URG | Urgent Pointer | 긴급한 데이터에 대해 높은 우선수위를 가지게 함. (ex> 명령어 도중 CTRL + c) |
    | . | Placeholder | SYN, FIN, RST, PSH 등 Flag가 설정되지 않은 상테 |
</br>


### Example
```shell
# Check Network Interface
$ ifconfig # Linux
$ windump -D # windows


$ tcpdump -n -vvv -tttt -A -i eth0 -w packetCheck.log
$ tcpdump -n -vvv -tttt -A -i eth0 -w packetCheck.log dst port 80 && dst port 443

# quit : ctrl + c
```
</br>
</br>


--------


## Traceroute
* ping과 마찬가지로 ICMP를 이용하여 경로를 추적한다.

### 사용법
* 명령어 : ```tracerout [option] host```
* 옵션
    | 구분 | 설명 | 예시 |
    |------|------|-----|
    | -d / --debug | Socket Level의 디버깅 |  |
    | -g gate / --gateway=gate | 특정 게이트웨이를 통해 패킷 라우팅 |  |
    | -i device / --interface=device | 특정 네트워크 인터페이스 지정 |  |
    | -n | IP Address에 대하여 DNS Resolver를 사용하지 않는다(빠르다) |  |
    | -T / --tcp | TCP를 사용하여 확인 |  |
    | -p port / --port=port | 특정 포트 설정 (기본 : ICMP) |  |
    | -U / --udp | UDP를 사용하여 확인 |  |

### Example
```
$ tracerout -g [proxy] -i eth0 -T -p 443 test.com
```
</br>
</br>


## Tshark
* 