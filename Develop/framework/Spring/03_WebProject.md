# Web Project
* 여기서는 IntelliJ Ultimate를 기준으로 쓰여있다.

## Create Project
1. Click File -> New -> Project
2. Select Java SDK, Build Tool(Maven, Gradle), Test Runner(JUnit, TestNG), 
Language(Java, Kotlin, Groovy)
3. Select Web Profile under Specifications
4. Enter a name for project

## Add Server
1. File -> Settings -> Build, Execution, Deployment -> Application Servers Click
2. **+** Button 클릭 후, Server Configuration 설정
    * Home: Server File이 저장되어 있는 위치

## Add Library
1. Maven 이용
    * 필요한 Library의 Maven을 검색
    * **pom.xml**에 붙여넣는다.
2. Gradle 이용
    * 필요한 Library의 Gradle을 검색 
    * **build.gradle**에 붙여넣는다.
3. 직접 Download
    1) 공식 사이트(https://projectlombok.org/)에서 파일 다운로드
    2) File -> Project Structure -> Modules
    3) 원하는 Module 선택 -> Dependencies -> **+** Button Click
    4) Library -> Library Type 선택 후, 원하는 Library를 선택하고 적용을 시키면 완료된다.

## Artifact
* Artifact란 테스트, 배포등을 하기 위해 함께 모아둔 프로젝트 자산의 어셈블리다.
    * 쉽게 표현하자면 프로젝트의 테스트, 배포 등을 위해 필요한 것이라고 생각하면 될거 같다.
    * 테스트하기 위한 java class, 배포하기 위한 Jar File 등이 될 수 있다.
* Artifact 다음 구성요소를 포함하는 Archive File 또는 Directory 구조일 수 있다.
    * 하나 이상의 모듈에 대한 Compile 결과물
    * 모듈 종속성에 포함된 Library
    * Resource 모음(Web pages, Images, Descriptor files, etc.)
    * 다른 artifacts
    * Individual 파일, Directory, Archive

### Artifact Configure 방법
1. File -> Project Structure -> Artifacts
    * Project 클릭 후, F4키를 눌러도 된다.
2. Project Settings -> Artifacts 클릭
3. **+** Button Click -> JAR, Web Application 등 원하는 Artifact 선택
4. Artifact 구성 설정
    * Name: Artifact 이름
    * Output directory: Artifact가 저장될 위치
        * 기본적으로 Artifact는 Build 될 때, out/artifacts/<artifact_dir> Directory에 배치된다.
            
### Artifact Build
* Artifact Build 하는 방법
    * Build -> Artifacts 클릭 후, 원하는 Artifact를 선택

* Build Option
    * Build: 처음에는 전체 Artifact를 빌드하고, 그 이후에는 변경된 부분에 대해서 Build를 진행하게 된다.
    * Rebuild: 처음처럼 전체 Artifact를 Build 한다 (Clean 후 Build와 같은 동작이다)
    * Clean: Artifact Output Directory의 모든 콘텐츠를 삭제한다.
    * Edit: Artifact Configuration 설정

### Artifact Deploy
* 웹 서버에 배포하기 위한 패키지 유형
    1. package(archive)
        * Archive(.war, .ear) 파일로 배포
        * Archive 파일은 WAS에 의해 압축이 풀린다.
        * 파일이 많은 경우 압축을 푸는 시간이 오래 걸릴 수 있다.
        * 원격 서버에 배포시 한 개의 파일만 전송하면 된다. 
    2. exploded(expanded)
        * Archive 파일을 압축이 풀린 형태로(Directory) 배포
        * 별도의 Directory에 원본 소스를 복사하여 만든다.
        * 압축 및 해제하는 과정이 필요하지 않다.
        * 파일이 많은 경우 복사하는 시간이 오래 걸릴 수 있다.
        * 우너격 서버에 배포시 파일이 많은 경우 전송 시간이 오래 걸릴 수 있다.
    3. in-place
        * Source Directory를 그대로 배포
        * Local Sever에 배포하는gh 경우 적합
        * Source Directory를 그대로 배포하기 때문에, WAS가 런타임시 생성하는 파일이 Source와 섞일 수 있는 문제가 있다.

* Run/Debug 시 Server(or Cloud)에 Deploy 하는 방법
    1. Run -> Edit Configurations 클릭
    2. **+** Button Click -> 원하는 Server(or Cloud) 선택
    3. Server menu
        * Name: 이름 설정
        * Application Server: Server 파일 위치 설정
    3. 아래쪽에 있는 Before launch의 **+** Button 클릭 -> Build Artifact -> 빌드시킬 Aritfacet 선택
    4. Deployment menu
        * **+** Button을 클릭해서 Artifact 선택
        * 배포할 Artifact 선택 후, Application Context 지정    


