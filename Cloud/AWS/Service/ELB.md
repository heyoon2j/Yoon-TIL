# ELB 
* Elastic Load Balancer
* 기능
    1) Target Group에 대한 Health Check
    2) Listener Rule에 따라 Traffic Routing
    3) Algorithm에 의해 Traffic 부하 분산
</br>


## ELB 종류
1. __ALB (Application Load Balancer)__
    * 7 Layer Application 계층에서 Routing 결정(HTTP/HTTPS)
    * Host name, Path, Query 문자열 매개변수, HTTP Header, HTTP Method, Source IP 또는 Port Number를 기반으로 분석하여 Rouoting 결정
    * 사용 가능 Protocol: HTTP/1.1, HTTP/2, HTTPS, gRPC
    * Redirection 또는 고정 응답을 반환하도록 구성 가능
    * 동적 호스트 매핑 지원
    * Static IP 사용 불가(Dynamic IP)
        * Dynamic IP 사용하기 위한 과정은 다음과 같다.
        1) 트래픽 증가/감소
        2) 트래픽에 따른 ELB Node 증가/감소
        3) Node에 대한 IP를 Route 53 DNS에 업데이트(추가/삭제)
2. __NLB (Network Load Balancer)__
    * 4 Layer Transport 계층에서 Routing 결정(TCP/UDP/SSL)
    * 초당 수백만 개의 요청을 처리할 수 있다.
    * 지정한 TCP 프로토콜과 포트 번호를 사용하여 Routing
    * 동적 호스트 매핑 지원
    * Static IP 사용
3. __GLB (Gateway Load Balancer)__
    * 3 Layer Network 계층에서 작동
    * 방화벽, 침임 탐지 및 방지 시스템, 심층 패킷 검사 시스템과 같은 가상 어플라이언스를 배포, 확장 및 관리할 수 있다.
4. __CLB (Classic Load Balancer)__
    * 4 Layer 또는 7 Layer에서 Routing 결정
    * 동적 호스트 매핑을 지원하지 않으므로, 고정된 관계가 필요
</br>


### ELB Selection
* 기본적으로 AWS에서는 CLB 보다는 ALB/NLB 사용을 권장한다.
* HTTP/HTTPS Protocol을 사용하지 않는 경우, NLB 사용
* ALB는 HTTP Packet 분석으로 인한 큰 Overhead가 존재하지만, NLB는 4 Layer까지만 분석하므로 속도가 빠르다. 그렇기 때문에 NLB는 ALB가 다루지 않는 모든 것에 사용된다. 일반적인 사용 사례는 실시간 데이터 스트리밍 서비스(비디오, 주식 시세 등) 등이 있다.
* REST API의 구현은 일반적으로 ELB와 Computing Service를 사용하는 것보다 AWS API Gateway가 더 나은 선택이다. 하지만 API Gareway의 경우 확장성에 제한이 있기 때문에 잘 선택하는 것이 좋다.
* Static IP를 사용하려면 NLB를 사용해야 하며, 교차 영역 활성화를 하지 않으면 Static IP가 해당하는 가용 영역의 서버에만 트래픽을 전달한다.
    * Reference : https://stackoverflow.com/questions/60934851/in-aws-why-is-that-an-nlb-can-provide-static-ip-addresses-whereas-an-alb-cannot#:~:text=As%20per%20AWS%2C%20Network%20Load%20Balancer%20routes%20traffic,Also%2C%20NLB%20supports%20static%20%2F%20Elastic%20IP%20addresses.
    * https://stackoverflow.com/questions/3821333/amazon-ec2-elastic-load-balancer-does-its-ip-ever-change
    > NLB만 Static IP를 가지는 이유(추측)는 다음과 같다. NLB는 OSI 4 Layer에서 작동하며, 4 Layer에서만 작동하는 응용 프로그램에서도 사용이 가능해야 한다. 그리고 Layer 4에서는 DNS Protocol는 Layer 7로 사용할 수 없다. 이러한 이유들 때문에 NLB는 Layer 4 통신을 위해 ALB처럼 DNS를 활용하는 방법이 아닌 IP를 사용하는 방식을 기본으로 가진다. 하지만 NLB는 구축 시 Domain을 가진다. 이는 NLB를 이용하는 HTTP/HTTPS 등과 같이 Layer 7 응용 프로그램을 위해 그리고 AWS 제공하는 서비스 관점에서 제공해주기 때문이거나, 메인 서비스는 Layer 4 동작을 관리하지만 엔진 서비스는 Layer 7까지 처리할 수 있기 때문일거 같다.
