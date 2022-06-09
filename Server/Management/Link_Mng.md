# Link Management
* Update 등에 따라 Package의 여러 버전이 설치되는 경우가 있다. 이를 위해 보통 Symbolic Link를 통해 관리


## Alternatives
* ```alternatives``` 명령어 사용
* Ref: https://skyoo2003.github.io/post/2017/03/17/what-is-alternatives-command/
</br>
</br>

### 작업 순서
1. Download & Install JDK
    * 원하는 버전을 다운받은 후, 특정 위치에 설치한다.
2. Display symbolic link in Alternatives
    ```
    $ alternatives --display <name>
    ```
    * 해당 ```<name>```으로 등록되어 있는 심볼릭 링크 그룹에 대한 정보를 출력한다.
    * auto/manual, master/slaver 경로, priority 정보 확인할 수 있다.
3. Add Java Version in Alternatives
    ```
    $ alternatives --install <link> <name> <package_path> <priority>

    ex> alternatives --install /usr/bin/java java /usr/java/jdk.1.8.0_22/bin/java 100
    ```
    * install을 하게 되면 ```/etc/alternative/<name>``` 심볼릭 링크를 생성한다.
    * ```<link>``` ---> ```/etc/alternative/<name>```를 가리킨다.
    * 해당 정보는 ```/var/lib/alternatives/<name>```에 저장된다.
4. Set Version
    ```
    $ alternatives --config <name>
    ex> alternatives --config java

    $ alternatives --auto <name>
    ex> alternatives --auto java
    ```
    * config : 수동 설정. 원하는 버전 숫자 키 입력 + Enter
    * auto : 자동 설정. Priority가 높은 값은 우선적으로 선택 (값이 높을 수록 우선 수위)

