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

## Python Data Type
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


### Byte


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