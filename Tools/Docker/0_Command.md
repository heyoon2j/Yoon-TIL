# Install

* 
    ```
    ```

## Basic
* Print inforamtion
    ```sh
    $ docker info
    ...
    Docker Root Dir: /var/lib/docker
    Debug Mode: false
    Registry: https://index.docker.io/v1/
    Labels:
    Experimental: false

    ```



---

## Image
* Print list of docker image
    ```
    $ docker images
    REPOSITORY   TAG       IMAGE ID   CREATED   SIZE
    ```

* Download image from repository
    ```
    $ docker pull centos
    ```

* Upload image to repository
    ```
    $ docker push centos
    ```
</br>


---
## Process
* Print list of docker process
    ```bash
    # docker ps [option]
    # -a : 모든 프로세스 출력 (종료된 프로세스 포함)
    # 

    $ docker ps
    CONTAINER ID   IMAGE     COMMAND   CREATED   STATUS    PORTS     NAMES
    ```
    - CONTAINER ID : 컨테이너 ID (Random)
    - IMAGE : 실행한 이미지
    - COMMAND : 실행할 명령
    - CREATED : 생성된 시간
    - STATUS : 프로세스 상태
    - PORTS : 
    - NAMES : 컨테이너 이름

* Run process
    ```sh
    # docekr run [optional] <image:tag> --name <name>

    # Run background
    $ docker run -d centos:latest

    # Run 
    $ docker run -it centos:latest bash --name testCentos
    ```
    * 

* Restart porcess
    ```sh
    # docker restart 
    ```

</br>


