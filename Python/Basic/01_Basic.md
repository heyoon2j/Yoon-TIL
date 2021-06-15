# Python Basic
* https://docs.python.org/ko/3/tutorial/interpreter.html

## 1) Interpreter
* 프로그래밍 언어의 소스 코드를 바로 실행하는 컴퓨터 프로그램
* 컴파일러는 소스 코드를 기계어 코드로 변환한 뒤 실행한다.
* 일반적으로 **/usr/local/bin/python**에 설치 된다.
* 기본 Prompt에서 EOF 문자 or quit()명령을 입력하면 인터프리터가 종료된다.
    * Windows : ctrl + z

## 2) 용어 정리
## iterable
* 맴버들을 한 번에 하나씩 돌려줄 수 있는 객체
* 예로는 모든 시퀀스 형들(list, str, tuple 등), dict 같은 몇몇 비 시쿼스 형들, 파일 객체들, 
```__iter__()``` 나 시퀀스 개념을 구현하는 ```__getitem__()``` 메서드를 써서 정의한 모든 클래스의 객체들


## 3) 기본 문법
## 들여쓰기
* 들여쓰기는 4개의 공백을 사용할 것을 권장

## 주석
* '#' 을 이용
* 여러 줄인 경우, ```"""..."""``` 또는 ```''''...'''``` 이용

## Scope
* Python Scope는 총 4개의 범위를 가진다.
* Local, Enclosed, Global, Built-in
1. Local Scope
    * 변수나 함수 혹은 객체라는 특정 범위에서만 유효하다.
2. Enclosed Scope
    * 중첩함수에 적용되며, 부모 함수에서 선언된 변수는 중첩함수 안에서도 유요한 범위를 갖는다.
3. Global Scope
    * 해당 파일에서 가장 바깥쪽에 선언되어 있으며, 선언된 지점 아래로 유효한 범위를 가진다.
4. Built-in Scope
    * 가장 광범위한 Scope. 따로 선언이 없어도 모든 파이썬 파일에서 유요한 범위를 가지고 있다.
    예로는 list 등과 같은 자료구조의 len() 함수가 있다.


## 4) 많이 사용되는 함수
## 내장 함수
| Function | Description | Example |
|----------|-------------|---------|
| type() | Data type 반환 | type(123)     # int |
| len() | 문자열 길이 반환 | len('123')    # 3 |
| range() |  |  |
| eval(expression [, globals = None [, locals = None]]) | 문자열로 표현된 식(Expression)을 받아, Python 컴파일 코드로 변환 후 실행 | num = eval('num + 1') |
| exec(object [, globals [, locals ]]) | 문자열로 표현된 명령문(Statement)을 Python 컴파일 코드로 변환 후 실행 | exec('num = num + 1') |
| compile(str, filename, mode) | 문자열로 표현된 명령문을 Python 컴파일 코드로 변환 | code = compile('num = num + 1', '<string>', 'exec') |

* exec, compile 참조 : https://jeongchul.tistory.com/521


## 형변환 (Casting)
| Function | Description | Example |
|----------|-------------|---------|
| bin() | 정수형 -> 2진수로 변환하여 문자열 반환 | bin(10) # '0b1010' |
| oct() | 정수형 -> 8진수로 변환하여 문자열 반환 | oct(10) # '0o12' |
| hex() | 정수형 -> 16진수로 변환하여 문자열 반환 | hex(10) # '0xa' |
| int(str, int) | 진수값 문자열 -> 정수형으로 반환 | int('0xc', 16)  # 12 |
| int(x) | 인자 값 -> 정수 타입으로 변환 | int("10.1")  # 10 |
| float(x) | 인자 값 -> 실수 타입으로 변환 | float(10) # 10.0 |
| str(x) | 인자 값 -> 문자열로 변환 | str(True)  # True <class 'str'> |
| chr(x) | 인자 값 -> 문자(유니코드)로 변환 | chr(65)    # A |
| bool(x) | 인자 값 -> bool 타입으로 변환. 숫자는 0인지 아닌지, 문자는 비어있는지 아닌지로 결정된다 | bool(0.0)  # False |



#### Reference
* https://dojang.io/mod/page/view.php?id=2281

