# Wrapper Class
* 기본형 타입을 객체로 쓰기 위해 있는 클래스
* 기본형 타입이 허용되지 않는 기본형 타입을 쓰기 위해서 제공

| 기본형 | Wrapper Class |
|-------|---------------|
| byte | Byte |
| char | Character |
| short | Short |
| int | Integer |
| long | Long |
| float | Float |
| double | Double |
| boolean | Boolean |


## Wrapper Class 생성 방법
* **valueOf()** 이용
    ```java 
    Integer integer1 = Integer.valueOf(10);
    ```
* **new 키워드** 이용
    ```java
    Integer integer = new Integer(10);
    ```

## AutoBoxing & UnBoxing
* **AutoBoxing**
    * 기본 자료형을 Wrapper Class로 자동 변환 시킴
    ```java
    Integer integer2 = 10;
    Character character2 = 'A';
    ```

* **UnBoxing**
    * 기본 자료형이 필요한 자리에 Wrapper Class를 사용하는 경우 자동으로 변경
    ```java
    public static Integer add(Integer x, Integer y){
        return x + y;   // UnBoxing, 클래스는 덧셈이 안 된다.
                        // 자동으로 기본 자료형으로 변형되어서 계산
                        // 여기서 반환 시에는 다시 Autoboxing이 이루어짐
    }
    ```

* Wrapper Class의 값 비교
    * Wrapper Class는 Reference Type이므로 == 을 이용하여 비교할 수 없다.
    ```java
    Integer intOne = new Integer(100);
    Integer intTwo = new Integer(100);
    
    System.out.println(intOne == intTwo); // false
    System.out.println(intOne.equals(intTwo)) // true
    System.out.println(intOne == 100) // true (Unboxing)    
    ```

* **문자열 <-> 기본 자료형**
    * **parse+자료형 정적 메소드** 사용하여 문자열 -> 기본 자료형
    ```java
      int x = Integer.parseInt("123");
    ```
    * **new 키워드** 이용하여 문자열 -> Wrapper Class
    ```java
    Integer y = new Integer("100"); // 오버로딩되어 있음
    Integer z = Integer.valueOf("5555");
    ```


