package s02;

/**
 * 인터페이스 (Interface)
 * - 클래스가 사용되는 방식/접점만을 선언하는 클래스의 유사한 틀
 * - 아무런 구현이 되어 있지 않으며, 모든 메소드가 추상 메소드
 *
 *
 * 인터페이스의 특징
 * - class가 아니고 interface 키워드 사용
 * - 맴버 변수는 항상 "public static final"이다. (생략 가능)
 * - 맴버 메소드는 항상 "public abstract"이다. (생략 가능)
 * - 클래스는 하나만 상속할 수 있으나, 인터페이스는 여러 개 가능
 * - 인터페이스의 상속은 implements 키워드 사용
 *
 *
 */
// public, default 가능
interface IFoo {
    public static final int NUMBER_VAR = 10;
    int NUMBER_VAR2 = 20;   // public static final

    public abstract void methodA(int param);
    void methodB(int param);
}

class Foo implements  IFoo{

    @Override
    public void methodA(int param) {
        System.out.println(param);
    }

    @Override
    public void methodB(int param) {
        System.out.println(param);
    }
}

// interface IFoo <- class Foo : 한 마디로 표현하기 힘든 경우, I를 맨 앞에 붙여줌
// interface Printable <- class Bar : 형용사, 부사 형태로 표현, Runnable 등

/**
 * 인터페이스 상속
 * - 인터페이스로 인터페이스를 상속할 경우, extends
 * - 클래스 <- 클래스 상속과 달리 다중 상속 가능 *
 */

interface Walkable {
    void walk();
}

interface  Runnable{
    void run();
}

interface  Jumpalbe extends Walkable, Runnable{
    void jump();
}

class Jumper implements Jumpalbe{

    @Override
    public void walk() {
        System.out.println("walk");
    }

    @Override
    public void run() {
        System.out.println("run");
    }

    @Override
    public void jump() {
        System.out.println("jump");
    }
}

/**
 * JDK 1.8 이후의 인터페이스
 *
 * 1. Default Method
 * - 항상 public 이다.
 * - 인터페이스 철학과 맞지 않다.
 * - 기존에 사용하던 인터페이스가 개선되어, 모든 자식 메소드에 동일하게 구현되어야 하는 메소드가 생긴 경우에
 * 쉽게 기능을 추가하기 위해 만들어짐.
 *
 */

interface IFooTwo {
    void abstractMethod();
    default void defaultMethod(){   // 디폴트 메소드
        System.out.println("Default Method");
    }
}

class FooTwo implements IFooTwo{
    @Override
    public void abstractMethod() {
        System.out.println("Abstract method implemented");
    }

//    @Override   // Overriding not necessary
//    public void defaultMethod() {
//        System.out.println("Override default method");
//    }
}

class SuperFoo{
    public void defaultMethod(){
        System.out.println("Default method in Super class");
    }
}

class FooThree extends SuperFoo implements IFooTwo{
    @Override
    public void abstractMethod() {
        System.out.println("abstract method implemented");
    }

//    @Override
//    public void defaultMethod() {
//        System.out.println("");
//    }
}

/**
 * 인터페이스 static 메소드
 *
 */
interface IBar {
    static void staticMethod(){
        System.out.println("Static Method");
    }
}

class Bar implements IBar{

}

class AFoo {
    public static void aaa(){}
}
class BFoo extends AFoo {

}

public class Interface {
    public static void main(String[] args) {
        FooTwo footwo = new FooTwo();
        footwo.abstractMethod();
        footwo.defaultMethod();

        FooThree foothree = new FooThree();
        foothree.abstractMethod();
        foothree.defaultMethod();   // 부모와 인터페이스에 모두 메소드가 있는 경우, 부모 클래스의 메소드들을 실행한다.

        IBar.staticMethod();    // 인터페이스 명으로 클래스 메소드 호출 가능
        // Bar.staticMethod(); // 구현체인 자식 클래스로는 클래스 메소드 호출 불가능
    }
}


// Heap 메모리 중에
// Young memory (nursery) - ex> String = new String("abcd")
// Old memory - String pool(Heap 영역에 있다) ex> String s = "abc"