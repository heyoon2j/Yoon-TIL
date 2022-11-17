package com.company;

import java.util.Scanner;

/**
 * 자료 입출력
 * 출력 - print println printf, 포맷팅 방법
 * 입력 - 키보드 입력 받기
 */

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("String can be printed.");
        System.out.println('C');
        System.out.println(40234);
        System.out.println(1.423f);
        System.out.println(1523.124);

        System.out.println("124124\r\n"); // \r : 캐리지리턴, \n : new line
        System.out.println("124124\n");

        System.out.printf("%s is for string \n", "STRING");

        System.out.printf("%s %s %s", "123", "1414", "15234");
        System.out.printf("%b\n", true);
        System.out.printf("%d 0x%x 0x%X 0%o\n", 15, 15, 15, 15);
        System.out.printf("%f\n", 14.23);
        System.out.printf("%f\n", 14.23F);

        System.out.printf("%e\n", 14.423);
        System.out.printf("%c %c\n", 'U', '\u0042');

        System.out.printf("%n \n");
        System.out.printf("%5d.\n", 10);
        System.out.printf("%-5d.\n", 10); // 3칸은 최소한 확보

        System.out.printf("%5.4f\n", 12311.1231312);


        // 입력 메소드
        Scanner scanner = new Scanner(System.in);

        //String s= scanner.next(); // 공백으로 구분된 String을 입력 받는다.
        //System.out.println(s);

        // next() 메소드는 입력을 내용이 있을 때 가지 기다린다.
        // Blocking 메소드라고 부른다. (<-> non-blocking 메소드)
//        System.out.println(scanner.next());
//        System.out.println(scanner.next());
//        System.out.println(scanner.next());

//        System.out.println(scanner.nextInt());
//        System.out.println(scanner.nextInt());
//        System.out.println(scanner.nextInt());
//        System.out.println(scanner.nextInt());  // 자료형이 안 맞으면 에러 발생

        // 엔터로 구분
        System.out.println(scanner.nextLine());
        System.out.println(scanner.nextLine());
        System.out.println(scanner.nextLine());
        
    }
}
