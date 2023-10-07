# Shell Script 작성법

## Shell Script 기본
```sh
#!/bin/bash

# 변수 선언
userSvcName="amazon-ssm-agent.service"
userSearch="Active"

isExist=`systemctl status ${userSvcName} 2> /dev/null | grep "${userSearch}"`
active_state=$(echo "${status}" | grep 'ActiveState=' | awk -F= '{ print $2 }')


# 조건문 - if [];then elif [];then else fi
if [ "${isExist}" == "" ];then
    echo "false"
elif [ "${isExist}" == "test" ];then
    echo "test"
else
    echo "true"
fi


# 반복문

``` 

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
    ```shell script
    var1="test"
    var2="test"
    echo $var1      # test
    echo ${var2}    # test

    var1=`echo "Python"`    # Python
    var2=$(echo "PYTHON")   # Python
    ```
    * ```=```를 이용하여 변수 선언 및 대입할 수 있으며, 띄어쓰기가 있으면 안된다.
    * 변수 사용 방법:
        1) ```$``` 를 붙인다.
        2) ```${}``` 를 붙인다.
    * 명령어를 변수로 취급하는 방법:
        1) \`\` 를 붙인다. 
        2) $() 를 붙인다.
    > 보통 괄호 있는 것을 더 많이 사용한다. 이유는 중첩이 가능하기 때문이다.
   
5. for in 문
    * 반복문을 통해 순차적으로 가지고 올 수 있다.
    ```sh
    range=$(seq 1 9)

    # for i in {1..9}  / for ((i=1; i<10; i++))
    for i in $range 
    do
        echo $i
    done
    # 0\n1\n2\n3\n4\n5\n6\n7\n8\n9

    fruit="apple banana orange"
    for i in $fruit
    do
        echo $i
    done
    # apple\nbanana\norange
    ```
    ```shell script
    # list.txt
    a
    b
    c
    
    # test.sh
    for val in $(cat list.txt)
    do
        echo ${val}
    done
    ```
5. Break, Continue
    ```

    ```    
6. 조건문
    * ```if [];then elif [];then else fi``` 사용
7. 큰따옴표 vs 작따옴표
    * "" : $ 등의 특수문자 사용 가능
    * '' : $ 등의 특수문자 사용 불가능하며, 내용 모두를 문자열로 취급
    > 주의해야 한다!!!


   
## 심화
### 산술 연산
* expr / $[] / $(())
    ```sh
    a=100
    b=200

    # expr 사용
    c=$(expr $a + $b)

    c=$[$a + $b]

    c=$(($a + $b))
    ```



### 특수 변수
* 위치 매개변수 (기준 : ```$ /usr/local/bin/command.sh -i input.txt```)
    - $0 : "/usr/local/bin/command.sh"
    - $1 : "-i"
    - $2 : "input.txt"
    - 앞에 $ 를 붙여 사용하며, $0 ~ $9, 10부터는 ${10} 형태로 사용한다. $10은 $1로 먼저 읽어지기 때문이다.
* 환경 변수
    - $PS1 : 
    - $PS2 : 
    - $PATH : 디렉토리 경로가 설정되어 있는 환경 변수
    - $TZ : Timezone 환경 변수
    - $PWD : 현재 디렉토리 경로
    - $IFS : 구분자 기호로 사용되는 값 (ex: tab, space 등)





### Ref
* https://shlee1990.tistory.com/917