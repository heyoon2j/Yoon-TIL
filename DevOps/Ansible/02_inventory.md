# Inventory
Ansible은 인벤토리를 사용하여 노드(관리 대상)들에 대한 호스트 정보를 관리한다.
- 
</br>
</br>


---
## Directory Structure
### Basic
```
# Inventory Structure (inventory/...)

inventories/
├── production/
│   ├── hosts.yaml          # inventory file for production servers
│   ├── group_vars/
│   │   ├── webserver.yml       # here we assign variables to particular groups
│   │   └── batchserver.yml
│   └── host_vars/
│       ├── websrv1.test.com.yml    # here we assign variables to particular systems
│       ├── websrv2.test.com.yml
│       ├── batchsrv1.test.com.yml    # here we assign variables to particular systems
│       └── batchsrv2.test.com.yml
│
└── staging/
    ├── hosts.yaml          # inventory file for staging enviroment
    ├── group_vars/
    │   ├── group1.yml       # here we assign variables to particular groups
    │   └── group2.yml
    └── host_vars/
        ├── hostname1.yml    # here we assign variables to particular systems
        └── hostname2.yml

```
- hosts.yaml : 모든 호스트와 그룹을 정의하는 YAML 파일
- host_vars/ : 각 호스트별 특정 변수를 정의하는 YAML 파일
- group_vars/ : 그룹별로 변수를 정의하는 YAML 파일
</br>


### Load Order
```
# Inventory Structure (inventory/...)
.
├── 01-openstack.yml          # configure inventory plugin to get hosts from OpenStack cloud
├── 02-dynamic-inventory.py   # add additional hosts with dynamic inventory script
├── 03-on-prem                # add static hosts and groups
├── 04-groups-of-groups       # add parent groups
├── group_vars
│   └── all
├── hosts
└── host_vars    
```
- Ansible은 파일 이름에 따라 ASCII 순서로 병합한다. 파일에 접두사를 추가하여 로드 순서를 제어할 수 있다.
</br>
</br>


--
## Config
* 기본적으로 ```/etc/ansible/hosts``` 파일에 정의한다.
* https://docs.ansible.com/ansible/latest/user_guide/intro_inventory.html


---
### File
* Hosts 파일
    ```yaml
    all:
      vars:
      ansible_user: your_user
      ansible_ssh_private_key_file: /path/to/private/key
      ansible_become: true
      ansible_become_method: sudo
      ansible_become_user: root

    children:
      webservers:
        hosts:
          webserver1.test.com:
            ansible_host: 192.168.1.1
            ansible_port: 40022
          webserver2.example.com:
            ansible_host: 192.168.1.2
            ansible_port: 40022

      batservers:
        hosts:
          batchsrv[1:5].test.com:
            ansible_host: "192.168.1.[1:5]"
            ansible_port: 2222
            ansible_user: batch_user
            ansible_ssh_private_key_file: /path/to/dbserver/key
            ansible_connection: ssh
            ansible_ssh_common_args: '-o ProxyCommand="ssh -W %h:%p jump_host"'       
        vars:
            haproxy_backend_port: 80
            haproxy_max_connections: 3000
          batservers:
      
      loadbalancers:  
        hosts:
          lb[1:3].test.com:
            ansible_host: lb[1:3].test.com 
        vars:
            haproxy_backend_port: 80
            haproxy_max_connections: 3000
    ```
    * 기본 옵션
        - ansible_host: 실제 연결할 호스트의 IP 주소 또는 호스트 이름.
        - ansible_port: SSH 연결에 사용할 포트 (기본값은 22).
        - ansible_user: SSH 연결에 사용할 사용자 이름.
        - ansible_password: SSH 연결에 사용할 비밀번호 (가능하면 비밀번호 대신 키 기반 인증을 사용하는 것이 권장됨).
        - ansible_ssh_private_key_file: SSH 연결에 사용할 프라이빗 키 파일의 경로.
        - ansible_connection: 호스트 연결 방식 (local, ssh, docker 등).
        - ansible_ssh_common_args: SSH 연결에 사용할 추가적인 SSH 인자.
        - ansible_become: true로 설정하면 become (sudo) 명령을 사용하여 루트 또는 다른 사용자로 작업 수행.
        - ansible_become_method: sudo, su, pbrun, pfexec, doas 등. 기본값은 sudo.
        - ansible_become_user: become을 사용할 때 사용할 사용자 이름.
        - ansible_become_password: become을 사용할 때 사용할 사용자 비밀번호션
    * "all:"은 모든 Hosts를 의미한다.
    * "children:"은 부모/자식 그룹을 만들 수 있다.
    * 하위 그룹의 구성원인 모든 호스트는 자동으로 상위 그룹의 구성원이 됩니다.
    * 그룹은 여러 부모와 자식을 가질 수 있다.
    * 호스트는 여러 그룹에 있을 수도 있지만 런타임에는 호스트 인스턴스가 하나만 있게 된다(Ansible은 여러 그룹의 데이터를 병합하기 때문에)
    * 중복된 Host name을 가질 수 없다.
    > 하위 그룹의 변수는 상위 그룹의 변수보다 높은 우선 순위(재정의)를 갖는다.

* Variable 파일
    ```yaml
    # host_vars/websrv1.test.com.yaml
    http_port: 80
    max_clients: 200

    # host_vars/batch1.test.com.yaml
    batch_user: batchadmin
    ```

    ```yaml
    # group_vars/all.yaml
    ansible_user: your_user
    ansible_ssh_private_key_file: /path/to/private/key
    ansible_become: true
    ansible_become_method: sudo
    ansible_become_user: root    
    ``

    ```yaml
    # group_vars/webservers.yaml
    http_port: 80
    ``


---
## Hosting
Ansible에서는 크게 두가지의 호스팅 방법이 있다.
1) 정적 호스트 설정
2) 동적 호스트 설정

### 정적 호스팅 (Static Inventory)
위쪽 예제와 같이 IP, FQDN 등 작성하는 방법
</br>
</br>


### 동적 호스팅 (Dynamic Inventory)
외부 데이터 소스에서 호스트 정보를 실시간으로 가져오는 방법. 클라우드 같은 경우 동적으로 변경되기 때문에 이러한 환경일때 유용
* Plugin Example
    ```YAML
    plugin: aws_ec2
    regions:
      - us-east-1
      - us-west-2
    filters:
      instance-state-name: running
    keyed_groups:
      - key: tags.Name
        prefix: tag
    hostnames:
      - tag:Name
    compose:
      ansible_host: public_ip_address
      private_ip: private_ip_address
      instance_id: instance_id

    ```
* Python
    ```py
    #!/usr/bin/env python
    import json

    def main():
        inventory = {
            "webservers": {
                "hosts": ["webserver1.example.com", "webserver2.example.com"]
            },
            "dbservers": {
                "hosts": ["dbserver1.example.com", "dbserver2.example.com"]
            },
            "_meta": {
                "hostvars": {
                    "webserver1.example.com": {
                        "ansible_host": "192.168.1.1"
                    },
                    "webserver2.example.com": {
                        "ansible_host": "192.168.1.2"
                    },
                    "dbserver1.example.com": {
                        "ansible_host": "192.168.1.3"
                    },
                    "dbserver2.example.com": {
                        "ansible_host": "192.168.1.4"
                    }
                }
            }
        }
        print(json.dumps(inventory))

    if __name__ == "__main__":
        main()
    ```
    ```bash
    chmod +x dynamic_inventory.py
    ./dynamic_inventory.py --list
    ```


















