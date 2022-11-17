# Volume Resizing

## Resizing 과정
* Resizing 하는 과정은 다음과 같다. 
* https://docs.aws.amazon.com/ko_kr/AWSEC2/latest/UserGuide/recognize-expanded-volume-linux.html
1. Volume 크기 확장
2. resizing 할 대상 장티션 또는 볼륨 확인 
    ```
    # File system Check
    $ df -hT

    # Partion Check
    $ lsblk
    ```
    * df 명령어에서는 크기가 확장된 것을 확인할 수 없다. 그 이유는 File System은 확장된 공간에는 File System이 적용되어 있지 않기 때문이다.
    * 그렇기 때문에 lsblk 명령어로 확인해야 한다.
3. 파티션 확장
    ```
    $ lsblk

    # growpart [device] [partition_number]
    $ growpart /dev/nvme0n1 1
    
    $ lsblk
    ```
    * growpart 명령어 사용

4. 파일 시스템 확장
    ```
    $ df -Th

    # XFS File System인 경우
    $ xfs_growfs -d /
    $ xfs_growfs -d /data

    # EXT4 File System인 경우
    $ resize2fs /dev/nvme0n1p1
    $ resize2fs /dev/nvme1n1
    ```
</br>


## Optimizing 과정
![Optimizing](../img/Optimizing.png)
> File을 i-node를 가지고 있고, i-node 안에는 Data Block 위치 정보가 저장되어 있다.

1. File에 Write가 될 수 있으므로 해당 File을 점유
2. 1번 Data Block을 다른 Block으로 복사
3. File의 i-node에 저장되어 있는 1번 Data Block 위치 정보를 복사한 Block 위치 정보로 변경한다.
4. 1 ~ 3 까지 디스크가 최적화가 완료될 때까지 반복 진행
5. 최적화 완료
</br>

