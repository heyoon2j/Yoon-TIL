/**
 * java.lang 패키지
 * - Java에서 가장 기본적이며, 자주 사용되는 클래스를 모은 패키지
 * - 별로로 import를 하지 않아도 사용이 가능한 기본 중의 기본
 *
 */

public class Main {
    public static void main(String[] args) throws Exception {

        // Object 클래스
        // 모든 클래스의 조상 클래스 - 클래스의 기본 기능을 제공
        // 필요한 경우 Object 클래스에 구현된 메소드를 Override
        Object obj = new Object();
        Object obj1 = obj;
        Object obj2 = new Object();

        // getClass - 현재 객체의 클래스를 반환
        System.out.println(obj.getClass());

        // equals - 동일 객체를 가리키는지 여부
        System.out.println(obj.equals(obj1));
        System.out.println(obj.equals(obj2));

        // String의 경우 내용까지 (효율적으로) 비교하게 끔 Override
        String str1 = "abc";
        String str2 = str1;
        String str3 = "abc";
        String str4 = new String("abc");
        System.out.println(str1.equals(str2));

        // hashCode() 매소드
        // - 객체를 구분하기 위해서 사용하는 정수값(int)을 반환
        // - 각 객체마다 프로그램 내에서 유일하기 때문에 주소값처럼 사용 가능
        // - hashCode()는 native이기 때문에 정의를 볼 수 없다.
        //      - native: C 또는 C++ 등 외부 언어로 작성된 메소드
        // - hashCode()를 Ovveride할 때 제약사항
        //      - 한 객체의 hashCode()를 여러 번 호출할 경우,
        //        해당 객체의 equals()에서 사용하는 값이 변하지 않았다면 hashCode()의 반환 값은 동일해야 한다. (필수)
        //      - equals() 메소드가 같다고 판단한 두 객체의 hashCode() 값은 항상 같아야 한다. (필수)
        //      - equals() 메소드에서 다르다고 판단하는 두 객체는 항상 hashCode() 값이 다를 필요는 없으나,
        //        다르면 Hash 기반 자료구조의 성능이 향상된다. (권고)
        //      => 즉, 같으면 hashCode()는 반드시 같고, 다르다고 hashCode()가 반드시 다른 것은 아니다.

        class Foo{

        }

        class Bar{
            public void methodA() {
                System.out.println("method A is called.");
            }
        }


        Bar bar = new Bar();
        Class barClass = bar.getClass();

        // getName - 객체 이름
        System.out.println(barClass.getName());

        // getDeclaredMethods() -
        // Reflection API를 이용
        System.out.println(barClass.getDeclaredMethods());
        barClass.getDeclaredMethod("methodA").invoke(bar);


        // System 클래스
        // OS와 Interaction 하기 위한 클래스
        // 정적 변수와 메소드만으로 구성된 클래스

        // in, out은 정적 변수
//        System.in.read();       // in: InputStream 객체, 표준 입력
        System.out.println();   // out: PrintStream 객체, 표준 출력
        System.err.println();   // err: PrintStream 객체, 표준 에러


        // System.arraycopy() : Array를 shallow Copy
        // native로 되어있기 때문에 좀 더, 최적화가 되어있다 생각하면 된다.
        // 그렇기 때문에 for문 보다 더 효율적이다!!!
        int[] ints = {1, 2, 3, 4};
        int[] ints1 = new int[10];
        System.arraycopy(ints, 0, ints1, 0, ints.length);

        // currentTimeMillis(), nanoTime : 시간
        System.out.println(System.currentTimeMillis()); //
        System.out.println(System.nanoTime());  // RTOS가 아니기 때문에 정확하지 않을 수 있음(Real Time OS), 보통 참고용으로만 사용

        // exit: 프로그램 강제 종료
        // status = 0 : 정상 종료
        // status != 0 : 비정상 종료 (보통 1 사용)
//        System.exit(0);

        // Garbage Collection, GC를 요청(원하는 타이밍에 좀 더 빨리 처리해달라고 요청하는 정도, 가능성)
        System.gc();

        System.out.println(System.getenv());
        System.out.println(System.getenv("JAVA_HOME"));
        System.out.println(System.getenv("Path"));
        System.out.println(System.getProperties());
        System.out.println(System.getProperty("user.country"));
        System.out.println(System.getProperty("java.io.tmpdir"));
        System.out.println(System.getProperty("line.separator"));   // windows \r\n, linux \n
        System.out.println(System.getProperty("file.separator"));   // windows \ linux

    }
}
