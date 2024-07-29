```
Basic Commands (Beginner):
  create          Create a resource from a file or from stdin
  expose          Take a replication controller, service, deployment or pod and

expose it as a new Kubernetes service
  run             Run a particular image on the cluster
  set             Set specific features on objects

Basic Commands (Intermediate):
  explain         Get documentation for a resource
  get             Display one or many resources
  edit            Edit a resource on the server
  delete          Delete resources by file names, stdin, resources and names, or
by resources and label selector


============================================================
Deploy Commands:
  rollout         Manage the rollout of a resource
  scale           Set a new size for a deployment, replica set, or replication
controller
  autoscale       Auto-scale a deployment, replica set, stateful set, or
replication controller

Cluster Management Commands:
  certificate     Modify certificate resources
  cluster-info    Display cluster information
  top             Display resource (CPU/memory) usage
  cordon          Mark node as unschedulable
  uncordon        Mark node as schedulable
  drain           Drain node in preparation for maintenance
  taint           Update the taints on one or more nodes

Troubleshooting and Debugging Commands:
  describe        Show details of a specific resource or group of resources
  logs            Print the logs for a container in a pod
  attach          Attach to a running container
  exec            Execute a command in a container
  port-forward    Forward one or more local ports to a pod
  proxy           Run a proxy to the Kubernetes API server
  cp              Copy files and directories to and from containers
  auth            Inspect authorization
  debug           Create debugging sessions for troubleshooting workloads and
nodes
  events          List events

Advanced Commands:
  diff            Diff the live version against a would-be applied version
  apply           Apply a configuration to a resource by file name or stdin
  patch           Update fields of a resource
  replace         Replace a resource by file name or stdin
  wait            Experimental: Wait for a specific condition on one or many
resources
  kustomize       Build a kustomization target from a directory or URL

Settings Commands:
  label           Update the labels on a resource
  annotate        Update the annotations on a resource
  completion      Output shell completion code for the specified shell (bash,
zsh, fish, or powershell)

Subcommands provided by plugins:

Other Commands:
  api-resources   Print the supported API resources on the server
  api-versions    Print the supported API versions on the server, in the form of
"group/version"
  config          Modify kubeconfig files
  plugin          Provides utilities for interacting with plugins
  version         Print the client and server version information

```


## Cluster 관리

```
$ kubectl cluster-info

$ kubectl version

$ kubectl get nodes
```
- Cluster 정보
- K8s 버전 정보
- Node 정보





```
# Config



kubectl config --kubeconfig=config-demo set-cluster development --server=https://1.2.3.4 --certificate-authority=fake-ca-file
kubectl config --kubeconfig=config-demo set-cluster test --server=https://5.6.7.8 --insecure-skip-tls-verify


```



## 사용자/클러스터/컨텍스트 관리
* 클러스정 설정
    ```sh
    # kubectl config [--kubeconfig=config_file_path] set-cluster NAME [--server=server] [--certificate-authority=path/to/certificate/authority] [--insecure-skip-tls-verify=true] [--tls-server-name=example.com] [Options]

    $ kubectl config --kubeconfig=config-demo set-cluster development --server=https://1.2.3.4 --certificate-authority=fake-ca-file
    $ kubectl config --kubeconfig=config-demo set-cluster test --server=https://5.6.7.8 --insecure-skip-tls-verify=true

    ```
    * insecure-skip-tls-verify : 인증서에 대하여 공인기관에 검증하는 과정을 건너뛴다
    * certificate-authority-data : "insecure-skip-tls-verify: true"인 경우 해당 부분을 공백으로 설정. "false"인 경우, 접속하고자하는 k8s master node의 certificate-authority-data(Root Cert)를 넣어줘야 한다
    * certificate-authority : data가 아닌 경로를 넣어준다
    * tls-server-name : 
* 인증 설정
    ```sh
    # $ kubectl config [--kubeconfig=config_file_path] set-credentials NAME [--client-certificate=path/to/certfile] [--client-key=path/to/keyfile] [--token=bearer_token] [--username=basic_user] [--password=basic_password] [--auth-provider=provider_name] [--auth-provider-arg=key=value] [--exec-command=exec_command] [--exec-api-version=exec_api_version] [--exec-arg=arg] [--exec-env=key=value]

    $ kubectl config --kubeconfig=config-demo set-credentials developer --client-certificate=fake-cert-file --client-key=fake-key-seefile
    $ kubectl config --kubeconfig=config-demo set-credentials experimenter --username=exp --password=some-password

    ```
    * client-certificate / client-key : Root Cert로부터 사인된 하위 인증서와 비밀 키 파일 경로
    * username / password : Root Cert에 설정해 놓은 ID/PW를 설정
    * token : Bearer token
    * exec : 
* 컨텍스트 설정
    ```sh
    # kubectl config set-context [NAME | --current] [--cluster=cluster_nickname] [--user=user_nickname] [--namespace=namespace]

    $ kubectl config --kubeconfig=config-demo set-context dev-frontend --cluster=development --namespace=frontend --user=developer
    $ kubectl config --kubeconfig=config-demo set-context dev-storage --cluster=development --namespace=storage --user=developer
    $ kubectl config --kubeconfig=config-demo set-context exp-test --cluster=test --namespace=default --user=experimenter

    $ kubectl config --kubeconfig=config-demo use-context exp-test
    ```



* 사용자/클러스터/컨텍스트 삭제
    ```
    $ kubectl --kubeconfig=config-demo config unset users.<name>
    $ kubectl --kubeconfig=config-demo config unset clusters.<name>
    $ kubectl --kubeconfig=config-demo config unset contexts.<name>
    ```




---
# Pod
## Pod 생성



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


kubectl get <kind> -n [namespace]

kubectl get all


kubectl describe <kind> <pod_name> : 상세 현황 출력
kubectl describe pod pod-abcde
kubectl descirbe replicaset replicatset-abc



kubectl delete <kind> <resource_name>




## 잘 안쓰이는

kubectl edit <kind> <resource_name>



kubectl set image deplyment/myapp-deployment nginx-container=nginx:1.9.1





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

# Statud
kubectl rollout status deployment/myapp-deployment
kubectl rollout history deployment/myapp-deployment

# Rollback
kubectl rollout undo deployment/myapp-deployment
```

배포전략 2가지 (Deployment Strategy)
1. Recreate
   1. Pod를 모두 Down 시키고 다시 생성
2. Rolling upgrade
    - 순차적으로 변경분을 업데이트
    - 이를 위하 ReplicaSet을 새로 만들고, 신규 ReplicaSet에 Pod 1개 생성 / 기존 ReplicaSet에서 Pod 1개 삭제를 반복적으로 진행되면서 하나씩 업데이트가 진행된다.




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
# API 

$ kubectl api-resources -o wide                 # API Groups 확인

```