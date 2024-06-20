package com.company;

/**
 * 연산자 (Operator)
 * 연산자(Operator), 피연산자(Operand) -> 연산식(Expression)
 * 사칙 연산자 : + - * / %
 * 비교 연산자 : > < == <= >=
 * 논리 연산자
 * 증감 연산자
 * 삼항 연산자
 * 비트 연산자
 * 대입 연산자
 */


public class Main {

    public static void main(String[] args) {
	// write your code here

        // 사칙 연산
        int x = 10, y = 20, z;

        z = x + y; // +: 연산자, x,y : 피연산자, x+y: 연산자(Expression)
                    // =: 연산자, z: 피연산자, x+y: 피연산자
        System.out.println(z);

        System.out.println("정수형 사칙 연산");
        System.out.println(20-5);
        System.out.println(5-20);
        System.out.println(10*662);
        System.out.println(150/8);      // 정수 나누기 -> 몫
        System.out.println(150%8);      // modulus, 나머지

        System.out.println("실수형 사칙 연산");
        System.out.println(10.0+52.3);
        System.out.println(10.5f+12.3);
        System.out.println(10.4 - 50);
        System.out.println(10.2*4.2);
        System.out.println(150/8.0);
        System.out.println(5.2/1.2);
        System.out.println(5.2%1.2);


        // 사칙 연산의 주의 사항
        System.out.println("사칙연산의 주의사항");
        System.out.println(Integer.MAX_VALUE /2 * 3); // Overflow
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE + 1);

        System.out.println((6.0 - 5.9) * 10);     // 1이 아닌 0.99999가 나온다.
        // 6.0이 정밀도가 떨어지는 숫자 이다. 이 외에도 여러가지가 있다.
        System.out.println(6.0 * 10);
        System.out.println(Math.floor((6 - 5.9) * 10));     // 내림

        System.out.println(40 / 0.0);   // Infinity
        System.out.println(40 % 0.0);   // NaN


        // 대입 연산자
        z = x + y;
        z += 10;    // z = z + 10;
        z %= 10;

        // 논린 연산자, 비트 연산자 등등 다 됨

        // 비교 연산자
        System.out.println("비교 연산자");   // 출력인 boolean
        System.out.println(10 > 20);
        System.out.println(10 < 20);
        System.out.println(10 >= 10);

        x = 10;
        y = 10;
        System.out.println(x == y);
        System.out.println(x != y);
        System.out.println("");


        // 논리 연산자 - 입출력이 모두 boolean
        // a AND b : a, b 모두 참일 때만 참
        // a OR b : a 또는 b 둘 중 하나만 참 이어도 참
        // a XOR b : a 또는 b 둘 중 하나만 참 이어야 참
        // NOT a : a가 참이면 거짓, 거짓이면 참 -> 단항 연산자


        System.out.println( 10<20 & 40 >=2);    // AND
        System.out.println(40<2 | 1 > 0);   // OR
        //System.out.println(!false);
        System.out.println(10>2 ^ 5<2); // XOR

        // short-circuit, 비트 연산으로 처리, 앞에 꺼만 먼저 처리
        System.out.println(10<20 && 4<2);

        int val = 0;

        System.out.println(val++);  // val = 0으로 먼전 Expression 평가 후에 val += 1 적용
        // sout(val);
        // val +=1;
        System.out.println(++val);  // val +=1을 먼저 계산한 후에 Expresstion 평가
        // val += 1;
        // sout(val);
        System.out.println(val--);
        // sout(val);
        // val -=1;
        System.out.println(--val);
        // val -= 1;
        // sout(val);

        // 삼항 연산자
        // ?:
        // (cond)?(ture):(false)
        System.out.println(true?1:0);
        System.out.println(false?1:0);

        x = 10;
        y = 13;
        System.out.println(x > y?x:y);  // max function
        System.out.println(x < y?x:y);  // min function

        // 비트 연산자
        // 정수형 연산에 사용
        System.out.printf("b%32s\n", Integer.toBinaryString(8));
        System.out.printf("b%32s\n", Integer.toBinaryString(8 >> 1)); // shift 연산자, 새로 추가되는 비트는 MSB로 처리
        System.out.printf("b%32s\n", Integer.toBinaryString(-1));
        System.out.printf("b%32s\n", Integer.toBinaryString(-1 >> 1));
        System.out.printf("b%32s\n", Integer.toBinaryString(-1 >>> 1)); // >>>는 MSB가 아닌 0으로 채움

        System.out.printf("b%32s\n", Integer.toBinaryString(1252));
        System.out.printf("b%32s\n", Integer.toBinaryString(15234));
        System.out.printf("b%32s\n", Integer.toBinaryString(1252 & 15234));
        System.out.printf("b%32s\n", Integer.toBinaryString(1252 | 15234));
        System.out.printf("b%32s\n", Integer.toBinaryString(1252 ^ 15234));
        System.out.printf("b%32s\n", Integer.toBinaryString(~1252));

        int intVal = 4123;
        intVal >>= 2; // intVal = intVal >>2; Shift 연산자도 대인연산자 가능
        intVal |= 412; // intVal | 412; Bitwise 연산자도 대입 연산자 가능


    }
}
