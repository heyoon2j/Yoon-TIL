# Spring Boot

spring-boot-starter-web
spring-boot-starter-test

- Spring boot는 tomcat을 라이브러리로 가지고 있어서
8080 포트로 바로 구동시킬 수 있다.
=> 보통 Unit 테스트를 할때 서버를 킬 필요가 없는데 어떻게 
테스팅이 가능한가 똑같은 방법으로 사용
SpringApplication.run(SpringBootProject01Application.class, args);

SpringApplication appication = 
	new SpringApplication(SpringBootProject01);
application.setWebApplicationType(WebApplicationType.NONE);
// GenericConext가 생성되기 때문에 Tomcat이 실해되지 않는다.
// Web으로 사용하고 싶으면, WebApplicationType.SERVLET

application.setBannserMode();
application.run(args)

=================================
application.properties: 그냥 열면 안되고 Spring Properties Editor로 열어야 한다.
properties 설정이 우선순위가 높다

## WebApplication Type Setting
spring.main.web-application-type=none // none, servlet등 가능


## Server Port Setting
server.port=7777

## Banner Setting
spring.main.banner-mode=console
//

## Logging Setting
logging.level.org.springframework=debug


banner.txt가 있으면 해당 Banner를 적용시킨다.
My Banner

${spring-boot.formatted-version}

banner 예제 사이트: patorjk.com/software/taag/

SpringBoot는 AnnotationConfigApplicationContext를 사용
new AnnotationConfigApplicationContext(configJava.class);

@SpringApplication
    @SpringConfiguration
    @EnableAutoConfiguration: CommonMultipartResolver와 같은 내가 만들지 않은 객체들을 메모리에 생성하는 역할.
    자동으로 사용가능한 설정을 찾는다. spring-boot.autoconfig의 META-INF의 spring.factories의 파일을 읽는다.
    @Configuration에 @Conditional이 있다면 해당 조건을 만족해야 동작한다.
    @ConditionalOnWebApplication: 해당 애플리케이션의 타입 확인
    @ConditionalOnClass: classpath에 해당 Class가 있다면
    @ConditionalOnMissingBean: 해당 Bean이 메모리에 없다면
    @AutoConfigureOrder: 우선 순위 
    @AutoConfigureAfter: 자동 설정의 순서


@ComponentScan(BasePackages)
@RestController
@ResponseBody
@GetMapping

@Configuration: 해당 클래스는 Spring 환경설정 클래스라는 의미


우리도 AutoConfiguration Class 파일을 만들었으면
따로 proj의 META-INF에 spring.factories를 만들고 하면 된다.

org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
com.rubypaper.jdbc.config.BoardAutoConfig


```java
// ApplicationRunner는 해당 클래스가 생성이 되면 run Method가 실행된다.
public class JDBCConnectionManagerRunner implements AppicationRunnur {}
    @Autowired
    private JDBCConnectionManager manger;
    
    
    public void run(ApplicationArguments args) throws Exception {
        
    }
}

```

// Overridng을 하기 위해서는 application.properties에
spring.main.allow-bean-definition-overriding

// 자동으로 생성되는 클래스에 설정만 바꾸는 방법
boar.jdbc.

@ConfigurationProperties(prefix="board.jdbc")   // 외부 Properties를 읽어들여서 prefix만 읽어 들이겠다
public class JDBCConnectionManagerProperties {

}

@EnableConfigurationProperties(JDBCConnectionManagerProperties.class)
public class BoardAutoConfiguration {

}




















