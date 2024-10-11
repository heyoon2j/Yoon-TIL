# Backup
* 자료를 보호하는 방법 중 하나로 복사본을 만들어 놓고, 장애 발생 시 대체
* 고려 사항
    1) 백업 대상 : 쉽게 설치 가능하거나, 자동으로 생성되는 데이터들은 백업이 불필요
    2) 






```
mkdir /data2
cd /data
find . -depth -print | cpio -pamd /data2
mv /data /data_bak
vi /etc/fstab
    # dat2 -> data mount 
```