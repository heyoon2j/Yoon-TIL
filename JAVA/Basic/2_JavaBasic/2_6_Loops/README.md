# 반복문 (Loops)
- 초기화 - 반복문을 실행하기 위해서 증감할 변수를 초기화  
- 조건식 - 반복문을 실행할 조건 (또는 종료한 조건)  
- 증감식 - 실행문이 실행된 후에 변수에 증감을 주는 것  
- 실행문 - 조건식이 참(또는 거짓)일 경우 실행할 코드  
- 증감식 - 실행문이 실행된 후에 변수에 증감을 주는 것

## 1. for
```
for( 초기화 ; 조건식 ; 증감식 ){
	실행문
}
```

- 예제
```
for(int i = 0; i < 5; i++){
	System.out.println(i);
}
```


## 2. while
- while 문
```
while(조건문){
	실행문
}
```

- do - while
```


```
> do - while 문은 최소 한 번 실행 된다.

## 3. 제어문
- continue
 반복문의 조건식으로 돌아간다.
```
for(int i = 0; i < 5; i++){
	if(i == 3)
		continue;
}
```

- break
반복문 Scope에서 빠져나온다.
```
for(int i = 0; i < 5; i++){
	if(i == 3)
		break;
}
```
- label - continue label
해당 label이 위치한 Nested 반복문으로 이동
```
label:
for(int i = 0; i < 5; i++){
	for(int j = 0; j < 5; j++){
		if(i == 3)
			continue label;
	}
}
```

