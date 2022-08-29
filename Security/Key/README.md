# Encryptoin/Decription



## 


## 키 교환 알고림즘
* 다음은 암호화 알고리즘이 아닌 키를 교환하기 위ㅎ
### 대칭 키 암호화 (Symmetic Encryption)
* 동일한 키를 가지고 Server와 Client가 암호화/복호화를 진행한다.
* 장점
    * 속도가 빠르다. 
* 단점
    * 키 교환 과정에서 유출되는 경우, 모든 암호화된 통신이 노출된다.
</br>

### 비대칭 키 암호화 (Asymmectric Encryptionㅎ
* 서로 다른 키를 가지고 Server(Private Key)와 Client(Public Key)가 암호화/복호화를 진행한다.
* Client가 Public Key를 이용하여 Date를 암호화 ---> Server는 Public Key로 암호화된 파일을 Private Key를 이용하여 Date를 복호화
* 장점
    * 공개 키는 공개되어 있기 때문에 키 교환 등으로 인한 유출을 크게 신경쓰지 않아도 된다. 
* 단점
    * 대칭 키 암호화에 비해 속도가 느리다.
</br>


### Diffie-Hellman Key Exchange Algorithm
* 디피-헬먼 키 교환 알고리즘
* 



 


* Ref
* https://medium.com/@jamessoun93/ssh%EB%9E%80-%EB%AC%B4%EC%97%87%EC%9D%B8%EA%B0%80%EC%9A%94-87b58c521d6f# 
* https://yoonix.tistory.com/76?category=756509