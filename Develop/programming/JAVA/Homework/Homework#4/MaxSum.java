/**
 * 배열의 연속합 최대 구하기
 *
 * 정수 배열에서 연속된 값의 합의 최대값을 구하시오.
 *
 * ex1) {1, 45, -2, 5, -6} => 1 + 45 + (-2) + 5 = 49
 * ex2) {-4, 5, 12, -7, 52, -5, 7} => 52
 *
 *
 * 인자
 * integers: 정수 배열
 */
public class MaxSum {
    public static void main(String[] args){
        int [] integers = {-4, 7, 14, 9, -5, 4, 16, -22, 31}; // 54
        //int [] integers = {-4, 5, 12, -7, 52, -5, 7};  // 64
        //int [] integers = {1, 45, -2, 5, -6};    // 49

        int sum = 0;
        int maxSum = 0;

        // O(n^2)
        maxSum = integers[0];

        for(int i = 0; i < integers.length; i++){
            sum = integers[i];
            for(int j = i+1; j < integers.length; j++) {
                sum += integers[j];

                if(sum > maxSum)
                    maxSum = sum;
            }
        }

        // O(n^3)
//        for(int i = 0; i < integers.length; i++){
//            int l = integers.length;
//            for(int j = i; j < integers.length; j++) {
//                sum = integers[i];
//
//                for (int k = i + 1; k < l; k++)
//                    sum += integers[k];
//
//                if (sum > maxSum)
//                    maxSum = sum;
//                l--;
//            }
//        }

        System.out.println("MaxSum : "+maxSum);
    }
}
