# 자료 입출력

## 출력
### 1. println
- System.out.print()에 \r\n 입력한 것
  - "\r" : carriage return
  - "\n" : new line


### 2. printf
- formmating 방법

지시자 | 내용
------------ | -------------
%s | String, 문자열
%b | Boolean, 논리
%d | Decimal Number, 정수
%f | Floating Number, 실수
%x, %X | Hex, 16진수
%o, %O | Octal, 8진수
%e, %E | Exponent, 10^n
%c | Character, 문자
%n | \n와 동일
%3d | 최소한 3칸 확보 후, 오른쪽 정렬
%-3d | 최소한 3칸 확보 후, 왼쪽 정렬
%5.4f | 최소한 5칸 확보 및 소수점 넷째자리까지 표시

- 예제
```
  System.out.printf("%s %s %s", "123", "1414", "15234");
  System.out.printf("%b\n", true);
  System.out.printf("%d 0x%x 0x%X 0%o\n", 15, 15, 15, 15);
  System.out.printf("%f\n", 14.23);
  System.out.printf("%f\n", 14.23F);
```



## 입력(Scanner)
- Scanner의 Method 경우, 입력을 받을 때까지 기다리는 Blocking Method 이다. 

Scanner 함수 | 내용
------------ | -------------
next() | 공백으로 구분된 String을 입력 받는다.
nextInt() | Integer 형의 값을 입력 받는다. 자료형이 다른 경우 에러 발생
nextLine() | Enter로 구분하여 String을 입력받는다.

- 예제
```
  Scanner scanner = new Scanner(System.in);
  String s= scanner.next();
  System.out.println(scanner.next());
  System.out.println(scanner.nextInt());  // 자료형이 안 맞으면 에러 발생
  System.out.println(scanner.nextLine());
```

