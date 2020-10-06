# Code Convention
* Google Java Code Convention 정리
* 공식 문서 : https://google.github.io/styleguide/javaguide.html

## 1. Source file 기본 사항
### 1.1. File name
* Source file은 대소문자를 구분한 이름을 가지고, 확장자는 .java로 지정한다.

### 1.2. File encoding: UTF-8
* Source file의 인코딩은 UTF-8을 사용한다.

### 1.3. Special characters
#### 1.3.1. Whitespace characters
* 공백문자(0x20)는 Source file에서 아무렇게나 쓸 수 있는 유일한 문자이다.
    * 문자열 및 문자 리터럴의 다른 모든 공백 문자는 이스케이프 된다.
    * Tab 문자는 들여쓰기에 사용되지 않는다.

#### 1.3.2. Special escape sequences
* 특수 이스케이프 문자를 사용할 때는 이스케이프 시퀀스(\b, \t, \n, \f, \r, \" , \'과  \\ )를 사용한다.
* 진수 표현(\012) 또는 유니코드(\u000a)는 추천하지 않는다.

#### 1.3.3. Non-ASCII characters
* 비 ASCII 문자의 경우, 실제 유니코드 문자 또는 동등한 유니코드 이스케이프가 사용된다.


## 2. Source file structure
* 파일 구조 순서는 다음과 같다.
    1. 라이센스 또는 저작권 정보(있는 경우)
    2. package
    3. import
    4. 하나의 최상위 클래스
```java
/*
*   Copyright ...
*/
package kr.co.example;
import java.io.File;
public class ClassName{
    // ...
}
```
### 2.1. License or copyright information, if present
* 라이센스 또는 저작권 정보가 있는 경우, 여기에 속한다.

### 2.2. Package statements

### 2.3. Import statements
* Wildcard Import는 사용되지 않는다.
* Import 문은 줄 바꿈이 되지 않는다.

#### 2.3.1 Ordering and spacing
* Imports의 순서는 다음과 같다.
    1. 단일 블록에서 모든 static imports
    2. 단일 블록에서 모든 non-static imports

* static과 non-static이 모두 있는 경우, 하나의 빈 줄이 두 블록을 구분한다.
* import 문 사이에는 다른 빈 줄이 없다.
* 각 블록 내에는 이름이 ASCII 정렬 순서로 정렬된다.

#### 2.3.2. No static import for classes
* 정적 중첩 클래스에는 정적 가져오기가 사용되지 않는다.
* 해당 클래스는 일반 imports로 가져온다.


## 3. Formatting
### 3.1. Braces
#### 3.1.1. Braces are used where optional
* if, else, for, do 및 while문에는 중괄호가 사용돼야 한다(몸이 비어있거나 단 하나의 문이 포함된 경우에도)

#### 3.1.2. Nonempty blocks: K & R style
* 중괄호는  Kernighan 와 Ritchie style을 따른다. 
    1. 여는 중괄호 앞에는 줄 바꿈이 없다.
    2. 여는 중괄호 뒤는 줄 바꿈.
    3. 닫는 중괄호 앞에는 줄 바꿈.
    4. 닫는 중괄호 뒤에는 줄 바꿈(else, catch 등이 있는 경우에는 줄 바꿈을 하지 않는다.)
* 열거형 클래스의 경우에는 예외가 존재한다.
* Example
```java
return () -> {
  while (condition()) {
    method();
  }
};

return new MyClass() {
  @Override public void method() {
    if (condition()) {
      try {
        something();
      } catch (ProblemException e) {
        recover();
      }
    } else if (otherCondition()) {
      somethingElse();
    } else {
      lastThing();
    }
  }
};
```

#### 3.1.3. Empty blocks: may be concise
* 빈 블록인 경우, 줄 바꿈 없이 사용할 수 있다.
* 다중 블록 명령문인 경우에는 줄 바꿈을 해야 된다.
```java
  // This is acceptable
  void doNothing() {}

  // This is equally acceptable
  void doNothingElse() {
  }

  // This is not acceptable: No concise empty blocks in a multi-block statement
  try {
    doSomething();
  } catch (Exception e) {}
```

### 3.2. Block indentation: +2 spaces
* 새 블록이 열릴 때마다, 들여쓰기가 두 칸씩 증가한다.
* 다른 곳에서는 +4 spaces or Tab을 사용하는 경우도 있다.

### 3.3. Column limit: 100
* Java 코드의 열 제한은 100자이다.

### 3.4. Line-wrapping
* 줄 바꿈의 주요 지침은 더 높은 구문 수준에서 중단하는 것을 선호한다.
* 포괄적이고 결정적인 공식은 없다.
    1. 비 할당 연산자에서 줄이 끊어지면 기호 앞에서 끊어진다.
    2. 할당 연산자의 경우, 보통 기호 뒤에서 끊어진다.
    3. 매서드 또는 생성자 이름 뒤에 오는 여는 괄호( "(" )는 연결된 상태를 유지한다.
    4. 쉼표는 앞에 토큰에 계속 붙어 있는다.
    5. 람다의 본문이 중괄호가 없는 단일 식으로 구성된 경우 화살표 바로 뒤에 줄 바꿈이 있을 수 있다는 점을 제외하고는 람다의 화살표 옆에서 줄이 끊어지지 않는다. 

#### 3.4.1. Indent continuation lines at least +4 spaces
* 줄 바꿈 시 연속 줄인 경우, 최소 +4 공백의 들여쓰기를 한다.


### 3.5. Whitespace
#### 3.5.1. Vertical Whitespace
* 수평 공백은 다음과 같다.
    1. if, for, catch 뒤에 오는 괄호 ( 와는 분리
    2. else, catch 앞에 오는 } 와는 분리
    3. { 여는 중괄호 앞에 공백이 사용
        * 예외 ```@SomeAnnotation({a, b})```
        * 예외 ```String[][] x = {{"foo"}```
    4. , : ; 다음이나 닫는 괄호 ) 뒤에 공백 사용
    5. 선언의 유형과 변수 사이: ```List<String> list```


### 3.6. Grouping parentheses: recommended
* 선택적 그룹화 괄호는 코드가 잘못 해석될 가능성이 없는 경우 생략한다.


### 3.7. Specific constructs
#### 3.7.1. Enum classes
* Enum 상수 뒤에 오는 쉼표 뒤 줄 바꿈은 선택 사항이다.
```java
private enum Answer {
  YES {
    @Override public String toString() {
      return "yes";
    }
  },

  NO,
  MAYBE
}
```

#### 3.7.2. Variable declarations
##### 3.7.2.1. One variable per declaration
* 모든 변수 선언은 하나의 변수만 선언한다.
* ```int a, b;``` 은 사용되지 않는다.
    * for 루프 헤더의 경우는 허용된다.

##### 3.7.2.2. Declared when needed
* 지역 변수는 시작 부분에서 습관적으로 선언하지 않는다.
* 처음 사용되는 지접에 가깝게 선언하여 사용한다.

#### 3.7.3. Arrays
* C Style을 사용하지 않는다 ```String args[]```
* 다음과 같이 사용한다 ```String[] args```

#### 3.7.4. Switch statements
##### 3.7.4.1. Indentation
* 다른 블록과 마찬가지로 스위치 블록의 내용은 +2로 들여쓰기를 한다.

##### 3.7.4.2. Fall-through: commented
* Fall-through가 발생하는 경우, 주석을 단다.
* // fall through

##### 3.7.4.3. The default case is present
* default 문의 경우, Code가 없더라도 존재해야 된다.

* Example
```java
switch (input) {
  case 1:
  case 2:
    prepareOneOrTwo();
    // fall through
  case 3:
    handleOneTwoOrThree();
    break;
  default:
    handleLargeNumber(input);
}
```

#### 3.7.5. Annotations
* 각 Annotation은 자체 줄에 나열된다(즉, 한 줄에 하나의 Annotation)
* 파라미터가 없는 Annotation의 경우는 한 줄로 사용 가능하다.
* 필드에 적용되는 Annotation의 경우도 보통 한 줄에 하나씩 적용할 수 있으나, 여러 개의 Annoation을 적용하는 경우 한줄로 표현할 수 있다.
* Example
```java
@Override
@Nullable
public String getNameIfPresent() { ... }

@Override public int hashCode() { ... }

@Partial @Mock DataLoader loader;
```

#### 3.7.6. Comments
* 블록 주석은 주변 코드와 동일한 수준에서 들여쓰기 한다.


#### 3.7.7. Modifiers
* Modifier는 다음 순서를 따른다.
```java
public protected private abstract default static final transient volatile synchronized native strictfp
```


#### 3.7.8. Numeric Literals
* long 정수 리터럴의 경우, 소문자가 아닌 대문자 L을 사용하여 표기한다.



## 4. Naming
### 4.1. Rules by identifier type
#### 4.1.1. Package names
* 패키지 이름은 모두 소문자이며, 연속된 단어는 단순히 함께 연결한다(밑줄 없음)
```java
package com.example.deepspace
// package com.example.deepSpace : not use
// package com.example.deep_space : not use
```

#### 4.1.2. Class names
* UpperCamelCase로 작성
* 클래스 이름은 일반적으로 명사 또는 명사구이다(Ex> Character, ImmutableList 등)
* 인터페이스 이름은 명사 또는 명사구, 형용사 또는 형용사구이다(Ex> Readable 등)
* 테스트 클래스는 테스트 중인 클래스의 이름으로 시작하여 Test를 붙인다(Ex> HashTest, HashIntegrationTest)

#### 4.1.3. Method names
* lowerCamelCase로 작성
* 메서드 이름은 일반적으로 동사 또는 동사구이다(Ex> sendMessage, stop 등)

#### 4.1.4. Constant names
* 상수 이름은 모두 대문자를 사용되며, 각 단어는 단일 밑줄로 구분한다(Ex> CONSTANT_CASE)
* 이름은 일반적으로 명사 또는 명사구이다.

#### 4.1.5. Non-constant field names
* 상수가 아닌 필드 이름은 lowerCamelCase로 작성된다.
* 이름은 일반적으로 명사 또는 명사구이다(Ex> computedValues, index)

#### 4.1.6. Parameter names
* 매개변수 이름은 lowerCamelCase로 작성된다.

#### 4.1.7. Local variable names
* 지역 변수 이름은 lowerCamelCase로 작성된다.


## 5. Programming Practices
### 5.1. @Override: always used
* @Override Annotation은 항상 사용한다.
* 예외로 부모 메서드가 @Deprecated 인 경우, 생략 가능하다.

### 5.2. Caught exceptions: not ignored
* 아래에 언급된 경우를 제외하고는 포착된 예외에 대한 응답으로 아무것도 하지 않는 것은 거의 옳지 않다.
```java
try {
  int i = Integer.parseInt(response);
  return handleNumericResponse(i);
} catch (NumberFormatException ok) {
  // it's not numeric; that's fine, just continue
}
return handleTextResponse(response);
```

### 5.3. Static members: qualified using class
* 정적 클래스 멤버에 대한 참조는 클래스를 사용하여 정규화한다.

### 5.4. Finalizers: not used
* Object.finalize를 재정의하는 것은 거의 드물다.


## ETC
* 좋은 코드는 80자 또는 130자를 생각한다.
    * 옛날 터미널 크기가 80*25 이어서 고정폭 폰트로 80자는 딱 터미널 사이즈에 맞았다.
    * 옛날 프린터 용지가 132자였다.

