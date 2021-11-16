# Partition 하는 방법
* 크게 명령어는 ```fdisk```와 ```parted```가 있다.
* 기본적인 순서는 다음과 같다.
    1) 파티션 생성
        * MBR : O Sector는 MBR이므로 __1 Sector 부터 파티션 시작점으로 잡아야 한다.__
        * GPT : O ~ 33 Sector는 GPT이므로 __34 Sector 부터 파티션 시작점으로 잡아야 하며__, Backup을 위한 33 Sector가 필요하므로 __마지막 33 Sector는 남겨서 파티션을 잡아야 한다.__
    2) 파일 시스템 설정
    3) 마운트 지정
    4) LVM 생성
    5) PV 초기화
    6) VG 생성
    7) LV 생성
</br>

## fdisk
0. 사용법
1. 기본 명령어
2. File System Format
	* ```mkfs.FS-TYPE DEVICE``` : 파일 시스템 포맷
	* FS-TYPE => ext3, ext4, fat32, hfs, linusx-swap(v1/v0), ntfs, xfx, apfs2 등
	```
	$ mkfs.ext4 /dev/xvdf1
	```
</br>

3. Mount
    * ```mount DEVICE MOUNT-DIR``` : Directory에 Device를 마운트
	```
	$ mount /dev/xvdf1 /test
	```
</br>

4. fstab
	* 시스템 재부팅 후에도 볼륨을 자동으로 탑재하기 위해서는 ```/etc/fstab``` 파일에 추가해야 한다.
	* fstab 설정 정보
		| Filed 이름 | 내용 | 비 고 |
		|------------|------|------|
		| Filesystem Device Name | 파일 시스템 장치 이름 / UUID |  |
		| Mount Point | 연결할 디렉토리 지정 |  |
		| Filesystem Type | 파일 시스템 종류 | ext2, ext3, ext4, nfs 등 |
		| Mount Option | 파일 시스템 옵션을 지정 | 보통 default 설정 |
		| Dump | 덤프 여부 설정 | 0: 덤프 설정 X </br> 1: 덤프 설정 O |
		| File Sequence Check Option | 파일 점검 옵션 | 0 : fsck 실행 X </br> 1: 루트 파일 시스템 </br> 2: 루트 파일 시스템 이외의 파일 시스템 |
		* 장치 이름은 UUID를 사용하는 것을 권장. 장치 이름은 바뀔 수 있지만, UUID는 파티션 수명이 다할 때까지 유지되기 때문이다.
		* Mount Option은 다음과 같다.
			1) default : rw, exec, nouser, auto, suid 속성을 모두 설정
			2) q
			3) c
			4) d
			5) ef
			6) g
			7) 
	* Example
	```

	```

* https://yoonix.tistory.com/24
* https://docs.aws.amazon.com/ko_kr/AWSEC2/latest/UserGuide/ebs-using-volumes.html
</br>
</br>


## parted
* fdisk는 2TB 이상을 넘겨서 사용할 수 없다.
* 이를 해결하기 위해 새로운 명령어인 parted가 나왔다.
0.  사용법   
	* ```$ parted \[OPTION\] DEVICE```
        
	```
	$ lsblk
	NAME    MAJ:MIN RM  SIZE RO TYPE MOUNTPOINT
	xvda    202:0    0    8G  0 disk
	└─xvda1 202:1    0    8G  0 part /
	xvdf    202:80   0  100G  0 disk
        
	$ parted /dev/xvdf
	```
</br>

1.  기본 명령어
	```
	# print : Partition table 정보 출력
	$ print
	

	# unit UNIT : 용량 단위 변경
	## UNIT => s, B, MB, GB, TB, MiB, GiB 등
	$ unit s


	# mklabel LABEL-TYPE : Disk Label 설정
	## LABEL-TYPE => msdos, gpt, bsd, mac, sun 등 지원
	$ mklabel gpt


	# mkpart PART-TYPE [FS-TYPE] START END : Partition 나누기
	## PART-TYPE => primary, logical, extended
	$ mkpart primary 2048s 50%


	# align-chek TYPE NUMBER : 파티션 정렬 확인 
	## TYPE => min (minimal) / opt (optimal)
	(parted) align-check opt 1
	1 aligned				# 정상적인 경우 aligned
	(parted) align-check opt 2
	2 not aligned				# 비정상적인 경우 not aligned


	# rm NUMBER : 파티션 삭제
	$ rm 1

	```
</br>

2. File System Format
	* ```mkfs.FS-TYPE DEVICE``` : 파일 시스템 포맷
	* FS-TYPE => ext3, ext4, fat32, hfs, linusx-swap(v1/v0), ntfs, xfx, apfs2 등
	```
	$ mkfs.ext4 /dev/xvdf1
	```
</br>

3. Mount
    * ```mount DEVICE MOUNT-DIR``` : Directory에 Device를 마운트
	```
	$ mount /dev/xvdf1 /test
	```
* https://doyaji-bw.tistory.com/33
</br>
</br>

> 2048s로 하는 기본적인 이유는 Winodws와 호환을 위해서이다. 사실 Linux는 상관없는 것으로 안다(아마도)!!! 과거 Windows에서 Partition의 시작은 63 Sector에서 시작한다. 기존 512 Byte를 1 Sector로 계산했을 때는 괜찮았지만, 4096 Byte(또는 8 KB)로 커진 디스크에서 계산을 하면 첫 번째 파티션닝 주소 계산이 맞지 않는 경우가 발생하여 이를 위해 안정적으로 1MB를 경계로 정렬하는 것을 권장하고 있다.
>  결론적인거는 호환 이런거 생각안하면 생각할 필요가 없긴하지만, 기본적으로 MBR(0 Sec)과 Protective MBR(34 Sec) / Backup MBR(33 Sec)을 생각해야 된다.
* Reference
    * https://joungkyun.gitbook.io/annyung-3-user-guide/annyung3-white-paper/wp-partition-alignment
    * https://www.thomas-krenn.com/en/wiki/Partition_Alignment_detailed_explanation