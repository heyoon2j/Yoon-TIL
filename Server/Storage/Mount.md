# Mount
시스템에 스토리지를 탑재하기(Mount) 위해서는 ```/etc/fstab``` 파일에 내용을 추가해야 한다.

```
$ vi /etc/fstab


# [FILE_SYS] [MOUNT_DIR] [FILE_SYS]  [OPTION] [DUMP] [FS_CHK]

## Block Stroage
UUID=aebf131c-6957-451e-8d34-ec978d9581ae  /data  xfs  defaults,nofail  0  2

## File Storage
10.20.3.86:/data    /shared nfs4    nfsvers=4.1,rsize=1048576,wsize=1048576,hard,timeo=600,retrans=2,noresvport 0   2

## Object Stroage
10.30.192.251:/s3-bi-prd-an2-etl	/shared_obj	nfs	nolock,hard	0	2


# mount -a
$ mount /data
$ mount /shared
$ mount /shared_obj
```
* FILE_SYS : 연결시킬 Storage
* MOUNT_DIR : 마운트 위치
* OPTION : 마운트 옵션
* DUMP : 백업 유틸리티 'dump' 명령어 사용 여부
    - 0: dump 명령을 통한 파일 시스템 덤프 불가
    - 1: dump 명령을 통한 파일 시스템 덤프 가능
    > 대신할 방법이 있다면 0, 없다면 1 하는 것이 좋아 보임 - ex> AWS EBS Snapshot과 같은 대체 백업 방법
* FS_CHK : 부팅시, File System 무결성 점검 여부
    - 0 : 부팅 시, 파일 시스템을 점검하지 않는다
    - 1 : Root File System에 대하여 무결성 검사 수행 (Root 볼륨) 
    - 2 : Root File System 이외의 파일 시스템에 대하여 무결성 검사 수행 (나머지 볼륨)
    > 무결성 : 데이터나 시스템이 의도하지 않은 변경이나 손상 없이 완전성, 정확성, 일관성을 유지하는 특성. 1) 파일 시스템의 구조적 무결성 검사 (디렉토리 구조, 파일 블록, 링크 등), 2) 손상된 데이터 블록 및 메타데이터 복구, 3) 잘못된 파일 권한 또는 소유자 정보 수정

    > 일반적으로 1, 2하면 되고, 대용량인 경우에는 고려해봐야 한다! 부팅 시간이 오래걸림으로

---
## Mount Option
### File System 관련 옵션
| Filed 이름 | 내용 | 비 고 |
|------------|------|------|
| defaults | 기본 옵션 | rw, suid, dev, exec, auto, nouser, async 포함 |
| auto | 부팅 시, 자동 마운트 | defaults |
| noauto | 부팅 시, 자동 마운트 X | 수동 조치 필요 |
| rw | Read Write, 읽기 쓰기 모두 허용 | defaults |
| ro | Read Only, 읽기 전용 |  |
| exec | 실행 파일이 실행되는 것을 허용 | defaults |
| noexec | 실행 파일이 실행되는 것을 허용 X |  |
| sid | SetUID / SetGID 허용 | defaults |
| nosid | SetUID / SetGID 허용 X |  |
| nouser | 루트 사용자만 마운트 가능 | defaults |
| user | 일반 사용자가 마운트할 수 있도록 허용 | |
| dev | 장치 파일(/dev)을 해석합니다 | defaults |
| nodev | 장치 파일을 해석하지 않습니다 | |
| async | 모든 I/O를 비동기적으로 수행합니다 | defaults |
| sync | 모든 I/O를 동기적으로 수행합니다 | |
</br>

### 부팅 관련 옵션
| Filed 이름 | 내용 | 비 고 |
|------------|------|------|
| nofail | 마운트가 실패해도 부팅이 중단되지 않고, 시스템이 계속 부팅할 수 있게 보장 | 중요! 기본적으로 사용 권장  |
| x-systemd.automount | 마운트를 지연하여 처음 액세스할 때까지 기다렸다가 마운트. 시스템 부팅 시간을 단축시킬 수 있음 |
| x-systemd.requires-mounts-for | 지정된 마운트 포인트가 필요할 때까지 현재 파일 시스템을 마운트하지 않음 |
</br>

### 기타 옵션
| Filed 이름 | 내용 | 비 고 |
|------------|------|------|
| quota |  |  |
| noquota | 디스크 사용량 제한을 적용하지 않음 |  |
| usrquota | 사용자별 디스크 용량 제한 |  |
| grpquota | 그룹별 디스크 용량 제한 |  |
| noatime | 파일을 읽을 때 access time을 업데이트하지 않음 | 속도 향상 |
| relatime | 파일을 읽을 때 필요한 경우만 access time 업데이트 | 속도 향상 |
| root_squash | root 계정을 --> 서버의 nobody 계정으로 매핑 |  |
| no_root_squash | root 계정은 --> 서버의  |  |
| loop | 디스크 이미지 파일을 마운트할 때 사용 | | 
</br>

### NFS 옵션
| Filed 이름 | 내용 | 비 고 |
|------------|------|------|
| nfsvers | NFS version 정보 | 4.1 : Linux / 4.0 : MacOS |
| rsize | read 요청에 대해 NFS Client가 수신할 수 있는 최대 바이트 수 | 최대값 1048576 (== 1 MB) |
| wsize | write 요청에 대해 NFS Client가 전송할 수 있는 최대 바이트 수 | 최대값 1048576 (== 1 MB) |
| hard | NFS Client의 요청에 대한 timeout이 발생했을 때, 복구 동작으로써 서버가 응답할 때까지 무기한 재시도를 한다 | 데이터 무결성을 위해 설정하는 것이 좋으며, 설정하지 않는 경우 timeo를 15초 이상 설정하는 것이 좋다 |
| timeo | NFS Client 요청에 대한 timeout 값 | 기본값 600 (== 60s) |
| retrans | 복구 동작이 실행되기 전까지 NFS Client의 요청 재시도 횟수 |  |
| noresvport | 연결이 끊긴 경우, NFS 클라이언트가 새로운 TCP 소스 포트를 사용하여 연결을 시도한다. 설정하지 않으면 동일한 TCP 소스 포트를 사용하여 시도한다(TCP RFC를 준수하지 않음) | 재연결 또는 네트워크 복구와 같은 이벤트 후에도 투명하게 연결을 시도할 수 있다 |
| mountport | mountd 포트 번호 지정 | 굳이 사용? |

> Read만 하는 경우, soft 옵션을 사용한다. hard,nofail을 사용함으로써 무결성을 유지하도록 연결에 실패해도 부팅은 정상적으로 진행하고 연결이 될때까지 재시도를 한다!!
