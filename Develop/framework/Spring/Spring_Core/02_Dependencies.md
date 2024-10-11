# Dependencies
* Application의 많은 Object는 함께 일을 한다.
* 이런 종속적인 관계를 Code Level에서가 아닌 설정을 통해 처리한다.
* 장점
    * Test를 더 쉽게 할 수 있다.
    * Business에 더 신경 쓸 수 있다.


## Dependency Injection Process
1) ApplicationContext가 설정 메타데이터에 의해 생성(XML, Java, Annotation 등의 메타데이터)
2) 각 각의 Bean은 Factory Method, Constructor 등에 의해 표현되어 생성

### Constructor vs Setter DI
* https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-collaborators 
* 보통은 Setter로 DI를 하는 것이 복잡도를 늘리기 때문에, 최대한 Constructor DI를 하는 것을 권장

    
## Constructor-based Dependency Injection
* 생성자에 대한 의존성 주입
* ```constructor-arg```를 이용
* ```ref```: Constructor에 다른 Class 종속성 주입, id 입력
* ```index```: 생성자 인자의 인덱스
* ```name```: 생성자 인자의 이름
* ```value```: 생성자 인자에 해당하는 값
* Example
```xml
    <!--A는 B에 의존성이 있다. A 객체가 B 객체를 가지고 있다-->
    <bean id="a" class="kr.co.fastcampus.cli.di.A">
        <constructor-arg ref="b" />
    </bean>
    <bean id="b" class="kr.co.fastcampus.cli.di.B">
        <constructor-arg index="0" value="true" />
    </bean>

	<bean id="tv" class="Polymorphism4.SamsungTV">
		<constructor-arg name="speaker" ref="speaker" />
		<constructor-arg name="price" value="150" />
	</bean>
	<bean id="speaker" class="Polymorphism4.AppleSpeaker">
	</bean>    
```
* 하지만 Constructor의 파라미터가 많아지면 동시에 하나의 클래스가 많은 책임을 떠안기 때문에, 리팩토링을 고민해봐야 된다.


## Setter-based Dependency Injection
* Setter에 대한 의존성 주입
* ```property``` 키워드 사용
* ```name``` : Setter의 이름명, 앞에 set을 없애고 써야된다.
```xml
    <bean id="a" class="kr.co.fastcampus.cli.di.A">
        <property name="b" ref="b" />
    </bean>
    <bean id="b" class="kr.co.fastcampus.cli.di.B">
        <property name="condition" value="false" />
    </bean>

	<bean id="tv" class="Polymorphism4.SamsungTV">
		<property name="speaker" ref="speaker" />
        <property name="price" value="150"/>
	</bean>
	<bean id="speaker" class="Polymorphism4.AppleSpeaker">
	</bean>
```


## Circular Dependency
* 순환 참조
* 서로 의존성을 가진 경우, 기본적으로 에러가 발생
    * Constructor Injection의 경우, 에러가 발생한다. 이유는 생성자체가 안되기 때문이다.
    * Setter Injection의 경우, 에러가 나지 않을 수 있다. 이유는 생성을 하고 Setter 함수를 이용하여 초기화하기 때문이다.
* **Bean의 의존성 주입은 객체가 메모리에 적재된 후에 주입되게 된다.**


## Dependency Property Collection
* ```<list/>```: List Collection
* ```<set/>```: Set Collection
* ```<map/>```: Map Collection
* ```<props/>```: Properties Collection
* Example
    * Java
    ```java
    class ExamVO {
      private List<Object> list;
      private Properties props;
      private Map<String, Object> map;
      private Set<Object> set;
      
      public void setMap...
      public void setList...
      public void setProps...
      public void setSet...
      ...
    }
    ```
  
    * XML
    ```xml
    <bean id="strBn" class="java.lang.String">
      <constructor-arg index="0" value="Bean String" />
    </bean>

    <bean id="voBn" class="ExamVO">
      <!-- setMap(java.util.Map) call -->
      <property name="map">
          <entry key="an entry" value="Some String" />
          <entry key="a ref" value-ref="strBn"/>
      </property>
      
      <!-- setList(java.util.List) call -->
      <property name="list">
          <value>Some String</value>
          <ref bean="strBn" />
      </property>
  
      <!-- setProps(java.util.Properties) call -->
      <property name="props">
          <prop key="administrator">administrator@example.org</prop>
          <prop key="support">support@example.org</prop>
          <prop key="development">development@example.org</prop>
      </property>
  
      <!-- setSet(java.util.Set) call -->
      <property name="set">
          <value>Some String</value>
          <ref bean="strBn" />
      </property>          
    </bean>
    ```

## Using depends-on
* ```depends-on```을 이용하여 종속성을 표현할 수 있다.
* 해당 Bean을 초기화기 전에 ```depneds-on```에 설정한 Bean을 먼저 초기화한다.
* 소멸의 경우, ```depends-on```에 설정한 Bean보다 먼저 소멸된다.
```xml
<bean id="beanOne" class="ExampleBean" depends-on="manager"/>
<bean id="manager" class="ManagerBean" />
```
## Lazy-initialized Beans
* 초기화를 게으르게 한다는 의미
* 기본적으로는 ApplicationContext를 구현하면 Singleton Bean을 모두 초기화(생성)한다.
* ```lazy-init``` 키워드를 true로 설정하게 되면, 해당 Bean을 사용하는 시점에서 Bean을 초기화(생성)한다.
* ```beans```의 속성에 ```default-lazy-init``` 키워드를 사용하면, 안에 있는 Bean들을 Lazy-initialization 할 수 있다.
* Memory 낭비를 막을 수 있다!!!
```xml
<bean id="lazy" class="com.something.ExpensiveToCreateBean" lazy-init="true"/>
<bean name="not.lazy" class="com.something.AnotherBean"/>


<beans default-lazy-init="true">
    <!-- no beans will be pre-instantiated... -->
</beans>
```

## Autowiring
* 추천하지 않음...
* 자동으로 연결하는 방법이기 때문에 예상치 못한 결과가 발생할 수 있다.

### Reference
* https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-dependencies
* 정리를 잘하신 내용 같다: https://coding-start.tistory.com/250
