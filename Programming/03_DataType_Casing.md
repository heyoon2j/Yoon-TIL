# Data Type
![DataType](img/DataType.png)
* Data Type은 크게 기본형(Primitive Type), 참조형(Reference Type)으로 구분된다.

## 기본형 (Primitive Type)
* 값을 변수에 직접 저장하는 자료형
</br>

## 참조형 (Reference Type)
* 값을 직접 저장하지 않고, 값의 위치(주소)를 저장하는 자료형
    ```
    # Example
    Test a = new Test();
    ```
> Python의 모든 Data Type은 참조형으로 되어 있다.
</br>

----
## Data Type (C/C++, JAVA)
| Data Type | Byte Size           | Range                               |
|-----------|-----------------------|---------------------------------------|
| boolean | 1 Byte | false(거짓), true(참) |
| char | 2 Byte	|  |
| byte | 1 Byte | -2^7 ~ 2^7 - 1 ( -128 ~ 127 ) |
| short	| 2 Byte | -2^15 ~ 2^15 - 1 ( -32768 ~ 32767 ) |
| int | 4 Byte | -2^31 ~ 2^31 - 1 ( -약 21억 ~ 약 21억 ) |
| long | 8 Byte | -2^63 ~ 2^63 - 1 |
| float | 4 Byte | -m * 10^e ~ m * 10^e  // m = 23, e = 8 ( Max : 3.4028235 * 10^38 ) ( Min : 1.4 * 10^-45, 절대값 기준 ) |
| double | 8 Byte | -m * 10^e ~ m * 10^e  // m = 52, e = 11 ( Max : 1.8 * 10^308 ) ( Min : 4.9 * 10^-324 ) |
* 일반적으로 리터럴 상수는 Integer, 실수는 Double을 사용한다(언어마다 다르다!)
> 1/2 Byte Data Type은 연산 시에 4 Byte인 int로 자동 Casting 후에 연산을 진행하는데 이유는 컴퓨터가 연산을 처리할 때 32bit(4 Byte) 또는 64bit(8 Byte) 연산 체제를 가지고 있기 때문이다.

> 실수 연산은 오차가 존재하기 때문에 이에 대한 처리가 필요하다!
</br>

## Data Type (Python)
| Data Type | Description           | Example                               |
|-----------|-----------------------|---------------------------------------|
| int       | 정수형 데이터          | a = 100   # 0xFF(16진수), Oo56(8진수)  |
| float     | 소수점을 포함한 실수형  | a = 10.25                             |
| complex   | 복소수                 | a = 3 + 1j                            |
| str       | 문자열                 | a = "abc"                             |
| bool      | 참, 거짓               | a = True   # False                    |
| None      | Null                  | a = None                              |

</br>

### Numbers
* Python이 지원하는 정수(Integer), 실수(Real Number), 복소수(Complex Number) 3가지가 있다.
1) 정수 (Integer)
    - 메모리가 허용하는 선에서 무한대의 정수를 사용    
    => Data Type : ```int```
2) 실수 (Real Number)
    - 8 바이트 크기의 부동 소수형 제공
    - 소수점 15자리까지 표현된다.
    - 정밀한 계산을 하기는 힘들다.
    => Data Type : ```float```
3) 복소수 (Complex Number)
    - "a + bj" 와 같이 표현
    => Data Type : ```complex```

```python
print("PYTHON")
print("---------- Numbers ----------")

a = 2
print(type(a))          # <class 'int'>

b = 3.15
print(type(b))          # <class 'float'>

v = 2 + 3j 
v.real    # 2
v.imag    # 3
print(type(v))          # <class 'complex'>
print(type(v.real))     # <class 'float'>
print(type(v.imag))     # <class 'float'>

```
</br>


### String
* 작은 따음표 또는 큰 따음표를 이용하여 문자열 표현
* 문자열의 특성은 다음과 같다.
    1) 기본적으로 문자열은 불변하다(immutable). 그러므로 대입 연산이 불가능하다.
    2) 인덱싱과 슬라이싱 가능
    3) 이스케이프(\)를 사용하여 특수문자 표현 가능
        * 이스케이프(\)를 특수문자  취급하기 싫으면 첫 따옴표 앞에 r을 붙이면 된다(raw string). 대신 이 경우 모든 이스케이프(\)를 일반 문자 취급하게 된다.
    4) """""" 또는 ''''''를 사용하여 여러줄 표현 가능
        * 줄 넘김 문자는 자동으로 문자열에 포함된다. 하지만 줄 끝에 \를 붙여 방지할 수 있다.
    5) 문자열로 연산이 가능
    6) 문자열 Formatting이 가능
