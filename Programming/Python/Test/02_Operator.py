##############################################
# Operator
"""
- 연산자(Operator) : 연산에 사용되는 표시나 기호
- 피연산자(Operand) : 연산에 사용되는 데이터
- 연산식(Expression) : 연산자와 피연산자를 이용하여 연산 과정을 기술한 것
- 연산자 종류
    1) 산술 연산자
    2) 비교 연산자
    3) 대입 연산자
    4) 논리 연산자
    5) 비트 연산자
    6) 멤버십 연산자
    7) 삼항 연산자
    8) Identity 연산자
    9) Slice 연산자
    10) Nonde 연산자
"""

##############################################
# 1. 산술 연산자
"""
Operator	Description	    Example
+	        덧셈	        5 + 2 = 7
-	        뺄셈	        5 - 2 = 3
*	        곱셈	        5 * 2 = 10
**	        지수	        5 ** 2 = 25
/	        나누기	        5 / 2 = 2.5
//	        나누기(소수점 이하는 버림)	5 // 2 = 2
%	        나머지	        5 % 2 = 0.5
"""
print("---------- 산술 연산자 ----------")

a = 5
b = 2
print(a + b)    # 결과 : 
print(a - b)    # 결과 : 
print(a * b)    # 결과 : 
print(a ** b)    # 결과 : 
print(a / b)    # 결과 : 
print(a // b)    # 결과 : 
print(a % b)    # 결과 : 


##############################################
# 2-2. 비교 연산자
"""
Operator	Description 	Example
==  	    같음	        5 == 5
!=	        다름	        5 != 3
> / <	    초과/미만	    5 > 3
>= / <=	    이상/이하	    5 >= 3
"""
print("---------- 비교 연산자 ----------")


a = 5
b = 2
print(a == b)    # 결과 : 
print(a != b)    # 결과 : 
print(a > b)    # 결과 : 
print(a < b)    # 결과 : 
print(a >= b)    # 결과 : 
print(a <= b)    # 결과 : 



##############################################
# 2.3. 대입 연산자
"""
Operator	Description
=	        대입
+=      	a += b, a = a + b
-=      	a -= b, a = a - b
*=      	a *= b, a = a * b
/=      	a /= b, a = a / b
%=      	a %= b, a = a % b
//=     	a //= b, a = a // b
"""
print("---------- 대입 연산자 ----------")

a = 50
b = 20

a += b
print(a)    # 결과 : 

a -= b
print(a)    # 결과 : 

a *= b
print(a)    # 결과 :

a /= b
print(a)    # 결과 : 

a //= b
print(a)    # 결과 : 

a %= b
print(a)    # 결과 : 


##############################################
# 2-4. 논리 연산자
"""
Operator	Description	Example
and     	a && b  	a and b
or      	a || b      a or b
not     	!a	        not a

- Short Circuit Evaluation
=> 앞의 조건에 따라 뒤의 조건은 확인하지 않음으로 불필요한 연산을 줄여 효율적이다.
=> 논리 곱(&&)은 앞의 항이 false 이면 뒤 항의 결과를 평가하지 않아도 false임
=> 논리 합(||)은 앞의 항이 true 이면 뒤 항의 결과를 평가하지 않아도 true임
"""
print("---------- 논리 연산자 ----------")

x = True
y = False
print(x and y)      # 결과 : 
print(x or y)       # 결과 :
print(not x)           # 결과 : 



##############################################
# 2-5. Bitwise 연산자
"""
- 컴퓨터 내에서 데이터를 표현하는 최소 단위를 비트(Bit, Binary digit)라고 한다.
- Bit는 2진수 0, 1 로 표현되며, 실제로 컴퓨터는 모든 숫자를 2진수로 판단한다.

Operator	Description	    Example
&	        Bitwise AND 	a & b
|	        Bitwise OR	    a | b
^	        Bitwise XOR	    a ^ b
<<	좌측 피연산자의 각 비트를 왼쪽으로 우측 피연산자만큼 이동시킨다. 빈자리는 0으로 채운다.
>>	좌측 피연산자의 각 비트를 오른쪽으로 우측 피연산자만큼 이동시킨다. 빈자리는 Sign Bit로 채운다.	a >> 1
"""
print("---------- 비트 연산자 ----------")

a = 0b00010
b = 0b00101
print(a & b)    # 결과 :
print(a | b)    # 결과 : 
print(a ^ b)    # 결과 : 
print(a << 2)   # 결과 : 
print(b >> 2)   # 결과 : 




##############################################
# 2-6. 멤버십 연산자
"""
Operator	Description	    Example
in	        포함	        3 in a
not in	    비포함	        4 not in a
"""
print("---------- 멤버십 연산자 ----------")

a = [1, 2, 3, 4, 5]
print(7 in a)           # 결과 : 
print(4 not in a)       # 결과 : 



##############################################
# 2-7. 삼항 연산자
"""
- if 와 else 문 사용
- (true) if (condition) else (false)
"""
print("---------- 삼항 연산자 ----------")

x = 10
y = 20
z = (x + y) if (x > y) else (x - y)    # 결과 :
print(z)


##############################################
# 2-8. Identity 연산자
"""
- is, is not 연산자
- 동일한 Objcet를 가리키는지 아닌지 체크
"""
print("---------- Identity 연산자 ----------")

a = "abc"
b = 3
print(a is b)       # 결과 : 

b = "abc"
print(a is not b)   # 결과 : 

print(type(a))
print(type(b))


##############################################
# 2-9. Slice 연산자
"""
- [:] 연산자.
    => ex> [a:b] : a부터 b-1까지
- Slice 연산은 요청한 항목들을 포함한 얕은 복사복을 반환한다!
    => 객체의 주소만을 복사한다.
    => 복사를 하기 때문에 속도가 느리다.
- 문자열 경계
     +---+---+---+---+---+---+
     | P | y | t | h | o | n |
     +---+---+---+---+---+---+
     0   1   2   3   4   5   6
    -6  -5  -4  -3  -2  -1
"""
print("---------- Slice 연산자 ----------")

str = "I love you."
print(str[2:8])             # 결과 : 
print(str[0:12])            # 결과 : 
print(str)                  # 결과 : 
print(str[-1:12])           # 결과 :
print(str[-4:-1])           # 결과 :

##############################################
# 2-10. None
"""
빈 변수를 입력할 때는 None을 할당해주면 된다.
"""
print("---------- None ----------")

a = None
print(a)

##############################################


"""
class myClass:
    staticValue=0
    
    def __init__(self,x,y):
        self.x=x
        self.y=y


e=myClass(10,20)
f=myClass(20,30)

print(str(id(e.staticValue)) + " / " + str(e.staticValue))
print(str(id(f.staticValue)) + " / " + str(f.staticValue))
print(str(id(myClass.staticValue)) + " / " + str(myClass.staticValue))

e.staticValue = 50
f.staticValue = 100
myClass.staticValue = 150

print(str(id(e.staticValue)) + " / " + str(e.staticValue))
print(str(id(f.staticValue)) + " / " + str(f.staticValue))
print(str(id(myClass.staticValue)) + " / " + str(myClass.staticValue))
"""