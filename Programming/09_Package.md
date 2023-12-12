# Package / Library
* 특정 기능들을 구현해 놓은 코드들의 모음 (코드를 저장해둔 파일)
</br>
</br>


---
## Module 
* 여러 기능 들이 뭉쳐져있는 하나의 .py 파일
* 모듈은 파이썬 클래스, 함수나 스크립트 등을 담고 있는 파일
</br>


### Module 검색 경로
* 인터프리터는 먼저 해당 모듈의 이름을 가지는 내장 모듈을 찾는다. 발견되지 않으면, 변수 sys.path 로 주어지는 디렉터리들에서 
모듈 파일을 찾는다.
* 변수 sys.path는 아래 위치들로 초기화된다.
    * 입력 스크립트를 포함하는 디렉터리
    * PYTHONPATH
    * 설치 의존적인 기본값
</br>
</br>


---
## import
* Ref : https://note.nkmk.me/en/python-import-usage/
* Ref : https://docs.python.org/3/tutorial/modules.html#packages
</br>

### import module
```python
# import <module>
import os
print(os.listdir())

# from <module> import <var, func, class>
# !!! 조심해야할 점은 이름이 겹치면 문제가 발생한다 !!!
from os import *
print(listdir())

from os import listdir
print(listdir())

# import <module> as <alias>
import os as o
print(o.listdir())


# from <module> import <var, func, class> as <alias>
from os import listdir as o
print(o())

```
* import를 기준으로 이름을 추가하냐 안하냐 이다!
* __all__이 모듈에 정의되어 있다면, *는 __all__에 정의된 값만 import한다
</br>
</br>


### import from package
```python
"""
# File structure

test.py
urllib/
├── __init__.py
├── error.py
├── parse.py
├── request.py
├── response.py
└── robotparser.py
"""

# import <Package> 
## Error!!!!! 이렇게는 그냥 사용할 수 없다. __init__ 파일이 필요하다!!
import urllib 
print(urlib.error)  # no attribute 'error'


# import <pacakge>.<module>
import urllib.error
print(urllib.error)
print(urllib.error.HTTPError)


# from <package> import <module>
from urllib import error
print(error)
print(error.HTTPError)


# import <package> : __init__ 파일을 import 한다. 원하는 내용을 넣을 수 있다. import도 가능하다

# urllib/__init__.py
#from . import error    // 해당 내용으로는 error를 선언해야지만 사용 가능하다
from .error import *

# test.py
import urllib
print(urllib.error.HTTPError)
print(urllib.HTTPError)


# from <package> import * 
# from <module> import *
# __all__을 명시해주지 않으면 안하면 전체 해당하지만, 명시하여 제한을 걸 수 있다.
__all__ = ['error']
```
* 동일 경로에 있는 경우
    * ```.``` 이용
    ```python
    from . import myModule
    ```
* 하위 경로에 있는 경우
    * 상대 경로를 이용
* 상위 경로 or 다른 경로 있는 경우
    * 상위 경로를 사용하기 위해서는 절대경로 path에 상위 경로에 대한 path를 추가해줘야 한다.
    ```python
    import sys
    sys.path.append(os.path.dirname(os.path.abspath(os.path.dirname(__file__))))
    ```
</br>
</br>




---
## Package (패키지)
* 패키지는 점으로 구분된 모듈 이름을 써서 파이썬의 모듈 이름 공간을 구조화하는 방법
* 예로 모듈 이름 A.B 는 A 라는 이름의 패키지에 있는 B 라는 이름의 서브 모듈을 가리킨다.
    * 패키지 계층 구조
        * 파이썬이 디렉터리를 패키지로 취급하게 만들기 위해서는 ```__init__.py``` 파일이 필요하다.
        * 가장 간단한 경우 ```__init__.py``` 는 빈 파일일 수 있지만, 패키지의 초기화 코드를 실행하거나 ```__all__```, ```__version__``` 등의 변수를 설정한다.
        * ```__all__ = ["wavread, aiffread"]```에는 ```import *```시 어떤 모듈만을 임포트할수록 만들지 정의한다!
        ```
        sound/                          Top-level package
              __init__.py               Initialize the sound package
              formats/                  Subpackage for file format conversions
                      __init__.py
                      wavread.py
                      wavwrite.py
                      aiffread.py
                      aiffwrite.py
                      auread.py
                      auwrite.py
                      ...
              effects/                  Subpackage for sound effects
                      __init__.py
                      echo.py
                      surround.py
                      reverse.py
                      ...
              filters/                  Subpackage for filters
                      __init__.py
                      equalizer.py
                      vocoder.py
                      karaoke.py
                      ...
        ```
      
    * 패키지 import
        ```
        import sound.effects.echo
      
        from sound.effects import echo
      
        from sound.effects.echo import echofilter
        ```

### 절대 경로
* 위에서 설명된 것은 패키지를 기준으로 절대 경로를 사용한다.
* 모든 경로를 입력한다.


### 상대 경로를 이용한 패키지 참조
* import는 다음과 같이 상대 경로를 이용할 수 있다.
    * .은 현재 서브 패키지
    * ..은 상위 서브 패키지를 가리킨다.
    ```
    from . import echo
    from .. import formats
    from ..filters import equalizer
    ```

> 주의 필요!! Relative import를 포함한 python 모듈은 스크립트로 직접 실행할 수 없다. 안티 패턴이라고 한다! 다른 말로 표현하면, main되는 모듈에서는 상대 경로를 사용할 수 없다 (https://daco2020.tistory.com/62, https://velog.io/@anjaekk/python%EC%A0%88%EB%8C%80%EA%B2%BD%EB%A1%9C%EC%83%81%EB%8C%80%EA%B2%BD%EB%A1%9C-%EC%83%81%EB%8C%80%EA%B2%BD%EB%A1%9C-import-%EC%97%90%EB%9F%AC%EC%9D%B4%EC%9C%A0%EC%99%80-%ED%95%B4%EA%B2%B0)

> 그렇기 때문에 패키지안에서만 상대 경로(., ..)를 사용하고, 아닌 경우 절대 경로 사용

</br>
</br>




---
## Packaging (패키징)

* Reference
    - https://ryanking13.github.io/2021/07/11/python-packaging.html
    - 



## 컴파일된 파이썬 파일
* 모듈 로딩을 빠르게 하기위해 사용된다.
* ```__pycache__``` 디렉터리에 각 모듈의 컴파일된 버전을 module.version.pyc 라는 이름으로 캐싱한다. 
version은 일반적으로 파이썬의 버전 번호를 포함한다.
* Example
    * CPython 배포 3.3 에서 spam.py
    ```
    __pycache__/spam.cpython-33.pyc
    ```


## dir() 함수
* 내장 함수 dir()은 모듈이 정의하는 이름들을 찾는데 사용된다. 인자가 없으면, 현재 정의된 변수, 모듈 함수 등등의 이름들을 나열한다.
* 문자열들의 정렬된 리스트를 돌려준다.
    ```
    import fibo
    dir(fibo)       # ['__name__', 'fib', 'fib2']
    ```




## 특수 메서드
* 파이썬에서의 특수 메서드는 항상 앞에 ```__```이 붙는다.
* __name__ : 

* __str__
* ____ :
* ____ :
* ____ :
* ____ :
* ____ :
* ____ :
* ____ :
* 
> https://wikidocs.net/84418