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


## get_url
* Downloads files from HTTP, HTTPS or FTP to the remote server.
* Parameters
    * url: 요청할 URL
    * dest: 파일을 저장할 절대 경로.
    * mode: 허용 권한 설정. /usr/bin/chmode를 사용.
    * headers: hash/dict 형식으로 요청에 사용자 지정 HTTP 헤더를 추가합니다.
    * checksum: checksum이 매개 변수에 전달되면, 대상 파일의 digest가 다운로드된 후 계산되어 무결성을 확인하고 전송이 성공적으로 완료되었는지 확인한다.
    * username: HTTP 인증에 사용되는 username
    * password: HTTP 인증에 사용되는 password
* Example
    ```
    - name: Download foo.conf
      get_url:
        url: http://example.com/path/file.conf
        dest: /etc/foo.conf
        mode: '0440'

    - name: Download file with custom HTTP headers
      get_url:
        url: http://example.com/path/file.conf
        dest: /etc/foo.conf
        headers:
          key1: one
          key2: two    


    - name: Download file with check (md5)
      get_url:
        url: http://example.com/path/file.conf
        dest: /etc/foo.conf
        checksum: md5:66dffb5228a211e61d6d7ef4a86f5758


    - name: < Fetch file that requires authentication.
        username/password only available since 2.8, in older versions you need to use url_username/url_password
      get_url:
        url: http://example.com/path/file.conf
        dest: /etc/foo.conf
        username: bar
        password: '{{ mysecret }}'
    ```


## shell
* Execute shell commands on targets
* Parameters
    * chdir: command 실행 전에 Directory 변경
    * cmd: command
    * creates: 파일이름이 존재하면 실행되지 않는다.
    * executable: shell을 변경한다. 절대 경로로 입력해야 한다.
    * removes: 파일이름이 존재하지 않으면 실행되지 않는다.
* Example
    ```
    - name: Execute the command in remote shell; stdout goes to the specified file on the remote.
      shell: somescript.sh >> somelog.txt

    # You can also use the 'cmd' parameter instead of free form format.
    - name: This command will change the working directory to somedir/.
      shell:
        cmd: ls -l | grep log
        chdir: somedir/

    - name: Run a command that uses non-posix shell-isms (in this example /bin/sh doesn't handle redirection and wildcards together but bash does)
      shell: cat < /tmp/*txt
      args:
        executable: /bin/bash


    # You can use shell to run other executables to perform actions inline
    - name: Run expect to wait for a successful PXE boot via out-of-band CIMC
      shell: |
        set timeout 300
        spawn ssh admin@{{ cimc_host }}

        expect "password:"
        send "{{ cimc_password }}\n"

        expect "\n{{ cimc_name }}"
        send "connect host\n"

        expect "pxeboot.n12"
        send "\n"

        exit 0
      args:
        executable: /usr/bin/expect
      delegate_to: localhost
    ```


## file 
* Manage files and file properties
* Parameters
    * path: 절대 경로
    * owner: 소유자 이름
    * group: 그룹 이름
    * mode: 권한 설정. file/direcotyr인 경우 설정하는 것이 좋다.
    * state: 파일 상태
        * file: 기본 옵션. file이 존재하는 경우 mode가 변경될 수 있다.
        * directory: 존재하지 않는 경우 recursively하게 directory 생성.
        * absent: 삭제
        * hard/soft: hard/soft link 파일 생성 or 변경
        * touch: 파일이 존재하지 않으면, 빈 파일 생성.
    * recurse: 
    * access_time: 파일의 access_time을 설정할 수 있다.
* Example
    ```
    - name: Create a symbolic link
      file:
        src: /file/to/link/to
        dest: /path/to/symlink
        owner: foo
        group: foo
        state: link

    - name: Update modification and access time of given file
  file:
      path: /etc/some_file
        state: file
        modification_time: now
        access_time: now

    - name: Recursively change ownership of a directory
      file:
        path: /etc/foo
        state: directory
        recurse: yes
        owner: foo
        group: foo
    
    - name: Remove file (delete file)
      file:
        path: /etc/foo.txt
        state: absent

    - name: Recursively remove directory
      file:
        path: /etc/foo
        state: absent    
    ```



## script
* Runs a local script on a remote node after tranferring it
* Parameters
    * 
* Example
    ```


    # 'argv' is a parameter, indented one level from the module
    - name: Use 'argv' to send a command as a list - leave 'command' empty
      command:
        argv:
          - /usr/bin/make_database.sh
          - Username with whitespace
          - dbname with whitespace
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
