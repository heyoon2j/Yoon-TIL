# 추상 클래스 (Abstract Class)
* 일부 메소드가 구현되지 않고, 선언만 되어있는 클래스
    * 자식 클래스에서 구현되지 않은 메소드를 반드시 구현하도록 강제하는 것
    * 필요한 모든 클래스가 구현 하도록 하기 때문에 안정성을 높임
* 추상 클래스는 객체를 생성할 수 없다.
    * 그렇기 때문에 구현된 자식 클래스 객체를 받아야 한다.
```java
abstract class AbstractFoo{
    int x, y;

    public void method(){
        System.out.println("method");
    }
    public abstract void abstractMethod();  // 세미콜론을 잊지말아야 한다
                                            // 선언만 하고 구현하지 않음
}

class Foo extends AbstractFoo{

    @Override
    public void abstractMethod() {
        System.out.println("implemented abstractMethod");
    }
}
```