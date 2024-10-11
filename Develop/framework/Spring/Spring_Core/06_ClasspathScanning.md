# Classpath Scanning and Managed Components
* Java는 Classpath를 기준으로 관리할 Components를 Scanning 한다.
* 이때에 Bean Definition을 등록하게 된다.
* Classpath를 지정하여 사용(관리)할 수 있다.

1. **@Component**
    * @Repository, @Service, @Controller 등은 @Component를 가지고 있다.
    * @Component를 사용하기 보다는 @Controller와 같은 적합한 Annotation을 사용하는 것이 좋다.
2. **@Repository**
    * DAO에 필요한 서비스를 담당하는 Bean을 의미
3. **@Service**
    * Business Logic을 담당하는 Bean을 의미
4. **@Controller**
    * Controller를 담당하는 Bean을 의미

## 사용방법
1. Configuration Bean을 및 XML에 등록
    * **@Configuration** 등록
    * Annotation을 이용하여 **@ComponentScan(basePackages = "kr.co.example")** 등록
    ```java
    @Configuration
    @ComponentScan(basePackages = "kr.co.fastcampus.cli")
    public class AppConfig {
       // basePackages에 Component를 Scan할 Package 위치 입력
    }
       
    // XML, 해당 Configuration Bean은 XML 등록해야 된다.
    <bean id="appConfig" class="kr.co.fastcampus.cli.AppConfig" />
    ```
    * XML을 이용하여 ComponentScan 등록 방법, <context:component-scan /> 태그는 <context:annotation-config />을 포함하고 있다.
    ```xml
    <beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:context="http://www.springframework.org/schema/context"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
            https://www.springframework.org/schema/beans/spring-beans.xsd
            http://www.springframework.org/schema/context
            https://www.springframework.org/schema/context/spring-context.xsd">
       
       <context:component-scan base-package="org.example"/>
    </baeans>
    ```
    
    
2. 사용할 Component 등록
    * **@Component, @Service** 등을 이용하여 Bean 설정
    ```java
    @Component
    public class A  {
    }
    ```
    
3. AnnotationConfigApplicationContext를 사용



