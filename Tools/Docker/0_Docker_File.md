# Docker File
Docker Image를 생성하는 방법으로 Dockerfile을 통해 생성하는 방법이 있다.

Dockerfile을 작성하는 방법에 따라 컨테이너 생성 시, 작성 방법에 따라 성능 차이가 발생할 수 있기 때문에 Application 아키텍처에 맞게 신중하게 작성해야 한다.
> Image Layer에 의한 Size, CoW에 의한 Overhead 등

추가로 .dockerignore 파일을 작성하여, 이미지 생성 시 이미지 안에 들어가면 안되는 파일들을 지정할 수 있다.

---
## Docker File Basic
| Command | Description | Comment |
|---------|-------------|------------|
| ARG | 컨테이너 실행 시, 인자값을 받는다 | FROM 앞에 사용할 수 있는데, FROM 이후에는 사용 불가능해진다 |
| FROM | Base Image 지정 | |
| LABEL | 이미지 메타데이터 추가 | Key-Value 형태 |
| USER | 실행한 계정 지정 | Default: root 계정 |
| WORKDIR | 작업 디렉터리 변경 | |
| ENV | 환경 변수 적용 | |
| COPY | 파일을 컨테이너에 파일 시스템에 복사 | Host 내 파일 복사 / --chown 옵션으로 소유 권한 설정 가능 / ADD보다 권장됨 |
| ADD | 파일을 컨테이너에 파일 시스템에 복사 | Host 내, Remote 파일 복사 모두 가능 |
| VOLUME | 특정 호스트 디렉터리에 저장하도록 지정 | |
| EXPOSE | 오픈할 Port 지정 | |
| RUN | 명령어 실행 (시점: 이미지 빌드) | |
| ENTRYPOINT | 명령어 실행 (시점: 컨테이너 실행) / 항상 실행되어야 하는 커맨드를 지정한다. 이유는 커맨드로 실행한 프로세스가 죽으면 컨테이너도 같이 종료된다. | |
| CMD | Default 명령어 실행 (시점: 컨테이너 실행) | ENTRYPOINT와 다르게, run 시 변경이 가능 / Dockerfile 당 1번 사용 가능 |
| ONBUILD |  |  |
| STOPSIGNAL | 컨테이너 종료 시그널 |  |


</br>

### FROM
* 기본 구조
    ```dockerfile
    # Default
    # --platform : linux/amd64, linux/arm64또는 windows/amd64
    FROM [--platform=<platform>] <image> [AS <name>]
    ```
* ARG 사용하는 경우
    ```dockerfile
    ARG VERSION=latest
    FROM busybox:$VERSION
    ARG VERSION
    RUN echo $VERSION > image_version
    ```
    - FROM 전에 사용된 ARG는 빌드 스테이지 외부 단계로 인식하기 때문에, 사용하려면 다시 재선언이 필요하다!
</br>

### LABEL
* 기본 구조
    ```dockerfile
    # Default
    LABEL <key>=<value> <key>=<value> <key>=<value> ...
    ```
    - value에 큰따옴표를 사용해야 한다!!
* 띄어쓰기
    ```
    LABEL multi.label1="value1" multi.label2="value2" other="value3"

    LABEL multi.label1="value1" \
        multi.label2="value2" \
        other="value3"
    ```
</br>

### EXPOSE
* 기본 구조
    ```dockerfile
    # Default
    # --platform : linux/amd64, linux/arm64또는 windows/amd64
    EXPOSE <port> [<port>/<protocol>...]
    ```
    - 기본적으로 TCP로 가정한다
    - 이후 컨테이너 실행 시, -p 옵션을 통해 통신할 수 있다.
</br>

### ENV
* 기본 구조
    ```dockerfile
    # Default
    ENV <key>=<value> ...
    ```
    - 이후 컨테이너 실행 시, --env 옵션을 통해 환경 변수 값을 수정할 수 있다.
> 환경 변수가 빌드 중에만 필요하고 최종 이미지에서는 필요하지 않은 경우, RUN 명령어를 사용해서 단일 명령에 대한 값을 설정하는 것이 좋다. 또는 ARG를 사용하는 것이 좋다

</br>

### ADD
내부/원격 파일 복사 및 압축 해제 기능을 가지고 있다.