</br>
</br>


## ELB 동작 과정
1. __Load Balancer의 Listener가 Request(REQ)를 수신하고, REQ를 Listener Rules 의해 결정된 Taget Group에 전달__
    1) LB Server 설정 
    2) Target Group Routing 기준 설정
2. __Target Group은 REQ를 Instance, Container 또는 IP 주소, Lambda로 Routing__
    1) Instance 관리
    2) Instance Traffic Routing Algorithm 결정
    * 분할하기 위해 Target Group은 Target에 대해 지속적인 Health Check를 하게 된다.
</br>


## ELB Attribute
### All LB
* "deletion_protection.enabled" : 삭제 방지 활성화 (기본 값: false / true,false)


### Application LB and Network LB
* "access_logs.s3.enabled" : 액세스 로그 활성화 (기본 값: false / true,false)
* "access_logs.s3.bucket" : 액세스 로그를 저장할 S3 버킷 이름
* "access_logs.s3.prefix" : S3 버킷에 저장할 때, Prefix
* "ipv6.deny_all_igw_traffic" : IGW의 액세스를 방지. internet-facing인 경우 false, internal인 경우 true로 설정


### Only Application LB
* "idle_timeout.timeout_seconds" : 유휴 시간 (기본 값: 60 / 1~4000초)
    > 참고로 NLB는 TCP: 350초 / UDP: 120초
* "routing.http.desync_mitigation_mode" : HTTP Desync로 인한 문제로부터 애플리케이션을 처리하는 방법 설정 (기본 값: defensive / monitor,defensive,strictest)
    * defensive
        1) RFC 7230 규칙을 준수하는지 여부와 무관하게 애플리케이션이 알려진 안전한 요청을 수신하도록 허용
        2) RFC 규칙을 준수하지 않는 요청과 알려진 보안 위협에 해당하는 요청을 차단
        3) 모호한 요청에 대해서는 HTTP 유지 한도와 무관하게 클라이언트 및 업스트림 연결을 모두 종료 (모호한 요청이란 RFC 7230 규칙을 준수하지 않고 프록시 또는 웹 서버마다 해석이 달라져서 Desync를 초래할 수 있는 요청)
    * strictest : RFC 7230 규칙을 준수하는 요청만 확인
    * monitor : RFC 7230 규칙과 관계없이 수신되는 모든 요청을 그 뒤에 있는 애플리케이션에 전달
* "routing.http.drop_invalid_header_fields.enabled" : 잘못된 HTTP 헤더가 포함된 경우 Drop할지의 여부 (기본 값: false / true,false)
* "routing.http.x_amzn_tls_version_and_cipher_suite.enabled" : x-amzn-tls-version 및 x-amzn-tls-cipher-suite 헤더가 요청에 포함될지 여부 (기본 값: false / true,false)
* "routing.http.xff_header_processing.mode" : X-Forwarded-For 헤더를 수정, 보존 또는 제거 여부
    * X-Forwarded-For: 타겟이 클라이언트의 정보를 알도록 기록하는 헤더
    * append : Request Header XFF에 클라이언트 IP를 추가
    * preserve : Request Header XFF 보존하고 다른 값을 추가하지 않음 (당연히 수정을 하지 못하니 클라이언트 IP를 ALB에서 추가하지 못한다)
    * remove : 모든 X-Forwarded-For Header 삭제
