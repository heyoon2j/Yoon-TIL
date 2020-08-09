import java.util.Scanner;

/**
 * https://algospot.com/judge/problem/read/BOGGLE
 * "BOGGLE Game"
 * Using Recursive Function
 *
 */

/*
Example>
1
URLPM
XPRET
GIAET
XTNZY
XOQRS
6
PRETTY
GIRL
REPEAT
KARA
PANDORA
GIAZAPX

Example Result>
PRETTY YES
GIRL YES
REPEAT YES
KARA NO
PANDORA NO
GIAZAPX YES
 */

public class boggle {
    public static void main(String[] args){

        char[][] test = new char[5][5];
        char[][] findWord;
        Scanner scanner = new Scanner(System.in);
        int findWordCount;
        int testCase;

        testCase = scanner.nextInt();
        scanner.nextLine();

        for(int i = 0; i < testCase; i++) {
            //System.out.println("예제를 입력 하시오.");
            test[0] = scanner.nextLine().toCharArray();
            test[1] = scanner.nextLine().toCharArray();
            test[2] = scanner.nextLine().toCharArray();
            test[3] = scanner.nextLine().toCharArray();
            test[4] = scanner.nextLine().toCharArray();

            findWordCount = scanner.nextInt();
            scanner.nextLine();
            findWord = new char[findWordCount][10];

            //System.out.println("찾을 단어를 입력 하시오.");
            for (int j = 0; j < findWordCount; j++) {
                findWord[j] = scanner.nextLine().toCharArray();
            }

            for (int j = 0; j < findWordCount; j++)
                searchingWord(test, findWord[j]);
        }
    }

    static void printResult(Boolean result, char[] word){
        if (result) {
            System.out.println(String.valueOf(word) + " Yes");
        } else {
            System.out.println(String.valueOf(word) + " No");
        }
    }

    static void searchingWord(char[][] test, char[] findWord){
        int index = 0;
        for(int i = 0; i < findWord.length; i++){
            if(findWord[i] != 0)
                index++;
        }

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(findNextWord(i, j, index-1, findWord, test)) {
                    printResult(true, findWord);
                    return;
                }
            }
        }

        printResult(false, findWord);
    }
    static Boolean findNextWord(int i, int j, int index, char[] findWord, char[][] test){

        final int[] dx = {-1, -1, -1, 1, 1, 1, 0, 0}; // UL, L, DL, UR, R, DR, U, D
        final int[] dy = {-1, 0, 1, -1, 0, 1, -1, 1};

        if(i == -1 || j == -1 || i == 5 || j == 5)  // Range 를 벗어난 경우
            return false;

        //System.out.printf("i=%d / j=%d / index=%d / Word=%c / testWord=%c \n",i,j,index,findWord[index], test[i][j]);
        if(test[i][j] != findWord[index])   // 맞지 않는 경우
            return  false;

        // 맞는 경우
        if(index == 0) { // 하나 남은 경우, Recursive 필요가 없는 경우
            //System.out.printf("i=%d / j=%d / index=%d / Word=%c\n",i,j,index,findWord[index]);
            return true;
        }

        for(int k = 0; k < 8; k++){
            //System.out.printf("i=%d / j=%d / index=%d / Word=%c\n",i,j,index,findWord[index]);
            if(findNextWord(i+dx[k], j+dy[k], index-1, findWord, test))
                return true;
        }
        return false;
    }
}
