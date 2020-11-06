# Web Server
* 요즘에는 Web Server라고 하면, Web + WAS Server를 말한다.

## Web Server
* HTTP(HyperText Transfer Protocol)를 통해 Web browser에서 요청하는 HTML 문서나 오브젝트(이미지 파일 등)를 전송해주는 서버.
* HTTP : HyperText Transfer Protocol, W3 상에서 정보를 주고받을 수 있는 프로토콜로 80번 포트를 사용한다.

## CGI(Common Gateway Interface)
* 공용 게이트웨이 인터페이스, Web Server와 외부 프로그램 사이에서 정보를 주고받는 방법이나 규약
* Web Server가 동적으로 동작하기 위해 고안된 방법으로, 웹 서버가 동적 로직을 외부 프로그램을 호출하여 처리하고 결과를 반환한다.
* CGI 동작
    1) Request가 Web Server로 들어오면, Web Server는 Request URL에 대응하는 CGI를 찾는다.
    2) 대응하는 CGI가 있는 경우, 환경 변수와 표준 입력의 형태로 요청을 전달하고, 비즈니스 로직을 fork를 통해 새로운 프로세스를 생성하여 처리한다.
    3) 처리한 결과를 Web Server에게 반환한다.
* 문제점
    * 동일한 URL을 받아도, 새로운 프로세스를 생성하기 때문에 리소스 낭비 및 부하가 심하다(Multi-Process).
* 해결 방안
    * Servlet(Java) - Multi-Thread 방식
    * WSGI(Web Server Gateway Interface, Python) 
    
    
## WAS Server
* Web Application Server, Servlet Container 라고도 한다.
* 동적 콘텐츠를 제공하기 위해 수행하는 서버. Servlet을 관리하는 서버.
* 기존 Web Server는 요청에 대한 문서나 오브젝트를 전송하기만 하는 정적인 동작만 할 수 있기 때문에, WAS Server와 연동하여 사용된다.


## Web Structure
![WebStructure](img/WebArchitecture.png)


## Session



## Cookie





### Reference
* https://sehun-kim.github.io/sehun/spring-short-story/
* https://m.blog.naver.com/PostView.nhn?blogId=tothesky21&logNo=40038856386&proxyReferer=https:%2F%2Fwww.google.com%2F
* https://brownbears.tistory.com/350
