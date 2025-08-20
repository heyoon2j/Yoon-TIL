# DML
* Data Manipulation Language
* 데이터 조작어
    - SELECT
    - INSERT
    - DELETE
    - UPDATE
* 조건문
    - CASE


---
## SELECT
* 데이터를 검색할 때 사용되는 문법
* 기본 포멧
    ```
    SELECT select_expr
        [FROM table_references]
        [WHERE where_condition]
        [GROUP BY {col_name:expr:position}]
        [HAVING where_condition]
        [ORDER BY {col_name:expr:position}];
    ```

* Example
    ```
    # 전체 column 데이터 조회
    SELECT *
        FROM world.country;

    # code, name column 데이터 조회
    SELECT * 
        FROM world.country;
    ```

  
### ALIAS
* Column의 이름을 변경할 수 있다.
* **AS** 키워드 사용
```
SELECT code AS country_code
    FROM coutnry;
```

### WHERE
* 특정 조건을 주어 데이터를 검색하는데 사용되는 문법
1. 조건 연산자
    * =, <, >, != 등
2. 관계 연산자
    * NOT, AND, OR 등
3. BETWEEN
    * A AND B (A와 B가 포함된 사이 값)
4. IN ()
    * () 안에 포함되어 있는 값들, OR 연산자
5. NOT IN ()
    * () 포함되지 않는 값들
6. LIKE '김%_'
    * 문자열 내용 검색    
```
# WHERE : 비교 연산, 논리 연산
SELECT code, name, population
	FROM country
    WHERE population >= 200000000;

# 인구가 2억 ~ 3억인 국가를 출력
SELECT code, name, population
	FROM country
    WHERE population >= 200000000 AND population <= 300000000;
# BETWEEN
SELECT code, name, population
	FROM country
    WHERE population BETWEEN 200000000 AND 300000000;

# 아시아와 아프리카 대륙의 국가 데이터 출력
SELECT code, name, continent, population
	FROM country
    WHERE (continent = "Asia" OR continent = "Africa")
		AND population >= 100000000;
        
SELECT code, name, continent, population
	FROM country
    WHERE (continent IN ("Asia", "Africa"))
		AND population >= 100000000;


# LIKE : 특정 문자열이 포함된 데이터를 출력
SELECT code, name, GovernmentForm
	FROM country
	WHERE GovernmentForm LIKE "%Republic%";
```

### GROUP BY
* 그룹으로 묶어주는 역할
* 특정 Column의 동일한 데이터를 합쳐주는 방법
* 데이터를 합칠 때, 다른 column들에 대한 처리는 그룹 함수를 이용
* COUNT, MAX, MIN, AVG, VAR_SAMP(근사 값), STDDEV(편차)
    ```
    # city 테이블에서 국가별 도시의 갯수를 출력
    SELECT countryCode, COUNT(countryCode)
    	FROM city
        GROUP BY countryCode;
    
    # MAX : 대륙별 인구수와 GNP의 최대값을 출력
    SELECT Continent, MAX(Population), MAX(GNP)
    	FROM country
        GROUP BY Continent;
    
    # SUM : 대륙별 전체 인구수와 전체 GNP, 인당 GNP
    SELECT continent, SUM(population), SUM(GNP), SUM(GNP)/SUM(Population) as GPP 
    	FROM country
        GROUP BY continent
        ORDER BY GPP;
    
    # AVG : 대륙별 평균 인구수와 평균 GNP를 출력하고 인구수 순으로 내림차순 정렬
    SELECT continent, AVG(population) as population, AVG(GNP) as gnp
    	FROM country
        WHERE population != 0 AND gnp != 0
        GROUP BY continent
        ORDER BY AVG(population) DESC;
    ```

### HAVING 
* GROUP BY로 출력되는 결과를 필터링할 때 사용
```
# 대륙 별 전체 인구수를 출력하고 대륙 별 5억 이상이 되는 대륙만 출력
SELECT continent, SUM(population) as population
	FROM country
    GROUP BY continent
    HAVING population >= 500000000;

```

### ORDER BY
* 원하는 순서대로 정렬
    * ASC, 기본적으로 오름차순 (ASCENDING)
    * DESC, 내림차순 (DESCENDING)
    ```
    # ORDER BY : 데이터 정렬
    # 국가 데이터를 인구수 순으로 오름 차순으로 정렬
    SELECT code, name, population
	    FROM country
        ORDER BY population ASC;

    # 내림 차순으로 정렬
    SELECT code, name, population
    	FROM country
        ORDER BY population DESC;
    ```

