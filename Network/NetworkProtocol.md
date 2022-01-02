# Network Protocol

## VXLAN (Virtual Extensible LAN)
* Physical Network 위에 가상 네트워크를 사용하기 위한 Overlay Network를 구현하는데 사용되는 Protocol 중 하나.
* VXLAN은 Tunneling 기반으로 하는 기법이다. 가상 네트워크 안에서 발생한 Packet은 Encapsulation 되어 Physical Network
* 
* 
</br>

### 용어
* VTEP(VXLAN Tunnel End Point)
    * VXLAN Tunnel 간의 Encaptulation/Decapsulation 역할 수행
* VNI(Virtual Network Idenfitier)
    * 
* NVE(Network Virtualization Interface)
    * 
* VXLAN Segment
    * 
* VXLAN Gateway
    * 
</br>


### VLAN vs VXLAN
| List          | VLAN                | VXLAN                 |
| ------------- | ------------------- | --------------------- |
| Network Layer | Layer 2             | Layer 3               |
| ID bit        | 12bit / 최대 4096개 | 24bit / 최대 1600만개 |
|               |                     |                       |
|               |                     |                       |
|               |                     |                       |
|               |                     |                       |
|               |                     |                       |
|               |                     |                       |
|               |                     |                       |
</br>

### Reference
* https://ssup2.github.io/theory_analysis/Overlay_Network_VXLAN/
* 