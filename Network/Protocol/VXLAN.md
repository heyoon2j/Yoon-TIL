# VXLAN (Virtual Extensible LAN)
* Physical Network 위에 Virtual Network를 사용하기 위한 Overlay Network를 구현하는데 사용되는 Protocol 중 하나.
* VXLAN은 Tunneling 기반으로 하는 기법이다. Virtual Network 안에서 발생한 Packet은 Encapsulation 되어 Physical Network을 통과하고 다시 Decapsulation되어 Virtual Network로 전달 된다.
</br>


## 등장 배경
* 기본적인 등장 배경은 Cloud 환경에서는 많은 Address와 유동적으로 변경 가능한 네트워크가 필요하다는 점에서 등장하였다.
* 
* MAC Address 한계
    * 네트워크 장비의 한계로 MAC Address Cache Table 관리의 문제
    > Table 관리를 vSwitch에서 소유하고 해당 Swtich로 Forwading
* VLAN 한계
    * VLAN ID bit는 12 bit로 인해 숫자가 부족해짐
    > VXLAN ID bit는 24 bit로 구성
* 수동적인 구성
    * Zone 별 VLAN Trunk 구성은 수동적이기 때문에 급변하는 Cloud 환경에서는 L2 구성은 불필요한 구성이다.
</br>
</br>


## 용어
* VTEP(VXLAN Tunnel End Point)
    * VXLAN Tunnel 간의 Encapsulation/Decapsulation 작업이 발생하는 지점
    * VTEP는 소프트웨어 장치일 수도 있고, 하드웨어 장치일 수 있다. 
* VNI(Virtual Network Idenfitier)
    * 해당 VNI를 통해 어느 Vritual Network 인지 구분되고, 격리된다.
    * Encapsulation 된 Packet은 VXLAN Header에 저장된다.
* NVE(Network Virtualization Interface)
    * 
* VXLAN Segment
    * 
* VXLAN Gateway
    * 
</br>
</br>


## 통신과정
> 정보를 가지고 유추한 내용이므로 틀린 내용이 많을 것이다! 이후 수정 예정
1. A VM - > A VTEP
    * A VM에서 ARP Request Packet을 VLAN ID와 함께 전송한다.
2. A VTEP
    * A VM 네트워크의 VTEP는 VLAN ID와 VLAN 정보를 저장(설정)하고, VLAN ID를 VXLAN VNI와 매핑한다.
    * VXLAN VNI로 Packet을 Encapsulation한다.
    > VLAN ID는 Hypervisor 내에서 VM 끼리의 통신
    > VXLAN VNI는 Network를 통한 Hypervisor 끼리의 통신
3. A VTEP -> Network(Multicast) -> B VTEP
    * Encapsulation 된 Packet은 다른 VTEP들에게 전달된다.
    * 해당 Packet들의 정보로 Table이 생성된다.
4. B VTEP
    * Encapsulation 된 Packet을 Decapsulation 하여, 다시 ARP Packet으로 변환한다.
    * Packet 정보를 저장한다.
5. B VTEP -> B VM
    * B VM에게 ARP Request Packet을 전송한다.
6. B VM <-> A VM
    * 이후에는 Unicast를 통해 통신한다.
</br>
</br>


## VLAN vs VXLAN
| List          | VLAN                | VXLAN                 |
| ------------- | ------------------- | --------------------- |
| Network Layer | Layer 2             | Layer 3               |
| ID bit        | 12bit / 최대 4096개 | 24bit / 최대 1600만개 |
| Tunneling 방식              | Trunk                    |  Multicast                     |
|               |                     |                       |
</br>
</br>

### Reference
* https://ssup2.github.io/theory_analysis/Overlay_Network_VXLAN/
* https://blog.naver.com/PostView.nhn?blogId=lunaeye&logNo=221160549927
* https://youngmind.tistory.com/entry/Network-Overlay-VXLAN-%EB%B6%84%EC%84%9D-3?category=394664