> char 형은 문자형으로 가변이다(mutable). 그러므로 대입 연산이 가능하다!
</br>

```python
print("PYTHON")
print("---------- String ----------")


## 1) 문자열은 불변하다
str = "abcdefg"
#str[2] = "j"       Error Code


## 2) 인덱싱과 슬라이싱 가능
str = "abc"
print(str[0])           # a
print(str[2])           # c
print(str[0:2])         # ab


## 3) 이스케이프(\)를 사용하여 특수문자 표현 가능
str = "123\n456"
print(str)              # 123
                        # 456
str = "\"Python\""
print(str)              # "Python"

str = r'\"Python\"'
print(str)              # \"Python\"


## 4) """""" 또는 ''''''를 사용하여 여러줄 표현 가능
str = """
abc
def
"""
print(str)      # abc
                # def

str = """\
abc\
def
"""
print(str)      # abcdef

# 5) 문자열로 연산이 가능
print(3 * 'un' + 'ium')     # unununium


# 6) 문자열 Formatting
s = "Name: %s, Age: %d" % ("ABC", 30)
print(s)
```
</br>


### Byte
* 앞에 b'' 가 붙는다.
* 바이트를 표현하며, Immutable Type이다.
* bytearray 클래스의 경우, Mutable Type이다.
```python
print("PYTHON")
print("---------- String ----------")

s = "Python"
encodedS = s.encode("UTF-8")
print(encodedS)     # b'Python'
decodedS = encodedS.decode("UTF-8")
print(decodedS)     # "Python"

a = b'123'
print(a)            # b'123'
```

</br>


### Boolean
* Boolean은 True, False 둘 중 하나의 값을 가질 수 있다.
* True == 1, False == 0 으로 대응시킬 수 있다.

```python
print("PYTHON")
print("---------- Boolean ----------")

isTrue = True
isFalse = False

print(type(isTrue))         # <class 'bool'>
print(type(isFalse))        # <class 'bool'>

x, y = True + 1, False + 0 
print(x, y)                 # 2 0
```

----

## Python Data Structure
1) List
2) Tuple
3) Set
4) Dictionary
</br>

### List
* 순서가 있는 데이터의 집합 인터페이스
* 데이터 중복 허용
* 데이터의 순서(index)가 유일한 데이터의 구분자(identifier)로 사용
* 리스트는 [] 를 이용하여 감싸고, 가변하다(mutable). 그러므로 내용을 추가, 변경, 삭제할 수 있다.
    ```python
    cubes = [1, 4, 9, 16, 125]
    print(cubes)     # [1, 4, 9, 16, 125]

    # 변경
    cubes[3] = 64       # [1, 4, 9, 64, 125]
    print(cubes)


    a[2:5] = ['a', 'b', 'c']     # [0, 10, 'a', 'b', 'c', 50, 60, 70, 80, 90]


    # 추가
    cubes.append(216)   # [1, 4, 9, 64, 125, 216]
    print(cubes)

    # 삭제
    cubes[2:4] = []     # [1, 4, 125, 216]
    print(cubes)
    ```
* 증가폭 사용 가능
    ```python
    # list[]
    a = [0, 10, 20, 30, 40, 50, 60, 70, 80, 90]
    print(a[2:8:3])    # [20, 50]


    # 음수로도 표현 가능 (-1 == 마지막 인덱스)
    print(a[6:-2])      # [60, 70]  

   ```
* Merge & Repeat
    ```
    # Merge
    a = [1, 2]
    b = [3, 4, 5]
    c = a + b       # c == [1, 2, 3, 4, 5]

    # Repeat
    d = a * 3       # d == [1, 2, 1, 2, 1, 2]
    ```
</br>
 
