# ElastiCache

## Sharding
파티셔닝(Partitioning)이라고도 부름. 동일한 스키마를 가지고 있는 데이터를 특정 조건에 따라서 각 서버에 분산 저장하는 기법 
* Hash Shading : 특정 Hash 값에 따라 데이터를 분산하여 저장 (ex> id % 4)
* Range Shading : 특정 범위를 지정하여 데이터를 분산하여 저장 (ex> a-n = 0 / m-z = 1 / A-Z = 2)
</br>


---
## Cluster
Cluster는 1개 이상의 Shard로 구성되어 있다.
* Cluster 모드를 활성화해야 여러 개의 샤드를 구성할 수 있다.
* 비활성화 모드에서는 샤드가 1개로 제한된다.
* 1개 이상의 Replication이 있어야 한다.
> 활성화 시, RW/RO 엔드포인트가 없어지고 Cluster 엔드포인트가 생성되니 설정시 주의 필요
</br>


## Shard
Sharding을 하는 단위이며, Node들을 하나로 묶는 그룹이다.
* Shard 한도
    - Cluster 비활성 모드 : 오직 1개만 가능
    - Cluster 활성화되어 모드 : Cluster 당 최대 500개 (ex 83개의 샤드 적용 시, Primary 1개, Replica 5개면 83 + 83*5 = 498개로 적용 가능)
* 지원되는 Shading 기법 (0 ~ 16,383)
    - 균등 분배 (Equal distribution) : Shard 수만큼 균등하게 분배
    - 사용자 설정 분배 (Custom distribution) : 원하는 개수 만큼 분배
</br>


## Node
1개의 Primary Node(RW)가 존재하며, 추가로 여러 개의 Replication Node(RO)를 가질 수 있다 
* Primary : Read/Write 모두 가능
* Replication : Only Read 작업만 가능
* Multi AZ 활성화
    - 가용성을 위해 Primary Node와 Replication Node를 서로 다른 Availability Zone에 둘 수 있다
</br>



--
## Install
```

sudo yum install gcc
wget http://download.redis.io/redis-stable.tar.gz
tar xvzf redis-stable.tar.gz
cd redis-stable

# For tls
cd deps
make hiredis jemalloc linenoise lua
cd ..

make distclean      # ubuntu systems only
make BUILD_TLS=yes  # or make


```


--
## Basic
```
set test Hello
(error) NOAUTH Authentication required.

auth test-auth-token12
OK

get test
(nil)

quit
```




---
## Authentication
* Token 인증하는 방식을 사용할 수 있다.
  
    ```
    redis-cli -h <host> -p <port> --tls -a <auth_token>
    ```
    > 전송중 암호화를 활성화한 경우 ```--tls``` 옵션이 꼭 있어야 한다!!