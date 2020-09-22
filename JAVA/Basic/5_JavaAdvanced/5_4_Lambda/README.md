# Lambda
* Java에서 함수형 프로그래밍(Functional Programming)을 구현하는 방식
* 클래스를 생성하지 않고 함수의 호출만으로 기능을 수행
* 함수형 인터페이스를 선언함
* 병렬처리가 가능함
* Java 8부터 지원되는 기능

## Funcational Programming
* 순수 함수(Pure Function)를 구현하고 호출
* 매개 변수만을 사용하도록 만든 함수로 외부 자료에 부수적인 영향(Side Effect)이 발생하지 않도록 한다.
* 입력받은 자료를 기반으로 수행되고 외부에 영향을 미치지 않기 때문에, 병렬처리 등이 가능하고 안정적으로 확장이 가능한 프로그래밍 방식이다.

## Lambda 사용 가능한 경우
* Lambda 식은 하나의 Method를 정의하기 때문에, 하나의 Abstract Method가 선언된 Interface만이
Lambda 식의 타겟 타입이 될 수 있다.
* Lambda를 위한 Interface를 작성하는 경우, Annotation으로 **@FunctionalInterface**를 써주면 Method를 하나만 작성하도록 체크해 준다. 
* Lambda 식의 내부적인 동작은 Anonymous Object 동작이다.

## Lambda 문법
* 기본적인 형태
    * (타입 매개 변수, ...) -> { 실행문 }
    * ```(x, y) -> { return x + y }```
    
1. 매개 변수가 하나인 경우 괄호 생략 가능
    * ```str -> { System.out.println(str); }```
    
2. 중괄호 안의 구현부가 한 문장인 경우 중괄호 생략
    * ```str -> System.out.println(str);```

3. 중괄호 안의 구현부가 한 문장이더라도 return문은 중괄호를 생략할 수 없다.
    * ```str -> return str.length(); // Error```

4. 중괄호 안의 구현부가 반환문 하나라면 return과 중괄호를 모두 생략할 수 있다.
    * ```str -> str.length()```


## Use function like variable
* Lambda를 이용하면, 함수를 변수처럼 사용할 수 있다.


### Reference
* FAST_CAMPUS 박은종 강사