* 기본 구조
    ```dockerfile
    # Default
    ADD [--chown=<user>:<group>] [--chmod=<perms>] [--checksum=<checksum>] <src>... <dest>
    ADD [--chown=<user>:<group>] [--chmod=<perms>] ["<src>",... "<dest>"]
    ```
    - src : 빌드를 실행하는 위치를 기준으로 가진다. 절대 경로 사용 가능
    - dest : WORKDIR을 기준을 상대경로를 가진다. 절대 경로 사용 가능
    - 정규표현식 사용 가능
    - --chown 옵션을 사용하여 파일 권한을 수정할 수 있다. 단, chown이 수행되려면 루트 파일 시스템에 ```/etc/passwd```, ```/etc/group``` 파일이 포함되어 있어야 한다.
* Example
    ```
    ADD --chown=55:mygroup files* /somedir/
    ADD --chown=bin files* /somedir/
    ADD --chown=1 files* /somedir/
    ADD --chown=10:11 files* /somedir/
    ADD --chown=myuser:mygroup --chmod=655 files* /somedir/
    ```


### COPY
* 기본 구조
    ```dockerfile
    # Default
    COPY [--chown=<user>:<group>] [--chmod=<perms>] <src>... <dest>
    COPY [--chown=<user>:<group>] [--chmod=<perms>] ["<src>",... "<dest>"]
    ```
    - 기본 사용은 ADD와 동일
