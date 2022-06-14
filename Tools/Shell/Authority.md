# Linux 권한

# User 권한
## User 관련된 Command
* ```$ su```: switch user, 현재 계정을 로그아웃하지 않고 다른 계정으로 전환
* ```$ sudo```: superuser do, 현재 계정에서 root 권한을 이용하여 명령어 실행
* ```$ whoami```: 현재 로그인한 사용자 출력
* ```$ id```: 현재 로그인한 사용자의 정보 출력
* ```$ useradd```: 사용자 계정 생성
    * -c : Comment, 설명
    * -u : UID 설정, 설정하지 않을 시 자동 증가 값으로 설정
    * -g : GID 설정
    * -U : 같은 user 이름으로 group 생성
    * -s : login shell 설정
    * -p : Password 설정
    * -e : 계정 만료 기간
    * -d : home directory 지정
    * example
        ```
        $ useradd -c TestUser -e 2021-12-31 -u 1234 -U -s /bin/bash -p 1234567890! user1
        ```
* ```$ passwd <user_name>```: 사용자의 패스워드 설정
* ```$ usermod [option] <user_name>```: 사용자 정보 변경. 옵션은 useradd와 거의 동일하다 생각하면 된다.
    * -g : 기본 Group 변경
    * -G : Group 추가 (1개만 추가 가능하므로, 2개 이상부터는 /etc/group 파일을 수정해야 한다)
* ```$ userdel [option] <user_name>```: 사용자 삭제. 꼭 -r 옵션을 추가해줘야 Home Dir가 같이 삭제된다. 그렇지 않으면 직접 Dir를 삭제해야 한다(이후에 있을 에러를 방지하기 위해)
* ```$ groupadd <user_name>```: 그룹 생성
* ```$ gpasswd [option] <group_name>]```: 그룹 맴버 관리
* ```$ groupdel <group_name>```: 그룹 삭제



## User 관련된 파일
* /etc/passwd : 
* /etc/s :
* /etc/group : 그룹 정보 파일




## sudo 권한 부여
* sudo를 실행할 수 있는 권한은 sudo 설정파일 ```/etc/sudoers```에서 지정할 수 있다.
* 형식: ```user  MACHINE=(EXE_USER) [NOPASSWD:] COMMNADS```
    * user 계정은 해당 MACHINE에서 접근이 가능하며, MACHINE에서 COMMANDS 명령을 사용할 수 있다라는 의미이다.
    * ALL=(ALL): 모든 컴퓨터에서 접근 가능하며, 모든 컴퓨터에서 모든 명령을 할 수 있다.
* MACHINE
    * sudo 이용을 허용할 컴퓨터 이름을 명시해준다.
    * 옵션: ALL, localhost, <computer_name>
    * 컴퓨터 이름은 ```hostname``` 명령어 이용 or ```/etc/hostname``` 파일 내용을 변경한다.
* COMMANDS
    * sudo 이용을 허용할 명령어를 절대경로로 명시해 준다.
    * 옵션: ALL, <command>
* EXE_USER
    * 명령어를 실행할 때 어떤 계정의 권한을 갖는지 설정(생략 시 root로 실행)
* NOPASSWD:
    * 설정할 경우 명령어를 실행할 때, 계정 암호를 묻지 않는다.
* Example
    ```
    $ vi /etc/sudoers

    # user1에게 sudo 권한 부여
    # <user_name>    ALL=(ALL)   ALL
    user1   ALL=(ALL)   ALL

    # user1 Group에 sudo 권한 부여
    # %<group_name> ALL=(ALL)   ALL
    %user1  ALL=(ALL)   ALL

    # user1에게 password 확인 없이, sudo 권한 부여
    # NOPASSWD: 를 붙인다.
    user1   ALL=(ALL)   NOPASSWD: ALL

    %users  ALL=/sbin/mount /mnt/cdrom, /sbin/umount /mnt/cdrom

    %users  localhost=/sbin/shutdown -h now
    ```



* Reference
    * https://www.lesstif.com/ws/sudo-46366762.html
    * https://whitewing4139.tistory.com/74



# File 권한