@FunctionalInterface
interface PrintString{
    void showString(String str);
}

public class Lambda2 {
    public static void main(String[] args) {

        PrintString lambdaStr = s -> System.out.println(s);
        lambdaStr.showString("Test");

        showMyString(lambdaStr);

        PrintString lambdaaStr2 = returnString();
        lambdaaStr2.showString("Test3");
    }

    public static void showMyString(PrintString p){
        p.showString("Test2");
    }

    public static PrintString returnString(){
        return s -> System.out.println(s);
    }
}


