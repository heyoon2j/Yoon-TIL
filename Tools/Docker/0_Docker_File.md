# Docker File
작성법은 다음과 같다.


## Docker File 작성법
| Command | Description |
|---------|-------------|
| FROM | Base Image 지정 |
| RUN |  |
|  |  |
|  |  |
|  |  |
|  |  |
|  |  |
|  |  |



* Example
    ```dockerfile
    # Docker File
    FROM centos:7

    ARG APP_NAME
    ENV H_NAME $APP_NAME

    ENTRYPOINT ["/bin/bash", "-c", "echo hello $H_NAME"]
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