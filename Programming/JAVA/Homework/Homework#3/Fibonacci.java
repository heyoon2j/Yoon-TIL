package com.company;

public class Fibonacci {
    public static void main(String[] args){
        int seqLength = 100;
        System.out.println(fibonacciFunction(seqLength));
    }

    public static double fibonacciFunction(int n){
//        if(n == 1)
//            return 0;
//        else if(n == 2)
//            return 1;
//        else
//            return fibonacciFunction(n-1) + fibonacciFunction(n-2);

        double val1 = 0;
        double val2 = 1;
        double result = 0;

        if(n == 1)
            return val1;
        else if(n == 2)
            return val2;

        for(int i = 3 ; i <= n ; i++){
            result = val1 + val2;
            val1 = val2;
            val2 = result;
        }
        return result;
    }
}
