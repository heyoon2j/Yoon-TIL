# Web Application Security Risk
* 웹 서비스 보안 취약점 정리
* Top 10 List : https://owasp.org/
    1) Broken Access Control
    2) Cryptographic Failures
    3) Injection
    4) Insecure Design
    5) Security Misconfiguration
    6) 
</br>

## 


## HTTP Desync Attack (HTTP Request Smuggling)
* Hops 구조에서 서버 간 Content-Length와 Transfer-Encoding의 처리 방식 차이를 이용한 Suggling 공격
* 해당 공격에 사용되는 HTTP Header는 ```Trnsfer-Encoding: chunked```와 ```Content-Length``` 이다.

> HTTP Request Smuggling은 HTTP 처리 중 여러가지 방법을 통해 공격자가 의도한 요청을 백엔드로 전달하는 기술
</br>


### Transfer-Encoding: chunked & Content-Length
* ```Transfer-Encoding: chunked```
    * Content-Length가 큰 경우, 전체 사이즈를 계산하는데 오래 걸린다. 그렇기 때문에 전체를 한 번에 전송하는 것이 아닌, 일정 덩어리(chunked)를 조금씩 떼어 줌으로써 속도를 향상시킬 수 있다.
    * 전송 과정은 다음과 같다.
    ```
    Host: test.com
    Content-Type: text/plain
    Transfer-Encoding: chunked

    3\r\n
    Hey\r\n
    9\r\n
    Test code\r\n
    4\r\n
    Last\r\n    
    0\r\n
    \r\n
    ```
    1) 서버는 보내려는 사이즈를 데이터에 포함시켜 알린 후, 실제 데이터에서 해당 사이즈만큼 잘라서 보낸다.
        ```
        3\r\n       # Length
        Hey\r\n     # Data
        ```
    2) 1)번을 반복하고, 보낼 데이터가 없다는 의미의 "0"을 받으면 해당 전송이 완료된다.
        ```
        0\r\n
        \r\n
        ```


* ```Content-Length```
    * 전송할 데이터의 사이즈를 계산하여 헤더에 표시한다.
    ```
    Host: test.com
    Content-Type: text/plain
    Content-Length: 3

    ABC
    ```
    * 서버는 보내려는 데이터 사이즈를 헤더로 알린 후, 알려준 사이즈만큼 보낸다.
</br>

### 공격 방법



* Reference
    * https://www.hahwul.com/2019/08/12/http-smuggling-attack-re-born/
    * https://lactea.kr/entry/Paypal%EC%97%90%EC%84%9C-%EB%B0%9C%EA%B2%AC%EB%90%9C-HTTP-Request-Smuggling-%EC%84%A4%EB%AA%85-%EB%B0%8F-%EC%98%88%EC%A0%9C
    * https://goyunji.tistory.com/8