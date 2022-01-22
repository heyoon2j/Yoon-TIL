import java.util.Scanner;

/**
 * 입력받아 처리하기
 *
 * 3개의 정수를 입력받아, 그 중 최대값을 출력하고자 한다.
 * 이를 수행하는 프로그램을 작성하시오.
 *
 */

public class InputOutput {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int z = scanner.nextInt();

        int temp = 0;

        temp = (x > y)? x : y;
        temp = (temp > z)? temp : z;

        System.out.println("최대 값 : "+temp);

    }
}
