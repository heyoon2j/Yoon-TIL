# Spring Framework Overview
* Spring은 Java EE(Enterprise Edition)를 쉽게 만들게 한다.
* Spring Framework 5.1 이상부터 JDK 8 이상 지원
* Docs: https://docs.spring.io/spring/docs/current/spring-framework-reference/

## Maven 연동
* Search **org.springframework spring-context** at Maven Central

## IoC Container
* IoC(Inversion of Control)
* Object의 생성과 관계설정, 사용, 제거 등의 작업을 Application Code가 대신에 독립된 컨에이너가 제어권을 담당한다.
* 실제로 IoC Container라고 얘기하는 것은 **ApplicationContext** 인터페이스를 구현한 클래스의 Object를 말한다.
* Basic Package
    * org.springframework.beans
    * org.springframework.context
* Interface
    * BeanFactory : 기본 Interface
    * ApplicationContext : BeanFactory의 하위 Interface, 더 많능 제공
        * Easier integration with Spring's AOP features
        * Message resource handling
        * Application-Layer의 특정 context 사용을 의한 WebApplicationContext
        * Event Publication
        
![IoCContainer](../img/IoCContainer.png)
*https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html#beans-introduction*
* IoC Container를 동작하기 위해서는 2가지가 필요하다.
    1) **POJO Class** : 내가 실행하기 위해 만든 클래스
    2) **Configuration Metadata** : 클래스를 실행시킬 설정들을 담은 파일(Resource Directory에 저장되어 있다.)

* Bean : Spring Container에 의해서 관리되는 객체
* DI : Dependency Injection, 의존성 주입
* DAO : Database Access Object, Database 접근에 대한 Object
        
## ApplicationContext
* org.springframework.context.ApplicationContext
