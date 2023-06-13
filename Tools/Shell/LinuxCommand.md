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

 ## ```sed``` : 필터링과 텍스트를 변환하는 스트림 편집기
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
</br>
</br>


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

## ```date``` : 날짜 및 시간 정보
* 날짜 및 시간 정보를 출력한다.
* https://m.blog.naver.com/PostView.nhn?blogId=coldlion1&logNo=100156549623&proxyReferer=https:%2F%2Fwww.google.com%2F
* Option
    | 옵션 | 설명 |
    |------|------|
    | -s [string] '2017-01-01 00:00:00' | 시간을 설정할 때 사용 |
    | -u | UTC로 출력 |
    | -d [string] | string 인자 값에 해당하는 날짜를 출력 |
    | -r [file] | File 또는 Directory를 참조하여 수정 일시 출력 |

    * Example
        ```shell script
        $ date -s '2017-01-01 00:00:00'     # Sun Jan  1 00:00:00 KST 2017

        $ date -u       # Sun Jan  1 00:00:00 UTC 2017

        $ date -d '-1 second'       # 1초 전
        $ date -d '+1 minute'       # 1분 후
        $ date -d 'tomorrow'        # 내일
        ```

* Format
    | 포맷 | 설명 |
    |------|------|
    | %% | % 기호 출력 |
    | %a / %A | 요일 출력, Sun / Sunday |
    | %b / %B | 월 출력, Jan / January |
    | %y / %Y | 연도 출력21 / 2021 |
    | %m | 월 출력, 12 |
    | %d | 날짜 출력, 01 |   
    | %H | 시간 출력, 23 |     
    | %M | 분 출력, 59 |
    | %s / %S | 초 출력, 첫 UTC 시간으로부터 경과된 초 / 02 |
    | %D | '%m/%d/%y' 형태 출력, 12/01/21 |
    | %R | '%H:%M' 형태 출력, 23:59 |    
    | %T | '%H:%M:%S' 형태 출력, 23:59:02 |
    | %z | 타임존 값 출력 |
    
    * Example
        ```
        $ date -u '+%Y-%m-%d %T'
        $ date -u '+%Y-%m-%dT%R:%s'

        # Script
        #!/bin/sh
        cur_date=$(date +%Y/%m/%d)
        yesterday_date_eng="`date +%Y/%m/``expr \`date +%e\` \- 1`"
        yesterday_date_nor="`date +%Y/%m/``expr \`date +%e\` \- 1`"
        yesterday_date_ago="`date --date=\"1 days ago\"`"
        echo ${cur_date}
        ```
</br>
</br>


* ```expr``` : 


* ```tree``` : 특정 위치에 존재하는 Directory 및 File을 Tree 형식으로 출력한다.
    ```shell script
    # option -L : Level
    $ tree -L 2         # Depth 2 까지만 표시
    ```
</br>
</br>


## awk
* 파일로 부터 레코드를 선택하고, 선택된 레코드에 포함된 값을 조작하거나 데이터화하는 것을 목적으로 사용하는 프로그램.
* 파싱하는 프로그램으로 생각하면 될거 같다.
* https://recipes4dev.tistory.com/171
```s
# $ awk [OPTION...] 'pattern { action }' [ARGUMENT...]
$ awk '{print}' ./file.txt          # 모든 레코드 출력
$ awk '/p/' ./file.txt              # p를 포함하는 레코드 출력
$ awk 'length($0) > 10 { print $3, $4, $5} ' ./file.txt     # 레코드 길이가 10 초과인 경우, 레코드의 3,4,5 필드 출력



```


* $0 : 레코드(row)
* $n : 필드 (column)
* 



</br>
</br>


## curl
* 다양한 통신 프로토콜을 이용하여 데이터를 전송하기 위한 명령어
* Example
    ```
    curl --proxy 10.20.200.10:5000 \
        --proxy-header "Proxy-Connection:" \
        --request POST \
        --header "Content-Type:application/json" \
        --data "{\"title\":\"aaaaa\",\"text\":\"TTT\"}" \
        --key ~/tls-ca.pem \
        --output curl_result.txt \
        --verbose \
            https://test.com:4321
    ```

* Option
    | Option | Argument | Content |
    |--------|----------|---------|
    | -x, --proxy | [protocol://]host[:port] | Use this proxy |
    | -X, --request | [command] | Specify request command to use |
    | -H, --header | [name: value] | Pass custom header(s) to server |
    | -d, --data | [data] | HTTP POST data |
    | --key | [key] | Private key file name |
    | --key-type | [type] | Private key file type (DER/PEM/ENG) |
    | -o, --output | [file] | Write to file instead of stdout |
    | -v, --verbose |  | Make the operation more talkative |


* https://help.claris.com/ko/pro-help/content/curl-options.html#:~:text=%EC%A7%80%EC%9B%90%EB%90%98%EB%8A%94%20cURL%20%EC%98%B5%EC%85%98%20cURL%20%28Client%20for%20URLs%29%EC%9D%80%20URL%EC%97%90%EC%84%9C,%EC%98%B5%EC%85%98%20%EC%A7%80%EC%A0%95%20%EC%97%90%20%EB%8C%80%ED%95%9C%20%ED%85%8D%EC%8A%A4%ED%8A%B8%20%ED%91%9C%ED%98%84%EC%8B%9D%EC%9D%80%20%EB%8B%A4%EC%9D%8C%EA%B3%BC%20%EA%B0%99%EC%8A%B5%EB%8B%88%EB%8B%A4.?msclkid=68ffc045baec11ec85eb3ddb6f27226b



