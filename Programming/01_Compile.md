# Compile 과정
## Compile 과정 (컴파일러 & 인터프리터)
* 각 방식의 Build Process는 다음과 같다.

## 컴파일러 (Compiler)
![Compiler](img/Compiler.png)
* 기본 과정
    1) 헤더 파일(.h) 작성, 함수 및 클래스 선언
    2) 소스 파일(.c) 작성, 헤더 파일에서 선언한 함수의 기능 코드 작성
    3) 컴파일러를 통해 Object 파일 생성
    4) 링커를 통해 Object 파일들을 링크하여, 실행 파일을 생성
    5) 해당 장치에서 실행 파일을 실행
* __전처리기(Preprocessor)__ : 각 소스 파일에서 지시자를 찾는다. 지시자는 # 으로 시작해서 줄 바꿈으로 끝나는 코드이다. 전처리기는 컴파일러가 실행되기 직전에 단순히 텍스트를 조작하는 치환 역할을 하기도 하고, 디버깅에도 도움을 주며 헤더 파일의 중복 포함도 방지해주는 기능을 가진다.
* __컴파일(Compile)__ : 소스 파일을 바이너리 파일(기계어)로 번역하는 작업. Object 파일을 생성.
* __링크(Link)__ : 분리된 파일들을 연결시켜 실행 파일을 생성(코드를 하나의 파일에서만 작업하지 않으므로 연결할 필요가 있다.)
* __빌드(Build)__ : 컴파일과 링크 과정을 합쳐서 빌드라고 함
* __라이브러리__ : 컴파일된 함수(기능)를 구현하는 코드가 저장되어 있는 파일들 모음
* __헤더 파일__ : 함수(기능)를 호출하기 위한 방법(인터페이스) 정보가 저장되어 있는 파일
</br>
</br>

## 인터프리터 (Interpreter)
![Interpreter](img/Interpreter.png)
* 기본 과정
    1) 소스 파일(.java/.py)을 컴파일러를 통해 바이트 코드 파일(.class/.pyc) 생성
    2) 바이트 코드 파일를 인터프리터가 해석하고 실행
* __바이트 코드 파일__ : 특정 VM이 읽을 수 있는(해석) 코드 파일 
* __인터프리터__ : 특정 VM에서 바이트 코드 파일을 해석하고 실행시키는 장치
</br>
</br>


## JAVA 컴파일 과정
![JVM](img/JVM.png)
* 기본 과정
    * __JDK__
        1) __Java Source File(*.java)__ 작성
        2) __Compiler(javac.exe)__ 을 통해 __Byte Code File(*.class)__ 로 변환
        3) __JVM(java.exe)__ 구동
    * __JRE__
        4) Runtime에 __Class Loader__ 를 통해 __Byte Code File__ 을 JVM 내로 로드
        5) ByteCode Verifier을 통해 검증 단계를 거친다.
            * Data Types 체크
            * Stack Overflow 체크
            * Code가 JVM이 명시한 내용과 일치한지
            * Memory에 허가되지 않은 접근이 존재한지
        6) Execution Engin에 의해서 Byte Code File을 __Binary Code__ 로 변환 및 실행

### 


### 


###


###




</br>
</br>


## JDK (Java Development Kit)
![JDK](img/JDK.png)
* __JDK(Java Development Kit)__ : 자바 개발 키트. JRE ++ 개발에 필요한 도구(Compiler, java.exe 등)
* __JRE(Java Runtime Environment)__ : 자바 실행 환경. JVM + 표준 클래스 라이브러리
* __JVM(Java Virtual Machine)__ : 자바 가상 기계. 실제 운영체제를 대신해서 자바 프로그램을 실행하는 가상의 운영체제 역할을 한다(JIT + Complier + GC 등)
> JDK와 JRE는 운영체제 별로 제공된다.
</br>

### JDK
* 


</br>
</br>




