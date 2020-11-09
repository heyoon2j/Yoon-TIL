# VIEW & INDEX
## View
* 가상 테이블. 실제 데이터를 저장하지 않고, 참조한 값을 보여준다.
* 그렇기 때문에 수정 및 인덱스 설정이 불가능
* 사용하는 이유는 단순하게 만들어 주기 위해서 사용된다.
* 뷰의 장점
    1) 보안 : Table에는 접근하지 못하도록 제한을 걸고 뷰에만 접근 권한을 준다.
    2) 복잡한 쿼리 단순화 : 복잡한 쿼리를 View를 생성해 두고 View에 접근한다.
```
CREATE VIEW view_name
    AS query;
```

## Index
* 테이블에서 데이터를 빠르게 검색할 수 있도록 도와주는 기능
* 인덱스 장단점
    * 장점
        * 데이터를 더 빠르게 찾을 수 있도록 해준다.
    * 단점
        * 저장공간을 더 많이 차지. <찾아보기>가 책보다 두꺼워 질 수 있다.
        * Insert, Update, Delete 속도가 느려짐
* 사용방법
    * WHERE 절에서 조건으로 사용하는 Column을 INDEX로 설정하면 좋음
* 자료구존
    * B-Tree (Balance)
* 인덱스 종류
    1) 클러스트형 인덱스 : <영어 사전>, 책의 내용 자체가 순서대로 정렬되어 있어, 인덱스 자체가 책의 내용과 같은 것.
        * Clustered Index 테이블 당 1개
        * PRIMIARY KEY 설정 시 클러스터형 인덱스가 자동으로 생성
        * PK가 없는 경우, UNIQUE NOTNULL 설정 시 클러스터형 인덱스가 자동으로 생성
        * PK가 없는 경우, UNIQUE NOTNULL 설정 시 보조 인덱스가 생성된다.
    2) 비클러스트형 인덱스 (보조 인덱스) : <찾아보기가 있는 책>, 찾아보기가 별도로 있어서, 찾아보기를 찾아 그 옆에 표시된 페이지로 이동
        * Secondary Index는 테이블 당 여러개 생성 가능
* 튜닝 방법
    1) 응답 시간(Response Time)을 빨리 한다.
    2) 서버의 부하량 최소화.
    * 단, 시간을 줄인다고 서버의 부하량을 늘리게 되면, 시스템 성능은 나빠질 수 있다.
    * 응답 시간 : 캐쉬 이용
    * 서버의 부하량, 인덱스 or 잘 짜여진 쿼리 사용
* 


```
# 1. INDEX 생성
CREATE INDEX index_name
    ON table_name (col_name);

# ALTER TABLE table_name ADD INDEX index_name (col_name);

# Example
CREATE INDEX fdate
	ON salaries (from_date);


# 2. INDEX 삭제
DROP INDEX index_name
    ON table_name;

# Example
DROP INDEX fdate
    ON salaries;
```