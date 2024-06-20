# Project
프로젝트의 규모가 커지고 유지보수할 것들이 늘어날 수록, 관리되어져야 하는 포인트들이 점차 많아진다. 그렇기 때문에 포인트를 줄여야 하는데 이를 위해 여러가지 도구들을 사용하게 된다.

프로젝트 구성 및 배포 과정은 다음과 같다.
1. 개발 환경 구성 
2. 프로젝트 구성 : https://devocean.sk.com/blog/techBoardDetail.do?ID=163566
3. 코드작성
4. 배포 : https://bskyvision.com/entry/python-%EB%82%B4%EB%B6%80%EB%A7%9D%ED%8F%90%EC%87%84%EB%A7%9D%EC%97%90-whl-%ED%8C%8C%EC%9D%BC%EC%9D%84-%ED%86%B5%ED%95%B4%EC%84%9C-%ED%8C%A8%ED%82%A4%EC%A7%80-%EC%84%A4%EC%B9%98%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95
5. 컨테이너 : https://www.ghanei.net/python-app-multistage-docker-build/
6. https://pythonspeed.com/articles/multi-stage-docker-python/


Reference
- https://ryanking13.github.io/2021/07/11/python-packaging.html
- https://devocean.sk.com/blog/techBoardDetail.do?ID=163566


보통 프로젝트 구성 요소는 다음과 같다.
- 프로젝트 설명 문서 (README.md)
- 라이센스 문서 (LICENSE)
- 프로젝트 메타데이터
- 프로젝트 의존성 명세서
- 프로젝트 빌드 명세서
- 프로젝트 소스코드 (src)
- 라이브러리 (libs)

* 프로젝트 구성
    ```
    project
    ├── README.md
    ├── LICENSE
    ├── project_metadata
    ├── build_metadata 
    ├── build/
    ├── lib/
    ├── src
    │   └── proj
    │       ├── __init__.py
    │       ├── core
    │       ├── 
    │       ├── 
    │       ├── 
    │       └── utily
    ├── test
    └── dist
    ```
    - README.md : README 파일
    - LICENSE : Project License 정보
    - project_metadata : 프로젝트 구성 정보
    - build_metadata : 빌드 및 패키지 의존성 정보
        - Python : poetry (pyproject.toml, requirements.txt, setup.py)
        - JAVA : Gradle (build.gradle, settings.gradle)
        - C/C++
    - build : Build 관련된 파일
    - lib : 외부 라이브러리 파일
    - src : Source Code
    - test : Test Code
    - dist : 배포할 파일

dadependency pyproject.toml or setup.py)






---
## Python

---
## 프로젝트 구성
### Project Structure
```sh
## Directory Structure
$ tree .

python_proj
├── README.md
├── LICENSE
├── pyproject.toml or setup.py)
├── src
│   └── y2proj
│       ├── __init__.py
│       ├── core
│       ├── 
│       ├── 
│       ├── 
│       └── utily
└── test
```
- LICENSE : Project License 정보
- README.md : README 파일
- pyproject.toml (or setup.py): 패키지 빌드 및 의존성 명세서 (메타데이터)
- src : Source Code
- test : Test Code



---
### pyproject.toml
setup.py 스크립트를 사용하는 것은 Legacy한 방식으로 최근 추세는 toal 파일을 사용하려 한다.

