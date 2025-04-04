


* Hostname 수정
```
# 1. /etc/hostname 파일 수정 (재부팅 필요)
## ex> cat "abc.test.net" > /etc/hostname

$ cat "<host_name>" > /etc/hostname


# 2. hostnamectl 명령어 사용 (즉시 적용)
## ex> hostnamectl set-hostname abc.test.net

$ hostnamectl set-hostname <host_name>
```
