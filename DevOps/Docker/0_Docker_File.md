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
| ONBUILD | 다음 이미지 실행 트리거 |  |
| STOPSIGNAL | 컨테이너 종료 시그널 |  |
| HEALTHCHECK | Health check 설정 |  |
| SHELL | 기본 쉘 재정의 |  |

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



### RUN
* 기본 구조
    ```dockerfile
    # Default
    RUN /bin/bash -c 'source $HOME/.bashrc && echo $HOME'

    RUN ["/bin/bash", "-c", "echo hello"]
    ```
* mount 옵션
    ```dockerfile
    # Default
    EXPOSE <port> [<port>/<protocol>...]
    ```
* network 옵션
    ```dockerfile

    ```
* security 옵션
    ```dockerfile
    
    ```


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
</br>

### ONBUILD
자신이 만든 이미지가 베이스로 사용되어 질 때, 새로 만들어진 이미지에서 특정 명령어를 실행시키고 싶을 때 사용한다. 즉, 트리거라고 생각하면 되고, 내가 만들 당시에는 명령어가 실행되지 않는다!! 

* 기본 구조
    ```dockerfile
    # Default
    # --platform : linux/amd64, linux/arm64또는 windows/amd64
    EXPOSE <port> [<port>/<protocol>...]
    ```