```toal
#################################################################################
# 빌드 백엔드
#################################################################################
[build-system]
requires = ["setuptools>=40.8.0", "wheel"]          # 빌드를 위한 패키지 정의
build-backend = "setuptools.build_meta"             # 빌드를 수행할 Python 객체 이름


#################################################################################
# 프로젝트 메타데이터 정의
#################################################################################
[project]
name = "test-y2-pkg"                                # 패키지 이름
version = "1.2.5"                                   # 패키지 버전

description = "Package description"                 # 패키지 설명
readme = "README.md"                                # 설명 파일 (README 파일)
# readme = {file = "README.txt", content-type = "text/markdown"}

authors = [                                         # 패키지 작성자 (사람, 기업)
    {name = "Pradyun Gedam", email = "pradyun@example.com"},
    {name = "Tzu-Ping Chung", email = "tzu-ping@example.com"},
]
maintainers = [                                     ## 패키지 관리자
    {name = "Brett Cannon", email = "brett@example.com"}
]

requires-python = ">=3.7"                           # 파이썬 최소 요구 버전

license = {file = "LICENSE"}                        # LICENSE 파일
# keywords = ["one", "two"]                              # Pypi 에서 검색시 (인스타 태그 느낌)

classifiers = [                                     # Pypi 분류 (https://pypi.org/classifiers/)
    "Development Status :: 3 - Alpha",
    "Framework :: Django",
    "Programming Language :: Python :: 3",
    "Operating System :: POSIX :: Linux"
]

dynamic = ["version"]                               # 동적 메타데이터 목록 / 벡엔드에서 코드, Git tag 등의 값을 읽도록 할 수 있다. 백엔드에서 적용을 해야한다


#################################################################################
# 프로젝트 의존성 정의
#################################################################################
dependencies = [                                    # 의존성 정의 (pip 사용하는 의존성)
    "requests",
    'importlib-metadata; python_version<"3.8"',
    "httpx",
    "gidgethub[httpx]>4.0.0",                           
    "enum34; python_version<'3.4'",                     ## 특정 환경 만족할 때 적용 (ex> Python version < 3.4)
    "pywin32 >= 1.0; platform_system=='Windows'",       ## 특정 환경 만족할 때 적용 (ex> Windows)
    "django>2.1; os_name != 'nt'",
    "django>2.0; os_name == 'nt'"
]


[project.optional-dependencies]                     ## 패키지의 특정 기능에만 의존성이 필요한 경우 (사용법 : pip install your-project-name[gui] or 명령어 통해서 따로 추가)
pdf = ["ReportLab>=1.2", "RXP"]
rest = ["docutils>=0.3", "pack ==1.1, ==1.3"]
gui = ["PyQt5"]
cli = [
  "rich",
  "click",
]

#################################################################################
# 실행 가능 스크립트???
#################################################################################
[project.scripts]
my-script = "my_package.module:function"

[project.gui-scripts]
spam-gui = "spam:main_gui"


#################################################################################
# 프로젝트 관련 사이트 주소 (URL)
#################################################################################
[project.urls]
Homepage = "https://example.com"
Documentation = "https://readthedocs.org"
Repository = "https://github.com/me/spam.git"
Issues = "https://github.com/me/spam/issues"
Changelog = "https://github.com/me/spam/blob/master/CHANGELOG.md"


#################################################################################
# Plugin 추가
#################################################################################
[project.entry-points."spam.magical"]
tomatoes = "spam:main_tomatoes"



#################################################################################
# Setuptool 사용방법 (https://setuptools.pypa.io/en/latest/userguide/pyproject_config.html#dynamic-metadata)
#################################################################################
[tool.setuptools.dynamic]
version = {attr = "my_package.VERSION"}
readme = {file = ["README.rst", "USAGE.rst"]}

```
* [build-system] : 빌드 패키지 관련 정의
* [project] : 프로젝트 메타데이터 정의
* [project.optional-dependencies] : 특정 기능에서만 의존성이 필요한 경우
* [project.urls] : 프로젝트 관련 사이트 주소 (URL)
* [project.scripts] : 
* [project.gui-scripts] : 
* [project.entry-points."spam.magical"] : Plugin 추가


</br>
</br>

---
### Setup
기존 setup.cfg(정적 메타데이터를 가지고 있는 설정 파일)를 사용하였다. 하지만 setup.py를 통하여 우리는 동적으로 메타데이터를 빌드를 할 수 있게 된다. 작성 방법은 다음과 같다.

```py
import setuptools

with open("README.md", "r", encoding="utf-8") as fh:
    long_description = fh.read()

setuptools.setup(
    name = "package-name",
    version="0.1.1",
    author="Schooldevops",
    author_email="schooldevops@gmail.com",
    description="schooldevops sample lib",
    long_description=long_description,
    long_description_content_type="text/markdown",
    url="https://github.com/schooldevops/python-tutorials",
    project_urls={
        "Bug Tracker": "https://github.com/schooldevops/python-tutorials/issues",
    },
    classifiers=[
        "Programming Language :: Python :: 3",
        "License :: OSI Approved :: MIT License",
        "Operating System :: OS Independent",
    ],
    package_dir={"": "src"},
    packages=setuptools.find_packages(where="src"),
    python_requires=">=3.6",
)
```
* name: 배포할 패키지 이름
* version: 배포할 패키지 버전
* author: 작성자 (사람, 조직)
* author_email: 작성자 메일
* description: 설명
* long_description: 긴 설명 (보통 README.md 내용을 추가)
* long_description_content_type: 설명 파일 확장자 (text/markdown)
* url: 설명 가능한 페이지 URL 추가
* project_urls: PyPI에서 보여줄 추가적인 링크를 나타낸다. Github 등 Repository 깃헙과 같은 소스 리포지토리가 보통 들어간다.
* classifiers: 패키지를 위한 몇가지 추가적인 메타 데이터를 나열한다. 파이썬 패키지, 라이선스, 운영체제 등을 기술하였다.
* package_dir: 패키지 이름들과 디렉토리를 기술한다. 비어있는 값은 루트 패키지의 값으로 여기서는 src를 작성했다 src 하위에 패키지가 포함되어 있음을 확인하자.
* package: 모든 파이썬 import packages의 목록이다. 여기에는 배포되는 패키지를 포함한다.
* python_requires: 파이썬 버젼을 기술한다.


