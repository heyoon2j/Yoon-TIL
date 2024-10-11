# Security



## Primary Security
- Who can access? : 누가 접근할 수 있게 할건지
- What can they do? : 그 대상이 어떤 걸 할 수 있게 할건지


## Authentication


## 
Pod Security Policy



## 보안 사항
* automountServiceAccountToken: false
    - 자동으로 Pod에 ServiceAccount Token이 마운트되지 않도록 한다.
    - 필요시 ```kubectl create token <serviceAccount_name>```을 통해 획득한다.
    - Ref : https://ikcoo.tistory.com/389
* imagePullSecrets 설정
    - Private 이미지를 기반으로 컨테이너를 실행하는 데 권장되는 접근 방식 (컨테이너 이미지 레지스트리 키 사용하여 접근)
    - 방식 : 컨테이너 이미지 레지스트리 키 Secret 생성 ---> Pod yaml 파일에 해당 키 추가
    - Ref : https://kubernetes.io/docs/tasks/configure-pod-container/configure-service-account/
    - https://www.ibm.com/docs/ko/cloud-private/3.2.x?topic=images-creating-imagepullsecrets-specific-namespace
* Secret
    - Secret은 인코딩되어 있는 문서이다. 암호화가 아니다!
    1. EncryptionConfiguration 구성
        - etcd에 저장 시 암호화하는 방식
        - 암호화할 Object 지정 가능
    2. Authentication & Athorization
        - Secret 접근 자체를 인증을 통해서만 접근 가능하도록 하는 방식 (접근 제어가 필요하다!!!!!!)
        - Type은 기존 인증방식과 동일 (ref : https://kubernetes.io/docs/concepts/configuration/secret/)



## EncryptionConfiguration 암호하하기
1) EncryptionConfiguration 리소스 생성 (Path : /etc/kubernetes/enc/enc.yaml)
    ```
    ```
2) /etc/kubernetes/manifests/kube-apiserver.yaml 파일 수정
3) "--encryption-provider-config=/etc/kubernetes/enc/enc.yaml" 옵션 추가
4) 볼륨 마운트 옵션 추가 (kube-apiserver 컨테이너 내부에서 접근해야되기 때문에)
    ```
    apiVersion: v1
    kind: Pod
    metadata:
    annotations:
      kubeadm.kubernetes.io/kube-apiserver.advertise-address.endpoint: 10.20.30.40:443
    creationTimestamp: null
    labels:
      app.kubernetes.io/component: kube-apiserver
      tier: control-plane
    name: kube-apiserver
    namespace: kube-system
    spec:
    containers:
    - command:
      - kube-apiserver
      ...
      - --encryption-provider-config=/etc/kubernetes/enc/enc.yaml  # add this line
      ...
      volumes:
      - name: encryption-config
        hostPath:
        path: /etc/kubernetes/encryption-config.yaml  # 호스트 파일 경로

      volumeMounts:
      - name: encryption-config
        mountPath: /etc/kubernetes/encryption-config.yaml  # 컨테이너 내부에서 마운트될 경로
        readOnly: true
    ```