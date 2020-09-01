# Servlet
* Web Programming에서 Client의 요청을 처리하고 그 결과를 다시 Client에게 전송하는 역할을 하는 자바 프로그램
* Java Code 안에 HTML이 삽입되어 있다.


## Maven 연동
1) Move Maven Central (https://search.maven.org/)
2) Search javax.servlet (javax.servlet-api)
3) Copy Maven Code


## Maven War Plugin 연동
1) Move Maven War Plugin (https://maven.apache.org/plugins/maven-war-plugin/)
2) pom.xml에 packaging 추가
3) Project Structure도 맞춰줘야 된다.
4) web.xml에 Basic Example을 복사 (https://javaee.github.io/servlet-spec/downloads/servlet-4.0/servlet-4_0_FINAL.pdf)

## CGI(Common Gateway Interface)
* 공용 게이트웨이 인터페이스 
* CGI는 Web Server와 외부 프로그램 사이에서 정보를 주고받는 방법이나 규약
* 어떤 식으로 요청을 처리하고 응답할지를 정한다.

## Servlet Container
* Tomcat 등이 있다.
* 역활은 아래와 같다.
1) Web Server와 Servlet이 서로 통신할 수 있게 한다.
    * 원래는 socket을 생성하고, listen, accept, read등을 해야 되지만 API로 기능을 제공
2) Multi-Threading 지원
    * 다중 요청을 처리해준다.
3) Servlet Lifecycle 관리

4) 선언적인 보안 관리
    * XML을 통해 보안 관리 가능


## Servlet 동작 방식
![ServletOrder](img/ServletOrder.png)
1) Client가 Web Server에 Request를 보낸다.
2) Servlet Container는 HttpServletRequest, HttpServletResponse 객체를 생성한다.
3) Client가 요청한 URL을 분석하여, web.xml에 해당 Sevlet이 있는지 확인
4) Servlet 객체를 생성 or 로드  (Servlet LifeCycle 참조)
5) 새로운 Thread에 작업 호출
6) Servlet의 service() 호출
7) GET인 경우 doGet(), POST인 경우 doPost() 호출하여 실행 결과를 HttpServletResponse에 저장
8) Client에 Response를 보내고, HttpServletRequest, HttpServletResponse 객체 소멸
 
## Servlet Lifecycle
![ServletLifecycle](img/ServletLifecycle.PNG)
*그림 - https://placeforjake.tistory.com/43*
1) Request가 들어오면 Container는 해당 Servlet의 Instance가 Memory에 있는지 확인하고, 없을 경우 Instance를 생성하고
init()를 호출한다(필요에 따라 init()를 Overriding하여 구현)
2) 생성(로드)된 Instance는 service()를 호출하고, Request에 따라 doGet, doPost()를 호출한다.
3) Container가 Sevlet의 종료 요청을 하게 되면, destroy()가 호출된다(필요에 따라 destroy()를 Overriding하여 구현)
 

## Servlet 구조
```xml
<servlet>
    <servlet-name>catalog</servlet-name>
        <servlet-class>com.example.CatalogServlet
        </servlet-class>
    </servlet>
<servlet-mapping>
    <servlet-name>catalog</servlet-name>
    <url-pattern>/catalog/*</url-pattern>
</servlet-mapping>

<filter>
    <filter-name>simpleFilter</filter-name>
    <filter-class>kr.co.fastcampus.web.SimpleFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>simpleFilter</filter-name>
    <url-patter>/simple</url-patter>
</filter-mapping>
```
* servlet과 servlet-mapping은 서로 한 쌍을 이룬다.
* **<servlet-class>** : Servlet Class Name(extends HttpServlet)
* **<servlet-name>** : Servlet Name
* **<url-pattern>** : 해당 Servlet에 대한 URL Pattern
* **<filter-class>** : Filter Class Name(implements Filter)

## Servlet 3.0 이상부터 사용되는 Annotation
* web.xml이 필요없다.
```java
@WebServlet(
        name = "simple",
        urlPatterns = "/simple"
)

```



## Session
* Request 객체를 통해 가지고 올 수 있다.
```java
@Slf4j
public class SimpleFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("Filter-Hello World");
        HttpSession session = ((HttpServletRequest)request).getSession();
        String username = (String)session.getAttribute("username");
        if(username == null){
            log.info("new user");
            session.setAttribute("username", "yoon2");
        }else{
            log.info("user -> "+username);
        }
        chain.doFilter(request, response);
        var writer = response.getWriter();
        writer.println("filter - Hello World!!");
    }
}
```


## Deployment Descriptor(DD, 배포 서술자)
* 보통 WEB-INF Directory 아래의 web.xml 파일을 가리킨다.
* Servlet Mapping 정보, Listener, Filter, Secure 등에 대한 설정이 저장된다.



## Install Tomcat 
1) Move to https://tomcat.apache.org/download-90.cgi
2) Select Version and Download Tomcat
3) Copy war file to webapps directory
4) Start startup.sh or startup.bat in tomact/bin directory



### Reference
* https://mangkyu.tistory.com/14
* https://lee-mandu.tistory.com/12?category=633570
* https://sjh836.tistory.com/126