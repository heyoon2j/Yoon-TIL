# Networking
* endpoint : Service가 패킷을 전달하고자하는 Pod 집합의 진입점을 Endpoint라고 한다. 해당 Endpoint에는 단일 DNS가 부여된다.
EKS는 AWS에서 제공하는 CNI(Container Network Interface) Plugin을 사용한다!!
* Amazon EKS는 Pod에게 예약된 Worker Node와 동일한 서브넷에서 IP를 할당해준다.
    > 필요시, 
* Basic
    - Architecture
    - Traffic Flow


---
## Architecture
쿠버네티스 네트워크의 구성 핵심 요소는 다음과 같다. 
1) kube-proxy
2) CNI
    - All containers/Pods can communicate to one another without NAT.
    - All nodes can communicate with all containers and vice-versa without NAT. 
    - NAT 구성 없이도 쿠버네티스 내부의 리소스들은 통신이 되어야 한다. 이를 위해 많은 네트워크 플랫폼이 구축되어 있다.

### kube-proxy
주로 서비스 추상화를 구성하고 클러스터 내부 로드 밸런싱 기능을 담당
- L4에서 동작
- Service 레벨의 네트워크 역할 수행
    - Cluster 내부 및 외부에서 서비스로의 네트워크 프록시 역할 수행
    - 서비스에 대한 로드 밸런싱 제공
    - Endpoint 제공!!!
* Mode Type
    - iptables Mode : Chain Rule을 찾아 트래픽 전달 / 시간 복잡도 : O(n)
    - IPVS Mode : Hashing 기반으로 Rule을 찾아 트래픽 전달 / 시간 복잡도 : O(1)
    > https://blog.naver.com/alice_k106/221606077410 / https://techblog.lotteon.com/%EB%A1%AF%EB%8D%B0on-eks-%EC%9A%B4%EC%98%81-tips-8a1cade6a0d5

### CNI (Container Network Interface)
컨테이너 오케스트레이션 플랫폼에서 네트워킹을 관리하기 위한 표준 인터페이스로 L2/L3에서 작동

일부는 솔루션은 L4까지 확장되어 동작

* 구성
    1) CNI Plugin: Container Runtime에서 컨테이너 생성 시 IP 할당, 네트워크 네임스페이스 설정 등을 위해 실행하는 바이너리 실행 파일
    2) CNI Solution: Pod, Node 통신에 대한 네트워크 정책을 관리하는 솔루션 (ex> CNI DaemonSets)
    > 서로 보완하며 쿠버네티스 네트워크를 관리하지만, 서로 통신하지는 않는다!
* CNI List
    - Calico (가장 많이 쓰임, L3 네트워크, BGP, VXLAN, ACL 등 지원)
    - Flannel (가볍게 사용하는 경우 사용, L2 네트워크)
    - Cilium
    - Kub-router
    - Romana
    - WeaveNet
    - Canal
    - Amazon VPC CNI plugin for Kubernetes (AWS용)
* CNI Plugin
    - Container Level 네트워크 설정 (Virtual Interface, Virtual Brdige 등)
    - CNI Plugin Responsibilities:
        - Support arguments ADD/DEL/CHECK
        - Support parameters container id, network ns etc...
        - Manage IP Address assignment to PODs
            - /etc/cni/net.d/10-bridge.conf 설정에서 대역등 을 설정할 수 있다.
        - Return results in a specfic format
    - 설정 방법
        ``` sh
        # /etc/cni/net.d/net-script.conflist
        # /opt/cni/bin/net-script.sh
        ./net-script.sh add <container> <namespace>

        # /etc/cni/net.d/10-bridge.conf : CNI 설정
        # /opt/cni/bin   : 네트워크 설정 스크립트
        ```
