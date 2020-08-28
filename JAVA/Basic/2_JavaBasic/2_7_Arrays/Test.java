package com.company;

public class Test {
    public static void main(String[] args){
        /**
         * Array 연습
         *
         *
         *
         */

        int[] integers = {4, 2, 12, 23, 62, 9, -2, 0 ,-4};
        // 1. 위 배열에서 최대 값을 찾는 프로그램을 작성
        int maxVal = integers[0];

        for(int i = 0; i < integers.length; i++){
            if(integers[i] >= maxVal)
                maxVal = integers[i];
        }
        System.out.println(maxVal);
        System.out.println();


        // 2. 위 배열에서 2번째로 큰 값을 찾는 프로그램을 작성
        // 2 - 1
        for(int i = 0; i < integers.length; i++){
            if(integers[i] > maxVal){
                maxVal = integers[i];
            }
        }

        int secVal = (integers[0]==maxVal)?integers[0]:integers[1];
        for(int i = 0; i < integers.length; i++){
            if(integers[i] > secVal && maxVal > integers[i]){
                secVal = integers[i];
            }
        }
        System.out.println(maxVal+" : "+secVal);

        // 2 - 2
        if(integers[0]>integers[1]){
            maxVal = integers[0];
            secVal = integers[1];
        }else{
            maxVal = integers[1];
            secVal = integers[0];
        }

        for(int i = 2; i < integers.length; i++){
            if(integers[i]>maxVal){
                secVal = maxVal;
                maxVal = integers[i];
            }
        }
        System.out.println(maxVal+" : "+secVal);

    }
}
