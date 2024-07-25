# Playbook
실행할 명령 등이 포함된 Role과 Host 정보가 들어있는 Inventory를 이용하여 Playbook을 작성한다.
</br>

---
## 작성 방법
```
- name: 전체 서버 설정
    hosts: all
    become: true
    roles:
    - common

- name: 웹 서버 설정
    hosts: webservers
    become: true
    roles:
    - role: webserver
      tags: webserver
    - role: security
      tags: security

- name: 데이터베이스 서버 설정
    hosts: dbservers
    become: true
    roles:
    - dbserver
    - role: backup
      tags: backup
```
- hosts: Target hosts List
- become: (sudo와 같이)상승된 권한 사용 여부. 값은 ```yes``` or ```no```
- roles: 실행된 Role List
- port: 연결에 사용될 기본 port
- timeout: task가 실해될 제한 시간
- vars: Dictionary/Map variables
</br>
</br>


---
## 실행 방법
* 기뵨 명령어: 
```bash
$ ansible-playbook <playbook.yml> -i <inventory_path> --private-key <key>

$ ansible-playbook site.yaml --limit webservers --tags webserver        # Hosts: webservers로 설정되어 있고 Role의 Tag: webserver 실행
$ ansible-playbook site.yaml --limit webservers --skip-tags webserver        # Hosts: webservers로 설정되어 있고 Role의 Tag: sercurity 실행

```
- -l, --limit : 특정 Host로 제한
- --tags : 특정 태그로 제한
- --skip-tags : 특정 태그 제외
- --check: 실행 시 어떤 것이 변경되는지 확인할 수 있다. 처음에는 해당 옵션을 통해 확인 후 적용하는 것이 좋다(dry-run mode라고 한다). 실제 적용되지 않는다!!!
- -v ~ -vvvv : 디버깅 정보 출력
- --private-key : 특정 키 사용
- --stats : Task의 특정 통계 정보보기
- -i : Inventory 파일 or 폴더 지정
