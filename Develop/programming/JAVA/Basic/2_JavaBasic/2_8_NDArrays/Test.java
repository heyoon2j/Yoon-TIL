package com.company;

/**
 * N-D Array 연습
 */
public class Test {
    public static void main(String[] args){
        int[][] matA = {{1, 2, 3}, {3, 4, 5}};
        int[][] matB = {{3, 4, 5}, {1, 4, 2}};

        // 1. matA + matB를 구하고, 출력하시오.
        for(int i = 0; i< matA.length; i++){
            for(int j = 0; j < matA[i].length; j++){
                System.out.print(matA[i][j]+matB[i][j]+" ");
            }
            System.out.println();
        }


        // 2. matA를 Transpose하고 출력하시오.
        // 1 2 3 (0,0 0,1 0,2)
        // 4 5 6 (1,0 1,1 1,2)

        // 1 4 (0,0 1,0)
        // 2 5 (0,1 1,1)
        // 3 6 (0,2 1,2)
        for(int i = 0; i < matA[0].length; i++){    // 3
            for(int j = 0; j < matA.length; j++) {
                System.out.print(matA[j][i] + " ");
            }
            System.out.println();
        }
    }
}
