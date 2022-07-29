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
        # 생성자 오버로딩
        def __init__(self, name = None, age = None):
            self.name = name
            self.age = age

        def sound(self):
            print("...")


    # Animal Class 상속받는다.
    class Cat(Animal):
        """Sub Class"""

        # 생성자 오버로딩
        def __init__(self, name = None, age = None, existsOwner = None):
            super().__init__(name, age)
            self.existsOwner = existsOwner
        
        def sound(self):
            print("ya~ong~")
    ```
</br>

### Overloading
* 같은 이름의 메서드를 여러개 정의하는 것. 기능이 동일한 메서드들의 이름을 동일하게 가주가기 위해서 사용한다.
* 매개변수의 타입 또는 갯수가 달라야 한다.
    > Return Type은 상관없다!!
* Example(Method Overloading)
    ```python
    class Test:
        def add(self, a, b):
            return a + b
    ```
    ```java
    public class Test {
        int add(int a, int b) {
            return a + b;
        }

        double add(double a, double b) {
            return a + b;
        }
        """
        !!! Error Code, 매개변수 타입과 갯수가 add(int a, int b)와 동일하므로
        double add(int c, int d) {
            return (double)(c + d)
        }
        """
    }
    ```
    * Python은 변수 타입이 정해져있지 않기 때문에, 타입을 생각할 필요가 없다. 갯수를 줄이는 방법은 ```None``` Type을 사용한다.
* Example(Constructor Overloading)
    ```python
    class Animal:
        """Super Class"""
        # 생성자 오버로딩
        def __init__(self, name = None, age = None):
            self.name = name
            self.age = age


    # Animal Class 상속받는다.
    class Cat(Animal):
        """Sub Class"""

        # 생성자 오버로딩
        def __init__(self, name = None, age = None, existsOwner = None):
            super().__init__(name, age)
            self.existsOwner = existsOwner        
    ```
</br>



### Overriding
* 같은 이름, 같은 인자를 가진 함수가 부모/자식 클래스에 각 각 선언되어 있으면, 자식 클래스의 함수로 재정의 되어 재정의된 함수가 실행된다.
    ```python
    class Animal:
        """Super Class"""
        # None을 사용하여 생성자 오버로딩 ()
        def __init__(self, name = None, age = None):
            self.name = name
            self.age = age

        def sound(self):
            print("...")


    class Cat(Animal):
        """Sub Class"""

        # 생성자 오버로딩
        def __init__(self, name = None, age = None, existsOwner = None):
            super().__init__(name, age)
            self.existsOwner = existsOwner
        
        def sound(self):
            print("ya~ong~")


    a = Animal()
    c = Cat()

    a.sound()           # ...
    c.sound()           # ya~ong~
    ```
</br>



### @classmethod vs @staticmethod
* 좋은 의견 중 하나 (https://hamait.tistory.com/635). 해당 글에서 확인하고 싶은 것은 크게 두가지이다.
    1) @classmethod와 @staticmethod에 대해 Memory 저장 및 처리하는 방식이 다른가? => Memory 위치에 따라 처리하는 로직이 다를 수 있으므로(처리 향상)
    2) 클래스로 함수 저장하는 것(@staticmethod)과 모듈로 함수 저장하는 것에 대한 Memory 저장 및 처리하는 방식이 다른가?
* 이렇게 말했지만 cls를 쓰는 경우는 거의 드물다(ex> Factory Method 등) 
</br>



## 다중 상속



## 제어자 (Modifiers)
* 클래스, 변수, 메서드에 부가 기능을 부여하는 키워드


### 접근 제어자 (Access Modifiers)
* 접근할 수 있는 범위를 정하는 제어자
    * public : 전체에서 사용 가능
    * protected : 같은 Package 또는 다른 Package에 속한 자식 클래스에서 사용 가능
    * private : Class 안에서만 사용 가능
* https://yoonix.tistory.com/217?category=838902
    ```python
    class Test:
        def __init__(self):
            self.__var1 = 10
            self.var2 = 20

        def aaa(self):
            print(self.__var1)

        def __bbb(self):
            print(self.var2)


    a = Test()
    a.aaa()             # 10
    print(a.__var1)     # Error!!
    print(a.var2)       # 20
    a.bbb()             # Error!!

    ```
    * Python은 기본적으로 Public이다.
    * ```_``` : 밑줄 하나. Protected 접근 제어자. 내부적으로만 사용하는 변수 혹은 메서드 (Coding Convention이기 때문에 실질적으로 에러를 발생시키지 않음)
    * ```__``` : 밑줄 두개. Private 접근 제어자
</br>


### final
* 더 이상 바뀔 수 없음을 의미
* Class : 상속 불가
* Method : Overriding 불가
* Variable : 변수의 값이 초기화된 이후에 변경 불가
    > 생성자에서 초기화가 이루어지는 것을 blank final이라고 한다.
* Python은 없다.
</br>

### static
* Python은 없다.



</br>
</br>


## Getter, Setter
1. get, set 이용
    ```python
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

2. ```@property``` 이용
    ```python
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
    * ```__``` 변수명 앞에 해당 기호가 붙여 private으로 만든다.
    * 그 후에 ```@property```와 ```@변수.setter```를 이용하여 은닉할 수 있다.






## 얕은 복사 vs 깊은 복사
* https://wikidocs.net/16038
* 



