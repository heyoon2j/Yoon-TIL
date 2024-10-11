# Network File System

## NFS Server
## NFS Client
0. NFS Utils 설치
    * Amazon Linux2
        ```
        $ sudo yum install -y amazon-efs-utils
        ```
    * CentOS
        ```
        $ sudo yum install -y nfs-utils
        ```
1. Mount
	```
    # $ mount -t nfs [SERVER]:[SHARED_DIR] [MOUNT_DIR]
    $ mount -t nfs 10.10.0.1:/etc/local/httpd /shared
	```
</br>

2. fstab
	* 시스템 재부팅 후에도 볼륨을 자동으로 탑재하기 위해서는 ```/etc/fstab``` 파일에 추가해야 한다.
        ``` 
        $ vi /etc/fstab

        # <file system>  <mount point>  <type>  <options>  <dump>  <pass>
        
        10.20.3.86:/data    /shared nfs4    nfsvers=4.1,rsize=1048576,wsize=1048576,hard,timeo=600,retrans=2,noresvport 0   0

        10.30.192.251:/s3-bi-prd-an2-etl	/shared	nfs	nolock,hard	0	0

        abc.test.com:/  /test   nfs4    nfsvers=4.1,defaults,rsize=1048576,wsize=1048576,hard,timeo=600,retrans=2,noresvport,nofail  0    0

        $ mount -a
        ```
        - file system : 연결시킬 대상 위치
        - mount point : 해당 서버의 마운트 위치
        - type : 파일 시스템 타입
        - options : 마운트 옵션
        - dump : 백업 유틸리티('dump')를 사용하여 해당 파일 시스템을 백업할 수 있는지 여부 / 0 (백업 뷸필요), 1 (백업 필요)
            > 그렇다고 실제로 dump 명령어를 막지는 않고, 경고만 한다. 즉, 단순히 정보 제공이나 문서화 목적으로 사용된다. (구분을 위해 사용되는 용도이지 않나 싶다)
        - pass : 부팅 시 파일 시스템 검사 순서로, 디스크의 파일 시스템에 손상이 있는지 확인하고 필요할 경우 복구하는 유틸리티 / 0(검사 안 함), 1(루트 파일 시스템, 가장 우선), 2 이상 (루트 이외의 파일 시스템)
            - 파일 시스템의 구조적 무결성 검사 (디렉토리 구조, 파일 블록, 링크 등)
            - 손상된 데이터 블록 및 메타데이터 복구
            - 잘못된 파일 권한 또는 소유자 정보 수정
	* fstab 마운트 옵션
		| Filed 이름 | 내용 | 비 고 |
		|------------|------|------|
        | nfsvers | NFS version 정보 | 4.1 : Linux </br>4.0 : MacOS |
        | rsize | read 요청에 대해 NFS Client가 수신할 수 있는 최대 바이트 수 | 최대값 1048576 (== 1 MB) |
        | wsize | write 요청에 대해 NFS Client가 전송할 수 있는 최대 바이트 수 | 최대값 1048576 (== 1 MB) |
        | hard | NFS Client의 요청에 대한 timeout이 발생했을 때, 복구 동작으로써 서버가 응답할 때까지 무기한 재시도를 한다 | 데이터 무결성을 위해 설정하는 것이 좋으며, 설정하지 않는 경우 timeo를 15초 이상 설정하는 것이 좋다 |
        | timeo | NFS Client 요청에 대한 timeout 값 | 기본값 600 (== 60s) |
        | retrans | 복구 동작이 실행되기 전까지 NFS Client의 요청 재시도 횟수 |  |
        | noresvport | 연결이 끊긴 경우, NFS 클라이언트가 새로운 TCP 소스 포트를 사용하여 연결을 시도한다. 설정하지 않으면 동일한 TCP 소스 포트를 사용하여 시도한다(TCP RFC를 준수하지 않음) | 재연결 또는 네트워크 복구와 같은 이벤트 후에도 투명하게 연결을 시도할 수 있다 |
        |  |  |
        | mountport |  |  |
        | root_squash | root 계정을 --> 서버의 nobody 계정으로 매핑 |  |
        | no_root_squash | root 계정은 --> 서버의 root 계정 그대로 매핑 |  |
        | all_squash | 모든 계정을 --> 서버의 nobody 계정으로 매핑 |  |

        |  |  |  |