## Web Project 구성
* Web과 Gradle 기준
1. Project 구성 요소
    * app or application: Application Code
    * assets: 

2. Project Structure
    ```
    .
    ├── .gradle
    ├── .idea
    ├── build
    ├── out
    ├── src
    │   └── main
    │       ├── java
    │       └── resources
    │       └── webapp
    │           ├── MEA-INF
    │           │   └── application.xml
    │           └── WEB-INF
    │               ├── web.xml
    │               ├── web.xml
    │               └── web.xml    
    ├── build.gradle
    ├── gradle
    │   └── wrapper
    │       ├── gradle-wrapper.jar
    │       └── gradle-wrapper.properties
    ├── gradlew
    ├── gradlew.bat
    └── settings.gradle
    ```
    * ```.idea```: 자신의 IntelliJ 프로젝트의 설정 값들이 저장된다.
        * 자신의 IDE에 맞춰서 자동으로 생성되기 때문에 Git(VSC)등을 이용해서 관리하는 경우 해당 폴더를 제외시키면 된다.
    * ```out```: IntelliJ 프로젝트 출력 Directory
        * run을 하게되면 자동으로 생성하게 된다.
    * ```src```: Source Directory
        * 개발한 Source Code들을 저장한다.
    * ```resrouces```: 
    * ```webapp```: Web 관련 Resource File을 저장한다.
    * ```META-INF```: 
    * ```WEB-INF```: 브라우저는 WEB-INF Directory를 볼 수 없다(은닉되어 있다). 그렇기 때문에 중요 파일들은 WEB-INF Directory에 넣어둔다.
        * ```web.xml```: 
    * ```libs```: 



## Wep develop 하면서 기본적으로 생각해야 될 것들
1. IDE Setting
    * IntelliJ, VS Code, Linux, Windows 등 자신에 맞게 설정
    1) JDK Version
        * JDK Download -> 위치는 /usr/lib/jvm/openjdk
        * System Env Var 설정: JAVA_HOME (링크를 이용)
    2) Plugins

2. Server Setting
    1) Web Server
        * 어떤 Stack을 쓸지 정한다.
            * Server: Nginx, Apache
            * Language: HTML5, CSS, javascript
            * Framework: AngularS
    2) WAS Server
        * 어떤 Stack을 쓸지 정한다.
            * Server: Nginx, Tomcat
            * Language: Java, Python, C/C++, C#
            * Framework: Spring, Django, Flask, .NET
    3) DB Server
        * 어떤 Stack을 쓸지 정한다.
            * SQL: MySQL, PostgreSQL
            * No-SQL: MongoDB, Hbase, Cassandra

3. Project 구성
    1) Project Structure
        * Multi Module 구성
        * 
    2) Gradle Build
        * Multi Module 구성
        * Dependency 추가
    3)     

4. Develop
    1) web.xml 설정
        * Servlet: 
        * Listener: 
        * Filter: Session, Security, Encoding 등 Server Connection 관리
    4) Business
        * IoC: 객체를 코드가 아닌 프레임워크를 통해서 관리
        * AOP: Exception
        * DB



## 변화과정
1. CGI(Common Gateway Interface)를 사용하여 Web Application 동작
    * Application을 처리하기 위해 새로운 Process를 생성한다.
    * 단점으로 계속 새로운 Process를 생성하기 때문에 리소스 낭비가 심함
2. Servlet
    * Multi-Thread 방식
    * 단점으로는 정해진 Servlet 형식에 맞춰서 개발을 해야된다.
    * 방식
    1) Servlet Class
        * HttpServlet 클래스를 상속해야 한다.
        * public 클래스로 만들어야 한다.
        * default 생성자가 있어야 한다.
        * 원하는 기능을 정의하기 위해서 doGet, doPost, init, destroy 재정의(Overriding) 해야한다.
    2) Filter Class    
        * Filter 클래스를 상속해야 한다.
        * public 클래스로 만들어야 한다.
    3) Listener Class
        * 
        *     
3. 
    * 
4. 
    * 
5. 
    * 
6. 
    * 
7. 
    * 


### Reference
* https://gmlwjd9405.github.io/2018/12/24/intellij-tomcat-war-deploy.html


