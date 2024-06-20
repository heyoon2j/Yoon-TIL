# Shell Script 작성하기
* Linux에서 Shell(Bash 등)을 이용하여 Script 작성하던 것을 Python을 이용하여 작성한다.
* py 파일을 실행하는 방법: ```$python <py_file> [argv]```

## 인자 값 받기
* 기본적으로 py 파일은 인자 값 1개를 가진다.
    ```python
    import sys

    def checkArgvNum():
        print("인자 값 수 %d" % (len(sys.argv)))

    checkArgvNum()
    ```
    * 인자 값이 하나로 나오는 이유는 py 파일이 인자로 인식하기 때문이다.

* Example
    ```python
    import sys

    def checkArgvNum():
        num = len(sys.argv)

        if num < 2 :
            print("인자 값이 없습니다")
        else :
            for i in range(1, num) :
                print("{}번째 인자 값 {}".format(i,sys.argv[i]))

    checkArgvNum()    
    ```
    * 실행방법 예시: ```python3 test.py 123```


## Directory 생성
* Function: ```os.mkdir(path)``` or ```os.makedirs```
* ```mkdir()``` Example
    ```python
    import os

    path = './test/temp'
    os.mkdir(path)
    ```
    * 지정한 path가 존재하지 않는 경우, "FileNotFoundError" error가 발생한다.


* ```makedirs()``` Example
    ```python
    import os

    def makeDir(absPath):
        path_list = ['/inventory', '/playbooks', '/roles', 'hosts']

        for i in range(len(path_list)) :
            os.makedirs(absPath + path, exist_ok=True)
    ```
    * Directory를 재귀적으로 생성하고 싶을 때 사용.
    * 중간에 지정한 Directory가 없는 경우, 같이 생성한다.
    * 단, 마지막에 지정한 Directory가 만들어져 있는 경우에는 에러를 발생한다. 존재하는 경우에 에러를 발생시키지 않으려면 ```exist_ok``` 옵션을 ```True```로 지정하면 된다.





