# 조건문(Conditional)

## 1. if 계열
- if - else if - else 문
```
if(조건문){
	...
} else if(조건문){
	...
} else{
	...
}
```

- 예제
```
int score = 2;
char grade;
if(score >= 9){
	grade = 'A';
}else if(score >= 7){
	grade = 'B';
}else{
	grade = 'C';
}
```



## 2. switch 계열
- switch ~ case 문의 조건문은 '값'이 들어오게 된다(Boolean에 한정되지 않는다)
- case가 범위가 될 수 없고, case도 값이어야 한다.
- break 문을 사용하지 않으면 기본적으로 fall-through가 발생한다.

```
switch('값'){
	case '값':
		...
		break;
	default:
		...
}
``` 

- 예제
```
switch (grade){  
	case 'A':
		System.out.println("훌륭!");
		break;
	case 'B':
		System.out.println("멋짐!"); // fall-through (의도된 것을 의미)
	case 'C':
		System.out.println("노력필요");
		break;
	default:
		System.out.println("점수가 이상한 걸?");
}
```



