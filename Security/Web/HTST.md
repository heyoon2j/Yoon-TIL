# HTST (HTTP Strict Transport Security)
* Web site 접속 시, HTTPS Protocol 만으로 접속하게 하는 기능
* Web Browser, Web Server 모두 지원해야 기능을 사용할 수 있다
* Ref : https://m.blog.naver.com/aepkoreanet/221575708943


## 동작 과정
* 일반적으로 사용자는 HTTPS 서비스를 제공하는지 모른다(URL만 치고 넘어오기 때문에).
1. 사용자는 Browser에 URL 입력
2. 서버는 HTTPS Redirection Response 전달
3. Browser는 HTTPS로 다시 접속 시도