package com.company;

/**
 * N-D 배열 (N-D Arrays)
 * 배열이 배열을 담고 있으면, 다차원 배열이라 한다. (N-D Array)
 * 수학에서 말하는 점 -> 선 -> 면 -> 공간 -> 4차원 -> 5차원 -> ...
 *
 */
public class Main {

    public static void main(String[] args) {
	// write your code here
        int[][] ints;
        int[] halfCStyle[]; // Hybrid
        int cStyle[][]; // old...

        int[][] ints1 = new int[10][5];    // 앞에 있는 숫자가 더 큰 차원 숫자
        int[][] ints2 = new int[10][];
        for(int i = 0; i <ints2.length; i++){
            ints2[i] = new int[ints1[0].length];
        }

        int[][] ints3 = new int[5][];
        ints3[0] = new int[10];
        ints3[1] = new int[5];

        // 다차원 배열 초기화
        int[][] ints4 = {{1, 2, 3}, {4, 5, 6}}; // int [2][3]
        int[][] ints5 = {{1, 3, 2}, {1, 2}, {4, 5, 6}}; // int [3][]
        for(int i = 0; i < ints5.length; i++){
            System.out.printf("[%d] : ",ints5[i].length);
            for(int j = 0; j < ints5[i].length; j++)
                System.out.printf("%d ",ints5[i][j]);
            System.out.println();

        }

        for(int[] array : ints5){
            System.out.printf("[%d] : ",array.length);
            for(int val : array)
                System.out.printf("%d ",val);
            System.out.println();
        }






    }
}
