# Openssl

---
# 인증서 통신 과정




---
## 인증서 생성 (Create Private Key)
```
#openssl genrsa -[messageDigest_value] -out [outputFile_name] 2048
$ openssl genrsa -aes256 -out server.key 2048
```
* aes256 암호화로 대칭 키를 생성하고, 2048bit RSA 암호화 인증서를 생성

### 패스워드 제거
```
$ cp server.key server.key.origin
$ openssl rsa -in server.key -out server_pri.key
```
* 로드밸런서나 아파치 등에서 사용할 때는 패스워드를 제거해준다.

## 인증 요청서 생성
```
$ openssl req -new -key server.key.origin -out server.csr
```

## 인증서 생성
```
$ openssl x509 -req -days 3650 -in server.csr -signkey server.key.origin -out server.crt
```
</br>


## 암호화 알고리즘 확인
```
$ openssl ciphers -v
```
암호화 알고리즘 확인