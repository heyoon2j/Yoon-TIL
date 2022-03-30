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