# Bean
* Spring Container에 의해서 관리되는 객체
* Bean 정보는 보통 xml 형식으로 Resource Directory에 저장

## Bean 구성
* xml ```<bean />```으로 정의
* Bean Configuration
    * Package-qualified class Name, Actual implementation class
    * Bean의 동작 설정(scope, lifecycle 등)
    * Reference
    * 다른 Configuration

## Bean Naming
* Bean은 하나의 ```id```를 가져야 된다.
* Bean은 여러 개의 ```name```을 가질 수 있다.
* 서로 다른 Bean끼리는 id, name이 달라야 된다.

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

