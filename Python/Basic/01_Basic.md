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

================================================================================


### 6. 문자열과 바이트
* 문자열은 기본적으로 클래스 타입이다.
* Escape Sequence는 Java와 마찬가지로 '\'를 이용하여 표현
* Immutable Type이다.

* 기본 표현방법 : ' ', " " 
    ```
    # Basic '', ""
    s = 'ABC'
    s = "ABC"
    ```

* 여러열을 사용하는 경우, ''' ''', """ """
    ```
    s = '''ABC
    DEF
    GEF
    print(s)    
    ```

* 문자열 Formatting
    * '%' 연산자를 사용하여 표현
    ```
    s = "Name: %s, Age: %d " % ("ABC", 25)
    print(s)
    ```
* Index로 표현 가능
    * Python은 Char 형이 없기 때문에 모든 문자 타입은 str이 된다.
    ```
    s = "ABC"
    type(str)   # str
    ch = str[1] # "A"
    type(ch)    # str
    ```

* byte 클래스
    * 바이트를 표현하는 클래스
    * Immutable Type이다.
    * bytearray 클래스의 경우 Mutable Type이다.
    ```
    s = "Python"
    encodedS = s.encode("UTF-8")
    print(encodedS)     # b'Python'
    decodedS = encodedS.decode("UTF-8")
    print(decodedS)     # "Pthon"
    ```

### 9. Collection
#### List
* 동적 배열(Dynamic Array)
* 추가, 수정, 삭제가 가능
    ```
    # Basic
    a = [1, 2, 3, 4, 5]
    ```

1) List Indexing
    ```
    a = ["ABC", 25, False]
    idx1 = a[1]     # 25
    a[2] = True
    ```
2) List Slicing
    * 리스트에서 부분 요소를 선택하는 방법
    * Tuple과 문자열에도 사용 가능하지만 Immutable이므로 변경은 불가
    * [n:m] : n ~ m-1 까지의 부분 집합
    ```
    a = [1, 4, 7, 10, 20]
    x = a[1:4]      # [4, 7, 10]
    x = a[1:-1]     # [4, 7, 10], -1은 리스트의 마지막 요소를 가리킨다.
    x = a[:3]       # [1, 4, 7]
    x = a[3:]       # [10, 20]
    ```
   
   * 증가폭 사용하기
   ```
   a = [0, 10, 20, 30, 40, 50, 60, 70, 80, 90]
   x = a[2:8:3]     # [20, 50]
   
   a[2:5] = ['a', 'b', 'c']     # [0, 10, 'a', 'b', 'c', 50, 60, 70, 80, 90]
   ```

    * Slice 삭제, del 키워드 사용
    ```
    a = [0, 10, 20, 30, 40, 50, 60, 70, 80, 90]
    del a[2:8:2]    # [0, 10, 30, 50, 70, 80, 90]
    ```

3) List Add
    * append: 요소를 추가
    * extend : 리스트를 연결하여 확장
    * insert: 특정 인덱스에 요소 추가
    ```
    a = ["ABC", 25, True]
   
    # Append
    a.append(30.01)     # ["ABC", 25, True, 30.01]
    a[1] = 28           # ["ABC", 28, True, 30.01]
    del a.[2]           # ["ABC", 25, 30.01]
    
    # Extend
    a.extend([200, 300])  # ["ABC", 25, 30.01, 200, 300]
   
    # Insert
    a.insert(2, 0)      # ["ABC", 25, 0, 30.01, 200, 300]
    
    # 자주 사용되는 패턴
    a.insert(0, 3)      # 처음 위치에 삽입
    a.insert(len(a), 3) # 마지막 위치에 삽입 == a.append(3)
    ```

5) List Remove
    * pop : 마지막 요소 또는 특정 인덱스의 요소를 삭제 후 값 반환
    * remove : 특정 값을 찾아서 삭제 후 값 반환, 가장 먼저 찾은 값을 삭제
    * clear : 모든 요소 삭제
    ```
    a = [10, 20, 30]
    x = a.pop()     # 30
    print(x)        # [10, 20]
   
    a.insert(2, 30)
    x = a.pop(1)    # 20, index 1번 위치
   
    x = a.remove(30)
   
    a.clear()
    ```

