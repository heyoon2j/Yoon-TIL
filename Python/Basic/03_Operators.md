# 3. Operators
## 3.1. Arithmetic operators
| Operator | Description | Example |
|----------|-------------|---------|
| + | 덧셈 | 5 + 2 = 7 |
| - | 뺄셈 | 5 - 2 = 3 |
| * | 곱셈 | 5 * 2 = 10 |
| ** | 지수 | 5 ** 2 = 25 |
| / | 나누기 | 5 / 2 = 2.5 |
| // | 나누기(소수점 이하는 버림) | 5 // 2 = 2 |
| % | 나머지 | 5 % 2 = 0.5 |

* 나눗셈은 항상 float을 반환한다.
* 정수와 실수의 연산인 경우, 실수로 형변환 된다.

## 3.2. 비교 operators
* ==, !=, >, <, >=, <= 등이 있다.
        
## 3.3. 할당 연산자
* =, +=, -=, *=, /=, %=, //= 등이 있다.
        
## 3.4. 논리 연산자
* Short Circuit Operator
       
| Operator | Description | Example |
|----------|-------------|---------|
| and | a && b | a and b |
| or | a || b | a or b |
| not | !a | not a |

## 3.5. Bitwise 연산자
* &, |, ^, ~, <<, >> 연산자       
    
## 3.6. 맴버쉽 연산자
* in, not in 연산자
* Left Operand가 Right Collection에 포함되어 있는지, 아닌지 확인
```
a = [1, 2, 3, 4]
b = 3 in a
print(b)
```

## 3.7. 삼항 연산자
* if 와 else 문 사용
* ```(true) if (condition) else (false)```
```
x = 10
y = 20
result = (a-b) if (a > b) else (b - a)
```
   
## 3.8. Identity 연산자
* is, is not 연산자
* 동일한 Objcet를 가리키는지 아닌지 체크
```
a = "ABC"
b = a
print(a is b) # True
```

## 3.9. Slice operator
* [:] 연산자
* Slice 연산은 요청한 항목들을 포함한 얕은 복사복을 반환한다!!
    * 복사를 하기 때문에 속도가 느리다.
* 문자열 경계
    ```
     +---+---+---+---+---+---+
     | P | y | t | h | o | n |
     +---+---+---+---+---+---+
     0   1   2   3   4   5   6
    -6  -5  -4  -3  -2  -1
    ```