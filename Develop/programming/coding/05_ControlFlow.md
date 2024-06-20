# Control flow
* 제어 흐름은 프로그램에서 실행되는 각 구문, 명령어나 함수가 호출되는 순서를 의미한다.
</br>


## 조건문 (Condition)
* if, elif, else 문 사용
    ```python
    # if 문
    if x > 10:
        print(x)
  
    # if, else if, else 문
    if x > 0:
        print(x)
    elif x == 0:
        print(0)
    else:
        print(-x)
    ```
</br>

## 반복문
1. while 문
    ```python
    while i != 5:
        print(i)
        i += 1
    ```

2. for in 문
    * range() 이용
    ```python
    for i in range(10):     # 0 ~ 9
        print(i)
   
    for i in range(len("word")):
        print(i)            # 0 ~ 3
    ```
   
    * list 이용
    ```python
    list = ["Python", "is", "good."]
    for s in list:
        print(s)    # Python is good.
    ```

3. break / continue 문
    ```python
    while True:
        if i == 1:
            continue
        else:
            break
        i += 1
    ```

4. range()
    * range(start, stop, step)
    * 해당되는 범위의 값을 반복 가능한 객체로 만들어 리턴, 리스트를 반환하는 것이 아니다! 
    range 객체를 반환한다.
    * 공급이 소진될 때까지 일련의 항목들을 순서대로 돌려주는 객체를 iterable이라고 한다. 이러한 객체를 사용하는 곳은 for, list 등이 있다.

    | Example | Return |
    |---------|--------| 
    | range(5) | 0, 1, 2, 3, 4 |
    | range(2, 5) | 2, 3, 4 |
    | range(3, 10, 2) | 3, 5, 7, 9 |


5. pass 문
    * pass 문은 아무것도 하지 않는다. 프로그램이 특별히 할 일이 없을 때 사용할 수 있다.
    >  추상 클래스/메서드 및 예외처리 시에 사용!
    ```python
    while True:
        pass
   
    class MyEmptyClass:
        pass
    ```

## 4.3. 루프 테크닉
* items()
    * items() 를 이용하여 키와 대응하는 값을 동시에 얻을 수 있다.
    ```
    knights = {'gallahad': 'the pure', 'robin': 'the brave'}
    for k, v in knights.items():
        print(k, v)
  
    # gallahad the pure
    # robin the brave
    ```
    
* enumerate()
    * enumerate(iterable, start=0)
    * iterable 자료형(list, set, tuple, dictionary, string)을 입력받아 인덱스 값을 포함하는 enumerate 객체를 반환
    * 보통 for문과 함께 자주 사용된다.
    * Example
        ```python
        # enumerate 함수
        data = enumerate((1, 2, 3))
        print(data, type(data))     # <enumerate object at 0x0000000002424EA0> <class 'enumerate'>
        
        seasons = ['Spring', 'Summer', 'Fall', 'Winter']
        list(enumerate(seasons))        # [(0, 'Spring'), (1, 'Summer'), (2, 'Fall'), (3, 'Winter')]
        list(enumerate(seasons, start=1))       # [(1, 'Spring'), (2, 'Summer'), (3, 'Fall'), (4, 'Winter')]
        
    
        for i, v in enumerate(['tic', 'tac', 'toe']):
            print(i, v)
    
        # 0 tic
        # 1 tac
        # 2 toe
        ```

* zip()
    * 둘이나 그 이상의 시퀀스를 동시에 루핑하기 위한 방법
    ```
    questions = ['name', 'quest', 'favorite color']
    answers = ['lancelot', 'the holy grail', 'blue']
    for q, a in zip(questions, answers):
        print('What is your {0}?  It is {1}.'.format(q, a))
    
    # What is your name?  It is lancelot.
    # What is your quest?  It is the holy grail.
    # What is your favorite color?  It is blue.
    ```

* reversed()
    - 시퀀스를 거꾸로 루핑하기
    - 원본데이터는 그대로 둔다!
    ```
    for i in reversed(range(1, 10, 2)):
        print(i)
    ```

* sorted()
    - 정렬된 순서로 시퀀스 루핑
    - 원본데이터는 그대로 둔다!
    ```
    basket = ['apple', 'orange', 'apple', 'pear', 'orange', 'banana']
    for i in sorted(basket):
        print(i)
    ```

* 루프를 돌고 있는 리스트를 변경하고픈 유혹을 느낀다. 하지만, 종종, 대신 새 리스트를 만드는 것이 더 간단하고 더 안전할 수 있다.

* 시퀀스와 다른 형들 비교하기
    * 비교는 사전식 순서를 사용한다.
    ```
    (1, 2, 3)              < (1, 2, 4)
    [1, 2, 3]              < [1, 2, 4]
    'ABC' < 'C' < 'Pascal' < 'Python'
    (1, 2, 3, 4)           < (1, 2, 4)
    (1, 2)                 < (1, 2, -1)
    (1, 2, 3)             == (1.0, 2.0, 3.0)
    (1, 2, ('aa', 'ab'))   < (1, 2, ('abc', 'a'), 4)
    ```
