# 어노테이션 (Annotation)
* 어노테이션의 사전적 의미는 "주석"
* JVM, 컴파일러, 프레임워크 등에서 사용할 수 있도록 전달하는 메타데이터

## 기본 어노테이션
| Annotation | Description |
|------------|-------------|
| @Override | 상속하여 오버라이드된 메서드 |
| @Deprecated | 앞으로 사용되지 않을 클래스/메서드/변수 ... 사용하지 말라는 의미 |
| @SuppressWarnings | 컴파일러에게 특정 경고 메세지를 무시하도록 한다. |
| @FunctionalInterface | 함수형 인터페이스임을 표기(Lambda) |

## 어노테이션의 구성 요소
* 어노테이션의 작성
    * 추상 메서드와 유사한 형태로 구현
```java
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE}) // 메타 어노테이션
@Retention(RetentionPolicy.SOURCE)                                     // 메타 어노테이션
public @interface SuppressWarnings {                                   // 어노테이션 선언
    String [] value();                                                 // 어노테이션 속성
}
```

* 어노테이션 사용
    * Key = Value 형태로 어노테이션 에 속성 값 전달
```java
@SuppressWarnings(value = {"unused", "rawtypes"}) // 키 = 값 배열
@SuppressWarnings(value = "unused")               // 값이 하나인 경우 배열 생략 가능
@SuppressWarnings({"unused", "rawtypes"})         // 속성이 value 하나인 경우 키 생략 가능

@CustomAnnotation(key1 = "value1", key2 = {"value2", "value3"}) // 속성이 여러개인 경우 키를 여러개 사용
```

* Target 설정
    * 어노테이션을 사용할 수 있는 대상을 설정
    * ElementType 열거형 상수 사용
    
    | ElementType | 범 위 |
    |-------------|-------|
    | TYPE | 클래스, 인터페이스, 어노테이션, 열거형 |
    | FIELD | 필드(멤버 변수), 열거형 상수 |
    | METHOD | 메소드 |
    | PARAMETER | 메소드의 파라미터 |
    | CONSTRUCTOR | 생성자 |
    | LOCAL_VARIABLE | 로컬 변수 |
    | ANNOTATION_TYPE | 어노테이션 |
    | PACKAGE | 패키지 |

* Retention 설정
    * 어노테이션 정보를 언제까지 유지할 것인지 설정
    * RetentionPolicy 열거형 상수 사용
    * reflection: java.lang.reflect API

    | RetentionPolicy | 범 위 |
    |-----------------|-------|
    | SOURCE | 컴파일러가 사용. 빌드된 .class 파일에는 포함되지 않음 |
    | CLASS | .class 파일에 포함되나, JVM에 포함되지 않음 |
    | RUNTIME | .class 파일에 포함. JVM에 올라와서 Reflection API에서 사용 가능 |

## 사용자 정의 어노테이션
* 사용자 정의 어노테이션 구현
    * 어노테이션 속성 값의 default 값을 지정할 수 있다.
    ```java
  @Target(FIELD)
  @Retention(RetentionPolicy.RUNTIME)
  @interface MyAnnotation {
      String[] value(); // 어노테이션 속성 설정 (기본 속성 이름은 value)
      String valueTwo() default "기본값";
  }
    ```

* 사용자 정의 어노테이션 사용
    ```java
  class AnnotationUsage {
      @MyAnnotation("game")
      String gameName = "여러분의 틱택토";
  
      // value가 String[] 이지만 하나만 쓸 수 있도록 허용
      @MyAnnotation(value = "server", valueTwo = "localHost")
      String serverIP;
  
      @MyAnnotation(value = "server", valueTwo = "8080")
      String serverPort;
  
      @MyAnnotation("game")
      String gameMode = "AI vs AI";
  
      @MyAnnotation(value = "db", valueTwo = "localhost")
      String database;
  }
    ```

* Reflection을 이용하여 어노테이션에 할당된 값 사용
    ```java
  public class Main {
      public static void main(String[] args) throws IllegalAccessException {
          @SuppressWarnings("unsued")
          int x;
  
          AnnotationUsage obj = new AnnotationUsage();
          Map<String, Object> gameProp = new HashMap();
          Map<String, Object> serverProp = new HashMap<>();
          Map<String, Object> dbProp = new HashMap<>();
  
          Field[] fields = AnnotationUsage.class.getDeclaredFields();
           for (Field field : fields) {
               MyAnnotation annotation = field.getDeclaredAnnotation(MyAnnotation.class);
               if(field.get(obj) == null) {
                   field.set(obj, annotation.valueTwo());
               }
  
               if (annotation.value()[0].equals("game")) {
                   gameProp.put(field.getName(), field.get(obj));
               } else if (annotation.value()[0].equals("server")) {
                   serverProp.put(field.getName(), field.get(obj));
               } else {
                   dbProp.put(field.getName(), field.get(obj));
               }
           }
      }
  }
    ```