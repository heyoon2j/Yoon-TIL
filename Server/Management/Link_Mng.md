# Link Management
* Update 등에 따라 Package의 여러 버전이 설치되는 경우가 있다. 이를 위해 보통 Symbolic Link를 통해 관리


## Alternatives
* ```alternatives``` 명령어 사용

### 작업 순서
1. Download & Install JDK
   * 원하는 버전을 다운받은 후, 특정 위치에 설치한다.
2. Add Java Version in Alternatives
   ```
   $ alternatives --install <link> <name> <package_path> <priority>

   ex> alternatives --install /usr/bin/java java /usr/java/jdk.1.8.0_22/bin/java 100
   ```
   * install을 하게 되면 ```/etc/alternative/<name>``` 심볼릭 링크를 생성한다.
   * ```<link>``` ---> ```/etc/alternative/<name>```를 가리킨다.
   * 해당 정보는 ```/var/lib/alternatives/<name>```에 저장된다.
3. Set Version
    ```
    $ alternatives --config java
    ```
    * 원하는 버전 숫자 키 입력 + Enter

