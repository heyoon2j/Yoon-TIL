# Linux Special Character
* ```#``` : comment
    ```
    # This line is a comment
    ```

* ```;``` or ```||``` : 앞의 명령어가 실패해도 다음 명령어가 실행
    ```
    $ yum install -y qwer;ls -al
    ```

* ```&&``` : 앞의 명령어가 성공한 경우에만 다음 명령어 실행
    ```
    $ yum install -y mysql && systemctl restart mysql
    ```

* ```&``` : 백그라운드에서 작업한다.
    * ```jobs``` : 백그라운드 작업들의 목록 확인
    ```
    $ ls -al &
    ```

* ```|``` : 파이프, 2개의 프로세스를 연결해준다. 앞의 명령의 결과를 뒤쪽의 명령에게 입력으로 전달한다.
    * ```&&``` 는 명령이 따로 따로 실행되는 것이다.
    ```
    $ cat test.txt | grep qwer
    ```

* ```{}``` : 명령을 그룹핑한다.
    ```
    $ cat test && {mkdir groupdir || touch test.txt } || echo $Path
    ```

* ```>```, ```>&``` : Redirection
    * ```a > b``` : a의 출력을 b 파일로 redirection
    * ```a >& b``` : 일반적으로 표준 입력, 출력, 에러를 위해 사용된다. 이유는 2 > 1을  하게 되면
    표준 에러를 1이라는 파일로 보내겠다는 의미가 되기 때문이다. 
    ``````

* ```$``` : 변수에 접근
    ```
    $ echo $Path
    ```
  
#### Reference
* https://tldp.org/LDP/abs/html/special-chars.html