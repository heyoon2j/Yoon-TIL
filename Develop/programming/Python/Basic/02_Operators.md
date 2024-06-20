# 2. Operators
* 연산자(Operator) : 연산에 사용되는 표시나 기호
* 피연산자(Operand) : 연산에 사용되는 데이터
* 연산식(Expression) : 연산자와 피연산자를 이용하여 연산 과정을 기술한 것
</br>

## 2.1. 산술 연산자 (Arithmetic operators)
* 나눗셈은 항상 float을 반환한다.
* 정수와 실수의 연산인 경우, 실수로 형변환 된다.

| Operator | Description | Example |
|----------|-------------|---------|
| + | 덧셈 | 5 + 2 = 7 |
| - | 뺄셈 | 5 - 2 = 3 |
| * | 곱셈 | 5 * 2 = 10 |
| ** | 지수 | 5 ** 2 = 25 |
| / | 나누기 | 5 / 2 = 2.5 |
| // | 나누기(소수점 이하는 버림) | 5 // 2 = 2 |
| % | 나머지 | 5 % 2 = 0.5 |

</br>
</br>


## 2.2. 비교 연산자 (Comparison Operators)
* ==, !=, >, <, >=, <= 등이 있다.

| Operator | Description | Example |
|----------|-------------|---------|
| == | 같음 | 5 == 5 |
| != | 다름 | 5 != 3 |
| > / < | 초과/미만 | 5 > 3 |
| >= / <= | 이상/이하 | 5 >= 3 |

</br>
</br>


## 2.3. 대입 연산자 (Assignment Operators)
* =, +=, -=, *=, /=, %=, //= 등이 있다.

| Operator | Description |
|----------|-------------|
| = | 대입 |
| += | a += b, a = a + b |
| -= | a -= b, a = a - b |
| *= | a *= b, a = a * b|
| /= | a /= b, a = a / b|
| %= | a %= b, a = a % b|
| //= | a //= b, a = a // b|

</br>
</br>


## 2.4. 논리 연산자 (Logical Operators)
| Operator | Description | Example |
|----------|-------------|---------|
| and | a && b | a and b |
| or | a \|\| b | a or b |
| not | !a | not a |

* __Short Circuit Evaluation__
    * 앞의 조건에 따라 뒤의 조건은 확인하지 않음으로 불필요한 연산을 줄여 효율적이다.
    * 논리 곱(&&)은 앞의 항이 false 이면 뒤 항의 결과를 평가하지 않아도 false임
    * 논리 합(||)은 앞의 항이 true 이면 뒤 항의 결과를 평가하지 않아도 true임
</br>
</br>


## 2.5. Bitwise 연산자
* 컴퓨터 내에서 데이터를 표현하는 최소 단위를 비트(Bit, Binary digit)라고 한다.
* Bit는 2진수 0, 1 로 표현되며, 실제로 컴퓨터는 모든 숫자를 2진수로 판단한다.
* &, |, ^, ~, <<, >> 연산자

| Operator | Description | Example |
|----------|-------------|---------|
| & | Bitwise AND | a & b |
| \| | Bitwise OR | a \| b |
| ^ | Bitwise XOR | a ^ b |
| << | 좌측 피연산자의 각 비트를 왼쪽으로 우측 피연산자만큼 이동시킨다. 빈자리는 0으로 채운다. | a << 2 |
| >> | 좌측 피연산자의 각 비트를 오른쪽으로 우측 피연산자만큼 이동시킨다. 빈자리는 Sign Bit로 채운다. | a >> 1 |

</br>
</br>


## 2.6. 멤버십 연산자
* in, not in 연산자
* Left Operand가 Right Collection에 포함되어 있는지, 아닌지 확인
```
a = [1, 2, 3, 4]
b = 3 in a
c = 5 not in a
print(b)        # True
print(c)        # True
```
</br>
</br>


## 2.7. 삼항 연산자
* if 와 else 문 사용
* ```(true) if (condition) else (false)```
```
x = 10
y = 20
result = (a-b) if (a > b) else (b - a)
```
</br>
</br>   


## 2.8. Identity 연산자
* is, is not 연산자
* 동일한 Objcet를 가리키는지 아닌지 체크
```
a = "ABC"
b = a
print(a is b) # True
```
</br>
</br>


## 2.9. Slice operator
* [:] 연산자
* ```[start : stop : 증가폭]```
* Slice 연산은 요청한 항목들을 포함한 얕은 복사복을 반환한다!!
    * 객체의 주소만을 복사한다.
    * 복사를 하기 때문에 속도가 느리다.
* 문자열 경계
    ```
     +---+---+---+---+---+---+
     | P | y | t | h | o | n |
     +---+---+---+---+---+---+
     0   1   2   3   4   5   6
    -6  -5  -4  -3  -2  -1
    ```
</br>
</br>


## 2.10. None
* 빈 변수를 입력할 때는 ```None```을 할당해주면 된다.
```
a = None
```
