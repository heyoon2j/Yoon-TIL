# Annotation-based
* 기존에 배운 것들을 Annotation 기반으로 작성
* Annotation vs XML => 상황에 따라 다르다.
* Annotation 기반으로 하기 위해서는 아래 내용을 추가해야 된다.
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>

</beans>
```

## Annotation 종류
* #### ```@Autowired```
    * Bean과 Bean의 관계를 자동으로 연결시켜주는 방법
    * xml ```ref``` 대신에 사용된다고 생각하면 된다.
    * 비어있는 객체를 연결시킨 경우 에러가 발생하므로, 비어있는 객체를 연결시키기 위해서는 ```(required = false)``` 또는 ```@Nullalbe``` 를 추가해야 된다.
    * Example
    ```java
  public class A  {
     @Autowired(required = false) private B b;
  
     @PostConstructor
      void init() {
          log.info("" + b);
      }
  }
    ```
* #### ```@Primary ```
    * ```@Autowired```와 같이 사용되며, 같은 타입의 Bean이 있는 경우
    ```@Primary```을 가진 Bean이 우선순위를 가지게 된다.
    * xml 설정에서는 ```primary="true"```을 사용하면 된다.
    * Example
    ```java
    @Configuration
    public class AppConfig {
        @Bean
        @Primary
        public B b1(){
            return new B();
        }
    
        @Bean
        public B b2(){
            return new B();
        }
    }
    ```
* #### ```@Qualifier```
    * 같은 타입의 Bean을 만들 때, 원하는 Bean으로 설정 가능
    * ```@Primary```와 다르게, id, name을 이용하여 설정
    * Example
    ```java
    public class A  {
        @Autowired @Qualifier("b1") private B b;
    
        public A(B b) {
            this.b = b;
        }
    }
  
    // XML 
    /*
    <bean class="kr.co.fastcampus.cli.B">
      <qualifier value="b1"/>
    </bean>
    <bean class="kr.co.fastcampus.cli.B">
      <qualifier value="b2"/>
    </bean>
    */
    ```
   
* #### ```@Resource```
    * ```@Autowired```와 ```@Qualifier``` 를 합쳐놓은 기능
    * name 키워드 사용, name에는 메소드 명이나, 변수 명이 사용된다.
    
* #### ```@Value```
    * ```@PropertySource``` 사용
    * properies 파일을 읽어들인다.
    * application.properties
        * catalog.name=MovieCatalog (Key=Value) 형태
        * 보통 classpath는 resources나 java에 잡힌다.
    * Example
    ```java
    @Configuration
    @PropertySource("classpath:application.properties")
    public class AppConfig {
        @Bean
        public B b1(){
            return new B();
        }
    
        @Bean
        public B b2(){
            return new B();
        }
    }
  
    public class A  {
        @Autowired @Qualifier("b1") private B b;
        @Autowired private ApplicationContext context;
        @Value("${catalog.name}") String catalogName;
  
        // "#{systemProperties['user.catalog'] : systemProperties에 여러 개의  key-value가 있고 그 중에서 user.catalog의 value 값을 가지고 온다.
  
        @PostConstruct
        private
        void init(){
            log.info("" + context);
        }
    }
    ```
    
* #### ```@PostConstruct```
    * Bean이 생성한 후, 실행
    * 
    
* #### ```@PreDestory```
    * Bean이 소멸할 때, 실행
    * 

* #### ```@Bean```
    * 새로운 객체를 인스턴스화, 설정, 초기화하는 메서드를 가리킬때 사용된다.
    * <beans/>의 설정에 영향을 받고, <bean />의 역할을 하게 된다.
    * Method에 설정
    * @Component와 같이 사용할 수 있지만, 일반적으로 @Configuration과 같이 사용된다.
    * "lite" mode: @Configuration과 같이 사용하지 않는 경우. 내부 클래스의 종속성은 처리하지 못하기 때문에
    Factory method 메커니즘으로 동작을하게 된다.
    * init-method, destroy-method를 설정할 수 있다.
    * Example
        * ```@Bean(initMethod="init") / @Bean(destroyMethod="cleanup")```
        * 기존 close or shutdown 메서드가 자동으로 설정되어 있는 경우, ```@Bean(destroyMethod="")``` 식으로 설정하면 자동으로 실행되지 않는다.
    ```java
    public class BeanOne {
    
        public void init() {
            // initialization logic
        }
    }
    
    public class BeanTwo {
    
        public void cleanup() {
            // destruction logic
        }
    }
    
    @Configuration
    public class AppConfig {
    
        @Bean(initMethod = "init")
        public BeanOne beanOne() {
            return new BeanOne();
        }
    
        @Bean(destroyMethod = "cleanup")
        public BeanTwo beanTwo() {
            return new BeanTwo();
        }
    }
    ``` 

* #### ```@Configuration```
    * Bean 정의를 한 소스라는 것을 가리키는데 사용된다.
    * Class에 설정

* #### ```@Scope```
    * 기본 Scope는 Singleton 이다.
    * Example
    ```java
    @Configuration
    public class MyConfiguration {
    
        @Bean
        @Scope("prototype")
        public Encryptor encryptor() {
            // ...
        }
    }
    ```

* #### ```scoped-proxy```
    * Scope를 위해 Proxy를 설정
    * 여기서 주의할 점은 Scope 설정 시 짧은 Scope를 다른 긴 scope의 빈에 주입되는 경우 AOP proxy를 사용해야 한다. 그렇지 않으면
    긴 scope를 가진 Bean은 정상적으로 동작하지 않을 수 있다. 그렇기 때문에 Proxy 패턴을 이용하여 Proxy 객체를 통해 이를 해결한다.
    * ```proxyMode``` 속성 값을 통해 Proxy Mode를 설정할 수 있다. 기본은 ```ScopedProxyMode.NO``` 이며, ```ScopedProxyMode.TARGET_CLASS``` 와 ```ScopedProxyMode.INTERFACES``` 가 있다.



## Annotation vs XML
* 개발 전략에 따라 맞춰서 사용하는 것이 좋다.
* https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-annotation-config
* Spring Docs를 읽어보면 소스 코드에서 관리하기 선호하는 개발자는 Annotation을 선호하고, 
XML을 선호하는 개발자의 경우 Annotation을 사용하면 더이상 POJO가 아니며, 관리가 분산되어 컨트롤하기 더 어렵다고 한다.

### Annotation
* 유지보수 자주 변경되지 않는 경우, 개발의 생산성을 향상시키기 위해 @ 사용
    * DAO, Controller
    * https://blog.naver.com/bamda1127/220995405513
* 내가 만들지 않은 코드의 경우, Annotation을 사용해야 한다.
* 장점(pros)
    * 짧고 간결한다.
* 단점(cons)
    * Annotation이 붙임으로써 특정 조건을 주기 때문에, 더이상 POJO가 아니게 된다.
    * Compile 단계가 필요하다.

### XML
* 유지보수 과정에서 자주 변경되는 경우
* 시스템 전체에 영향을 주는 메타 데이터들은 XML에 작성하여 코드와 독립적으로 분리되는 것이 좋다.
    * ex> dataResource, viewResolver, myBatis 등등
* 장점(pros)
    * 소스 코드를 건드릴 필요없다.
    * Recompiling이 필요없다.
* 단점(cons)
    * 엄청 길어진다.
