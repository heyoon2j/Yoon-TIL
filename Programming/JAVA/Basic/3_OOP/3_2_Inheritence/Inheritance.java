/**
 * 상속 (Inheritance)
 * 상속: 어떤 클래스의 모든 맴버변수 및 메소드를 계승하여, 새로운 클래스를 생성하는 것
 *
 * 상속 대상: 추상 클래스, 부모 클래스, 상위 클래스, 슈퍼 클래스
 * 상속 결과: 자손 클래스, 자식 클래스, 하위 클래스, 서브 클래스
 *
 * 상속 관계를 흔히 'IS-A' 관계라고 부른다.
 *
 */
/*
class Person{
    String name;

    public void work(){
        System.out.println("일하기");
    }

    public void sleep(){
        System.out.println("잠자기");
    }
}

// Person을 상속하는 자식 클래스
class Developer extends Person{
    String mainLang;

    public void writeCode(){
        System.out.println("코딩하기");
    }
}

class Student extends Person{

    public void writeCode(){
        System.out.println("열심히 코딩하기");
    }
}

public class Inheritance {
    public static void main(String[] args) {
        // 클래스를 상속하면, 모든 맴버변수와 모든 메소드를 상속 받는다.
        Developer dev = new Developer();    // Develop 이지만 Person 이기도 하다.
        dev.name = "나개발";                 // Develop 'IS-A' Person
        System.out.println("나개발");
        dev.work();
        dev.sleep();

        dev.mainLang = "Java";
        dev.writeCode();

        Student stud = new Student();       // Student 'IS-A' Person
        stud.writeCode();


    }
}
*/