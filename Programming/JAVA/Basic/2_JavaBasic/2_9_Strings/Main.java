package com.company;

/**
 *
 * 문자열 (String)
 *
 * 문자열은 내부적으로 '클래스'로 구성되어 있다.
 * 내부에는 문자의 배열로 된 데이터가 있다. char[]
 * 한 번 만든 문자열은 변하지 않는다. (Immutable)
 *
 * 문자열 편집은 String을 쓰지 않고 Builder나 Buffer 등을 사용한다.
 *
 *
 */

public class Main {

    public static void main(String[] args) {
	// write your code here

        //문자열 생성
        String s1 = "문자열 생성 방법";
        String s2 = new String("문자열 생성 방법2");   // 클래스 생성자, 권장하지 않는다.

        String s3 = "abcde";
        String s4 = "abcde";
        String s5 = new String("abcde");

        System.out.println(s3 == s4);   // 문자열을 곧바로 생성할 경우 상수 풀에서 찾아 사용
        System.out.println(s3 == s5);   // 문자열을 클래스로 생성할 경우 새로운 값을 생성, 권장하지 않음

        System.out.println(s3.equals(s4));
        System.out.println(s3.equals(s5));

        String s = "This is a string.";

        System.out.println(s.length()); // int length() : String의 길이를 반환
        System.out.println(s.charAt(2));    // char charAt(int index) : 해당 Index에 위치한 Character 반환
        System.out.println(s.indexOf('a')); // 해당 String에서 Index 위치는 확인
        System.out.println(s.equalsIgnoreCase("THIS IS A STRING>"));    // Boolean equalsIgnoreCase() : 대소문자 상관없이 비교
        System.out.println(s.replace('i','t')); // String replace(oldChar, newChar) : oldChar를 newChar로 변경, 변경된 String을 새로 생성하게 된다.
        System.out.println(s.substring(3,9));  // String substring(int from, int to) : from 부터 to - 1까지 잘른다, 3~8번까지 잘라준다.
        System.out.println(" wwef ".trim());   // String.trim() : 양 옆에 공백이 남아있다면 제거해준다.
        System.out.println("*".repeat(10)); // String.repeat(int count) : count 만큼 반복

        char[] characters = s.toCharArray(); // String.toCharArray() : String -> Char[] 로 변환

    }
}
