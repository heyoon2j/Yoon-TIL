# CloudFront
CDN(Content Delivery Network) 서비스로 콘텐츠를 사용자에게 더 빨리 배포하도록 지원할 수 있다.
* 구축 시 생각해야 될 내용들
1) 접근 방법
    * CloudFront로 통합
    * Origin Server와 CDN 분리 (콘텐츠에 CDN URL 표시)
2) 액세스 제한
3) 보안
4) 캐싱 주기 (배포 시 유의)
5) Keep-Alive
6) 로그

</br>

## Architecture
End Point에 따라 아키텍처는 크게 2가지 방안이 있다. (참고: https://blog.leedoing.com/35)
1) Origin Server와 CDN으로 2개 가주가는 방법 (콘텐츠에 CDN URL 표시)
    * 콘텐츠 안에 CDN URL을 표기한다.
        ```
        <img src="http://cdnUrl.com/img/test.png">
        ```
    * 단점 : End Point가 두 군데이기 때문에 보안 및 액세스 제어에 대해서 양쪽을 신경써야 한다.

2) CDN으로 통합 (CDN이 가장 앞단으로, URL에 따라 해당하는 Origin Server에게 전달)
    * 콘텐츠 안에 CDN URL을 표기한다.
        ```
        <img src="http://cdnUrl.com/img/test.png">
        ```
    * 단점 : CDN 기능이 좋아야 한다.

https://techblog.woowahan.com/2699/



## 보안
HTTPS 사용 여부를 결정

</br>
</br>


---
## 액세스 제어
액세스 제어으로는 크게 2가지가 있다.
1) 캐싱된 CDN 파일에 대한 액세스 제한
2) Origin에 대한 액세스 제한

### CDN 파일에 대한 제한
방법으로는 서명된 URL과 서명된 쿠키 사용이 있다.
1. 서명된 URL
    * 개별 파일에 대한 액세스 제한
    * 쿠키를 지원하지 않는 클라이언트의 경우 사용
2. 서명된 쿠키
    * 파일 여러 개에 대한 액세스 권한 제한
    * 현재의 URL을 변경하고 싶지 않은 경우 사용
3. 제약사항
    * 개발자가 개발 코드를 넣어야 한다.
    * https://docs.aws.amazon.com/ko_kr/AmazonCloudFront/latest/DeveloperGuide/private-content-choosing-signed-urls-cookies.html


### AWS 오리진에 대한 제한




### 지리적 제한


</br>
</br>

---
## Log



</br>
</br>



## Deploy
1. Contents를 오리진 서버에 배포
2. 캐싱 처리
3. 
4. 

</br>
</br>


## 구축
1. Distribution Config 설정


2. 

</br>
</br>


### Troubleshooting
* 동적 IP으로 인한 방화벽 (동적)
    * 일반적으로 범위로 적용
* 콘텐츠에 대한 권한
    * CORS
    * CDN 파일 액세스 권한
* Origin에 배포 후, Edge에서의 캐싱 만료 및 다시 다운로드하도록 해야 됨
</br>
