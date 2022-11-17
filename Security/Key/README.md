# Encryptoin/Decription
* 암호화는 크게 양방향과 단방향 암호화로 분리된다.
* 단방향 암호화는 평문을 암호문으로 암호화는 가능하지만, 복호화는 불가능하다.
    > 복호화가 불가능하기 때문에, 해당 데이터가 정확한 데이터인지를 비교하는데 사용된다(무결성)
* 양방향 암호화는 암호화/복호화 모두 가능
    > 암호화/복호화가 가능한 키를 생성하여 권한을 가진 대상만 접근할 수 있게 한다(기밀성)
* Ref : https://velog.io/@inyong_pang/Programming-%EC%95%94%ED%98%B8%ED%99%94-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EC%A2%85%EB%A5%98%EC%99%80-%EB%B6%84%EB%A5%98
</br>
</br>



# 단방향 암호화
## Hash Algorithm
* 해시 함수는 임의의 데이터를 고정된 길이의 데이터로 매핑하는 함수
* 데이터 무결성을 위해 사용(송신자가 보낸 메세지가 변조 유무)
</br>

### Message Digest
* Message를 Hash 한다.
* MD5, SHA-1, SHA2, SHA-3
</br>

### MAC (Message Authentication Code)
* HMAC, NMAC, CBC-MAC

</br>
</br>



# 양방향 암호화
## 키 교환 알고림즘
* 다음은 암호화 알고리즘이 아닌 키를 교환하기 위ㅎ
### 대칭 키 암호화 (Symmetic Encryption)
* 동일한 키를 가지고 Server와 Client가 암호화/복호화를 진행한다.
* 종류 : AES > DES
* 장점
    * 속도가 빠르다. 
* 단점
    * 키 교환 과정에서 유출되는 경우, 모든 암호화된 통신이 노출된다.
</br>

### 비대칭 키 암호화 (Asymmectric Encryptionㅎ
* 서로 다른 키를 가지고 Server(Private Key)와 Client(Public Key)가 암호화/복호화를 진행한다.
* Client가 Public Key를 이용하여 Date를 암호화 ---> Server는 Public Key로 암호화된 파일을 Private Key를 이용하여 Date를 복호화
* Data == Message + 대칭키 + MAC(Message Authentication Code) 등
* 종류 : RSA
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