* CNI Solution
    - Pod, Node Level 네트워크 역할 수행 (Overlay Network 설정 및 관리 - Network routing)
        - Pod 간의 통신
        - Node 간의 Pod 통신을 위한 라우팅 구성
        - 네트워크 정책 구현
    - route, iptables, VXLAN Interface, CNI Plugin 데몬 프로세스 등을 통해 구성
</br>
</br>




---
## 통신 과정 (Traffic Flow)
![K8s_Network_Architecture](img/K8s_Network_Architecture.png)
통신은 크게 4가지로 분류된다 (즉, 네트워크 Layer가 크게 4개로 분류된다고 생각하면 된다)
1. Container 간의 통신
    - 자체 네트워크 사용
    1) 기본적으로 Pod 내의 Container들은 동일한 IP(veth) 가짐
    2) 같은 veth를 가지기 때문에 localhost를 사용하고 Port로 구분되어 통신
2. Pod 간의 통신
    - 컨테이너 서비스 환경(k8s, docker 등)에 따라 다르지만, Overlay Network 서비스(CNI, kubenet 등)를 이용해서 통신
    1) Pod 간에는 서로 다른 veth ip가 할당
    2) 네트워크 서비스(CNI, kubenet 등)가 Network,Routing을 관리 (```route -n```)
3. Pod - Service 간의 통신
    - Pod는 고유한 IP 주소를 부여받는다. 하지만 DaemonSet, ReplicaSet, 수동에 의해 관리되어지는 Pod는 언제든지 삭제/생성이 될 수 있으며, 그때마다 Pod의 IP는 보장되지 않는다. 그렇기때문에 Pod를 대표하는 IP가 필요하다. 이를 위해 사용되는 것이 Service이다.
    - Service가 패킷을 전달하고자 하는 Pod 집합의 진입점을 Endpoint라고 한다. 해당 Endpoint에는 단일 DNS가 부여된다.
    - iptables Rule
        1) PREROUTING rule : 서비스로 들어오는 모든 패킷은 KUBE-SERVICES 규칙을 따르도록 Chaining
        2) KUBE-SERVICES rule : Service에 따라 해당 Rule을 따르도록 Chaining (Service마다 Rule이 생성됨)
        3) KUBE-SERVICES SEP rule : Serivce Endpoint로 접근하고, 여러 Pod로 로드밸런싱 및 Destination NAT 처리
        4) Return 돌아왔던 순서대로 다시 돌아간다.
4. Service - External 간의 통신
    - 3번과 동일
* Ref
    - https://medium.com/finda-tech/kubernetes-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC-%EC%A0%95%EB%A6%AC-fccd4fd0ae6
    - https://sookocheff.com/post/kubernetes/understanding-kubernetes-networking-model/
    - https://malwareanalysis.tistory.com/265

</br>
</br>


---
# CoreDNS
- 설정파일 위치: ```/etc/coredns/Cofefile```
```
curl -v http://wix.apps.
http://[host].[namespace].svc.cluster.local
curl -v http///web-servix.apps.svc
curl -v http://10-2-2-5/apps.pod.cluster.local

[host_name].[namespace].[type].[cluster_root_domain]
```
</br>
</br>




---
# Overlay Network (On linux)
* Switching and Routing
    - Switching
    - Routing
    - Default Gateway
* DNS
    - DNS COnfiguations on Linux
    - CoreDNS Introduction
* Network Namespaces (Linux Bridge)
* Docker Networking


### DNS
```
# DNS 서버 수정
cat /etc/resolv.conf

# 리눅스 DNS 확인 순서
cat /etc/nsswitch.conf
...
host:       files dns           # files: /etc/hosts 파일, dns: DNS 서버
...

```
</br>

