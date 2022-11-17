class OutClass{
    private int outNum = 10;
    private static int sOutNum = 20;
    private InClass inClass;

    public OutClass(){
        inClass = new InClass();    // 일반적으로 Inner Class는 Constructor에서 생성함
    }


    class InClass {
        int iNum = 100;
//        static int sInNum = 200;
        void inMethod(){
            System.out.println(outNum);
            System.out.println(sOutNum);
            System.out.println(iNum);
            usingInner();
        }
    }

    public void usingInner(){
        inClass.inMethod();
    }

    static class InStaticClass{
        int inNUm = 100;
        static int sInNum = 200;

        void inTest(){
//            System.out.println(outNUm);
            System.out.println(sOutNum);
            System.out.println(inNUm);
            System.out.println(sInNum);
        }
        static void sTest(){
//            System.out.println(outNUm);
            System.out.println(sOutNum);
//            System.out.println(inNum);
            System.out.println(sInNum);
//            usingInner();
        }
    }
}


public class InnerClass2 {
    public static void main(String[] args) {
        OutClass outClass = new OutClass();
        outClass.usingInner();

        OutClass.InClass myInClass = outClass.new InClass();

        OutClass.InStaticClass sInClass = new OutClass.InStaticClass();
        OutClass.InStaticClass.sTest();
//        OutClass.InStaticClass.inNum;
    }
}
