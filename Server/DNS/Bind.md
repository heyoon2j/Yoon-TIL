# Bind 설정
* MX, TXT는 CNAME과 같이 쓰일 수 없다!
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

### ACL
```
acl "trusted" { 172.16.17.0/24, 172.16.18.0/24 };   # ACL List 선언
acl "untrusted" { 192.168.10.0/23 };
```
* "any", "none", "localhost" 및 "localnets"는 미리 정의되어 있다.


</br>


### Option
```
options {
    listen-on port 53 { any; };
    listen-on-v6 port 53 { any; };

    allow-query { trusted };            # Query를 허용할 ACL 설정

    recursion no                        # Yes : Caching Name Server / No : Authoritative Name Server
    allow-recursion { any; };           # Recursion을 허용할 ACL 설정

}
```



### Logging
```
```



</br>
</br>


## Zone 설정

```
// Provide a reverse mapping for the loopback
// address 127.0.0.1
zone "0.0.127.in-addr.arpa" {
     type primary;
     file "localhost.rev";
     notify no;
};
// We are the primary server for example.com
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
// We are a secondary server for eng.example.com
zone "eng.example.com" {
     type secondary;
     file "eng.example.com.bk";
     // IP address of eng.example.com primary server
     primaries { 192.168.4.12; };
};
```


## Zone File 작성



### Load Balancing

