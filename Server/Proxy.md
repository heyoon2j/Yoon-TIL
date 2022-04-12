# Proxy Server


## Forward vs Reverse
* 


### Reference
* https://aws.amazon.com/ko/premiumsupport/knowledge-center/ec2-al1-al2-update-yum-without-internet/
* https://docs.aws.amazon.com/ko_kr/AWSEC2/latest/UserGuide/add-repositories.html
* https://github.com/Yongdae-Kim/HowToUseSquid
* https://blog.naver.com/PostView.naver?blogId=ncloud24&logNo=221848446598&redirect=Dlog&widgetTypeCall=true&directAccess=false
* https://krespo.net/188
* https://hgko1207.github.io/2020/09/28/linux-3/
* https://sncap.tistory.com/773
 

## Installation

* yum
    ```
    $ sudo yum install -y squid
    ```

* brew
    ```
    $ brew install squid
    ```

## Setting
### __Config File 수정__
* ```$ vi /etc/squid/squid.conf``` 파일을 수정한다.
1. Port 설정
    ```
    http_port 3128          # Default 3128
    
    http_port 5000          # Port change to 5000
    ```


2. 접근 설정
    ```
    
    ```


3. 특정 사이트 차단
    ```

    ```


4. SSL
    * 