> 여러 개의 COPY 명령어 사용 시, 각 각의 파일이 영향을 미치지 않으면 --link 옵션을 사용하는 것이 Build 속도가 빠르다 (https://docs.docker.com/engine/reference/builder/#copy, https://ko.linux-console.net/?p=7884)

</br>

### ENTRYPOINT
 실행 파일로 실행될 컨테이너를 구성할 수 있다. 즉, 라이프 사이클이 실행 파일의 실행 상태와 같이 간다.
* 기본 구조
    ```dockerfile
    # Default
    ENTRYPOINT ["executable", "param1", "param2"]

    ENTRYPOINT command param1 param2
    ```
</br>



### VOLUME
* 기본 구조
    ```dockerfile
    # Default
    VOLUME ["<path_in_container>"]
    ```
    - ```/var/lib/docker/volumes/```에 VOLUME이 자동으로 생성된다.
    - -v 옵션을 통해서도 볼륨을 붙일 수 있다.
> VOLUME은  '/var/lib/docker/volumes/'에 종속되어 있기 때문에 -v 옵션을 사용하는 것이 좋아 보인다. 하지만 -v 옵셩을 사용해서 관리하는 경우, VOlUME 명령어에 아무것도 출력되지 않기 때문에 관리자가 해당 구조를 알고 있어야 한다!



### USER
* 기본 구조
    ```dockerfile
    # Default
    USER <user>[:<group>]
    
    USER <UID>[:<GID>]
    ```
</br>


### WORKDIR
* 기본 구조
    ```dockerfile
    # Default
    WORKDIR /path/to/workdir

    # Example
    WORKDIR /a
    WORKDIR b
    WORKDIR c
    RUN pwd         # /a/b/c
    ```
    - 작업 디렉토리는 이전 경로를 기준으로 설정된다.
</br>


### ARG
* 기본 구조
    ```dockerfile
    # Default
    ARG <name>[=<default value>]
    ```
    - ```--build-arg <varname>=<value>``` 옵션을 통해 변수를 재정의할 수 있다!
    - 사전에 정의되어 있는 변수
        - HTTP_PROXY
        - http_proxy
        - HTTPS_PROXY
        - https_proxy
        - FTP_PROXY
        - ftp_proxy
        - NO_PROXY
        - no_proxy
        - ALL_PROXY
        - all_proxy
    - 자동으로 정의되는 변수
        - TARGETPLATFORM - 빌드 결과의 플랫폼. 예 linux/amd64: , linux/arm/v7, windows/amd64.
        - TARGETOS - TARGETPLATFORM의 OS 구성요소
        - TARGETARCH - TARGETPLATFORM의 아키텍처 구성 요소
        - TARGETVARIANT - TARGETPLATFORM의 변형 구성 요소
        - BUILDPLATFORM - 빌드를 수행하는 노드의 플랫폼입니다.
        - BUILDOS - BUILDPLATFORM의 OS 구성요소
        - BUILDARCH - BUILDPLATFORM의 아키텍처 구성 요소
        - BUILDVARIANT- BUILDPLATFORM의 변형 구성 요소

### ONBUILD
* 기본 구조
    ```dockerfile
    # Default
    # --platform : linux/amd64, linux/arm64또는 windows/amd64
    EXPOSE <port> [<port>/<protocol>...]
    ```

### STOPSIGNAL signal
* 기본 구조
    ```dockerfile
    # Default
    # --platform : linux/amd64, linux/arm64또는 windows/amd64
    EXPOSE <port> [<port>/<protocol>...]
    ```


### STOPSIGNAL signal
* 기본 구조
    ```dockerfile
    # Default
    # --platform : linux/amd64, linux/arm64또는 windows/amd64
    EXPOSE <port> [<port>/<protocol>...]
    ```



### STOPSIGNAL signal
* 기본 구조
    ```dockerfile
    # Default
    # --platform : linux/amd64, linux/arm64또는 windows/amd64
    EXPOSE <port> [<port>/<protocol>...]
    ```








---
## 작성 방법
```dockerfile
ARG IMAGE_VERSION=latest
FROM alplain:${IMAGE_VERSION}

LABEL maintainer="hy2" \
    version="1.0" \
    description="This image is test image for test."

EXPOSE 80/tcp 
# EXPOSE 80/udp

# VOLUME ["/data", "/shared"] or docker run -itd -v /host/some/where:/container/some/where ubuntu

USER tester

ENV PYTHON_PATH="/var" PYTHON_HOME="/var"

# --build-arg PYTHON_VERSION=
ARG PYTHON_VERSION="3.8"


WORKDIR /var/

ADD 

COPY 

RUN touch test.txt && 


RUN --mount=type=secret.


```

```sh
#!bin/bash
docker run -p 8080:80/tcp
```
* LABEL
    - LABEL은 한줄로 : 가동성
* ENV
    - ENV는 컨테이너 생성 이후에도 계속 사용되는 경우에만 사용. 그 외에는 ARG 또는 RUN으로 선언 : 예상치 못한 부작용 방지
    - 
* ADD
    - 특별한 상황이 아닌 이상 사용하지 않는다. ADD 명령어는 체인처리가 불가능하므로, RUN wget, curl 등을 사용하는 것이 사이즈를 줄이는 방법이다.
    - 내부 파일을 압춥해제 해서 넣어야 되는 경우에만 사용! 아니면 COPY + RUN을 써야 한다.
* COPY
    - 여러 개의 COPY 명령어 사용 시, 각 각의 파일이 영향을 미치지 않으면 --link 옵션을 사용하는 것이 Build 속도가 빠르다
* VOLUME
    - VOLUME 명령어를 사용하면 기본적으로 docker 내부 디스크에 종속되기 때문에 관리 포인트에 따라 -v 옵션을 통해 사용하는 것이 좋을 수 있다.
* WORKDIR
    - 꼭 써줘야 하는 것이 좋다!
* 사이즈 크기 줄이는 방법
* RUN --mount=type=secret.



* Example
    ```dockerfile
    # Docker File
    FROM centos:7

    ARG APP_NAME
    ENV H_NAME $APP_NAME

    ENTRYPOINT ["/bin/bash", "-c", "echo hello $H_NAME"]



    --chown 옵션으로 파일과 디렉토리에 대한 소유 권한을 지정할 수 있다.
 
COPY --chown=toram:toram *.txt /toramko/
출처: https://toramko.tistory.com/entry/docker-도커파일Dockerfile-의-개념-작성-방법문법-작성-예시 [토람코:티스토리]
    ```

```
$ vi .dockerignore

.git

.gitignore

README.md

LICENSE

.vscode
```



```
$ docker build --build-arg APP_NAME="Test" --tag test .

$ docker inspect test

$ docker run -it --name test
```



## 명령어
```
# Docker Image Remove
$ docker rmi <image>


# Container Process Check
$ docker ps -a


# Container Remove
$ docker rm <container>

```



## Build 




## Run
| 옵 션 | 설 명 | 비 고 |
|------|------|------|
| -i / --interactive | 컨테이너 표준 입력(stdin) 활성화 (-it 같이 사용) |  |
| -t / --tty | tty 할당 (-it 같이 사용) |  |
| --name | 컨테이너 이름 지정 | --name abc02 |
| -d / --detach | Background에서 실행 |  |
| -p / --publish | Host와 컨테이너 포트 연결 (Port Forwading)) | -p <host_port>:<container_port> |
| -v / --volume | Host와 컨테이너 디렉터리 연결 (Volume Mount) | -v <host_abs_path>:<container_abs_path> |
| -e | ENV 환경 변수 설정 | -e PI=3.14 |
| --env-file | ENV 환경 변수 파일 설정 | --env-file <path> |
| --rm | 컨테이너 종료 시, 컨테이너 삭제 옵션 |  |
| --restart | 컨테이너 종료 시, 재시작 정책 설정 (--rm과 함께 쓰일 수 없다) | --restart="always" (항상 재시작) / "on-failure" (종료 status가 0이 아닌 경우 재시작) |
| --privileged | 컨테이너 안에서 Host의 리눅스 커널 기능 사용 |  |