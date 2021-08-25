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
2. __NLB (Network Load Balancer)__
    * 4 Layer Transport 계층에서 Routing 결정(TCP/UDP/SSL)
    * 초당 수백만 개의 요청을 처리할 수 있다.
    * 동적 호스트 매핑 지원
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
* REST API의 구현은 일반적으로 ELB와 Computing Service를 사용하는 것보다 AWS API Gateway가 더 나은 선택이다.
</br>


## ELB 동작 과정
* __Load Balncer의 Listener가 Request(REQ)를 수신하고 REQ를 Taget Group에 전달__
1. __Listener는 REQ를 수신하고 요청을 전달할 Target Group을 Rule들에 의해 결정__
    * Listener Rule에서는 
2. __Target Group은 REQ를 Instance, Container 또는 IP 주소, Lambda로 Routing__
    * Target Group은 Traffic 분할 Algorithm을 기준으로 결정
    * 분할하기 위해 Target Group은 Target에 대해 지속적인 Health Check를 하게 된다.
</br>


## Listener
* REQ를 수신하고 Packet을 분석하여 Target Group으로 전달한다.
* Packet 분석은 Listner Rule에 의해 결정된다.

## Listener Rule
### 우선 순위
* 

### 규칙 작업
* 기본적으로 각 규칙에는 ```fixed-response```, ```forward```, ```redirect``` 작업 중 하나는 꼭 포함되어 있어야 하며, 이 작업이 수행할 마지막 작업이어야 한다.
1. ```authenticate-cognito```: [HTTPS 리스너] Amazon Cognito와 호환되는 자격 증명 공급자를 사용하여 사용자를 인증
2. ```authenticate-oidc```: [HTTPS 리스너] OpenID Connect(OIDC)와 호환되는 자격 증명 공급자를 사용하여 사용자를 인증
3. ```fixed-response```: 사용자 지정 HTTP 응답 반환
4. ```forward```: 요청을 지정된 대상 그룹으로 전달
5. ```redirect```: URL의 요청을 다른 URL로 리디렉션

### 규칙 조건





</br>


## Target Group


### Health Check



</br>


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





