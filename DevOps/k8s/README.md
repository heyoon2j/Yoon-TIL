# Kubernetes

## 핵심 구성 요소
* 오케스트레이션
* 스케줄링
* 네트워킹
* 보안
* 저장소
* 신원 확인 및 인증
* 인프라 관리


## C





### 공부할 내용
* 인증
    - 서비스 간의 인증(mTLS)
        - 인증서 관리 (순환 자동화)
        - https://docs.aws.amazon.com/ko_kr/eks/latest/userguide/cert-signing.html
        - https://kubernetes.io/docs/tasks/tls/certificate-rotation/
        - https://coffeewhale.com/kubernetes/authentication/x509/2020/05/02/auth01/
        - https://kubernetes.io/docs/reference/access-authn-authz/service-accounts-admin/#user-accounts-versus-service-accounts
* 네트워크





인증 과정







## 명령어 정리
```sh
$ export do="-o yaml --dry-run=client"
$ alias k=kubectl


$ kubectl run redis --image=redis --labels="tier=db,test=abc" --port=8080

$ kubectl create deployment nginx --image=nginx --replicas=3

$ kubectl expose po nginx --type=ClusterIP --port=444 --name=nginx-svc
$ kubectl expose rc nginx --type=ClusterIP --port=4100 --protocol=UDP --name=nginx-svc
$ kubectl expose deploy nginx --type=ClusterIP --port=80 --target-port=8000 --name=nginx-svc


$ kubectl get pods --show-labels -o wide
$ kubectl get po --selector="env=dev,bu=finance"
```

## Schedule
1. nodeName / nodeSelector
2. taint & toleration
   - NoSchedule : Toleration가 일치하지 않은 Node에는 배포하지 못한다 (새로운 Pod에만 적용됨)
   - NoExecute : 실행중인 Pod 중에 Toleration이 적용되어 있지 않으면, 다른 Node로 옮기고 새로운 Pod를 Node에 배포하지 못하게 한다.
   - PreferNoSchedule : 가급적이면 해당 Node에 배포하지 않는다는 의미이다.
3. Node Affinity
    - requiredDuringSchedulingIgnoredDuringExecution: 실행중인 것은 제외

```yaml
kind: Pod
spec:
  # 1) 
  nodeName: node01
  # 2)
  nodeSelector:
    disktype: ssd
  containers:
  -  image: nginx
     name: nginx
```
```
$ kubectl taint node node01 tier=db:NoSchedule
$ kubectl taint node node01 tier=db:NoSchedule-
```


## Static Pod
```
$ ps -aux | grep kubelet | grep config
> staticPodPath: /etc/kubernetes/manifests
```


## Logging
```
$ kubectl logs webapp
$ kubectl logs webapp-2 -c simple-webapp
```


## Rolling Upade & Rollback
* Update 방식
    ```
    strategy:
      rollingUpdate:
        maxSurge: 25%
        maxUnavailable: 25%
      type: RollingUpdate
    ```
* Update 확인 및 Rollback
    ```
    $ kubectl edit

    $ kubectl rollout status deployment/app-test
    $ kubectl rollout history deployment/app-test

    $ kubectl rollout undo deployment/app-test
    ```


## Command & Env
* Dockerfile : ENTRYPOINT / CMD
    ```
    ENTRYPOINT ["python", "app.py"]
    CMD ["--color", "red"]
    ```
* K8s YAML :  command / args
    ```
    spec:
      containers:
      - name: command-demo-container
        image: debian
        command: ["printenv"]
        args: ["HOSTNAME", "KUBERNETES_PORT"]
    ```
* ENV
    ```
    spec:
      containers:
      - env:
        - name: APP_COLOR
          value: pink
    ```


## ConfigMap & Secret

```
$ kubectl create configmap my-config --from-literal=APP_COLOR=darkblue --from-literal=APP_OTHER=disregard

$ k create secret generic db-secret --from-literal=DB_Hos
t=sql01 --from-literal=DB_User=root --from-literal=DB_Password=password1
23
```


## OS Upgrade
drain / cordon /uncordon
* drain : 해당 노드의 Pod들 지우고, 더이상 해당 노드에 스케줄링하지 못하도록 설정 (ReplicaSet이 아닌 경우, Pod가 아예 삭제된다. 스케줄링이 안걸려있으므로)
* cordon : 더이상 해당 노드에 스케줄링하지 못하도록 설정


## Cluster Upgrade
* taint가 없는 Node를 Workload라고 표현한다.
* Updade
    ```
    $ kubeadm upgrade plan
    $ 
    ```


## API Server stop
```
$ crictl ps -a

$ crictl logs container-id
```



## 인증 방식
### User
1. CSR 생성
2. Approve 승인
3. config 파일 수정 (User/Context/Cluster)




## imagePullSecrets




## Trouble shooing
1. Application
    - Service Name
    - Service Port
2. 




## Kube admin 설치

* --apiserver-cert-extra-sans=controlplane,api.example.com : API 서버의 TLS 인증서에 추가적인 Subject Alternative Names (SANs)을 지정하는 데 사용
* --apiserver-advertise-address : 단일 컨트롤 플레인 주소
* --control-plane-endpoint : 다중 컨트롤 플레인(HA) 구성 시 사용. 로드밸런스 주소 지정
    - 둘중에 하나만 사용