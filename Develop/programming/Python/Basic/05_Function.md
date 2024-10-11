# 5. Function

## 5.1. 함수 정의
* def 키워드를 시작의로 함수를 정의한다.
* 함수 바디의 첫 번째 문장은 선택적으로 문자열 리터럴이 될 수 있고, 이 문자열 리터럴은 함수의 docstring이 된다. 
그렇기 때문에 항상 써주는 습관을 들이는 것이 좋다.
* 인자들은 call by value로 전달된다. 파이썬은 객체로 인자가 전달되기 때문에 객체 참조 값이 전달된다.
    ```
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
    ```
    f = fib
    f(100)
    ```

* return 문이 없는 함수의 경우, None을 반환한다.

## 5.2. 기본 인자 값
* 기본적으로 하나 또는 그 이상의 인자들의 기본 값을 지정할 수 있다.
    ```
    def ask_ok(prompt, retries=4, reminder='Please try again!'):
        while True:
            ok = input(prompt)
            ...
    
    ask_ok("hi")
    ask_ok("bye", 5)
    ```

* 함수 정의는 오직 한 번만 정의 되어 진다.
    ```
    i = 5
    
    def f(arg=i):
        print(arg)
    
    i = 6
    f()         # 5가 출력된다.    
    ```
    * 초기화 값이 가변 객체일 경우 조심해야 된다(특별한 상황아니면 이런식으로는 하지 말자!)
    ```
    def f(a, L=[]):
        L.append(a)
        return L
    
    print(f(1))     # [1]
    print(f(2))     # [1, 2]
    print(f(3))     # [1, 2, 3]
    ```

## 5.3. 키워드 인자
* 키워드를 이용하여 선택적으로 인자 값을 초기화할 수 있다.
    ```
    def parrot(voltage, state='a stiff', action='voom', type='Norwegian Blue'):
        ...
    
    parrot(1000)                                          # 1 positional argument
    parrot(voltage=1000)                                  # 1 keyword argument
    parrot(voltage=1000000, action='VOOOOOM')             # 2 keyword arguments
    parrot(action='VOOOOOM', voltage=1000000)             # 2 keyword arguments
    parrot('a million', 'bereft of life', 'jump')         # 3 positional arguments
    parrot('a thousand', state='pushing up the daisies')  # 1 positional, 1 keyword
    ```

* 제약사항으로 키워드 인자는 위치 인자 뒤에 나와야 한다.
    ```
    parrot()                     # required argument missing
    parrot(voltage=5.0, 'dead')  # non-keyword argument after a keyword argument
    parrot(110, voltage=220)     # duplicate value for the same argument
    parrot(actor='John Cleese')  # unknown keyword argument
    ```


## 5.4. 특수 매개 변수
* 인자가 전달될 방법을 제한할 수 있다.
* 형태는 다음과 같다.
    ```
    def f(pos1, pos2, /, pos_or_kwd, *, kwd1, kwd2):
          -----------    ----------     ----------
            |             |                  |
            |        Positional or keyword   |
            |                                - Keyword only
             -- Positional only
    ```
    * / 와 * 는 없을 수 있다.


## 5.5. 임의의 인자 목록
* 인자 값을 여러 개 받을 때 사용.
* *args는 Tuple 형태로, **kwd는 Dictionary 형태로 값을 저장하게 된다.
    ```
    def cheeseshop(kind, *arguments, **keywords):
        ...
    
    cheeseshop("Limburger", "It's very runny, sir.",
               "It's really very, VERY runny, sir.",
               shopkeeper="Michael Palin",
               client="John Cleese",
               sketch="Cheese Shop Sketch")
    ```
  
## 5.6. 람다 표현식
* lambda 키워드를 사용하여 작고 이름 없는 함수를 만들 수 있다.
    ```
    def make_incrementor(n):
        return lambda x: x + n
    f = make_incrementor(42)
    f(0)        # 42
    f(1)        # 43
    ```

## 5.7. Documentation String
* \__doc__ 을 이용하여 확인할 수 있다.
* 첫 줄은 항상 객체의 목적을 짧고, 간결하게 요약해야 한다. 객체의 이름이나 형을 명시적으로 언급하지 않아야 한다. 그리고 대문자로 시작하고 마침표로 끝나야 한다.
    ```
    def my_function():
        """Do nothing, but document it.
   
        No, really, it doesn't do anything.
        """
        pass
    
    print(my_function.__doc__)
    Do nothing, but document it.
    
        No, really, it doesn't do anything.
    ``` 


## 5.8. 함수 어노테이션
* https://docs.python.org/ko/3/tutorial/controlflow.html

  