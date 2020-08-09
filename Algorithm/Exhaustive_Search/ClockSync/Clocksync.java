import java.util.Scanner;

public class Clocksync {
    public static final int[][] clockSwitch = new int[][]{
            {0, 1, 2},
            {3, 7, 9, 11},
            {4, 10, 14, 15},
            {0, 4, 5, 6, 7},
            {6, 7, 8, 10, 12},
            {0, 2, 14, 15},
            {3, 14, 15},
            {4, 5, 7, 14, 15},
            {1, 2, 3, 4, 5},
            {3, 4, 5, 9, 13}
    };

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        int testCount = 0;
        int[][] clockBlock = new int[4][4];
        int[] result;

        testCount = scanner.nextInt();
        scanner.nextLine();
        result = new int[testCount];

        for(int i = 0; i < testCount; i++) {

            for(int j = 0; j < clockBlock.length; j++){
                for(int k = 0; k < clockBlock[j].length; k++){
                    clockBlock[j][k] = scanner.nextInt();
                }
            }
            scanner.nextLine();

            result[i] = countingSwitch(clockBlock, 0);
        }

        for(int i = 0; i < testCount; i++)
            System.out.println(result[i]);
    }

    static int countingSwitch(int[][] clockBlock, int switchNumber){
       int minCount = -1;

        if(isAllTwelve(clockBlock)){    // 찾은 경우, 0 반환
            return 0;
        }

        if(switchNumber == 10) {
            return -1;          // 못찾은 경우, -1 반환
        }

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < clockSwitch[switchNumber].length; j++)
                moveClock(clockBlock, clockSwitch[switchNumber][j], i);

            int nextCount = countingSwitch(clockBlock, switchNumber + 1);
            //if(nextCount == -1)     // 못 찾아서 반환한 경우, 1) minCount == -1이면 그대로 / 2) minCount != -1  아닌 경우 에는 아무것도 안 해도 됨
            // minCount = -1;, 그대로(할 거 없음)

            // nextCount == 0, 맨 마지막에 찾은 경우 => count = nextCount(0) + i; 초기화
            // nextCount >= 0, i + nextCount가 가장 낮은 거를 찾아야 한다. 찾아서 가장 적은 횟수를 반환한 경우 => count = nextCount + i;
            if(nextCount != -1) {
                if (minCount == -1 || (minCount > nextCount + i))
                    minCount = nextCount + i;
           }
            for(int j = 0; j < clockSwitch[switchNumber].length; j++)
                reverseMoveClock(clockBlock, clockSwitch[switchNumber][j], i);
        }
        return minCount;
    }

    static boolean isAllTwelve(int[][] clockBlock){
        for(int i = 0; i < clockBlock.length; i++){
            for(int j = 0; j < clockBlock[i].length; j++){
                if(clockBlock[i][j] != 12){
                    return false;
                }
            }
        }
        return true;
    }

    static void moveClock(int[][] clockBlock, int clockNumber, int count){
        int row = 0;
        int column = 0;
        boolean check = false;

        for(int i = 0; i < clockBlock.length; i++){
            for(int j = 0; j < clockBlock[i].length; j++){
                if(4*i + j == clockNumber){
                    row = i;
                    column = j;
                    check = true;
                }
            }
            if(check)
                break;
        }

        for(int i = 0; i < count; i++){
            clockBlock[row][column] += 3;
            if(clockBlock[row][column] == 15)
                clockBlock[row][column] = 3;
        }
    }

    static void reverseMoveClock(int[][] clockBlock, int clockNumber, int count){
        int row = 0;
        int column = 0;
        boolean check = false;

        for(int i = 0; i < clockBlock.length; i++){
            for(int j = 0; j < clockBlock[i].length; j++){
                if(4*i + j == clockNumber){
                    row = i;
                    column = j;
                    check = true;
                }
            }
            if(check)
                break;
        }
        for(int i = 0; i < count; i++){
            clockBlock[row][column] -= 3;
            if(clockBlock[row][column] == 0)
                clockBlock[row][column] = 12;
        }
    }
}