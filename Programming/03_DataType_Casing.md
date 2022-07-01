# Data Type
![DataType](img/DataType.png)
* Data Type은 크게 기본형(Primitive Type), 참조형(Reference Type)으로 구분된다.

## 기본형 (Primitive Type)
* 값을 변수에 직접 저장하는 자료형
</br>

## 참조형 (Reference Type)
* 값을 직접 저장하지 않고, 값의 위치(주소)를 저장하는 자료형
    ```
    # Example
    Test a = new Test();
    ```
> Python의 모든 Data Type은 참조형으로 되어 있다.
</br>

----
## Data Type (C/C++, JAVA)
| Data Type | Byte Size           | Range                               |
|-----------|-----------------------|---------------------------------------|
| boolean | 1 Byte | false(거짓), true(참) |
| char | 2 Byte	|  |
| byte | 1 Byte | -2^7 ~ 2^7 - 1 ( -128 ~ 127 ) |
| short	| 2 Byte | -2^15 ~ 2^15 - 1 ( -32768 ~ 32767 ) |
| int | 4 Byte | -2^31 ~ 2^31 - 1 ( -약 21억 ~ 약 21억 ) |
| long | 8 Byte | -2^63 ~ 2^63 - 1 |
| float | 4 Byte | -m * 10^e ~ m * 10^e  // m = 23, e = 8 ( Max : 3.4028235 * 10^38 ) ( Min : 1.4 * 10^-45, 절대값 기준 ) |
| double | 8 Byte | -m * 10^e ~ m * 10^e  // m = 52, e = 11 ( Max : 1.8 * 10^308 ) ( Min : 4.9 * 10^-324 ) |
* 일반적으로 리터럴 상수는 Integer, 실수는 Double을 사용한다(언어마다 다르다!)
> 1/2 Byte Data Type은 연산 시에 4 Byte인 int로 자동 Casting 후에 연산을 진행하는데 이유는 컴퓨터가 연산을 처리할 때 32bit(4 Byte) 또는 64bit(8 Byte) 연산 체제를 가지고 있기 때문이다.

> 실수 연산은 오차가 존재하기 때문에 이에 대한 처리가 필요하다!
</br>

## Data Type (Python)
| Data Type | Description           | Example                               |
|-----------|-----------------------|---------------------------------------|
| int       | 정수형 데이터          | a = 100   # 0xFF(16진수), Oo56(8진수)  |
| float     | 소수점을 포함한 실수형  | a = 10.25                             |
| complex   | 복소수                 | a = 3 + 1j                            |
| str       | 문자열                 | a = "abc"                             |
| bool      | 참, 거짓               | a = True   # False                    |
| None      | Null                  | a = None                              |

</br>

### Numbers
* Python이 지원하는 정수(Integer), 실수(Real Number), 복소수(Complex Number) 3가지가 있다.
1) 정수 (Integer)
    - 메모리가 허용하는 선에서 무한대의 정수를 사용    
    => Data Type : ```int```
2) 실수 (Real Number)
    - 8 바이트 크기의 부동 소수형 제공
    - 소수점 15자리까지 표현된다.
    - 정밀한 계산을 하기는 힘들다.
    => Data Type : ```float```
3) 복소수 (Complex Number)
    - "a + bj" 와 같이 표현
    => Data Type : ```complex```

```python
print("PYTHON")
print("---------- Numbers ----------")

a = 2
print(type(a))          # <class 'int'>

b = 3.15
print(type(b))          # <class 'float'>

v = 2 + 3j 
v.real    # 2
v.imag    # 3
print(type(v))          # <class 'complex'>
print(type(v.real))     # <class 'float'>
print(type(v.imag))     # <class 'float'>

```
</br>


### String
* 작은 따음표 또는 큰 따음표를 이용하여 문자열 표현
* 문자열의 특성은 다음과 같다.
    1) 기본적으로 문자열은 불변하다(immutable). 그러므로 대입 연산이 불가능하다.
    2) 인덱싱과 슬라이싱 가능
    3) 이스케이프(\)를 사용하여 특수문자 표현 가능
    4) """""" 또는 ''''''를 사용하여 여러줄 표현 가능
        * 줄 넘김 문자는 자동으로 문자열에 포함된다. 하지만 줄 끝에 \를 붙여 방지할 수 있다.
    5) 문자열로 연산이 가능

</br>


### Byte


</br>


### Boolean




----

## Python Data Structure
1) list
2) tuple
3) set
4) dictionary
</br>

### 


</br>
</br>



----

# Casting


개발 공부 순서
1 - 변수 스코프 정리
2 - 데이터 타입 / 타입 캐스팅
3 - 연산자
4 - 조건문 / 제어문 / 반복문
5 - 함수
6 - 클래스 / 상속 overriding overloading
7 - 인터페이스 / 추상 클래스 
7 - 모듈
8 - 입출력
9 - 예외처리
10 - 라이브러리