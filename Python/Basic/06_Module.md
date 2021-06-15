# 6. Module
* 모듈은 파이썬 함수나 스크립트 등을 담고 있는 파일
* 파일의 이름은 모듈 이름에 확장자 .py 를 붙인다.

## 6.1. import
* 모듈을 다른 모듈에서 사용하기 위해서는 import를 해야된다.
* 패키지, 모듈, 함수, 변수, 클래스 등을 import할 수 있다.
    * 예제 fibo.py
    ```
    # Fibonacci numbers module
    
    def fib(n):    # write Fibonacci series up to n
        a, b = 0, 1
        while a < n:
            print(a, end=' ')
            a, b = b, a+b
        print()
    
    def fib2(n):   # return Fibonacci series up to n
        result = []
        a, b = 0, 1
        while a < n:
            result.append(a)
            a, b = b, a+b
        return result
    ```

    * import 예제
        * 모듈이름을 사용해서 함수들을 액세스할 수 있다.
        * moduleName.itemName
    ```
    import fibo
    fibo.fib(1000)
  
    # 함수를 자주 사용할 경우 지역 이름으로 대입할 수 있다.
    fib = fibo.fib
    fib(1000)
    ```

* ```from import```를 이용해 모듈에 있는 이름을 직접 임포트할 수 있다.
    ```
    from fibo import fib, fib2
    fib(1000)
  
    ####
    
    from fibo import *
    fib(1000)
    ``` 

* 동일 경로에 있는 경우
    * ```.``` 이용
    ```python
    from . import myModule
    ```

* 하위 경로에 있는 경우
    * 상대 경로를 이용
    

* 상위 경로 or 다른 경로 있는 경우
    * 상대 경로를 사용할기 위해서는 절대경로 path에 상위 경로에 대한 path를 추가해줘야 한다.
    ```python
    import sys
    sys.path.append(os.path.dirname(os.path.abspath(os.path.dirname(__file__))))
    ```


## 6.2. Script 실행
* import가 된다고 실행이 되는 것이 아니다.
* ```if __name__ == "__main__"```을 이용하여, 실행 코드가 main 파일일때만 실행하도록 만들 수 있다.
    ```
    # 모듈 실행
    python fibo.py <arguments>
    
    # fibo.py
    if __name__ == "__main__":
        import sys
        fib(int(sys.argv[1]))
    ```


## 6.3. 모듈 검색 경로
* 인터프리터는 먼저 해당 모듈의 이름을 가지는 내장 모듈을 찾는다. 발견되지 않으면, 변수 sys.path 로 주어지는 디렉터리들에서 
모듈 파일을 찾는다.
* 변수 sys.path는 아래 위치들로 초기화된다.
    * 입력 스크립트를 포함하는 디렉터리
    * PYTHONPATH
    * 설치 의존적인 기본값


## 6.4. 컴파일된 파이썬 파일
* 모듈 로딩을 빠르게 하기위해 사용된다.
* ```__pycache__``` 디렉터리에 각 모듈의 컴파일된 버전을 module.version.pyc 라는 이름으로 캐싱한다. 
version은 일반적으로 파이썬의 버전 번호를 포함한다.
* Example
    * CPython 배포 3.3 에서 spam.py
    ```
    __pycache__/spam.cpython-33.pyc
    ```


## 6.5. dir() 함수
* 내장 함수 dir()은 모듈이 정의하는 이름들을 찾는데 사용된다. 인자가 없으면, 현재 정의된 변수, 모듈 함수 등등의 이름들을 나열한다.
* 문자열들의 정렬된 리스트를 돌려준다.
    ```
    import fibo
    dir(fibo)       # ['__name__', 'fib', 'fib2']
    ```


## 6.6. 패키지
* 패키지는 점으로 구분된 모듈 이름을 써서 파이썬의 모듈 이름 공간을 구조화하는 방법
* 예로 모듈 이름 A.B 는 A 라는 이름의 패키지에 있는 B 라는 이름의 서브 모듈을 가리킨다.
    * 패키지 계층 구조
        * 파이썬이 디렉터리를 패키지로 취급하게 만들기 위해서는 ```__init__.py``` 파일이 필요하다.
        * 가장 간단한 경우 ```__init__.py``` 는 빈 파일일 수 있지만, 패키지의 초기화 코드를 실행하거나 ```__all__``` 변수를 설정한다.
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

## 6.7. 상대 경로를 이용한 패키지 참조
* 위에서 설명된 것은 패키지를 기준으로 절대 경로를 사용한다.
* import는 다음과 같이 상대 경로를 이용할 수 있다.
    * .은 현재 서브 패키지
    * ..은 상위 서브 패키지를 가리킨다.
    ```
    from . import echo
    from .. import formats
    from ..filters import equalizer
    ```

