## SSH 접속 방법
* SSH : Secure Shell. 보안 채널을 형성하여, 안전한 데이터 통신을 하게 해주는 프로그램 또는 프로토콜
* SSH 접속을 하기 위해서는 아래와 같은 조건을 만족해야 한다.
    1. 22번 TCP 포트가 Open되어 있어야 한다.
    2. 접속할 Server에 **SSH Server** 프로그램이 설치 및 실행되고 있어야 한다.
    3. SSH 프로토콜로 접속하기 위해서는 **SSH Client**가 있어야 한다.
* 명령어
    ```
    $ssh -i rsa_key -p port user@domain
    
    # 기본 포트는 20번이다
    $ssh -i ex.pem -p 5000 ubuntu@111.222.333.444
    ```

### Install SSH Server
1. Ubuntu
    * ```apt install openssh-server```
2. CentOS
    * ```yum install -y openssh-server```

#### Reference
* https://jootc.com/p/201808031462