# Function


## 날짜
```sql
-- NOW() : Timestamp
select NOW();


-- CURRENT_TIMESTAMP 값 사용 (== NOW())
select CURRNET_TIMESTAMP;


-- CURRENT_TIME, CURRENT_DATE
select CURRENT_TIME, CURRENT_DATE;


-- UNIX Timestamp
-- 기본적으로 UNIX 시간은 그냥 얻을 수 없고, 다음과 같은 방법으로 얻을 수 잇다.
select extract(epoch from now())




-- 변경
select to_char(now(), 'YYYYMMDD');

select to_timestamp('20230415', 'YYYYMMDD');


```

### 날짜 계산
```sql
-- interval





```



