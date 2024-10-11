# Bind 설정
</br> 


## Configuration
* 보통 Application 서버에서 ACL은 ANY로 설정하고, iptables, firewalld와 같은 보안 프로그램이나 Third-party 프로그램을 통해 관리한다 (관리 포인트에 대한 모듈화)
* 설정 파일 위치
    1) ```/etc/named.conf``` : Name Server 전체 설정
    2) ```/etc/named.rfc1912.zones``` : Zone File 설정
* 옵션 설정에 대한 우선 순위
    * Zone 설정 -> Server 설정
    1) ```/etc/named.rfc1912.zones```
    2) ```/etc/named.conf```
</br>
</br>


## DNS Server 설정
* ```/etc/named.conf```에서 설정
* Template
    ```
    ======================================================
    # cat /etc/named.conf
    include "/etc/bind/named.conf.options";
    include "/etc/bind/named.conf.logging";
    include "/etc/named.rfc1912.zones"

    ======================================================
    # cat /etc/named.conf.options
    acl trusted { 172.16.17.0/24; 172.16.18.0/24; };
    acl untrusted { 192.168.10.0/23; };


    options {
        listen-on port 53 { any; };
        listen-on-v6 port 53 { any; };

        allow-query { trusted; };

        recursion yes; 
        allow-recursion { trusted; };

        forwarders {
            8.8.8.8;
            8.8.4.4;
        };
    }

    ======================================================
    # cat /etc/named.conf.logging
    logging {
        category default {
            example_log;
        };
        channel example_log {
            file "log/named/example.log" versions 3 size 250k;
            severity info;
            print-category yes;
            print-severity yes;
            print-time yes;
        };
    };
    ```
</br>

### ACL 선언
```
# Create List
# acl <name> {acl_list; ...};
acl trusted { 172.16.30.0/24; 172.16.18.0/24; };
acl untrusted { 192.168.10.0/23; };

allow-query { trusted; };
```
* 액세스 제어 목록을 선언
* 다음 기본 값을 제공한다.
    1) ```any```: 모든 호스트 일치
    2) ```none```: 모든 호스트 제외
    3) ```localhost```: 시스템이 가지고 있는 모든 네트워크 인터페이스와 일치
    4) ```localnets```: 시스템이 가지고 있는 모든 네트워크 인터페이스의 네트워크와 일치


### Logging 설정
```
logging {
    category default {
        example_log;
        test_log;
    };
    # default 데이터 유형은 example_log, test_log 채널에 저장하라
    channel example_log {
        file "log/named/example.log" versions 3 size 250k;
        severity info;
		print-category yes;
		print-severity yes;
		print-time yes;
    };
     channel test_log {
        file "log/named/test.log" versions 3 size 10m;
        severity info;
		print-category yes;
		print-severity yes;
		print-time yes;
    };   
};
```
* category : 데이터 유형으로 Bind에 설정되어 있는 값들이 있다. 확인이 필요
* channel : 데이터 스트림 정의
* versions number : 파일 크기만큼 도달 시, 해당 숫자 파일만큼 새로운 파일을 만들어서 저장
* size : 파일 크기
* severity : 로그 레벨

</br>
</br>


### Options 설정
    ```
    options {
        listen-on port 53 { any; };         // IPv4 Listener
        listen-on-v6 port 53 { any; };      // IPv6 Listener

        allow-query { 172.16.30.0/24; };     // Query를 허용할 Target 설정

        recursion no;                       // Caching NS : yes, Authoritative NS : no (일반적으로 무조건 NO)
        allow-recursion { none; }; // Recursion을 허용할 Target 설정 (Caching 서버로 사용할 경우에 사용)
        # forward { 8.8.8.8; };

        auth-nxdomain = no;                  // conform to RFC1035.
        # nxdomain : 등록되어 있으나 IP주소가 지정되지 않은 도메인 이름 등 IP주소가 지정되어 있지 않은 모든 도메인 이름

        # DNSSEC
        #dnssec-enable yes;
        #dnssec-validation yes;
    }
    ```

* Caching 서버로 사용할 경우
    ```
    options {
        listen-on port 53 { any; };         // IPv4 Listener
        listen-on-v6 port 53 { any; };      // IPv6 Listener

        allow-query { 172.16.30.0/24; };     // Query를 허용할 Target 설정

        recursion yes;
        allow-recursion { localhost; };
        forward {
            8.8.8.8;
        };
        forward only;                       // 이 서버가 모든 요청을 전달하고 자체적으로 요청을 해결하려고 시도하지 않아야 하므로 지시어를 "only"로 설정

        auth-nxdomain = no;                  // conform to RFC1035

        # DNSSEC
        #dnssec-enable yes;
        #dnssec-validation yes;
    }
    ```
</br>
</br>




## Zone 설정
https://dev.dwer.kr/2020/04/bind-9.html
https://bind9.readthedocs.io/en/v9_18_3/reference.html
https://froghome.tistory.com/37

```
// Provide a reverse mapping for the loopback
// address 127.0.0.1
zone "0.0.127.in-addr.arpa" {
     type primary;
     file "localhost.rev";
     notify no;
     allow-transfer { none; };
};


// Primary server for example.com
zone "example.com" {
     type primary;
     file "example.com.db";
     // IP addresses of secondary servers allowed to
     // transfer example.com
     allow-transfer {
        192.168.4.14;
        192.168.5.53;
     };
};


// Secondary server for eng.example.com
zone "eng.example.com" {
     type secondary;
     file "eng.example.com.bk";
     // IP address of eng.example.com primary server
     primaries { 192.168.4.12; };
};
```
* type : 해당 서버의 타입 설정 (이중화 관련)
* file : zone 파일 위치
* notify : (Priamry + Secondary 구성시, Primary option) Primary의 Zone 데이터베이스가 수정되었음을 Secondary 서버에게 알려줌으로써, 동적 동기화를 사용할 수 있다
* allow-transfer : (Primary option) 요청시 Zone 파일을 전달할 수 있는 서버 리스트
* priamries : (Secondary option) Zone 파일 정보를 요청할 서버

## Zone File 작성
```
$ORIGIN example.com.
$TTL 2d

; Start of Authority RR defining the key characteristics of the zone (domain)
@	    IN	SOA	ns1.test.com.	hostmaster.example.com. (
		    	2001062501 ; serial                     
		    	21600      ; refresh after 6 hours
		    	3600       ; retry after 1 hour
		    	1209600     ; expire after 2 week
		    	86400 )    ; minimum TTL of 1 day
        IN  NS  ns1.test.com.        ; Primary name server
        IN  NS  ns2.test.com.        ; Secondary name server
        IN  NS  ns3.test.net.        ; When external to this zone
        IN  MX  10  mail.example.com.   ; Mail server
        IN  MX  20  mail.example.net.   ; 
        IN  MX  30  mail2.example.com.  ; 
#ns1     IN  A   192.168.254.2
#ns3     IN  A   192.168.254.3
mail    IN  A   192.168.254.7
mail2   IN  A   192.168.254.8
www     IN  A   192.168.254.7
ftp     IN  CNAME   ftp.example.net.


```
* $TTL <time> : 해당 Zone 파일의 모든 RR에 대한 기본 TTL
* $ORIGIN <zone_name>. : 해당 Zone 파일의 레코드에 추가되는 도메인 이름 (ex> www IN 10.0.0.5 => www.example.com IN 10.0.0.5로 인식된다)
* @ : 선언한 $ORIGIN을 의미 (example.com.)
</br>
</br>


### Load Balancing
SRV아 함께 사용될 수 있을거 같다(확인 필요)
</br>