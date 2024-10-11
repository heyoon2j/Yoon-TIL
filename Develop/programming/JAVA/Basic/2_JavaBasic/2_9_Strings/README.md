# 문자열 (String)
* 문자열은 내부적으로 **"클래스"**로 구성되어 있다.
* 내부에는 문자의 배열로 된 데이터가 있다(**"char[]"**)
* 한 번 만든 문자열은 변하지 않는다(**"Immutable"**)
* 문자열은 String을 쓰지 않고 Builder나 Buffer 등을 사용한다.





## String Method
| 함수 | 기능 |
|----------|----------|
|int length()| 해당 String의 길이 반환 |
|char charAt(int index)| 해당 Index에 위치한 Character 반환 |
|int indexOf(char ch)| 해당 ch의 값 Index 위치 반환 |
|boolean equalsIgnoreCase(String anotherString)| 대소문자 상관없이 비교연산 |
|String replace(char oldChar, char newChar)|oldChar를 newChar로 변경, 변경된 String을 새로 생성하게 된다|
|String substring(int from, int to)| from 부터 to - 1까지 자른다 |
|String trim()| 양 옆에 있는 공백을 제거해준다 |
|String repeat(int count)| count만큼 반복 |
|char[] toCharArray| String -> char[] 형으로 변환 |
> String.valueOf(charp[] ch)을 이용해 char[] -> String 형으로 변환

```
String s = "This is a string.";  
  
System.out.println(s.length()); // int length() : String의 길이를 반환  
System.out.println(s.charAt(2)); // char charAt(int index) : 해당 Index에 위치한 Character 반환  
System.out.println(s.indexOf('a')); // 해당 String에서 Index 위치는 확인  
System.out.println(s.equalsIgnoreCase("THIS IS A STRING>")); // Boolean equalsIgnoreCase() : 대소문자 상관없이 비교  
System.out.println(s.replace('i','t')); // String replace(oldChar, newChar) : oldChar를 newChar로 변경, 변경된 String을 새로 생성하게 된다.  
System.out.println(s.substring(3,9)); // String substring(int from, int to) : from 부터 to - 1까지 자른다, 3~8번까지 잘라준다.  
System.out.println(" wwef ".trim()); // String trim() : 양 옆에 공백이 남아있다면 제거해준다.  
System.out.println("*".repeat(10)); // String repeat(int count) : count 만큼 반복  
  
char[] characters = s.toCharArray() // String.toCharArray() : String -> Char[] 로 변환
```
