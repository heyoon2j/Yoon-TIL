# Regulation Expression
* 특정 조건에 맞는 문자열을 검색(Search) / 치환(Replace)하는 패턴 기반의 식
* Email, 전화번호 등 특정한 형식에 맞게 적혀 있는지 validation 하는데 사용 가능
* Crawling 등 날 것의 자료를 긁어 모았을 때 정리하는 데에 유용
* 정규표현식은 느리기 때문에 남용하면 안된다.
    * 알고리즘 문제를 푸는데 정규 표현식을 쓰는 건 안된다.

> 정규 표현식 테스트 사이트 : https://regexr.com/

## 정규표현식 표현 방법
* 기본 작성 방법
```
/Pattern/flag
```

### 메타 문자
| 메타 문자 | Description |
|---------|--------------|
| ^x | x로 시작 |
| $x | x로 끝 |
| .x | x 앞에 하나의 문자가 있음 |
| x? | x가 있거나 없음 |
| x+ | x가 1개 이상 |
| x* | x가 0개 이상 |
| x|y  | x 또는 y |
    
### 괄호 활용
| RegExp | Description |
|---------|-------------------|
| (xy) | 괄호 안의 내용을 그룹화 |
| (?!y) | y가 없는 경우 일치 |
| x{n} | x가 n번 반복 |
| x{n,} | x가 n번 이상 반복 |
| x{n, m} | x가 n번 이상 m번 이하 반복 |
| [xy] | x 또는 y |
| [^x] | x가 아닌 |
| [^0-9] | 숫자가 아닌 |
| [a-z] | 알파벳 소문자 (a~z) |
| [0-9] | 숫자 |
| [가-힣] | 한글 |

### 축약 문자
| RegExp | Description |
|--------|-------------|
| ```\^```, ```\.```, ... | ```\``` 뒤에 나오는 문자를 문자로 처리 |
| ```\b``` | 단어의 경계를 찾는다. 경계는 시작, 끝, 공백, 문장기호를 의미 |
| ```\B``` | 단어의 경계가 아닌 것을 찾는다. |
| ```\d``` | 숫자를 찾는다. |
| ```\D``` | 숫자가 아닌 것을 찾는다. |
| ```\s``` | 공백 문자를 찾는다. |
| ```\S``` | 공백 문자가 아닌 것을 찾는다. |
| ```\w``` | [a-zA-Z0-9_] |
| ```\W``` | [^a-zA-Z0-9_] |

* 유용한 정규표현식 예
    * 한글 이름 : ```^[가-힣]{2,5}$```
    * 휴대폰 번호 : ```^01(0|1|2|6|9)[-\s]?\d{3,4}[-\s]?\d{4}$```
    * 이메일 주소 : ```[\w\.-]{1,64}@[\w\.-]{1,252}\.\w{2,4}$```

#### Flag
| Flag | Description |
|------|--------|
| g | 문자열 내 모든 패턴을 찾음 |
| i | 대소문자를 구분하지 않음 |
| m | 문자열의 모든 줄에서 찾음 |


## 정규표현식을 사용하는 클래스
### Pattern 클래스
* Pattern Method

    | Method | Description |
    |--------|-------------|
    | public static Pattern compile(String regex) | Pattern 객체를 생성 |
    | public Matcher matcher(CharSequence input) | 입력을 분석하는 Matcher 객체 생성 |
    | public static boolean matches(String regex, CharSequence input) | 입력이 regexp에 해당하는지 판단 |

* Example
    ```
    String regex = "[0-9]*";
    String str = "1234124";
    System.out.println(Pattern.matches(regex, str));
  
    Pattern pattern = Pattern.compile("\\bJava\\b");
    Matcher matcher = pattern.matcher("Java is Java and Java will be Java.");
    ```
    * 자바에서는 \ 도 문자열이기 때문에 두 개를 써야 문자열로 인식하지 않는다.
   
    
### Matcher 클래스
* Matcher Method

    | Method | Description |
    |--------|-------------|
    | find() | 정규표현식에 부합되는 내용이 문자열에 포함되어 있는지 반환. 이전 검색 위치부터 이어서 검색. |
    | start() | 패턴에 부합되는 요소의 시작 인덱스 반환 |
    | end() | 패턴에 부합되는 요소가 끝나는 위치 + 1을 반환 |
    | matches() | 문자열 전체가 정규표현식에 일치하는지 반환 |
    | lookingAt() | 비교하려는 문자열이 정규표현식으로 시작하는지 반환. 0번 인덱스부터 검색. |
    | replaceFirst() | 일치하는 첫 패턴을 문자열로 대체 |
    | replaceAll() | 일치하는 모든 패턴을 문자열로 대체 |
    | reset() | Matcher의 정보를 리셋하여 0번 인덱스부터 다시 검색 |
    | group() | 정규표현식에서 그룹화한 문자열을 저장 |

* Example
    ```java
    // ?<>를 이용해서 그룹에 이름을 넣을 수 있다.
    Pattern pattern1 = Pattern.compile("^(?<field>\\w+):(?<name>\\w+)\\.(?<ext>\\w+)$");
    Matcher matcher1 = pattern1.matcher("filename:temp.png");

    matcher1.find();
    System.out.println(matcher1.group());   // 매칭된 전체가 출력
    System.out.println(matcher1.group(0));  // 매칭된 전체가 출력
    System.out.println(matcher1.group(1));  // 첫번째 그룹
    System.out.println(matcher1.group(2));
    System.out.println(matcher1.group(3));

    System.out.println(matcher1.group("field"));
    System.out.println(matcher1.group("name"));
    System.out.println(matcher1.group("ext"));    
    ```
  