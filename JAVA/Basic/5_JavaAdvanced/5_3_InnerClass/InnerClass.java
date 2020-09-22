class OuterClass{
    int outNum = 100;
    static int sOutNum=200;

    Runnable getRunnable(int i){
        int num = 100;

        // LocalClass
        class MyRunnable implements Runnable{
            @Override
            public void run() {
//                num += 10;        // num, i는 Local 변수이기 때문에, 함수가 종료되면 사라지기 때문에h사용불가
//                i = 200;          // 그렇기 때문에, Java에서는 Local 변수를 final로 자동으로 변경하고, 복사하여 LocalClass 내부에도 저장한다.
                System.out.println(num);
                System.out.println(i);

                System.out.println(outNum);     // Possible
                System.out.println(sOutNum);    // Possible
            }
        }
//        return new MyRunnable();

        // Anonymous Object
        return new Runnable(){
            int i = 10;
//            static int j = 20;
            @Override
            public void run() {
                System.out.println(num);
                System.out.println(i);

                System.out.println(outNum);
                System.out.println(sOutNum);
            }
        };
    }

    // Anonymous Object
    Runnable runner = new Runnable() {
        @Override
        public void run() {
            System.out.println();
        }
    };
}

public class InnerClass {
    public static void main(String[] args) {
        OuterClass oc = new OuterClass();
        oc.getRunnable(10);
    }
}
