# DHCP Procotol (Dynamic Host Configuration Protocol)
Host의 IP 주소와 TCP/IP 기본 설정을 자동적으로 제공해주는 프로토콜. 
</br>


## 동작 과정
IP 주소 할당 과정은 다음과 같다.
1. DHCP Discover
    * DHCP 서버를 찾기 위해 DHCP Broadcasting을 보낸다.
2. DHCP Offer
    * Broadcasting을 받은 서버는 Host에게 응답해준다.
3. DHCP Request
    * Host는 DHCP에게 IP와 네트워크 정보를 요청한다.
4. DHCP Ack
    * Request에 대한 응답을 보낸다.
</br>
</br>

---
## DHCP Option
DHCP가 추가적으로 전달하는 네트워크 정보는 다음과 같다(아래에는 몇가지만 있다, 자세한 내용은 찾아보는걸로)
* Gateway IP
* Subnet Mask
* Domain name server IP
* Domain name
</br>
</br>

---
## IP 주소 할당 방식
1. 동적 할당(Dynamic Allocation)
    * 임시 기간 동안 IP를 할당해주고, 끝나면 회수해가는 방식.
2. 자동 할당(Automatic Allocation)
    * DHCP 서버에서 정의된 규칙에 따라 IP주소를 영구적으로 할당하는 방식.
3. 수동 할당(Manual Allocation)
    * 관리자가 IP 주소를 수동을 수동으로 할당하는 방식.
</br>
</br>



### Reference
https://jwprogramming.tistory.com/35




