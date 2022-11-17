package com.company;

/**
 * 반복문(Loops)
 * for 문, while 문
 * 초기화 - 반복문을 실행하기 위해서 증감할 변수를 초기화
 * 조건식 - 반복문을 실행할 조건 (또는 종료한 조건)
 * 증감식 - 실행문이 실행된 후에 변수에 증감을 주는 것
 * 실행문 - 조건식이 참(또는 거짓)일 경우 실행할 코드
 * 증감식 - 실행문이 실행된 후에 변수에 증감을 주는 것
 */

public class Main {

    public static void main(String[] args) {
	// write your code here
        // for 문
        for(int i = 0; i < 5; i++){
            System.out.println(i);
        }

        for(int i = 0; i < 5; i=i+2){
            System.out.println(i);
        }

        for(int i = 4; i >= 0; i--)
            System.out.println(i);

        // Nested for 문
        for(int i = 0; i<5; i++){
            for(int j = 0; j < 5; j++)
                System.out.printf("[%d, %d]\n",i,j);
        }

        for(int i = 0; i < 5; i++)
            System.out.println(i);  // iterated
        System.out.println(""); // not iterated

        // while문
        // while(조건문){
        //  실행문
        // }

//        int i = 0;
//        while(i<5){
//            System.out.println(i);
//            i++;
//        }
//
//        i = 0;
//        do{     // 최소 한 번이 실행된다.
//            System.out.println(i);
//            i++;
//        }while(i < 5);

        // 제어문 - break continue
        for(int i = 0; i < 10; i++){
            if(i==3)
                continue;
            System.out.println(i);
        }
        for(int i = 0; i < 10; i++){
            if(i==3)
                break;
            System.out.println(i);
        }

        outer:      // Label
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(i == 3) {
                    System.out.println("cc");
                    continue outer;
                }
                System.out.printf("%d * %d = %d\n", i, j, i*j);
            }
        }

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(i == 3) {
                    continue;
                }
                System.out.printf("%d * %d = %d\n", i, j, i*j);
            }
        }

    }
}
