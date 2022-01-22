package com.company;

public class Main {

    public static void main(String[] args) {
//	// write your code here
//        // 정수형 : byte, short, int, long
//        System.out.print("Byte : ");
//        System.out.println(Byte.BYTES);
//        byte byteValue = 42;
//        System.out.println(Byte.MAX_VALUE); // 2^7 - 1;
//        System.out.println(Byte.MIN_VALUE); // -2^7;
//
//        System.out.println("Short");
//        System.out.println(Short.BYTES);
//        System.out.println(Short.MAX_VALUE); // 2^15 -1;
//        System.out.println(Short.MIN_VALUE); // -2^15;
//
//        System.out.println("Integer");
//        System.out.println(Integer.BYTES);
//        System.out.println(Integer.MAX_VALUE); // 2^31 -1;
//        System.out.println(Integer.MIN_VALUE); // -2^31;
//
//        System.out.println("Long");
//        System.out.println(Long.BYTES);
//        System.out.println(Long.MAX_VALUE); // 2^63 -1;
//        System.out.println(Long.MIN_VALUE); // -2^63;
//
//        // Overflow
//        // 32767 = 2^15-1 => 0111111111111111
//        // 64000
//        short shortValue = (short)64000;
//        System.out.println(shortValue);
//
//        // 정수형은 기본적으로 int
//        //byte byteValue3 = 144;
//        short shortA = 10;
//        short shortB = 20;
//        short shortC = (short)(shortA+shortB);
//
//        int bigInt = Integer.MAX_VALUE;
//        int biggerInt = bigInt + 1;
//        System.out.println(biggerInt);
//
//        long veryBigInt = 10000000000000000L;
//
//        // 진수법 - Binary(2), Octal(8), Decimal(10), Hexadecimal(16)
//        System.out.println(0b1101);
//        System.out.println(071);
//        System.out.println(0x11);   // 0~9 10~15: A,B,C,D,E,F
//
//        // byte flagByte = 0b00101100
//
//        // 실수형 float, double
//        System.out.println("float");
//        System.out.println(Float.BYTES); // 4
//        System.out.println(Float.MAX_VALUE); // 3.4028235*10^38
//        System.out.println(Float.MIN_VALUE); // 1.4*10^-45(절대값으로 가장 작은 값, resolution)
//
//        System.out.println("double");
//        System.out.println(Double.BYTES); // 8
//        System.out.println(Double.MAX_VALUE); // 1.8*10^308
//        System.out.println(Double.MIN_VALUE); // 4.9*10^-324
//
//        float floatVal = 1.4234f;
//        float floatVal2 = (float)1.4234;
//
//        double doubleVal = 104.42512341245;
//        double doubleVal2 = 1.423E8;
//        double doubleVal3 = 1.423e8;
//
//
//        // 문자형
//        System.out.println("Char");
//        System.out.println(Character.BYTES);    // 2
//        System.out.println((int)Character.MAX_VALUE);
//        System.out.println((int)Character.MIN_VALUE);
//
//        char charVal = 'A';
//        System.out.println(charVal);
//        System.out.println((int)charVal);
//        System.out.println('\'');
//        System.out.println('"');
//        System.out.println('\"');
//
//        System.out.println('\u0041');
//        System.out.println((char)65);
//
//        System.out.println("boolean");
//        System.out.println(Boolean.TRUE);
//        System.out.println(Boolean.FALSE);
//
//        boolean isTrue = true;
//        boolean isFalse = false;
//
//        // isTrue = 1; Java에서는 안됨
//
//        // 문자열 (String)
//        System.out.println("String");
//
//        String s = "This is a new String.";
//        System.out.println(s);
//
//        String s1 = "This"+" is "+"also"+" a Stirng.";
//        System.out.println(s1);
//
//        int intVal10 = 20;
//        String s2 = "String + Integer = "+intVal10;
//        System.out.println(s2);
//
//        String s3 = "String + Integer = "+Integer.valueOf(intVal10).toString(); // 수동으로 Integer to String

//        // 형변환 (Type Casting)
//        System.out.println("Casting");
//        int intValue = (int)100.9;
//        System.out.println(intValue);
//
//        // Upcasting
//        System.out.println("Upcasting");    // 번위가 작은 쪽 -> 범위가 큰 쪽
//                                            // 정밀도가 작은 쪽 -> 정밀도가 큰 쪽
//        byte byteVal = 10;
//        int intVal = byteVal;
//        intVal = (int)byteVal;              // Upcating은 자동으로 된다.
//        System.out.println(intVal);
//
//        intVal = 10244;
//        long longVal = intVal;
//        longVal = (long)intVal;
//
//        float floatVal = longVal;           // float: 4byte long: 8byte
//        floatVal = (float)longVal;
//
//        double doubleVal = floatVal;
//        doubleVal = (double)floatVal;



        // Downcasting
        long longVal = 104244L;
        int intVal = (int)longVal;
        System.out.println(intVal);

        longVal = 100_000_000_000L;
        intVal = (int)longVal;
        System.out.println(intVal);     // Downcasting 하면서 상위 비트 소실

    }
}
