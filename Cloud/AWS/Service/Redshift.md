# AWS Redshift
* AWS 관리형 데이터 하우스 서비스







## Node Type
1. RA3

2. DC2





### Resizing
* https://aws.amazon.com/ko/premiumsupport/knowledge-center/redshift-elastic-resize/
* 슬라이스 매핑을 사용하여 Cluster 크기를 조정
1. 탄력적 크기 조정 (Elastic resize change)
    * Cluster는 유지한 채, Node만 추가한다. 그렇기 때문에 Cluster의 Endpoint가 변경되지 않는다.
    * 전체 데이터 슬라이스를 슬라이스 매핑된 노드에 복제된다.
2. 클래식 크기 조정 (Classic resize change)
    * 새로운 Cluster를 생성하고, 모든 행을 Cluseter에 저장한다. 그 후에 노드에 매핑하여 분포 설정에 따라 분할한다.
    * Cluster의 Enpoint가 바뀌는거 같다.
</br>



ami-0e0f72498373d693d
t2.medium
<powershell>
net user Administrator “koreanre12!”
$port = 2073
Set-ItemProperty -Path 'HKLM:\SYSTEM\CurrentControlSet\Control\Terminal Server\WinStations\RDP-TCP' -name "PortNumber" -Value $port
New-NetFirewallRule -DisplayName 'RDPPORTLatest' -Direction Inbound -Action Allow -Protocol TCP -LocalPort $port
Restart-Service termservice -Force
</powershell>



## 구축 방법
* 
1. q
2. w
3. e
4. r









## Study

* 다크 데이터 : 숨겨져 있는 데이터들 또는 관리되지 않는 데이터들을 관리 및 활용할 수 있어야 된다.
* 통합이 필요, Hadoop 등을 사용하게 되면 해당 언어를 배워야 하지만 SQL 문으로 관리할 수 있다.
* Data Lake + Warehouse = Data Lake House Architecture


## Architecture
* Leader Node
    * SQL endpoint, metadata 저장소
    * SQL 병렬 처리 및 ML 최적화 조정
    * 2개 이상의 Compute Node 사용시 Reader Node는 무료
* Compute Node (연산 노드 == Reader Node)
    * 로컬, 컬럼형 스토리지
    * SQL 연산 처리
    * S3로부터 데이터 Load, Unload, Backup, Restore
    * 

* RAM (Redshift Managed Storage)
    * Noe 유형에 따라 다르다. 다른 유형인 경우(DC2), 각 Compute Node 쪽에 저장소가 있음
    * S3에 저장이되며, S3는 Object Storage라서 속도 저하가 올 수 있으므로 고속 SSD Caching이 있음

* VPC
    * Leader Node 만 Customer VPC 네트워크 안에 있다. 그래서 Leader Node에만 접근이 가능하다.
    * 내부적으로 Leader, Compute Node는 Internal VPC에서 동작을 한다.


## Data Modeling
* Star
* Snowflake
* Highly Denormalized


* 인덱스가 없고, Table 분산 스타일, 데이터 압축, Sork Key 등으로 처리한다.

구조
* 데이터를 넣을 때는 Schema-on-write(RDB) 형태를 가진다
* 데이터를 읽을 때 Schema-on-read(원하는 Schema 형태에 집어넣는)방식 지원


Best Choose
* 성능은 효율적인 I/O에 가장 큰 영향을 받는다.
* 모든 Column은 필요한 만큼 지정
    * 국가코드에 BIGINT 사용 금지
* 적절한 데이터 타입 사용
    * 날짜 값에 CHAR 타입 대신 TIMESTAMP 혹은 DATE 사용
    * 
* UTF-8 Multibiyte character 지원을 위해 VARCHAR 데이터 타입 사용
* SUPER 데이터 타입
    * 비정형 데이터의 경우 방법은 여러가지이다.
    * Read할 때 JSON에 넣는 방법(But 너무 느려서 최악!!!)
    * 넣을 때 SUPER 데이터로 놓는다.

고려 사항
* Sort Keys : 어떤 식으로 저장하고 부를지
* Compression : 어떤 식으로 저장하고 부를지
* Distribution Style : 데이터를 어떤 식으로 분산시킬지
    * 어떤 식으로 저장할지를 정해야 한다. 이유는 다른 Compute Node는 10초에 끝났는데, 다른 C.N.에서는 1시간 걸리면 총 걸리는 시간은 1시간이 된다.
    * Auto : 자동
    * Even : Round-Robin
    * ALL : 모든 Compute Node에 저장이 되어, 각 테이블에 Join이 되는 경우 사용 (모두 복사되므로 잘 사용해야 될듯 데이터는 많이 안증가하면서 Join이 필요한 경우에만)
    * Key : 같은 Key를 가진 가진 쪽에 저장. (100 개중 90개 정도가 해당 그룹을 사용한다에 사용 / Zone map)
* Workload Management : 어떤식으로 쿼리를 분산 시킬지
* Table Maintenance : 디스크 조각 모음


* Materialized Views (MView)
    * 미리 데이터를 저장하고 Reflash하는 방식


