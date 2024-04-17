# Function

## 함수 정의
* def 키워드로 함수를 정의한다.
* 함수 바디의 첫 번째 문장은 선택적으로 문자열 리터럴이 될 수 있고, 이 문자열 리터럴은 함수의 docstring이 된다. 
그렇기 때문에 항상 써주는 습관을 들이는 것이 좋다.
* 인자들은 call by value로 전달된다. 파이썬은 객체로 인자가 전달되기 때문에 객체 참조 값(주소)이 전달된다.
    ```python
    def fib(n):
        """Print a Fibonacci series up to n."""
        a, b = 0, 1
        while a < n:
            print(a, end=' ')
            a, b = b, a+b
        print()
    ```

* 함수도 객체로 취급하기 때문에 함수도 변수처럼 인식이된다.
    * C/C++ 함수 포인터라고 생각하면될거 같다.
    ```python
    f = fib
    f(100)
    ```

* return 문이 없는 함수의 경우, None을 반환한다.
</br>
</br>


## 기본 인자 값
* 기본적으로 하나 또는 그 이상의 인자들의 기본 값을 지정할 수 있다.
    ```python
    def ask_ok(prompt, retries=4, reminder='Please try again!'):
        while True:
            ok = input(prompt)
            ...
    
    ask_ok("hi")
    ask_ok("bye", 5)
    ```

* 함수 정의는 오직 한 번만 정의 되어 진다.
    ```python
    i = 5
    
    def f(arg=i):
        print(arg)
    
    i = 6
    f()         # 5가 출력된다.    
    ```

* 초기화 값이 가변 객체일 경우 조심해야 된다.
    ```python
    def f(a, L=[]):
        L.append(a)
        return L
    
    print(f(1))     # [1]
    print(f(2))     # [1, 2]
    print(f(3))     # [1, 2, 3]
    ```
</br>
</br>


## 키워드 인자
* 키워드를 이용하여 선택적으로 인자 값을 초기화할 수 있다.
    ```python
    def parrot(voltage, state='a stiff', action='voom', type='Norwegian Blue'):
        pass
    
    parrot(1000)                                          # 1 positional argument
    parrot(voltage=1000)                                  # 1 keyword argument
    parrot(voltage=1000000, action='VOOOOOM')             # 2 keyword arguments
    parrot(action='VOOOOOM', voltage=1000000)             # 2 keyword arguments
    parrot('a million', 'bereft of life', 'jump')         # 3 positional arguments
    parrot('a thousand', state='pushing up the daisies')  # 1 positional, 1 keyword
    ```

* 제약사항으로 키워드 인자는 위치 인자 뒤에 나와야 한다.
    ```python
    parrot()                     # required argument missing
    parrot(voltage=5.0, 'dead')  # non-keyword argument after a keyword argument
    parrot(110, voltage=220)     # duplicate value for the same argument
    parrot(actor='John Cleese')  # unknown keyword argument
    ```


## 특수 매개 변수
* 인자가 전달될 방법을 제한할 수 있다.
* 형태는 다음과 같다.
    ```python
    def f(pos1, pos2, /, pos_or_kwd, *, kwd1, kwd2):
          -----------    ----------     ----------
            |             |                  |
            |        Positional or keyword   |
            |                                - Keyword only
             -- Positional only
    ```
    * / 와 * 는 없을 수 있다.


## 임의의 인자 목록
* 인자 값을 여러 개 받을 때 사용.
* *args는 Tuple 형태로, **kwd는 Dictionary 형태로 값을 저장하게 된다.
    ```python
    def cheeseshop(kind, *arguments, **keywords):
        print(kind)
        print(arguments)
        print(keywards)
    
    cheeseshop("Limburger", "It's very runny, sir.",
               "It's really very, VERY runny, sir.",
               shopkeeper="Michael Palin",
               client="John Cleese",
               sketch="Cheese Shop Sketch")
    ```
  
## 람다 표현식
* lambda 키워드를 사용하여 작고 이름 없는 함수를 만들 수 있다.
    ```python
    # lambda <arguments>: expression
    """
    def temp(argument):
        expression
    """ 
    def make_incrementor(n):
        return lambda x: x + n
    f = make_incrementor(42)
    f(0)        # 42
    f(1)        # 43
    ```
</br>
</br>


## Documentation String
* \_\_doc\_\_ 또는 help()를 사용하여 확인할 수 있다.
* 첫 줄은 항상 객체의 목적을 짧고, 간결하게 요약해야 한다. 객체의 이름이나 형을 명시적으로 언급하지 않아야 한다. 그리고 대문자로 시작하고 마침표로 끝나야 한다.
    ```python
    def my_function():
        """Do nothing, but document it.
   
        No, really, it doesn't do anything.
        """
        pass
    
    print(my_function.__doc__)
    help(my_function)


    ``` 
</br>
</br>

## 함수 어노테이션
* https://docs.python.org/ko/3/tutorial/controlflow.html

  
---
## 특수 메서드
Magic method라고도 한다. 이중 밑줄(__)로 시작하고 끝난다.
```py
class TestClass:
    def __init__(self, x, y, z):
        self.x = x
        self.y = y
        self.z = z

    def __str__(self):
        return "TestClass __str__ function"

    def __len__(self):
        return len(z)    

    def __getitem__(self, idx):
        return self.z[idx]

    def __iter__(self):
        return iter(self.z)

    def __contains__(self, item):
        return item in self.z

    def __hash__(self):
        return hash(self.x)


obj = TestClass(10, 20, [1, 5, 9, 12, 77])
print(obj[3])           # 12

for item in obj:
    print (item)

print(22 in obj)        # False
print(77 in obj)        # True


obj1 = TestClass(100, None, None)
obj2 = TestClass(100, None, None)

print(obj1 == obj2)                 # False
print(hash(obj1) == hash(obj2))     # True
```
* __init__: 객체의 초기화를 담당하는 생성자 메서드
* __del__: 객체의 삭제를 담당하는 소멸자 메서드
* __str__: 객체를 문자열로 표현할 때 호출되는 메서드로, 주로 사용자가 읽기 쉬운 형태의 문자열을 반환합니다.
* __repr__: 객체의 "공식적인" 문자열 표현을 반환하는 메서드입니다. 주로 객체를 재생성하기 위한 문자열을 반환합니다.
* __len__: 객체의 길이를 반환하는데 사용되는 메서드로, 시퀀스형 객체에서 자주 사용됩니다.
* __getitem__, __setitem__, __delitem__: 인덱싱 및 슬라이싱을 지원하기 위한 메서드들입니다.
* __iter__, __next__: 반복 가능한 객체를 만들기 위한 메서드로, 이터레이터 프로토콜을 구현합니다.
* __contains__: 멤버십 연산자 in을 지원하기 위한 메서드로, 객체가 지정된 요소를 포함하는지 여부를 반환합니다.
* __enter__, __exit__: 컨텍스트 관리 프로토콜을 구현하기 위한 메서드로, with 문과 함께 사용됩니다.
* __eq__, __ne__, __lt__, __le__, __gt__, __ge__: 객체 간의 비교를 수행하기 위한 비교 연산자 메서드들입니다.
* __hash__: 객체의 해시값을 반환하는 메서드로, 해시 가능한 객체를 만들기 위해 필요합니다.
* __call__: 객체를 함수처럼 호출할 때 호출되는 메서드로, 클래스 인스턴스를 호출 가능하게 만듭니다.
