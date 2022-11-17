import java.util.Arrays;
import java.util.Comparator;
import java.util.function.*;

@FunctionalInterface
interface FunctionalInterface2 {
    int a = 10;
    public abstract String method();
}

interface AAA{
    void method();
}

public class Main {
    static int a = 20;
    static void functionalMethod(FunctionalInterface2 obj) {
        System.out.println(obj.method());
    }

    public static void main(String[] args) {
        int kkk = 10;
        class Test implements FunctionalInterface2{
            int abc = 20;
            @Override
            public String method() {
                System.out.println(abc++);
                abc=abc+20;
                Main.a--;
                System.out.println("this: " + this);
//                System.out.println("OuterClass.this: " + Main.this);
                return "Test";
            }
        }

        AAA a = (()-> {});



        functionalMethod(new Test());

        functionalMethod(()->
//            System.out.println("this: " + FunctionalInterface2.a);
//            System.out.println(this)
//            System.out.println("OuterClass.this: " + Main.this);
            String.valueOf("1234")
        );

        functionalMethod(new FunctionalInterface2() {
            int abc = 20;
            @Override
            public String method() {
                System.out.println("this: " + this);
                System.out.println(++abc);
//                System.out.println("OuterClass.this: " + Main.this);
                return "Anonymous inner class used.";
            }
        });
    }
}
class ComparatorTest implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return o1.substring(1).compareTo(o2.substring(1));
    }
}

class Test{

    public static void main(String[] args) {
        String[] test = {"Java", "Study", "Yoon", "Church"};
        Arrays.sort(test);
        System.out.println(Arrays.toString(test));

        // 1. Comparator 구현
        Arrays.sort(test, new ComparatorTest());
        System.out.println(Arrays.toString(test));

        // 2. 익명 내부 클래스
        Arrays.sort(test, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.substring(2).compareTo(o2.substring(2)) < 0)
                    return -1;
                else if(o1.substring(2).compareTo(o2.substring(2)) == 0)
                    return 0;
                else
                    return 1;
//                return o1.substring(2).compareTo(o2.substring(2));
            }
        });
        System.out.println(Arrays.toString(test));

        // 3. Lambda 구현
        Arrays.sort(test,(o1, o2) -> o1.substring(3).compareTo(o2.substring(3)));
        System.out.println(test);

        // Comparable
        class ComparableTest implements Comparable<ComparableTest>{
            String value;

            public ComparableTest(String value) {
                this.value = value;
            }

            @Override
            public int compareTo(ComparableTest o) {
                return value.substring(1).compareTo(o.value.substring(1));
            }

            @Override
            public String toString() {
                return value.toString();
            }
        }

        ComparableTest[] test2 = {new ComparableTest("Java"), new ComparableTest("Study"), new ComparableTest("Yoon")};
        Arrays.sort(test2);
        System.out.println(Arrays.toString(test2));
    }
}

/**
 * Consumer 계열
 * - 파라미터 입력을 받아서 그것을 소비하는 Functional Interface
 * - accept 메소드를 사용: 리턴 타입이 void인 특징
 *
 *
 * Supplier 계열
 * - 아무런 입력을 받지 않고, 값을 하나 반환하는 함수형 인터페이스
 * - 자료를 '공급'하는 역할을 한다.
 *
 *
 *
 * Function 계열
 * - Maaping : 입력 -> 출력의 Mapping을 하는 함수형 인터페이스
 * - 입력 타입과 출력 타입은 다를 수 있다.
 *
 *
 * Operator 계열
 * - 입력받은 타입과 동일한 타입의 출력을 하는 함수형 인터페이스
 * - Function 계열과 달리 입출력 타입이 다를 수 없다.
 *
 *
 * Predicate 계열
 * - 논리 판단을 해주는 함수형 인터페이스
 * - 입력을 받아서 boolean 타입 출력으로 변환
 *
 */
