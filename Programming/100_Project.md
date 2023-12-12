# Project

```sh
## Directory Structure

$ tree .

python_proj
├── LICENSE
├── README.md
├── pyproject.toml
├── setup.py
├── src
│   └── y2proj
│       ├── __init__.py
│       └── utils.py
└── test
```
- LICENSE : Project License 정보
- README.md : README 파일
- pyproject.toml : 패키지 의존성 명세서
- setup.py : 빌드 정보 기술서
- src : Source Code
- test : Test Code

</br>
</br>


---
## project.toaml
```toml
[build-system]
requires = ["setuptools", "setuptools-scm", "wheel"]
build-backend = "setuptools.build_meta"


[project]
name = "test-y2-pkg"
description = "Package description"
version = "1.2.5"

authors = [
    {name = "Josiah Carberry", email = "josiah_carberry@brown.edu"},
]
readme = "README.rst"
requires-python = ">=3.7"
keywords = ["one", "two"]
license = {text = "BSD-3-Clause"}

classifiers = [
    "Framework :: Django",
    "Programming Language :: Python :: 3",
]

dependencies = [
    "requests",
    'importlib-metadata; python_version<"3.8"',
]
dynamic = ["version"]

[project.optional-dependencies]
pdf = ["ReportLab>=1.2", "RXP"]
rest = ["docutils>=0.3", "pack ==1.1, ==1.3"]

[project.scripts]
my-script = "my_package.module:function"

# ... other project metadata fields as listed in:
#     https://packaging.python.org/en/latest/guides/writing-pyproject-toml/

```
* build-system.requires : 빌드를 위한 패키지 정의
* build-system.build-backend : 빌드를 수행할 Python 객체 이름


---
## Setup
기존 setup.cfg를 이용한 빌드는 정적 메타데이터이다. 하지만 setup.py를 통한다면 우리는 동적 빌드를 할 수 있게 된다. 작성 방법은 다음과 같다.


```py
import setuptools

with open("README.md", "r", encoding="utf-8") as fh:
    long_description = fh.read()

setuptools.setup(
    name="example-pkg-scdev-pd-columns",
    version="0.0.1",
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
* name: 배포할 패키지의 이름을 작성한다. 문자열, '_', '-' 이 올 수 있다.
* version: 배포할 패키지의 버젼을 기술한다.
* author: 배포를 수행한 사람, 조직을 기술한다.
* author_email: 배포자의 메일주소
* description: 단순한 설명을 기술한다.
* long_description: README.md 에서 읽은 내용을 추가한다.
* long_description_content_type: 설명 파일의 컨텐츠를 작성한다. 여기서는 text/markdown 으로 마크다운임을 나타낸다.
url: 프로젝트 홈페이지 url을 기술한다.
project_urls: PyPI에서 보여줄 추가적인 링크를 나타낸다. 깃헙과 같은 소스 리포지토리가 보통 들어간다.
classifiers: 패키지를 위한 몇가지 추가적인 메타 데이터를 나열한다. 파이썬 패키지, 라이선스, 운영체제 등을 기술하였다.
package_dir: 패키지 이름들과 디렉토리를 기술한다. 비어있는 값은 루트 패키지의 값으로 여기서는 src를 작성했다 src 하위에 패키지가 포함되어 있음을 확인하자.
package: 모든 파이썬 import packages의 목록이다. 여기에는 배포되는 패키지를 포함한다.
python_requires: 파이썬 버젼을 기술한다.