* Function
    | Function	| Description	| Example | 
    |-----------|---------------|---------|
    | append(x)	| 리스트 끝에 추가	| list.append(3) | 
    | extend(iterable) | 리스트 끝에 iterable의 모든 항목을 덧붙이다. a[len(a):] = iterable 과 동일	| list.extend([1, 2]) |
    | insert(i, x)	| 주어진 위치에 항목을 삽입	| list.insert(2, 10) | 
    | remove(x)	| 리스트에서 값이 x와 같은 첫 번째 항목을 삭제, 없으면 ValueError 발생	| list.remove(10) | 
    | pop([i])	| 주어진 위치에 있는 항목을 삭제하고, 값 반환. 인덱스를 지정하지 않으면 마지막 항목을 삭제	| list.pop() | 
    | clear()	| 리스트의 모든 항목을 삭제. del a[:] 와 동등	| list.clear() | 
    | index(x[,start[,end]])	| 리스트에 있는 항목 중 값이 x와 같은 첫 번째의 인덱스를 반환(start와 end를 이용하여 범위를 지정할 수 있다). 항목이 없는 경우 ValueError 발생	| list.index("abc", 2) | 
    | count(x)	| 리스트에서 x가 등장하는 횟수를 반환	| list.count(3) | 
    | sort(*, key=None, reverse=False)	| 기존 리스트를 key 기준으로 정렬, Return None	| list.sort(str.lower) | 
    | reverse()	| 리스트의 요소들을 제자리에서 뒤집는다.	| list.reverse() | 
    | copy()	| 리스트의 얕은 사본을 돌려준다. a[:] 와 동등	| a.copy() | 
    * Example
    ```python
    a = ["ABC", 25, True]

    # Append
    a.append(30.01)     # ["ABC", 25, True, 30.01]
    a[1] = 28           # ["ABC", 28, True, 30.01]
    del a[2]           # ["ABC", 25, 30.01]

    # Extend
    a.extend([200, 300])  # ["ABC", 25, 30.01, 200, 300]

    # Insert
    a.insert(2, 0)      # ["ABC", 25, 0, 30.01, 200, 300]

    # 자주 사용되는 패턴
    a.insert(0, 3)      # 처음 위치에 삽입
                        # [3, "ABC", 25, 0, 30.01, 200, 300]
    a.insert(len(a), 3) # 마지막 위치에 삽입 == a.append(3)
                        # [3, "ABC", 25, 0, 30.01, 200, 300, 3]

    # 삭제
    a = [10, 20, 30]
    x = a.pop()     # 30
    print(x)        # [10, 20]

    a.insert(2, 30)
    x = a.pop(1)    # 20, index 1번 위치
    print(x)

    a.remove(30)

    a.clear()    
    print(a)    # 
    ```
* list.sort(reverse=True|False, key=myFunc) vs sorted(iterable)
    1) list.sort()
        * 기존에 있는 리스트를 정렬한다.
            > 그렇기 때문에 sorted()보다  빠르다.
        * 반환 값은 None
        * https://www.w3schools.com/python/ref_list_sort.asp#:~:text=%20Python%20List%20sort%20%28%29%20Method%20%201,...%20%204%20More%20Examples.%20%20More%20
        ```python
        names = ['Yeeun', 'Sion', 'Jadudu', 'YounS', 'A', 'OTL']

        names.sort(reverse=True)
        print(names)            # ['YounS', 'Yeeun', 'Sion', 'OTL', 'Jadudu', 'A']


        def testFunc(e):
            return len(e)

        names.sort(key=testFunc)
        print(names)            # ['A', 'OTL', 'Sion', 'YounS', 'Yeeun', 'Jadudu']
        ```
    2) sorted(iterable)
        * 정렬된 새로운 Iterable을 반환한다.
            > 그렇기 때문에 list.sort()보다 느리다.
        * 반환 값은 Iterable
        ```python

        ```
* del 문
    * 변수와 객체의 연결을 끊는다. 메모리를 지우는 것은 아니고, GC가 메모리에서 제거하도록 도와준다.
        * 메모리를 참조하고 있는 포인터를 지운다고 생각하면 될 거 같다.
    * 인덱싱과 슬라이싱을 이용하여 리스트의 값을 삭제할 수 있다. 그 외에도 키 값을 이용해 딕셔너리의 값을 삭제할 수 있다.
    * 리스트 변수 자체를 삭제할 수 있다.
        ```python
        a = [-1, 1, 66.25, 333, 333, 1234.5]
        del a[0]        # [1, 66.25, 333, 333, 1234.5]
        print(a)

        del a[2:4]      # [1, 66.25, 1234.5]
        print(a)

        del a[:]        # []
        print(a)

        del a           # a를 참조할 수 없다. 즉 에러가 발생한다.
        print(a)
        ```

* List Comprehension
    * ```[expression for element in collection [if condition]]```
    ```python
    list = [n + 2 for n in range(0,5) if (n % 2) == 0]
    # [2, 4, 6]
    ```
</br>
</br>


