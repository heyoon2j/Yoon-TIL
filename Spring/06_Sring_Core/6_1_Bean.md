# Bean
* Spring Container에 의해서 관리되는 객체
* Bean 정보는 보통 xml 형식으로 Resource Directory에 저장된다.

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

## 




## Example
```java
ApplicationContext context = new ClassPathXmlApplicationContext("dao.xml");
Dao dao = context.getBean("dao", Dao.class);
dao.run();
```

## 
* 
