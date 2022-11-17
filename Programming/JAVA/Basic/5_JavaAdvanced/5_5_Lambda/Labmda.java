@FunctionalInterface
interface MyMax{
    int getMyMaxNumber(int x, int y);
}

// not use lambda
@FunctionalInterface
interface StringConcat{
    public void makeString(String s1, String s2);
}

class StringConImpl implements StringConcat{
    @Override
    public void makeString(String s1, String s2) {
        System.out.println(s1 + ", " + s2);
    }
}

public class Labmda {
    public static void main(String[] args) {

//        MyMax max = (x, y) -> { return (x >= y)? x : y; };
        MyMax max = (x, y) -> (x >= y)? x : y;
        System.out.println(max.getMyMaxNumber(10, 20));

        // 비교, not use Lambda
        StringConImpl impl = new StringConImpl();
        impl.makeString("hello", "world");

        // Lambda is used Anonymous Object
        StringConcat concat = new StringConcat(){
            @Override
            public void makeString(String s1, String s2) {
                System.out.println(s1 + ", " + s2);
            }
        };
        concat.makeString("hello","world");
    }
}
