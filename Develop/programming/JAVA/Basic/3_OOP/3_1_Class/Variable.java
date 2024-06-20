package com.company;

/**
 * 변수 (Variable)
 * 클래스에서 쓰이는 다양한 변수
 *      - 클래스 맴버 변수 (static variable, class variable)
 *      - 인스턴스 맴버 변수 (member variable, attribute, ...)
 *      - 로컬 변수 (local variable)
 *      - 로컬 파라미터 변수 (local parameter variable), (argument)
 *
 */

public class Variable {
    static int classVar;    // 클래스 맴버 변수
    int instanceVar;

    public void method(int paramVar){   // 로컬 파라미터 변수
        System.out.println(paramVar);
        int localVar;

        // System.out.println(localVar);    : 로컬 변수는 초기화가 안 됨

        localVar = 10;
        {
            localVar = 30;
            int localVar2 = 20;
        }
        System.out.println(localVar);   // 블록 내에서 수정한 것도 반영됨
        //localVar2 = 40; -> {} 외부이므로 접근 불가, 생명주기(Life Cycle)가 끝났다.
    }
}

class VariableTest{
    public static void main(String[] args) {
        System.out.println("클래스 변수");
        System.out.println(Variable.classVar);  // 클래스 변수는 클래스 이름으로 바로 접근 가능
        Variable.classVar = 10; // 클래스 이름.변수명으로 접근 가능
        System.out.println(Variable.classVar);
        System.out.println("");

        System.out.println("인스턴스 변수");
        Variable var = new Variable();
        System.out.println(var.instanceVar);    // 0으로 초기화
        var.instanceVar = 20;
        System.out.println(var.instanceVar);

        Variable var2 = new Variable();
        System.out.println(var2.instanceVar);

        // System.out.println(var2.classVar);  // 권장하지 않은 방법
        // Variable.instanceVar : 접근 불가능

        System.out.println("로컬 변수");
        var.method(9);

    }
}
