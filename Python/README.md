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
            1) gcc Compiler (gcc)
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
    # Python2
    $ python -m virtualenv venv_name
    $ virtualenv venv_name --pyton=python2
    
    # Python3
    $ python3 -m virtualenv venv_name
    $ virtualenv venv_name --pyton=python3
    $ virtualenv venv_name --pyton=python3.7
    ```

3. Activate & Deactivate venv
    ```
    # Activating
    $ activate venv_name
    $ source ~/venv_name/bin/activate
    $ source ~/venv_name/Scripts/activate
   
    # Deactivating
    $ deactivate
    ```

## Visual Studio Code 연동
1. EXTENSIONS: MARKETPLACE 에서 "**Python**"과 "**Python for VSCode**" 확장 모듈 설치 (Ctrl + Shift + X)
    * 설치 후 Reload 해준다.
2. 가상 환경 구성
    * virtualenv 사용
3. Show Command Palette (F1 or Ctrl+Shift+P) -> python select interpreter 클릭
    * 현재 PC에서 사용 가능한 Python version과 virtual environment를 보여준다.
    * 원하는 환경 선택, 없다면 원하는 python.exe 파일을 찾는다.
4. Code Convention Tool 설치
    * 다른 블로그들을 확인하면 VSCode에서 자동으로 Install 창이 떠서 설치할 수 있다고 나온다.
    * 하지만 여러 시도를 해본 결과, 계속해서 파일의 \(Slash)가 없어지는 현상이 있어서 터미널에서 설치를 진행하였다.
        * https://github.com/microsoft/vscode-python/issues/13005
        * https://github.com/microsoft/vscode-python/issues/13493
        ```
        $ c:/Users/Yoon/Workspace/venv/test/Scripts/python.exe c:\Users\Yoon\.vscode\extensions\ms-python.python-2020.10.332292344\pythonFiles\pyvsc-run-isolated.py pip install -U pylint
        C:\Program Files\Python\Python37\python.exe: can't open file 'c:UsersYoon.vscodeextensionsms-python.python-2020.10.332292344pythonFilespyvsc-run-isolated.py': [Errno 2] No such file or directory
        ```
    1) Pylint
        * 가상 환경에서 ```pip3 install pylint``` 설치
        * Show Command Palette (F1 or Ctrl+Shift+P) -> Python: Select Linter -> Pylint
        * settings.json 파일에 아래 옵션이 자동으로 추가된다.
            * 저장 시, 자동으로 검사하도록 "python.linting.lintOnSave": true 옵션 추가
            ```
            # Linter 선택 시, 자동으로 추가
            "python.linting.pylintEnabled": true,
            "python.linting.enabled": true
          
            # 저장 시, 자동 검사 옵션 추가
            "python.linting.lintOnSave": true
            ```
        
    2) Black
        * 가상 환경에서 ```pip3 install black```
        * 코드 저장 시,면 자동으로 Code Convention을 맞춰준다.
        * settings.json에 아래 옵션 추가
            * "python.formatting.provider" : provider 설정
            * "editor.formatOnSave" : 저장 시 자동 변경
        ```
        {
            "python.pythonPath": "test\\Scripts\\python.exe",
            "python.formatting.provider": "black",
            "python.formatting.blackArgs": [
                "--line-length",
                "100"
            ],
            "editor.formatOnSave": true
        }
        ```


#### Reference
* https://www.python.org/about/gettingstarted/
* https://m.blog.naver.com/wideeyed/221837368919
* https://jhyeok.com/python-with-vscode/