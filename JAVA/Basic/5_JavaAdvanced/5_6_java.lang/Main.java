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


        // Math 클래스
        // 수학 계산에 필요한 메소드를 가진 final 클래스 -> 상속할 수 없다.
        // 모든 메소드가 static 메소드로 구현되어 있다.

        // abs() : 절대 값, int long float double Overriding
        System.out.println(Math.abs(-4));       // 4

        // ceil() : 올림, double 입력, double 출력
        System.out.println(Math.ceil(1.2));     // 2.0

        // floor() : 내림, double 입력, double 출력
        System.out.println(Math.floor(1524.4)); // 1524.0

        // max(), min() : 2개의 값만을 비교하게 되어 있음
        System.out.println(Math.max(4, 2));     // 4
        System.out.println(Math.min(4, 2));     // 2

        // random() : 0.0 이상, 1.0 미만의 값을 임의로 출력
        System.out.println(Math.random());
        System.out.println(Math.random() > 0.7);    // 30퍼센트 확율인 경우, 보통 이런 식으로 사용

        // 확률 표현
        int count = 0;
        for(int i = 0; i < 1000; i++){
            if(Math.random() < 0.3) {
                count += 1;
            }
        }

        int minVal = 2;
        int maxVal = 10;
        int randInt = (int)(Math.random() * (maxVal - minVal + 1) + minVal);    // 2 이상 ~ 9 미만
        System.out.println(randInt);


        // round() : 반올림, float -> int, double -> long 변환
        System.out.println(Math.round(1.4f));   // 1

        // addExact() : 덧셈 연산으로, Overflow 발생 시 ArithmaticException 발생.
        // subtractExact() : 뺄셈 연산으로, Overflow 발생 시 ArithmaticException 발생.
        // multiplyExact() : 곱셈 연산으로, Overflow 발생 시 ArithmaticException 발생.
        try {
            System.out.println(Math.addExact(Integer.MAX_VALUE, 10));
            System.out.println(Math.subtractExact(Integer.MIN_VALUE, 10));
            System.out.println(Math.multiplyExact(Integer.MAX_VALUE, 4));
        } catch(ArithmeticException e) {
            e.printStackTrace();
        }

    }
}
