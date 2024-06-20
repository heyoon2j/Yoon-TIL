package s03;

/**
 * 열거형 (Enumeration)
 * enum 키워드로 표현
 * enum -> java.lang.Enum 클래스를 상속
 * enum은 다른 클래스를 상속하지 못함(이미 상속하고 있음)
 *
 * 열거형은 다른 클래스를 상속하지 못하지만, 인터페이스 구현은 가능
 * 열거형 타입에는 열거형 상수와 null 값 할당 가능
 *
 * 내부적으로는 클래스로 동작한다.
 */

enum Job {  // 각 상수는 0부터 숫자를 가지지만, Symbol로만 사용하고 숫자는 사용하지 않는다.
    STUDENT, MARKETING, DEVELOPER, CHIEF_EXECUTIONAL_OFFICER; // 열거형
}

class Foo{
    enum Symbol{
        ONE, TWO, THREE;
    }
}

// 열거형 내부에서 메소드 구현
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

// 열거형 생성자를 이용한 enum 상수 초기화
enum Family {
    FATHER("아버지"), MOTHER("어머니"), SON("아들"), DAUGHTER("딸");
    private String koreanWord;  // 맴버 변수(객체에 속하는 변수)

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



public class Enumeration {
    public static void main(String[] args) {

        Job job = Job.STUDENT;  // 클래스 변수와 유사하게 사용

        if(job == Job.CHIEF_EXECUTIONAL_OFFICER){
            System.out.println("사장님 안녕하세요?");
        }

        char c = 'A';
        switch (c){
            case 'A':
                break;
            case 'B':
                break;
            case 'C':
                break;
        }

        switch(job){    // switch ~ case 문에는 열거형 자료형을 생략
            case STUDENT:
                System.out.println("you will be a great one");
                break;
            case DEVELOPER:
                System.out.println("you sells");
                break;
            case MARKETING:
                System.out.println("you make things");
                break;
            case CHIEF_EXECUTIONAL_OFFICER:
                System.out.println("you choose");
                break;
            default:
                System.out.println("who are you?");
        }

        //
        System.out.println(Foo.Symbol.ONE);

        // 열거형 메소드
        Symbol sym = Symbol.ONE;    // 열거형 상수는 객체이다.
        Symbol nextSym = sym.nextSymbol();
        System.out.println(nextSym);
        nextSym = nextSym.nextSymbol();
        System.out.println(nextSym);


        // 열거형 생성자와 맴버 변수 활용
        Family farm = Family.SON;
        System.out.println(farm.getKoreanWord());
        farm.setKoreanWord("버린 자식");
        System.out.println(farm.getKoreanWord());

    }
}
