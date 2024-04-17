# Variable
![Varialbe](img/Variable.png)
* 값을 저장하는 메모리 공간을 변수라고 한다.
* 생각해야 될 내용
    * 변수 (Variable)
    * 상수 (Constant)
    * 바인딩 (Binding)
    * 스코프 (Scope)
    * 타입 (DataType) / 03에서 확인 가능
</br>
</br>


## 변수 (Variable)
![Python_Varialbe](img/Python_Variable.png)
* 변할 수 있는 데이터
</br>

### 변수 선언 및 초기화
``` 
[data_type] <l_value> = <r_value>

=> data_type : int, doutble, char etc
```
* 변수를 선언할 때는 자료형과 변수명을 입력한다. 원하는 경우 초기값을 적용할 수 있다.
* 초기화되지 않은 변수는 쓰레기 값이 들어간다.
* L value(l-value) : 메모리 위치를 가지는 표현식 (메모리 위치를 가지고 있어야 한다)
* R value(r-value) : 해당 Language에서 허용하는 모든 것 (리터럴 상수, 메모리 위치 및 Temporary 등 포함 / 왠만한거 다 되는 듯하다)
> 인터프린터 언어는(Python, Javascript etc) 자료형을 선언하지 않아도 된다. 인터프리터가 자동으로 R value를 가지고 자료형을 판단한다.

</br>

### Naming Rule
* 변수의 이름은 알파벳, 숫자, 특수문자로 구성
* 대소문자 구분
* 변수의 이름은 숫자로 시작할 수 없고, 예약어는 사용할 수 없다.
* 특수문자는 ```_```와 ```$```만 사용 가능
* 다음은 일반적인 Naming convention 이다.
    * 변수나 함수의 경우, Camel Case 사용. 소문자부터 시작 (ex> strValue, intValue)
    * Class, Interface, Enum, Annotation 등의 경우, Pascal Case 사용 (ex> Car, Persion)
    * 상수의 경우, 대문자 사용 (ex> CONST_VALUE, PI)    
        | 대상 | 내용 |
        |------|------|
        | Variable, Method | camelCase |
        | Class, Interface, Enum, Annotation | PascalCase |
        | Const Varibale | CONST_VALUE |
</br>


## 상수 (Constant)
* 변하지 않는 데이터
* 상수는 크게 2 가지가 있다.
    1) 리터럴 상수
    2) 심볼릭 상수
</br>

### 리터럴 상수 (Literal)
* 이름 없는 상수를 가리켜 "리터럴 상수" 또는 "리터럴" 이라고 한다.
* Example
    ```
    num = 30 + 40
    => 먼저 30, 40이 메모리 공간에 상수 형태로 저장
    => 두 상수(30, 40) 기반으로 덧셈하여 70을 num에 저장
    ```
</br>

### 심볼릭 상수 (Symbolic)
* 실질적으로 변수이지만 키워드를 이용하여 상수처럼 취급.
* 대문자, _를 사용하여 Naming 한다.
* define, const, final 명령어를 사용
* 상수 선언과 동시에 초기화를 해야한다.
* Example
    ```
    # C/C++

    #define PI 3.1415

    const int CONST_NUM = 0;

    const int *prt1 = &var1;    // ptr1을 이용하여 var1 값을 변경할 수 없다.
    int* const ptr2 = &var2;    // ptr2가 상수화 된다. var2가 가리키는 값을 변경할 수 있다.
    const int* const ptr3 = &var3;


    # JAVA
    public static final int CONST_FI = 3.14;


    # PYTHON
    PI = 3.14

    ```
> Python은 const, final 명령어가 없다. 여러 방법이 존재 : https://velog.io/@pm1100tm/Python-%ED%8C%8C%EC%9D%B4%EC%8D%AC%EC%97%90%EC%84%9C-%EC%96%B4%EB%96%BB%EA%B2%8C-%EC%83%81%EC%88%98%EB%A5%BC-%EC%A0%95%EC%9D%98%ED%95%98%EA%B3%A0-%EC%82%AC%EC%9A%A9%ED%95%A0%EA%B9%8C

</br>
</br>



## Binding
* https://bubble-dev.tistory.com/entry/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%EC%96%B8%EC%96%B4-Names-Binding-Scopes
</br>

### Binding Time
* 속성이 결정되는 시기
1. 언어 설계 타임 (Language Design Time)
    * 해당 언어를 설계할 때 정의 (설계서)
    * ex> int 타입은 정수를 의미
2. 언어 구현 타임 (Language Implementation Time)
    * 설계한 내용을 구현할 때 정의
    * ex> int는 4 Byte 사용
3. 컴파일 타임 (Compile Time)
    * 컴파일러로 변환작업 거칠 때 정의
    * 변수 타입 결정
4. 링크 타임 (Link Time)
    * 링커가 실행될 때 정의
    * 다른 파일의 변수들에 대한 속성 결정
5. 로드 타임 (Load Time)
    * 프로그램이 메모리에 올라갈 때 정의
    * 전역 변수 할당 및 초기화가 이루어짐
6. 런 타임 (Run Time)
    * 프로그램이 실행 중에 정의
    * 동적 바인딩이 해당
</br>

