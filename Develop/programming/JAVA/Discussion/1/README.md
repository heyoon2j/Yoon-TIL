# Java Discussion
* 대주제 : 객체지향 프로그래밍(OOP, Object Oriented Programming)
* 소수제 : 클래스(Class), 추상클래스(Abstract Class), 인터페이스(Interface)의 용도와 클래스 간의 관계


## 용도
### 클래스(Class)
* 연관이 있는 속성(Variable)와 동작(Function)들을 하나로 묶어 사용하기 위해서 사용된다.


### 추상클래스(Abstract Class)
* 해당 추상 클래스의 상태와 동작들을 다른 클래스에게 상속해주기 위해 사용.
* 기본 클래스와 다른 점은 특정 동작을 상속받는 클래스에 구현을 강제할 수 있다.
> 그래서 왜 기본 클래스 말고 추상 클래스를 상속받게 할까?
>> 다형성을 표현할 때 상위 클래스에서 굳이 필요없는 Function을 구현할 필요는 없는 경우, 이를 하위 클래스에 구현을 강제할 수 있어서(구현하지 않을 시, 오류 발생 시킴)


### 인터페이스(Interface)
* 동작(function)을 상속받는 클래스에 구현을 강제하기 위해서 사용된다.
	* 생성자(Constructor)가 없다.
	* 다중 상속을 할 수 있다.
> 동작(function)에 대한 구현만을 위해서라면 인터페이스를 사용하는 것이 맞다.



## 관계
### 1. 다중상속은 왜 막을까?
* 다중상속의 모호성 때문에(C++에서는 해결방법이 존재하긴 함)
1) 상속하는 상위 클래스들에 같은 이름, 같은 Parameter의 함수를 가지고 있는 경우, 어떤 함수를 호출해야 될지 모른다.
	> namespace를 써서 해결
2) 상속하는 상위 클래스들이 같은 최상위 클래스를 상속하는 경우, 최상위 클래스 생성자를 각 각 호출 한다.
	> C++ 에서는 virtual(similar abstract) 상속을 통해 해결
 

### 2. Java에서 Object로 static method를 호출 할 수있는 이유는 무엇인가?
* 먼저 Data Type을 확인하게 되고 static method인 경우, class에 종속되어 있기 때문에(메모리적으로) 바로 호출
    * 객체가 null이더라도, static method를 호출할 수 있다.
    
* 다른 의견 1) 컴파일러가 똑똑하여,객체를 클래스로 바꾸어 static method를 호출
    * 컴파일러가 함수 확인 -> 함수가 static이면 -> 객체를 클래스로 변경해서 실행
    

* Reference
	* https://www.quora.com/Why-can-we-call-the-static-method-by-an-object-in-Java
	* https://stackoverflow.com/questions/4978000/static-method-in-java-can-be-accessed-using-object-instance
	* https://javabypatel.blogspot.com/2017/05/can-static-method-be-called-using-object.html


### 3. static method는 상속되나?
* static method는 상속된다(can)
    * Class에서는 상속이되고, Interface에서는 상속이 되지 않는다(다중 상속 때문에)

* 다른 의견 1) static method는 상속되지 않는다(can't)
	* 하위 클래스에서 Overriding이 되지 않는다.
	* 하위 클래스로 상위 클래스의 static method를 호출은 가능하다.
	> Data Type을 기준으로 확인하기 때문인 거 같다.

* Reference
	* https://stackoverflow.com/questions/10291949/are-static-methods-inherited-in-java


### 4. Interface는 상속의 개념인가? 구현의 개념인가?
* 표현의 방법 차이인거 같다. 기본적으로 상속이지만 구현이라고 생각한다.
    * abstract function을 사용하는 Abstact Class는 생성자가 존재하지만, Interface는 존재하지 않는다.
    * Function들이 Overriding(상속 기술)되어 Virtual Table 생성, 하지만 Virutal Table은 빈 슬롯(Null Pointer)으로 저장
    > 결과적으로 상속기술을 사용하지만, 하위 클래스가 인터페이스로 부터 받는 데이터(빈 슬롯)가 없다. 그렇기 때문에 구현이라고 생각

* Reference
	* https://stackoverflow.com/questions/10291949/are-static-methods-inherited-in-java // Answer : Java Tutorial
	* https://stackoverflow.com/questions/7636230/c-interview-vtable-for-a-class-with-a-pure-virtual-function


### 5. 왜 Interface를 구현하는 하위 Class에서는 Interface의 static method를 호출하지 못하나?
* 다중 상속 때문이다.
    * 기본 method의 경우, abstract로 Null Point가 전달되기 때문에 다중 상속이 가능하다.
    * 하지만 여러 개의 상위 인터페이스에서 같은 static method name으로 정의되어 있는 경우, 하위 클래스, 인터페이스에서 호출해야될지 모른다. 그렇기 때문에 막아 놓은 것으로 보인다.
    > 그래서 JDK 1.8 버전 이후, static method, default method가 넘어오면서 말이 많았던 거 인듯.

* Reference
	* https://stackoverflow.com/questions/31307723/why-an-entry-of-pure-virtual-function-in-virtual-table-is-required
