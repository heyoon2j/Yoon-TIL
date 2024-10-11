import java.util.Scanner;

/**
 * 제한 사항
 * - 환자의 ID는 1 이상 999999 이하의 정수로 입력된다고 가정한다.
 * - 환자의 병명은 5자 이하의 문자열로 입력되며, 좌측 정렬하여 출력한다.
 * - 비고 사항은 10자 이하의 문자열로 입력되며, 좌측 정렬하여 출력한다.
 *
 *  출력 포맷
 *  *----------*--------*---------------*
 *  | ID004029 | 코로나19 | 자가 격리 조치   |
 *  *----------*--------*---------------*
 */


public class PrintFormat {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("환자 ID 입력 : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // 개행 문자 제거
        System.out.print("환자 병명 : ");
        String s1 = scanner.nextLine();
        System.out.print("비 고 : ");
        String s2 = scanner.nextLine();

        System.out.println("*----------*--------*---------------*");
        System.out.printf("| ID%06d | %-5s | %-10s |\n", id, s1, s2 );
        System.out.println("*----------*--------*---------------*");

    }
}
