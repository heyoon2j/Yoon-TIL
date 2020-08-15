package io.dsub;

import java.util.Arrays;

public class Gomoku implements Simulatable, Winnable, Playable, Printable {

    /**
     * props
     */
    private Player player1;
    private Player player2;
    private Player curPlayer;
    private Player startPlayer;
    private char[][] board;

    /**
     * Constructors
     * @param player1
     * @param player2
     */
    public Gomoku(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.curPlayer = null;
    }
    public void run(){
        this.curPlayer = player1;
        this.startPlayer = player1;

        do{
            // Current Player typing keyboard
            curPlayer.getKeyboardInput();

            // curPlayer typing 'q'
            if(curPlayer.getPos().getX() == Player.INPUT_Q){
                break;
            }

            // Check Position
            if(!Validator.validate(this.board, curPlayer.getPos())){
                System.out.println("Invalid Input! Try Again!");
                continue;
            }

            // Put stone on the board
            this.play(curPlayer, curPlayer.getPos());

            // Check 5 stone or 3 by 3
            Player winnerPlayer = null;
            if( (winnerPlayer = this.getWinner()) != null){           // if not null, this game ends and restart.
                winnerPlayer.setNumWin(winnerPlayer.getNumWin()+1);
                System.out.println(winnerPlayer.getNumWin()+"번 이겼다!");
                this.isFinished();
                startPlayer = (startPlayer == player1)?player2:player1;
                curPlayer = startPlayer;
                continue;
            }

            // Change Player
            curPlayer = (curPlayer == player1)?player2:player1;

        }while(true);

        System.out.println(player1.getName()+" "+player1.getNumWin()+" - "+player2.getNumWin()+" "+player2.getName());
    }


    @Override
    public void play(Player player, Position pos) {
        char inType = player == player1 ? 'x' : 'o';
        this.board[pos.getX()][pos.getY()] = inType;
        this.printStatus();
    }

    @Override
    public void printStatus() {
        for(char[] ch : this.board){
            System.out.println(Arrays.toString(ch));
        }
    }

    @Override
    public void initialize() {
        this.board = new char[15][15];
        this.reset();
    }

    @Override
    public void isFinished() {
        this.reset();
    }

    @Override
    public void reset() {
        for (char[] chars : board) {
            Arrays.fill(chars, ' ');
        }
    }

    @Override
    public Player getWinner() {

        // Check 5 stone
        if(Validator.chkFive(this.board, curPlayer.getPos(), (curPlayer == player1)?'x':'o')) {
            System.out.println("Input 5 stone!");
            return curPlayer;
        }

        // Check 3 by 3
        if(Validator.chkThreeByThree(this.board, curPlayer.getPos(), (curPlayer == player1)?'x':'o', 0)){
            System.out.println("Input 3 by 3!");
            return (curPlayer == player1)?player2:player1;
        }

        return null;
    }


    /**
     * GETTERS AND SETTERS
     */
    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getCurPlayer() {
        return curPlayer;
    }

    public void setCurPlayer(Player curPlayer) {
        this.curPlayer = curPlayer;
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }
}
