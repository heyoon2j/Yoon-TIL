package io.dsub;

public class Player implements Inputtable {
    private String name;
    private int numWin;

    public Player(String name) {
        this.name = name;
        this.numWin = 0;
    }

    // todo: impl
    @Override
    public void getKeyboardInput() {

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
}
