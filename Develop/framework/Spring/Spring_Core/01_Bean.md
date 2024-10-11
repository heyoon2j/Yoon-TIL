# Bean
* Spring Container에 의해서 관리되는 객체
* Bean 정보는 보통 xml 형식으로 Resource Directory에 저장
* Bean 객체는 IoC Container가 실행되면 생성되고, IoC Container가 종료되야 삭제된다.

## Bean 구성
* xml ```<bean />```으로 정의
* Bean Configuration
    * Package-qualified class Name, Actual implementation class
    * Bean의 동작 설정(scope, lifecycle 등)
    * Reference
    * 다른 Configuration
* Example
    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">
    
    	<bean id="tv" class="Polymorphism4.LgTV"
        		  init-method="powerOn"
        		  destroy-method="powerOff"
                  lazy-init="true">
        </bean>
    
    <!--
    	<import resource="other.xml"/>
    -->
    </beans>
    ```
    * ```<beans xmlns="">```: xmlns는 XML Name Space를 의미한다.
    * ```<bean>```: Bean 객체 생성
        * ```init-method```: Bean 객체가 생성될 때, 실행되는 메서드
        * ```destroy-method```: Bean 객체가 종료될 때, 실행되는 메서드
        * ```lazy-init```: 기본적으로 IoC Container가 생성될 때 객체가 생성되므로 Pre-Loading이 기본이지만, Laze-loading을 하고 싶을 때 사용
    * ```<import resource="other.xml />```: 해당 xml이 호출될 때, 해당하는 resource를 호출


## Bean Naming
* Bean은 하나의 ```id```를 가져야 된다.
    * id는 공백 등이 불가능
* Bean은 여러 개의 ```name```을 가질 수 있다.
    * name은 공백, 특수문자 등이 가능
* 서로 다른 Bean끼리는 id, name이 달라야 된다.
* 생성된 Bean 객체는 PackagePath.ClassName#Number
    * Ex> polymorphism4.LgTV#0

## Bean Factory
* Factory Method 호출(Facotry Method 개념은 Factory Method Pattern 확인)
* ```factory-method```와 ```factory-bean``` 키워드를 이용
* ```factory-bean``` : Bean의 id 입력
* ```factory-method``` : Bean의 method 입력
* Example
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="daoFactory" class="kr.co.fastcampus.cli.DaoFactory"/>
    <bean id="dao" name="dao1, dao2, dao3" class="kr.co.fastcampus.cli.Dao"
          factory-bean="daoFactory"
          factory-method="create" />
   
</beans>
```

## Java Example
```java
ApplicationContext context = new ClassPathXmlApplicationContext("dao.xml");
Dao dao = context.getBean("dao", Dao.class);
dao.run();
```

