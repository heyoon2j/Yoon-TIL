# 2. Data Type & Variable

| Data Type | Description | Example |
|-----------|-------------|---------|
| int | 정수형 데이터 | a = 100   # 0xFF(16진수), Oo56(8진수) |
| float | 소수점을 포함한 실수 | a = 10.25 |
| complex | 복소수 | a = 3 + 1j |
| str | 문자열 | a =  |
| bool | 참, 거짓 | a = True   # False |
| None | Null | a = None |


## 2.1. Numbers
* Python이 지원하는 수에는 정수(Integer), 실수(Real Number), 복소수(Complex Number) 3가지가 있다.
* 그 외로 Class Decimal 이나 Class Fraction 등을 지원
1) 정수 (Integer)
    * 메모리가 허용하는 선에서 무한대의 정수를 사용
    
2) 실수 (Real Number)
    * 8 바이트 크기의 부동 소수형 제공
    * 소수점 15자리까지 표현된다.
    * 정밀한 계산을 하기는 힘들다.
    
3) 복소수 (Complex Number)
    * ```a + bj``` 와 같이 표현
    * i 대신 j로 표현된다.
    ```
    v = 2 + 3j
    v.real    # 2
    v.imag    # 3
    ```

## 2.2. String
* 기본적으로 문자열은 불변하다(immutable). 그러므로 대입 연산이 불가능하다.
* 작은 따음표 또는 큰 따음표를 이용
    ```
    print('hello')  # hello
    print("hello")  # hello
    ```
  
* 따음표 같은 문자는 \를 이용하여 이스케이핑할 수 있다.
    ```
    print('don\'t')  # don't
    ```
     
* \ 뒤에 나오는 문자가 특수 문자로 취급하기 싫으면 첫 따음표 앞에 r을 붙이면 된다(raw string). 
대신 이경우 모든 \를 일반 문자 취급을 하게 된다.
    ```
    print(r'C:\some\name')  # C:\some\name, r을 붙이지 않으면 \n을 Line Feed로 인식
    print('C:\\some\\name') # C:\some\name
    ```

* ```"""..."""``` 또는 ```''''...'''```을 이용하여 문자열을 여러 줄로 확장할 수 있다.
    * 줄 넘김 문자는 자동으로 문자열에 포함된다. 하지만 줄 끝에 \를 붙여 방지할 수 있다.
    ```
    print("""\
    Usage: thingy [OPTIONS]
         -h                        Display this usage message
         -H hostname               Hostname to connect to
    """)
  
    # Result
    Usage: thingy [OPTIONS]
         -h                        Display this usage message
         -H hostname               Hostname to connect to
    ```

* 문자열도 연산이 가능하다.
    ```
    print(3 * 'un' + 'ium')     # unununium
    ```

* 문자열은 인덱싱과 슬라이싱이 된다.
    * 음수인 경우 끝에서부터 적용된다(-1 부터 시작된다!)
    ```
    word = 'Python'
    print(word[0])      # P
    print(word[-1])     # n
    ```

* 문자열 Formatting
    * '%' 연산자를 사용하여 표현
    ```
    s = "Name: %s, Age: %d " % ("ABC", 25)
    print(s)
    ```

## 2.3. Byte
* 앞에 b'' 가 붙는다.
* 바이트를 표현하며, Immutable Type이다.
* bytearray 클래스의 경우, Mutable Type이다.
```
s = "Python"
encodedS = s.encode("UTF-8")
print(encodedS)     # b'Python'
decodedS = encodedS.decode("UTF-8")
print(decodedS)     # "Python"

a = b'123'
```


## 2.5. Boolean
* Python에서는 Boolean은 True, False 둘 중 하나의 값을 가질 수 있다.
    ```
    condition = False
    if condition == True:
        print("B")
    else:
        print("A")
    ```

* True == 1, False == 0 으로 대응시킬 수 있다.
    ```
    x, y = True + 0, False + 0
    print(x, y)     # 1, 0
    ```


## 2.4. List
* 리스트는 [] 를 이용하여 감싸고, 가변하다(mutable). 그러므로 내용을 추가, 변경, 삭제할 수 있다.
    ```
    squares = [1, 4, 9, 16, 25]
    print(squares)     # [1, 4, 9, 16, 25]

    # 변경
    cubes = [1, 8, 27, 65, 125]
    cubes[3] = 64

    # 추가
    cubes.append(216)   # [1, 4, 9, 64, 125, 216]

    # 삭제
    cubes[2:4] = []     # [1, 4, 125, 216]
    ```

* 함수

    | Function  | Description  | Example |
    |-----------|--------------|---------|
    | append(x) | 리스트 끝에 추가 | list.append(3) |
    | extend(iterable) | 리스트 끝에  iterable의 모든 항목을 덧붙이다. a[len(a):] = iterable 과 동일 | list.extend([1, 2]) |
    | insert(i, x) | 주어진 위치에 항목을 삽입 | list.insert(2, 10) |
    | remove(x) | 리스트에서 값이 x와 같은 첫 번째 항목을 삭제, 없으면 ValueError 발생 | list.remove(10) |
    | pop([i]) | 주어진 위치에 있는 항목을 삭제하고, 값 반환. 인덱스를 지정하지 않으면 마지막 항목을 삭제 | list.pop() |
    | clear() | 리스트의 모든 항목을 삭제. del a[:] 와 동등 | list.clear |
    | index(x[,start[,end]]) | 리스트에 있는 항목 중 값이 x와 같은 첫 번째의 인덱스를 반환(start와 end를 이용하여 범위를 지정할 수 있다). 항목이 없는 경우 ValueError 발생 | list.index("abc", 2) |
    | count(x) | 리스트에서 x가 등장하는 횟수를 반환 | list.count(3) |
    | sort(*, key=None, reverse=False) | 리스트를 key 기준으로 정렬 | list.sort(str.lower) |
    | reverse() | 리스트의 요소들을 제자리에서 뒤집는다. | list.reverse() |
    | copy() | 리스트의 얕은 사본을 돌려준다. a[:] 와 동등 | a.copy() |

