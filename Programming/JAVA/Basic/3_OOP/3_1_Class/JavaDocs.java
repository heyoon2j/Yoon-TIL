package com.company;

/**
 * 클래스에 대한 설명, 이 클래스는 JavaDocs 클래스를 공부하기 위해...
 * @author  Yoon Seok
 */
public class JavaDocs {
    /**
     * 맴버 변수에 대한 설명
     */
    int memberInt;

    /**
     * 맴버 변수에 대한 설명
     */
    String memberString;

    /**
     * 생성자에 대한 설명
     * @param memberInt 입력 1
     * @param memberString 입력 2
     */
    public JavaDocs(int memberInt, String memberString) {
        this.memberInt = memberInt;
        this.memberString = memberString;
    }

    /**
     * 메소드에 대한 설명
      * @param string 입력 인자 (파라미터)에 대해 설명
     * @return 리턴에 대한 설명
     */
    public int methodA(String string){
        return  Integer.parseInt(string);
    }

    public static void main(String[] args) {
        JavaDocs j = new JavaDocs(1,"string");
    }
}
