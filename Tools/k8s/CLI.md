
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

    ```



* 사용자/클러스터/컨텍스트 삭제
    ```
    $ kubectl --kubeconfig=config-demo config unset users.<name>
    $ kubectl --kubeconfig=config-demo config unset clusters.<name>
    $ kubectl --kubeconfig=config-demo config unset contexts.<name>
    ```




---
```

```


```
# Node & Pod

kubectl get pod                                 # Print Pod information
kubectl get pod -o wide                         # Output Option : wide
kubectl get pods -o=custom-columns=NAME:.metadata.name,IP:.status.podIP,STATUS:.status.phase,NODE:.spec.nodeName
kubectl get pods speaker-vnc2k -o yaml - n metallb-system
kubectl get pods --all-namespace                # Print all Pod information










kubectl run <Pod Nme> --image=nginx             # Run Pod

kubectl delete pod <Pod Name>                   # Delete Pod





kubectl api-resource                            # Print resource list




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