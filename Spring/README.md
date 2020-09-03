# Spring
* Web Framework for JAVA 
* We learn
    * Logback
    * Maven
    * Servlet
    * Spring Core
        * IoC Container Bean
        
    
* 기본 사전 지식
    * java File Compile 과정
        ```shell script
        $ javac -cp lib\logback-classic.jar;lib\logback-core.jar;lib\slf4j-api.jar kr\co\fascamfus\Main.java
        $ java -cp lib\logback-classic.jar;lib\logback-core.jar;lib\slf4j-api.jar;. kr.co.fascamfus.Main
  
        $ jar -c -m manifest.txt -f fascamfus.jar kr\* logback.xml
        $ java -jar fascamfus.jar // 실행 가능한 jar File
        ```
        * javac - cp {Class File Path} {JAVA File} : JAVA File을 Class File로 변환
        * java -cp {Class File Path} {Class FIle} : Class File 실행
        * java -jar {JAR File} : JAR File을 이용하여 실행

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