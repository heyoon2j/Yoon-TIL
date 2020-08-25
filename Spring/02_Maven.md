# Maven
* Build Tool
* 프로젝트 생성, 테스트 빌드, 배포 등의 작업을 위해 사용.
* 현재는 Gradle도 많이 사용되고 있다.

## Download Maven
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
    * Command -> Input **mvn -version**i
    
## Maven Lifecycle
* **Lifecycle**
    * **default** : 일반적인 빌드 프로세스를 위한 모델
    * **clean** : Build 시 생성되었던 target Directory(파일들)를 삭제하는 단계
    * **validate** : Project가 올바른지 확인하고 필요한 모든 정보를 사용할 수 있는지 확인하는 단계
    * **compile** : Project의 Source Code를 Compile하는 단계, target Director가 생성
    * **test** : Unit Test를 수행하는 단계(Test Fail 시, 실패 처리 또는 스킵 가능)
    * **package** : 실제 컴파일된 소스 코드와 리소스들을 jar, war 등의 파일로 배포하기 위한 Package로 만드는 단계 
    * **verify** : 통합 테스트 결과에 대한 검사를 실행하여 품질 기준을 충족하는지 확인하는 단계
    * **install** : 패키지를 로컬 저장소에 설치하는 단계
    * **site** : 프로젝트 문서와 사이트 작성, 생성하는 단계
    * **deploy** : 만들어진 package를 원격 저장소에 Release하는 단계

* **Phase**
    * 각 단계를 **Phase**라고 하며, Dependency 하다. 이전 Phase가 성공해야 다음 Phase가 실행된다.
* **Goal** 
    * 최소한의 실행 단위 **task**라고 하며, 하나의 Plugin에서는 여러 작업을 수행할 수 있다.
    * Plugin에서 실행할 수 있는 각각의 기능을 Goal이라고 한다.
    * 각각의 Phase에 연계된 Goal
    
* plugin 검색 : https://maven.apache.org/plugins/index.html

## Maven Setting File
1. **settings.xml**
    * Maven Build Tool과 관련한 설정파일
    * {Maven_HOME}/conf에 settings 파일이 있다.
    * Maven 빌드시, 의존 관계에 있는 Library, Plugin을 저장하는 로컬 저장소의 위치를 변경할 수 있다.
    * 기본 위치는 {USER_HOME}/.m2/reposiroty
2. **pom.xml**
    * POM(Project Object Model)을 의미
    * Project 당 1개이면, Project의 최상위 Directory에 생성
    * Project의 모든 설정, 의존성 등을 설정
    * Element
        * **modelVersion** : POM model Version
        * **parent** : Project 계층 정보
        * **groupId** : Project를 생성하는 Group ID
        * **artifactId** : Project Build시 대표 파일 이름. 보통 Project 이름 사용.
        * **version** : Project Current Version, Project 개발 중일 대는 SNAPSHOT을 접미사로 사용
        * **packaging** : Package Type(jar, war, ear 등)
        * **name** : Project Name
        * **description** : Project에 대한 간단한 설명
        * **url** : Project에 대한 Reference Site
        * **properties** : Project 속성 정보(ex> java Version 등)
        * **dependencies** : 해당 Tag 안에는 Project와 의존 관계에 있는 Library를 관리
        * **build** : 빌드에 사용할 Plugin 목록
    * dependence 는 https://search.maven.org/ 에서 찾는다(Maven Central)
    
## Maven File Structure
```shell script
my-app
|-- pom.xml
`-- src
    |-- main
    |   `-- java
    |       `-- com
    |           `-- mycompany
    |               `-- app
    |                   `-- App.java
    `-- test
        `-- java
            `-- com
                `-- mycompany
                    `-- app
                        `-- AppTest.java
```
* File Structure을 위와 같이 만들어야 한다.
* main, test Directory에 "java"와 함께 "resources"를 만들어 두면 용이하다.
* resources에는 logback.xml과 같은 파일을 저장한다.

## 


### Reference
* https://goddaehee.tistory.com/199
* https://maven.apache.org/guides/getting-started/index.html
* https://search.maven.org/