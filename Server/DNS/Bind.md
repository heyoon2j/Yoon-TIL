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
* Template
    ```
    acl "trusted" { 172.16.17.0/24, 172.16.18.0/24 };
    acl "untrusted" { 192.168.10.0/23 };

    logging {

    }



    options {
        listen-on port 53 { any; };
        listen-on-v6 port 53 { any; };

        allow-query { trusted };

        recursion no; 
        allow-recursion {  };
    }


    include "/etc/named.rfc1912.zones"

    ```
</br>

### ACL 선언
```
# Create List
# acl <name> {acl_list, ...}
acl trusted { 172.16.30.0/24, 172.16.18.0/24 }

allow-query { trusted };
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

}
```
</br>
</br>


### Options 설정
```
options {
    listen-on port 53 { any; };         // IPv4 Listener
    listen-on-v6 port 53 { any; };      // IPv6 Listener

    allow-query { 172.16.30.0/24 };     // Query를 허용할 Target 설정

    recursion no;                       // Caching NS : yes, Authoritative NS : no (일반적으로 무조건 NO)
    allow-recursion { 172.16.30.0/24 };                 // Recursion을 허용할 Target 설정 (Caching 서버로 사용할 경우에 사용)



}
```


</br>
</br>




## Zone 설정
```

```
* 



https://dev.dwer.kr/2020/04/bind-9.html
https://bind9.readthedocs.io/en/v9_18_3/reference.html
https://froghome.tistory.com/37