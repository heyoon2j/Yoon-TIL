package io.dsub;

import java.util.Arrays;

public class Gomoku implements Simulatable, Winnable, Playable, Printable {

    /**
     * props
     */
    private Player player1;
    private Player player2;
    private char[][] board;

    /**
     * Constructors
     * @param player1
     * @param player2
     */
    public Gomoku(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
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

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }
}
