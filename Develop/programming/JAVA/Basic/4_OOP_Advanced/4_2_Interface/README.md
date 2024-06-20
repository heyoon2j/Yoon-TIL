# 인터페이스 (Interface)
* 클래스가 사용되는 방식 / 접점만을 선언하는 클래스의 유사한 틀
* 아무런 구현이 되어 있지 않으며, 모든 메소드가 추상 메소드이다.


## 인터페이스 특징
* ```class```가 아니고 ```interface``` 키워드 사용
* 맴버 변수는 항상 ```public static final``` 이다(생략 가능)
* 맴버 메소드는 항상 ```public abstract``` 이다(생략 가능)
* 클래스는 하나만 상속할 수 있으나, 인터페이스는 여러 개 상속 가능

### 인터페이스 변수명 작성법
* __interface IFoo <- class Foo__ : 한 마디로 표현하기 힘든 경우, I를 맨 앞에 붙여준다.
* __interface Printable <- class Bar__ : 형용사 형태로 표현, Runnable 등

##  인터페이스 상속
* 인터페이스로 인터페이스를 상속할 경우, ```extends```
* 클래스 <- 클래스 상속과 다르게 다중 상속 가능
```java
interface Walkable {
    void walk();
}

interface  Runnable{
    void run();
}

interface  Jumpalbe extends Walkable, Runnable{
    void jump();
}
```

## JDK 1.8 이후
### Default Method
* 항상 public 이다.
* 인터페이스 철학과 맞지 않다.
* 기존에 사용하던 인터페이스가 개선되어, 모든 자식 메소드에 동일하게 구현되어야 하는 메소드가 생긴 경우에 쉽게 기능을 추가하기 위해 만들어짐
* 부모와 인터페이스 모두에 같은 이름의 메소드가 있는 경우, 상속 시 부모 클래스의 메소드를 실행한다.
```java
interface IFooTwo {
    void abstractMethod();
    default void defaultMethod(){   // 디폴트 메소드, 상속 받는 클래스에서 Overriding 할 수 있다.
        System.out.println("Default Method");
    }
}
```
### Static Method
* 인터페이스 이름으로 호출 가능
* 클래스 구현체의 이름으로도 호출 불가능
```java
interface IBar {
    static void staticMethod(){
        System.out.println("Static Method");
    }
}

class Bar implements IBar{

}

public class Interface {
    public static void main(String[] args) {
        IBar.staticMethod();    // 인터페이스 명으로 클래스 메소드 호출 가능
        // Bar.staticMethod(); // 구현체인 자식 클래스로는 클래스 메소드 호출 불가능
    }
}
```
