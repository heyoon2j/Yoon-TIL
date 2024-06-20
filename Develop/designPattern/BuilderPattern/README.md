# Builder Pattern
* 사용하는 이유
    1) 인스턴스 변수의 무결성을 위해서
    2) 인스턴스 변수 증가시, 생성을 위한 코드 수정 불필요

* 기존 방식 : 생성자 or Setter를 이용하여 인스턴스 생성 시생성 시
    ```java
    class Student {
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
    class Student {
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
    * 생성자를 이용하는 경우, 인스턴스 변수가 증가할 때마다 생성자 코드와 생성하는 위치에서 코드를 수정해줘야 한다.
    * Setter를 이용하는 경우(자바빈즈 패턴), Setter를 인스턴스 변수만큼 실행해야 한다. 그리고 자칫 Setter의 많은 사용으로 무결성을 해칠 수 있다.
* Builder Pattern 방식
    ```java
    class Student {
        private String name;
        private String major;    

        public Student(Builder builder) {
            this.name = builder.name;
            this.major = builder.name;
        }

        public static class Builder {
            private String name = "";
            private String major = "";    

            public Builder() {}

            public Builder setName(String name) {
                this.name = name;
                return this;
            }

            public Builder setMajor(String major) {
                this.major = major;
                return this;
            }

            public Student build() {
                return new Student(this);
            }
        }
    }

    public static int main() {
        Student a = new Student.Builder()
                                .setName("yon")
                                .build();

        Student b = new Student.Builder()
                                .setName("zon")
                                .setMajor("pysiclal")
                                .build();
    }   
    ```

> 결론 : 생성 시 변경이 없는 클래스인 경우, Builder Pattern을 사용하자. Python은 Keyword Argument가 있기 때문에 생성자 코드만은로도 괜찮지 않나 싶다.