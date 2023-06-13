# 암호화/복호화 (Encryption/Decriptin)
데이터를 암전하게 보호하기 위해 사용

## 암호화 구간
1. 저장 데이터 암호화 (Encyption At Rest)
    * 저장된 데이터를 암호화해서 시스템 손상 또는 데이터 무단 반출로부터 보호
    * 보통 AES(Advanced Encryption Standard)가 사용됨
2. 전송 중인 데이터 암호화 (Encryption In Transit)
    * 전송 전에 데이터를 암호화하고, 도착 시 복호화하여 통신 중 가로채기로 부터 보호
    * TLS/SSL, S/MIME 등 사용
3. 사용 중 암호화 (Encryption In Use)
    * 처리 중인 데이터를 암호화하여 메모리의 데이터 손상 또는 데이터 문단 반출로부터 보호
    * 암호화하기 매우 까다로움
</br>



---
# 암호화 방식
암호화 방식에는 크게 양방게 암호화와 단반향 암호화가 있다.
* 단방향 암호화는 평문을 암호문으로 암호화는 가능하지만, 복호화는 불가능하다.
    > 복호화가 불가능하기 때문에, 해당 데이터가 정확한 데이터인지를 비교하는데 사용된다(무결성)
* 양방향 암호화는 암호화/복호화 모두 가능
    > 암호화/복호화가 가능한 키를 생성하여 권한을 가진 대상만 접근할 수 있게 한다(기밀성)
* Ref : https://velog.io/@inyong_pang/Programming-%EC%95%94%ED%98%B8%ED%99%94-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EC%A2%85%EB%A5%98%EC%99%80-%EB%B6%84%EB%A5%98
</br>


# 양방향 암호화
평문을 암호화하고, 암호화된 정보를 다시 평문으로 복호화하는 기법
* 대칭키와 비대칭키로 나뉘어진다. 각각의 장단점이 존재하며 요즘에는 비대칭키로 대칭키를 암호화하고 대칭키로 전송 데이터를 암호화하는 방식으로 같이 사용하기도 한다(Diffie-Hellman Key Exchange Algorithm)
</br>

## 대칭키 암호화 (Symmetic Encryption)
같은 키를 가지고 데이터를 암호화/복호화를 한다. 정보 교환 당사자 간에 동일한 키를 공유해야 하기 때문에 키를 비공개한다.
* 장점 : 비대칭키에 비해 속도가 빠름
* 단점 : 키 교환 시 탈취 문제, 교환 당사자가 많아질수록 관리해야 되는 키가 많아져 관리 복잡도 증가
* 알고리즘 종류
    - AES
    - DES
    - SEED
    - RC4


## 비대칭키 암호화 (Asymmectric Encryption)
서로 다른 키를 가지고 Server(Private Key)와 Client(Public Key)가 암호화/복호화를 진행한다. 수신자만 개인키를 가지고 있고, 공개키는 외부에 등록되어 모든 사람들에게 공유되어 진다.
* 장점 : 공개 키는 공개되어 있기 때문에 키 교환 등으로 인한 유출을 크게 신경쓰지 않아도 됨, 키 관리 복잡도도 감소
* 단점 : 대칭키에 비해 속도가 느림
* 알고리즘 종류
    - RSA
    - DSA
    - ECC


### Diffie-Hellman Key Exchange Algorithm
* 디피-헬먼 키 교환 알고리즘
</br>
</br>


# 단반향 암호화
평문을 암호화하는 기법으로 암호화된 정보를 다시 평문으로 복호화하지는 못함

## Hash Algorithm
Hash 알고리즘은 이용해 고정된 길이의 암호화된 데이터로 변환하는 기법
* Hash 알고리즘은 특정 입력에 대해 항상 같은 Hash 값을 리턴한다. 이를 이용하여 데이터의 진위 여부 및 변조 유무를 확인할 때 많이 사용한다.
> 단점으로 다른 입력에도 같은 Hash 값을 리턴하기도 한다. 그렇기 때문에 중복이 적은 Hash 알고리즘일 수록 좋은 알고리즘이며, 이를 보완하기 위해 Salt 값을 넣거나, 알고리즘을 여러번 돌린다(ex> Salt 값 추가 : 패스워드 앞뒤에 임의 값을 넣고 알고리즘을 돌린다)
* 알고리즘 종류
    - MD5
    - SHA-256
    - SHA-512
    - SHA-3

### Message Digest
* Message를 Hash 한다.
* MD5, SHA-1, SHA2, SHA-3
</br>

### MAC (Message Authentication Code)
* HMAC, NMAC, CBC-MAC
</br>
</br>


---
### Ref
* https://medium.com/@jamessoun93/ssh%EB%9E%80-%EB%AC%B4%EC%97%87%EC%9D%B8%EA%B0%80%EC%9A%94-87b58c521d6f# 
* https://yoonix.tistory.com/76?category=756509