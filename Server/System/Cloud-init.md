# Cloud Init


## Boot Stage





sudo vi /etc/cloud/cloud.cfg
sudo vi /var/lib/cloud/instance/scripts
sudo vi /var/lib/cloud/scripts/



## Cloud Init CLI Interface
### status
```
$ cloud-init clean

$ 

```


### status
* https://carlo.cloud/linuxs-cloud-init-benefits-quirks-and-drawbacks-709e19a78951
```
$ cloud-init status



$ cat /var/lib/cloud/data/status.json

```

### MIME
```
Content-Type: multipart/mixed; boundary="//"
MIME-Version: 1.0

--//
Content-Type: text/cloud-config; charset="us-ascii"
MIME-Version: 1.0
Content-Transfer-Encoding: 7bit
Content-Disposition: attachment; filename="cloud-config.txt"

#cloud-config
cloud_final_modules:
- [scripts-user, always]

--//
Content-Type: text/x-shellscript; charset="us-ascii"
MIME-Version: 1.0
Content-Transfer-Encoding: 7bit
Content-Disposition: attachment; filename="userdata.txt"

#!/bin/bash
/bin/echo "Hello World" >> /tmp/testfile.txt
--//--
=========================================================
```
* Userdata에 넣어두면 재부팅 마다 계속 실행됨
* 기존 User Data도 실행된다(이유는 기본적으로 instance/script/에 복사하여 사용하기 때문에)

```
Content-Type: multipart/mixed; boundary="//"
MIME-Version: 1.0

--//
Content-Type: text/cloud-config; charset="us-ascii"
MIME-Version: 1.0
Content-Transfer-Encoding: 7bit
Content-Disposition: attachment; filename="cloud-config.txt"

#cloud-config
cloud_final_modules:
- [scripts-user, once]

--//
Content-Type: text/x-shellscript; charset="us-ascii"
MIME-Version: 1.0
Content-Transfer-Encoding: 7bit
Content-Disposition: attachment; filename="userdata2.txt"

#!/bin/bash
/bin/echo "Hello World" >> /home/sysadmin/testfile.txt
--//--

======================================================
```
* Userdata에 넣어둬도 재부팅마다 안돌아간다
* 이유는 파일을 instance/script/로 복사하기 전에 파일이 존재하는 지 확인하고 있으면 Pass하는거 같다.
* 또는 캐싱 저장 때문으로 현재는 추측

```


Content-Type: multipart/mixed; boundary="//"
MIME-Version: 1.0

--//
Content-Type: text/cloud-config; charset="us-ascii"
MIME-Version: 1.0
Content-Transfer-Encoding: 7bit
Content-Disposition: attachment; filename="cloud-config.txt"

#cloud-config
cloud_final_modules:
- [scripts-per-once, always]

--//
Content-Type: text/x-shellscript-per-once; charset="us-ascii"
MIME-Version: 1.0
Content-Transfer-Encoding: 7bit
Content-Disposition: attachment; filename="userdata3.txt"

#!/bin/bash
/bin/echo "Hello World" >> /home/sysadmin/testfile.txt
--//--


===================================================

Content-Type: multipart/mixed; boundary="//"
MIME-Version: 1.0

--//
Content-Type: text/cloud-config; charset="us-ascii"
MIME-Version: 1.0
Content-Transfer-Encoding: 7bit
Content-Disposition: attachment; filename="cloud-config.txt"

#cloud-config
cloud_final_modules:
- [scripts-per-once, once]

--//
Content-Type: text/x-shellscript-per-once; charset="us-ascii"
MIME-Version: 1.0
Content-Transfer-Encoding: 7bit
Content-Disposition: attachment; filename="userdata4.txt"

#!/bin/bash
/bin/echo "Hello World" >> /home/sysadmin/testfile.txt
--//--
```