# ElastiCache

## Shading
파티셔닝(Partitioning)이라고도 부름. 동일한 스키마를 가지고 있는 데이터를 특정 조건에 따라서 각 서버에 분산 저장하는 기법 
* Hash Shading : 특정 Hash 값에 따라 데이터를 분산하여 저장 (ex> id % 4)
* Range Shading : 특정 범위를 지정하여 데이터를 분산하여 저장 (ex> a-n = 0 / m-z = 1 / A-Z = 2)


---
## Shade
Shading을 하는 단위이며, Node들을 하나로 묶는 그룹이다 
* 1개의 Primary Node가 존재하며, 추가로 여러 개의 Replication Node를 가질 수 있다 
* 가용성을 위해 Primary Node 1개 / Replication Node 1개로 구성
* Multi AZ
    * 가용성을 위해 Primary Node와 Replication Node를 서로 다른 Availability Zone에 둘 수 있다
</br>


## Node
Primary(RW)와 Replication(RO) Node가 있다. Node에 캐싱 데이터를 저장한다
* Primary : Read/Write 모두 가능
* Replication : Only Read 작업만 가능
</br>



## Cluster
* 샤드를 여러개 구성하는 경우 Cluster 모드를 활성화시킨다.
* 비활성화 모드에서는 샤드가 1개로 제한
> RW/RO 엔드포인트가 없어지고, Cluster 엔드포인트가 생성되니 설정시 주의 필요