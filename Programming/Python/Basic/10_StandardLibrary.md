# 10. Standard Library



# Collection
* import collections
* collections.Counter()






---
# Math
* sum()
    * 
* comb()
    * 조합을 반환하는 Math 함수
    ```
    import math
    
    math.comb(balls, share)
    math.factorial(balls)
    ```
* factorail()
    



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