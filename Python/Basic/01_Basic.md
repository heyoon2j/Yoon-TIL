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


## 4) 많이 사용되는 함수
## 내장 함수
| Function | Description | Example |
| type() | Data type 반환 | type(123)     # int |
| len() | 문자열 길이 반환 | len('123')    # 3 |
| range() |  |  |
| eval(expression [, globals = None [, locals = None]]) | 문자열로 표현된 식(Expression)을 받아, Python 컴파일 코드로 변환 후 실행 | num = eval('num + 1') |
| exec(object [, globals [, locals ]]) | 문자열로 표현된 명령문(Statement)을 Python 컴파일 코드로 변환 후 실행 | exec('num = num + 1') |
| compile(str, filename, mode) | 문자열로 표현된 명령문을 Python 컴파일 코드로 변환 | code = compile('num = num + 1', '<string>', 'exec') |

* exec, compile 참조 : https://jeongchul.tistory.com/521


## 형변환 (Casting)
| Function | Description | Exmpale |
| bin() | 정수형 -> 2진수로 변환하여 문자열 반환 | bin(10) # '0b1010' |
| oct() | 정수형 -> 8진수로 변환하여 문자열 반환 | oct(10) # '0o12' |
| hex() | 정수형 -> 16진수로 변환하여 문자열 반환 | hex(10) # '0xa' |
| int(str, int) | 진수값 문자열 -> 정수형으로 반환 | int('0xc', 16)  #12 |
|  |  |  |
|  |  |  |
|  |  |  |



#### Reference
* https://dojang.io/mod/page/view.php?id=2281

