# Bean Scopes
* Bean의 객체 생성에 대한 범위를 지정
* ```scope``` 키워드를 이용하여 설정 가능

## singleton
* 하나의 객체만 생성(Singleton Pattern 확인)
* Bean의 Scope는 Default가 singleton 이다.

## prototype
* IoC를 호출할 때, 매번 새로운 객체를 생성
* ```ref```를 이용하여 참조할 때도 새로운 객체 생성
* Example
    1) XML
    ```xml
    <bean id="A" class="kr.co.fastcampus.cli.A" scope="prototype">
    ```
    
    2) Annotation
    ```java
    @Component
    @Scope("prototype")
        
    ```

## 그 외
* Spring ApplicationContext에서만 유효한 설정
* **request**: 하나의 HTTP Request의 Lifecycle에 의해 범위가 정의되는 Bean 객체이다. 각 각의 요청마다 
객체가 생성된다.
* **session**: HTTP의 Lifecycle에 의해 범위가 정의되는 Bean 객체. 각 각의 세션에 Bean이 생성되며,
 Session이 종료되면 객제가 소멸된다.
* **application**: ServletContext에 의해 범위가 정의되는 Bean 객체. 웹 응용 프로그램에 대해 한 번 Bean 객체가 생성된다.
* **websocket**: WebSocket에 의해 범위가 정의되는 Bean 객체.

## 주의해야 할 점
1. 여기서 주의할 점은 Scope 설정 시 자신보다 긴 다른 scope의 빈에 주입되는 경우 AOP proxy를 사용해야 한다.
 DI로 Proxy Object를 미리 주입하고, 호출 시 Proxy Object에 Bean 객체를 붙이게 된다.
    * ex> Singleton <- Session
    * ```<aop:scoped-proxy/>``` 태그 적용
    * 기본 Proxy는 CGLIB-based class proxy 이다. 그렇기 때문에 standard JDK interface-based proxy를 사용하기 위해서는 ```proxy-target-class``` 속성 값을 false로 지정하면 된다.
        * standard JDK interface-based proxy는 인터페이스를 이용하기 때문에 인터페이스를 구현한 객체가 최소 하나가 있어야 된다.
    * Example
    ```xml
    <!-- an HTTP Session-scoped bean exposed as a proxy -->
    <bean id="userPreferences" class="com.something.UserPreferences" scope="session">
        <!-- instructs the container to proxy the surrounding bean -->
        <aop:scoped-proxy proxy-target-class="false"/>
    </bean>
    
    <!-- a singleton-scoped bean injected with a proxy to the above bean -->
    <bean id="userService" class="com.something.SimpleUserService">
        <!-- a reference to the proxied userPreferences bean -->
        <property name="userPreferences" ref="userPreferences"/>
    </bean>
    ```
2. Prototype으로 주입 시 조심해야된다.
     * ex> Singleton <- Prototype / https://renuevo.github.io/spring/scope/spring-scope/
     * 글을 읽어보면 당연한 상황이란 생각이 들지만, 각 Object의 특성을 생각하면서 주입해야 된다는 것을 다시 한번 일깨워준다.

     
     
     
     
     