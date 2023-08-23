# Service
외부에서 Kubernetes Cluser에 접속하는 방법을 서비스라고 한다.
* Service Type은 다음과 같다.
    1) ClusterIP
    2) NodePort
    3) LoadBalancer (가장 많이 사용 될거라고 보임)
    4) ExternalName
* 보조 기능
    3) Ingress
    5) MetalLB

    7) HPA


---
## ClusterIP
Cluster 내에서만 유효한 가상 IP를 제공한다. 그렇기 때문에 Pod간의 통신을 위해 사용된다!



---
## NodePort 서비스
가장 쉬운 방법으로 모든 Worker Node의 특정 포트를 열고, 해당 포트로 들어오는 모든 요청은 NodePort 서비스로 전달되고 이를 연결된 Pod로 전달한다.
* Node IP를 빌려 외부 User가 Pod에 접근할 수 있도록 한다. (Node Port ---> Target Port로 변환)
* 1개의 NodePort는 1개의 Deployment만 할당할 수 있다.
* 부하분산 기능도 가지고 있다.
    ```
    $ kubectl expose deplyment np-pods --type=NodePort --name=np-svc-v2 --prot=80
    ```
    * expose로도 생성 가능하나 NodePort를 지정할 수 없다. 임의로 포트가 지정됨 (30000 ~ 32767)
</br>


---
## Ingress
외부에서 내부로 접근하는 요청을 어떻게 처리할 정해놓은 규칙 
* 기본적으로 Ingress Controller + Ingress Object를 생성해야 하며, Pod와 직접 통신할 수 없어 "NodePort" 또는 "LoadBalancer˚와 연동해야 한다!!
    * Ingress Conroller Pod/Deployment : Controller 생성
    * Ingress Object : 규칙 정의
    * NodePort or LoadBalancer Service : Service 정의

여러 개의 Deployment가 있을 때, 그 수만큼 노드포트 서비스를 구동해야 하는 경우 Ingress를 사용한다. (결국 NodePort Group을 만들다고 생각하면 될꺼 같다. 그렇기 때문에 NodePort와 동일한 통신 구조를 가지고 있다)

* 고유한 주소 제공
* 트래픽에 대한 L4/L7 Loadbalancer 제공
* 보안 인증서 처리
* 제공하는 Ingress Controller (타사 프로젝트 Controller가 있긴 하다)
    * AWS
    * GCE
    * nginx
</br>
</br>


---
## LoadBalancer
Load Balancer를 사용하려면 Load Balancer를 구현해 둔 외부 서비스 업체의 도움을 받아야 한다!




</br>
</br>


---
## MetalLB
On-premise에서 Load Balancer를 제공하고 싶을 때 사용한다(Bare metal 환경에서 로드 벨런서를 사용할 수 있게 고안된 오픈소스 프로젝트)
* L2 네트워크(ARP/NDP)와 L3 네트워크(BGP)로 구현됨
* MetalLB Contoller Pod 와 MetalLB Speaker DaemonSet을 구성한다.
</br>
</br>


## ExternalName
