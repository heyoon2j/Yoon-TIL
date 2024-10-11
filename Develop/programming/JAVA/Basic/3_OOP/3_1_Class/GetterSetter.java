package com.company;

/**
 * Getter, Setter
 * 맴버 변수를 간접적으로 다룰 수 있게 해주는 메소드
 * 맴버 변수의 캡슐화(Encapsulation)에 도움이 됨
 *  -> 정보의 은닉/보호
 * 맴버 변수의 값을 제한해야 할 때 유용
 */

public class GetterSetter {
    int x, y;
    String z;
    float w;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getZ() {
        return z;
    }

    public float getW() {
        return w;
    }

    public void setX(int x) {
        if(x > 0 && x <= 100)
            this.x = x;
        else
            System.out.println("0 < x <= 100, x is over Range");
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(String z) {
        this.z = z;
    }

    public void setW(float w) {
        this.w = w;
    }
}

class Test{
    public static void main(String[] args) {
        GetterSetter m = new GetterSetter();

        m.x = 10;
        System.out.println(m.x);

        m.setX(20);
        System.out.println(m.getX());

        m.setX(111111111);

    }

}
