# 제네릭 (Generic)
* 대상 객체의 타입을 입력 받아서 사용하는 형식
* 미리 사용할 수 있는 타입을 명시해서 **컴파일 타임**에 차크 가능
    * 입력을 Object로 할 수 있으나, 런타임에 Instanceof로 객체를 체크해야 함
    * 제네릭을 사용할 경우 이러한 과정 없이 간견하게 코드 작성을 할 수 있다.
    > ex) Object를 어떤 값으로 Casting 해야될지는 런타임에 입력 값에 의해 결정되는 경우, 입력을 어떤 식으로 할지 모르기 때문에 비효율적 코드를 작성하게 된다.

## 제네릭 클래스 (Generic Class)

* 클래스 선언 시, 설정되지 않은 타입을 타입 파라미터로 표현
    ```java
    class GenericFoo<T> {   // T: 타입 파라미터
        String name;
        T memberVar;
    
        GenericFoo(String name, T memberVar){
            this.name = name;
            this.memberVar = memberVar;
        }
    }    
    ```

* 제네릭 인터페이스도 제네릭 클래스와 동일하게 표현
    ```java
    interface GenericInterface<T> { 
          ...
    }
    ```
 
* 여러 개의 타입 파라미터를 사용할 수 있다.
    ```java
    class HashMap<K, V> {
          ...
    }
    ```

* 제네릭 클래스와 static 관계
    ```java
    class GenericBar<T> {
        // static T classVar;             // not possible, 클래스 영역이기 때문에, 객체 생성 이전에 메모리에 상주해야 되므로
        // static void method(T var){ }   // not possible, T가 객체를 생성해야 정해지므로, 호출이 불가능하다

        // T memberVar = new T();   // not possible
        // 안정성 문제 때문에(T가 어떤 것이 올지 모름) 위험하다.
        // new 연산자의 경우, 컴파일 단계에서 타입을 이용해서 Heap 영역에 충분한 공간이 있는지 확인을 하게 되늗데
        // 여기서는 그렇게 하지 못하기 때문에 에러가 발생한다.
        // Reference: https://yaboong.github.io/java/2019/01/19/java-generics-1/
        /*
        {
            Object obj = new Object();
            if(obj instanceof T){   // not Possible, T가 될 수 있는 값이 너무 많기 때문에
    
            }
        }
        */
    }    
    ```
## 제네릭 상속 (Generic Inheritance)
* 
    ```java
    class GFoo<T> {
          ...
    }
    interface IGFoo<D>{
          ...
    }  
    // 추가적인 타입 파라미터도 사용할 수 있다.
    class GIGFoo<N, T, D> extends GFoo<T> implements IGFoo<D>{
          ...
    }
    // 부모 클래스, 인터페이스들에 동일한 타입 파라미터를 넘겨줄 수 있다.
    class IGIFooTwo<T> extends GFoo<T> implements IGFoo<T>{
          ...
    }
    ```

## 제네릭 메소드 (Generic Method)
* 메소드 앞에 타입 파라미터를 써준다.
* 제네릭 클래스와 다르게 static generic method가 가능한 이유는 함수 호출 시에는 타입이 결정 되기 때문이다.
    ```java
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

    ```

## 타입 제한
* **extends** 키워드를 사용하여 부모 클래스를(상한) 제한
* **&** 를 이용하여 동시 제약을 할 수 있다.
    ```java
    // 타입 제한을 하지 않으면 extends Object와 동일하다.
    class GenericNoTypeLimit<T extends Object>{}
    
    // 제한한 클래스의 상위 클래스를 사용할 수 없다. Number와 하위 클래스
    class GenericTypeLimitation<T extends Number & Cloneable>{
    
    }
    ```

* 와일드 카드
    * **<?> == <? extends Object>** 서로 동일 
    * **<? extends T>** : 와일드 카드의 상한을 제한
    * **<? super T>** : 와일드 카드의 하한을 제한 
    ```java
    class WildFoo {
          ...    
    }
    
    class WildBar extends WildFoo{
          ...
    }
    
    class WildGeneric<T> {
              
    }
    
    class WildCar {
        public <T extends Object> void method1_2(WildGeneric<T> x) {}   // T와 ? 차이는 super 쓰냐 못 쓰냐이다.
        public void method1(WildGeneric<?> x) {}
        public void method1_eq(WildGeneric<? extends Object> x) {}      // Object가 상한 -> 모든 객체
        public void method2(WildGeneric<? extends WildFoo> x) {}        // WildFoo, WildBar
        public void method3(WildGeneric<? super WildBar> x) {}          // Object, WildFoo, WildBar
    }
    ```
                                                                                                                        