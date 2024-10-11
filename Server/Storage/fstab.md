# fstab

* 시스템 재부팅 후에도 볼륨을 자동으로 탑재하기 위해서는 ```/etc/fstab``` 파일에 추가해야 한다.
* fstab 설정 정보
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
``` 
$ vi /etc/fstab

#[SERVER]:[SHARED_DIR]   [MOUNT_DIR] [FILE_SYS]  [OPTION]
10.20.3.86:/data    /shared nfs4    nfsvers=4.1,rsize=1048576,wsize=1048576,hard,timeo=600,retrans=2,noresvport 0   0

10.30.192.251:/s3-bi-prd-an2-etl	/shared	nfs	nolock,hard	0	0

$ mount -a
```

## fstab Option
``` 
$ vi /etc/fstab

#[SERVER]:[SHARED_DIR]   [MOUNT_DIR] [FILE_SYS]  [OPTION] [DUMP] [FS_CHK]
10.20.3.86:/data    /shared nfs4    nfsvers=4.1,rsize=1048576,wsize=1048576,hard,timeo=600,retrans=2,noresvport 0   0

10.30.192.251:/s3-bi-prd-an2-etl	/shared	nfs	nolock,hard	0	0

$ mount -a
```
* OPTION : 마운트 속성 옵션
* DUMP : 
* FS_CHK : File System 점검 여부
    - 0 : 부팅 시, 파일 시스템을 점검하지 않는다
    - 1 : Root File System에 대하여 무결성 검사 수행 (Root 볼륨) 
    - 2 : Root File System 이외의 파일 시스템에 대하여 무결성 검사 수행 (나머지 볼륨)
</br>

### File System 속성

| Filed 이름 | 내용 | 비 고 |
|------------|------|------|
| defaults | rw, nouser, auto, exec, suid 속성을 가짐 |  |
| auto | 부팅 시, 자동 마운트 |  |
| noauto | 부팅 시, 자동 마운트 X |  |
| exec | 실행 파일이 실행되는 것을 허용 |  |
| noexec | 실행 파일이 실행되는 것을 허용 X |  |
| sid | SetUID / SetGID 허용 |  |
| nosid | SetUID / SetGID 허용 X |  |
| ro | Read Only, 읽기 전용 |  |
| rw | Read Write, 읽기 쓰기 모두 허용 |  |
| user | 일반 사용자도 마운트할 수 있다 |  |
| nouser | 일반 사용자는 마운트할 수 없다 |  |
| usrquota | 사용자별 디스크 용량 제한 |  |
| grpquota | 그룹별 디스크 용량 제한 |  |
| nofail | 마운트 실패 시, 오류를 보고 하지 않음 |  |
| noatime | 파일을 읽을 때 access time을 업데이트하지 않음 | 속도 향상 |
| relatime | 파일을 읽을 때 필요한 경우만 access time 업데이트 | 속도 향상 |

| root_squash | root 계정을 --> 서버의 nobody 계정으로 매핑 |  |
| no_root_squash | root 계정은 --> 서버의 root 계정 그대로 매핑 |  |
| all_squash | 모든 계정을 --> 서버의 nobody 계정으로 매핑 |  |

* Example
    ```

    ```
</br>

### NFS 옵션

| Filed 이름 | 내용 | 비 고 |
|------------|------|------|
| nfsvers | NFS version 정보 | 4.1 : Linux </br>4.0 : MacOS |
| rsize | read 요청에 대해 NFS Client가 수신할 수 있는 최대 바이트 수 | 최대값 1048576 (== 1 MB) |
| wsize | write 요청에 대해 NFS Client가 전송할 수 있는 최대 바이트 수 | 최대값 1048576 (== 1 MB) |
| hard | NFS Client의 요청에 대한 timeout이 발생했을 때, 복구 동작으로써 서버가 응답할 때까지 무기한 재시도를 한다 | 데이터 무결성을 위해 설정하는 것이 좋으며, 설정하지 않는 경우 timeo를 15초 이상 설정하는 것이 좋다 |
| timeo | NFS Client 요청에 대한 timeout 값 | 기본값 600 (== 60s) |
| retrans | 복구 동작이 실행되기 전까지 NFS Client의 요청 재시도 횟수 |  |
| noresvport | 연결이 끊긴 경우, NFS 클라이언트가 새로운 TCP 소스 포트를 사용하여 연결을 시도한다. 설정하지 않으면 동일한 TCP 소스 포트를 사용하여 시도한다(TCP RFC를 준수하지 않음) | 재연결 또는 네트워크 복구와 같은 이벤트 후에도 투명하게 연결을 시도할 수 있다 |
| mountport | mountd 포트 번호 지정 | 굳이 사용? |
| _ nolock _ | 파일 잠금 비활성화 | NFS 4부터 Deprecated |
|  |  |  |
|  |  |  |
|  |  |  |

* Example
    ```
    # NFS / AWS EFS
    10.20.3.86:/data    /shared nfs4    nfsvers=4.1,rsize=1048576,wsize=1048576,hard,timeo=600,retrans=2,noresvport 0   0

    # AWS StorageGateway for S3
    10.20.3.67:/s3-test   /sdata nfs  nolock,hard 0   0
    ```