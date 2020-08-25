# Spring
* Web Framework for JAVA 
* We learn
    * Logback
    * Maven
    * Servlet
    
* 기본 사전 지식
    * java File Compile 과정
        ```shell script
        $ javac -cp lib\logback-classic.jar;lib\logback-core.jar;lib\slf4j-api.jar kr\co\fascamfus\Main.java
        $ java -cp lib\logback-classic.jar;lib\logback-core.jar;lib\slf4j-api.jar;. kr.co.fascamfus.Main
  
        $ jar -c -m manifest.txt -f fascamfus.jar kr\* logback.xml
        $ java -jar fascamfus.jar // 실행 가능한 jar File
        ```
        * javac - cp {Class File Path} {JAVA File} : JAVA File을 Class File로 변환
        * java -cp {Class File Path} {Class FIle} : Class File 실행
        * java -jar {JAR File} : JAR File을 이용하여 실행


    