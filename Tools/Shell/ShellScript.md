# Shell Script 작성법

## Shell Script 기본
* https://twpower.github.io/131-simple-shell-script-syntax
1. Shell Script 실행 방법
    * Script 파일명 앞에 ```./```를 붙인다.
    ```
    $ ./test.sh
    ```

2. 주석
    * ```#``` 사용
    ```shell script
    # Comment
    ```

3. Shell 설정
    * ```#!``` 를 이용하여 설정한다.
    ```shell script
    #!/bin/bash
    ```

4. 변수 선언 및 사용
    * ```=```를 이용하여 변수 선언 및 대입할 수 있으며, 띄어쓰기가 있으면 안된다.
    * 변수명 앞에 ```${}```를 붙이면 변수로 사용가능하다.
    ```shell script
    var=test
    cat ${var}
   
    # 결과 : test
    ```

5. 명령문 실행
    * 기본적으로 Command를 쓰게 되면 실행된다.
    * 명령문 결과를 사용하기 위해서는 ```$()```를 사용하면 된다.
    ```shell script
    var=$(echo PYTHON)
    ```
   
6. for in 문
    * 반복문을 통해 순차적으로 가지고 올 수 있다.
    ```shell script
    # list.txt
    a
    b
    c
    
    # test.sh
    for val in 'cat list.txt'
    do
    echo ${val}
    done
    ```   
   
2. 주서
    * 
    ```shell script
    ```

2. 주서
    * 
    ```shell script
    ```


   
## 심화   