/**
 * 3차원 벡터 클래스 구현하기
 *
 * 3차원 벡터를 클래스로 구현하시오.
 *
 * 속성
 * float타입의 x, y, z
 *
 * 생성자
 * Vector3D(float x, float y, float z)
 *
 * 메소드
 * - x, y, z에 대한 getter 및 setter
 * - add(): 벡터끼리의 덧셈, 실수와의 덧셈을 반환
 * - sub(): 벡터끼리의 뺄셈, 실수와의 뺄셈을 반환
 * - inner(): 두 벡터의 내적을 반환
 * - mul(): 실수와의 곱을 반환
 * - mag(): 벡터의 크기를 반환
 * - print(): 벡터의 내용을 "(%.3f, %.3f, %.3f)\n" 형식으로 콘솔에 출력
 */

public class Vector3D {
    float x;
    float y;
    float z;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public Vector3D(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // add function overloading
    Vector3D add(Vector3D vector){
        return new Vector3D(this.x + vector.x, this.y + vector.y, this.z + vector.z);
    }
    Vector3D add(float num){
        return new Vector3D(this.x + num, this.y + num, this.z + num);
    }

    // sub function overloading
    Vector3D sub(Vector3D vector){
        return new Vector3D(this.x - vector.x, this.y - vector.y, this.z - vector.z);
    }
    Vector3D sub(float num){
        return new Vector3D(this.x - num, this.y - num, this.z - num);
    }

    // inner function
    double inner(Vector3D vector){
        return this.x*vector.x + this.y*vector.y + this.z*vector.z;
    }

    // mult function
    Vector3D mult(float num){
        return new Vector3D(this.x * num, this.y * num, this.z * num);
    }

    double mag(){
        return Math.sqrt(x*x + y*y + z*z);
    }

    void print(){
        System.out.printf("%.3f, %.3f, %.3f\n", x, y, z);
    }

}

class Vector3DTest {
    public static void main(String[] args) {
        Vector3D v1 = new Vector3D(0.5f, 0.2f, 0.9f);
        Vector3D v2 = new Vector3D(0.8f, 0.1f, 1.3f);

        v1.add(v2).print();
        v2.sub(v1).print();
        v1.add(0.2f).print();
        v2.sub(0.05f).print();
        System.out.println(v1.inner(v2));
        v1.mult(1.2f).print();
        System.out.println(v2.mag());
    }
}