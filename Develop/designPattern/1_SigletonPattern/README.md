# Singleton Pattern
* JVM 내에서 한 개의 인스턴스만 생성하기 위해 사용되는 패턴이다.

## Pattern Structure
![DecoratorPatter](../img/DecoratorPattern.png)

```java
class Singleton {
    private static Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

## Multi Thread 환경
* 기존 Singleton의 경우, Multi Thread 환경에서는 에러를 발생시킬 수 있다.
* 시나리오는 다음과 같다.
    1. Thread A가 getInstance()를 호출
    2. 아직 instance는 null이기 때문에 new 키워드를 호출
    3. 변수가 인스턴스를 가리키기 위해서는 다음과 같은 일이 진행된다.
        1) 인스턴스 데이터를 저장할 메모리를 할당받는다.
        2) 메모리 주소를 변수가 가리키게 된다. 이 때에는 아직 메모리만 할당 받았을 뿐, 데이터는 저장되어 있지 않다.
        3) 할당 받은 메모리에 인스턴스 정보가 저장된다.  
    4. 하지만 아직 메모리에 올라가지 않은 순간에 Thread B가 getInstance()를 호출
    5. Thread A의 작업에 의해서 아직 메모리에 올라가기 직전이기 때문에
    Thread B 또한 instance가 null이 되어 new 키워드를 호출하게 된다.

### 해결 방법
#### 1. synchronized 키워드 추가 
* 가장 기본적인 방법은 getInstance()에 synchronized를 추가한다.
* 이 경우, 역할에 비해 Lock에 의한 큰 오버헤드를 가지게 된다.
```java
class Singleton {
    private static Singleton instance;

    private Singleton() {}

    public static synchronized Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

### 2. DCL 기법 (Double Checked Locking)
* 기존에 큰 오버헤드를 가지는 synchronized를 함수 접근이 아닌 instance를 생성하는 시점에 처리하는 기법이다.
* 현재는 권고하지 않는다. 이유는 다음과 같다.
    1. Thread A가 getInstance()를 호출
    2. instance는 null이기 때문에 synchrnized Block 진입
    3. 아직 instance는 null이기 때문에 new 키워드 호출
    4. 변수가 인스턴스를 가리키기 위해서는 다음과 같은 일이 진행된다.
        1) 인스턴스 데이터를 저장할 메모리를 할당받는다.
        2) 메모리 주소를 변수가 가리키게 된다. 이 때에는 아직 메모리만 할당 받았을 뿐, 데이터는 저장되어 있지 않다.
        3) 할당 받은 메모리에 인스턴스 정보가 저장된다.  
    4. 하지만 A가 메모리만 할당 받고 인스턴스 정보가 메모리에 올라가지 않은 순간에 Thread B가 getInstance()를 호출
    5. 이 경우 instance가 null은 아니기 때문에 instance를 반환 받지만, 해당 instance에는 정보가 없기 때문에 오작동을 발생시킬 수 있다.

```java
class Singleton {
    private static Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```
 
### 3. LazyHolder 기법
* synchronized를 사용하지 않는 방법으로, 가장 많이 사용되는 기법이다.
* 기본적으로 해당 기법은 Singleton 인스턴스 초기화를 클래스 로딩 시 초기화 함으로써 안전성을 보장한다. 여기서 특별한 것은
Outer Class가 아닌 Inner Class 로딩에 초기화를 하기 때문에 LazyHoler.INSTANCE를 호출하는 순간에 Class 로딩이 이뤄진다.
* 이를 Lazy Initialization이라 하며, 객체의 초기화를 필요한 순간까지 미루는 것을 말한다.
* Lazy Initialization이 가능한 이유
    1. static inner class는 바로 접근이 가능하다는 의미이다.
    outer class와 다르게 멤버 변수 등 다른 곳에 사용되지 않는 이상 로딩되지 않는다.
    2. 그렇기 때문에 Singleton class를 로딩하더라도 LazyHolder의 INSTANCE는 초기화되지 않는다.

```java
class Singleton {
    private Singleton() {}

    public static Singleton getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
}
```

* Class 로딩을 이용해 thrad-safe를 보장하는 코드를 보니 아름답다라는 말 밖에...
어떻게 이런 생각을 가질까 싶다. 노력해야지~!