* del 문
    * 변수와 객체의 연결을 끊는다. 메모리를 지우는 것은 아니고, GC가 메모리에서 제거하도록 도와준다.
        * 메모리를 참조하고 있는 포인터를 지운다고 생각하면 될 거 같다.
    * 인덱싱과 슬라이싱을 이용하여 리스트의 값을 삭제할 수 있다. 그 외에도 키 값을 이용해 딕셔너리의 값을 삭제할 수 있다.
    * 리스트 변수 자체를 삭제할 수 있다.
    ```
    a = [-1, 1, 66.25, 333, 333, 1234.5]
    del a[0]        # [1, 66.25, 333, 333, 1234.5]
    del a[2:4]      # [1, 66.25, 1234.5]
    del a[:]        # []
    del a           # a를 참조할 수 없다. 즉 에러가 발생한다.
    ```

## 2.5. Tuple
* 튜플은 () 로 감싸고, 불변하다(immutable). 
* 기술적인 차이점
    * 리스트는 가변이기 때문에 빠른 요소 추가를 위해 메모리를 더 많은 공간을 차지한다. 
    * 튜플은 불변이다. 그렇기 때문에 Dict의 Key로 사용될 수 있다. 또한 메모리를 효율적으로 사용이 가능하다(*args는 튜플로 저장)
* 문화적인 차이점
    * https://edykim.com/ko/post/python-list-vs.-tuple/
    * 리스트는 단일 종류의 요소를 갖고 있고, 그 일련의 요소가 몇 개나 들어있는지 명확하지 않은 경우에 주로 사용
    * 튜플은 들어 있는 요소의 수를 사전에 정확히 알고 있을 경우에 주로 사용.
* 기술적, 문화적 차이점을 고려해 선택을 하면된다.
    ```
    t = 12345, 54321, 'hello!'
    t[0]        # 12345
    t           # (12345, 54321, 'hello!')
    u = t, (1, 2, 3, 4, 5)      # Tuples may be nested, ((12345, 54321, 'hello!'), (1, 2, 3, 4, 5))
    t[0] = 88888
    Traceback (most recent call last):
      File "<stdin>", line 1, in <module>
    TypeError: 'tuple' object does not support item assignment
    
    v = ([1, 2, 3], [3, 2, 1])
    ```


## 2.6. Set
* 집합은 {} 또는 set()를 이용하여 만들 수 있다. 단, 빈 집합인 경우 set()을 이용해서만 만들 수 있다.
* 순서가 존재하지 않는다.
* 중복되는 요소가 없다.
* 기본적인 용도는 멤버십 검사와 중복 엔트리 제거이다.
    ```
    basket = set()      # {} 은 Dictionary 를 만든다.
    
    basket = {'apple', 'orange', 'apple', 'pear', 'orange', 'banana'}
    print(basket)       # show that duplicates have been removed, {'orange', 'banana', 'pear', 'apple'}
    'orange' in basket  # fast membership testing, True
    'crabgrass' in basket   # False
    
    # Demonstrate set operations on unique letters from two words
    a = set('abracadabra')      # {'a', 'r', 'b', 'c', 'd'}
    b = set('alacazam')
    a - b                       # letters in a but not in b, {'r', 'd', 'b'}
    a | b                       # letters in a or b or both, {'a', 'c', 'r', 'd', 'b', 'm', 'z', 'l'}
    a & b                       # letters in both a and b, {'a', 'c'}
    a ^ b                       # letters in a or b but not both, {'r', 'd', 'b', 'm', 'z', 'l'}
    ```


## 2.7. Dictionary
* 딕셔너리는 {} 와 dict() 이용하고, 가변이다(mutable).
* Key-Value 형태로 저장한다. 
* Key로 인덱싱이 되며, 중복되지 않는다. 그리고 모든 불변형이 Key가 될 수 있다.
    * 튜플을 Key로 사용될 수 있으나, 튜플에 가변 객체가 포함되어 있으면 키로 사용될 수 없다.
* 리스트에서 사용되는 함수가 사용되나 정렬의 경우 sorted(d)를 사용하면 된다.
    ```
    dict([('sape', 4139), ('guido', 4127), ('jack', 4098)])
  
    tel = {'jack': 4098, 'sape': 4139}
    tel['guido'] = 4127     # {'jack': 4098, 'sape': 4139, 'guido': 4127}
    tel['jack']             # 4098
    
    # Delete 'sape' : 4139
    del tel['sape']
    
    # Insert 'irv' : 4127
    tel['irv'] = 4127       # {'jack': 4098, 'guido': 4127, 'irv': 4127}
    
    # 리스트로 받으면 Key를 저장한 리스트 반환
    list(tel)               # ['jack', 'guido', 'irv']
    sorted(tel)             # ['guido', 'irv', 'jack']
  
    'guido' in tel          # True
    'jack' not in tel       # False
    ```