| defaults: 일반적인 기본 옵션. rw, suid, dev, exec, auto, nouser, async를 포함합니다.
| ro | 읽기 전용 마운트 |  |
| rw | 읽기 및 쓰기 가능 마운트|  |
| noauto| 시스템 부팅 시 자동으로 마운트하지 않음 (수동) |  |
| auto | 시스템 부팅 시 자동으로 마운트 |  |
| user | 일반 사용자가 파일 시스템을 마운트할 수 있도록 허용 |
| nouser | 루트 사용자만 마운트할 수 있습니다(기본값) |
| exec | 파일 시스템에서 실행 파일을 실행할 수 있도록 허용 |
| noexec: 파일 시스템에서 실행 파일의 실행을 방지합니다
| suid | setuid 및 setgid 비트 허용 |
| nosuid | setuid 및 setgid 비트를 무시 |
| dev | 장치 파일(/dev)을 해석합니다 |
| nodev | 장치 파일을 해석하지 않습니다 |
| sync | 모든 I/O를 동기적으로 수행합니다 |
| async | 모든 I/O를 비동기적으로 수행합니다 |


1.2. NFS 전용 옵션
nfsvers=X: 사용할 NFS 프로토콜 버전을 설정합니다. 예: nfsvers=4
rsize=X: NFS 읽기 요청의 크기(바이트)를 지정합니다.
wsize=X: NFS 쓰기 요청의 크기(바이트)를 지정합니다.
timeo=X: 서버 응답 시간 초과를 설정합니다. (기본값은 0.1초)
retrans=X: 서버에 재전송 시도 횟수를 지정합니다.
soft: 서버 응답이 없을 때 클라이언트가 오류를 반환하도록 설정합니다.
hard: 서버 응답이 없을 때 클라이언트가 무기한 대기하도록 설정합니다.
bg: 마운트에 실패할 경우 백그라운드에서 재시도합니다.
intr: NFS 요청이 중단 가능하도록 설정합니다.
nolock: 파일 잠금을 사용하지 않습니다.


1.3. 다른 파일 시스템 및 보안 옵션
| relatime: 접근 시간(atime)을 업데이트하지만, 수정 또는 변경 시간이 이후인 경우에만 업데이트합니다.
| noatime: 파일에 접근할 때 접근 시간(atime)을 기록하지 않습니다. 파일 시스템 성능이 향상될 수 있습니다.
| nodiratime: 디렉토리에 대한 접근 시간을 기록하지 않습니다.
| errors=remount-ro: 파일 시스템에 오류가 발생하면 읽기 전용으로 다시 마운트합니다.
| discard: 파일이 삭제될 때, 파일 시스템에서 TRIM 명령을 발행하여 SSD의 불필요한 블록을 제거합니다.
| barrier=0 | 데이터 손실의 위험이 있지만, 성능을 위해 디스크 장벽을 사용하지 않도록 설정 |



부팅 관련 옵션
| nofail | 마운트가 실패해도 부팅이 중단되지 않고, 시스템이 계속 부팅할 수 있게 보장 |
| x-systemd.automount | 마운트를 지연하여 처음 액세스할 때까지 기다렸다가 마운트. 시스템 부팅 시간을 단축시킬 수 있음 |
| x-systemd.requires-mounts-for | 지정된 마운트 포인트가 필요할 때까지 현재 파일 시스템을 마운트하지 않음 |

기타 옵션
|quota / usrquota / grpquota | 파일 시스템에 사용자 또는 그룹의 디스크 사용량 제한을 설정 |
| noquota | 디스크 사용량 제한을 적용하지 않음 |
| loop | 디스크 이미지 파일을 마운트할 때 사용 |