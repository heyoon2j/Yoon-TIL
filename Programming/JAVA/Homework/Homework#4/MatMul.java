/**
 * 행렬의 곱 계산하기
 *
 * 두 행렬의 곱을 구하는 프로그램을 작성하시오.
 * 행렬의 곱을 계산한 후에 행렬 형태로 출력하시오.
 *
 *
 * 인자
 * matA: N x M 행렬
 * matB: M x K 행렬
 */

public class MatMul {
    public static void main(String[] args){
        int [][] matA = {{1, 2, 3},{4, 5, 2}};
        int [][] matB = {{5,2},{6,2},{1,0}};

        for(int i = 0; i < matA.length; i++){   // N
            for(int j = 0; j < matB[i].length; j++) { // K
                int sum = 0;
                for(int k = 0; k < matA[i].length; k++){    // M
                    sum += matA[i][k]*matB[k][j];
                }
                System.out.printf("%-3d",sum);
            }
            System.out.println();
        }
    }
}

// List 활용
//        List<Integer[]> listA = new ArrayList<Integer[]>();
//        List<Integer[]> listB = new ArrayList<Integer[]>();
//
//        for(int i = 0; i < matA.length; i++){
//            listA.add(Arrays.stream(matA[i]).boxed().toArray(Integer[]::new));
//        }
//        for(int i = 0; i < matA.length; i++){
//            int[] matBtoA = new int[matA[i].length];
//            for(int j = 0; j < matBtoA.length; j++) {
//                matBtoA[j] = matB[j][i];
//            }
//            listB.add(Arrays.stream(matBtoA).boxed().toArray(Integer[]::new));
//        }
//
//        for(int i = 0; i < listA.size(); i++){
//            for(int j = 0; j < listB.size(); j++){
//                int sum = 0;
//                for(int k = 0; k < listB.get(j).length; k++){
//                    sum += listA.get(i)[k]*listB.get(j)[k];
//                }
//                System.out.printf("%-3d",sum);
//            }
//            System.out.println();
//        }