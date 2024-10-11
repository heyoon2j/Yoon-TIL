import java.util.Scanner;

public class Boardcover {
    static final int[][][] checkPoints = new int[][][]{
            {{0, 0},{1, 0},{1, -1}},
            {{0, 0},{0, 1},{1, 1}},
            {{0, 0},{0, 1},{1, 0}},
            {{0, 0},{1, 0},{1, 1}},
    };

    public static void main(String[] args){
         int testCase = 0;
         int row = 0;
         int column = 0;
         int[] result = null;
         char[][] board = null;
         boolean[][]  areBlack= null;

        Scanner scanner = new Scanner(System.in);

        testCase = scanner.nextInt();
        scanner.nextLine();
        result = new int[testCase];

        for(int i = 0; i < testCase; i++){
            row = scanner.nextInt();
            column = scanner.nextInt();
            scanner.nextLine();

            board = new char[row][];
            for(int j = 0; j < row; j++){
                board[j]= scanner.nextLine().toCharArray();
            }
            areBlack = new boolean[row][column];

            result[i] += countCover(board, 0, 0);

        }
        for(int i = 0; i < testCase; i++)
            System.out.println(result[i]);

    }

    public static int countCover(char[][] board, int row, int column){

        int count = 0;
        boolean checkPoint = false;
        boolean initialPoint = false;

        // 가장 빠른 . 찾기
        for(int i = row; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(!initialPoint){
                    initialPoint = true;
                    j = column;
                }

                if(board[i][j] == '.') {
                    row = i;
                    column = j;
                    checkPoint = true;
                    break;
                }
            }
            if(checkPoint)
                break;
        }

        if(checkBoard(board))
            return 1;


        for(int i = 0; i < checkPoints.length; i++){
            boolean isError = false;
            for(int j = 0; j < checkPoints[i].length; j++){
                int rowPoint = checkPoints[i][j][0];
                int columnPoint = checkPoints[i][j][1];

                if(row + rowPoint < 0 || column + columnPoint < 0 || row + rowPoint >= board.length || column + columnPoint >= board[0].length) {    // IndexError
                    isError = true;
                    break;
                }

                if( board[row + rowPoint][column + columnPoint] == '#'){
                    isError = true;
                    break;
                }
            }

            if(!isError){
                for(int j =0; j < checkPoints[i].length; j++) {
                    board[row + checkPoints[i][j][0]][column + checkPoints[i][j][1]] ='#';
                }

                //System.out.println(row+","+column);
                //showBoard(board);
                count += (column == board[0].length - 1)?countCover(board, row + 1, 0):countCover(board, row, column + 1);

                for(int j =0; j < checkPoints[i].length; j++) {
                    board[row + checkPoints[i][j][0]][column + checkPoints[i][j][1]] ='.';
                }
            }
        }

        return count;
    }

    static boolean checkBoard(char[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == '.')
                    return false;
            }
        }
        return true;
    }
//    static  void showBoard(char[][] board){
//        for(int i = 0; i < board.length; i++){
//            for(int j = 0; j < board[i].length; j++){
//                System.out.print(board[i][j]);
//            }
//            System.out.println("");
//        }
//
//    }

}
