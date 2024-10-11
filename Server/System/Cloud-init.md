# Cloud Init


## Boot Stage
* 서버 부팅 시 동작 과정은 다음과 같다.
1. Generator
2. Local
3. Network
4. Config
5. Final
</br>

### Generator
* Booting 시에 Systemd에서 cloud-init.target을 부팅 목표에 포함되어야 하는지 여부를 결정한다.
* 기본적으로 cloud-init을 사용하도록 설정하며, 다음의 경우에 cloud-init을 사용하지 않도록 설정된다.
    1) ```/etc/cloud/cloud-init.disabled``` exists
    2) ```/proc/cmdline``` contains ```cloud-init=disabled```
</br>

### Local
* 

### Network

### Config

### Final
* 모든 시스템이 실행 된 후, 마지막 필요한 스크립트들을 실행하는 모듈
* Modeule : ```cloud_final_modules``` in ```/etc/cloud/cloud.cfg```


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


| 디렉터리 및 파일 | 설 명 |
|-----|-----|
| /etc/cloud/cloud.cfg | cloud-init 기본 구성, 각 모듈이 실행되는 단계 설정 | 
| /etc/cloud/cloud.cfg.d | cloud.cfg.d 디렉토리는 cloud-init 에 대한 지시문을 추가할 수 있는 위치 |
| /var/lib/cloud | cloud-init 가 실행되면 /var/lib/cloud 에 디렉터리 레이아웃이 생성된다. 레이아웃에는 인스턴스 구성에 대한 세부 정보를 제공하는 디렉터리 및 파일이 포함 | 
| /usr/share/doc/cloud-init/examples | 예제 디렉터리에는 여러 예제가 포함되어 있다. 이를 사용하여 자체 지시문을 모델링할 수 있음 |
| /etc/cloud/templates | 해당 디렉터리에는 특정 시나리오에 대해 cloud-init 에서 활성화할 수 있는 템플릿이 포함 (템플릿은 활성화의 방향을 제공) | 
| /var/log/cloud-init.log | cloud-init.log 파일은 디버깅에 유용한 로그 정보를 제공 |
| /run/cloud-init | 데이터 소스 및 ds-identify 스크립트에 대한 기록된 정보가 포함 | 

```
/var/lib/cloud/
    - data/
       - instance-id
       - previous-instance-id
       - previous-datasource
       - previous-hostname
       - result.json
       - set-hostname
       - status.json
    - handlers/
    - instance
       - boot-finished
       - cloud-config.txt
       - datasource
       - handlers/
       - obj.pkl
       - scripts/
       - sem/
       - user-data.txt
       - user-data.txt.i
       - vendor-data.txt
       - vendor-data.txt.i
    - instances/
        f111ee00-0a4a-4eea-9c17-3fa164739c55/
          - boot-finished
          - cloud-config.txt
          - datasource
          - handlers/
          - obj.pkl
          - scripts/
          - sem/
          - user-data.txt
          - user-data.txt.i
          - vendor-data.txt
          - vendor-data.txt.i
    - scripts/
       - per-boot/
       - per-instance/
       - per-once/
       - vendor/
    - seed/
    - sem/
       - config_scripts_per_once.once   
```