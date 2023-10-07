# Networking


## 

* CNI Plugin(Container Network Interface) : Overlay Network 관리 - Network routing
* kube-proxy : Firewall 관리 - Net filter, NAT
    - iptables Mode : Chain Rule을 찾아 트래픽 전달 / 시간 복잡도 : O(n)
    - IPVS Mode : Hashing 기반으로 Rule을 찾아 트래픽 전달 / 시간 복잡도 : O(1)
    > https://blog.naver.com/alice_k106/221606077410 / https://techblog.lotteon.com/%EB%A1%AF%EB%8D%B0on-eks-%EC%9A%B4%EC%98%81-tips-8a1cade6a0d5

## 통신 과정
![Network_Architecture]()
통신은 크게 4가지로 분류된다 (즉, 네트워크 Layer가 크게 4개로 분류된다고 생각하면 된다)
1. Container 간의 통신
    - 자체 네트워크 사용
    1) 기본적으로 Pod 내의 Container들은 동일한 IP(veth) 가짐
    2) 같은 veth를 가지기 때문에 localhost를 사용하고 Port로 구분되어 통신
2. Pod 간의 통신
    - 컨테이너 서비스 환경(k8s, docker 등)에 따라 다르지만, Overlay Network 서비스(CNI, kubenet 등)를 이용해서 통신
    1) Pod 간에는 서로 다른 veth ip가 할당
    2) 네트워크 서비스(CNI, kubenet 등)가 Routing을 관리 (```route -n```)
3. Pod - Service 간의 통신
    - Firewall 관리 ()
4. Service - External 간의 통신
* Ref
    - https://medium.com/finda-tech/kubernetes-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC-%EC%A0%95%EB%A6%AC-fccd4fd0ae6
    - https://sookocheff.com/post/kubernetes/understanding-kubernetes-networking-model/

---
## Basic
EKS는 AWS에서 제공하는 CNI(Container Network Interface) Plugin을 사용한다!!
* Amazon EKS는 Pod에게 예약된 Worker Node와 동일한 서브넷에서 IP를 할당해준다.
    > 필요시, 





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

