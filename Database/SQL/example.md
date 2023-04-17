# Table
```sql
CREATE TABLE custom_perf (
    host_name VARCHAR() NOT NULL,
    collect_date DATE NOT NULL,
    cpu_avg DOUBLE() NOT NULL,
    cpu_max DOUBLE() NOT NULL,
    mem_util_avg DOUBLE() NOT NULL,
    mem_util_max DOUBLE() NOT NULL,
    primary key (host_name, collect_date)
);
```


# Select
```sql
-- 처음 저장할 때 (모든 데이터)
-- Sub Quary
select h.name as h_name, h.host as h_ip, v.name, v.clock, to_char(v.value_min, 'FM990.00'), v.value_avg, v.value_max
    from hosts h left join
        (select i.hostid, i.name, to_char(to_timestamp(t.clock), 'YYYY-MM-DD') as clock, min(t.value_min) as value _min, avg(t.value_avg) as value_avg, max(t.value_max) as value_max
            from items i
            inner join trends t on i.itemid = t.itemid
        where i.name in ('CPU Utilization', 'Memory utilization')
        group by 1. hostid, 1.name, to_char(to_timestamp(t.clock), 'WY-MM-dd')
        ) v on v. hostid = h. hostid
    where not h.status = 3 and h. name = 'ec2-ac1-prd-an2-a-dbsafer01'
limit 100
;

-- Inner Join
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


-------------------------------------------------
분리 시켜보기
select h.name as h_name, h.host as h_ip
    from hosts h
;

select i.hotsid, to_char(to_timestamp(t.clock), 'YYYY-MM-DD'), t.value_max as cpu_max, t.value_avg as cpu_avg
    from item i
    where i.name = 'CPU Utilization'
    inner join trends t on i.itemid = t.itemid
    group by i.hotsid, t.num, t.clock
;

select i.hotsid, to_char(to_timestamp(t.clock), 'YYYY-MM-DD'), t.value_max as mem_max, t.value_avg as mem_avg
    from item i
    where i.name = 'Memory utilization'
    inner join trends t on i.itemid = t.itemid
    group by i.hotsid, t.num, t.clock
;

-------------------------------------------------


SELECT *
    FROM host h INNER JOIN item i ON h.hostid = i.hostid
    WHERE h.host = ''
;

-- Sub Query (check one)
SELECT 
    FROM trend t LEFT JOIN
        (SELECT h.host as h_name, h.name as v_name, i.
            FROM item i
            INNER JOIN host h ON h.hostid = i.hostid
            WHERE h.name = ''
                AND 
        ) v ON t.itemid = v.itemid
    -- WHERE h.host = ''
    LIMIT 100
;


-- Sub Query (check all)
SELECT *
    FROM trend t LEFT JOIN
        (SELECT * 
            FROM item i
            INNER JOIN host h ON h.hostid = i.hostid
        ) v ON t.itemid = v.itemid
    -- WHERE h.host = ''
    LIMIT 100
;


```

---
## Zabbix

```sql
-- Create Table
CREATE TABLE custom_perf (
    host_name VARCHAR() NOT NULL,
    collect_date DATE NOT NULL,
    cpu_avg DOUBLE() NOT NULL,
    cpu_max DOUBLE() NOT NULL,
    mem_avg DOUBLE() NOT NULL,
    mem_max DOUBLE() NOT NULL,
    primary key (host_name, collect_date)
);


-- Insert
insert into custom_perf (host_name, collect_date, cpu_avg, cpu_max, mem_avg, mem_max)
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
on conflict (host_name, collect_date)
do nothing
;



-- 배치로 저장할 때 (Start 00:05:00 / Before 1 Day)
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
    and t.clock = extract(epoch from CURRENT_DATE - 1)::integer
group by h.name as host_name, to_char(to_timestamp(t.clock), 'YYYY-MM-DD')
;


select current_date;
select extract(epoch from CURRENT_DATE);
select extract(epoch from CURRENT_DATE)::integer;

```


```yaml
database:
  postgre:
    host : "10.20.0.200"
    port : "5432"
    dbname : "zabbix"
    user : "zabbix"
    password: ""
```


```python
# custom_perf DO
class CustomPerfDO:
    def __init__(self, host_name, collect_date, cpu_avg, avg_max, mem_avg, mem_max):
        self.host_name = host_name
        self.collect_date = collect_date
        self.cpu_avg = cpu_avg
        self.cpu_max = cpu_max
        self.mem_avg = mem_avg
        self.mem_max = mem_max
    
    def __del__(self):
        del host_name
        del collect_date
        del cpu_avg
        del cpu_max
        del mem_avg
        del mem_max


## dbLib 
# Connection 
import psycopg2
import yaml

def getConnectionInfo(file):

    db_cfg = {}    
    with open(file, encoding='UTF-8') as f:
        cfg = yaml.load(f, Loader=yaml.FullLoader)

    db_cfg['host'] = cfg['host']
    db_cfg['dbname'] = cfg['dbname']    
    db_cfg['user'] = cfg['user']
    db_cfg['password'] = cfg['password']
    db_cfg['port'] = cfg['port']

    return db_cfg

def getConnection(host, port, dbname, user, password):
    try:
        conn = psycopg2.connect(host=host, dbname=dbname, user=user, password=password, port=port)
    except Exception as e:
        print("[error] cannot connect to postgresql", e, sep="\n")
    else:
        return conn


def closeConnection(conn):
    try:
        conn.close()
    except Exception as e:
        print("[error] cannot close connection", e, sep="\n")       


def selectPerf(conn, query=None):
    try:
        cur = conn.cursor()
        if query is not None:
            cur.execute(query)
        else:
            cur.execute("""select h.name as host_name,
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
                and t.clock = extract(epoch from CURRENT_DATE - 1)::integer
            group by h.name as host_name, to_char(to_timestamp(t.clock), 'YYYY-MM-DD')
            ;""")

    except Exception as e:
        pass


def insertPerf(conn, query=None):
    try:
        cur = conn.cursor()
        if query is not None:
            cur.execute(query)
        else:
            cur.execute("""SELECT * FROM custom_perf;""")
            cur.execute("""insert into custom_perf(host_name, collect_date, cpu_avg, cpu_max, mem_util_avg, mem_util_max) 
            values ('abc', '2023-04-16', 3.0, 4.0, 5.0, 6.0);""") 
        conn.commit()

    except Exception as e:
        conn.rollback()
        print("[error] insert error", e, sep="\n")
```

```python
## MAIN
import dbLib

def main():

    db_cfg = dbLib.getConnectionInfo('db_cfg.yaml') 

    # host="127.0.0.1"
    # port=5432
    # dbname="test"
    # user="postgres"
    # password=""

    host = db_cfg['host']
    port = db_cfg['port']
    dbname= db_cfg['dbname']
    user = db_cfg['user']
    password = db_cfg['password']    

    conn = dbLib.getConnection(host, port, dbname, user, password)
    print("exit")
    if conn is not None:
        dbLib.insertPerf(conn)
        dbLib.closeConnection(conn)
    

if __name__ == "__main__":
    main()


# CustomPerfLogic



```




