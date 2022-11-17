# HTTP/HTTPS Protocl
* Hyper Text Transfer Protocol

## HTTP Protocol Version
## 1. HTTP/1.1
* HTTP의 첫번째 표준 버전
* HTTP/1.1은 Text 기반의 Protocol이다.
* Method: 
* Request
    ```
    ```
* Response
    ```
    ```  

</br>

## 2. HTTP/2
* 2010년에 구글이 실험적인 SPDY Protocol을 구현하여 Client와 Server 간의 데이터 교환을 대체할 수단을 개발. 기초로 HTTP/2가 나오게 됬다.
* HTTP/2는 Text 기반이 아닌 Binary 기반이다.

* Request
    ```
    ```
* Response
    ```
    ```

## 3. gRPC

</br>


## 4. 

</br>


## HTTP Structure
* 크게 HTTP 구조는 Header와 Body로 구분된다.
* Header에는 HTTP Version, Method 등을 저장된다.
* 모든 Request에는 Body가 포함되어 있지 않으며, Body에는 요청에 대한 결과 내용이 


## HTTP Header
### Transfer-Encoding
* https://withbundo.blogspot.com/2017/08/http-20-http-ii-transfer-encoding.html
* hbh(hop-by-hop) 헤더로 데이터를 전송하기 위한 인코딩 형식을 지정
    ```
    Transfer-Encoding: chunked
    Transfer-Encoding: compress
    Transfer-Encoding: deflate
    Transfer-Encoding: gzip
    Transfer-Encoding: identity
    ```
    * chunked
    * compress
    * deflate
    * gzip
    * identity

</br>
</br>


### Content-Length
```
```
* 
* ```Transfer-Encoding: chunked``` 인 경우, 생략된다.
</br>


### X-Forwarded-For
```
```
* 
* 

</br>


### 
```
```
* 
* 

</br>


### 
```
```
* 
* 

</br>


###
```
```
* 
* 

</br>
</br>



## HTTP Request
### Request Method
* 가장 많이 사용되는 Method는 아래와 같다.
1. __GET__: 데이터 검색에 사용된다.
2. __POST__: 데이터 변경 및 삭제 시 사용된다. 

### Structure
![HTTP_Request](../img/HTTP_Request.png)

</br>


## HTTP Response
![HTTP_Response](../img/HTTP_Response.png)

</br>


### Reference
* https://falsy.me/http%EC%9D%98-%EB%B2%84%EC%A0%84-%EB%B3%84-%EC%B0%A8%EC%9D%B4%EC%97%90-%EB%8C%80%ED%95%B4-%EC%95%8C%EC%95%84%EB%B3%B4%EA%B3%A0-ubuntu-nginx%EC%97%90-http-2%EB%A5%BC-%EC%A0%81%EC%9A%A9%ED%95%B4/
* https://developer.mozilla.org/ko/docs/Web/HTTP/Messages



### Keep Alive
* HTTP는 Connectionless 방식으로 연결을 매번 끊고 새로 생성하는 구조. 즉, 요청마다 Handshake 과정을 반복한다.
* Keep-Alive 기능을 사용하게 되면 이미 연결되어 있는 TCP 연결을 재사용한다.
* 이는 Handshake 과정이 생략이 되어 성능 향상을 기대할 수 있는 장점이 있지만, 서버 자원을 많이 사용하게 되는 결과를 초래할 수 있다.
    > 이를 위해 Keep-Alive Timeout 설정이 필요하다!
* https://goodgid.github.io/HTTP-Keep-Alive/
* 