* "routing.http.xff_client_port.enabled" : X-Forwarded-For 헤더에 Port 정보를 추가할지 여부 (기본 값: false / true,false)
* "routing.http2.enabled" : HTTP/2가 활성화되었는지 여부 (기본 값: true / true,false)
* "waf.fail_open.enabled " : WAF로 요청을 전달할 수 없는 경우(막힌 경우)에도 LB를 통해 대상으로 라우팅할지 여부 (기본 값: false / true,false)
    * WAF를 사용하지 않으면 true로 할 필요가 없다.


### Network LB and Gateway LB
* "load_balancing.cross_zone.enabled" : 교차 영역 로드 밸런싱이 활성화되었는지 여부 (기본 값: false / true,false)
    * 기본적으로 가용 영역 별로 ELB 노드가 생성됨. 각 노드는 자신의 가용 영역에 있는 대상에게만 라우팅 가능. 활성화시 모든 가용 영역에 라우팅이 가능해짐
    * Application LB는 항상 적용됨
    * 밸런싱 비율 = 100 / 교차 영역에 대한 모든 인스턴스 수
    > NLB는 기본적으로 온프레미스도 포함되어 있기 때문에 false로 설정되어 있는거 같다.
</br>
</br>


---
## Listener
* REQ를 수신하고 Packet을 분석하여 Target Group으로 전달한다.
* Packet 분석은 Listner Rule에 의해 결정된다.
</br>

## ALB Listener Rule
### 우선 순위
* 숫자가 작은 거부터 우선 순위를 가진다(1 ~ 50000)
* 규칙은 서비스가 정상일 때 우선순위가 앞서야 하고, 오류 발생시 처리는 뒤에 나와야 한다.
</br>

### 규칙 작업
* 기본적으로 각 규칙에는 ```fixed-response```, ```forward```, ```redirect``` 작업 중 하나는 꼭 포함되어 있어야 하며, 이 작업이 수행할 마지막 작업이어야 한다.
* 고정 세션이 적용되도록 하려면 규칙에 대해 Target Group 고정을 활성화해야 한다. URL로 인코딩된 쿠키 값은 지원하지 않고, Target Group에 대한 정보를 인코딩하고 쿠키를 암호화하며 클라이언트에 대한 응답으로 __AWSALBTG__ 라는 쿠키를 생성하는데 이를 포함하여 요청해야 한다.
</br>

1. ```fixed-response```
    * Client 요청을 삭제하고, 사용자 지정 HTTP 응답 반환
    * 이 작업을 사용하여 2XX, 4XX, 5XX 응답 코드와 선택적 메시지를 반환할 수 있다.
    > 운영 중에는 잘 사용되지 않을 거 같다. 긴급하게 작업해야되거나 개발해야되는 상황 정도일거 같다. 에러메시지를 출력하는 정도로 사용될 거 같다. 보통 특정 Packet에 대해서 응답을 하는 건데, 방화벽에서 작업하거나 좋지 않을까 싶다.
    * Example
        ```
        [
          {
              "Type": "fixed-response",
              "FixedResponseConfig": {
                  "StatusCode": "200",
                  "ContentType": "text/plain",
                  "MessageBody": "Hello world"
              }
          }
        ]
        ```
2. ```forward```
    * 요청을 하나 이상의 지정된 대상 그룹에 전달
    * 여러 대상 그룹을 지정하는 경우 각 대상 그룹에 대해 가중치를 지정 (0 ~ 999)
    > 가용성 처리 및 한 쪽 AZ가 망가졌을 때, 배포할 때 사용. 가장 많이 사용할 거 같다. 고정 쿠키는 Service 규모가 작은면 사용해도 되지만 아닌 경우, Cache를 이용하는 것을 추천한다.
    * Example
        ```
        # TargetGroup이 1개인 경우, "Weight"가 필요 없다.
        [
            {
                "Type": "forward",
                "ForwardConfig": {
                    "TargetGroups": [
                        {
                            "TargetGroupArn": "arn:aws:elasticloadbalancing:us-west-2:123456789012:targetgroup/blue-targets/73e2d6bc24d8a067",
                            "Weight": 10
                        },
                        {
                            "TargetGroupArn": "arn:aws:elasticloadbalancing:us-west-2:123456789012:targetgroup/green-targets/09966783158cda59",
                            "Weight": 20
                        }
                    ],
                    # 고정 쿠키
                    "TargetGroupStickinessConfig": {
                        "Enabled": true,
                        "DurationSeconds": 1000
                    }          
                }
            }
        ]
        ```
