# 취약점 점검


## 
* Session

```
$vi /etc/profile


```
* 600 초 (10분) 이하로 설정하는 것이 양호
</br>



## SRV-069
* 비밀번호 관리정책 설정 미비
* 기준 



```
$ vi /etc/login.defs

PASS_MAX_DAYS   90
PASS_MIN_DAYS   1
PASS_MIN_LEN    8
PASS_WARN_AGE   7
```
* 일반적으로 패스워드 길이는 8자리 이상 권장
* 패스워드 최대 사용기간은 90일
* 패스워드 쇠소 사용기간은 1일 (0인 경우, 잦은 변경 방지)




