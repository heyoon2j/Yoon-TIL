/**
 * 생성자 (Constructor)
 * 클래스에서 인스턴스를 생성할 때 사용하는 메소드
 * new 키워드를 사용할 때 호출되는 메소드
 *
 * 기본 생성자 (Default Constructor)
 * 파라미터 생성자 (Parameter Constructor)
 *  -> 여러 개의 생성자를 오버로딩 할 수 있음
 *
 */
public class Constructor {
    int x;
    int y;
    String z;

//    private Constructor(){}   // Singleton Pattern에서 사용된다.

    public Constructor(){
        this(0,0,null);
//        x = 1;
//        y = 2;
//        z = "초기화";
    }
    Constructor(int x, int y, String z){
        this.x = x;         // this는 객체 자신을 나타낸다.
        this.y = y;
        this.z = z;
    }
    Constructor(int x, int y){
        this(x, y, ""); // this 생성자, this는 무조건 첫 줄에 쓰일 수 있다.
//        this.x = x;
//        this.y = y;
//        this.z = "";
    }
}

class ConstructorTest {
    public static void main(String[] args){
        Constructor c = new Constructor();  // 기본 생성자를 호출
        System.out.println(c.x + "," + c.y + "," + c.z);
        // z의 경우, 클래스이기 때문에 null로 초기화 된다.
        // null -> 아무것도 참조하고 있지 않다는 의미

        Constructor c1 = new Constructor(10, 20, "파라미터 생성자");
        System.out.println(c1.x + "," + c1.y + "," + c1.z);

        Constructor c2 = new Constructor(30, 40);
        System.out.println(c2.x + "," + c2.y + "," + c2.z);
    }

}