### Binding 종류
1. 정적 바인딩 (Static Binding)
    * 컴파일 타임 포함 모든 바인딩. 실행 후에도 변하지 않는 속성들
2. 동적 바인딩 (Dynamic Binding)
    * 런타임 바인딩. 실행 후 바뀌는 속성들
    * 메모리 주소 등
</br>

### Type Binding
1. 정적 타입 바인딩 (Static Type Binding)
    * 정적 바인딩 시에 타입이 지정
    * C/C++
2. 동적 타입 바인딩 (Dynamic Type Binding)
    * 동적 바인딩 시에 타입이 지정
    * Python, Javascript 등이 해당
</br>

### Storage Binding
* 메모리에 공간이 할당되는 지점
1. 정적 방식 (Static)
    * 프로그램 로드 타임 때 공간이 바로 할당된다.
    * 전역 변수 및 static 변수가 해당.
2. 스택 동적 방식 (Stack dynamic)
    * 런타임 도중 공간 할당.
    * 함수, 지역 변수가 해당.
3. 힙 동적 방식 (Heap dynamic)
    * 런타임 도중 공간 할당.
    * malloc, new 등이 해당.
</br>
</br>

---
## Scope
* 변수의 정의되는 위치 또는 명령어에 따라 해당 변수를 사용할 수 있는 범위가 지정된다.
* Storage Binding과 연관이 되어, 어느 시점에 메모리에 올라가
</br>

### 지역 변수 (Local Variable)
* 함수 내에서 선언되는 변수를 의미한다.
* Stack Area에 메모리 할당된다.
* 선언된 함수 내에서만 접근이 가능하며, 함수 종료시 소멸된다.
```python
print("PYTHON")
print("---------- 지역 변수 ----------")

def localVarFunc(param1, param2):
    a = param2 - param1
    b = param2 + param1
    print(a + b)

localVarFunc(4, 5)      #  10
```
</br>

### 전역 변수 (Global Variable)
* 어디서든 접근이 가능한 변수로 함수 외부에 선언된 변수
* 지역 변수와 이름이 같을 시, 지역 변수에 가려진다.
```python
print("PYTHON")
print("---------- 전역 변수 ----------")

globalVar = 10              # Global
globalVar2 = 20             # Global

def testFunc2():
    globalVar = 100        # Local
    global globalVar2       # Global
    globalVar2 = 30         # Global
    print(globalVar)                # 100     
    print(globalVar2)               # 30

print(globalVar)            # 10
print(globalVar2)           # 20
testFunc2()                 # 100 \ 30
print(globalVar)            # 10
print(globalVar2)           # 30
```
> 파이썬에서는 C/JAVA와 다르게 함수 내의 지역변수를 전역 변수로 사용하려면 global 명령어를 사용해야 한다 (잘 사용 안한다!!!)
</br>

### 정적 변수 (Static Varibale)
* 클래스 변수(Class Variable)라고도 한다.
* 시작과 동시에 할당되어 프로그램이 종료될 때까지 남아있다. 외부에서 클래스 이름만으로 접근이 가능하다.
* static 변수를 사용하는 이유는 크게 두가지 이유라고 생각한다.
    1) 접근 범위 제어 : 지역 변수는 해당 함수, 전역 변수는 해당 파일, 멤버 변수는 클래스로 제한하기 위해서.
        > 원래도 그런데 무슨 소리인가 싶은 내용이다. 여기서 제한한다는 의미는 해당 변수를 그냥 사용할 수 없고 함수, 파일, 클래스를 통해서만 접근이 가능하다는 의미이다!
    2) 공통 변수 사용 : 해당 함수, 클래스, 파일에서 하나의 변수를 공유해야 되는 경우.
```python
print("PYTHON")
print("---------- 정적 변수 ----------")


class TestClass:
    classVar = 3

    def __init__(self):
        self.memVar1 = 1
        self.memVar2 = "abc"


testClassObj = TestClass()
print(TestClass.classVar)           # 3
print(testClassObj.classVar)        # 3

testClassObj.classVar = 10
print(TestClass.classVar)           # 3
print(testClassObj.classVar)        # 10 / 이때부터 클래스 변수는 멤버 변수 취급을 하게 된다!!!!
```
> 멤버 변수는 init Function을 이용하여 선언한다.
</br>


### 멤버 변수 
* 클래스 내에 선언된 변수이다.
* 각 객체마다(instance)마다 변수가 생성된다. 
```python
print("PYTHON")
print("---------- 멤버 변수 ----------")


class TestClass2:
    classVal = 3

    def __init__(self):
        self.memVar1 = 1
        self.memVar2 = "abc"
    
    def testFunc(self):
        memVar1 = 25
        print(memVar1)          # Local Variable
        print(self.memVar1)     # Member Variable

testClass = TestClass2()
testClass.testFunc()        # 25 \ 1

testClass.memVar1 = 30
print(testClass.memVar1)    # 30
testClass.testFunc()        # 25 \ 30
```
> Python은 Class의 Method를 호출할 때, 기본적으롤 첫번째 인자 값으로 Instance 주소 값을 전달한다!(self 값) 이걸 활용하면 정적 함수와 비슷한 기능을 구현할 수 있다(@staticmethod 활용)

</br>
</br>

