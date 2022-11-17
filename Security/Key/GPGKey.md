# GPG Key
* GunPG == GUN Privacy Gurad
* Encryption/Decryption Key

## GPG 설치

## GPG Key 생성
1. ```gpg --full-generate-key``` 명령어 사용
2. 원하는 Key 선택
    ```
    Please select what kind of key you want:
    (1) RSA and RSA (default)
    (2) DSA and Elgamal
    (3) DSA (sign only)
    (4) RSA (sign only)
    (14) Existing key from card
    ```
    * 일반적으로 1번 선택하면 된다.
    * 
3. Key 크기 지정
    ```
    RSA keys may be between 1024 and 4096 bits long.
    What keysize do you want? (3072)
    ```
    * 원하는 크기 지정하면 된다.
4. Key 만료 기간 지정
    ```
    Please specify how long the key should be valid.
         0 = key does not expire
      <n>  = key expires in n days
      <n>w = key expires in n weeks
      <n>m = key expires in n months
      <n>y = key expires in n years
    Key is valid for? (0)
    ```
    * 만료하지 않으려면 0 입력 or Enter를 치면 된다.
5. 사용자 ID 및 Email 정보 입력
6. 보안 암호 입력
7. GPG 키 목록에서 사용하려는 GPG 개인 키 ID를 복사한다.
    ```
    $ gpg --list-secret-keys --keyid-format=long

    gpg: checking the trustdb
    gpg: marginals needed: 3  completes needed: 1  trust model: pgp
    gpg: depth: 0  valid:   1  signed:   0  trust: 0-, 0q, 0n, 0m, 0f, 1u
    /c/Users/CG01-USER/.gnupg/pubring.kbx
    -------------------------------------
    sec   rsa3072/652349692EC127E2 2021-10-01 [SC]
      8F5E328529A371D229C1F56F652349692EC127E2
    uid                 [ultimate] devUser0 (Terraform Test)        <devUser0@example.com>
    ssb   rsa3072/1E90B86D0D38984D 2021-10-01 [E]
    ```
    * 여기에서 키 ID는 ```652349692EC127E2```이다.
    * 공개키는 ```gpg --list-keys``` 명령어 사용
8. Key ID로 Public Key 생성
    ```
    $ gpg --armor --export 652349692EC127E2 > iam.gpg.pubkey
    ```
9. 키를 추가하면 된다.