</br>
</br>




---
---
## 빌드 및 배포
## 빌드
### 빌드 모듈 패키지 및 사용 패키지 설치
먼저 빌드에 필요한 모듈을 설치한다.

```
$ python3 -m pip install --upgrade build

$ python3 -m pip install --upgrade <사용_패키지1>
$ python3 -m pip install --upgrade <사용_패키지2>
```
</br>

### 빌드 수행
빌드를 수행하면 wheel 파일이 생성하게 된다. 

```
$ python3 -m build
```
</br>

수행하고 나면 다음과 같은 파일이 생성된다.

![python_build](./img/python_build.png)
- python_proj-1.2.5.tar.gz : Source archive (소스 코드 파일)
- python_proj-1.2.5-py3-none-any.whl : bdist_wheel (빌드 배포 파일)

</br>

아카이브 파일 내부는 다음과 같이 구성되어 있다.

![python_build_source](./img/python_build_source.png)


### 빌드 업로드
빌드 업로드도 먼저 필요한 모듈을 설치해야 한다. twine은 PyPI에 패키지를 업로드할 수 있게 해준다.

```
$ python3 -m pip install --upgrade twine

$ python3 -m twine upload dist/*
```
</br>

### 빌드 패키지 설치
1. 폐쇄망이 아닌경우
    ```
    $ python3 -m pip install --upgrade <package_name>
    ```

2. 폐쇄망인 경우 (Airgabbed)
    ```
    $ python3 -m pip install --upgrade <whl_name>
    ```

</br>

### 배포
빌드한 패키지를 설치하면, 아래와 같이 리스트에 추가된 것을 확인할 수 있다.

![python_build_result](./img/python_build_result.png)




---


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
* Pipfile의 ```python_version```의 포맷은 ```x.y``` or ```x```이여야 하며, 
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
    $ pipenv --python 3.7 # Specific version
    
    # Remove virtualenv
    $ pipenv --rm
    ```

### Step 2. Pipenv 환경 관리
* pipenv 환경은 다음 명령어로 관리 된다.
* ```$ pipenv install [package names]```
    * pipenv 가상 환경에 패키지를 설치하고 Pipfile을 업데이트한다.
    * Pipfile
        - Pipfile : 프로젝트 의존성을 정의하는 파일. 패키지와 버전이 명시
        - Pipfile.lock : 버전 고정 및 의존성 그래프 포함함으로써, 동일하게 빌드를 재현할 수 있게 해주는 파일
        -
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


---
## Java



---
## C/C++





----
## Poetry 사용법
1. Poetry로 가상 환경 생성
    ```
    cd <proj_dir>
    poetry new <proj_name>
    ```
2. 가상환경 생성
    ```
    poetry config virtualenvs.in-project true
    ```
3. 의존성 설치
    1) 기존 pyproject.toml 사용하여 설치하는 경우
        ```
        poetry install
        ```
    2) 새로운 패키지를 설치하는 경우
        ```
        poetry add <Package>
        ```
4. 프로젝트 실행
    ```
    poetry run python main.py

    poetry run ptyhon path/to/other_script.py
    ```
5. 
6. 
7.  및 의존성 설치:
프로젝트 디렉토리로 이동한 후에 다음 명령을 사용하여 Poetry를 사용하여 가상 환경을 생성하고 의존성을 설치합니다.

bash
Copy code
poetry install
이 명령은 프로젝트의 가상 환경을 생성하고, pyproject.toml 파일에 명시된 의존성 패키지를 모두 설치합니다.

Poetry로 프로젝트 실행:
의존성이 설치된 후에는 다음 명령을 사용하여 프로젝트를 실행할 수 있습니다.

bash
Copy code
poetry run <명령>
예를 들어, 프로젝트의 메인 스크립트를 실행하려면 다음과 같이 실행합니다.

bash
Copy code
poetry run python main.py
또는 프로젝트에 포함된 다른 스크립트를 실행하려면 해당 스크립트의 경로를 지정합니다.

bash
Copy code
poetry run python path/to/other_script.py
만약 프로젝트가 패키지 형태로 설치되어 있다면, 해당 패키지를 사용하는 방법에 따라 다를 수 있습니다. 일반적으로는 import를 사용하여 모듈을 불러오거나 명령어를 실행합니다.

이러한 방법을 통해 Poetry를 사용하여 프로젝트를 실행할 수 있습니다. Poetry를 사용하면 프로젝트의 의존성을 효과적으로 관리하고 실행 환경을 일관되게 유지할 수 있습니다.