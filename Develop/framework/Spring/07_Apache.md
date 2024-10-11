# Apache Tomcat
* **Apache** : HTTP Server
* **Tomcat** : WAS Server (Web Application Server)

## Install Tomcat 
1) Move to https://tomcat.apache.org/download-90.cgi
2) Select Version and Download Tomcat
3) Copy war file to webapps directory
4) Start startup.sh or startup.bat in tomact/bin directory

## Apache Tomcat Setting
1. Download Apache Tomcat
    * http://tomcat.apache.org/ 접속
    * Version 선택
    * Download
        * Windows : zip 파일
        * Mac, Linux : tar.gz 파일
2. 압축 해제
3. Port 설정
    * conf directory -> server.xml 파일을 수정한다.
    * Connector의 port를 수정한다.
    ```
    $ vi server.xml
   
        <Connector port="7777" protocol="HTTP/1.1"
        connectionTimeout="20000"
        redirectPort="8443" />
    ```
4. Apache Tomcat 실행
    * bin directory -> startup.sh 실행
    * ```./startup.sh``` 명령어 실행
5. Apache Tomcat 종료
    * bin directory -> shutdown.sh 실행
    * ```./shutdown.sh``` 명령어 실행


