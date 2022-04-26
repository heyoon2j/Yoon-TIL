```
# 파티션 제거
$ sudo lsblk
=> Device 정보 복사 ex> /dev/sda

$ sudo fdisk
> p
> d
> w
=> 파티션 분리하지 않을거기 때문에 엔터 누르시면 디폴트로 전부 선택되어 삭제될겁니다.
=> w 저장


# 파티션 생성
$ sudo lsblk
=> Device 정보 복사 ex> /dev/sda

$ sudo fdisk
> p
> n
> w
=> 파티션 분리하지 않을거기 때문에 엔터 누르시면 디폴트로 전체 선택되어 생성 될겁니다.


--------------------------------------------------------------------------


# Copy 작업

$ sudo mkdir /data2
$ cd /data
$ sudo find . -depth -print | cpio -pdmv /data2

----------------------------------------------------

# Umount 작업
$ sudo umount /data

----------------------------------------------------

# Mount 작업
$ sudo blkid
=> UUID Copy

$ sudo vi /etc/fastb
=>UUID=abcdef12345test	/data2	ext4	defaults	0	0

$ sudo mount -a

---------------------------------------------------------------------------------------------

# 기존 데이터 삭제
$ sudo rm -r /data
```