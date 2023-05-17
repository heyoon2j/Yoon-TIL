# 사용자 관리
https://tragramming.tistory.com/85
https://yoonix.tistory.com/21


```

useradd 




# Home Direcotry 변경
usermod -d /app/test testuser
```


```
#chage -l <user>

#chage -d YYYY-MM-DD user: 암호 변경일 변경

#chage -m 5 user: 최소 사용일 수 변경

#chage -M 99999 user: 변경일로부터 최대 사용일 수 변경, 설정하면 암호 만료일을 알 수 있다. 

#chage -W 7 user: 암호 만료 경고 기간 변경

#chage -I(대문자 i) 10 user: 유예기간(inactive) 변경

#chage -E YYYY-MM-DD user: 계정 만료 날짜 변경

=> YYYY-MM-DD를 "" or -1로 설정하면 제한 없음을 뜻한다.
=> 계정 만료 날짜는 유예기간보다 뒤로 설정해줘야 한다.

#chage -l(소문자 L) user: user의 패스워드 설정 정보 출력
```



```
# vi /etc/login.defs

PASS_MAX_DAYS   90
PASS_MIN_DAYS   1
PASS_MIN_LEN   8
PASS_WARN_AGE   7
```