# 추상 클래스 (Abstract Class)
* 일부 메소드가 구현되지 않고, 선언만 되어있는 클래스
    * 자식 클래스에서 구현되지 않은 메소드를 반드시 구현하도록 강제하는 것
    * 필요한 모든 클래스가 구현 하도록 하기 때문에 안정성을 높임
* 추상 클래스는 객체를 생성할 수 없다.
    * 그렇기 때문에 구현된 자식 클래스 객체를 받아야 한다.
    ```java
    abstract class AbstractFoo{
        int x, y;

        public void method(){
            System.out.println("method");
        }
        public abstract void abstractMethod();  // 세미콜론을 잊지말아야 한다
                                                // 선언만 하고 구현하지 않음
    }

    class Foo extends AbstractFoo{

        @Override
        public void abstractMethod() {
            System.out.println("implemented abstractMethod");
        }
    }
    ```

## Python
 Pytyhon이 기본적으로 추상 클래스를 제공하지 않는다.
* 그래서 __abc__ 모듈을 import해야 한다.
* __@abstractmethod__ 어노테이션을 사용해야 한다.
```python
from abc import ABC
class AbstractFoo(ABC):

    @abstractmethod
    def absMethod(self):
        pass

```
```python
from abc import ABCMeta

class AbstractFoo(metaclass=ABCMeta):

    @abstractmethod
    def absMethod(self):
        pass
```




---
# 인터페이스 (Interface)
* 클래스가 사용되는 방식 / 접점만을 선언하는 클래스의 유사한 틀
* 아무런 구현이 되어 있지 않으며, 모든 메소드가 추상 메소드이다.


## 인터페이스 특징
* ```class```가 아니고 ```interface``` 키워드 사용
* 맴버 변수는 항상 ```public static final``` 이다(생략 가능)
* 맴버 메소드는 항상 ```public abstract``` 이다(생략 가능)
* 클래스는 하나만 상속할 수 있으나, 인터페이스는 여러 개 상속 가능

### 인터페이스 변수명 작성법
* __interface IFoo <- class Foo__ : 한 마디로 표현하기 힘든 경우, I를 맨 앞에 붙여준다.
* __interface Printable <- class Bar__ : 형용사 형태로 표현, Runnable 등

##  인터페이스 상속
* 인터페이스로 인터페이스를 상속할 경우, ```extends```
* 클래스 <- 클래스 상속과 다르게 다중 상속 가능
```java
interface Walkable {
    void walk();
}

interface  Runnable{
    void run();
}

interface  Jumpalbe extends Walkable, Runnable{
    void jump();
}
```

## JDK 1.8 이후
### Default Method
* 항상 public 이다.
* 인터페이스 철학과 맞지 않다.
* 기존에 사용하던 인터페이스가 개선되어, 모든 자식 메소드에 동일하게 구현되어야 하는 메소드가 생긴 경우에 쉽게 기능을 추가하기 위해 만들어짐
* 부모와 인터페이스 모두에 같은 이름의 메소드가 있는 경우, 상속 시 부모 클래스의 메소드를 실행한다.
```java
interface IFooTwo {
    void abstractMethod();
    default void defaultMethod(){   // 디폴트 메소드, 상속 받는 클래스에서 Overriding 할 수 있다.
        System.out.println("Default Method");
    }
}
```
### Static Method
* 인터페이스 이름으로 호출 가능
* 클래스 구현체의 이름으로도 호출 불가능
```java
interface IBar {
    static void staticMethod(){
        System.out.println("Static Method");
    }
}

class Bar implements IBar{

}

public class Interface {
    public static void main(String[] args) {
        IBar.staticMethod();    // 인터페이스 명으로 클래스 메소드 호출 가능
        // Bar.staticMethod(); // 구현체인 자식 클래스로는 클래스 메소드 호출 불가능
    }
}
```
</br>

## Python - Protocol (Duck Typing)
Python은 인터페이스가 없다. 대신 Protocol이 있으며 '비공식 인터페이스'라고도 한다. 이를 이용하여 파이썬스러운(동적 타입) 코드를 작성할 수 있다.
* 구현할 필요가 없다. 그렇기 때문에 Protocol에 선언되어 있는 모든 함수를 구현할 필요가 없다!!
    > "동적으로 확장 가능한 프레임워크를 개발하지 않는 이상, 우리가 직접 ABC를 만들지 않아야 한다."
```python
from typing import Protocol

# Protocol 정의하기
class Printable(Protocol):
    def print(self) -> None:
        ...

# Protocol을 준수하는 클래스 작성하기
class Book:
    def __init__(self, title: str, author: str):
        self.title = title
        self.author = author
    
    def print(self) -> None:
        print(f"Title: {self.title}, Author: {self.author}")

# Protocol을 사용하는 함수 작성하기
def print_anything(obj: Printable) -> None:
    obj.print()

# Protocol을 준수하는 객체 생성하기
book = Book("Python Programming", "John Doe")

# Protocol을 사용하는 함수 호출하기
print_anything(book)  # 출력: Title: Python Programming, Author: John Doe
```



### 읽어야 될 내용
* https://junstar92.tistory.com/356#the-numbers-abcs-and-numeric-protocols
* https://medium.com/humanscape-tech/%ED%8C%8C%EC%9D%B4%EC%8D%AC%EA%B3%BC-%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4-%ED%94%84%EB%A1%9C%ED%86%A0%EC%BD%9C%EC%97%90%EC%84%9C-abc%EA%B9%8C%EC%A7%80-118bc5aed344