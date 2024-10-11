# Bit
컴퓨터 데이터를 나타내는 가장 최소 단위로 0 또는 1을 가질 수 있다. 
</br>

## MSB / LSB
* MSB : Most Significant Bit. 최상위 비트
* LSB : Less Significant Bit. 최하위 비트



---
# Endian
메모리에 바이트를 배열하는 방법을 엔디안(Endian)이라고 하며, 3가지 Endian이 있다.
* Big-endian
* Little-endian
* Middle-endian

## Big-endian
큰 단위가 앞에 오도록 배열하는 방법(=최상위 바이트부터 메모리에 순차적으로 배열) 
> 일반적으로 사람들이 숫자를 쓰는 형태로 그대로 저장된다.

## Little-endian
작은 단위가 앞에 오도록 배열하는 방법(=최하위 바이트부터 메모리에 순차적으로 배열)

## 비교
* Little-endian은 읽기는 어렵지만 연산을 하는 경우에 쉽고, 빠르다(연산은 작은 수부터 큰수로 처리하기 때문에)
* Big-endian은 가장 큰수부터 저장하기 때문에 숫자 비교를 할 때 빠르다(숫자 비교는 가장 큰수부터 비교하기 때문에)
</br>
</br>


---





