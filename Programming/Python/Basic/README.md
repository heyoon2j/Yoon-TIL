# Python 기본 문법

## 기초
### 1. 들여쓰기
* 들여쓰기에는 4개의 공백을 사용할 것을 권장
    
### 2. 파이썬 표준 라이브러리
* import 문 사용
    ```python
    import math
    n = math.sqrt(9.0)
    print(n)   # 3.0 출력
    ```
### 3. 주석
* '#' 을 이용
* 여러 줄인 경우, ``` ``` 이용

### 4. 데이터 타입
* 기본 타입
    
    | Type | Description | Example |
    |------|-------------|---------|
    | int | 정수형 데이터 | a = 100 # 0xFF(16진수), Oo56 |
    | float | 소수점을 포함한 실수 | a = 10.25 |
    | bool | 참, 거짓 | a = True # False |
    | None | Null | a = None |
    
* 복소수
    * ```a + bj``` 와 같이 표현
    * i 대신 j로 표현된다.
    ```
    v = 2 + 3j
    v.real    # 2
    v.imag    # 3
    ```
  
### 5. 연산자
1) 산술 연산자
    
    | Operator | Description | Example |
    |----------|-------------|---------|
    | + | 덧셈 | 5 + 2 = 7 |
    | - | 뺄셈 | 5 - 2 = 3 |
    | * | 곱셈 | 5 * 2 = 10 |
    | ** | 제곱 | 5 ** 2 = 25 |
    | % | 나머지 | 5 % 2 = 0.5 |
    | / | 나누기 | 5 / 2 = 2.5 |
    | // | 나누기(소수점 이하는 버림) | 5 // 2 = 2 |
        
2) 비교 연산자
    * Java와 같다.
    * ==, !=, >, <, >=, <= 등이 있다.
        
3) 할당 연산자
    * Java와 같다.
    * =, +=, -=, *=, /=, %=, //= 등이 있다.
        
4) 논리 연산자
    * Short Circuit Operator
        
    | Operator | Description | Example |
    |----------|-------------|---------|
    | and | a && b | a and b |
    | or | a || b | a or b |
    | not | !a | not a |

5) Bitwise 연산자
    * &, |, ^, ~, <<, >> 연산자       
    
6) 맴버쉽 연산자
    * in, not in 연산자
    * Left Operand가 Right Collection에 포함되어 있는지, 아닌지 확인
    ```
    a = [1, 2, 3, 4]
    b = 3 in a
    print(b)
    ```

7) 삼항 연산자
    * if 와 else 문 사용
    * ```(true) if (condition) else (false)```
    ```
    x = 10
    y = 20
    result = (a-b) if (a > b) else (b - a)
    ```
       
8) Identity 연산자
    * is, is not 연산자
    * 동일한 Objcet를 가리키는지 아닌지 체크
    ```
    a = "ABC"
    b = a
    print(a is b) # True
    ```

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

### 7. 조건문
* if, else if, else 문 사용
    ```
    # if 문
    if x > 10:
        print(x)
  
    # if, else if, else 문
    if x > 0:
        print(x)
    else if x == 0:
        print(0)
    else:
        print(-x)
    ```

### 8. 반복문
1. while 문
    ```
    while i != 5:
        print(i)
        i += 1
    ```

2. for in 문
    * range() 이용
    ```
    for i in range(10):
        print(i)
    ```
    * list 이용
    ```
    list = ["Python", "is", "good."]
    for s in list:
        print(s)
    ```

3. break / continue 문
    ```
    while True:
        if i == 1:
            continue
        else:
            break;
        i += 1
    ```

4. range()
    * 기본적으로 n 입력 시, 0 ~ n-1까지 반환
    
    | Example | Return |
    |---------|--------| 
    | range(5) | 0, 1, 2, 3, 4 |
    | range(2, 5) | 2, 3, 4 |
    | range(3, 10, 2) | 3, 5, 7, 9 |
  
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


# 고촬
* 기본적으로 Python은 수학적인 기능이 강력크하다.
* 코드를 작성할때 정확한 기능을 수행하도록 짜는 것도 중요하지만, 유지/보수/수정 등을 위한 확장성을 위한 모델링, 코드 가독성, 속도, 메모리 사용 등도 생각하면서 작성해야 한다. 
    1) 모델링
        * Design Pattern
        * 변수 사용 (고정값 사용 X)
    2) 코드 가독성
    3) 속도, 메모리
       * 조건문이나 반복문으로 표현하는 것이 아닌 수학적으로 해결 가능한가 확인. 예는 다음과 같다.
            > 단 수학적인 표현을 하면 너무 좋지만, 시간의 차이가 많이 나지 않는 경우 가독성도 생각해봐야 한다.
            ```python
            # 1) 조건문
            def solution(num_list):
                answer = [0,0]

                for num in num_list:
                    if num % 2 == 0:
                        answer[0] += 1
                    else:
                        answer[1] += 1

                return answer

            # 2) 수학적 표현
            def solution(num_list):
                answer = [0,0]
                for n in num_list:
                    answer[n%2]+=1
                return answer

            ## https://school.programmers.co.kr/learn/courses/30/lessons/120829    
            def solution(angle):
                answer = (angle // 90) * 2 + (angle % 90 > 0) * 1
                return answer
            ```
        * List, Set, Tuple, Dictionary, String 등 더 효율적으로 구현할 수 있는 자료구조를 사용해야 한다.
* 빙어 코드 작성하기. 제약 사항에 대한 코드 작성 및 예외 발생 시 처리 코드 작성
    ```python
    def solution(n, k):
        service = n//10
        drink = max(0, k-service)
        return (12000*n)+(2000*drink)
    ```
    ```

    ```
    