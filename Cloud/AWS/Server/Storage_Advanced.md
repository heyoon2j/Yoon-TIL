# Storage
* Block Storage
* File Storage
* Object Storage
</br>


---
## Block Storage
* 단위
    - Sector : 저장 장치(디스크)에서 데이터를 읽고 쓰는 최소 물리적 단위
        > 현재 최신 저장 장치 Sector는 물리적으로 4096 byte를 요청하고, 내장된 컨트롤러가 논리적으로는 512 byte로 처리한다!! (호환성을 위해)
    - Block : 파일 데이터를 관리하는 논리적 단위
    - Segemnt : 순차적인 Block들의 집합
    - Page : 메모리와 디스크 간 데이터를 교환하는 단위
* Partition type
    - MBR : < 2TiB
    - GPT : =< 2TiB
* 파티션의 시작은 2048 Sector부터 시작한다.
    - 이유는 2048 Sector가 Sector, Page의 사이즈가 512byte 이든 4KiB이든 상관없이 정렬될 수 있는 위치이기 때문이다.
</br>

### File System
- i-node


### Backup
- Backup을 위한 Check List
    - Partition Table Type
    - Partition Type
    - File System Type
    - Disk UUID
    - Backup & Restore 방식
- Backup 방법
    1) Full Backup (전체 백업)
    2) Incremental Backup (증분 백업)
    3) Differential Backup (차등 백업)
- Backup 명령어
    - tar
    - cpio
    - dump
    - dd
- 그 외
    - Backup vs Archive
</br>

### Performance
- Workload
    - Data Access Pattern : Random I/O or Seqential I/O
    - Data Size
    - Write 중심 or Read 중심
- 성능 지표
    - IOPS
    - Thoughput
    - Latency
- 스토리지 타입 설정
    - Block Size
    - Dist Type : HDD / SSD / NVMe SSD
    - File System
    - 그 외 : RAID, 스토리지 계층화
</br>
</br>


---
## File Storage



</br>
</br>


--
## Object Storage

