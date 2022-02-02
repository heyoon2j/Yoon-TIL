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
    * Class의 Method들은 기본적으로 인자로 self를 가진다.
    * ```__init__``` Method는 생성자이다.
    

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

## 특수 메소드
* https://hcr3066.tistory.com/85


## 7.2. 상속
* 상속하는 방법
    ```
    class DerivedClassName(modname.BaseClassName):
    ```

## 7.3. 다중 상속



## 7.4. 비공개 변수



## 7.5. Init, Getter, Setter
* Python은 접근제어자가 없다. 기본적으로 public을 권장하며, 제약이 필요할 시 ```@property``` 등으로 처리한다.
* ```__```가 앞에 있으면 private, ```_``` protected로 약속한다.

1. __init__ Method
    * class의 변수를 정의할 수 있는 생성자 함수이다.
    ```
        class Student:
            def __init__(self, name="yn"):
                self.name = name    
    ```

2. Getter, Setter Method
    1) get, set 이용
        ```
        class Student:
            def __init__(self):
                self.name = "yn"

            def getName(self):
                return self.name
                   
            def setName(self, name):
                self.name = name
            
        if __name__ == "__main__":
       
            a = Student()
            a.set_name("ww")
            
            print(a.get_name())
        ```
        * Method만 get, set이므로 ```a.name = "ww"```도 가능하다.

    2) ```@property``` 이용
        ```
        class Student:
            def __init__(self):
                self.__name = "yn"
            
            @property
            def name(self):
                return self.__name

            @name.setter
            def name(self, name):
                self.__name = name  
        ```
        * 먼저 ```__init__``` Method에서 변수를 정의할 때, ```__``` 기호를 붙인다.
        * 그 후에 ```@property```와 ```@변수.setter```를 이용하여 은닉할 수 있다.






## 얕은 복사 vs 깊은 복사
* https://wikidocs.net/16038
* 