3. ```redirect```
    * URL의 요청을 다른 URL로 리디렉션
    > 웹 개발자가 할 내용인거 같다...?, 필요시 찾아서 사용할면 될 거 같다.
4. ```authenticate-cognito```: [HTTPS 리스너] Amazon Cognito와 호환되는 자격 증명 공급자를 사용하여 사용자를 인증
5. ```authenticate-oidc```: [HTTPS 리스너] OpenID Connect(OIDC)와 호환되는 자격 증명 공급자를 사용하여 사용자를 인증
</br>

### 규칙 조건 형식
1. ```host-header```
    * Host 기반 Routing으로, 호스트 이름을 기반으로 라우팅
    * 단일 로드 밸런서를 사용하여 여러 하위 도메인과 여러 최상위 도메인을 지원할 수 있다.
    > 도메인마다 HAproxy Server 여러대 사용하는 것보다 1개로 처리할 수 있을 거 같다.
    * Example
        ```
        [
            {
                "Field": "host-header",
                "HostHeaderConfig": {
                    "Values": ["*.example.com"]
                }
            }
        ]
        ```
2. ```path-pattern```
    * Path 기반 라우팅. 요청의 URL을 기반으로 요청을 라우팅
    > path가 Host 개념으로 사용되는 경우 사용될거 같다. 그 외에 API 개념인 경우, API Gateway 또는 개발자가 개발해야 될 거 같다.
    * Example
        ```
        [
          {
              "Field": "path-pattern",
              "PathPatternConfig": {
                  "Values": ["/img/*"]
              }
          }
        ]
        ```
3. ```http-header```
    * HTTP 헤더 필드 Key와 Value로 Routing
    > 여기서부터 아래는 잘 안 사용될 듯
    * Example
        ```
        [
            {
                "Field": "http-header",
                "HttpHeaderConfig": {
                    "HttpHeaderName": "User-Agent",
                    "Values": ["*Chrome*", "*Safari*"]
                }
            }
        ]
        ```
4. ```http-request-method```
    * HTTP Method 조건을 사용하여 Routing
5. ```query-string```
    * Query 문자열의 Key/Value 페어 또는 Value를 기반으로 Routing
6. ```source-ip```
    * Source IP를 조건으로 Routing
    * __Client가 Proxy Server 뒤에 있는 경우, 이는 클라이언트의 IP가 아니라 Proxy IP이다!!__

</br>

## NLB Listener Rule
* 따로 Rule이 존재하지 않는다.
* TCP Traffic
    * Protocl, Source IP, Source Port, Destination IP, Destination Port, TCP Seq 사용하여 Routing.
    * 연결은 연결 수명 동안 하나의 대상에 라우팅
* UDP Traffic
    * Protocl, Source IP, Source Port, Destination IP, Destination Port를 사용하여 Routing
</br>
</br>



---
## Target Group
* Target에게 보내기 위해서 해당 Target이 정상적으로 동작하는지 Check하게 된다.
</br>

### ALB Routing Alogrithm
1. __Round Robin__
    * 기본 알고림즘
    * 요청을 Target에 번갈아가며 순차적으로 전달하게 된다.
    * 요청과 대상이 비슷하거나 대상 간에 똑같이 요청을 배포해야 하는 경우 사용
2. __Least Outstanding Requests__
    * 미처리 요청이 가장 적은 대상에 요청을 보낸다.
    * 요청의 복잡성이 다양하거나 대상의 처리 기능이 다양한 경우 사용하는 것이 좋다.

### NLB Routing Algorithm
* Roud Robin만 제공한다.
</br>


### Type
1. Insatnces
    * VPC 안에 있는 Intance들과 연결할 때 사용
