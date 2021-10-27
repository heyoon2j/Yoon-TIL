# MBR & GPT 구조


## MBR (Master Boot Record)
* MBR은 저장 장치의 부팅 정보, 파티션 정보 등을 저장한다.
* 저장 장치의 첫 번째 Sector(0 Sector)는 MBR이다.
    * Sector : 저장 장치는 데이터를 찾아가기 위해 Sector란 구조로 주소화되어 있으며, 크기는 512 byte로 구성되어 있다.
* BIOS를 사용하여 부팅하며, BIOS가 부팅 장치를 감지하면 해당 장치의 첫 번째 디스크 블록을 메모리로 읽어들인다.
* 파티션은 주 파티션 최대 4개 또는 주 파티션 최대 3개 + 확장 파티션 최대 1개로 총 4개의 파티션으로 분리할 수 있다.
</br>

### MBR 구조
1. Boot Strap Code : 440Byte로 구성. OS를 부팅하기 위해 부팅 파티션을 찾는 부분
2. Partition Table Entry : 64byte(=16byte*4)로 구성되어 있으며, 파티션의 정보가 저장되어 있다(각 파티션 정보는 16Byte로 구성)
    * Starting LBA Address : 4byte(=32Bit). LBA의 시작주소로 실제 파티션의 시작 위치.
3. Signature : 해당 Sector의 오류 유무를 확인하기 위한 값(기본 값: 0xAA55)
* https://gedor.tistory.com/3
* https://blog.naver.com/PostView.nhn?blogId=leekh8412&logNo=10013240650
* 파티션의 주소는 32bit를 사용하므로, MBR이 인식할 수 있는 __각 파티션의 크기는 최대 2TB로 제한된다(2^23-1)__
</br>


### 장단점
* __장점__
    * 대부분의 시스템과 호환이 된다.
* __단점__
    * 4개의 파티션만 허용되며, 크기를 최대 2TB로 제한된다.
    * MBR은 0 Sector에만 저장되므로, 해당 Sector가 손상되면 전체 하드 디스크를 읽을 수 없게 된다.
</br>
</br>


## GPT
* GPT는 저장 장치의 부팅 정보, 파티션 정보 등을 저장한다.
* 저장 장치의 첫 번째 Sector(0 Sector)는 MBR이다.
    * Sector : 저장 장치는 데이터를 찾아가기 위해 Sector란 구조로 주소화되어 있으며, 크기는 512 byte로 구성되어 있다.
* BIOS를 사용하여 부팅하며, BIOS가 부팅 장치를 감지하면 해당 장치의 첫 번째 디스크 블록을 메모리로 읽어들인다.
* 파티션은 주 파티션 최대 4개 또는 주 파티션 최대 3개 + 확장 파티션 최대 1개로 총 4개의 파티션으로 분리할 수 있다.



### GPT 구조
1. Boot Strap Code : 440Byte로 구성. OS를 부팅하기 위해 부팅 파티션을 찾는 부분
2. Partition Table Entry : 64byte(=16byte*4)로 구성되어 있으며, 파티션의 정보가 저장되어 있다(각 파티션 정보는 16Byte로 구성)
    * Starting LBA Address : 4byte(=32Bit). LBA의 시작주소로 실제 파티션의 시작 위치.
3. Signature : 해당 Sector의 오류 유무를 확인하기 위한 값(기본 값: 0xAA55)
* https://gedor.tistory.com/3
* https://blog.naver.com/PostView.nhn?blogId=leekh8412&logNo=10013240650
* 파티션의 주소는 32bit를 사용하므로, MBR이 인식할 수 있는 __각 파티션의 크기는 최대 2TB로 제한된다(2^23-1)__
</br>


### 장단점
* __장점__
    * 대부분의 시스템과 호환이 된다.
* __단점__
    * 4개의 파티션만 허용되며, 크기를 최대 2TB로 제한된다.
    * MBR은 0 Sector에만 저장되므로, 해당 Sector가 손상되면 전체 하드 디스크를 읽을 수 없게 된다.
</br>
</br>


