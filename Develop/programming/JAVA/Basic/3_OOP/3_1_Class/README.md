# 클래스 (Class)
* 클래스 - 객체를 생성하기 위한 설계도(Class)
* 객체 - 클래스를 구체화하여 값으로 생성된 것(Object, Instance)
* 클래스로 객체를 만드는 과정 -> Instantiation

## 메모리 구조(Memory Structure)
* 클래스 영역 (Class Area, Method Area, Code Area, Static Area)
	* Field 정보, Method 정보, Type 정보, Constant Pool
* 스택 영역 (Stack Area)
    * Method 호출 시, 선언도니 로컬 변수(임시로 있다가 사라짐)
* 힙 영역 (Heap Area)
    * new 키워드로 생성된 객체
    * Garbage Collection이 동작하는 영역: 더 이상 사용하지 않는 메모리를 알아서 반환하는 JVM 기능
    
```
public class MemoryStructure {  // Class 영역
    int x, y;   // Heap 영역
    String string = "String!!!";    // Heap 영역, Constant Pool

    public void method(int value) { // Class 영역
        char c = 'w';   // Stack 영역
    }
}
```

## 변수 (Variable)
* 클래스에서 쓰이는 다양한 변수
    * 클래스 맴버 변수 (static variable, class variable)
    * 인스턴스 맴버 변수 (member variable, attribute, ...)
    * 로컬 변수 (local variable)
    * 로컬 파라미터 변수 (local parameter variable), (argument)


## 메소드 (Methods)
* 객체가 하는 동작을 정의하는 작업을 수행하는 코드의 집합, 나열
* 코드의 중복을 방지, 유지보수성을 향상, 코드의 가독성 개선


## 생성자 (Constructor)
* 클래스에서 인스턴스를 생성할 때 사용하는 메소드
* new 키워드를 사용할 때, 호출되는 메소드
* 기본 생성자 (Default Constructor)
* 파라미터 생성자 (Parameter Constructor)
    * 여러 개의 생성자를 오버로딩할 수 있다.

## Getter, Setter
 * 맴버 변수를 간접적으로 다룰 수 있게 해주는 메소드
 * 맴버 변수의 캡슐화(Encapsulation)에 도움이 됨
    * 정보의 은닉/보호
 * 맴버 변수의 값을 제한해야 할 때 유용
 1. Getter
    * Return 값으로 해당 맴버 변수의 자료형을 반환
    ```$xslt
        private int x;
    
        public int getX() {
            return x;
        }
    ```
 2. Setter
    * 해당 맴버 변수 값 변환
    ```$xslt
        private int x;
    
        public void setX(int x) {
            if(x > 0 && x <= 100)
                this.x = x;
            else
                System.out.println("0 < x <= 100, x is over Range");
        }
    ```

## 초기화 블록 (Initializer)
* Static Initializer
    * Class를 Loading할 때, Static 초기화 블록이 실행된다.
* Object Initializer
    * Instance가 생성될 때, 초기화 블록이 실행된다.
```
public class Initializer {
    static int classVar;
    static int instanceCount;
    int instanceVar;

    // static initializer
    static {
        System.out.println("static block1");
        classVar = 20;
    }
    // Object initializer
    {
        System.out.println("blocks");
        instanceVar = 30;
        classVar = 50;
        instanceCount++;
    }
}
```

## JavaDoc
* JavaDoc 만드는 방법
    1. JavaDoc을 만들 Class에 주석 및 @author 추가
        ```
        /**
         * 클래스에 대한 설명, 이 클래스는 JavaDocs 클래스를 공부하기 위해...
         * @author  Yoon Seok
         */
        public class JavaDocs {
            ...
        }
        ```
    2. 설명할 Constructor, Method 등에 주석을 달아준다 
        ```$xslt
            /**
             * 생성자에 대한 설명
             * @param memberInt 입력 1
             * @param memberString 입력 2
             */
            public JavaDocs(int memberInt, String memberString) {
                this.memberInt = memberInt;
                this.memberString = memberString;
            }
        
            /**
             * 메소드에 대한 설명
              * @param string 입력 인자 (파라미터)에 대해 설명
             * @return 리턴에 대한 설명
             */
            public int methodA(String string){
                return  Integer.parseInt(string);
            }      
        ``` 
    3. Tools -> Generate JavaDoc...(Intellij)
        * Generate JavaDoc scope : JavaDoc을 만들 파일 선택
        * Output directory : JavaDoc이 저장될 위치
        * Output directory : JavaDoc이 저장될 위치
          Other command line arguments : 실행시킬 Command 입력 / "-encoding UTF-8" 추가

