# Networking

* endpoint : Service가 패킷을 전달하고자 하는 Pod 집합의 진입점을 Endpoint라고 한다. 해당 Endpoint에는 단일 DNS가 부여된다.


## 

* CNI Plugin(Container Network Interface) : Overlay Network 관리 - Network routing
* kube-proxy : Firewall 관리 - Net filter, NAT
    - iptables Mode : Chain Rule을 찾아 트래픽 전달 / 시간 복잡도 : O(n)
    - IPVS Mode : Hashing 기반으로 Rule을 찾아 트래픽 전달 / 시간 복잡도 : O(1)
    > https://blog.naver.com/alice_k106/221606077410 / https://techblog.lotteon.com/%EB%A1%AF%EB%8D%B0on-eks-%EC%9A%B4%EC%98%81-tips-8a1cade6a0d5

## 통신 과정
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

---
## Basic
EKS는 AWS에서 제공하는 CNI(Container Network Interface) Plugin을 사용한다!!
* Amazon EKS는 Pod에게 예약된 Worker Node와 동일한 서브넷에서 IP를 할당해준다.
    > 필요시, 



* Pod 네트워크
    - 
    - 
    - 서비스는 이를 통해 통신한다.




---
## Firewall
kube-proxy Mode

https://velog.io/@squarebird/Kubernetes-Networking-2.-Service

iptables mode






---
## 







---
## 






---
## 





---
## 




---
All containers/Pods can communicate to one another without NAT
All nodes can communicate with all containers and vice-versa without NAT
- NAT 구성 없이도 쿠버네티스 내부의 리소스들은 통신이 되어야 한다

이를 위해 많은 네트워크 플랫폼이 구축되어 있다.
- Cisco
- Cilium
- Calico (가장 많이 쓰임)
- flnnel
- VMWare NSX



* Switching and Routing
- Switching
- Routing
- Default Gateway

* DNS
- DNS COnfiguations on Linux
- CoreDNS Introduction

* Network Namespaces
* Docker Networking


1. DNS
```
# DNS 서버 수정
cat /etc/resolv.conf

# 리눅스 DNS 확인 순서
cat /etc/nsswitch.conf
...
host:       files dns           # files: /etc/hosts 파일, dns: DNS 서버
...

```




2. Routing - Switching
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

3. Linux Bridge 
네임스페이스마다 Routing Table / ARP Table이 존재
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


# 브릿지에 IP 등록?
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

### CNI
컨테이너 Network Namespace 생성 및 관리를 책임지는 인터페이스. 네트워크 스크립트를 실행
```
/etc/cni/net.d/net-script.conflist
/opt/cni/bin/net-script.sh
./net-script.sh add <container> <namespace>

/etc/cni/net.d/10-bridge.conf : CNI 설정
/opt/cni/bin   : 네트워크 설정 스크립트

```
* CNI Plugin Responsibilities:
    - Support arguments ADD/DEL/CHECK
    - Support parameters container id, network ns etc...
    - Manage IP Address assignment to PODs
        - /etc/cni/net.d/10-bridge.conf 설정에서 대역등 을 설정할 수 있다.
    - Return results in a specfic format



* Bridge
* VLAN
* IPVLAN
* MACVLAN
* WINDOWS

DHCP
host-local


> Docker는 지원하지 않음. Docker만의 CNM(Container network model)이 있음

### Bridge Network
$ bridge add <cid> <namespace>
$ bridge add 2e34dcf34 /var/run/netns/2e34cdf34



* CNI 설정방법
```
# kubelet
--cni-conf-dir=/etc/cni/net.d
--cni-bin-dir=/opt/cni/bin
./net-script.sh add <container> <namespace>

```



각 노드에 CNI Pod가 실행되어 있어서 전반적인 라우팅을 관리하고 있다. 다른 노드와 통신일 필요한 경우, 캡슐화를 통해 전달한다.


---
# Service


설정 위치
```sh
kube-api-server --service-cluster-ip-range=<ipNet>    # Default: 10.0.0.0/24

iptables -L -t nat | grep -i kube

cat /var/log/kube-proxy.log



```



# CoreDNS
```
curl -v http://wix.apps.
http://[host].[namespace].svc.cluster.local
curl -v http///web-servix.apps.svc
curl -v http://10-2-2-5/apps.pod.cluster.local

[host_name].[namespace].[type].[cluster_root_domain]
```


```
$ cat /etc/coredns/Cofefile

```


# Ingress





