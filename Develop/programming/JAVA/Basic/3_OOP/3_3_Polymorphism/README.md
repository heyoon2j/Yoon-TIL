# 다형성 (Polymorphism)
* 오버로딩에 의한 다형성
* 오버라이딩에 의한 다형성
    * 상속에 의한 다형성
    
## 다형성 특징
* 부모 클래스 타입으로 자식 클래스 타입의 객체를 참조하는 특징
    * 부모 클래스 타입으로 자식 클래스(메소드, 멤버 변수)에 접근할 수 없다.
    * 자식 클래스로 부모 클래스를 참조하려 하면 오류 발생
```java
class Foo{
    public void methodA(){
    }
}

class Bar extends Foo{
    public void methodB(){
    }
}

public class Polymorphism {
    public static void main(String[] args) {
        Bar bar = new Bar();    // 자식 객체를 생성
        Foo foo = (Foo)bar;     // 부모 클래스 타입으로 자식 객체를 받음

        foo.methodA();
        // foo.methodB();   // 자식 클래스 메소드는 사용 불가능

        Foo foo1 = new Foo();
        // Bar bar1 = (Bar)foo1;   // 자식 클래스 타입으로 부모 객체를 받음
        // 문법 오류는 아니지만, 런타임 오류가 발생
        // bar1.methodA();     // 문법 오류 x
        // bar1.methodB();     // 문법 오류 x

        // 힙 영역에는 계속 자식 객체가 있었던 것
        Bar bar1 = (Bar)foo;    // 자식 클래스 타입으로 자식 객체를 받음
        bar1.methodA();
        bar1.methodB();
    }
}
```

* 맴버 변수의 재정의는 선언된 객체의 타입을 따른다(문법적으로 본다)
* 메소드 오버라이딩은 메모리상의 객체 타입을 따른다(실체 객체를 본다)
    * 가상 메소드 호출: Virtual method call
```java
class Foo{
    public String x = "Super";

    public void methodA(){
        System.out.println("Super method");
    }
}

class Bar extends  Foo{
    public String x = "Sub";

    @Override
    public void methodA() {
        System.out.println("Sub method");
    }
}

public class poly02 {
    public static void main(String[] args) {
        Bar bar = new Bar();
        Foo foo = (Foo) bar;

        System.out.println(bar.x);  //Sub
        bar.methodA();  // Sub Method

        System.out.println(foo.x);  // Super
        foo.methodA();  // Sub Method, Virtual Method Call
    }
}
```
* 공변 반환 타입(Covariant return type)
    * 자기 자신을 반환하는 경우, 오버라이딩도 리턴 타입을 바꿀 수 있다.
```java
class Foo {
    public Foo getInstance(){
        return this;
    }
}

class Bar extends  Foo{
    @Override
    public Bar getInstance() {  // 오버라이딩이지만, 리턴 타입이 달라질 수 있다.
        return this;
    }
}
```