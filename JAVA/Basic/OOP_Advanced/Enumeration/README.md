# 열거형 (Enumeration)
* ```enum``` 키워드로 표현
* __enum -> java.lang.enum__ 클래스를 상속
* enum은 다른 클래스를 상속하지 못함(이미 상속하고 있음)
* 열거형은 다른 클래스를 상속하지 못하지만, 인터페이스 구현은 가능
* 내부적으로 클래스로 동작
* 열거형 타입에는 열거형 상수와 null 값 할당 가능
* public과 default만 사용 가능
### 열거형 구현
* 기본 구현
    * 열거형 상수는 객체이다.
```java
enum Job {  // 각 상수는 0부터 숫자를 가지지만, Symbol로만 사용하고 숫자는 사용하지 않는다.
    STUDENT, MARKETING, DEVELOPER, CHIEF_EXECUTIONAL_OFFICER; // 열거형
}

class Foo{
    enum Symbol{
        ONE, TWO, THREE;
    }
}

class Test{
    public static void main(Strin[] args){
        System.out.println(Job.STUDENT);
        System.out.println(Foo.Symbol.ONE);   
    }
}
```
* 열거형 내부에 메소드 구현
```java
enum Symbol {
    ONE, TWO, THREE, FOUR;

    public Symbol nextSymbol() {
        if (this.equals(ONE)) {
            return TWO;
        } else if (this.equals(TWO)) {
            return THREE;
        } else if (this.equals(THREE)) {
            return FOUR;
        }else{
            return ONE;
        }
    }
}
```
* 열거형 생성자를 이용한 enum 상수 초기화
    * enum 생성자는 __private__이다.
```java
enum Family {
    FATHER("아버지"), MOTHER("어머니"), SON("아들"), DAUGHTER("딸");
    private String koreanWord;

    // private는 생략 가능(public 불가, 자동으로 private)
    private Family(String koreanWord){
        this.koreanWord = koreanWord;
    }
    public String getKoreanWord() {
        return koreanWord;
    }
    public void setKoreanWord(String koreanWord) {
        this.koreanWord = koreanWord;
    }
}

class Test{
    public static void main(String[] args){
        Family farm = Family.SON;
        System.out.println(farm.getKoreanWord());
        farm.setKoreanWord("버린 자식");
        System.out.println(farm.getKoreanWord());
    }
}
```
