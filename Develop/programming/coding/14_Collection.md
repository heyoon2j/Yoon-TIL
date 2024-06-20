# Collection
자료구조를 구현한 라이브러리
</br>

----
## Data Structure
1) List
2) Tuple (Python에만 있음)
3) Set
4) Dictionary (== Map)
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


    a = [0, 10, 20, 30, 40, 50, 60, 70, 80, 90]
    a[2:5] = ['a', 'b', 'c']     # [0, 10, 'a', 'b', 'c', 50, 60, 70, 80, 90]
    print(a)


    # 추가
    cubes.append(216)   # [1, 4, 9, 64, 125, 216]
    print(cubes)

    cubes.extend([2, 2221]) # [1, 4, 9, 64, 125, 216, 2, 2221]
    print(cubes)


    # 삭제
    cubes[2:4] = []     # [1, 4, 125, 216, 2, 2221]
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
            > 그렇기 때문에 sorted()보다 빠르다.
        * 반환 값은 None
        * https://www.w3schools.com/python/ref_list_sort.asp#:~:text=%20Python%20List%20sort%20%28%29%20Method%20%201,...%20%204%20More%20Examples.%20%20More%20
        ```python
        names = ['Yeeun', 'Sion', 'Jadudu', 'YounS', 'A', 'OTL']

        names.sort(reverse=True)
        print(names)            # ['YounS', 'Yeeun', 'Sion', 'OTL', 'Jadudu', 'A']


        def testFunc(e):
            return len(e)

        names.sort(key=testFunc, revers)
        print(names)            # ['A', 'OTL', 'Sion', 'YounS', 'Yeeun', 'Jadudu']
        ```
        ```python
        # 2차원 배열 정렬
        a = [[1, 2], [3, 2] ,[1, 4]]
        a.sort(key=lambda x : (x[0], -x[1]))
        # [1, 4], [1, 2], [3, 2]
        ```
    2) sorted(iterable, key, reverse)
        * 정렬된 새로운 Iterable을 반환한다.
            > 그렇기 때문에 list.sort()보다 느리다.
        * 반환 값은 Iterable
        ```python
        b = {"abc":10, "rt":5, "zok": 8}
        x = sorted(b.items(), key=lambda x:x[1])    # [('rt', 5), ('zok', 8), ('abc', 10)]
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
    | dict[key] | 해당 Key 값에 대한 Value 값 추가 또는 변경 (에러 발생 O) | dict["a"] |
    | dict.get(key) | 해당 Key 값에 대한 Value 값 반환 (에러 발생 X) | dict.get["a"] |
    | update(dict) | 다른 Dictionary의 값으로 해당  Dictionary의 값에 반영 | dict.update(dict2) |
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


--
## Deque
양방향 큐로, FIFO가 아닌 양쪽 끝에 요소를 추가/삭제할 수 있다.
* O(1) (기존 큐와 동일)
* 사용 방법
    ```python
    from collections import deque
    deq = deque()
    deq = deque(maxlen = 10)

    deq.append(5)       # 5
    deq.appendleft(4)   # 4, 5

    arr = [1, 0]
    deq.extend(arr)     # 4, 5, 1, 0
    deq.extendleft(arr) # 1, 0 , 4, 5, 1, 0

    deq.pop()           # 1, 0 , 4, 5, 1
    deq.popleft()       # 0, 4, 5, 1

    deq.remove(4)       # 0, 5, 1
    deq.rotate(2)       # 5, 1, 0
    deq.rotate(-2)      # 0, 5, 1
    ```



---
# 2차원, 3차원 배열

```py
n = 5
m = 2
arr = [[0]*m for _ in range(n)]

# [[0,0],[0,0],[0,0],[0,0],[0,0]]


# N*M 크기의 2차원 배열
n = 5
m = 2
arr = [[0]*m]*n
arr[0][0] = 5

# [[5,0],[5,0],[5,0],[5,0],[5,0]]
```




