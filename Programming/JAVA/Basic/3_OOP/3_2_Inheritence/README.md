# 상속 (Inheritance)
* 클래스를 상속하게 되면 모든 맴버면수 및 메소드를 계승하여, 새로운 클래스를 생성하는 것
* 상속 대상: 추상 클래스, 부모 클래스, 상위 클래스, 슈퍼 클래스
* 상속 결과: 자손 클래스, 자식 클래스, 하위 클래스, 서브 클래스
* 상속 관계를 흔히 'IS-A' 관계라고 부른다.
* **extends** 키워드를 사용
```$xslt
// 부모 클래스
class Person{
    ...
}

// 자식 클래스{
class Developer extends Person{
    ...
}
```

## 포함 관계 (Class Composition)
* 'HAS-A' 관계
* 하나의 클래스가 다른 클래스의 객체를 보유하는 관계
```
class MainMachine{
    ...
}

class Developer{
    MainMachine mainMachine;
    ...
}
```

## 메소드 재정의(Method Override)
* Override -> 덮어 씌운다, 해킹해서 뭔가 달라지게 하다.
* 다형성 (Polymorphism)의 근간이 된다.
* 어노테이션 **@Override**를 관례적으로 붙여준다, 문법적 체크 및 가독성을 위해서
```$xslt
class Person{
    public void writeCode() {
        System.out.println("아무 코드나 일단 적어 보았다.");
    }
}

class Student extends Person{
    @Override
    public void writeCode() {
        System.out.println("능숙하게 코드를 작성해 보았다.");
        System.out.println("답은 틀렸다.");
        System.out.println("하지만 무언가 또 배웠다.");
    }
}

class Developer extends  Person{
    @Override
    public void writeCode() {
        System.out.println("능숙하게 코드를 작성했다.");
    }
}
```

## 다형성 (Polymorphism)
* Method Override를 이용하여 하나의 클래스로 여러 동작을 표현할 수 있게 한다.
```$xslt
public class MethodOverride {
    public static void main(String[] args) {
        Student stud = new Student();
        Developer dev = new Developer();

        // 부모 클래스 자료형으로 자식 클래스를 받을 수 있다.
        Person p = (Person)dev;
        // Developer의 writeCode() 실행, 부모 클래스 자료형이지만 오버라이드된 메소드가 동작한다.
        p.writeCode();

        // Student의 writeCode() 실행
        p = (Person)stud;
        p.writeCode();
    }
}
```

## super 키워드
* this가 자기 자신의 객체를 참조하듯, super는 부모 객체를 참조한다.
```$xslt
class Foo{
    int x;
    Foo(int x){
        this.x = x;
    }
}

class Bar extends Foo{
    int y;
    Bar(int x, int y){
        super(x);
        this.y = y;
    }
}
```

## 접근 지시자 (Access Modifier)
* private - 클래스 안에서만 사용 가능
* default - 같은 패키지 내에서 사용 가능
* protected - 같은 패키지 또는 다른 패키지에 속한 자식 클래스에서 사용 가능
* public - 전체에서 사용 가능
* Class는 private와 public만 가능하다.

```$xslt
package PackageA;

public class ClassA {
    public int x;
    protected int y;
    int z;  // default (=package)
    private int w;

    public void methodA(){

    }
    protected void methodB(){}
    void methodC() {}   // default (=package)
    private void  methodD(){}   // 내부 구현을 위해서만 쓰인다.

    public void methodTest(){
        System.out.println(x);
        System.out.println(y);
        System.out.println(z);
        System.out.println(w);

        methodA();
        methodB();
        methodC();
        methodD();
    }
}

class ClassTest{
    public static void main(String[] args) {
        ClassA obj = new ClassA();
        System.out.println(obj.x);  // public
        System.out.println(obj.y);  // protected
        System.out.println(obj.z);  // default
//        System.out.println(obj.w);// private

        obj.methodA();  // public
        obj.methodB();  // protected
        obj.methodC();  // default
//        obj.methodD();// private
    }
}
```
* 다른 패키지
```$xslt
import PackageA.ClassA;

class AA extends ClassA {

    public void methodTest(){
        System.out.println(x);  // public
        System.out.println(y);  // protected, 자식이면 다른패키지여도 가능하다.
//        System.out.println(z);  // default
//        System.out.println(w);  // private

        methodA();  // public
        methodB();  // protected
//        methodC();  // default
//        methodD();  // private
    }
}

public class ClassB {
    public static void main(String[] args) {
        ClassA obj = new ClassA();
        System.out.println(obj.x);  // public
//        System.out.println(obj.y);    // protected는 다른 패키지인 경우 자식만 된다.
//        System.out.println(obj.z);    // default는 다른 패키지면 안된다.
//        System.out.println(obj.w);    // private

        obj.methodA();
//        obj.methodB();    // protected는 다른 패키지인 경우 자식만 된다.
//        obj.methodC();    // default는 다른 패지면 안된다.
//        obj.methodD();    // private
    }
}
```
