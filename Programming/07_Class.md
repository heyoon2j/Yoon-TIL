# Class
* OOP 프로그래밍 언어 문법 중 하나이다.
* 심화 내용
    1) class variable (== static variable)
    2) class method
    3) static method
    4) Inheritance
</br>



## 선언 방법
* 기본적인 선언 방법
    ```python
    class Class_name:
        # 모든 인스턴스가 공유하는 속성 값, class variable
        class_val = 'all'

        # __init__ method : 생성자 메서드. 각 인스턴스의 속성 값을 설정, instance variable
        # self : 인스턴스 주소값
        def __init__(self):
            self.attr1 = 10
            self.attr2 = 20

        # @classmethod : 클래스 메서드
        # cls : 클래스 주소값
        @classmethod
        def clsMethod_name(cls, param = "clsMethod"):
            print(id(cls))
            print(param)

        # @staticmethod : 정적 메소드
        # 인자 값을 받지 않는다.
        @staticmethod
        def staticMethod_name():
            print("staticMethod")

        # Instance Method
        # self : 인스턴스 주소값
        def method_name(self, param = "instanceMethod"):
            print(id(self))
            print(param)
    
    obj = Class_name()

    # Variable 접근    
    print(obj.class_val)            # all, 접근은 가능하다 (추천하지 않음!!)
    print(Class_name.class_val)     # all

    print(obj.attr1)                # 10
    #print(Class_name.attr1)         # error!!

    # Method 접근
    obj.method_name()               # instance       
    obj.staticMethod_name()         # static (추천하지 않음!!)
    obj.clsMethod_name()            # class  (추천하지 않음!!) 

    #Class_name.method_name()            # error!
    Class_name.method_name(obj)         # instance (추천하지 않음!!)
    Class_name.staticMethod_name()      # static
    Class_name.clsMethod_name() # 1     # class
    
    ```
    * ```__init__``` Method는 생성자이다.
    * Class의 Method들은 기본적으로 첫번째 인자로 self(인스턴스 주소)인자를 가진다.
    * @classmethod는 첫번째 인자로 self 대신 cls(클래스 주소) 인자를 가진다.
</br>

* 인자로 List 언패킹을 사용할 때
    ```
    class Person:
        def __init__(self, *args):
            self.name = args[0]
            self.age = args[1]
            self.address = args[2]
     
    maria = Person(*['마리아', 20, '서울시 서초구 반포동'])
    ```
</br>

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
</br>
</br>


## 특수 메소드
* https://hcr3066.tistory.com/85
</br>
</br>


## 상속 (Inheritance)
* 부모 클래스(Super Class)의 속성과 함수를 물려받는 것.
* 자식 클래스(Sub Class)는 상속 받은 부모 클래스의 속성과 함수를 사용할 수 있다.
* 상속하는 방법
    ```python
    class Animal:
        """Super Class"""

        def __init__(self):
            self.name = "Animal"

        def sound(self):
            print("...")


    class Cat (Animal):
        """Sub Class"""
        def __init__(self):
            self.name = "Cat"
        
        def sound(self):
            print("ya~ong~")


    ```


### Overriding
* 같은 이름, 같은 인자를 가진 함수가 부모/자식 클래스에 각 각 선언되어 있으면, 자식 클래스의 함수로 재정의 되어 재정의된 함수가 실행된다.
*  


### @classmethod vs @staticmethod
* 이분이 작성한 글과 비슷한 의견을 가짐: https://hamait.tistory.com/635




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



