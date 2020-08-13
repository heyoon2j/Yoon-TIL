package s04;

/**
 * 제네릭 (Generics)
 * - 대상 객체의 타입을 입력 받아서 사용하는 형식
 * - 미리 사용할 수 있는 타입을 명시해서 컴파일 타임에 체크 가능
 *  - 입력을 Object로 할 수 있으나, 런타입에 Instaceof로 객체를 체크해야 함
 *  - 제네릭을 사용할 경우 이러한 과정 없이 간결하게 코드 작성을 할 수 있다.
 *
 *
 */

// 일반 클래스
class Foo {

}

// 제네릭 클래스
// 클래스를 선언할 때에는 설정되지 않은 타입이 있으며, 이것을 타입 파라미터로 표현
class GenericFoo<T> {   // T: 타입 파라미터
    String name;
    T memberVar;

    GenericFoo(String name, T memberVar){
        this.name = name;
        this.memberVar = memberVar;
    }


}
// 제네릭 인터페이스
interface GenericInterface<T> {


}
// 여러 개의 타입 파라미터도 쓸 수 있다.
class HashMap<K, V> {

}

class GenericBar<T> {
    // static T classVar; // not possible, 클래스 영역이기 때문에 이미 메모리에 상주(컴파일 단계에서)
    // static void method(T var){ } // not possible, T를 설정하지 않아도 호출할 수 있다라고 생각하지만, 실질적으로 객체와 연결되어 있어서 안됨(메모리에 상주)


    // not possible, 안정성 문제 때문에(T가 어떤 것이 올지 모름) 즉 위험하다.
    // T memberVar = new T();   // not possible
    // new 연산자의 경우, 컴파일 단계에서 타입을 이용해서 Heap 영역에 충분한 공간이 있는지 확인을 하게 되늗데
    // 여기서는 그렇게 하지 못하기 때문에 에러가 발생한다.
    // https://yaboong.github.io/java/2019/01/19/java-generics-1/
    /*
    {
        Object obj = new Object();
        if(obj instanceof T){   // not Possible

        }
    }
    */
}

// 제네릭 타입의 상속
class GFoo<T> {

}
interface IGFoo<D>{

}

// 타입 파라미터
// 추가적인 타입 파라미터도 사용할 수 있다.
class GIGFoo<N, T, D> extends GFoo<T> implements IGFoo<D>{

}
// 부모 클래스, 인터페이스들에 동일한 타입 파라미터를 넘겨줄 수 있다.
class IGIFooTwo<T> extends GFoo<T> implements IGFoo<T>{

}

// 타입 제한
// 타입 제한을 하지 않으면 extends Object와 동일하다.
class GenericNoTypeLimit<T extends Object>{}

// extends를 이용해서 부모 클래스를 제한할 수 있다.
// & 를 이용하여 동시 제약도 가능
class GenericTypeLimitation<T extends Number & Cloneable>{

}

// 제네릭 메소드
class GenericMethod<P> {
    public static <T> T staticMethod(T t){
        return t;
    }

    public int method(int x){
        return x;
    }

    public <T> T method(T x){
        return x;
    }
}


// 와일드 카드
class WildFoo {


}

class WildBar extends WildFoo{

}

class WildGeneric<T> {

}

// ? : 미리 정하지 않아서 아무거나 다 들어 올 수 있음
// 제네릭의 타입 변수를 결정하지 않거나 제한할 수 있다.
class WildCar {
//    public <T super Object> void method1_2(WildGeneric<T> x) {}   // T와 ? 차이는 super 쓰냐 못 쓰냐.
    public void method1(WildGeneric<?> x) {}
    public void method1_eq(WildGeneric<? extends Object> x) {}    // Object가 상한 -> 모든 객체
    public void method2(WildGeneric<? extends WildFoo> x) {}    // WildFoo, WildBar
    public void method3(WildGeneric<? super WildBar> x) {}  // Object, WildFoo, WildBar
}



public class Generics {
    public static void main(String[] args) {
        GenericFoo<String> foo = new GenericFoo<String>("Nunnu","Nanna");

        System.out.println(foo.name);
        System.out.println(foo.memberVar);

        GenericFoo<Integer> foo2 = new GenericFoo<Integer>("Yang2", 22);
        System.out.println(foo2.name);
        System.out.println(foo2.memberVar);

        // 동적 바인딩 -> 컴파일타임에는 동작이 완전히 정의가 되지 않음
        // 런타임에 결정된다.
        GenericMethod.staticMethod("qwe");
    }
}