> 언제 사용되나? 예시로는 베이스에 환경(Infra, OS, WAS etc)을 구축하고, 개발자가 해당 이미지로 코드만 특정 위치에 올리는 형태에서 사용된다. (https://nirsa.tistory.com/68)

</br>

### STOPSIGNAL
컨테이너를 종료할 때, 송신하는 시그널을 설정할 수 있다. 시그널을 통해 후킹을 할 수 있다 (특정적으로 사용하는 거 아니면, 굳이 사용할 필요가 있나?)

기본적으로 Docker는 SIGTERM 신호를 보내고, 종료되지 않으면, SIGKILL을 보낸다. 이때 조심해야 되는 것은 ENTRYPOINT 또는 RUN 명령어가 exec 형식을 사용해야 된다는 점이다. 명령이 shell 하위 명령으로 실행하게 되면 컨테이너의 PID 1이 실행시키고 싶은 명령이 아닌 shell이 되기 때문이다. 이렇게 되면 실행시키고 싶었던 명령이 SIGTERM을 못 받게 되다보니 비정상적인 종료를 하게 된다!

* 기본 구조
    ```dockerfile
    # Default
    STOPSIGNAL signal
    ```
* 조심해야 되는 형태
    ```
    ...
    ENTRYPOINT ["/bin/sh", "systemctl", "start", "abc"]
    ```
* Reference
    - https://ingnoh.tistory.com/39
    - https://ingnoh.tistory.com/39
</br>



### SHELL
기본 쉘을 재정의할 수 있다. Linux 기본 쉘은 ```["/bin/sh", "-c"]``` 이고, Windows는 ```["cmd", "/S", "/C"]``` 이다.

쉘을 변경해서 사용하고 싶은 경우에는 사용하는 것이 좋다. 그렇지 않으면 불필요한 명령을 계속 추가해서 사용해야되기 때문이다.

* 기본 구조
    ```dockerfile
    # Default
    SHELL ["executable", "parameters"]
    ```
* Linux 
    - zsh, csh, tcsh
* Windows
    ```docekrfile
    # Powershell
    SHELL ["powershell","-command"]
    ```
</br>
</br>





---
## 작성 방법
```dockerfile
# Build
ARG IMAGE_VERSION=latest
FROM alpine:${IMAGE_VERSION} as build

LABEL maintainer="hy2" \
    version="1.0" \
    description="This image is test image for test."
# LABEL maintainer="hy2" version="1.0" description="This image is test image for test."

EXPOSE 80/tcp 
# EXPOSE 80/udp

# VOLUME ["/data", "/shared"] or docker run -itd -v /host/some/where:/container/some/where ubuntu

RUN groupadd -r postgres && useradd --no-log-init -r -g postgres postgres


ENV PYTHON_PATH="/var" PYTHON_HOME="/var"

# --build-arg PYTHON_VERSION=
ARG PYTHON_VERSION="3.8"


WORKDIR /var/

# ADD --checksum=sha256:270d731bd08040c6a3228115de1f74b91cf441c584139ff8f8f6503447cebdbb \
#    https://dotnetcli.azureedge.net/dotnet/Runtime/$DOTNET_VERSION/dotnet-runtime-$DOTNET_VERSION-linux-arm64.tar.gz /dotnet.tar.gz



ONBUILD COPY website.tar /var/www/html/
COPY --chown=toram:toram *.txt /toramko/
# COPY requirements.txt /tmp/requirements.txt
RUN --mount=type=bind,source=requirements.txt,target=/tmp/requirements.txt \
    pip install --requirement /tmp/requirements.txt


RUN apt-get update && apt-get install -y \
  bzr \
  cvs \
  git \
  mercurial \
  subversion \
  && rm -rf /var/lib/apt/lists/*
RUN wget -O - https://some.site | wc -l > /number

ENTRYPOINT ["/bin/bash", "-c", "echo hello $H_NAME"]
# CMD ["postgres"]

```
* 효율적으로 작성하는 방법
    - 빌드 캐시 사용하기 위해 변경이 많은 명령어는 아래쪽이나 잘 분리한다.
    - LABEL : 가독성을 위해 한 줄로
    - ENV : 
        1) ENV는 컨테이너 생성 이후에도 계속 사용되는 경우에만 사용.
        2) 그 외에는 ARG 또는 RUN export & unset 으로 선언 (예상치 못한 부작용 방지)
    - ADD
        1) 특별한 상황이 아닌 이상 사용하지 않는다. ADD 명령어는 체인처리가 불가능하므로, RUN wget, curl 등을 사용하는 것이 사이즈를 줄이는 방법이다.
        2) 내부 파일을 압춥해제 해서 넣어야 되는 경우에만 사용! 아니면 COPY + RUN을 써야 한다.
    - COPY
        - 여러 개의 COPY 명령어 사용 시, 각 각의 파일이 영향을 미치지 않으면 --link 옵션을 사용하는 것이 Build 속도가 빠르다
        - 명령을 실행하기 위해 일시적으로 파일을 컨테이너에 추가해야 되는 경우에는 RUN을 대체하는 것이 좋다.
    - VOLUME
        - VOLUME 명령어를 사용하면 기본적으로 docker 내부 디스크에 종속되기 때문에 관리 포인트에 따라 -v 옵션을 통해 사용하는 것이 좋을 수 있다.
    - WORKDIR
        - 꼭 써줘야 하는 것이 좋다! 그렇지 않으면 동작이 어디서 일어나고 있는지 확인이 어렵다.
        - 그렇기 때문에 RUN cd 보다 WORKDIR을 사용하는 것이 좋다
    - USER
        - Root 권한이 꼭 필요하지 않는 이상 user 생성하는 것부터 시작하는 것이 좋다.
        - 꼭 필요하면 Root 사용을 고려한다.
    - CMD
        - 내부에서 특정적으로 사용하는 것이 아닌 이상 거의 사용하지 않는다.
    - ONBUILD
        - ONBUILD된 이미지에는 별도의 태그가 있어야 한다 (ruby:1.9-onbuild)
* 사이즈 크기 줄이는 방법
    1) 가벼운 Base image 사용
    2) 레이어 단계 줄이기
        - Layer를 생성하는 명령어는 Chaining 할 수 있는 경우 최대한 해주는 것이 좋다(RUN command1 && command2
        - 명령어의 특성에 맞게 사용하기
    3) 불필요한 데이터 복사 X (.dockerignore 활용)
    4) 불필요한 패키지 설치 X (개발용 패키지 설치 X, --no-install-recommends, Pipefile.lock과 같은 Lock 파일 등 사용)
        - Base image에서 삭제 후 만들어도 된다.
        - Multi stage 사용
    5) 불필요한 파일 및 캐시 삭제 O (apt-get clean, rm -rf data 등 사용)
    6) 압축파일 해제
    7) Application 역할 분리 (하나의 역할을 가지게 구성)
    8) 중간 이미지 하나로 합치기 (docker export & import)
    9) Multi-stage 패턴 사용 : 빌드 등에는 필요하지만 최종 이미지에서는 필요 없는 환경을 제거할 수 있도록 단계를 나누어서 만드는 기법
        ```
        # syntax=docker/dockerfile:1
        FROM golang:1.21 as build
        WORKDIR /src
        COPY <<EOF /src/main.go
        package main

        import "fmt"

        func main() {
        fmt.Println("hello, world")
        }
        EOF
        RUN go build -o /bin/hello ./main.go

        FROM scratch
        COPY --from=build /bin/hello /bin/hello
        CMD ["/bin/hello"]
        ```
        - as를 사용하여 스테이지에 이름을 붙일 수 있다.
        > 예시로 빌드 단계에서 빌드에 필요한 모든 것을 설치한 뒤 빌드하고, 실행 단계에는 빌드한 내용만 전달한다!! 


