# Ansible Module
* 공식 문서: https://docs.ansible.com/ansible/2.9/modules/list_of_all_modules.html


## Module 정리
## yum
* Manages packages with the yum package manager
* Parameters
    * name: 패키지 이름 (버전 포함 가능, name-1.0)
    * state: 설치, 삭제 여부
        * ```present```, ```installed```: 패키지 설치
        * ```latest```: 패키지를 가장 최신으로 업데이트
        * ```absent```, ```removed```: 패키지 삭제
    * enablerepo: 설치 및 업데이트시, 사용할 repository 지정
    * disablerepo: 설치 및 업데이트시, 사용하지 않을 repository 지정
    * exclude: Package name에 트정 내용이 포함되지 않게 할 때 사용(state=present or latest일 때)
* Example
    ```yaml
    - name: Installing node and npm
      yum:
        name: "{{ packages }}"
        enablerepo: epel
        state: installed
      vars:
        packages:
          - nodejs
          - npm

    - name: Installing node and npm
      yum:
        name:
          - nodejs
          - npm
        enablerepo: epel
        state: installed


    - name: remove the Apache package
      yum:
        name: httpd
        state: absent

    - name: upgrade all packages
      yum:
        name: '*'
        state: latest

    - name: upgrade all packages, excluding kernel & foo related packages
      yum:
        name: '*'
        state: latest
        exclude: kernel*,foo*

    - name: install the nginx rpm from a remote repo
      yum:
        name: http://nginx.org/packages/centos/6/noarch/RPMS/nginx-release-centos-6-0.el6.ngx.noarch.rpm
        state: present

    - name: install nginx rpm from a local file
      yum:
        name: /usr/local/src/nginx-release-centos-6-0.el6.ngx.noarch.rpm
        state: present

    - name: install the 'Development tools' package group
      yum:
        name: "@Development tools"
        state: present  
    ```

## copy
* Copy files to remote locations
* Parameters
    * src: 복사할 파일의 Local Path. Path가 Directory인 경우("/"로 끝나는 경우), 내부 파일 모두를 복사한다.
    * dest: Remote absolute path. ```src```가 Directory인 경우, path는 마찬가지로 Directory가 되야 한다.
    * owner: 소유자 이름. chown으로 설정
    * group: 그룹 이름. chown으로 설정
    * mode: 허용 권한 설정. /usr/bin/chmode를 사용. 
    * validate: 
    * content: ```src``` 대신 사용되며 ```dest```가 File일 때 사용 가능하다. 
    * follow: 
* Example
    ```
    - name: Copying the application file
      copy:
        src: helloworld.js
        dest: /home/ec2-user/
        owner: ec2-user
        group: ec2-user
        mode: 0644
    
    - name: Another symbolic mode example, adding some permissions and removing others
      copy:
        src: /srv/myfiles/foo.conf
        dest: /etc/foo.conf
        owner: foo
        group: foo
        mode: u+rw,g-wx,o-rwx

    - name: Copy file with owner and permission, using symbolic representation
      copy:
        src: /srv/myfiles/foo.conf
        dest: /etc/foo.conf
        owner: foo
        group: foo
        mode: u=rw,g=r,o=r

    - name: Copy a "sudoers" file on the remote machine for editing
      copy:
        src: /etc/sudoers
        dest: /etc/sudoers.edit
        remote_src: yes
        validate: /usr/sbin/visudo -csf %s


    - name: If follow=yes, /path/to/file will be overwritten by contents of foo.conf
      copy:
        src: /etc/foo.conf
        dest: /path/to/link  # link to /path/to/file
        follow: yes
    ```



## service
* Manage services
* Parameters
    * name: Service 이름
    * state: Service 상태 설정
        * ```started```/```stopped```
        * ```reloaded```
        * ```restarted```
    * enabled: Boot 시, 해당 서비스를 시작시킬지 여부. ```no``` or ```yes```
    * arguments: Command line에 제공하는 추가 인수. 별칭 ```args```로도 사용할 수 있다.
* Example
    ```
    - name: Starting the HelloWolrd node service
      service:
        name: helloworld
        state: started

    - name: Enable service httpd, and not touch the state
      service:
        name: httpd
        enabled: yes

    - name: Restart network service for interface eth0
      service:
        name: network
        state: restarted
        args: eth0
    ```


## 
* 
* Parameters
    * 
* Example
    ```
    ```


## 
* 
* Parameters
    * 
* Example
    ```
    ```