class StandardLambda {
    public static void main(String[] args) {
        Consumer<String> consumer = (s) -> System.out.println(s);
        consumer.accept("Hello");

        // 입력 값이 두 개
        BiConsumer<String, String> biConsumer = (t, u) -> System.out.println(t+u);
        biConsumer.accept("StringA", "StringB");

        // Autoboxing/Unboxing은 비효율적
        Consumer<Integer> integerConumser = (x) -> System.out.println(x);
        integerConumser.accept(5);

        // IntConsumer
        IntConsumer intConsumer = (x) -> System.out.println(x);
        intConsumer.accept(10);
        // LongConsumer, DoubleConsumer

        ObjIntConsumer<String> objIntConsumer = (t, x) -> System.out.println(t + " : "+x);
        objIntConsumer.accept("x",1023);
        // ObjLongConsumer, ObjDoubleConsumer

        ///////////////////////////////////////

        Supplier<String> supplier = () -> "A String";
        System.out.println(supplier.get());

        // Supplier는 P Type 계열에서 getAsP 메소드로 정의
        BooleanSupplier boolSup = () -> true;
        System.out.println(boolSup.getAsBoolean());
        // IntSuplier, LongSupplier, DoubleSupplier

        IntSupplier rollDice = () -> (int)(Math.random()*6);

        int x = 4;
        IntSupplier intSupp = () -> x;
        // Lambda로 동작한다. Local, Parameter 의 경우, 내부로 final로 저정됨


        /////////////////////////////////////////

        // Function 계열
        Function<String, Integer> func = s -> s.length();
        func.apply("Four");

        // Bi가 붙으면 '입력'을 2가지를 받을 수 있다는 의미
        BiFunction<String, String, Integer> funcTwo = (s, u) -> s.length();

        // P Type Function은 입력을 P Type으로 받는다.
        IntFunction<String> funcTree = value -> "" + value;
        System.out.println(funcTree.apply(512));

        // ToP Type Function은 출력을 P 타입으로 한다.
        ToIntFunction<String> funcFour = (s) -> s.length();

        // 출력이 P타입인 경우에는 AsP가 들어간다.
        funcFour.applyAsInt("ABCDE");
        // ToIntBiFunction<String, String> funcFive
        // P: Int, Long, Double

        // PtoQFunction : P -> Q로 매핑하는 함수
        IntToDoubleFunction funcFive;
        // IntToIntFunction은 없다. 동일한 형태는 없다.

        ////////////////////////////////////////

        // 기본 Operator가 없다, 입력이 1개인 것을 Unary로 표현
        UnaryOperator<String> operator = s -> s + ".";
        System.out.println(operator.apply("왔다"));

        BinaryOperator<String> operatorTwo = (s1, s2) -> s1 + s2;
        System.out.println(operatorTwo.apply("나", "왔다"));

        IntUnaryOperator op = value -> value * 10;
        System.out.println(op.applyAsInt(5));
        // LongUnaryOperator, DoubleUnaryOperator

        IntBinaryOperator op2 = (v1, v2) -> v1 * v2;
        System.out.println(op2.applyAsInt(5, 10));
        // LongBinaryOperator, DoubleBinaryOperator

        ////////////////////////////////////////////////////////

        Predicate<String> pred = (s) -> s.length() == 4;
        System.out.println(pred.test("abcd"));
        System.out.println(pred.test("abcde"));

        BiPredicate<String, Integer> pred2 = (s, v) -> s.length() == v;
        System.out.println(pred2.test("abcd", 3));

        IntPredicate pred3 = (i) -> i > 0;
        System.out.println(pred3.test(3));
        // LongPredicate, DoublePredicate

        /**
         * andThen 메소드, compose 메소드
         * - andThen 메소드: A.andThen(B) -> A부터 실행하고 B를 실행, Consumer, Function, Operator 계열의 default Method 구현
         * - compose 메소드: A.compose(B) -> B부터 실행하고 A를 실행, Function, Operator 계열의 default Method 구현
          */

        Consumer<String> c0 = s -> System.out.println("c0:"+s);
        Consumer<String> c1 = s -> System.out.println("c1:"+s);
        Consumer<String> c2 = c0.andThen(c1);
        c2.accept("STRING");


        // Function 계열은 입력 -> 출력 이 연쇄가 되어야 된다.
        Function<String, Integer> func1 = s -> s.length();
        Function<Integer, Long> func2 = value -> (long)value;
        Function<String, Long> func3 = func1.andThen(func2);
        System.out.println(func3.apply("Four"));

        Function<String, Long> func4 = func2.compose(func1);
        System.out.println(func4.apply("Four"));


        BinaryOperator<String> op0 = (s1, s2) -> s1 + s2;
        UnaryOperator<String> op1 = (s) -> s+".";
        // Operator들을 섞어 쓰더라고, 중간에 Function 계열이 있을수도 있기 때문에
        // 최종 조합 결과는 Funcation 계열로 받아  주어야 함
        BiFunction<String, String, String> op_2 = op0.andThen(op1);

        UnaryOperator<String> op3 = (s) -> s;
        op1.compose(op3);


        /**
         * Predicate 계열 기본 메소드 / 클래스 메소드
         * - and(), or(), negate() : 기본 메소드
         * - isEqual() : 클래스 메소드
         *
         */

        // and(), or(), negate() : 입력의 개수가 같아야 된다.
        DoublePredicate p0 = (y) -> y > 0.5;
        DoublePredicate p1 = (y) -> y < 0.7;
        DoublePredicate p2 = p0.and(p1);    // and
        System.out.println(p0.test(0.9));
        System.out.println(p1.test(0.9));
        System.out.println(p2.test(0.6));

        DoublePredicate p3 = p0.or(p1);     // or
        DoublePredicate p4 = p0.negate();   // not


        // Class Method
        Predicate<String> eq = Predicate.isEqual("String");
        System.out.println(eq.test("String"));
        System.out.println(eq.test("String!"));


        /**
         * BinaryOperator 인터페이스의 클라스 메소드
         * - minBy, maxBy : Comparator를 입력받아 min, max 출력
         */

        BinaryOperator<String> minBy = BinaryOperator.minBy((o1, o2) ->
                o1.length() > o2.length() ? 1 : -1);        // true이면 1, o2 출력
        BinaryOperator<String> maxBy = BinaryOperator.minBy((o1, o2) ->
                o1.length() > o2.length() ? 1 : -1);

        System.out.println(minBy.apply("abc", "cd"));
        System.out.println(maxBy.apply("abc", "cd"));
        System.out.println(minBy.apply("abc", "cde"));


        /**
         * 람다식에 기존 메소드/생성자 사용
         * - 람다식에 기존에 구현되어 있는 내용을 재사용하고자 할 때
         * - 생성자를 andThen, compose와 같이 사용 가능
         */

        // 클래스::인스턴스_메소드
        String[] strings = {"fast", "campus", "best"};
        Arrays.sort(strings, String::compareTo);
        // Comparator 인터페이스는 2개의 입력 o1, o2를 받음
        // o1.인스턴스_메소드(o2)로 호출되는 메소드가 사용됨


        // 클래스::클래스_메소드
        Function<String, Integer> func0 = Integer::parseInt;

        // 인스턴스::인스턴스_메소드
        String s = "String";
        Predicate<String> preq = s::equals;
        pred.test("String");    // True
        pred.test("String2");   // False

        // 클래스::new
        Function<Integer, Integer> fnc = Integer::new;

        // 클래스[]::new
        IntFunction<String[]> fnc2 = String[]::new;
        String[] strings2 = fnc2.apply(100);
    }
}















