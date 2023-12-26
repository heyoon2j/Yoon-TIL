# Python

## Install Python
* 사이트 접속 : https://www.python.org/downloads/
* 버전 상태(Bugfix 등)을 확인 후 설치할 버전 선택
* Python 버전 확인 : ```python --version or python3 --version```

#### 1. Windows
* executable installer 설치
#### 2. Mac OS
* Winodws와 같은 방법으로 설치
#### 3. Red Hat, CenOS, Debian, Ubuntu
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
#### 1. Windows
* 시스템 -> 고급 시스템 설정 -> 고급 탭 -> 환경 변수
* 시스템 변수
    * 새로 만들기 : PYTHON_HOME = C:\Program Files\Python\Python37
    * 기존 시스템 변수 Path에 추가 : %PYTHON_HOME%;%PYTHON_HOME%\Scripts;
#### 2. Linux
* 설정 방법은 아래와 같다.
    ```
    $ vi ~/.bashrc  # 또는 ~/.profile, /etc/environment
    export PYTHON_HOME=/usr/local/bin/python3/
    export PATH=$PATH:$PYTHON_HOME
    ```
</br>
</br
>

---
## pip
* Pip Installs Packages의 약자. Python Package를 설치 및 관리한다.
* pip.exe의 위치는 ```<python_dir>/script```에 위치

### Install
* 명령어: 
    ```
    curl -O https://bootstrap.pypa.io/get-pip.py
    python3 get-pip.py --user
    ```


### Command
* Package 설치
    ```
    $ pip install <package_name>
    ```
* Package 정보 확인
    ```
    $ pip show <package_name or names>
    ```
* 설치된 Package list 확인
    ```
    $ pip list
    ```
* Package 삭제
    ```
    $ pip uninstall <pacakge_name>
    ```


## Virtual Environment
* Python 환경을 구축하기 위해 필요한 모듈만 담은 개발 환경
#### 1. Install virtualenv
```
$ pip3 install virtualenv
```

#### 2. Create virtual environment
```
$ cd ~

# 기본으로 설정된 버전으로 만들어진다.
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

#### 3. Activate & Deactivate venv
```
# Activating
$ activate venv_name
$ source ~/venv_name/bin/activate
$ source ~/venv_name/Scripts/activate
   
