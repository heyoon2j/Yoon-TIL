/**
 * MyMath 클래스 구현하기
 * 인스턴스를 생성할 수 없는 MyMath 클래스를 구현하시오.
 *
 * MyMath 클래스는 다음 정적 변수를 가진다.
 * PI = 3.1415927;
 * E = 2.718281;
 *
 * MyMath 클래스는 다음 정적 메소드를 가진다.
 * min - 정수 또는 실수를 여러개 입력받아 최소값을 구한다.
 * max - 정수 또는 실수를 여러개 입력받아 최대값을 구한다.
 * abs - 정수 또는 실수를 입력받아 절대값을 구한다.
 * floor - 실수를 입력받아 내림 연산한 정수를 출력한다.
 * ceil - 실수를 입력받아 올림 연산한 정수를 출력한다.
 */

public class MyMath {
    public static final double PI = 3.1415927;
    public static final double E = 2.718281;

    private MyMath(){}

    // min overloading
    static int min(int... num){
        int temp = num[0];
        for(int i = 1; i < num.length; i++) {
            if (num[i] < temp)
                temp = num[i];
        }
        return temp;
    }
    static double min(double... num){
        double temp = num[0];
        for(int i = 1; i < num.length; i++){
            if(num[i]<temp)
                temp = num[i];
        }
        return num[0];
    }

    // max overloading
    static int max(int... num){
        int temp = num[0];
        for(int i = 1; i < num.length; i++) {
            if (num[i] > temp)
                temp = num[i];
        }
        return temp;
    }
    static double max(double... num){
        double temp = num[0];
        for(int i = 1; i < num.length; i++) {
            if (num[i] > temp)
                temp = num[i];
        }
        return temp;
    }
    // abc overloading
    static int abs(int num){
        if(num<0)
            num *= -1;
        return num;
    }
    static double abs(double num){
        if(num<0)
            num *= -1;
        return num;
    }
    // floor function 내림
    static double floor(double num){
        double pointNum = num%1;
        if(pointNum>=0)
            return num-pointNum;
        else
            return num-pointNum-1;
    }
    // ceil function 올림
    static double ceil(double num){
        double pointNum = num%1;
        if(pointNum > 0)
            return num-pointNum+1;
        else
            return num-pointNum;

    }
}

class MyMathTest{
    public static void main(String[] args){
        System.out.println();
        System.out.println(MyMath.PI);
        System.out.println(MyMath.E);
        System.out.println(MyMath.min(2, 3, -4, 6));
        System.out.println(MyMath.max(7, 0, 6, 16, -4));
        System.out.println(MyMath.abs(5));
        System.out.println(MyMath.abs(-2.3));
        System.out.println(MyMath.floor(-1.5232));
        System.out.println(MyMath.ceil(4.6452));
    }
}
