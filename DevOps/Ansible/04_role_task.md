# Role 
Ansible 구성 요소를 모듈화하고 재사용성을 높이기 위해 사용된다.
* 역할 스크립트는 https://galaxy.ansible.com/과 같은 커뮤티니 사이트에서 지원을 받을 수 있다.
* ```ansible-galaxy``` 명령어를 사용해 커뮤니티에서 지원하는 역할을 임포트할 수 있다.
> task는 각 프로세스가 생성되어 실행된다. 그렇기 때문에 ```source``` 명령어가 필요한 경우, 하나의 프로세스에서 실행시켜야 한다!!!!!!

</br>
</br>


---
## Directory structure 
```
# Role Initialization
# ansible-galaxy init [option] <role_name>
$ ansible-galaxy init nodejs

# Role Structure
role_name/
.
├── defaults
│   └── main.yaml
├── files
│   └── test.sh
│   └── y2.txt
├── handlers
│   └── main.yaml
├── meta
│   └── main.yaml
├── README.md
├── tasks
│   └── main.yaml
├── templates
├── tests
│   ├── inventory
│   └── test.yaml
└── vars
    └── main.yaml
```
* meta: 저자, 지원 플랫폼, 종속성과 같은 Role의 메타 데이터를 포함
    - meta/main.yml은 두 가지 영역이 있다.
    1) galaxy_info: 빌드할 역할에 대한 정보
    2) dependencies: 종속정 정보 작성
* defaults: Role의 기본 변수를 포함. 우선순위가 가장 낮다.
* vars: 해당 Role의 전역 변수 정의. defaults 디렉토리의 변수보다 우선순위가 높다.
* template: 해당 Role의 수정을 지원하는 file template을 포함한다.
* files: 원격 호스트에 복사할 파일들이 저장되어 있다.
* task: 해당 Role의 역할 정의. Ansible이 Playbook을 실행할 때 tasks/main.yml에 있는 코드를 실행한다.
* handlers: "notify" 지시문에 의해 호출될 수 있고, 서비스와 연관된 Handler를 포함

</br>
</br>



---
## 작성 방법
### tasks/main.yaml
```yaml
- name: Apache 설치
  apt:
    name: apache2
    state: present
    update_cache: yes

- name: Apache 시작 및 활성화
  systemd:
    name: apache2
    state: started
    enabled: yes

- name: index.html 파일 배포
  copy:
    src: index.html
    dest: "{{ apache_document_root }}/index.html"
    owner: www-data
    group: www-data
    mode: '0644'
```
</br>

### handlers/main.yaml
```yaml
- name: Restart Apache
  systemd:
    name: apache2
    state: restarted
    enabled: yes
```
</br>

### files/index.html
```yaml
<!DOCTYPE html>
<html>
<head>
    <title>Welcome to Apache!</title>
</head>
<body>
    <h1>It works!</h1>
</body>
</html>

```
</br>

### vars/main.yaml
```yaml
# Role에 필요한 변수들
apache_port: 80

```
</br>

### defaults/main.yaml
```yaml
# 기본 변수들 (다른 변수보다 낮은 우선순위)
apache_user: www-data
apache_group: www-data
```
</br>

> {{ 변수명 }} 형태로 사용한다. ex> {{ apache_port }}


### meta/main.yaml
```yaml
---
# dependencies: []
dependencies:
  - role: common
  - role: security
    vars:
      firewall_enabled: true

galaxy_info:
  author: your_name
  description: This role installs and configures Apache web server.
  company: your_company
  license: MIT
  min_ansible_version: 2.9

  platforms:
    - name: Amazon2023
      versions:
        - bionic
        - focal

    - name: CentOS
      versions:
        - 7
        - 8

  galaxy_tags:
    - web
    - apache
    - httpd
    - server

  dependencies: []
```
- dependencies : 해당 Role이 common과 security에 의존성을 가진다. 그렇기 때문에 해당 Role을 사용하게되면 common과 security가 자동으로 실행된다.
> 종속성 중복을 막기 위해 


