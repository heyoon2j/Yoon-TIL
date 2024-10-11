/**
 * super 키워드
 * this가 자기 자신의 객체를 참조하듯,
 * super는 부모 객체를 참조한다.
 */

class Foo {
    String x = "Foo";

    public Foo(String x) {
        this.x = x;
    }
}

class Jaemi {       // == class Jaemi extends Object 와 동일
    public void method(){

    }
}


class Bar extends Foo {
    String x = "Bar";

    // 부모클래스에 기본 생성자를 사용하는 경우에는 호출 안 해주어도 된다(자동 처리)
    // 부모클래스에 파라미터 생성자가 있으면 호출해 주어야 한다.
    public Bar(String x, String x1) {
        super(x);   // this와 마찬가지로 첫줄에 써야 합니다. 부모클래스 생성자 호출
        this.x = x1;
    }

    public void method(){
        //String x = "method";
        System.out.println(x);  // 로컬 변수 -> 맴버 변수 -> 부모의 맴버 변수
        System.out.println(this.x); // 자기 자신의 객체에 접근 가능
        System.out.println(super.x);    // 부모 객체에 접근 가능
    }
}

public class Super {
    public static void main(String[] args) {
        Bar bar = new Bar("", "");
        bar.method();
        // 자식 객체를 생성하면, 부모 객체를 먼저 생성하고 자식 객체를 생성한다.
        // 자식 객체가 삭제되면, 자식 객체가 먼저 삭제되고 부모 객체를 삭제한다.
    }
}