---
## Build Cache
이미지를 빌드하게 되면 Build cache라는 것이 생성이 된다. 이것을 통해 우리는 변경 분에 대해 보다 빠른 빌드를 할 수 있게 된다. 다음 이미지를 보면서 캐시에 대해서 설명하면 다음과 같다.

![image_cache_stack](img/image_cache_stack.png)
1. 기본 이미지를 생성
2. 빌드 캐시가 저장
3. main.c 내용 수정 ---> COPY 명령어에 영향
4. COPY 명령어부터 downstream 레이어 내용은 전부 무효화 됨
5. 하지만 COPY 명령어의 upstream 레이어들은 캐시가 있기 때문에 추가 빌드가 필요하지 않다. 이것이 빌드 캐시이다.


* 캐시를 효율적으로 사용하는 방법
    - 레이어 순서 지정
        ```dockerfile
        # Before
        FROM node
        WORKDIR /app
        COPY . .          # Copy over all files in the current directory
        RUN npm install   # Install dependencies
        RUN npm build     # Run build


        # After
        FROM node
        WORKDIR /app
        COPY package.json yarn.lock .    # Copy package management files
        RUN npm install                  # Install dependencies
        COPY . .                         # Copy over project files
        RUN npm build                    # Run build
        ```
        - npm install을 미리 실행하여 종속성 설치하는 부분을 캐싱해 둔다



---
## Image 최적화 Test
상황 : Python 프로젝트에 컴파일이 필요한 C/C++$$ 
```dockerfile
# Build
ARG IMAGE_VERSION="3.12.1-alpine3.19"
FROM python:${IMAGE_VERSION} as build

LABEL maintainer="hy2" \
    version="1.0" \
    description="This image is test image for test."
# LABEL maintainer="hy2" version="1.0" description="This image is test image for test."

EXPOSE 80/tcp 
# EXPOSE 80/udp

# VOLUME ["/data", "/shared"] or docker run -itd -v /host/some/where:/container/some/where ubuntu

ENV PYTHON_PATH="/var" PYTHON_HOME="/var"

ARG PYTHON_VERSION="3.8"

RUN groupadd -r postgres && useradd --no-log-init -r -g postgres postgres

# --build-arg PYTHON_VERSION=



WORKDIR /var/

# ADD --checksum=sha256:270d731bd08040c6a3228115de1f74b91cf441c584139ff8f8f6503447cebdbb \
#    https://dotnetcli.azureedge.net/dotnet/Runtime/$DOTNET_VERSION/dotnet-runtime-$DOTNET_VERSION-linux-arm64.tar.gz /dotnet.tar.gz



ONBUILD COPY website.tar /var/www/html/
COPY --chown=toram:toram *.txt /toramko/
# COPY requirements.txt /tmp/requirements.txt
RUN --mount=type=bind,source=requirements.txt,target=/tmp/requirements.txt \
    pip install --requirement /tmp/requirements.txt


RUN apt-get update && apt-get install -y \
  bzr \
  cvs \
  git \
  mercurial \
  subversion \
  && rm -rf /var/lib/apt/lists/*
RUN wget -O - https://some.site | wc -l > /number

ENTRYPOINT ["/bin/bash", "-c", "echo hello $H_NAME"]
# CMD ["postgres"]

######################################################
# Application Stage
FROM 



```






