# 10. Standard Library



# Collection
* import collections
* collections.Counter()




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