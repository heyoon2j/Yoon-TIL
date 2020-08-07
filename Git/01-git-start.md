# Git Start

## Git Structure
#### .git 구조
```shell script
$ git init
$ ls .git
HEAD
config
description
/branches
/hooks
/objects
/refs
```

#### Objects
1. __BLOB(Binary Large OBject)__
    * 소스 파일, 이미지 파일 등 데이터를 파일 명 같은 메타데이터 없이, 바이너리 데이터 자체만 저장
2. __Tree__
    * blob에 저장되지 않은 파일 이름, 속성, 디렉터리 위치 정보를 저장하고 있다.
    * tree object는 blob(실제로 blob 메타데이터)와 tree object로 구성. 특정 시점의 디렉터리포 표현할 수 있다.
3. __Commit__
    * tree object명과 Author, Date, Message 저장.
4. __Tag__
    * commit object를 가리키며 태그명과 태그를 만든 사람, 주석 저장

* Reference
    * [HappyProgrammer_김대현](https://medium.com/happyprogrammer-in-jeju/git-%EB%82%B4%EB%B6%80-%EA%B5%AC%EC%A1%B0%EB%A5%BC-%EC%95%8C%EC%95%84%EB%B3%B4%EC%9E%90-1-%EA%B8%B0%EB%B3%B8-%EC%98%A4%EB%B8%8C%EC%A0%9D%ED%8A%B8-81b34f85fe53)
    * [빨간색코딩_빨간색소년](https://sjh836.tistory.com/37)


#### .gitignore
* git에서 추적하고 싶지 않은 파일을 설정하는 경우. 즉, 불필요한 파일에 대해 git 명령어가 사용되지 않도록 방지할 수 있다.
* https://www.toptal.com/developers/gitignore 에서 설정할 내용을 검색할 수 있다.

#### License
* Apache License
* MIT Licese
* GNU General Public License 
    
## Git Process
![git-Data-Transper.png](https://github.com/yoon2ix/Yoon-TIL/blob/master/Git/img/gitDataTransport.png)
* Git은 기본적으로 파일의 변경 이력 별로 구분하여 저장한다.
* __workspace__ : Working Directory, 현재 작업하고 있는 Directory 
* __index__ : Stagin Area, Local Repository에 저장하기 위해서는 이곳에 등록을 해야된다.
* __local repository__ : 내부 저장소, File의 생성/변경을 저장하는 공간
* __remote repository__ : 원격 저장소, 여기서는 Github에 생성한 Repository

## Install Git
* __for windows__ : [https://gitforwindows.org/](https://gitforwindows.org/)
* __for MacOS__ : ``` $ brew install git ```
* __for CentOS__ : ```$ yum install -y git```


## Configuration
#### 1. 전역 설정 
 * __$ git config --global user.name "{github username}"__ : 전역 사용자 설정
 * __$ git config --global user.email "{github email address}"__ : 전역 이메일 설정
 * __$ git config --global core.editor "{editor name}"__ : 전역 Editor 설정, 기본 편집기
 * __$ git config --global core.pager "cat"__ : Git은 log 또는 diff 같은 명령의 메시지를 출력할 때 페이지로 나누어 보여준다. 기본으로 사용하는 명령은 less 다. more 를 더 좋아하면 more 라고 설정한다. 페이지를 나누고 싶지 않으면 빈 문자열로 설정한다.
 * __$ git config --list__ : 설정 리스트 확인
 * __$ git config --global --unset {config name}__ : --unset 옵션을 이용하여 설정 지우기
```shell script
Ex>
$ git config --global user.name "yoon2ix"
$ git config --global user.email "email@gihub.com"
$ git config --global core.editor "vim"
$ git config --global core.pager "cat"
$ git config --list
$ git config --global --unset core.pager // core.pager 설정 지우기
 ```

#### 2. 저장소별 설정(해당 Directory)
* $ __git config --global user.name "{github username}"__ : 해당 저장소 사용자 설정
* $ __git config --global user.email "{github email address}"__ : 해당 저장소 이메일 설정
```shell script
Ex>
$ git config user.name "yoon2ix"
$ git config user.email "email@github.com"
```

## Start Git
#### 1. git init 이용하는 방법
* __$ git init__ : Git 저장소를 초기화, Local Repository로 역할을 시작한다.
* __$ git remote add {Name} {URL}__ : {URL}에 해당하는 Remote Repository를 {Name}으로 추가
* __$ git remote -v__ : Remote Repository의 이름 목록 표시
* __$ git remote rm {Name}__ : {Name} Remote Repository 삭제
```shell script
Ex>
$ git init
$ git remote add origin https://github.com/yoon2ix/Yoon-TIL.git
$ git remote -v
$ git add README.md
$ git commit -m "docs: Create README.md"
$ git push -u origin master // 처음 연결시킬때 -u 옵션 사용
$ git remote rm origin
```
#### 2. git clone 이용하는 방법
* __$ git clone {URL}__ : Remote Repository를 복사한다.
```shell script
Ex>
git clone https://github.com/yoon2ix/Yoon-TIL.git
```

## Add, Commit, Push
* __$ git add {file}__ : 생성/수정 File을 index로 staging한다.
    > objects에 blob 타입으로 파일 내용이 추가된다.
    
    > 파일이 수정되면 새로운 object(blob)가 생성된다. 
* __$ git status__ : 현재 git 상태 확인
* __$ git commit -m "{message}"__ : staging 되어있는 내용을 Local Repository에 저장 
    > objects에 commit 객체와 tree 객체가 추가된다.
    
    > tree 객체는 commit할 때의 index를 Snapshot을 찍어 저장한다.
* __$ git push {remote repository name} {branch name}__ : branch 내용을 remote repository에 저장.
    > -u 옵션은 upstream으로 remote repository와 branch를 연결하기 위해 사용된다. __보통 처음 연결할때 사용__

#### Commit Message 작성
* Commit할 때는 동작하는 최소 단위로 ex> 메소드 단위
* __feat:__ - features, 기능 구현시, 기능 설명
* __docs:__ - documentations, README.md 등 문서화 작업
* __fix:__ - bug-fix, 버그 수정시
* __conf:__ - configurations, 환경 설정
* __deploy:__ - deploy, 배포 작업
* __refactor:__ - refactoring, Refactoring하는 경우