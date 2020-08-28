# InnerClass
* InnerClass Type
    * Instance Inner Class 
    * Static Inner Class
    * Local Inner Class
    * Anonymous Object
* 왜 사용하는가?
    * 해당 Outer(Class, Method)에서만 사용하기 위해서 사용한다.
* Compile 시, Class Name
    * Instance, Static Inner Class : **{OuterClass}${InnerClass}.class**
    * Local Inner Class : **{OuterClass}$1{InnerClass}.class**


## Static Class에 대한 고찰
1. 기본적으로 작성하는 Class(Outer Class)는 Static Class 이다.
    * 그렇기 때문에, 바로 Class 생성이 가능하다(참조 값을 알 수 있다)
    > 메모리에 올라가 있다.
2. Static Class만 바로 접근이 가능하다는 의미이지, Class의 멤버 변수,
멤버 함수에 접근이 가능하다는 의미가 아니다.
3. Inner Class vs Static Inner Class(Static Member Class)
    * Inner Class의 경우
        1) Outer Class의 Instacne에 대한 외부 참조 값을 가진다.
        2) 생성 시: OuterClassInstance.new InnerClass();
    * Static Inner Class의 경우, 
        1) Outer Class의 Instance에 대한 외부 참조 값이 없다(당연하다)
        2) 생성 시: new OuterClass.InnerClass();


## Instance Inner Class
* 구현 위치 : Outer Class 멤버 변수와 동일
* 사용할 수 있는 Outer Class 변수 : Outer Instance 변수, Outer Static 변수


## Static Inner Class
* 구현 위치 : Outer Class 멤버 변수와 동일
* 사용할 수 있는 Outer Class 변수 : Outer Static 변수


## Local Inner Class
* 구현 위치 : Method 내부에 구현
* 사용할 수 있는 Outer Class 변수 : Outer Instance 변수, Outer Static 변수


## Anonymous Object
* 구현 위치 : Method 내부에 구현, 변수에 대입하여 직접 구현
* 사용할 수 있는 Outer Class 변수 : Outer Instance 변수, Outer Static 변수
* 사용하는 이유는 한 번만 사용하는 Class의 경우, 굳이 클래스를 만들 필요없이 바로 코드에 넣을 수 있다.

### Reference
* https://siyoon210.tistory.com/141
