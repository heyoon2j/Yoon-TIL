# CloudFront
CDN(Content Delivery Network) 서비스로 콘텐츠를 사용자에게 더 빨리 배포하도록 지원할 수 있다.
* 구축 시 생각해야 될 내용들
1) 접근 방법
    * CloudFront로 통합
    * Origin Server와 CDN 분리
2) 보안
    * Protocol
    * 액세스 제어
3) 캐싱
    * 정책 및 설정
    * 주기 (배포 시 유의)
4) Keep-Alive
5) 로그

</br>
</br>

---
## Architecture
End Point에 따라 아키텍처는 크게 2가지 방안이 있다. (참고: https://blog.leedoing.com/35)
1) Origin Server와 CDN으로 2개 가주가는 방법 (콘텐츠에 CDN URL 표시)
    * 장점 : 정적 콘텐츠, 동적 콘텐츠마다 다른 통신 설정을 각 각 분리할 수 있다.
    * 단점 : End Point가 두 군데이기 때문에 보안 및 액세스 제어에 대해서 양쪽을 신경써야 한다.

2) CDN으로 통합 (CDN이 가장 앞단으로, URL에 따라 해당하는 Origin Server에게 전달)
    * 장점 : 관리 포인트가 한 군데이기 때문에, 배포, 보안, 통신 장애 포인트 관리 등 관리하기에 편하다. 
    * 단점 : CDN 기능이 좋아야 한다 / 동적 콘텐츠에 대한 동작도 CloudFront를 거치기 때문에 세션, 연결 등에 대한 트래픽도 관리해야 한다.

https://techblog.woowahan.com/2699/



---
## 보안
크게 액세스 제어, HTTPS 관련하여 설정할 수 있다.

* 액세스 제어으로는 크게 2가지가 있다.
    1) CDN에 대한 액세스 제한
    2) Origin에 대한 액세스 제한
* HTTPS 관련 설정으로는 SSL 인증서 및 보안 정책이 있다.
</br>

## 1. CDN에 대한 액세스 제어
### CDN 파일에서 제어
방법으로는 헤더 추가 및 서명된 URL과 서명된 쿠키 사용이 있다.
1. 헤더 추가
    * CDN 설정에서 헤더를 추가한다.
2. 서명된 URL
    * 개별 파일에 대한 액세스 제한
    * 쿠키를 지원하지 않는 클라이언트의 경우 사용
3. 서명된 쿠키
    * 파일 여러 개에 대한 액세스 권한 제한
    * 현재의 URL을 변경하고 싶지 않은 경우 사용
* 제약사항
    * 개발자가 추가로 코드를 추가해야 한다.
    * https://docs.aws.amazon.com/ko_kr/AmazonCloudFront/latest/DeveloperGuide/private-content-choosing-signed-urls-cookies.html
</br>

### WAF를 사용하여 제어
AWS WAF acl을 이용하여 액세스를 제어한다.
</br>

### 지리적 기준에 대한 제어
</br>
</br>

## 2. Origin에 대한 액세스 제어
### AWS 오리진에 대한 제어

</br>
</br>

---
## Log
CloudFront에서 객체에 대한 각 요청의 정보를 로깅하고 이 로그 파일을 Amazon S3 버킷에 저장할 수 있다.
* 배포 설정에서, 활성화/비활성화 할 수 있다.
</br>
</br>


---
## 구축
1. Distribution Config 설정 (CloudFront --> Origin 설정)
    * Origin 도메인, 추가할 Origin URL 경로 : ```{protocol}://{origin_domain}/{add_url}}```
    * Origin 이름
    * 사용자 정의 헤더 추가 (추가할 수 없는 사용자 지정 헤더)
        * CloudFront 요청 식별
        * CORS 활성화
        * 콘텐츠에 대한 액세스 제어
    * Origin Shield 사용 여부 : CloudFront와 Origin 간에 캐싱 사용 여부
    * 연결 시도 / 연결 제한 시간 / 응답 제한 시간 / 연결 유지 제한 시간 : CloudFront --> Origin 간의 통신 제어
    * 프로토콜 / HTTP 포트 / HTTPS 포트 : CloudFront --> Origin 간의 통신 프로토콜
2. Caching 정책 설정
    * 
3. Caching Default 동작 설정 (User --> CloudFront 설정)
    * 경로 패턴
    * View 설정 : 최종 사용자가 통신할 때 허용할 프로토콜 설정
        * 프로토콜 / HTTP Method / Access Control
    * Caching 정책 설정
    * 자동 객체 압축 사용 여부 : 엄청 큰 의미는 모르겠다. 올릴 때 보통 압축을 해두니 말이다...!
    * Mirosoft Smooth Streaming 사용 여부
    * 필드 레벨 암호화 : Application에서 이미 암호화해야 하지 않았을까?
4. 함수 연결
    * 트리거 설정
5. 설정
    * 요금 설정
    * AWS WAF ACL 설정
    * SSL 인증서
    * 보안 정책
    * 기본 루트 객체 지정
    * 로깅
6. Origin 그룹 : Target 이중화가 가능한 경우
7. 동작 : URL마다 캐싱 정책을 다르게 가져가야 되는 경우 
8. 오류 페이지 : 
9. 지리적 제한 : 국가에 대한 액세스 제한
10. 무효화 : 제거할 캐싱 URL 입력
</br>
</br>



---
## Deploy
1. Contents를 오리진 서버에 배포
2. 캐싱 처리
3. Distribution Config 업데이트
4. 
</br>
</br>




---
### Troubleshooting
* 동적 IP으로 인한 방화벽 (동적)
    * 일반적으로 범위로 적용
* 콘텐츠에 대한 권한
    * CORS
    * CDN 파일 액세스 권한
* Origin에 배포 후, Edge에서의 캐싱 만료 및 다시 다운로드하도록 해야 됨
</br>