### Routing - Switching
```
$ ip link

$ ip addr   

$ ip addr add 192.168.1.10/24 dev eth0          # IP 할당

$ route

# Server 1 (Net CIDR: 192.168.1.0/24)
$ ip route add 192.168.2.0/24 via 192.168.1.1


# Server 2 (Net CIDR: 192.168.2.0/24)
$ ip route add 192.168.1.0/24 via 192.168.2.1
$ ip route add default via 192.168.2.1          # 기본 게이트웨이 설정. default == 0.0.0.0
$ ip route add 192.168.2.0/24 via 0.0.0.0       # Local 대역이므로 0.0.0.0은 게이트웨이를 가지 않아도 된다는 의미이다.



# 해당 서버가 네트워크 트래픽을 포워딩하는 역할을 하도록 설정
cat /proc/sys/net/ipv4/ip_forward  (임시, 재부팅 시 다시 0으로 변경)
1

/etc/sysctl.conf    (영구)
net.ipv4.ip_forward = 1

```

```sh
$ ip route
# 라우팅 규칙이 일치하지 않은 모든 트래픽에 대해 / IP 172.25.0.1로 / eth1 인터페이스를 통해
default via 172.25.0.1 dev eth1
# Dst: 10.244.0.0/16 트래픽에 대해 / weave 인터페이스를 통해 / weave IP == 10.244.192.0
10.244.0.0/16 dev weave proto kernel scope link src 10.244.192.0
172.25.0.0/24 dev eth1 proto kernel scope link src 172.25.0.5
192.1.171.0/24 dev eth0 proto kernel scope link src 192.1.171.3
```
- dev: 네트워크 인터페이스장치
- proto kernel: 해당 라우팅 엔트리가 커널에 의해 자동으로 생성됨을 의미
- scope: 라우팅 범위
    - link: 같은 내크워크 세그먼트 내에서만 유효
    - host: 호스트 내부에서만 유효
    - global: 전역적으로 유효
- src: 소스 IP 주소
- metric: 라우팅 메트릭 값
</br>

 
### Linux Bridge 
네트워크 네임스페이스마다 Routing Table / ARP Table이 존재

```
# 가상 브릿지 생성
# ip link add : 네트워크 인터페이스 생성
ip link add v-net-0 type bridge

# 가상 브릿지 UP
ip link set dev v-net-0 up

# veth를 생성하고 브릿지 연결용 veth 생성
ip link add veth-red type veth peer name veth-red-br
ip link add veth-blue type veth peer name veth-blue-br

# Namespace에 연결 / 가상 브릿지에 연결
## blue라는 네임스페이스에 독립적인 라우팅 테이블, ARP 테이블이 생성
ip link set veth-red netns red
ip link set veth-red-br master v-net-0

ip link set veth-blue netns blue
ip link set veth-blue-br master v-net-0

# IP 설정 후 인터페이스 UP
ip -n red addr add 192.168.15.1 dev veth-red
ip -n blue addr add 192.168.15.2 dev veth-blue

ip -n red link set veth-red up
ip -n red link set veth-blue up

# 브릿지의 IP를 라우팅에 등록
ip addr add 192.168.15.5/24 dev v-net-0

# Route 등록 (필요시)
ip -n red route add 192.168.15.1
ip -n blue route add 192.168.15.1


iptables -t nat -A PREROUTING -j DNAT --dport 8080 --to-destinatino 80
iptables -t nat -A DOCKER -j DNAT --dport 8080 --to-destinatino 172.17.0.3:80
iptables -nvL -t nat

```
- Pod는 Kubernetes 클러스터 내의 네트워크 네임스페이스를 대표하는 단위이다.
- Network Bridge는 CNI가 제공하는 가상 네트워크 디바이스이다.


### Network Namespace 설정 및 관리
1. Create Network Namespaces
2. Create Bridge Network/Interface
3. Create VETH Pairs (Pipe, Virtual Cable)
4. Attach vEth to Namespace
5. Attach Other vEth to Bridge
6. Assign IP Addresses
7. Bring the interfaces up
8. Enable NAT - IP Masquerade


### Bridge Network
$ bridge add <cid> <namespace>
$ bridge add 2e34dcf34 /var/run/netns/2e34cdf34

