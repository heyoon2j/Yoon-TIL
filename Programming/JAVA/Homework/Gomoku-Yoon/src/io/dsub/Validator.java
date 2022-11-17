package io.dsub;

public class Validator {
    public static char[] sample1 = new char[]{' ','x', 'x','x',' '};
    public static char[] sample2 = new char[]{' ','o', 'o', 'o', ' '};
    private static int[][] posOfType = {
            {1, 1}, {1, -1}, {1, 0}, {0, 1}
    };
    private static int[][] posOf5Type = {
            {-1, 0},{-1, -1},{0, -1},{1, -1}
    };


    private Validator(){}
    public static boolean validate(char[][] board, Position pos) {
        return isValidPosition(pos) && !isAlreadyInPos(board, pos);
    }

    private static boolean isAlreadyInPos(char[][] board, Position pos) {
        return board[pos.getX()][pos.getY()] != ' ';
    }

    private static boolean isValidPosition(Position pos) {
        return chkRange(pos.getX()) && chkRange(pos.getY());
    }

    private static boolean chkRange(int num) {
        return num > -1 && num < 15;
    }

    private static boolean isLineThree(char[][] board, Position pos, char target, int type) {

        int x = posOfType[type][0];
        int y = posOfType[type][1];
        int pivotX = pos.getX();
        int pivotY = pos.getY();
        char[] sample = (target == 'x')?sample1:sample2;

        // 3개 찾기
        for(int i = 0; i < 3; i++){
            boolean chkSample = true;
            pivotX = pos.getX();
            pivotY = pos.getY();

            for(int j = 0; j < i+1; j++){   // Reverse, Pivot Setting
                pivotX -= x;
                pivotY -= y;
            }

            if(!chkRange(pivotX) || !chkRange(pivotY)){
                continue;
            }

            for(int j = 0; j < 5; j++) {
                if(board[pivotX][pivotY] != sample[j]) {
                    chkSample = false;
                    break;
                }
                pivotX += x;
                pivotY += y;
            }

            if(chkSample){
                return true;
            }
        }

        return false;
    }
    public static boolean chkThreeByThree(char[][] board, Position pos, char target, int type) {
        boolean chkTemp = false;
        int exceptType = -1;
        for(int i = 0; i < 4; i++){

            if(isLineThree(board, pos, target, i)){
                exceptType = i;
                break;
            }
        }

        if(exceptType == -1)
            return false;

        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (j == exceptType)
                    continue;

                // pivot setting
                for(int k = 0; k < 2; k++) {
                    if(isLineThree(board, new Position(pos.getX()-(posOfType[exceptType][0]*k),pos.getY()-(posOfType[exceptType][1]*k)), target, j))
                        return true;
                }
                if(isLineThree(board, new Position(pos.getX(),pos.getY()), target, j))
                    return true;

                for(int k = 0; k <2; k++){
                    if(isLineThree(board, new Position(pos.getX()+(posOfType[exceptType][0]*k),pos.getY()+(posOfType[exceptType][1]*k)), target, j))
                        return true;
                }

            }
        }
        return false;
    }


    private static boolean isTarget(char[][] board, int x, int y, char target){

        if(!chkRange(x) || !chkRange(y)){
            return false;
        }

        if(board[x][y] != target) {
            return false;
        }

        return true;
    }

    public static boolean chkFive(char[][] board, Position pos, char target){
        boolean isFive = true;
        int count = 0;

        for(int i = 0; i < 4; i++){         // posOf5Type
            for(int j = 0; j < 5; j++) {    // Move Pivot
                isFive = true;

                // check target;
                for(int k = 4-j; k > 0; k--){
                    if(!isTarget(board,pos.getX()+k*posOf5Type[i][0],pos.getY()+k*posOf5Type[i][1], target)) {
                        isFive = false;
                        break;
                    }
                }
                // check break;
                if(!isFive)
                    break;

                if(!isTarget(board, pos.getX(), pos.getY(), target)) {
                    isFive = false;
                    break;
                }

                for(int k = 0; k < j; k++){
                    if(!isTarget(board,pos.getX()-(k+1)*posOf5Type[i][0],pos.getY()-(k+1)*posOf5Type[i][1],target)){
                        isFive = false;
                        break;
                    }
                }

                if(isFive)
                    return true;
            }
        }
        return false;
    }
}
