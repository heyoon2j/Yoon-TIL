# java.lang 패키지
* Java에서 가장 기본적이며, 자주 사용되는 클래스를 모아놓은 패키지
* 별도로 import를 하지 않아도 사용이 가능한 기본 중의 기본

## Object Class
* 모든 클래스의 조상 클래스 - 클래스의 기본 기능을 제공
* 필요한 경우 Object 클래스에 구현된 메소드를 Override

| 메소드 | 설명 |
|-------|-----|
| public final native Class<?> getClass() | 현재 객체의 클래스를 반환한다. |
| public native int hashCode() | 현재 객체의 해시코드 값을 반환한다. |
| public boolean equals() | 현재 객체와 대상이 같은 객체를 참조하는지 여부를 반환한다. |
| public String toString() | 객체를 문자열로 변환하여 반환한다. |
| proteted native clone() throws CloneNotSupportedException | 객체를 복사하여 새로운 객체로 반환한다. |

### equals()
* 동일한 객체를 가리키는지 여부 확인
* Object 클래스의 equals() 메소드 구현
    * 객체의 참조만을 비교
    ```java
    public boolean equals(Object obj) {
        return (this == obj);
    }
    ```

* String 클래스의 equals() 메소드 구현 (Overriding)
    * 효율적으로 객체의 내용이 동일한지 비교
    ```java
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof String) {
            String aString = (String)anObject;
            if (!COMPACT_STRINGS || this.coder == aString.coder) {
                return StringLatin1.equals(value, aString.value);
            }
        }
        return false;
    }
    ```

### hashCode()
* 객체를 구분하기 위해 사용하는 정수 값(해시코드)을 반환
    * 프로그램 내에서 객체마다 유일하므로 주소값처럼 사용 가능
    * 메모리 주소는 JVM에서 관리
* native 메소드이므로 구현 내용을 볼수 없다.
* hashCode() 제한 사항
    * 필수 - 한 객체의 hashCode()를 여러 번 호출할 경우, equals()에 사용하는 값이
    변하지 않으면 동일한 값을 반환해야 한다.
    * 필수 - equals 메소드가 같다고 판단한 두 객체의 hashCode() 반환값은 같아야 한다.
    * 권고 - equals() 메소드가 다르다고 판단한 두 객체의 hashCode()가 반드시 다를 필요는 없으나, 다른 값이 나오면 HashTable 성능이 향상된다.


### clone()
* 자신을 복제하여 새로운 객체를 생성하는 메소드
* 배열을 복제할 경우 새로운 객체를 생성하므로 원본을 훼손하지 않을 수 있음
* clone() 메소드를 Overriding 할 시, Cloneable Interface를 구현해야 함
    * Deep Copy 필요시, Overriding 해야 한다.
    ```java
    int [] ints = {1, 2, 3, 4, 5};
    int [] ints2 = ints.clone();
    ```

### getClass()
* 현재 객체의 Class 클래스를 반환
    ```java
    class Foo {
        public void methodA() {
            System.out.println("method A called.");
        }
    }
    
    class FooTest {
        public static void main(String[] args) throws Exception {
            Foo foo = new Foo();
            Class fooClass = foo.getClass();
    
            System.out.println(fooClass.getName());
            System.out.println(fooClass.getDeclaredMethods().length);
            System.out.println(Foo.class.getDeclaredMethod("methodA").invoke(foo));
        }
    }
    ```


## System
* OS와 Interaction 하기 위한 클래스
* 정적 변수와 정적 메소드만으로 구성된 클래스

| 메소드 | 설명 |
|-------|-----|
| arraycopy() | src 배열의 내용을 dst 배열로 복사한다. | 
| currentTimeMillis() | 현재 시스템 시간을 ms로 반환한다. |
| exit() | 프로그램을 종료한다 |
| dc() | GC를 요청한다. |
| getenv() | 환경 변수의 값을 반환한다. |
| getProperties() | 시스템 속성을 Property로 반환한다. |
| getProperty() | 시스템 속성 값을 문자열로 반환한다. 없을 경우 null 또는 def를 반환 |
| identityHashCode() | 객체의 해시코드 값을 반환한다. |
| lineSeparator() | 시스템의 줄넘김 문자열을 반환한다. UNIX: \n, WINDOWS: \r\n |
| nanoTime() | 시스템 시간을 ns로 반환한다. |
| setProperties() | 시스템 속성을 한번에 설정한다. |
| setProperty() | 시스템 속성을 하나씩 설정한다. |

### in, out, err 정적 변수
* in : InputStream 객체, 표준 입력
* out : PrintStream 객체, 표준 출력
* err : PrintStream 객체, 표준 에러
    ```java
    System.in.read();       // in: InputStream 객체, 표준 입력
    System.out.println();   // out: PrintStream 객체, 표준 출력
    System.err.println();   // err: PrintStream 객체, 표준 에러
    ```

### System.arraycopy()
* Array를 shallow copy
* native로 되어있기 때문에 좀 더 최적화가 되어있다고 생각하면 된다.
    * 그렇기때문에 for문 보다 더 효율적이다!!!
    ```java
    int[] ints = {1, 2, 3, 4};
    int[] ints1 = new int[10];
    System.arraycopy(ints, 0, ints1, 0, ints.length);
    ```

### currentTimeMillis(), nanoTime : 시간
```java
System.out.println(System.currentTimeMillis());
System.out.println(System.nanoTime());  // RTOS가 아니기 때문에 정확하지 않을 수 있음(Real Time OS), 보통 참고용으로만 사용
```

### exit()
* 프로그램 강제 종료
* status == 0 : 정상 종료
* status != 0 : 비정상 종료 (보통 1 사용)
```java
System.exit(0);
```

### gc()
* Garbage Collection 호출
* 하지만 바로 호출이 아닌, 원하는 타이밍에 좀 더 빨리 처리해달라고 요청하는 정도라 생각하면 된다.
```java
System.gc();
```

### getenv(), getProperties(), getProperty()
* getenv() : 환경변수 호출
* getProperties() : 모든 속성 값 호출
* getProperty() : 특정 속성 값 호출
```java
System.out.println(System.getenv());
System.out.println(System.getenv("JAVA_HOME"));
System.out.println(System.getenv("Path"));
System.out.println(System.getProperties());
System.out.println(System.getProperty("user.country"));
System.out.println(System.getProperty("java.io.tmpdir"));
System.out.println(System.getProperty("line.separator"));   // windows \r\n, linux \n
System.out.println(System.getProperty("file.separator"));   // windows \ linux
```


## Math





