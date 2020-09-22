/**
 * 람다식을 이용하여 알고리즘 정답을 테스트하는 프로그램을 작성하시오.
 *
 * 주어진 interface와 실행 메소드를 이용하여 알고리즘 정답을 테스트하는 프로그램을 작성하시오.
 * 이 테스트 방식을 이용하여 실제 알고리즘 문제를 하나 이상 풀이하시오.
 *
 */

interface Solution<T, R> {
    R[] solve(T[] t);
}

class Algorithm<T, R> {
    boolean test(T[] input, R[] groundtruth, Solution<? super T, ? super R> solution) {
        // TODO: solution을 실행하고, 이것이 정답(groundtruth)와 일치하는지 테스트하여 일치 여부를 출력.

        Object[] answer = solution.solve(input);
        for(int i = 0; i < input.length; i++){
            if(!answer[i].equals(groundtruth[i]))
                return false;
        }
        return true;
    }
}

public class LambdaExpressions {
    public static void main(String[] args) {
        // TODO: test를 이용하여 알고리즘 문제를 하나 이상 풀이하고 테스트 결과를 출력하시오.
        Algorithm<Integer, Integer> algorithm = new Algorithm<>();
        Integer[] input = {1,2,3,2,3};
        Integer[] result = {4,3,1,1,0};

        System.out.println("Test Result : " + algorithm.test(input, result,(Integer[] prices) -> {
            Integer[] answer = new Integer[prices.length];

            for(int i = 0; i < prices.length-1; i++){
                int time = 1;

                for(int j = i+1; j < prices.length-1; j++){
                    if( prices[i] <= prices[j]){
                        time++;
                    }else{
                        break;
                    }
                }

                answer[i] = time;
            }
            answer[prices.length - 1] = 0;
            return answer;
        }));
    }
}