## Data loading / unloading
loading : 외부에서 -> Redshift로 데이터가 이동
* copy 명령어 사용 (가이드)
* Best Practices
    * 각 테이블 당 하나의 COPY 명령어
    * COPY를 사용할 수 없는 경우, multi-row insert 사용

* 부하가 낮은 시점에 백그라운드에서 자동으로 vacuum 및 analyze 사용
    * 하지만 기다리는 시점까지 안된다는 의미로 현실적으로 힘드므로 워크로드 내에서 직접 vacuum 실행후 저장
* PK, FK, Unique, constraints는 Redshift 외부에서 작업 후, DB에 저장
* DELETE 대신 TRUNCATE 사용 고려




unloading : Redshift에서 -> 외부로 데이터가 이동
* unload 명령어 사용





## Query 처리
* EXPLAIN 명령어를 통해 실행 계획 가능(Redshift에서 제공 가능)
* Query Plan 중에 Cost를 보고 어느정도 사용하는지 볼 수 있다(참고정도만)
    * Cost는 Relative Cost이므로 Query 안에서만 이므로 상대적인 것이기 때문에 실제로 그만큼 시간이 걸리는 것이 아니다...
* DS_DIST_NOTE : 각 노드 안에서만 동작하므로 네트워크 비용이 안든다. 즉, 다른 노드의 데이터를 가져와서 사용할 필요가 없다.


## 

```

# Error Message Check
select * from SYS_LOAD_ERROR_DETAIL;


# Delete 한 후에 테이블에서 바로 삭제가 되는 것이 아닌 쇼프트 삭제(	estimated_visible_rows)가 진행된다. vacuum delete 명령을 통해 실제 테이블 공간을 삭제를 해야 한다.
# Delete, Update, Sort 등
select "table", size, tbl_rows, estimated_visible_rows
from SVV_TABLE_INFO
where "table" = 'orders';

delete orders where o_orderdate between '1997-01-01' and '1998-01-01';

vacuum delete only orders;
vacuum sort only orders;
vacuum recluster orders;
vacuum recluster orders boost;



# 프로시저 호출, Redshift 구조는 ELT 구조가 가능하다
SELECT count(*) FROM "dev"."public"."lineitem"; --303008217
call lineitem_incremental();


# MView 사용하여 특정 쿼리에 대해 정보를 저장하며 주기적인 업데이트 가능 (AUTO REFRESH 옵션은 YES로 )
# 기본 테이블이 업데이트 될때 기준으로 Refresh
CREATE MATERIALIZED VIEW supplier_shipmode_agg
AUTO REFRESH YES AS
select l_suppkey, l_shipmode, datepart(year, L_SHIPDATE) l_shipyear,
  SUM(L_QUANTITY)	TOTAL_QTY,
  SUM(L_DISCOUNT) TOTAL_DISCOUNT,
  SUM(L_TAX) TOTAL_TAX,
  SUM(L_EXTENDEDPRICE) TOTAL_EXTENDEDPRICE  
from LINEITEM
group by 1,2,3;


## EXPLAIN을 통해 DRY Run 가능
explain
select n_name, s_name, l_shipmode, SUM(L_QUANTITY) Total_Qty
from lineitem
join supplier on l_suppkey = s_suppkey
join nation on s_nationkey = n_nationkey
where datepart(year, L_SHIPDATE) > 1997
group by 1,2,3
order by 3 desc
limit 1000;

# 사용자 함수의 경우 SQL 또는 Python 사용 가능
create function f_sql_greater (float, float)
  returns float
stable
as $$
  select case when $1 > $2 then $1
    else $2
  end
$$ language sql;  

select f_sql_greater (l_extendedprice, l_discount) from lineitem limit 10

create function f_py_greater (a float, b float)
  returns float
stable
as $$
  if a > b:
    return a
  return b
$$ language plpythonu;

select f_py_greater (l_extendedprice, l_discount) from lineitem limit 10


$$

```

* Spectrum : read only 

Logging
* Audit Logging
* Sevice Logging : CloudTrail

Migration


## Glue
1. Glue -> Database -> DB 생성
2. Glue -> Crawlers 생성 -> Crawlers Run -> Dastabse Tables에 Catalog Table이 생성 됨
3. 




## 운영
* 

* 스냅샷 복원 시간은 10분ㅂ내외로
* resizing
* RA3의 경우, 2단계 과정 거친다
    * stage1
        * 클러스터 메타데이터를 마이그레이션 하는 과정
    * stage2
        *용
* DA2
    * 생성 후, 복제


* Redshift Workload Management(WLM)
    * 사용자를 기준으로 분리
    * 쿼리의 우선 순위를 설정 가능
    * 쿼리 모니터링 Rule 설정 가능


* 시스템
SYS_QUERY_HISTORY
SYS_QUERY_DETAIL
SYS_EXTERNAL_QUERY_DETAIL
SYS_LOAD_HISTORY
SYS_LOAD_ERROR_DETAIL
SYS_UNLOAD_HISTORY
SYS_SERVERLESS_USAGE