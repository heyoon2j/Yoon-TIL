package com.company;

/**
 * 메소드 (Methods)
 * 객체가 하는 동작을 정의하는 작업을 수행하는 코드의 집합, 나열
 * 코드의 중복을 방지, 유지보수성을 향상, 코드의 가독성 개선
 *
 */

class Foo {
    int value;
}

class Bar {
    // return type (출력의 자료형)
    public int add(int x, int y){   // 입력 파라미터
        return x + y;   // 반환값 (Return Value)
    }
    // 선언(Declaration) - 한 것이 있다. 실제 구현은 x
    // 정의(Definition) - 선언 + 구현(초기화)

    // 정적 메소드(static method), 클래스 메소드(class method)
    // void 리턴 타입은 아무 것도 반환하지 않는다.
    public static void classMethod(){
        System.out.println("클래스 메소드 호출");
    }

    static public void swapPrimitive(int x, int y){
        // int x, int y 의 사본이 넘어온다.
        // 기본형 타입(Primitive Type)의 경우에 해당
        // call by value: 메소드를 호출할 때, 값을 복사해서 넘긴다.
        int temp = x;
        x = y;
        y = temp;
    }

    static public void swapReference(Foo x, Foo y){
        // Foo -> Class, class는 참조형 변수
        // 참조형 타입(Reference Type)
        int temp = x.value;
        x.value = y.value;
        y.value = temp;
    }

    // ... 키워드는 여러 개를 받는다는 의미
    // 입력 받은 결과는 배열로 주어진다.
    // 가변 인자 (Variable arguments)
    public static int sumAll(int... params){
        int sum = 0;
        for(int value : params){
            sum +=value;
        }
        return sum;
    }
    // 메소드 오버로딩 Overloading
    // 함수명은 같고, 입력 인자가 달라야 한다.
    // 입력 인자의 개수도 다를 수 있다.
    public static float sumAll(float... params){
        float sum = 0;
        for(float value : params)
            sum += value;
        return sum;
    }
}

class Person {
    boolean isHungry = true;

    // 클래스 메소드
    public static void describe(){
        System.out.println("사람입니다.");
        // 클래스 메소드는 클래스 변수를 사용할 수 있다.
        // 단, 객체에 속하는 속성을 사용할 수 없다.
    }

    // 인스턴스 메소드
    public void eat(){
        isHungry = false;
    }
}

public class Methods {
    public static void classMethod(){
        System.out.println("클래스 메소드 호출");
    }

    public void instanceMethod(){
        System.out.println("인스턴스 메소드 호출");
    }

    public static void main(String[] args){
        Bar.classMethod();
        // Bar.add(1, 2);
        Bar bar = new Bar();
        System.out.println(bar.add(1, 2));

        Person p1 = new Person();
        Person p2 = new Person();

        // 인스턴스 메소드는 객체의 속성을 변화시킨다.
        p1.eat();
        System.out.println(p1.isHungry);
        System.out.println(p2.isHungry);

        // 클래스 메소드는 객체의 속서오가 상관이 없다.
        Person.describe();

        int x = 10;
        int y = 20;
        Bar.swapPrimitive(10, 20);
        System.out.println(x+", "+y);

        Foo xF = new Foo();
        Foo yF = new Foo();
        xF.value = 10;
        yF.value = 20;
        Bar.swapReference(xF, yF);
        System.out.println(xF.value+", "+yF.value);

        System.out.println(Bar.sumAll(2, 5, 10, 6));

        classMethod();  // 동일 클래스에 속한 클래스 메소드는 사용 가능
        Methods.classMethod();
        Methods m = new Methods();
        m.instanceMethod();
    }
}
