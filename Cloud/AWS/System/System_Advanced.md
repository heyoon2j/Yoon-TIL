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


---
## 📌 PCR(Platform Configuration Register)
- **PCR**은 TPM(Trusted Platform Module) 내부의 레지스터
- 부팅 과정에서 펌웨어, 부트로더, 커널, OS 구성요소 등을 **해시(측정값)**으로 기록하고, 이전 값과 함께 `extend` 연산(누적 해시)으로 갱신한다
- 이전 값과 현재 값을 비교함으로써 무결성을 검사할 수 있다 (시스템이 신뢰할 수 있는 상태인지 확인 가능)

## PCR 번호별 역할 (TPM 2.0 기준)

| PCR 번호 | 주요 측정 대상 | 설명 |
|----------|----------------|------|
| **PCR 0** | Core Root of Trust for Measurement (CRTM) | 펌웨어 초기 실행 코드, BIOS/UEFI 초기화. |
| **PCR 1** | Platform Configuration | CPU 마이크로코드, 펌웨어 설정 등. |
| **PCR 2** | Option ROMs | 확장 카드(네트워크/스토리지 등)의 Option ROM 실행 코드. |
| **PCR 3** | Platform-specific Configuration | 추가 펌웨어/하드웨어 설정 값. |
| **PCR 4** | **Boot Manager / UEFI Application** | OS 로더(shim, grub, bootmgfw.efi 등). 부트로더가 바뀌면 PCR4가 변함. |
| **PCR 5** | OS Loader Config | OS 로더 설정값 (예: grub.cfg, 커널 파라미터). |
| **PCR 6** | State Transitions | ACPI, SMM 전환, 기타 보드 레벨 상태 변화. |
| **PCR 7** | **Secure Boot Policy** | UEFI Secure Boot 변수(PK/KEK/db/dbx), Secure Boot on/off 여부. Secure Boot 키가 갱신되면 PCR7 값이 바뀜. |
| **PCR 8–9** | OS Kernel 및 Drivers | 커널, initrd/initramfs, 초기 드라이버. |
| **PCR 10** | OS-specific | OS 로더 이후 추가 측정(일부 Linux에서는 드라이버 모듈, AppArmor/IMA 정책). |
| **PCR 11–13** | Runtime Config | 런타임 환경, 추가 소프트웨어/정책(IMA/EVM 등). |
| **PCR 14** | Debug | 디버그 관련 상태. |
| **PCR 15** | DRTM (Dynamic Root of Trust for Measurement) | TXT/TPM DRTM 이벤트(Late launch). |

> 💡 주의: 실제 매핑은 **플랫폼·펌웨어·OS 구현**에 따라 다소 차이가 있다.  
> 하지만 **PCR4 = 부트로더, PCR7 = Secure Boot 정책**이라는 점은 거의 공통.


### 🔎 활용 예시
- **TPM Event Log** (`/sys/kernel/security/tpm0/binary_bios_measurements`)를 확인하면 어떤 바이너리/변수가 PCR에 extend 되었는지 볼 수 있음.
- **무결성 검증**