2. IP addresses
    * On-premise에 있는 장비들과 연결할 때 사용
    * 이론적으로 다른 네트워크와 통신하기 위해서 이므로 NLB가 NAT로 동작하도록 설정된다. 따라서 Client IP가 보존되지 않는다!!! (Ref: https://aws-hyoh.tistory.com/135)
3. Lambda function
    * Single Labmda function과 연결할 때 사용
4. Application Load Balancer
    * ALB와 연결할 때 사용
</br>


### Health Check
* 요청을 주기적으로 전송하여 상태를 확인한다.
* Protocol / Port
    * ALB: HTTP, HTTPS
    * NLB: TCP, TLS, UDP, TCP_UDP
* Healthy threshold : Healthy 상태를 위한 Check 연속 성공 개수
* Unhealthy threshold : : Unhealthy 상태를 위한 Check 실패 개수
* Timeout : Health Check 요청에 대한 Timeout
* Interval : Health Check 요청 간격
</br>


### Stickiness
* LB, APP Cookie는 ALB에서만, Source IP는 NLB에서만 사용 가능하다.
* Source IP를 기준으로 Target을 결정한다.
    > 2022년 가을인가에 변경됨. 원래는 2 tuple (Source IP, NLB Node IP)로 체크하였다. 그래서 이상한 동작을 하였다. 현재는 Source IP 기준으로 갈린다. 단, Cross-zone이 활성화되어 있는 경우.
* __사용하기 위해서는 Listener Rule에서 Stickness를 사용한다고 설정해놔야 한다.__
* 고정 세션 해결 방법
    * https://kchanguk.tistory.com/146
    * https://st-soul.tistory.com/31
1. __LB Cookie__
    * ELB 생성 쿠키 AWSALB를 사용하여 Target Group의 동일한 대상으로 요청을 Routing
    * Session Cookie 정보를 ALB가 생성해 가지고 있다.
    * 후속 요청에 AWSALB 쿠키를 포함해야 한다.
2. __App Cookie__
    * Client 대상 고정에 대한 사용자 고유의 기준을 설정할 수 있는 유연성을 제공
    * Session Cookie 정보를 Target이 Custom application cookie를  생성해 가지고 있고, ALB에는 Target이 생성한 Custom application cookie를 캡처해 Application Cookie 생성하여 가지고 있게 된다.
    * Application Cookie는 Custom application cookie의 속성을 복사하지 않는다.
    * Target은 고정을 활성화하기 위해서 ALB에 구성된 쿠키와 일치하는 사용자 지정 애플리케이션 쿠키를 설정해야 한다.
3. __Source IP__
    * Source IP로 지정
> 고정 세션이므로, 특정 서버만 과부하가 발생해 로드밸런싱이 의도한대로 동작하지 않을 수 있다. 그리고 해당 특정 서버가 Fail이 발생하면 저장되어 있던 모든 세션이 소실될 수 있다.
</br>


### Connection Draining
* 등록 취소 또는 Unhealthy 인스턴스에 대해서 ELB는 연결을 중지시키지만(요청을 더이상 보내지 않는다), 해당 옵션을 활성화하게 되면 요청 전송은 보내지 않지만 설정한 시간까지 연결을 유지시킨다.
* 이를통해 인스턴스가 등록 취소되기 전에 받은 요청을 완료할 수 있다.
* 옵션: Deregistration delay
</br>


### Preserve Source IP
* HTTP/HTTPS는 XFF가 있기 때문에 따로 설정이 없고, TCP/UDP는 따로 존재하지 않기 때문에 설정이 존재한다.
* NLB는 2가지 방법을 지원한다.
    1) preserve_client_ip : 같은 VPC에서만 사용 가능, LB 자체에서 Client IP를 전달
    > https://docs.aws.amazon.com/ko_kr/elasticloadbalancing/latest/network/load-balancer-troubleshooting.html
    > 클라이언트 IP 보존 사용 시, Client가 NLB로 요청하는 경우 Source IP와 Target IP가 동일하게 되어 간현적인 연결 실패를 가져올 수 있다!!!!
    2) proxy_protocol_v2 : 다른 네트워크에서도 사용 가능, LB에서 Proxy Protocol을 사용해서 전달
    > "preserve_client_ip.enabled"를 true로 하면 무조건 다른 서버들에 대해 Client IP를 보내게 되므로 false로 지정하고 "proxy_protocol_v2.enabled"와 Server의 설정을 이용하도록 권장하는 것이 좋아 보인다(HAProxy Docs 읽어보면 비슷하게 설정하는 듯 보임)
