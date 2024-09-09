
# 키워드 정리




Scheduling
    - Node Selection : 
        - nodeName : (manual) / nodeSelector / nodeAffinity
    - 유지 보수 및 장애조치 :  taints & tolerations
    - 리소스 제한 : 


## Network
```
ip link
ip addr
ip addr add 192.168.1.10/24 dev eth0
ip route
ip route add 192.168.1.0/24 via 192.168.2.1
cat /proc/sys/net/ipv4/ip_forward
arp
netstat -plnt
route
```
- 많이 쓰이는 Commands





## Network Policy
```
kubectl describe netpol
```



## Security Context
```

```


## Role
```

$ kubectl auth can-i create deployments --as dev-user

```





## config
```
$ kubectl config view   # Cluster 정보 확인
$ kubectl config use-context --kubeconfig <config_path> [cluster]      # Config 설정을 Cluster로 변경
```


##




## ETCCT 설정방법
```
export ETCDCTL_API=3
etcdctl version


```





## Backup
- 백업이 필요한 데이터
    1) Resource Configuration
       - ```$ kubectl get all --all-namespaces -o yaml > all-deploy-services.yaml```
       - VELERO. ARK by HeptIO 라는 오픈소스 사용
    2) ETCD Cluster (상태 저장)
        - 백업 :
            ```
            $ etcdctl --endpoints=https://127.0.0.1:2379 \
            --cacert=/etc/kubernetes/pki/etcd/ca.crt \
            --cert=/etc/kubernetes/pki/etcd/server.crt \
            --key=/etc/kubernetes/pki/etcd/server.key \
            snapshot save /opt/snapshot-pre-boot.db
            ```
        - 복구 시, 먼저 kube-apiserver 중지 : ```service kube-apiserver stop```
        - 복구 진행 : 
            ```
            $ etcdutl --data-dir <data-dir-location> snapshot restore snapshot.db
            $ vi /etc/kubernetes/manifests/etcd.yaml
            ...
            hostPath : <data-dir-location>
            ...
            ```
            1) 특정 Directory에 백업본 복구
            2) 복구한 위치로 YAML 파일 수정
        - 권한 수정 : chown -R etcd:etcd /var/lib/etcd-data-new
        - 데몬 재시작 : ```systemctl restart daemon-reoload```
        - etcd 서비스 재시작 : ```service etcd restart  /  systemctl restart etcd```
        - kube-apiserver 시작 : ```service kube-apiserver start```
    3) 





## Maintenance
```
# drain : 해당 노드에서 모든 파드를 지우고, 다른 노드로 이동
# cordon : 행당 노드에서 새로운 파드가 생성되지 않도록 설정
# uncordon : drain & cordon 설정 해제

$ kubectl drain node-1

$ kubectl cordon node-2

$ kubectl uncordon node-1

$ kubectl version

```



## 스케줄링
```
# tains & toleration
# NoSchedule / PreferNoSchedule / NoExecute
$ kubectl taint nodes <노드 이름> key1=value1:NoSchedule key2=value2:NoExecute

kubectl taint nodes node-name key=value:<taint-effect>      


kubectl get events -o wide


kubectl logs my-custom-scheduler --name-space=kube-system

```


---
## 모니터링 & 로킹
```
kubectl top nodes
kubectl top pods



$ kubectl logs <pod_name>/<container_name> --all-containers=true
$ kubectl logs -f : 실시간 로그 확인


kubectl logs pod/webapp-1 --all-containers=true


$ kubectl exec -it -n <namespace> <pod> -c <container_name> -- <script>


kubectl exec -it my-pod -- /bin/bash
kubectl exec -it -n default app -c nginx -- cat /log/app.log

```


---


# 생성 및 업데이트 방법
```
# Create

kubectl run custom-nginx --image=nginx --port=8080 --namespace=default --dry-run=client -o yaml > pod-temp.yaml

kubectl create deployment nginx --replicas=4 --image=nginx --port=8080 --dry-run=client -o yaml > deployment-temp.yaml

kubectl create service clusterip redis --tcp=6379:6379 --dry-run=client -o yaml > clusterip-temp.yaml

kubectl expose pod nginx --type=NodePort --port=80 --name=nginx-service --dry-run=client -o yaml



# Update
kubectl edit deployment nginx

kubectl scale deployment nginx --replicas=5

kubectl set image deployment nginx nginx=nginx:1.18



kubectl get all --selector env=prod,bu=finance,tier=frontend



kubectl label node node01 color=blue


# 강제로 삭제 후 재생성
kubectl replace -f elephant.yaml --force

```

## Rolling
```
# Create
    $ kubectl create -f deployment-def.yaml

# Get
    $ kubectl get deployments

# Update
    $ kubectl apply -f deployment-def.yaml

    $ kubectl set image deployment/app-test nginx=nginx:1.9.1

# Status
    $ kubectl rollout status deployment/app-test

    $ kubectl rollout history deployment/app-test

# Rollback
    $ kubectl rollout undo deployment/app-test

```





---
# Pod
## Pod 생성
## Static Pod 확인하는 방법
ps -aux | grep -i kubelet # Config 확인
vi /var/lib/kublet/config # staticPodPaths 확인


Describe를 통해 OwnerReferences 확인 : Node인 경우 Static Pod




## Pod 정보 출력
* 명령어 : ```kubectl get pod [pod_name] [options]```
    ```
    kubectl get pod                                 # Print Pod information
    kubectl get pod -o wide                         # Output Option : wide
    kubectl get pods -o=custom-columns=NAME:.metadata.name,IP:.status.podIP,STATUS:.status.phase,NODE:.spec.nodeName
    kubectl get pods speaker-vnc2k -n metallb-system -o yaml 
    kubectl get pods --all-namespace                # Print all Pod information
    ```
    - 

