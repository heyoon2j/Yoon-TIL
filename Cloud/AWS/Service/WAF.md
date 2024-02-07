# AWS WAF
AWS CloudFront, AWS API Gateway, AWS Application Load Balacner에 전달되는 HTTP(S) 요청을 모니터링할 수 있게 해주는 웹 애플리케이션 방화벽
* 구성 요소는 다음과 같다.
    * 웹 액세스 제어 목록 : 보호 전략 정의 (규칙과 리소스의 맵핑을 관리, 규칙 우선순위 설정 등)
    * 규칙 : 각 조건과 조치 내용 정의
    * 규칙 그룹 : 규칙들의 그룹
</br>
</br>

---
## Web ACL
1. 대상 종류는 다음과 같다.
    - AWS CloudFront
    - AWS API Gateway
    - AWS Application Load Balancer
    - AWS AppSync GraphQL API
    - AWS Cognito user pool
    - AWS App Runner service
    - AWS Verified Access instance
2. 기본 정책은 다음과 같으며, 자체 규칙 또는 규칙 그룹을 통해 정책을 추가한다.
    * 기본 정책
        - 허용 : 대부분의 사용자가 접근하도록 허용 (BlackList)
        - 차단 : 대부분의 사용자가 접근하지 못하도록 차단 (WhiteList)
3. 규칙 우선순위
    * 규칙 그룹 안에 있는 규칙의 우선 순위는 그룹 밖에 있는 규칙의 우선 순위에 영항을 주지 않는다!!!
    * 우선 순위 리스트 예제
        ```
        Rule1 – priority 0
        RuleGroupA – priority 100
            RuleA1 – priority 10,000
            RuleA2 – priority 20,000
        Rule2 – priority 200
        RuleGroupB – priority 300
            RuleB1 – priority 0
            RuleB2 – priority 1
        ```
    * 우선 순위 적용 결과
        ```
        Rule1
        RuleGroupA RuleA1
        RuleGroupA RuleA2
        Rule2
        RuleGroupB RuleB1
        RuleGroupB RuleB2
        ```
4.본문 크기 제한
    * Web ACL의 기본 본문 검사 크기 제한은 16KB (최대 64KB까지 늘릴 수 있음)
</br>
</br>

---
## Rule Group
Web ACL에 추가할 수 있는 재사용 가능한 규칙의 집합
* 크게 두가지 규칙 그룹이 있다
    1. 관리형 규칙 그룹 : AWS 규정해 놓은 규칙들
    2. 자체 규칙 그룹 : 자체적으로 만들어 놓은 규칙들
</br>
</br>


---
## Rule
HTTP(S) 웹 요청을 검사하는 기준과 일치할 때 수행할 작업을 정의
* Rule Type
    * Regular rule
        * 조건에 일치하는 요청을 찾는다
        * 논리문 사용 가능 (AND, OR, NOT)
    * Rate-based rule
        * 너무 빠른 속도로 수신되는 수신 요청 및 속도 제한 요청을 계산
            > 나중에 공부, DDOS 방어 등도 가능해보임
* Inspect (검사 가능 항목은 다음과 같다)
    - HTTP method
    - Single header
    - All headers
    - Header order
    - Cookies
    - URI path
    - Query string
    - Single query parameter
    - All query parameters
    - Body
    - JSON body
    - 등등
* Match Statement (일치 조건 목록은 다음과 같으며, 각 목록마다 WCU가 다르다!!)
    - 요청의 Source 국가
    - IP set 및 주소 범위 
    - 동일한 웹 ACL의 다른 규칙에 의해 추가된 레이블에 대한 요청을 검사 (앞에 조건에 의해 Lable이 추가되고, 이를 다시 검사)
    - Regex 일치
    - Regex pattern set
    - 문자열 일치
    - 크기 제약 (요청의 특정 부분 크기)
    - 악성 SQL 코드 (SQL Injection 등)
    - 스크립팅 탐지 (XSS)
* Action (조건을 만족하는 경우 수행할 동작을 정의한다)
    - Allow and Deny : 일치하는 요청에 대하여 Web ACL의 모든 처리(action)를 중지
    - Count : 일치하는 요청에 대하여 다음 규칙 셋트를 따르는 규칙을 계속 처리
    - CAPTCHA and Challenge : 일치하는 요청이 있으면 토큰 상태를 확인한 후, 유요한 경우 - 다음 규칙 셋트를 따르는 규칙을 계속 처리
</br>
</br>


---
## JSON






---
## Logging
https://docs.aws.amazon.com/waf/latest/developerguide/logging.html
* Log Location
    * Amazon CloudWatch Log
    * Amazon S3 Bucket
    * Amazon Kinesis Data Firehouse stream
</br>
</br>




---
## Deploy
변경 사항을 적용하기 전에 테스트하고 조정하는 것이 좋다. 이를 위해 AWS에서는 몇가지 기능을 제공한다.
* https://docs.aws.amazon.com/ko_kr/waf/latest/developerguide/web-acl-testing.html
* Request sampling options
    * 옵션을 활성화하면 검사한 요청에 대한 샘플을 확인할 수 있음
</br>
</br>


---
## 보안 취약점 목록
* Core rule set : 일반적인 웹 애플리케이션 관련 공격 패턴
* SQL database rule : SQL Injection과 같은 SQL 관련 공격 패턴 
</br>
</br>



---
### Test 진행
https://sessin.github.io/awswafhol/pre/module2.html
</br>



---
### AWS Shield
* AWS Shield : DDoS 공격 방어 서비스. 네트워크 및 전송 계층(Layer 3, 4)과 애플리케이션 계층(Layer 7)에서 AWS 리소스에 대한 DDoS(Distributed Denial of Service) 공격으로부터 보호

* AWS Shied Advanced : 

</br>
</br>




### AWS Firewall Manager
* AWS Firewall Manager : Multiple accounts에 대하여 AWS WAF, AWS Shield Advanced, Amazon VPC 보안 그룹, AWS Network Firewall 및 Amazon Route 53 Resolver DNS Firewall을 비롯한 다양한 리소스들을 유지 및 관리하는 서비스 
</br>
</br>