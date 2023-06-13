# Code convention

## Code Style
* Camel Case
    ```java
    int studentNumber = 0
    String phonNumber = "010-1111-2222"
    ```java
* Pascal Case
    ```java
    class Studnet() {}
    class CloudProvider() {}
    ```
* Snake Case
    ```
    int student_number = 0
    static final int RIGHT_ANGLE = 90
    ```
    * 보통 대문자인 경우, 상수에 많이 사용
    * 소문자인 경우, 데이터베이스 필드 이름에 많이 사용

* Kebab Case
    ```
    http://test.com/basic-image-1
    ```
    * URL에 많이 사용


* 'l'(소문자 엘), ''O"(대문자 오), ''I'(대문자 아이)는 단일 문자 변수명으로 결코 쓰지마라.
* 모듈은 짧은 소문자로만 구성된 이름을 가져야 한다. 가독성을 개선할 수 있다면 밑줄을 사용할 수 있다. 파이썬 패키지 또한 짧고 소문자로만 구성된 이름을 가져야 한다. 비록 밑줄의 사용은 찬성하지 않지만.
* Getter/Setter : Getter, Setter 메소드는 반드시 'get + 멤버변수 이름', 'set + 멤버변수 이름' 형식으로 작성한다. 단, Getter 메소드의 반환값이 Boolean 인 경우 get 대신 is 조합을 사용



