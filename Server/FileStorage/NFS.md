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

	# $ mount -t nfs [SERVER]:[SHARED_DIR] [MOUNT_DIR]
    $ mount -t nfs 10.10.0.1:/etc/local/httpd /shared
	```
</br>

2. fstab
	* 시스템 재부팅 후에도 볼륨을 자동으로 탑재하기 위해서는 ```/etc/fstab``` 파일에 추가해야 한다.
	* fstab 설정 정보
		| Filed 이름 | 내용 | 비 고 |
		|------------|------|------|