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
    
* #### ```@PreDestory```
    * Bean이 소멸할 때, 실행