</br>


## Target Group Attribute
### All LB
* "stickiness.enabled" : 고정 세션을 활성화할지 여부를 나타냅니다.
* "stickiness.type" : 고정의 유형. 가능한 값은 source_ip, lb_cookie, app_cookie.
* "deregistration_delay.timeout_seconds" : Elastic Load Balancing이 대상의 등록 취소 상태를 draining에서 unused로 변경하기 전에 대기하는 시간입니다. 범위는 0~3600초입니다. 기본 값은 300초입니다.
* "deregistration_delay.connection_termination.enabled" : 등록 취소 시간 제한이 끝날 때 로드 밸런서가 연결을 종료하는지 여부를 나타냅니다. 값은 true 또는 false입니다. 기본값은 false입니다.
</br>


### Application LB


</br>


### Network LB
* "preserve_client_ip.enabled" : 클라이언트 IP 보존이 활성화되었는지를 나타냅니다. 값은 true 또는 false입니다. 
    * 인스턴스 유형 대상 그룹: 활성화됨
    * IP 유형 대상 그룹(UDP, TCP_UDP): 활성화됨 / 클라이언트 IP 보존을 비활성화할 수 없다.
    * IP 유형 대상 그룹(TCP, TLS): 비활성화됨
* "proxy_protocol_v2.enabled" : 프록시 프로토콜 버전 2의 활성화 여부를 나타냅니다. 기본적으로 프록시 프로토콜은 비활성화되어 있습니다.
    * 해당 속성은 IP 유형 대상 그룹(TCP, TLS)인 경우에만 생각하면 된다.
</br>
</br>



## ELB Logging
* ELB Log Data는 S3에 저장된다.
* Bucket Policy 추가
* Setting 방법
    1) ELB -> Description -> Attributes -> Access logs
    2) Check Enable -> S3 location : ```${s3 name}/${prefix}```



## Cost
1. __Application Load Balancer__
    * ALB가 실행된 시간 또는 부분 시간 & 시간당 사용된 ALB 용량 단위(LCU)에 대해 요금 부과
2. __Network Load Balancer__
    * NLB가 실행된 시간 또는 부분 시간 & 시간당 사용된 NLB 용량 단위(NLCU)에 대해 요금 부과 
3. __Gareway Load Balancer__
    * GLB가 실행된 시간 또는 부분 시간 & 시간당 사용된 GLB 용량 단위(GLCU)에 대해 요금 부과 
4. __Classic Load Balancer__
    * CLB가 실행된 시간 또는 한 시간 미만 & 시간당 LB를 통해 전송된 각 GB 단위 데이터에 대해 비용 청구 


</br>

### Reference
* https://iamondemand.com/blog/elb-vs-alb-vs-nlb-choosing-the-best-aws-load-balancer-for-your-needs/
* https://browndwarf.tistory.com/38

---
## LB TCP 통신 과정
### L4 3-way Handshaking
* 기본 동작 과정 : Network/Switch.md 확인

### Idle time 초과 동작
* L4 : NLB는 자신만 세션을 종료시키며, 알지 못하는 Client는 동일한 Connection으로 트래픽을 보냈을 때 RST를 받게 된다.
* L7 : Client와 Target에 FIN을 보내 세션 테이블을 종료 시킨다.
* https://tech.kakao.com/2014/05/30/l4/
* https://medium.com/tenable-techblog/lessons-from-aws-nlb-timeouts-5028a8f65dda
* Network/Protocol/TCP.md

