
# 키워드 정리

## 기본적인 명령어
* 객체 생성
    ```sh
    # kubectl create -f <file_path>
    $ kubectl create -f ./test-pod.yaml

    $ kubectl run custom-nginx --image=nginx --port=8080 --namespace=default --dry-run=client -o yaml > pod-temp.yaml
    $ kubectl run custom-nginx --image=nginx --port=8080 --expose           # ClusterIP도 같이 생성

    $ kubectl create deployment nginx --replicas=4 --image=nginx --port=8080 --dry-run=client -o yaml > deployment-temp.yaml

    $ kubectl create service clusterip redis --tcp=6379:6379 --dry-run=client -o yaml > clusterip-temp.yaml

    $ kubectl expose pod nginx --type=NodePort --port=80 --name=nginx-service --dry-run=client -o yaml

    ```

* 정보 출력
    ```sh
    # kubectl get <obj_type>/<obj_name> -n <namespace> -o <output_type>
    $ kubectl get all -A
    $ kubectl get all --all-namespaces

    $ kubectl get pods -o=custom-columns=NAME:.metadata.name,IP:.status.podIP,STATUS:.status.phase,NODE:.spec.nodeName

    $ kubectl get pods/spk-vnc2k -n test-app -o yaml
    $ kubectl get pods/spk-vnc2k -n test-app -o wide

    ```

* 변경
    ```sh

    ```

* 객체 삭제
    ```sh
    $ kubectl delete -f ./pod.json
    $ kubectl delete -f '*.json'                        # Delete resources from all files that end with '.json'
    
    
    $ kubectl delete pod,service baz foo                # Delete pods and services with same names "baz" and "foo"  
    $ kubectl delete pods,services -l name=myLabel      # Delete pods and services with label name=myLabel


    $ kubectl delete pod foo --force                    # Force delete a pod on a dead node


    $ kubectl delete pods --all                         # Delete all pods


    # Delete resources from a directory containing kustomization.yaml - e.g. dir/kustomization.yaml
    kubectl delete -k dir
     
    # Delete a pod based on the type and name in the JSON passed into stdin
    cat pod.json | kubectl delete -f -
    
     
    # Delete a pod with minimal delay
    kubectl delete pod foo --now
    

    ```


---
## 스케줄링 
* 스케줄링
    ```sh
    # tains & toleration
    # NoSchedule / PreferNoSchedule / NoExecute
    $ kubectl taint nodes <노드 이름> key1=value1:NoSchedule key2=value2:NoExecute

    $ kubectl taint nodes node-name key=value:<taint-effect>      

    $ kubectl get events -o wide

    $ kubectl logs my-custom-scheduler --name-space=kube-system
    ```

* Maintenance
    ```sh
    # drain : 해당 노드에서 모든 파드를 지우고, 다른 노드로 이동
    # cordon : 행당 노드에서 새로운 파드가 생성되지 않도록 설정
    # uncordon : drain & cordon 설정 해제

    $ kubectl drain node-1

    $ kubectl cordon node-2

    $ kubectl uncordon node-1

    $ kubectl version
    ```

* Update
    ```sh
    # 강제로 삭제 후 재생성
    $ kubectl replace -f elephant.yaml --force

    $ kubectl edit deployment nginx

    $ kubectl scale deployment nginx --replicas=5

    $ kubectl set image deployment nginx nginx=nginx:1.18

    $ kubectl get all --selector env=prod,bu=finance,tier=frontend

    $ kubectl label node node01 color=blue
    ```

* Rolling
    ```sh
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
## 모니터링 & 로킹
```
$ kubectl top nodes
$ kubectl top pods


$ kubectl logs <pod_name>/<container_name> --all-containers=true
$ kubectl logs -f : 실시간 로그 확인


kubectl logs pod/webapp-1 --all-containers=true


$ kubectl exec -it -n <namespace> <pod> -c <container_name> -- <script>


kubectl exec -it my-pod -- /bin/bash
kubectl exec -it -n default app -c nginx -- cat /log/app.log

```

======================================================




-
# Pod


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











=========================================================================================================

Scheduling
    - Node Selection : 
        - nodeName : (manual) / nodeSelector / nodeAffinity
    - 유지 보수 및 장애조치 :  taints & tolerations
    - 리소스 제한 : 




## Ingress
```
kubectl get ingress

```


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




