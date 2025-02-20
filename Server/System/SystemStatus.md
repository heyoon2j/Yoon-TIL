## top
* OS 상태를 나타내주는 명령어

```
Processes: 608 total, 2 running, 1 stuck, 605 sleeping, 3018 threads                                                        16:44:32
Load Avg: 1.77, 2.07, 1.91  CPU usage: 7.42% user, 4.22% sys, 88.34% idle  SharedLibs: 654M resident, 121M data, 43M linkedit.
MemRegions: 489825 total, 5229M resident, 341M private, 2106M shared. PhysMem: 15G used (2092M wired, 2520M compressor), 365M unused.
VM: 268T vsize, 4171M framework vsize, 1049476(0) swapins, 1440347(0) swapouts. Networks: packets: 27451869/27G in, 11181970/4891M out.
Disks: 24977108/503G read, 21328563/321G written.
```
* system time / uptime / user sessions : 시스템 현재 시간 / OS가 살아있는 시간 / 현재 접속힌 유저 수 
* Tasks
    * total : 전체 프로세스
    * running : 실행 중인 프로세스
    * sleeping : 대기 중인 프로세스 (다음 I/O or event 대기)
    * stooped : 종료된 프로세스
    * zombie : 좀비 프로세스 (프로세스는 종료되어 있지만, wait() 함수를 호출하지 않아 프로세트 테이블에 남아있는 프로세스. 강제 종료를 하지 않으면, 리소스를 사용하지 않지만 계속 PID를 가지고 있다.)
* CPU
    * us : 유저 영역에서 사용중인 CPU 사용률
    * sy : 커널 영역에서 사용중인 CPU 사용률
    * ni : 프로세스의 우선순위(priority) 설정에 사용되는 CPU 사용률
    * id : 사용하고 있지 않는 CPU 비율
    * wa : I/O가 완료될 때까지 기다리고 있는 CPU 비율
    * hi : 하드웨어 인터럽트에 사용되는 CPU 사용률
    * si : 소프트웨어 인터럽트에 사용되는 CPU 사용률
    * st : CPU를 VM에서 사용하여 대기하는 CPU 비율
* Memory
    * total : 총 메모리
    * free : 사용 가능한 메모리
    * used : 사용중인 메모리
    * buff/cache : 버퍼/캐시에 사용할 수 있는 메모리
* PID : 프로세스 ID
* USER : 프로세스를 실행시킨 사용자 ID
* PR : 프로세스 우선순위 (priority)
* NI : nice value 값. 낮을수록 우선순위가 높음.
* VIRT : 가상 메모리의 사용량 (swap + RES)
* RES : 물리 메모리의 사용량. 현재 페이지가 상주하고 있는 크기(resident size)
* SHR : VIRT 중 공유 메모리의 사용량
* S : 프로세스의 상태 / S(sleeping), R(running), W(swapped out process), Z(zombies)
* %CPU : 프로세스가 사용하는 CPU의 사용률
* %MEM : 프로세스가 사용하는 메모리의 사용률
* COMMAND : 실행된 명령어 
</br>
</br>



---
## free
* Memory 상태를 나타내주는 명령어
* total : 총 메모리 크기
* used : total에서 free, buff/cache를 제외하고 사용중인 메모리
* free : total에서 used, buff/cahce를 제외하고 사용 가능한 메모리
* shared : 여러 프로세스에서 공유되는 메모리
* buff/cache : 버퍼/캐시 영역에서 사용하는 메모리
    * buffers : 커널 버퍼(Buffer Cache)에서 사용중인 메모리. 블록 디바이스의 특정 블록의 접근 시 필요한 정보를 저장하는 캐싱 메모리 영역(i-node 정보 등 파일 시스템의 메타 데이터).
    > 요즘에는 Page Cache 안에 들어갔다!!
    * cache : Page Cache와 Slab으로 사용중인 메모리. Page Cache는 파일 내용에 대한 Read/Write 할 때, 해당 내용을 저장하는 캐싱 메모리 영역. Slab은 리눅스 커널 내부적으로 사용하는 캐시
    > https://brunch.co.kr/@alden/25
    > https://brunch.co.kr/@dreaminz/2
* available : swapping 없이 새로운 프로세스에서 할당 가능한 메모리의 예상 크기. (예전의 -/+ buffers/cache이 사라지고 새로 생긴 컬럼)

### 메모리 계산
* total = 실제 메모리 사용량 + free + buff/cache
* 실제 free = free + buff/cache (slab 영역은 리눅스 커널에서 사용하고 있으므로 완전하게 free라고 보기는 어렵다!!)
* 실제 메모리 사용량 = total - (free + buff/cache)


---
## 명령어 정리
```
# CPU 확인
$ grep 'physical id' /proc/cpuinfo | sort -u | wc -l    # 물리 CPU 갯수
$ grep 'cpu cores' /proc/cpuinfo | uniq     # CPU 코어 수
$ grep processor /proc/cpuinfo | wc -l      # vCPU 수

# 메모리 확인
$ free -h -g
$ cat /proc/meminfo
```