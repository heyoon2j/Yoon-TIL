# Computing
```
[ 실제 물리 서버 (온프레미스 또는 클라우드 인프라) ]
┌────────────────────────────┐
│  CPU 칩 (ex. Xeon 6338)    │
│  ┌────────────────────┐    │
│  │ Core 0 ──┬─ Thread 0 │──┐
│  │          └─ Thread 1 │  │
│  │ Core 1 ──┬─ Thread 0 │──┤ HT (하이퍼스레딩)
│  │          └─ Thread 1 │  │
│  └────────────────────┘    │
└────────────────────────────┘
          │
          ▼
   [ Hypervisor (KVM, VMware 등) ]
          │
  ┌───────┼────────┬────────┬────────┐
  ▼       ▼        ▼        ▼
[vCPU0] [vCPU1]  [vCPU2]  [vCPU3]   ← VM에 할당되는 가상 CPU
  │       │        │        │
 VM1     VM1      VM2      VM3

```
- CPU: 컴퓨터에 장착된 물리 칩 전체 / 하드웨어
- Core: CPU 내의 연산 유닛 / 하드웨어
- Hyper-Threading(HT): Core를 2개의 Thread로 논리적으로 분리시킴
- vCPU: Hypervisor가 인식하는 논리 코어 단위(=Thread). 즉, Hypervisor는 물리적인 Thread(or Core)를 CPU로 인식시켜서 동작시킨다.
    > pCPU: Hypervisor가 인식하는 물리 코어 단위(=Core)
    - 예시> Spec: CPU 4Core (HT On - 2 Thread per 1 Core)
        - 계산: 4Core == 8Thread == 8vCPU



# Storage
## 구성요소 (Component)
```
[ Application (DB, 로그 분석기, 유저 요청 등) ]
                    │
                    ▼
              [ File (논리 단위) ]
                    │
                    ▼
        ┌────────────┴────────────┐
        ▼                         ▼
[ File System (ext4, xfs 등) ]   (메타데이터 관리)
        │                         │
        │                     ┌───┴────┐
        │                     │ Inode │ (파일의 속성과 위치 정보)
        │                     └───┬────┘
        │                     ┌───┴────┐
        │                     │ Extent│ (연속 블록 묶음 단위)
        │                     └───┬────┘
        ▼                         ▼
          [ Block ]  (일반적으로 4KB, 실제 디스크 I/O 단위)
                    │
                    ▼
          [ Sector ] (물리 저장 단위, 보통 512B 또는 4KB)
                    ▼
             [ Physical Disk ]

```
- File: 프로그램이 다루는 논리적 데이터 단위
- File System: Storage에 File과 Directory를 i-node/extent 형태로 구조화해서 저장하는 방식
    - i-node: File의 Metadata를 담고 있는 파일 시스템 구조체
    - Extent: 연속 Block 묶음
- Block: File System이 Storage에 저장하는 최소 단위
- Sector: 디스크의 가장 작은 단위 (Block은 여러 Sector로 구성되어 있음)
    > 물리적인 단위


## 기본 구조
"파일을 읽는다/쓴다"라는 의미는 다음과 같다.
- i-node에서 필요한 extent로 묶여있는 Block들의 주소를 읽는다 (논리적으로 묶여있기 때문에 연속된 Block들의 주소들을 계산이 필요없다(효율, 속도 up))
    > 이때, 조각화 등으로 extent가 많이 분산되어 있을 수 있다(Random I/O). 이러면 속도 저하가 발생할 수 있다.
- Block들을 확인하고 Sector에 접근한다


## Performance
- Storage의 성능을 얘기할때 일반적으로 "얼마나 빠르게", "얼마나 많이" Read/Write 할 수 있는지로 결정된다.
- 성능 지표
    - IOPS: "얼마나 빠르게 찾아서 입력/출력 할 수 있냐". 탐색·요청 처리 능력
    - Throughput: "얼마나 많이 입력/출력 할 수 있냐". 데이터 전송량
    > 성능은 얼마나 빠르게 블록 주소에 접근하여(IOPS), 얼마나 많이 처리할 수 있는지(Throughput)로 결정된다.

    > 어떤게 더 중요한게 아니라 워크로드에 따라 더 중요하다일 뿐이다. 양쪽의 대한 밸런스도 중요하다!!
- 예시
    - 로그 파일, 대량 데이터: 많은 양을 읽어야 하는 작업이기 때문에 Throughput 성능이 더 중요
    - DB row 데이터, 작은 단위 데이터: 작은 양을 빠르게 읽어야 하는 작업이기 때문에 IOPS 성능이 더 중요

    
## Random / Sequential
| 구분 | 설명 | 관련 지표 |
|-----|-----|----------|
| Random Read/Write | 디스크의 서로 다른 위치(블록)를 "점프해서" 읽거나 쓰는 작업 | IOPS (많은 요청, 작은 데이터) |
| Sequential Read/Write | 디스크의 연속된 블록을 "쭉 이어서" 읽거나 쓰는 작업 | Throughput (적은 요청, 큰 데이터) |

* 정리
    ```
    파일을 읽거나 쓰려면
    ↓
    디스크 블록을 접근해야 하고
    ↓
    - 블록들이 랜덤이면 → Random I/O → IOPS가 중요
    - 블록들이 연속이면 → Sequential I/O → Throughput이 중요
    ```