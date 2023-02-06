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
    * sleeping : 대기 중인 프로세스 (I/O or event 대기)
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
    * buff/cache : 






---
## free
* Memory 상태를 나타내주는 명령어









