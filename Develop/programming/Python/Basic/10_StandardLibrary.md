# 10. Standard Library



# Collection
* import collections
* collections.Counter()





---
# 
* map(func, iterable)
    * iterable을 받아, 각 요소에 함수를 적용
    ```python
    data = [[1, 2, 3, 4], [5, 6, 0, 9]]
    minValue = min(map(min, data))      # 0
    ```



---
# Math
* sum()
    * q
    ```
    # sum(iterable)

    ```
* comb()
    * 조합을 반환하는 Math 함수
    ```
    import math
    
    math.comb(balls, share)
    math.factorial(balls)
    ```
* factorail()

* max(iterable)
* min(iterable)
* abs(number)
    * 절대값 반환
* divmod()
    * 몫과 나머지 Tuple로 반환
    ```python
    # divmod(number, number)
    a = 5
    b = 2
    print(divmod(5, 2))     # (2.5, 0)
    ```


---
# Hash
* hash()
    * Python에서 제공하는 내장 함수
    
* SHA-1 & SHA-256
    * https://www.fun-coding.org/Chapter09-hashtable.html
    * 안전한 Hash 알고리즘
    * import hashlib, hashlib.sha1() & hashlib.sha256()
    * Example
        ```
        import hashlib
        data = 'test'.encode()
        hash_obj = hashlib.sha1()       # 해쉬 알고리즘 선택
        hash_obj.update(data)           # 해시 값 변환
        hex_dig = hash_obj.hexdigest()  # 16진수로 변환
        
        #######
      
        hash_obj = hashlib.sha256()
        hash_obj.update(b'test2')
        hex_dig = hash_obj.hexdigest()        
        ```

* 가장 큰 값
    * str * 2 등을 통해 같은 자리수로 만들어 비교
* 해시 값으로 변경하여 비교 (해시는 유일하기 때문에)
    * 해시 값 위치에 값을 저장 dict[hash] = value
    * hash = key
* set을 이용해 비교 (중복제거)    


# String
* join()
    * List 안의 str형을 구분자 기준으로 합쳐서 반환한다.
    ```python
    # <구분자 string>.join(list)
    print('_'.join("def"))      # "d_e_f"
    ```

* replace()
    * 문자열 바꾸기
    ```python
    my_string = "BCBdbe"
    letter = "B"
    answer = my_string.replace(letter, '')
    print(answer)   # Cdbe
    ```

* split()
    * 문자열 자른 후 리스트 반환
    * 구분자가 없는 경우 공백(space, tab, enter 등)으로 구분
    ```python
    # <String>.split(<구분자>)
    test = "My body is instrument"
    tList = test.split()
    print(tList)    # ['My', 'body', 'is', 'instrument']

    test = "c:b:b:d"
    tList = test.split(":")
    print(tList)    # ['c', 'b', 'b', 'd']
    ```
* find()
    * 문자열 검색
    * 문자열이 있다면 첫번재 문자열 위치(index) 반환, 없다면 -1 반환
    ```python
    word = "1Python"
    search = "Py"
    print(word.find(search))    # 1

    word = "1Python"
    search = "llPy"
    print(word.find(search))    # -1
    ```

* strip(), lstrip(), rstrip()
    * 양 끝에 있는 문자열 제거. 입력 값이 없을때는 공백 제거
    ```python
    # <string>.strip(<find>)
    text = ",,,,,123.....water....pp"
    print(text.lstrip(',123.p'))    # water....pp
    print(text.rstrip(',123.p'))    # ,,,,,123.....water
    print(text.strip(',123.p'))     # water

    ```
        


---
# 정규표현식 regex
Python 정규표현식 모듈 : re
* re.compile()
    * 정규표현식을 객체 타입으로 저장
    ```python
    # re.compile("<정규표현식>")

    pattern = re.compile("[0-9]")
    print(type(pattern))             # <class 're.Pattern'>
    ```
    
* re.sub()
    * 문자열 치환
    ```python
    # re.sub(정규표현식, 치환 문자, 문자열)

    my_string = "aAb1B2cC34oOp"
    pattern = re.compile("[0-9]")
    ch_string = re.sub(pattern, '', my_string)  # aAbBcCoOp
    ```

* re.findall()
    * 모든 매치를 찾아 리스트로 반환
    ```python
    my_string = "aAb1B2cC34oOp"
    pattern = re.compile("[0-9]")
    numbers = pattern.findall(my_string)        # ['1', '2', '3', '4']
    ```




