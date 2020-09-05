# Bean Scopes
* Bean의 객체 생성에 대한 범위를 지정
* ```scope``` 키워드를 이용하여 설정 가능

## singleton
* 하나의 객체만 생성(Singleton Pattern 확인)
* Bean의 Scope는 Default가 singleton 이다.

## prototype
* IoC를 호출할 때, 매번 새로운 객체를 생성
* ```ref```를 이용하여 참조할 때도 새로운 객체 생성
* Example
```xml
<bean id="A" class="kr.co.fastcampus.cli.A" scope="prototype">
```

## 그 외
* **request** 
* **session**
* **application**
* **websocket**