6) List Function
    
    | Function | Description |
    |----------|-------------|
    | index(value) | 특정 값의 인덱스 구하기 |
    | count(value) | 특정 값의 개수 구하기 |
    | reverse() | 리스트의 순서 뒤집기 |
    | sort() or sort(reverse=False) | 오름차순 정렬 |
    | sort(reverse=True) | 내림차순 정렬 |
    |  |  |
    * sorted()는 내장 함수로, sort()와 다르게 새로운 리스트를 생성한다.
    

7) List Merge & Repeat
    ```
    # Merge
    a = [1, 2]
    b = [3, 4, 5]
    c = a + b       # c == [1, 2, 3, 4, 5]
   
    # Repeat
    ```
    d = a * 3       # d == [1, 2, 1, 2, 1, 2]
    ```

5) List Search
    * index(), count() 메서드 이용
    ```
    sList = "Python is good language!!".split()
    idx = sList.index("good")   # idx = 2
    n = sList.count("Python")   # n = 1
    ```

6) List Comprehension
    * ```[expression for element in collection [if condition]]```
    ```
    list = [n + 2 for n in range(0,5) if (n % 2) == 0]
    # [2, 4, 6]
    ```

#### Tuple
* Immutable Type
* List와 다르게 새로운 요소를 추가, 수정 삭제할 수 없다. 
즉, 한 번 결정된 요소를 변경할 수 없다.
    ```
    # Basic
    t = ("ABC", 25, True)
    
    idx = t[1]      # 25
    ```

#### Dictionary
* Key-Value 형태로 저장
* Key는 중복되지 않기 때문에, 중복해서 저장할 경우 마지막에 저장한 값을 저장한다.
* 기본 Key의 타입은 문자열이다.
* Dictionary는 Hash를 이용해 값을 저장한다(HashMap, HashTable)
    ```
    # Basic
    d = {'name' : 'hey', 'age' : 25, 'money' : 10000}
  
    # Value 가져오기
    d['name']           # 'hey'
    d['name'] = 'ABC'   # 'ABC'
  
    # Key가 없는 경우, Error
    # in 연산자로 확인
    d['feel']           # KeyError
    x = 'feel' in d     # False
  
    # 다른 자료형으로 key 설정 가능
    # 단, key에는 list, dictionary 사용 불가
    d = {100: 'hundred', False: 0, 3.5: [3.5, 3.5]}
    ```

#### Set


### 10. Function
* def 키워드 이용
    ```
    # Basic
    def func_name(parm):
        code...
  
    # Example
    def add(a, b):
        print(a + b)
    ```

### 11. Class
* Python도 OOP 프로그래밍 언어 중 하나이다.
* 기본 선언 방법
    ```
    class class_name:
        # __init__ method에서 Class 속성 값을 설정
        def __init__(self):
            self.attr1 = value1
            self.attr2 = value2
  
        # Method
        def method_name(self, parm):
            code... 
  
    # Class 선언
    obj = MyClass()
    ```
* type(obj) : 객체가 어떤 클래스인지 확인

* 인자로 List 언패킹을 사용할 때
    ```
    class Person:
        def __init__(self, *args):
            self.name = args[0]
            self.age = args[1]
            self.address = args[2]
     
    maria = Person(*['마리아', 20, '서울시 서초구 반포동'])
    ```
  
 * 인자로 Dictionary 언패킹을 사용할 때
    ```
    class Person:
        def __init__(self, **kwargs):    # 키워드 인수
            self.name = kwargs['name']
            self.age = kwargs['age']
            self.address = kwargs['address']
     
    maria1 = Person(name='마리아', age=20, address='서울시 서초구 반포동')
    maria2 = Person(**{'name': '마리아', 'age': 20, 'address': '서울시 서초구 반포동'})
    ```
  
### 12. Package
### 13. Exception
### 14. Unit Test
#### Reference
* https://dojang.io/mod/page/view.php?id=2281

