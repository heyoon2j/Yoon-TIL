# Bind 설정

* MX, TXT는 CNAME과 같이 쓰일 수 없다!
* 



## DNS Server 설정
* ```/etc/name.conf```에서 설정
```
acl "trusted" { 172.16.17.0/24, 172.16.18.0/24 };
acl "untrusted" { 192.168.10.0/23 };

options {
    listen-on port 53 { any; };
    listen-on-v6 

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