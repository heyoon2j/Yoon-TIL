# Bind 설정
* MX, TXT는 CNAME과 같이 쓰일 수 없다!
 


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
```
acl "trusted" { 172.16.17.0/24, 172.16.18.0/24 };
acl "untrusted" { 192.168.10.0/23 };

options {
    listen-on port 53 { any; };
    listen-on-v6 port 53 { any; };

    allow-query { trusted };
    allow-recursion {  };
}
```

* listen-on port 53 { any; };


* acl 설정
    ```
    # Create List
    # acl <name> {acl_list, ...}
 


    allow-query { trusted };
    ```

## Zone 설정