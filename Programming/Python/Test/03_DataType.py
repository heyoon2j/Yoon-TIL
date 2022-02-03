##############################################
# Data Type
"""
- Data Type은 크게 기본형(Primitive Type)과 참조형(Reference Type)으로 구분된다.
1) 기본형
    - 값을 변수에 직접 저장하는 자료형
2) 참조형
    - 값을 직접 저장하지 않고, 값이 저장된 주소를 저장하는 자료형
    ex> Class a = new Class();

=> Python의 모든 Data Type은 참조형으로 Class로 구성되어 있다!!!



- Python 기본 Data Type
| Data Type | Description           | Example                               |
|-----------|-----------------------|---------------------------------------|
| int       | 정수형 데이터          | a = 100   # 0xFF(16진수), Oo56(8진수)  |
| float     | 소수점을 포함한 실수형  | a = 10.25                             |
| complex   | 복소수                 | a = 3 + 1j                            |
| str       | 문자열                 | a = "abc"                             |
| bool      | 참, 거짓               | a = True   # False                    |
| None      | Null                  | a = None                              |


- Python 자료구조
1) list
2) tuple
3) set
4) dictionary
"""
print("---------- Data Type ----------")



##############################################
# 1. Numbers
"""
- Python이 지원하는 수에는 정수(Integer), 실수(Real Number), 복소수(Complex Number) 3가지가 있다.
    => 그 외로 Class Decimal 이나 Class Fraction 등을 지원(2, 8, 16 진수 표현)

1) 정수 (Integer)
    - 메모리가 허용하는 선에서 무한대의 정수를 사용    
    => Data Type : int
2) 실수 (Real Number)
    - 8 바이트 크기의 부동 소수형 제공
    - 소수점 15자리까지 표현된다.
    - 정밀한 계산을 하기는 힘들다.
    => Data Type : float
3) 복소수 (Complex Number)
    - "a + bj" 와 같이 표현
    => Data Type : complex
"""
print("---------- Numbers ----------")

a = 2
print(type(a))

b = 3.15
print(type(b))

v = 2 + 3j
v.real    # 2
v.imag    # 3
print(type(v))
print(type(v.real))
print(type(v.imag))



##############################################
# 2. Boolean
"""
- Boolean은 True, False 둘 중 하나의 값을 가질 수 있다.
- True == 1, False == 0 으로 대응시킬 수 있다.
"""
print("---------- Boolean ----------")

isTrue = True
isFalse = False
print(type(isTrue))
print(type(isFalse))
x, y = True + 1, False + 0
print(x, y)





##############################################
# 3. String
"""
- 작은 따음표 또는 큰 따음표를 이용하여 문자열 표현
- 문자열의 특성은 다음과 같다.
    1) 기본적으로 문자열은 불변하다(immutable). 그러므로 대입 연산이 불가능하다.
    2) 인덱싱과 슬라이싱 가능
    3) 이스케이프(\)를 사용하여 특수문자 표현 가능
    4) """""" 또는 ''''''를 사용하여 여러줄 표현 가능
        * 줄 넘김 문자는 자동으로 문자열에 포함된다. 하지만 줄 끝에 \를 붙여 방지할 수 있다.
    5) 문자열로 연산이 가능

"""
print("---------- String ----------")


## 1) 문자열은 불변하다
## 2) 인덱싱과 슬라이싱 가능
str = "abc"
print(str[0])
print(str[2])
print(str[0:2])

# Error
## str[0] = d



## 3) 이스케이프(\)를 사용하여 특수문자 표현 가능
str = "123\n456"
print(str)
str = "\"Python\""
print(str)


## 4) """""" 또는 ''''''를 사용하여 여러줄 표현 가능
str = """
abc
def
"""
print(str)

str = """\
abc\
def
"""
print(str)

# 5) 문자열로 연산이 가능
print(3 * 'un' + 'ium')     # 결과 : 




##############################################
# 4. String Formatting
"""



"""
print("---------- Numbers ----------")


##############################################
# 5. List
"""
- 리스트는 [] 를 이용하여 감싸고, 가변하다(mutable). 그러므로 내용을 추가, 변경, 삭제할 수 있다.


"""
print("---------- List ----------")


##############################################
# 6. Tuple
"""



"""
print("---------- Tuple ----------")


##############################################
# 7. Set
"""



"""
print("---------- Set ----------")


##############################################
# 8. Dictionary
"""



"""
print("---------- Dictionary ----------")





