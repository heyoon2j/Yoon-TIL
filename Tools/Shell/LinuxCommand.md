# Linux Command
* ```cat``` : 파일의 내용을 화면에 출력.
    
* ```grep``` : 파일에서 패턴이 존재하는 라인을 찾아준다.
    * ```grep [option] [pattern] [file_name]```
    * Option
        * -v : 해당 패턴을 포함하지 않는 행 출력
        * -i : 대소문자 구분 X
        * -n : 줄 번호 함께 출력
        * -I : 파일명 함께 출력
   ```shell script
   $ grep -i root /etc/passwd
   ```

* ```head``` : 파일의 시작 라인에서 지정한 라인까지 출력
    * 
    * Option
        *
    ```shell script
    $ head -10 /etc/passwd
    ```
    
* ```tail``` : 파일의 끝에서 지정한 라인까지 출력
    * 
    * Option
        *
    ```shell script
    
    ```

* ```cut``` : 
    * 
    * Option
        *
    ```shell script
    
    ```

* ```paste``` : 두개 이상의 파일을 읽어 각 파일의 내용을 각각의 필드 형태로 합쳐
새로운 파일을 생성
    * 
    * Option
        * -s : 
        * -d : 필드로 붙일 때, 구분자
    ```shell script
    $ paste -d : test1 test2
    ```

* ```sort``` : 텍스트로된 파일의 행단위 정렬
    * ```sort [option] <file_name>```
    * 기본적으로 각 행의 첫번째 단어(필드)의 첫번째 문자들을 기준으로 알파벳 오름차순 정렬.
    * Option
        * -r : 내림차순
        * -k <number> : 지정한 number 필드를 기준으로 정렬
        * -u : 중복된 내용을 하나로 취급하여 정렬
        * -l : 용량크기 순으로 오름차순 정렬
    ```shell script
    $ sort -r -k 5 /etc/passwd
    ```

 * ```sed``` : 필터링과 텍스트를 변환하는 스트림 편집기
     * 원본 변화없이 출력 결과를 변화시킨다.
     * ```>>``` 등 리다이렉션을 통해 따로 저장할 수 있다.
     * ```sed 's/찾을 텍스트/바꿀 텍스트/' filen_name```
     * https://jhnyang.tistory.com/287, https://m.blog.naver.com/PostView.nhn?blogId=coldlion1&logNo=100156549623&proxyReferer=https:%2F%2Fwww.google.com%2F
     * Option
         * -n : 보통 p와 함께 쓰이며, pattern space의 출력을 제한한다.
         * -e : 다중 편집 실행
         * -i[SUFFIX] : 변환 후, 확장자 변경하여 저장
     ```shell script
     # 특정 범위 출력  
     $ sed -n '1,3p' test    # 1~3번줄까지 출력
     $ sed -n '5,$p' test    # 5번줄~끝까지 출력
     $ sed -n -e '1,3p' '5,$p' test
   
     # 특정 패턴 출력
     $ sed -n '/abc/p' test
     $ sed -n '/^123/p' test  # 107로 시작하는 패턴 출력
   
     # 파일에서 삭제
     $ sed -i '/^$/d' test    # 빈 줄 제거 후 저장
     $ sed '/^#\|^$\| *#/d' test   # "#" or 빈 줄 or 스페이스 제거
 
     # 치환 (기본적으로 한 행만 치환)
     $ sed 's/[Zz]ip/txt/g' test   # zip or Zip을 txt로 치환
     $ sed -i 's/\r/' test         # hidden new line 제거
     $ sed 's/abc/efg/g' test > new_test   # abc를 efg로 전체 행 치환
     $ sed -i 's/abc/efg/gi' test   # abc 대소문자 상관없이 찾아서 efg로 치환
     ```

* ```expr```
    * https://storycompiler.tistory.com/111
* awk
* wc
* uniq





* ```nohup``` : 로그아웃된 상태에서도 유지하도록 한다.
    * ```nohup```에 대한 log는 명령을 실행한 디렉토리에 "nohup.out" 파일을 생성하여 저장한다.
    ```
    $ nohub ls -al &
    ```

* ```date``` : 날짜 및 시간 정보
    * 
    * https://m.blog.naver.com/PostView.nhn?blogId=coldlion1&logNo=100156549623&proxyReferer=https:%2F%2Fwww.google.com%2F
    ```shell script
    #!/bin/sh
    cur_date=$(date +%Y/%m/%d)
    yesterday_date_eng="`date +%Y/%m/``expr \`date +%e\` \- 1`"
    yesterday_date_nor="`date +%Y/%m/``expr \`date +%e\` \- 1`"
    yesterday_date_ago="`date --date=\"1 days ago\"`"
    echo ${cur_date}
    ```

* ```expr``` : 


