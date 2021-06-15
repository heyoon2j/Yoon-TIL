# Component
* 

## 



### Code
1. Controller



### Component Scan
* xml 태그 추가
    ```xml
    <beans ...
      xmlns:context="http://www.springframework.org/schema/context"
      xsi:schemaLocation=" ...
      http://www.springframework.org/schema/context 
      http://www.springframework.org/schema/context/spring-context-4.2.xsd">
  
      <context:component-scan base-package="com.example.web" />  
    </beans>
    ```
    * ```context:component-scan```: Component를 스캔하여 Bean 객체 생성
        * 해당 tag를 사용하기 위해서는 xmlns:context와 xsi:schemaLocation을 추가한다.
    
### Component Annotation
1. ```@Component``` 

 
2. ```@Service```
    * Naming은 **XXXServiceImpl** 형태로 짓는다.
    * 비즈니스 로직을 처리하는 Service 클래스

3. ```@Repository```
    * Naming은 **XXXDAO** 형태로 짓는다.
    * DB 연동을 처리하는 DAO 클래스
    
4. ```@Controller```
    * Naming은 **XXXController** 형태로 짓는다.
    * 사용자 요청을 제어하는 Controller 클래스

5. ```Configuration```
    * Naming은 ```XXXConfig``` 형태로 짓는다.
    * XML에 대신 Bean 등 설정

@RequestMapping: 
    @RequestMapping(path="/insertBoard.do", method=Request.GET)

@RequestParam: 요청 값명과 인자 값명이 다를 때??????
    @RequestParam(value='seq', defaultValue="1", required=true)

```
@RequestMapping()
public String insertBoardView(BoardVO vo) {
    // 매개변수로 받은 VO 객체는 자동으로 requesst 내장 객체에 등록된다.
    // 따라서 최종적으로 실행되는 화면(JSP)에서 EL을 이용하여 값을 뿌릴 수 있다.
    
}

```


