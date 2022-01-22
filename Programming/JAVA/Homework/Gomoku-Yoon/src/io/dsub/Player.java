package io.dsub;

import java.util.Scanner;

public class Player implements Inputtable {
    private String name;
    private int numWin;
    private Position pos;

    static final int INPUT_Q = Integer.MAX_VALUE;

    public Player(){
        this.name = null;
        numWin = 0;
        pos = new Position(INPUT_Q, INPUT_Q);
    }

    public Player(String name) {
        this.name = name;
        this.numWin = 0;
    }

    // todo: impl
    @Override
    public void getKeyboardInput() {
        Scanner sc = new Scanner(System.in);

        // Input Position
        System.out.print("좌표 입력(예, 1-2) : ");
        String input = sc.nextLine();

        // Input Q means Game Over
        if (input.equals("q")) {
            this.pos.setX(this.INPUT_Q);
            this.pos.setY(this.INPUT_Q);
            return;
        }

        // Input Position
        String[] strPos = input.split("-");
        pos.setX(Integer.parseInt(strPos[1]) - 1);
        pos.setY(Integer.parseInt(strPos[0]) - 1);

    }

    /**
     * GETTERS SETTERS
     * @return
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumWin() {
        return numWin;
    }

    public void setNumWin(int numWin) {
        this.numWin = numWin;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }
}