```
# namespace
kubectl config set-contxt $(kubectl config current-context) --namespace=dev

kubectl get pods --all-namespaces




kubectl create namespace dev
# Template 생성



kubectl run nginx --image=nginx --dry-run=client -o yaml
kubectl create deployment --image=nginx nginx --replicas=4 --dry-run=client -o yaml > nginx-deployment.yaml




kubectl get <kind> -n [namespace]

kubectl get all


kubectl describe <kind> <pod_name> : 상세 현황 출력
kubectl describe pod pod-abcde
kubectl descirbe replicaset replicatset-abc



kubectl delete <kind> <resource_name>




## 잘 안쓰이는 방법 : 직접 수정

kubectl edit <kind> <resource_name>



kubectl set image deplyment/myapp-deployment nginx-container=nginx:1.9.1

###############




```
----
## Update & Rollback
* 업데이트 전체 주기
```
# Create
kubectl create -f deployment-definition.yaml --record=true

# Get
kubectl get deployments

# Update
kubectl apply -f deployment-definition.yaml
kubectl set imgae deployment/myapp-depolyment nginx=nginx:1.91.

# Status
kubectl rollout status deployment/myapp-deployment
kubectl rollout history deployment/myapp-deployment

# Rollback
kubectl rollout undo deployment/myapp-deployment





---
# ReplicaSet Replica 갯수 변경 시!
kubectl edit replicaset test-replicaset

kubectl apply -f replicaset-def.yaml

kubectl replace -f replicaset-def.yaml : 파드를 모두 삭제하고 재 생성

kubectl scale --replicas=6 -f replicaset-def.yaml

kubectl scale --replicas=6 replicaset test-replicaset

```

배포전략 2가지 (Deployment Strategy)
1. Recreate
   1. Pod를 모두 Down 시키고 다시 생성
2. Rolling upgrade
    - 순차적으로 변경분을 업데이트
    - 이를 위하 ReplicaSet을 새로 만들고, 신규 ReplicaSet에 Pod 1개 생성 / 기존 ReplicaSet에서 Pod 1개 삭제를 반복적으로 진행되면서 하나씩 업데이트가 진행된다.


```


## Service
```
kubectl get service


```










kubectl get nodes

  kubectl delete -f ./pod.json
  
  # Delete resources from a directory containing kustomization.yaml - e.g. dir/kustomization.yaml
  kubectl delete -k dir
  
  # Delete resources from all files that end with '.json'
  kubectl delete -f '*.json'
  
  # Delete a pod based on the type and name in the JSON passed into stdin
  cat pod.json | kubectl delete -f -
  
  # Delete pods and services with same names "baz" and "foo"
  kubectl delete pod,service baz foo
  
  # Delete pods and services with label name=myLabel
  kubectl delete pods,services -l name=myLabel
  
  # Delete a pod with minimal delay
  kubectl delete pod foo --now
  
  # Force delete a pod on a dead node
  kubectl delete pod foo --force
  
  # Delete all pods
  kubectl delete pods --all

```




```







kubectl run <Pod Nme> --image=nginx             # Run Pod

kubectl delete pod <Pod Name>                   # Delete Pod


kubectl apply -f <file_name>


kubectl api-resource                            # Print resource list





kubectl get replicasets




```





* Resource 관련
    ```sh
    # 네임스페이스에 속하는 리소스
    kubectl api-resources --namespaced=true

    # 네임스페이스에 속하지 않는 리소스
    kubectl api-resources --namespaced=false
    ```




```
# Object
# kubectl create -f <YAML File>                   # Create Object

kubectl create deployment dpy-nginx --image=nginx

kubectl create -f ~/dpy-template.yaml


kubectl apply -f ~/dpy-template.yaml


## Label 
## label 추가
$ kubctl label <object> <obj_name> <key>=<value>



## History
$ kubectl apply -f ~/dpy-template.yaml --record     # 배포 History 기록
$ kubectl rollout history depoyment dpy-tempate     # 배포 History 출력


$ kubectl describe deployment rollout-nginx         # 상태 출력


$ kubectl delete -f ~/dpy-template.yaml

```
* ad-hoc(일회적으로 사용)으로 Object를 생성하는 경우, Create
* 지속적으로 변경이 생길 가능성이 있는 복잡한 Object를 생성하는 경우, Apply





```
kubectl exec -it nginx-pod -- /bin/bash



```


```
# Node Management


$ kubectl get nodes --kubeconfig admin.conf       # Print Node inforamtion


$ kubectl cordon <node_name>

$ kubectl drain <node_name>
```


```
# Service
$ kubectl get services

$ kubectl apply -f ~/nodport.yaml


$ kubectl expose deplyment np-pods --type=NodePort --name=np-svc-v2 --prot=80

$ kubectl delete services np-svc

```
* xpose로도 생성 가능하나 NodePort를 지정할 수 없다. 임의로 포트가 지정됨 (30000 ~ 32767)



```
# ConfigMap
$ kubectl create configmap test-config --from-literal=APP_COLOR=blue --from-literal=APP_MOD=dev
$ kubectl create configmap test-config --from-file=./test.properties


# Secret
# ConfigMap
$ kubectl create secret generic
$ kubectl create secret test-config --from-literal=APP_COLOR=blue --from-literal=APP_MOD=dev
$ kubectl create secret test-config --from-file=./test.properties


```



```
# API 

$ kubectl api-resources -o wide                 # API Groups 확인

```




