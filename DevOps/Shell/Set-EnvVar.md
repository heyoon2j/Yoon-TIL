# Set Environment Variable
* 환경 변수 설정

## 1. 일회용 환경 변수
* 해당 터미널에만 적용된다. 터미널을 종료하면 설정 값이 없어진다.
```
$expert PATH=$PATH:/home/java/jdk
```

## 2. Login shell(+Non) 환경 변수
* 사용자가 로그인 or 새 터미널을 여는 경우, 적용된다.
```
# 파일 수정
# ~/.profile, /etc/profile, ~/.bash_profile, ~/.bashrc 등이 있다.
$vi ~/.profile

# 파일에 해당 내용을 적는다.
expert PATH=$PATH:/home/java/jdk

# 수정 내용 적용
$source ~/.profile
```

## 3. System 환경 변수
* 시스템 환경 변수에 적용하는 방버
```
# 적용 방법은 2번과 같다.
$vi /etc/environment
```