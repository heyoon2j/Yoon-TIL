# Lombok
* Java에서 Model Objcet를 만들 때, 
Getter/Setter, ToString, Constructor 코드 등 불필요하게 반복적으로 만드는 코드를 Annotation을 통해 줄여주는 라이브러리 프로젝트.
* Compile Time에 처리해준다.

## Lombok 적용(IntelliJ)
* File -> Settings -> Plugins Click
* Plugins에서 Lombok을 찾아서 Install을 한다.
* Intellij에서 사용하기 위해서는 **"ctrl+s" -> "Compiler" -> "Annotation Processor" -> "Enable annotation processor" Check**

1. Maven 이용
    * **https://projectlombok.org/ 접속 -> Install -> Maven**
    * **pom.xml**에 붙여넣는다.

2. Gradle 이용
    * **https://projectlombok.org/ 접속 -> Install -> Gradle**
    * **build.gradle**에 붙여넣는다.
        ```
        repositories {
        	mavenCentral()
        }
        
        dependencies {
        	compileOnly 'org.projectlombok:lombok:1.18.16'
        	annotationProcessor 'org.projectlombok:lombok:1.18.16'
        	
        	testCompileOnly 'org.projectlombok:lombok:1.18.16'
        	testAnnotationProcessor 'org.projectlombok:lombok:1.18.16'
        }
        ```
 
3. 직접 Download
    1) 공식 사이트(https://projectlombok.org/)에서 파일 다운로드
    2) File -> Project Structure -> Modules
    3) 원하는 Module 선택 -> Dependencies -> **+** Button Click
    4) Library -> Library Type 선택 후, 원하는 Library를 선택하고 적용을 시키면 완료된다.


## Lombok Annotation 종류
* @Data
    * @ToString, @EqualsAndHashCode, @Getter, @Setter,@RequiredArgsConstructor 모두 실행

* @ToString
    * 기본적으로 Non-static field와 함께 Class Name을 순서대로 쉼표로 구분하여 인쇄
    * 일부 field를 제외하기 위해서는 field에 @ToString.Exclude 사용
    * 일부 field만 지정해서 사용하기 위해서는 @ToString(onlyExplicitlyIncluded = true), field에 @ToString.Include 사용
    * Super Class의 toString()을 호출하기 위해서는 parameter callSuper=true 설정
    ```java
    // Lombok Site's Code
    import lombok.ToString;
    
    @ToString
    public class ToStringExample {
      private static final int STATIC_VAR = 10;
      private String name;
      private Shape shape = new Square(5, 10);
      private String[] tags;
      @ToString.Exclude private int id;
      
      public String getName() {
        return this.name;
      }
      
      @ToString(callSuper=true, includeFieldNames=true)
      public static class Square extends Shape {
        private final int width, height;
        
        public Square(int width, int height) {
          this.width = width;
          this.height = height;
        }
      }
    }
    ```
* @EqualsAndHashCode
    * 기본적으로 non-static, non-transient fields에 대해 equals(), hashCode() 생성
    * 사용 방법은 @ToString과 동일 
    
* @Getter / @Setter
    * 모든 field에 대해 Getter Method 생성
    * final를 제외한 field에 대해 Setter Method 생성
    * AccessLevel을 이용하여 public, protected, private 설정 가능
    ```java
    public class GetterSetterExample {   
      @Getter @Setter private int age = 10;
      @Setter(AccessLevel.PROTECTED) private String name;
      
      @Override public String toString() {
        return String.format("%s (age: %d)", name, age);
      }
    }
    ```

* @NoArgsConstructor, @RequiredArgsConstructor and @AllArgsConstructor
    * @NoArgsConstructor : 기본 생성자
    * @RequiredArgsConstructor : 원하는 field만 지정 가능
    * @AllArgsConstructor : 모든 field에 대한 생성자
    * AccessLevel을 통해 접근 지시자 설정 가능
    ```java
    @RequiredArgsConstructor(staticName = "of")
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public class ConstructorExample<T> {
      private int x, y;
      @NonNull private T description;
      
      @NoArgsConstructor
      public static class NoArgsExample {
        @NonNull private String field;
      }
    }
    ```
    
* @NonNull
    * null이 아닌지 체크
    * null인 경우, NullPointerException 발생
   ```java
    public class NonNullExample extends Something {
      private String name;
      
      public NonNullExample(@NonNull Person person) {
        super("Hello");
        // if (person == null) {
        //       throw new NullPointerException("person is marked @NonNull but is null");
        // }
        this.name = person.getName();
      }
    }
   ``` 

### Reference
* https://projectlombok.org/features/all
* https://goddaehee.tistory.com/95