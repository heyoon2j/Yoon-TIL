##############################################
# Variable
"""
- 변수, 상수 = 값을 저장할 수 있는 메모리 공간
- L value : 메모리 위치를 가지는 표현식 (메모리 위치를 가지고 있어야 한다)
- R value : 해당 Language에서 허용하는 모든 것
"""

##############################################
# 1. 변수
"""
- 변수는 변할 수 있는 데이터
- 변수를 선언할 때는 자료형과 변수명을 입력
    => 파이썬은 자료형을 선언하지 않아도 된다. 인터프리터가 자동으로 R Value를 가지고 자료형 판단한다.

- 유효범위에 따른 변수 종류
    1) 지역 변수
    2) 전역 변수
    3) 정적 변수 (== 클래스 변수)
    4) 멤버 변수
"""
print("---------- 변수 ----------")

## C/C++ or JAVA
"""
int x = 3
int y = 4
int z = x + y
"""

## Python
x = 3
y = 4
z = x + y

print("########################\n")

##############################################
# 1) 지역 변수 (Local Variable)
"""
* 함수 내에서 선언되는 변수를 의미한다.
* Stack Area에 할당된다.
* 선언된 함수 내에서만 접근이 가능하며, 함수 종료 시 소멸된다.
"""
print("---------- 지역 변수 ----------")

def localVarFunc():
    a = 3
    b = 7
    print(str(a) + "  /  " + str(b))

    for c in range(3):
        a = 4
        print(a)
    
    print(a)

localVarFunc()
# print(a)
print("########################\n")

##############################################
# 2) 전역 변수 (Global Variable)
"""
- 어디서든 접근이 가능한 변수로 함수 외부에 선언된 변수
- 지역 변수와 이름이 같을 시, 지역 변수에 가려진다.
    => 함수 내의 지역변수를 전역 변수로 사용하려면 global 명령어를 사용해야 한다.
"""
print("---------- 전역 변수 ----------")

globalVar = 10
globalVar2 = 20

def testFunc2():
    global globalVar2
    globalVar2 = 30
    print(globalVar)
    print(globalVar2)
    # print(locals())

testFunc2()
print(globalVar)
print(globalVar2)
# print(globals())

print("########################\n")

##############################################
# 3) 정적 변수 (== 클래스 변수)
"""
- 시작과 동시에 할당되어 프로그램이 종료될 떄까지 남아있다.
- 그렇기 때문에 외부에서 클래스 이름으로 접근이 가능하다.
- static 변수를 사용하는 이유는 접근 범위를 지역 변수는 해당 함수로, 전역 변수는 해당 파일로, 멤버 변수는 클래스로 제한하기 위해서__
    => Static 변수의 경우, 클래스를 위해 메모리 공간에 딱 하나만 할당된다.
"""
print("---------- 정적 변수 ----------")

class TestClass:
    classVar = 3
  
    def __init__(self):
        self.attr1 = 1
        self.attr2 = 2
  
print(TestClass.classVar)
print("########################\n")


##############################################
# 4) 멤버 변수 (Member Variable)
"""
- 클래스 내에 선언된 변수이다.
- 각 객체마다 변수가 생성된다.
    => 파이썬에서는 init method에서 self 명령어를 사용하여 선언할 수 있다.
"""
print("---------- 멤버 변수 ----------")

class TestClass2:
    classVal = 3
    # memberVar = 3

    def __init__(self):
        self.memberVar = 1
        self.memberVar2 = 2

    def testFunc(self):
        memberVar = 2
        print(memberVar)
        print(self.memberVar)
        # print(TestClass2.memberVar)
  
# print(TestClass.memberVar)
testClass = TestClass2()
testClass.testFunc()
print("########################\n")


##############################################
# 2. 상수
"""
- 변하지 않는 데이터
- 상수는 크게 2가지 종류
    1) 리터럴 상수
    2) 심볼릭 상수
"""
print("---------- 상수 ----------")

# 1) 리터럴 상수
num = 30
a = True
str = "Sing"


# 2) 심볼릭 상수
# 대문자로 표시하며, 상수이지만 실질적으로 변경이 가능하며, 개발자 간의 약속으로 생각하면된다.
SYMBOLIC_VALUE = 30

print("########################\n")

##############################################
# 3. Naming
"""
1) 변수의 이름은 알파벳, 숫자로 구성된다.
2) 대소문자를 구분한다.
3) 변수의 이름은 숫자로 시작할 수 없고, 예약어는 사용할 수 없다.
    => 숫자를 사용하고 싶다면 ```_8value```
4) 특수 문자는 ```_```만 가능
5) 다음은 변수에 대한 Naming Rule이다.
    => 변수나 함수는 camelCase (intVal, keyOfPiano)
    => 클래스, 인터페이스 등은 PascalCase (ComputerTheory, LoveSong)
    => 상수는 대문자 사용 (MOVIE_NUMBER, FINAL_VALUE)
"""
print("---------- naming ----------")


# 1) 변수의 이름은 알파벳, 숫자로 구성된다.
x = 10
x2 = 11


# 2) 대소문자를 구분한다.
val = 1
Val = 2
print(val)
print(Val)

# 3) 변수의 이름은 숫자로 시작할 수 없고, 예약어는 사용할 수 없다.
# 4) 특수 문자는 ```_```만 가능
# 2x = 3
#_2x = 3

print("########################\n")