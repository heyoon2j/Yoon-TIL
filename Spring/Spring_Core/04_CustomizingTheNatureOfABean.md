# Customizing the Nature of a Bean
* Lifecycle Callbacks
* ```ApplicationContextAware``` and ```BeanNameAware```
* Other ```Aware``` Interface

## Lifecycle Callbacks
1. Initialization Callbacks
    * 특정 Bean이 생성된 후에, 특정 함수 호출하는 방법
    1. ```org.springframework.beans.factory.InitializingBean``` Interface 구현
        * ```afterPropertiesSet()``` 함수 구현
        * 하지만 Spring에서 권장하지 않는 방법
        
    2. XML에 ```init-method``` 키워드 사용
        * ```init-method```의 값으로 호출할 함수 입력
        * Example
            ```xml
            <bean id="exampleInitBean" class="examples.ExampleBean" init-method="init"/>
            ```
          
    3. Annotation
        * ```@PostConstruct ``` Annotation 이용


2. Destruction Callback
    * 특정 Bean이 소멸될 때, 특정 함수를 호출하는 방법
    * 함수를 호출하기 위해서는 ```ConfigurableApplicationContext close()```를 호출해야 된다.
    
    1. ```org.springframework.beans.factory.DisposableBean``` Interface 구현
        * ```destroy()``` 함수 구현
        * Intialization과 마찬가지로 Spring에서 권장하지 않는 방법
        
    2. XML에서 ```destroy-method``` 키워드 사용
        * ```destroy-method```의 값으로 호출할 함수 입력
        * Example
            ```xml
            <bean id="exampleInitBean" class="examples.ExampleBean" destroy-method="cleanup"/>
            ```
        
    3. Annotation
        * ```@PreDestroy``` Annotation 이용
        

## Default Initialization and Destroy Methods
* 각 각의 Bean에 설정하는 것이 아닌 ```<beans >```에 설정
* ```default-init-method```와 ```default-destroy-method``` 키워드 사용
* Example
```xml
<beans default-init-method="init" default-destroy-method="destroy">
    <bean id="blogService" class="com.something.DefaultBlogService">
        <property name="blogDao" ref="blogDao" />
    </bean>
</beans>
```


## Startup and Shutdown Callbacks
* **Lifecycle Interface**에 정의되어 있는 Method
    * ```void start()```
    * ```void stop()```
    * ```boolean isRunning()``` : 현재 Running 중인가
* **LifecycleProcessor Interface**
    * **Lifecycle Interface** 상속
    * ```void onRefresh()```
    * ```void onClose()```

## ApplicationContextAware and BeanNameAware
* ApplicationContextAware을 주로 사용
* 해당 Bean을 관리하는 ApplicationContext 객체를 가지고 올 수 있다.
* ```ApplicationContextAware Interface```를 상속
* Example
```java
@Slf4j
public class A implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    void init(){
       log.error(">> "+applicationContext);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
```
