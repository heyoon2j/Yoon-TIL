# Partition 하는 방법
* 크게 명령어는 ```fdisk```와 ```parted```가 있다.
* 기본적인 순서는 다음과 같다.
    1) 파티션 생성
    2) 파일 시스템 설정
    3) 마운트 지정
    4) LVM 생성
    5) PV 초기화
    6) VG 생성
    7) LV 생성
</br>

## fdisk
* https://yoonix.tistory.com/24
</br>
</br>


## parted
* 

1. 파티션 생성
    ```
    ```
2. a
    ```
    ```
3. b
    ```
    ```
4. c
    ```
    ```
5. d
    ```
    ```
6. e
    ```
    ```
7. f
    ```
    ```

</br>
</br>

> 2048s로 하는 기본적인 이유는 Winodws와 호환을 위해서이다. 사실 Linux는 상관없는 것으로 안다(아마도)!!! 과거 Windows에서 Partition의 시작은 63 Sector에서 시작한다. 기존 512 Byte를 1 Sector로 계산했을 때는 괜찮았지만, 4096 Byte(또는 8 KB)로 커진 디스크에서 계산을 하면 첫 번째 파티션닝 주소 계산이 맞지 않는 경우가 발생하여 이를 위해 안정적으로 1MB를 경계로 정렬하는 것을 권장하고 있다.
>  결론적인거는 호환 이런거 생각안하면 생각할 필요가 없긴하지만, 기본적으로 MBR(0 Sec)과 Protective MBR(0~33) 생각해야 된다.
* Reference
    * https://joungkyun.gitbook.io/annyung-3-user-guide/annyung3-white-paper/wp-partition-alignment
    * https://www.thomas-krenn.com/en/wiki/Partition_Alignment_detailed_explanation