* 여러 개의 Column 정렬 시 1번 째 조건에 대해 정렬 후, 같으면 2번 째 조건으로 정렬
    ```
    # 기준 컬럼을 여러개 설정 : 1 번째 조건으로 소팅 > 같으면 2번째 조건으로 소팅 ...
    # city 테이블에서 국가 코드 순으로 정렬(오름차순)하고
    # 국가 코드가 같으면 인구수 순으로 정렬(내림차순)
    SELECT CountryCode, name, population
	    FROM city
        WHERE countrycode IN ("USA", "KOR", "JPN")
        ORDER BY countrycode ASC, population DESC;
    ```

* **LIMIT** : 조회하는 데이터의 수를 제한
    * LIMIT offset, count : offset 부터 count 개수만큼 제한 (offset은 0부터 시작)
    ```
    SELECT countrycode, name, population
    	FROM city
        ORDER BY population DESC
        LIMIT 5;
    
    # LIMIT 5, 2 : 앞에 5개의 데이터를 스킵하고 뒤에 2개 데이터를 출력
    SELECT countrycode, name, population
    	FROM city
        ORDER BY population DESC
        LIMIT 5, 2;
    ```

---
## INSERT
* Table에 데이터 추가
    ```sql
    INSERT INTO table_name(col1, col2, ...)
        VALUES (val1, val2, ...)

    # Example    
    INSERT INTO user1(user_id, name, email, age, rdate)
    	VALUES (2, "bndy", "bndy@gmail.com", 23, now()),
    		(3, "cndy", "cndy@gmail.com", 23, now()),
    		(4, "dndy", "dndy@gmail.com", 23, now());
    ```

* SELECT 문 결과를 INSERT
    ```sql
    INSERT INTO city2
    	SELECT Name, CountryCode, Population
    	FROM city
        WHERE Population >= 8000000;
    ```


* 데이터 없을 때 추가, 있을 때는 업데이트
    * INSERT INTO ~ ON CONFLICT [colume_name/ON CONSTRAINT constraint_name/WHERE predicate] [DO NOTHING/DO UPDATE SET column = value]
    * DO NOTHING : 충돌날 경우, 아무것도 안하겠다는 의미
    * DO UPDATE SET : 충돌날 경우, 해당 데이터로 업데이트
    ```sql
    insert into custom_perf (host_name, collect_date, cpu_avg, cpu_max, mem_avg, mem_max)
        on conflict (host_name, collect_date)
        do nothing
    ```



---
## DELETE
* 데이터 삭제
```
DELETE FROM [table_name]
    WHERE [condition];

# Example
DELETE FROM user2
	WHERE rdate > "2020-10-25"
    LIMIT 1;
```


---
## UPDATE
* 데이터 수정
    ```
    UPDATE [table_name]
        SET col1 = val1, col2 = val2 ...
        FROM [ref_table]
        WHERE condition;

    # Example
    UPDATE user2
        SET email = "jin@gamil.com", age = 22
        WHERE name = "jin"
        LIMIT 1;
    ```
* Update join query
    ```
    # update custom_pef mcp
    update msp custom perf mcp
    set cpu_max = tmp.cpu_max, mem_max = tmp.mem_max, cpu_avg = tmp.cpu_avg, mem_avg = tmp.mem_avg
    from (
        select h. name as name,
            ...
        group by h.name, date_trunc(' day", to timestamp(t.clock))
    ) tmp
    where mcp.name = tmp.name
    and mcp.collect_date = tmp.collect_date
    ;
    ```

---
## CASE THEN
* Basic
    ```
    CASE column_name WHEN 'Value1' THEN 'Result1'
    ```
    - column_name == 'Value1'인 경우, Result1 반환
* Example
    ```sql
    select h.name as host_name,
        to_char(to_timestamp(t.clock), 'YYYY-MM-DD') as collect_date,
        avg(case i.name when 'CPU Utilization' then t.value_avg end) as cpu_avg,
        min(case i.name when 'CPU Utilization' then t.value_max end) as cpu_max,
        avg(case i.name when 'Memory utilization' then t.value_avg end) as mem_avg,
        min(case i.name when 'Memory utilization' then t.value_max end) as mem_max,
    from host h
        inner join items i on i.hostid = h.hostid
        inner join trends t on t.itemid = i.itemid
    where h.status = 0
        and h.flags = 0
        and i.name in ('CPU Utilization', 'Memory utilization')
        and t.clock >= extract(epoch from to_timestamp('2023-04-01', 'YYYY-MM-DD'))::integer
        and t.clock < extract(epoch from to_timestamp('2023-04-16', 'YYYY-MM-DD'))::integer
    group by h.name as host_name, to_char(to_timestamp(t.clock), 'YYYY-MM-DD')
    ;
    ```