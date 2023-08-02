# Booting

## Linux Booting Sequence
* BIOS : Basic Input/Output System. 컴퓨터의 입출력을 처리하는 펌웨어
* ROM : Read Only Memory
1. Power On
    * 메인보드에 전원 공급
    * 메인보드의 ROM을 통해 특정 번지에 있는 BIOS를 실행하도록 한다.
2. Execute BIOS Program
    * 자체 진단 기능 실행 (POST, Power On Self Test)
    * CMOS, CPU, Memory, 주변장치 등 각종 장치의 이상 유무를 검사
3. Boot Loader 실행 (Linux : GRUP)
    1) MBR(Master Boot Record) 또는 VBR(Volume Boot Record) 로드 후 grup 실행 (이때 bios_grup 파티션이 있다면 해당 파티션으로 접근한다)
    2) /boot/grup에 접근하여 커널(vmlinuz)관련 파일을 메모리에 로드한다
4. Loading Kernel
    1) 커널 파일 실행
    2) PCI Bus 및 하드웨어 점검 후, /var/log/dmesg 기록
    3) Process ID(PID) 0인 swapper 프로세스를 실행하여 각 장치 드라이버 초기화(ps 명령어에 안보임 Memory context switching)
    4) 초기화 완료 후, PID 1인 init 프로세스 실행(systemd)
5. File System Mount
    * /etc/fstab에 지정된대로 마운트 진행
    1) Root File System("/") 마운트
    2) 그 외 다른 파일 시스템 마운트
6. init 프로세스에 따라 순서대로 시스템 초기화 (/usr/lib/systemd/systemd)
    * /etc/rc.d/init.d 실행
    * systemd-journald 실행 (서비스 로그 저장)
    * 서비스 실행
> Ref : https://lilo.tistory.com/21 / https://yonlog.tistory.com/59