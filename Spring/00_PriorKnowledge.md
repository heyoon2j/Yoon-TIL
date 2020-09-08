# Prior Knowledge
* 기본 사전 지식

## Java File Compile 과정
```shell script
$ javac -cp lib\logback-classic.jar;lib\logback-core.jar;lib\slf4j-api.jar kr\co\fascamfus\Main.java
$ java -cp lib\logback-classic.jar;lib\logback-core.jar;lib\slf4j-api.jar;. kr.co.fascamfus.Main
  
$ jar -c -m manifest.txt -f fascamfus.jar kr\* logback.xml
$ java -jar fascamfus.jar // 실행 가능한 jar File
```
* javac - cp {Class File Path} {JAVA File} : JAVA File을 Class File로 변환
* java -cp {Class File Path} {Class FIle} : Class File 실행
* java -jar {JAR File} : JAR File을 이용하여 실행

## Context
* 필요한 정보를 포함하고 있는 설정 파일
* Spring에서는 기본적으로 2개가 들어간다.
    1) Root WebApplicationContext
    2) Servlet WebApplicationContext

## Servlet/JSP Listener Interface 종류
1. ServletContextListener
    * Servlet 기반 Web Application의 시작, 종료 Event에 대한 Listener
    * **public void contextInitialized(ServletContextEvent sce)** : 웹어플리케이션을 초기화할 때 호출
    * **public void contextDestroyed(ServletContextEvent sce)** : 웹 어플리케이션을 종료할 때 호출.
2. HttpSessionListener
    * Http Session의 시작, 종료 Event에 대한 Listener
3. ServletRequestListener
    * Client로부터 Request를 받고, Servlet Request Listener

### ContextLoaderListener
* ServletContextListener Interface의 구현체
* Initialize the root web application context(Java Docs)
* 해당 Servlet의 Lifecycle에 맞추어 ApplicationContext를 추가, 삭제 하도록 한다.
* Servlet에 Spring 연동 Example
```xml
<web-app>
    <context-param>
        <param-name>contextClass</param-name>
        <param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationCentext</param-value>
    </context-param>

    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/applicationContext.xml</param-value>
    </context-param>

    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>

    <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>/WEB-INF/dispatcher-servlet.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
</web-app>
```
* ```contextClass``` : 
* ```contextConfigLocation``` : 



## Web Application 설정 및 동작
* **Spring Framework**는 **Servlet 기반의 Web Application을 쉽게 만들 수 있도록 
도와주는 Framework**이다.
* **Spring Framework**를 사용한다는 의미는 아래와 같다.
    * IoC Container 사용
    * Spring MVC 사용
* Servlet을 사용하기 위해서 Maven에 등록
    * **javax.servlet-api** 등록(pom.xml)
* Spring(IoC Container, MVC)을 사용하기 위해서 Maven 등록
    * **spring-context** 등록(pom.xml)

![SpringOrder](img/SpringOrder.png)
1. Servlet Container 초기화(web.xml 기준으로 초기화, 보통 webapp/WEB-INF에 저장)
    * Servlet 생성 및 삭제, Request를 처리
2. ContextLoaderListener 생성
    * Servlet Container의 Listner로써 Servlet의 생명주기를 관리
    * Servlet을 시작/종료 시점에 Servlet Context에 ApplicationContext 등록/삭제
3. ContextLoader가 root-context.xml Loading
    * **context-parm의 contextConfigLocation**에 설정한 xml 파일 Loading
    * 파일 : **root-context.xml(applicationContext.xml)**
4. Root Spring Container 실행
    * **root-context.xml(applicationContext.xml)** 에는 주로 Service, DAO 등이 설정
5. Client로 부터 Request
6. Dispatcher Servlet 생성
    * FrontController 역할 수행(Front Controller Pattern 이용)
7. servlet-context(presentation-layer.xml) Loading
    * **servlet-context(presentation-layer.xml)** 에는 controller, ViewResolver, Interceptor 등이 설정
8. Dispatcher Servlet에 해당하는 Spring Container 실행(controller, ViewResolver, Interceptor 등)


## Spring MVC 흐름
![SpringStream](img/SpringStream.jpg)
*https://owin2828.github.io/devlog/2019/12/30/spring-10.html*
1) **DispatcherServlet**이 모든 Request과 Response를 관리하게 된다.
    * **DispatcherServlet** 또한 Servlet Container가 관리
    * **FrontController Pattern** 역할을 하게된다(URL Mapping을 web.xml 또는 @Annotation을 이용하는 것이 아닌
    해당 FrontController Servlet이 관리)
2) Request가 들어오면 **DispatcherServlet**은 **HandlerMapping Bean** 객체에게 Controller 검색을 요청
    * **HandlerMapping**은 **Controller Bean** 객체를 **DispatcherServlet**에게 전달
3) **DispatcherServlet**는 전달받은 **Controller Bean** 객체를 **HandlerAdapter Bean**에게 요청 처리를 위임
4) **HandlerAdapter Bean**은 **Controller**의 Business Logic을 실행
5) **Controller**는 결과를 **ModelandView** 객체로 **DispatcherServlet**에게 전달.
6) **DispatcherServlet**은 결과를 보여줄 View를 찾기 위해 **ViewResolver Bean**객채를 이용한다.
7) **ViewResolver**에게 받은 **View** 객체을 이용해 Response 결과를 생성해 Client에게 전달

### Reference
* https://12bme.tistory.com/555
* https://galid1.tistory.com/524
* https://javannspring.tistory.com/231