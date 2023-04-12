# SQL 기본 문법
```
# Select DB
USE [db_name]

# Show DB, Table, Column List
SHOW DATABASES;
SHOW TABLES;
DESC [table_name];

# Show Key
SHOW KEYS FROM [table_name];

# Show Index
SHOW INDEX FROM [table_name];

# Show DB used current
SELECT DATABASE();
```

## 사용자 권한
* Reference: https://treasurebear.tistory.com/51
1. MySQL 접속
    * mysql -h{host_ip} -u{id} -p{pw}
    ```
    # ip가 자기 자신인 경우 추가할 필요없다.
    $ mysql -uroot -p
    ```

2. MySQL 사용자 확인
    * user Table에서 확인할 수 있다.
    ```
    mysql> use mysql;
    mysql> select user, host from user;
    ```

3. 사용자 추가하기
    * '%'는 모든 Host에서 접근이 가능하다는 표시이다.
    ```
    mysql> CREATE USER 'id'@'host' IDENTIFIED BY 'pw';
    mysql> CREATE USER 'id'@'%' IDENTIFIED BY 'pw';
   
    mysql> CREATE USER 'test'@'localhost' IDENTIFIED BY 'rada';
    ```

4. 사용자 제거
    ```
    mysql> DROP USER 'id';
   
    mysql> DELETE FROM user WHERE user='id';
    ```

5. 사용자 권한 확인
    ```
    mysql> SHOW GRANTS FOR 'id'@'host';
    
    mysql> SHOW GRANTS FOR 'test'@'localhost';
    ```
6. 사용자 권한 설정
    * GRANT {권한} ON {db_name}.{table_name} TO '{id}'@'{host}' IDENTIFIED BY '{pw}';
    * DB와 Table에서 '*'는 All을 의미한다.
    * IDENTIFIED BY '{pw}'를 쓰게 되면 기존 id에 pw가 있을 시, 변경된다.
    ```
    # ALL PRIVILEGES는 모든 권한을 의미
    mysql> GRANT ALL PRIVILEGES ON test.* TO 'testid'@'%';
    
    # SELECT, UPDATE, DELETE, INSERT
    mysql> GRANT SELECT, UPDATE ON test.money TO 'testid'@'%';
    ```
   
7. 사용자 권한 삭제
    * REVOKE {권한} ON {db_name}.{table_name} TO '{id}'@'{host}';
    * 원하는 권한만 삭제도 가능하다.
    ```
    # ALL은 모든 권한을 의미
    mysql> REVOKE ALL ON test.* TO 'testid'@'%';
   
    # SELECT, UPDATE, DELETE, INSERT
    mysql> REVOKE SELECT ON test.* TO 'testid'@'%';
    ```
   
8. 변경된 내용 메모리에 반영
    ```
    mysql> FLUSH PRIVILEGES;
    ```

## EXPLAIN
* 직접 쿼리문을 던지는 것이 아닌 실행 계획을 확인한다.
* 여러 가지를 확인할 수 있다.
    * Cardinality : 첫 Tree Node 개수 (즉, Index를 사용해야 값이 나온다)
```
EXPLAIN
SELECT *
	FROM salaries
    WHERE from_date < "1986-01-01";
```

## DB Query 작성
* JOIN, Sub Query를 작성할 때는 무조건!!! 데이터를 줄여서 합치는 방법을 선택해야 된다.
* FROM DUAL : Sub Query 등으로 FROM 절에 올 Table이 없는 경우 DUAL을 사용

## Function()
* CONCAT() : 여러 개의 문자열을 연결
    ```
    CONCAT('str1', 'str2', 'str3')
    ```
* GROUP_CONCAT() : GROUP BY 할 때, 문자열을 연결시킨다.
    ```
    GROUP_CONCAT(col_name)
    ```
* SUBSTR() : 문자열 추출, 인덱스는 1부터 시작
    ```
    # str : 원본 문자열 / pos : 시작 위치값 / len : 길이
    SUBSTR(str, pos) # 해당 pos 부터 추출
    SUBSTR(str, pos, len) # 해당 pos 부터 len 길이만큼 추출
    ```
* LENGTH() : 문자의 Byte 길이 반환, 한글 길이는 알기 힘들다.
    ```
    LENGTH(str)
    ```
* CHAR_LENGTH() : 단순히 몇 개의 문자를 가지고 있는지 반환
    ```
    CHAR_LENGTH(str);
    ```
* CEIL() : 올림
    ```
    SELECT CEIL(12.345); # 13
    
    # 셋째 자리에서 올림하는 방법
    SELECT CEIL(12.345 * 100) / 100; # 12.35
    ```
* ROUND() : 반올림
    ```
    SELECT ROUND(12.345);
    
    # 소수점 둘째 자리까지 표현
    SELECT CEIL(12.345, 2);
    ```
    
* TRUNCATE() : 버림
    ```
    # 소수점 둘째 자리까지 표현
    SELECT TRUNCATE(12.345, 2);
    ```
    
* DISTINCT() : 중복된 값을 제거.

* DATE_FORMAT : 날짜 데이터에 대한 포멧을 변경
    * 공식 문서 : https://dev.mysql.com/doc/refman/5.7/en/date-and-time-functions.html
    ```
    SELECT SUM(amount) AS income, DATE_FORMAT(payment_date, "%H") AS monthly
    	FROM payment
    	GROUP BY monthly
        ORDER BY income DESC;
    ```

## 조건문
* IF(condition, true, false)
    ```
    SELECT countrycode, name, population,
    		IF(population >= 1000000, "big city", "small city")
    	FROM city
    	WHERE population >= 800000
        ORDER BY population DESC;
    ```

* IFNULL(true, false) : 데이터가 있는 경우 true, NULL인 경우 false 출력
    ```
    SELECT code, name, indepyear,
    		IFNULL(indepyear, 0)
    	FROM country;
    ```

* CASE WHEN THEN END
    ```
    SELECT code, name, population
    	, CASE
    		WHEN population >= 1000000000 THEN "level3" 
    		WHEN population >= 100000000 THEN "level2"
            ELSE "level1"
    	END AS scale
    	FROM country
        WHERE population >= 80000000
        ORDER BY population DESC;
    ```