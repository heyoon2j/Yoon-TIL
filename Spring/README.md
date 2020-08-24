# Spring

## Maven
* 
* 
### Download Maven
1. Download Maven (https://maven.apache.org/download.cgi)
2. Set Path
    1. Windows
        * Name: **MAVEN_HOME** / Value: **{Maven Directory}**
        * Add Path: **%MAVEN_HOME%\bin;**
    2. Linux
        ```shell script
        vi /etc/profile
       expert MAVEN_HOME = {Maven Directory Path}
       expert Path = $PATH:$MAVEN_HOME
       ```
3. Check Maven
    * Command -> Input **mvn -version**

    
## logback
* JAVA에서 가장 많이 사용되었던 Logging Library인 log4j의 후속 버전.
* logback-classic은 SLF4J를 구현한다. 그렇기 때문에 SLF4J를 이용하여 logback Class를 사용할 수 있다.
### Download logback
1. Download logback (http://logback.qos.ch/)
    * logback-classic 
    * logback-core
2. Download slf4j (http://www.slf4j.org/) 
    * slf4j-api

### Example
    ```java
    package kr.co.fastcampus;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    
    public class Main {
        private static Logger logger = LoggerFactory.getLogger(Main.class);
    
        public static void main(String[] args){
            logger.info("Hello world!");
    
        }
    }
    ```
    * LoggerFactory.getLogger(Main.class) - Return: Logger Object, Parameter: {Class Name}.class / "{Package Path/ClassName}" 

### Logger Level
* trace -> debug -> info -> warn -> error 순으로 높다
* 설정 Level 이상의 로그 출력, info로 호출 시, trace, debug는 보여지지 않는다.
```java
  public void trace(String message);
  public void debug(String message);
  public void info(String message); 
  public void warn(String message); 
  public void error(String message); : 
```

### Logback Setting
* 설정파일 및 우선순위는 다음과 같다.
1. logback.groovy 
2. logback-test.xml
3. logback.xml


