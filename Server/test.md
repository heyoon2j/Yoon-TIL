```
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