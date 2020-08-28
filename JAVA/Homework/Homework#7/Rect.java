import java.util.Arrays;

/**
 * 아래 테스트 코드가 정상 동작하도록 클래스들을 완성하시오.
 *
 * getArea(): 사각형의 넓이를 반환한다.
 * getCenterOfMass(): 사각형의 질량중심을 반환한다.
 * GetAllPoints(): 사각형의 네 점을 배열로 반환한다.
 * rot90(): Pivot을 기준으로 사각형을 90도 회전시킨다.
 */

class Vector2D {
    public float x, y;

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        // write codes here
        //return "(VECTOR2D)"
        return "("+this.x+","+ this.y +")";
    }
}

class RectCore {
    protected Vector2D pos;
    protected float w, h;

    public RectCore(float x, float y, float w, float h) {
        this.pos = new Vector2D(x, y);
        this.w = w;
        this.h = h;
    }

    public String toString() {
        // write codes here
        return "RECTCORE";
    }
}

public class Rect extends RectCore {
    public Rect(float x, float y, float w, float h) {
        super(x, y, w, h);
    }

    public float getArea() {
        // write codes here
        return this.w * this.h;
    }

    public Vector2D getCenterOfMass() {
        // write codes here
        return new Vector2D( this.pos.x + w/2, this.pos.y + h/2);
    }

    public Vector2D [] getAllPoints() {
        // write codes here
        Vector2D v1 = new Vector2D(this.pos.x, this.pos.y);
        Vector2D v2 = new Vector2D(this.pos.x+w, this.pos.y);
        Vector2D v3 = new Vector2D(this.pos.x, this.pos.y+h);
        Vector2D v4 = new Vector2D(this.pos.x+w, this.pos.y+h);
        return new Vector2D[]{v1, v2, v3, v4};
    }

    public void rot90(Vector2D pivot) {
        // write codes here
        float a = 0;
        float b = 0;

        // (0,0) 기준 점으로 변경
        a = this.pos.x - pivot.x;
        b = this.pos.y - pivot.y;

        // (0,0) 기준 으로 90도 회전
        float temp = a;
        a = b;
        b = -1*temp;

        // 다시 pivot으로 기준 점 변경
        a = a + pivot.x;
        b = b + pivot.y;

        this.pos.x = a;
        this.pos.y = b;
    }

    float func(float x){
        int variable = 3;
        return variable;
    }

    public String toString() {
        // write codes here
//        return "RECT";
        return "("+this.pos.x+","+ this.pos.y +")";
    }
}

class RectTest {
    public static void main(String[] args) {
        Rect rect = new Rect(0.5f, 0.7f, 1.5f, 2.3f);
        System.out.println("Area: " + rect.getArea());
        System.out.println("CoM: " + rect.getCenterOfMass());
        System.out.println("All Points: " + Arrays.toString(rect.getAllPoints()));

        rect.rot90(new Vector2D(0.4f, 0.2f));
        System.out.println("Rotated rect: " + rect);
    }
}