### Tuple
* 튜플은 () 로 감싸고, 불변하다(immutable).
* 기술적인 차이점
    * 리스트는 가변이기 때문에 빠른 요소 추가를 위해 메모리를 더 많은 공간을 차지한다.
    * 튜플은 불변이다. 그렇기 때문에 Dict의 Key로 사용될 수 있다. 또한 메모리를 효율적으로 사용이 가능하다
    * 파이썬에서 보통 튜플로 인자값을 처리한다!!! __(*args는 튜플로 저장)__
* 문화적인 차이점
    * https://edykim.com/ko/post/python-list-vs.-tuple/
    * 리스트는 단일 종류의 요소를 갖고 있고, 그 일련의 요소가 몇 개나 들어있는지 명확하지 않은 경우에 주로 사용
    * 튜플은 들어 있는 요소의 수를 사전에 정확히 알고 있을 경우에 주로 사용.
> 기술적, 문화적 차이점을 고려해 선택을 하면된다.
* 튜플 요소에 대한 변경/삭제 불가능
    ```python
    t = 12345, 54321, 'hello!'
    print(t[0])        # 12345
    print(t)           # (12345, 54321, 'hello!')

    # Error
    t[0] = 88888
    Traceback (most recent call last):
    File "<stdin>", line 1, in <module>
    TypeError: 'tuple' object does not support item assignment

    # Error
    del t[1]
    Traceback (most recent call last):
    File "main.py", line 5, in <module>
        del t[1]
    TypeError: 'tuple' object doesn't support item deletion
    ```
* 변경, 삭제는 불가능하지만 슬라이싱 등을 통해 새로운 튜플 생성 가능
    ```python
    u = t, (1, 2, 3, 4, 5)      # Tuples may be nested, ((12345, 54321, 'hello!'), (1, 2, 3, 4, 5))
    print(u)

    v = ([1, 2, 3], [3, 2, 1])
    print(v)

    x = t[1:]
    ```
* Function
    | Function	| Description	| Example | 
    |-----------|---------------|---------| 
    | index(x[,start[,end]])	| 리스트에 있는 항목 중 값이 x와 같은 첫 번째의 인덱스를 반환(start와 end를 이용하여 범위를 지정할 수 있다). 항목이 없는 경우 ValueError 발생	| tuple.index("abc", 2) | 
    | count(x)	| 리스트에서 x가 등장하는 횟수를 반환	| tuple.count(3) |
</br>
</br>


### Set
* 순서가 없는 데이터 {집합}을 다루는 인터페이스
* 중복을 허용하지 않고, 중복되는 데이터를 효율적으로 제거하는데에 사용 가능
* 중복을 검사하는 수단으로 hashCode(), equals()
    * hash를 빠르게 계산해서 hash만 비교
    * 그 다음에 판정이 안 나면 equals()로 추가 비교
    ```python
    basket = set([1,2,3])

    basket = {'apple', 'orange', 'apple', 'pear', 'orange', 'banana'}
    print(basket)       # show that duplicates have been removed, {'orange', 'banana', 'pear', 'apple'}
    'orange' in basket  # fast membership testing, True
    'crabgrass' in basket   # False

    # Demonstrate set operations on unique letters from two words
    a = set('abracadabra')      # {'a', 'r', 'b', 'c', 'd'}
    print(a)
    b = set('alacazam')
    print(b)
    a - b                       # letters in a but not in b, {'r', 'd', 'b'}
    a | b                       # letters in a or b or both, {'a', 'c', 'r', 'd', 'b', 'm', 'z', 'l'}
    a & b                       # letters in both a and b, {'a', 'c'}
    a ^ b                       # letters in a or b but not both, {'r', 'd', 'b', 'm', 'z', 'l'}
    ```
