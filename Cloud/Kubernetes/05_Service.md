# Service
서비스는 외부와 Kubernetes Cluster 네트워크 연결을 위한 점점(서비스)이다.
* Service Type은 다음과 같다.
    1) ClusterIP
    2) NodePort
    3) LoadBalancer (가장 많이 사용 될거라고 보임)
    4) ExternalName
* 보조 기능
    1) Ingress
    2) MetalLB
    3) HPA
* 서비스를 생성하면 해당 DNS 엔트리가 생성된다.
    - FQDN : ```<서비스-이름>.<네임스페이스-이름>.svc.cluster.local```

---
## ClusterIP
Cluster 내에서만 유효한 Cluster IP를 제공한다. 그렇기 때문에 Pod간의 통신을 위해 사용된다!
* 외부에서 kube-proxy를 통해서 접근할 수 있다 (Client --> kube-proxy --> Service:ClusterIP --> Pod)


---
## NodePort 서비스
가장 쉬운 방법으로 모든 Worker Node의 특정 포트를 열고, 해당 포트로 들어오는 모든 요청은 NodePort 서비스로 전달되고 이를 연결된 Pod로 전달한다.
* Node IP를 빌려 외부 User가 Pod에 접근할 수 있도록 한다. (Node Port ---> Target Port로 변환)
    - Node Port : Node Port
    - Port : NodePort 서비스 Port (기본적으로 Target Port와 같은 값으로 설정)
    - Target Port : Pod Port
* 1개의 NodePort는 1개의 Deployment만 할당할 수 있다.
* 부하분산 기능도 가지고 있다.
    ```
    $ kubectl expose deplyment np-pods --type=NodePort --name=np-svc-v2 --prot=80
    ```
    - expose로도 생성 가능하나 NodePort를 지정할 수 없다. 임의로 포트가 지정됨 (30000 ~ 32767)
</br>




---
## LoadBalancer
Load Balancer를 사용하려면 Load Balancer를 구현해 둔 외부 서비스 업체의 도움을 받아야 한다!
* 내부적으로 NodePort 서비스를 사용하며, 이를 클라우드 제공자의 LoadBalancer를 통해 외부에 노출시킨다.






</br>
</br>



---
## ExternalName



</br>
</br>



---
## Ingress
외부에서 내부로 접근하는 요청을 어떻게 처리할 정해놓은 규칙
> Ingress를 사용하는 이유는 여러가지 기능을 사용하기 위해서 Rule, 인증 등등
* 기본적으로 Ingress Controller + Ingress Object를 생성해야 하며, Pod와 직접 통신할 수 없어 "NodePort" 또는 "LoadBalancer˚와 연동해야 한다!!
    * Ingress Conroller Pod/Deployment : Controller 생성
    * Ingress Object : 규칙 정의
    * NodePort or LoadBalancer Service : Service 생성
* 여러 개의 Deployment가 있을 때, 그 수만큼 노드포트 서비스를 구동해야 하는 경우 Ingress를 사용한다. (결국 NodePort Group을 만들다고 생각하면 될꺼 같다. 그렇기 때문에 NodePort와 동일한 통신 구조를 가지고 있다)
* 고유한 주소 제공
* 트래픽에 대한 L4/L7 Loadbalancer 제공
* 보안 인증서 처리
* Ingress Controller 설정시, Default Backend Service를 지정할 수 있다. 규칙에 해당하지 않을 시, 해당 Service로 라우팅된다.
    - ```--default-backend-service=app-space/default-backend-service```
</br>

### Ingress Controller
Ingress 동작을 수행하기 위해서는 Controller 설치가 필요하다!
* K8s에서 제공하는 Ingress Controller (타사 프로젝트 Controller가 있긴 하다)
    * AWS
    * GCE
    * nginx
* 여러의 Ingress Controller를 사용할 수 있으며, 원하는 Conroller를 IngressClass를 통해 선택하면 된다.
</br>


### 구축 순서
1) Ingress Controller Namespace 생성
2) ServiceAccount 생성
    - ingress-nginx
    - ingress-nginx-admission
3) 각각에 맞는 Role/RoleBinding, ClusterRole/ClusterRoleBindings 생성
4) Ingeress Controller Deployment + Service(NodePort - 외부 접근용, ClusterIP - Admission 접근용)
5) Ingress 생성


---
## MetalLB
On-premise에서 Load Balancer를 제공하고 싶을 때 사용한다(Bare metal 환경에서 로드 벨런서를 사용할 수 있게 고안된 오픈소스 프로젝트)
* L2 네트워크(ARP/NDP)와 L3 네트워크(BGP)로 구현됨
* MetalLB Contoller Pod 와 MetalLB Speaker DaemonSet을 구성한다.
> 필요시 나중에 오픈소스 프로젝트 확인 필요!

</br>
</br>

---
## HPA (Horizontal Pod Autoscaler)
Auto Scaling 기능을 가진 Object
* metrics-server가 필요 (해당 서버는 Auto Scaling을 위한 용도로 데이터를 kube-apiserver로 전달하기 때문에 실제 모니터링를 하는 용도와는 거리가 멀다!!)
* metrics-server는 kubelet으로부터 데이터를 수집, 통합하여 kube-apiserver로 전달
* metrics-server 설정
    - TLS 인증 : kubelet과 통신할 때 CA 인증서가 필요
    ```
    $ kubectl autoscale deployment hpa-hname-pods --min=1 --max=30 --cpu-percent=50
    ```