# Deactivating
$ deactivate
```

## Visual Studio Code 연동
#### 1. "**Python**"과 "**Python for VSCode**" 확장 모듈 설치
* EXTENSIONS: MARKETPLACE 에서 설치 가능 (Ctrl + Shift + X)
* 설치 후 Reload 해준다.

#### 2. 가상 환경 구성
* virtualenv 사용

#### 3. Select interpreter
* Show Command Palette (F1 or Ctrl+Shift+P) -> Python select interpreter 클릭
* 현재 PC에서 사용 가능한 Python version과 virtual environment를 보여준다.
* 원하는 환경 선택, 없다면 원하는 venv의 python.exe 파일 위치를 찾는다.
* ```Error Message: The Python path in your debug configuration is invalid.```

#### 4. Code Convention Tool 설치
##### 1) Pylint
* Code Style Checker, 문법 검사를 한다.
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
        
##### 2) Black
* Auto Formatter가 수행된다.
* 가상 환경에서 ```pip3 install black```
* 코드 저장 시, 자동으로 Code Convention을 맞춰준다.
* vscode 명령어 : ```shift + alt + f```
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
  
* 다른 블로그들을 확인하면 VSCode에서 자동으로 Install 창이 떠서 설치할 수 있다고 나온다.
* 하지만 여러 시도를 해본 결과, 계속해서 파일의 \(Slash)가 없어지는 현상이 있어서 터미널에서 설치를 진행하였다.
    * https://github.com/microsoft/vscode-python/issues/13005
    * https://github.com/microsoft/vscode-python/issues/13493
    ```
    $ c:/Users/Yoon/Workspace/venv/test/Scripts/python.exe c:\Users\Yoon\.vscode\extensions\ms-python.python-2020.10.332292344\pythonFiles\pyvsc-run-isolated.py pip install -U pylint
    C:\Program Files\Python\Python37\python.exe: can't open file 'c:UsersYoon.vscodeextensionsms-python.python-2020.10.332292344pythonFilespyvsc-run-isolated.py': [Errno 2] No such file or directory
    ```


##### 3) Markdown
* VSCode extension (단축키: ```ctrl```+```shift```+```x```) -> Markdown 검색 -> "Markdown All in One" 설치


##### 4) vim
* VSCode extension (단축키: ```ctrl```+```shift```+```x```) -> vim 검색 -> vim 설치
* vim mode on/off 방법
    * ```ctrl```+```shift```+```p``` 입력 -> vim toggle 검색 및 클릭


## pipenv
* 공식 : https://pypi.org/project/pipenv/
* 참조
    * https://cjh5414.github.io/how-to-manage-python-project-with-pipenv/
    * https://pipenv.pypa.io/en/latest/
* pipenv를 사용하는 이유
    1. Pipfile, Pipfile.lock을 이용하여 패키지를 추가/삭제할 수 있다.
    2. 기존에 pip와 virtualenv 동시에 사용하는 것으로 가상환경과 Dependencies를 더 편하게 관리할 수 있다.
    3. Dependency에 대한 그래프를 제공한다 ($ pipenv graph)
    4. .env 파일을 이용하여 개발 워크플로어를 로딩한다.

## 1) Installation   
* using pip
    ```
    $ pip install pipenv
  
    # pipx
    $ pipx install pipenv
    ```

* using Debian
    ```
    $ sudo apt install pipenv
    ```
  
* using Mac
    ```
    $ pip install pipenv
    ```


## 2) Basic Concept
* virtualenv가 하나도 없으면 자동으로 생성된다.
* install에 매개 변수를 전달하지 않으면, 모든 패키지들[packages]이 설치된다.
* Python 3 가상환경으로 초기화하기 위해서는, ```$ pipenv --three```
* Python 2 가상환경으로 초기화하기 위해서는, ```$ pipenv --two```
* 버전을 설정하지 않으면, virtualenv는 현재 시스템의 Python version 기본값으로 설정된다.


## 3) Other Command
* ```shell```: activated virtualenv에서 shell을 실행시킨다. 해당 shell은 ```exit```를 이용해 deactivated 할 수 있다.
* ```run```: 전달된 인수와 함께 virtualenv에서 명령을 실행한다. ex> ```$ pipenv run pip freeze```
* ```install```: 주어진 Package를 설치하고 Pipfile에 추가한다.
* ```uninstall```: 주어진 Package를 삭제하고 Pipfile에서도 삭제한다.
* ```check```: 보안 취약성을 확인하고 현재 환경에서 PEP 508 요구 사항을 충족하는지 확인한다.
* ```graph```: 설치된 모든 dependencies에 대한 그래프를 출력한다.
* ```lock```: Pipfile.lock 생성
* ```sync```: Pipline.lock에 특정되어 있는 모든 Package를 설치한다.
* ```clean```: Pipfile.lock에 특정되어 있지 않는 모든 Package들을 uninstall 시킨다.


## 4) 사용 방법
### Step 0. 권장 사항
* 일반적으로 Pipfile 및 Pipfile.lock 버전 제어를 모두 유지하는 것이 좋다.
* 여러 버전으로 관리하는 것은 좋지않고, 버전은 하나로 통일하는 것이 좋다.
* 버전 지정은 Pipfile의 [requires]를 참조하면 된다.
* Pipfile의 ```python_version```의 포맷은 ```x.y``` or ```x```여야 하며, 
```python_full_version```의 포맷은 ```x.y.z```여야 한다.

### Step 1. virtualenv 생성
* 원하는 위치로 이동 후 virtualenv 생성, 이후 프로젝트는 해당 virtualenv로 실행된다.
* virtualenv를 생성하기 위해 ```--two --three --python x.y.z``` 옵션을 사용하며, Pipfile도 함께 생성된다.
* 생성하기 전에 virtualenv가 존재하는지 확인하는 것이 좋다. 그 이유는 ```--two```, ```--three``` 옵션의 경우, 기존 환경을 삭제하기 때문이다.
    ```
    $ cd pyPprj
  
    # Check virtualenv information
    $ pipenv --venv

    # virtualenv version
    $ pipenv --two      # Python 2
    $ pipenv --three    # Python 3  
    $ pipenv --python 3.7 # Specific versiono
    
    # Remove virtualenv
    $ pipenv --rm
    ```

### Step 2. Pipenv 환경 관리
* pipenv 환경은 다음 명령어로 관리 된다.
* ```$ pipenv install [package names]```
    * pipenv 가상 환경에 패키지를 설치하고 Pipfile을 업데이트한다.
    * 기본적으로 default 환경에만 설치된다. develop 환경에 설치하기 위해서는 ```--dev``` 옵션을 추가해야 한다.
        ```
        $ pipenv install pytest             # default 환경에만 설치
        $ pipenv install --dev pytest       # develop 환경에만 설치
        $ pipenv install                    # default 환경 패키지들만 설치
        $ pipenv install --dev              # default와 develop 모든 환경 패키지들 설치
        ```
    * 옵션
        * ```--dev```: Pipfile에 있는 develop과 default 패키지들을 설치한다.
        * ```--system```: 시스템 pip 명령어를 사용한다.
        * ```--deploy```: 패키지들이 Pipfile.lock에 제대로 잠겨있는지 확인하며, lock 파일이 오래된 경우에는 중단한다.
        * ```--ignore-pipfile```: Pipfile은 무시하고 Pipfile.lock에 있는 패키지를 설치한다.
        * ```--skip-lock```: Pipfile.lock은 무시하고 Pipfile에 있는 패키지를 설치한다. 
        추가적으로 Pipfile의 변경사항을 Pipfile.lock에 쓰지 않는다.
    * 버전 지정
        * 기본적으로 ```major.minor.micro``` 형태로 버전을 지정할 수 있다.
        * 지정 시, 자동으로 Pipfile도 업데이트한다.
        * ```requests```를 사용할 때, 리디렉션을 방지하기 위해 쌍따옴표를 사용한다.
        * 예시는 다음과 같다.
        ```shell script
        $ pipenv install "requests>=1.2"  # 1.2.0 이상의 버전을 설치한다.
        $ pipenv install "requests<=2.21" # 2.21.0 이하의 버전을 설치한다.
        $ pipenv install "requests>3.4"   # 3.4.0 초과 버전을 설치
        $ pipenv install "requests~=2.22" # 2.22 버전을 설치하고 minor 버전은 업데이트한다. 즉 major 버전이 2로 잠긴다.
        $ pipenv install "requests!=2.22" # 2.22 버전은 설치하지 않는다.
        
        # == 보다 ~=를 사용하는 것이 좋다. == 는 업데이틑를 잠그기 때문이다.
        ```
    * 편집 가능한 Dependencies 지정
        * ```pipenv -e [path]```
        * 모든 하위 종속성은 Pipfile.lock에도 추가된다.
        ```shell script
        $ pipenv -dev -e .    # current path
      
        $ cat Pipfile
        ...
        [dev-packages]
        "e1839a8" = {path = ".", editable = true}
        ...
        ```
* ```$ pipenv uninstall [package names]```
    * 기본적으로 pipenv install이 지원하는 모든 파라미터를 사용할 수 있다.
    * ```--all```: 가상 환경에서 모든 파일을 제거하지만 Pipfile은 그대로 둔다.
    * ```--all-dev```: 가상 환경에서 모든 개발 패키지들을 제거하고 Pipfile에서도 삭제한다.
* ```$ pipenv lock```
    * Pipfile.lock을 생성하는데 사용된다.
    * 모든 종속성 및 하위 종속성과 종속성에 대한 사용 가능한 최신 버전 및 현재 Hash들이 선언되어 있다.
    * 이것은 반복적으로 가능하고 결정적 빌드를 보장한다.
    * ```--pre``` 옵션을 이용하여 pre-release 패키지를 추가할 수 있다.
    * ```--clear``` 옵션을 이용해 캐시를 삭제할 수 있다.

### Step 3. Pipenv shell 활성/비활성
* Pipenv shell을 활성화하기 위해서 ```pipenv shell```을 입력한다. 해당 명령어를 통해 새로운 셀 하위 프로세스가 생성된다.
* shell을 비활성화를 위해서는 ```exit```를 입력하면 된다.
    ```shell script
    $ pipenv shell
    $ exit
    ```



## pipx
* https://pipxproject.github.io/pipx/comparisons/
* https://github.com/pipxproject/pipx



#### Reference
* https://www.python.org/about/gettingstarted/
* https://m.blog.naver.com/wideeyed/221837368919
* https://jhyeok.com/python-with-vscode/