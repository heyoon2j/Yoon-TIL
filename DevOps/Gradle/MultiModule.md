# Gradle Multi Module

## Multi Module 구성하기
* https://docs.gradle.org/current/userguide/declaring_dependencies_between_subprojects.html
* https://dreamaz.tistory.com/55
* Structure
    ```groovy
    //  Project Name : "GanziProject"
    .
    ├── buildSrc
    │   ...
    ├── applications
    │   ├── src
    │   │   └──...
    │   └── build.gradle
    ├── libraries
    │   └── person-service
    │       ├── src
    │       │   └──...
    │       └── build.gradle
    └── settings.gradle
    ```

* settings.gradle(root)
    ```groovy
    rootProject.name = "GanziProject"
    include 'api', 'shared', 'services:person-service'
    ```
    * ```include```를 이용하여 subproject를 추가한다.

* build.gradle
    * 우아한 형제들 기술 블로그 예시: https://woowabros.github.io/tools/2019/04/30/gradle-kotlin-dsl.html
    * 기본 
    * ```allprojects {}```: 모든 프로젝트에 적용시킬 빌드 스크립트
    * ```subprojects {}```: 서브 프로젝트에 적용시킬 빌드 스크립트
    * 즉, 2개의 Block을 활용해서 공통 속성과 작동을 정의할 수 있다.


## settings.gradle
```
rootProject.name = "SpringIOCProject"


```


