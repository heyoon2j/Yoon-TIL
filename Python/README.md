# Python

## Install Python
* 사이트 접속 : https://www.python.org/downloads/
* 버전 상태(Bugfix 등)을 확인 후 설치할 버전 선택
* Python 버전 확인 : ```python --version or python3 --version```

1. Windows
    * executable installer 설치
2. Mac OS
    * Winodws와 같은 방법으로 설치
3. Red Hat, CenOS, Debian, Ubuntu
    * Copy link : Gzipped tarball
    * Download tgz file
        ```
        $ wget https://www.python.org/ftp/python/3.7.9/Python-3.7.9.tgz
        ```
    * 압축 해제
        ```
        $ tar xvfz Python-3.7.9.tgz
        ```
    * Install
        * README.rst를 읽어보면 아래와 같은 순으로 진행하라고 알려준다.
            1) ./configure
            2) make
            3) make test
            4) sudo make install
        * Python을 설치하려면 여러가지 의존 시스템을 설치해줘야 된다.
            1) GCC Compiler (gcc)
            2) Openssl or libssl (openssl-devel)
            3) _cpython (libffi-dev)
        ```
        #    ./configure
        #    make
        #    make test
        #    sudo make install

        $ cd ./Python-3.7.9
        $ ./configure           # ./configure --prefix=/usr/local, prefix는 위치를 설정할 수 있다.
        $ make
        $ make test
        $ make install
        ```

## Set Path
1. Windows
    * 시스템 -> 고급 시스템 설정 -> 고급 탭 -> 환경 변수
    * 시스템 변수
        * 새로 만들기 : PYTHON_HOME = C:\Program Files\Python\Python37
        * 기존 시스템 변수 Path에 추가 : %PYTHON_HOME%;%PYTHON_HOME%\Scripts;
2. Linux
    ```
    $ vi ~/.bashrc  # 또는 ~/.profile, /etc/environment
    export PYTHON_HOME=/usr/local/bin/python3/
    export PATH=$PATH:$PYTHON_HOME
    ```


## Virtual Environment
* Python 환경을 구축하기 위해 필욯나 모듈만 담은 개발 환경
1. Install virtualenv
    ```
    $ pip3 install virtualenv
    ```

2. Create virtual environment
    ```
    $ cd ~
    $ virtualenv venv_name
   
    # 원하는 버전 선택
    $ virtualenv venv_name --pyton=python2
    $ virtualenv venv_name --pyton=python3
    $ virtualenv venv_name --pyton=python3.7
    ```

3. Activate & Deactivate venv
    ```
    # Activating
    $ activate venv_name
    $ source ~/venv_name/bin/activate
   
    # Deactivating
    $ deactivate
    ```

#### Reference
* https://www.python.org/about/gettingstarted/