* Function
    | Function	| Description	| Example | 
    |-----------|---------------|---------|
    | add(x) | 요소 추가 | set.add(2) |
    | update(iterable) | Iterable 추가 | set.update([1, 2, 3]) |
    | remove(x)	| 값이 x와 같은 항목을 삭제, 없으면 KeyError 발생	| set.remove(10) |
    | discard(x) | 값이 x와 같은 항목을 삭제, 없어도 Error를 발생시키지 않음 | set.discard(10) |
    | clear()	| 모든 항목을 삭제. del a[:] 와 동등	| set.clear() | 
    | pop()	| 임의의 요소 삭제 후, 반환 | list.pop() | 
    * Example
    ```python
</br>
</br>


### Dictionary
* 딕셔너리는 {} 와 dict() 이용하고, 가변이다(mutable).
* Key, Value 쌍으로 구성된 자료의 집합을 다루는 인터페이스
    * Map.Entry<K, V>
* Key는 중복될 수 없으며, Value는 중복이 가능
    * Key가 identifier 역할을 한다.
* Key로 인덱싱이 되며, 중복되지 않는다. 그리고 모든 불변형(immutable)이 Key가 될 수 있다.
    * 튜플을 Key로 사용될 수 있으나, 튜플에 가변 객체가 포함되어 있으면 키로 사용될 수 없다.
* 리스트에서 사용되는 함수가 사용되나 정렬의 경우 sorted(d)를 사용하면 된다.
    ```
    ```
* Function
    | Function	| Description	| Example | 
    |-----------|---------------|---------|
    | keys() | 모든 Key 값 출력 (dict_keys[list] 반환) |  dict.keys() |
    | values() | 모든 Value 값 출력 (dict_values[list] 반환) | dict.values() |
    | items() | 모든 Key-Value 값 출력 (dict_items[List(Tuple)] 반환) | dict.items() |
    | has_key(key) | 해당 키 값의 존재 여부 (True / False) | dict.has_key("a") |
    | dict[key] | 해당 Key 값에 대한 Value 값 추가 또는 변경 (에러 발생 O) | dict["a"] |
    | dict.get(key) | 해당 Key 값에 대한 Value 값 반환 (에러 발생 X) | dict.get["a"] |
    | clear()	| 모든 항목을 삭제. del a[:] 와 동등	| dict.clear() | 
    * Example
    ```python
    dict([('sape', 4139), ('guido', 4127), ('jack', 4098)])

    tel = {'jack': 4098, 'sape': 4139}
    tel['guido'] = 4127     # {'jack': 4098, 'sape': 4139, 'guido': 4127}
    print(tel['jack'])      # 4098


    # Insert 'irv' : 4127
    tel['irv'] = 4127       # {'jack': 4098, 'sape': 4139, 'guido': 4127, 'irv': 4127}
    print(tel)

    # Delete 'sape' : 4139
    del tel['sape']         # {'jack': 4098, 'guido': 4127, 'irv': 4127}
    print(tel)

    # Key, Value
    print(tel.keys())       # dict_keys(['jack', 'guido', 'irv'])
    print(tel.values())     # dict_values([4098, 4127, 4127])
    print(tel.items())      # dict_items([('jack', 4098), ('guido', 4127), ('irv', 4127)])

    for value in tel.values():
        print(value)
        

    # Check
    print('a' in tel)                    # False / Key Check
    print('4127' in tel.values())        # True / Value Check


    # 리스트로 받으면 Key를 저장한 리스트 반환
    list(tel)               # ['jack', 'guido', 'irv']
    sorted(tel)             # ['guido', 'irv', 'jack']

    # in 연산자로 확인
    'guido' in tel          # True
    'jack' not in tel       # False    
    ```
</br>
</br>



----

# Type Casting
* Data Type을 변환한다.
* 연산은 Data Type 특성(Binding)을 가지고 진행이 된다. 크게 Upcasting과 Downcasting으로 분류된다.



* __Upcasting__ : 범위가 작은 쪽에서 
* __Downcasting__ : 

> 헷갈리기 쉬운데, 메모리를 기준으로 보는 것이 아닌 Data Type을 기준으로 생각해야 한다! Class 다형성은 이후에 정리할 예정.
</br>

## Upcasting
* 범위가 작은 쪽 ---> 큰 쪽으로 타입 변환
1. 메모리 크기가 작은 쪽 ---> 큰 쪽
2. 정밀도가 작은 쪽 ---> 높은 쪽
3. Parent Class ---> Child Class (아마 가능은 하나? 사용하지 않음)
</br>

## Downcasting
* 범위가 큰 쪽 ---> 작은 쪽으로 타입 변환
* 범위가 작아짐에 따라 제약 사항이 따라온다!
1. 메모리 크기가 큰 쪽 ---> 작은 쪽
    * 
2. 정밀도가 높은 쪽 ---> 작은 쪽
3. Child Class ---> Parent Class
```c++
long x = 10             # long type은 8 byte로 연산하는 특성을 가지고 있다. => x는 8 byte 메모리를 할당받는다(초기화 및 대입연산)
int y = x               # int type은 4 byte 로 연산하는 특성을 가지고 있다. => y는 4 byte 메모리를 할당받는다(초기화 및 대입연산산
                        # y는 4 byte만 저장 가능하므로 x의 8 byte 중 4 byte만 저장된다. 즉, 앞 4 byte는 잘려나간다.

# Class
Child a = new Child()       # 
Parent b = a                # 
                            # 
```

</br>



