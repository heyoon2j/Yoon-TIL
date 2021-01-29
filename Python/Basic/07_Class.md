# 7. Class
* Python도 OOP 프로그래밍 언어 중 하나이다.

## 7.1. 선언 방법
* 기본적인 선언 방법
    ```
    class class_name:
        # 모든 인스턴스가 공유하는 속성 값, class variable
        class_val = 'all'
  
        # __init__ method에서 각 인스턴스의 속성 값을 설정, instance variable
        def __init__(self):
            self.attr1 = value1
            self.attr2 = value2
  
        # Method
        def method_name(self, parm):
            code... 
  
    # Class 선언
    obj = MyClass()    
    ```

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

## 7.2. 상속
* 상속하는 방법
    ```
    class DerivedClassName(modname.BaseClassName):
    ```

## 7.3. 다중 상속



## 7.4. 비공개 변수



















