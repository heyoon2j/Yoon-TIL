# Kubeadm




### Synopsis
```
preflight                    Run pre-flight checks
certs                        Certificate generation
  /ca                          Generate the self-signed Kubernetes CA to provision identities for other Kubernetes components
  /apiserver                   Generate the certificate for serving the Kubernetes API
  /apiserver-kubelet-client    Generate the certificate for the API server to connect to kubelet
  /front-proxy-ca              Generate the self-signed CA to provision identities for front proxy
  /front-proxy-client          Generate the certificate for the front proxy client
  /etcd-ca                     Generate the self-signed CA to provision identities for etcd
  /etcd-server                 Generate the certificate for serving etcd
  /etcd-peer                   Generate the certificate for etcd nodes to communicate with each other
  /etcd-healthcheck-client     Generate the certificate for liveness probes to healthcheck etcd
  /apiserver-etcd-client       Generate the certificate the apiserver uses to access etcd
  /sa                          Generate a private key for signing service account tokens along with its public key
kubeconfig                   Generate all kubeconfig files necessary to establish the control plane and the admin kubeconfig file
  /admin                       Generate a kubeconfig file for the admin to use and for kubeadm itself
  /kubelet                     Generate a kubeconfig file for the kubelet to use *only* for cluster bootstrapping purposes
  /controller-manager          Generate a kubeconfig file for the controller manager to use
  /scheduler                   Generate a kubeconfig file for the scheduler to use
etcd                         Generate static Pod manifest file for local etcd
  /local                       Generate the static Pod manifest file for a local, single-node local etcd instance
control-plane                Generate all static Pod manifest files necessary to establish the control plane
  /apiserver                   Generates the kube-apiserver static Pod manifest
  /controller-manager          Generates the kube-controller-manager static Pod manifest
  /scheduler                   Generates the kube-scheduler static Pod manifest
kubelet-start                Write kubelet settings and (re)start the kubelet
upload-config                Upload the kubeadm and kubelet configuration to a ConfigMap
  /kubeadm                     Upload the kubeadm ClusterConfiguration to a ConfigMap
  /kubelet                     Upload the kubelet component config to a ConfigMap
upload-certs                 Upload certificates to kubeadm-certs
mark-control-plane           Mark a node as a control-plane
bootstrap-token              Generates bootstrap tokens used to join a node to a cluster
kubelet-finalize             Updates settings relevant to the kubelet after TLS bootstrap
  /experimental-cert-rotation  Enable kubelet client certificate rotation
addon                        Install required addons for passing conformance tests
  /coredns                     Install the CoreDNS addon to a Kubernetes cluster
  /kube-proxy                  Install the kube-proxy addon to a Kubernetes cluster
show-join-command            Show the join command for control-plane and worker node
```









## Cluster 구축

### Ask
* 목적이 무엇인지? (교육, 개발/테스트, 운영 등)
* Cloud vs On-prem
* Workload
    - 얼마나 많은 인원이 사용하는지?
    - 어떤 종류의 서비스를 할것인지? (웹, 빅데이터 및 분석)
    - Application 리소스 사용량은 얼마나 쓰는지? (CPU, Memory)
    - 트래픽 성향은 어떤지? (Heavy traffic, Burst traffic)


### Cluster Architecture
- Single Node (Master & Worker)
- Single Master & Single Worker
    - Woker Node ---> Master Node에 대한 join 필요
- Single Master & Multi Worker
- Multi Master (& Multi Worker) : 서로 다른 개체이다 그렇기 때문에 API Server로 정보
    - API Server : 앞단에 로드밸런스를 둬야한다.
    - Controller Manager/Scheduler : Pod의 상태체크를 하며 스케일링을 진행하게되는데, 여러 개의 CM,Sc가 동작하게 되면 병렬적으로 동작하여 원하지 않는 동작을 야기시킬 수 있다(동시에 Pod 생성/삭제 등). 그렇기때문에 Active/Standby 형태로 동작하게 설정한다.
        ```sh
        $ kube-controller-manager --leader-elect true                 # Active/Standby를 위한 리더 선출 활성화
                                  --leader-elect-lease-duration 15s   # 리더의 활성 상태 유지 최대 기간 (임대 기간)
                                  --leader-elect-renew-deadline 10s   # 리더가 임대 기간을 갱신할 수 있는 가건 (갱신 유예 기간)
                                  --leader-elect-retry-period 2s      # 리더가 임대 갱신을 시도하는 기간 (권한획득 재시도 기간)
        ```

    - ETCD : 분산된 형태로 사용하고, 추가적으로 External ETCD 형태로 사용할 수 있음
        ```
        $ kube-apiserver --etcd-servers=https://10.0.24.10:2379,https://10.0.24.15:2379
        ```
- Using External ETCD (multi node)
- ETCD in HA : 고가용성을 위해 여러 개의 ETCD를 사용. RAFT 프로토콜을 통해 하나의 리더가 선출되고, Write는 해당 리더가 처리하며 다른 노드가 Write 요청을 받은 경우 리더에게 프로세스를 전달한다. Write 요청을 받은 리더는 다른 노드도 데이터를 저장할 수 있도록 Write 작업을 요청한다.



 ```
 ```
* All노드
    1. 각 노드에 Runtime Engine 설치 (Containerd, Docker) --- All
    2. 각 노드에 kubeadm / kubectl / kubelet 설치 --- All
    3. kubeadm 초기화 --- Master
    4. Pod 네트워크 구성 --- All
        1. CNI Plugin 설치
        2.  
    5. Join Node 설청 --- Worker
    6. 
    7. 




### Troubleshooting

