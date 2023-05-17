# Builder Pattern
* 사용하는 이유
    1) 인스턴스 변수의 무결성을 위해서
    2) 인스턴스 변수 증가시, 생성을 위한 코드 수정 불필요

* 기존 방식 : 생성자 or Setter를 이용하여 인스턴스 생성 시생성 시
    ```java
    class Student() {
        private String name;

        public Student() {
            this.name = null;
        }

        public Student(String name) {
            this.name = name;
        }    

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static int main() {
        Student a = new Student("yon");
        Student b = new Student();
        b.setter("zon")

    }

    // 인스턴스 변수 추가
    /*
    class Student() {
        private String name;
        private String major;
   

        public Student(String name, String major) {
            this.name = name;
            this.major = major;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMajor() {
            return this.major;
        }

        public void setMajor(String major) {
            this.major = major;
        }
    }

    public static int main() {
        Student a = new Student("yon","computer");
        Student b = new Student();
        b.setName("zon")
        b.setMajor("pysical")
    }    
    */
    ```
    * 생성자를 이용하는 경우, 인스턴스 변수가 증가할 때마다 생성자 코드와 생성한는 위치에서 코드를 수정해줘야 한다.
    * Setter를 이용하는 경우, Getter/Setter함수를 추가해줘야 하며, 자칫 다른곳에서 쓰일 수 있기 때문에 무결성을 해칠 수 있다.
* Builder Pattern 방식
    ```java

    ```

> 결론 : 생성 시 변경이 없는 클래스인 경우, Builder Patter을 사용하자. Python은 Keyword Argument가 있기 때문에 생성자 코드만은로도 괜찮지 않나 싶다.