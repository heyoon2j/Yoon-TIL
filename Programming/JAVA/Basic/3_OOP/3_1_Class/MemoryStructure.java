package com.company;

/**
 * 클래스 객체의 메모리 구조
 * - 클래스 영역 (Class Area, Method Area, Code Area, Static Area)
 *     -> field 정보, method 정보, type 정보, constant pool
 * - 스택 영역 (Stack Area)
 *      -> method 호출 시, 선언된 로컬 변수(임시로 있다가 사라짐)
 * - 힙 영역(Heap Area)
 *      -> new 키워드로 생성된 객체
 *      -> Garbage Collection이 동작하는 영역: 더이상 사용하지 않는 메모리를 알아서 반환하는 JVM의 기능
 */

// 기본적으로 OS에 의해서 포인터 크기가 결정된다.
public class MemoryStructure {  // Class 영역
    int x, y;   // Heap 영역
    String string = "String!!!";    // Heap 영역, Constant Pool

    public void method(int value) { // Class 영역
        char c = 'w';   // Stack 영역
    }
}
