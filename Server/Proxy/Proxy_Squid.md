# Squid
* Squid는 Proxy Mode와 Web Accelerator Mode가 있다.
* Proxy Mode : Caching Forward Proxy 동작
* Web Accelerator Mode : Caching Reverse Proxy 동작

### Reference
* https://aws.amazon.com/ko/premiumsupport/knowledge-center/ec2-al1-al2-update-yum-without-internet/
* https://docs.aws.amazon.com/ko_kr/AWSEC2/latest/UserGuide/add-repositories.html
* https://github.com/Yongdae-Kim/HowToUseSquid
* https://blog.naver.com/PostView.naver?blogId=ncloud24&logNo=221848446598&redirect=Dlog&widgetTypeCall=true&directAccess=false
* https://hgko1207.github.io/2020/09/28/linux-3/
* https://sncap.tistory.com/773
 
 -----

## Installation
* Amazon Linux2 / CentOS
    ```
    $ sudo yum install -y squid
    ```

* MacOS
    ```
    $ brew install squid
    ```
</br>
</br>

-----

## Setting
### __Config File 수정__
* ```$ vi /etc/squid/squid.conf``` 파일을 수정한다.
1. Port 설정 : ```http_port [port_num]```
    ```
    #http_port 3128          # Default 3128
    http_port 5000          # Port change to 5000
    ```
</br>


2. ACL Rule List 작성 : ```acl [aclname] [acltype] [argument ...]```
    ```
    acl localnet src 10.0.0.0/8     # RFC1918 possible internal network

    acl SSL_ports port 443
    acl Safe_ports port 80          # http

    acl asexample dst_as 1241


    # ACL Type LIst
    # http://www.squid-cache.org/Doc/config/acl/

    # acltype : IP Address
    acl aclname src ip-address/mask
    acl aclname dst [-n] ip-address/mask

    # acltype : MAC Address
    acl aclname arp      mac-address
	acl aclname eui64    eui64-address

    # acltype : Port & Protocol
    acl aclname port 80 70 21 0-1024
    acl aclname proto HTTP FTP

    # acltype : Domain
	acl aclname dstdomain [-n] .foo.com ...
	acl aclname dstdom_regex [-n] [-i] \.foo\.com

    # acltype : AS Number
    acl aclname src_as number
	acl aclname dst_as number

    # acltype : URL
    acl aclname url_regex [-i] ^http://
	acl aclname urlpath_regex [-i] \.gif$


    # acltype : Authentication
    acl aclname proxy_auth [-i] username
	acl aclname proxy_auth_regex [-i] pattern

    # acltype : SSL
    acl aclname user_cert attribute values...
	  # match against attributes in a user SSL certificate
	  # attribute is one of DN/C/O/CN/L/ST or a numerical OID

	acl aclname ca_cert attribute values...
	  # match against attributes a users issuing CA SSL certificate
	  # attribute is one of DN/C/O/CN/L/ST or a numerical OID  [fast]

    ```
    * ```aclname``` : 원하는 이름으로 설정하면된다.
    * ```aclypte``` : ALC Rule Type
</br>


3. Access Permission 설정 : ```http_access allow|deny [!]aclname```
    ```
    http_access deny !Safe_ports

    http_access deny CONNECT !SSL_ports

    http_access allow localhost manager
    http_access deny manager

    http_access allow localnet
    http_access allow localhost

    http_access deny all
    ```
    * squid.conf에 규칙이 없으면 거부한다.
</br>


4. Log
    ```
    #Log Location
    # /var/log/squid/access.log
    # /var/log/squid/icap.log

    ```
    * ```/bin/squid-migrate-conf.py``` 확인 가능
</br>


5. Cache
* Cache Setting
    ```
    #####
    # Cache Data
    # cache_dir ufs /var/spool/squid 100 16 256 (Default)

    ######
    # Cache Memory
    # cache_mem 256MB (Default: 256MB)
    # memory_cache_mode always|disk|network (Default: always)
    # memory_cache_shared on

    ```
    * ```cache_dir``` : Cache Data를 저장하고 있는 Directory
    * ```cache_mem``` : Cahce Memory Size
    * ```memory_cache_shared``` : SMP 모드에서만 사용 가능하며, 그 외 충족 조건들을 만족해야 한다.

* Cache Refresh : ```refresh_pattern [-i] regex min percent max [options]```
    ```
    refresh_pattern ^ftp: 1440 20% 10080 
    refresh_pattern ^gopher: 1440 0% 1440 
    refresh_pattern -i (/cgi-bin/|\?) 0 0% 0 
    refresh_pattern . 0 20% 4320 
    ```
    * 주어진 요청이 cache에 명중되었는지,cache로 분실되었는지 여부를 결정하는 데 도움을 준다.
    * http://www.squid-cache.org/Doc/config/refresh_pattern/
    * https://intrepidgeeks.com/tutorial/squid-parameterrefreshpattern-description

* Cache Log File
    ```
    # cache_log /usr/local/squid/var/logs/cache.log
    # cache_store_log stdio:/usr/local/squid/var/logs/store.log
	# cache_store_log daemon:/usr/local/squid/var/logs/store.log
    ```
    * 기본으로 설정되어 있는 값이다.


</br>

6. Core Dump
    ```
    coredump_dir /var/spool/squid
    #coredump_dir /var/spool/squid/coredump
    ```
    * 기본 설정은 캐시 디렉토리로 설정된다.
</br>

7. HTTPS
    ```
    # 1) HTTP Connet Tunnel
    # Connect 메서드 사용

    acl SSL_ports port 443
    http_access deny CONNECT !SSL_ports

    # 2) TLS 연결

    https_port [ip:]포트 [모드] tls-cert=certificate.pem [옵션]
    ```
    * 크게 Proxy에 대해 HTTPS 연결 방법으로 3가지가 있다 (https://wiki.squid-cache.org/Features/HTTPS)
        1) Tunnel 연결
            * Client와 Proxy간의 터널을 만들어 전달받는다.
            * 대신 HTTPS 말고도 다른 프로토콜도 터널링이 가능하기 때문에 주의가 필요하다.
        2) TLS 연결
            * Proxy와 TLS 통신을 한다.
            * Reverse Proxy에서 사용한다.
    * 기본적으로 Proxy Server로 들어왔다가 나가기 때문에 가로채기가 가능하다(주의 필요)
</br>


8. SSL
    ```
    auth_param basic program /usr/lib/squid3/basic_ncsa_auth /etc/squid/htpasswd
    auth_param basic realm proxy
    acl myauth proxy_auth REQUIRED
    ```
</br>

9. Reverse Proxy
    ```
    ### Accelerator Mode Set
    http_port 80 accel defaultsite


    ######
    # Cache Peer (for hierarchy architecture)
    # cache_peer hostname type http-port icp-port [options]
    cache_peer backend.webserver.ip.or.dnsname parent 80 0 no-query originserver name=myAccel

    acl our_sites dstdomain your.main.website.name
    http_access allow our_sites
    cache_peer_access myAccel allow our_sites
    cache_peer_access myAccel deny all
    ```
    * http_port의 ```accel``` 옵션 사용하여 Accelerator Mode 사용
    * ```cache_peer```를 이용하여 실제 웹서버 설정
    * https://wiki.squid-cache.org/ConfigExamples
</br>
</br>
