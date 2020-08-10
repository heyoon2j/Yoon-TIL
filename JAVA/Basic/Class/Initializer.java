package com.company;

/**
 * 초기화 블록 (Initializer)
 *
 */
public class Initializer {
    static int classVar;
    static int instanceCount;
    int instanceVar;

    // static initializer
    static {
        System.out.println("static block1");
        classVar = 20;
    }
    // Object initializer
    {
        System.out.println("blocks");
        instanceVar = 30;
        classVar = 50;
        instanceCount++;
    }
    static {
        System.out.println("static block2");
        classVar = 5;
    }
    {
        System.out.println("blocks2");
        instanceVar = 2;
    }
}


class MainTest {
    public static void main(String[] args) {
        System.out.println(Initializer.classVar);

        Initializer init = new Initializer();
        System.out.println(Initializer.instanceCount);
        System.out.println(init.instanceVar);
        System.out.println(init.classVar);

        Initializer init2 = new Initializer();
        System.out.println(Initializer.instanceCount);


    }
}
