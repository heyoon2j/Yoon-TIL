package p01;
/**
 * 다형성 (Polymorphism)
 * - 오버로딩에 의한 다형성
 * - 오버라이딩에 의한 다형성
 *      -> 상속에 의한 다형성
 */

/**
 * 부모 클래스 타입으로 자식 클래스 타입의 객체를 참조하는 특징
 */

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
        Foo foo = (Foo)bar; // 부모 클래스 타입으로 자식 객체를 받음

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
