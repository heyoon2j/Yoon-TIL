# Authentication & Authorization (인증 & 인가)
모든 사용자와 서비스는 Kubernetes 서비스를 사용하기 위해서는 API Server(Control Plane)에 인증 및 인가 작업을 받아야 한다. 이를 위해 인증 및 인가라는 과정을 거친다.
* 과정은 다음과 같다.
    1) Authentication 
    2) Authorization
    3) Admission Control
* 용어 정리
    - JWT : JSON Web Tokens, JSON 형태로 정보를 정의한 토큰
* 기본적인 인증 및 인가 과정
    1) User/Group or ServiceAccount 생성
    2) Role 바인딩

### Config File 작성
- User Account : Cluster에 접근하는 관리자 및 사용자 (전역적이므로 모든 Namespace에 걸처 고유해야 함)
    * 인증 정보 위치 : ```$HOME/.kube/config``` 파일에 저장
    * 저장 내용
        1) clusters : 접근할 Cluster 주소 / 인증 정보
        2) credentials : User 정보 / 인증 정보
        3) context : clusters와 users 끼리 매핑
    > Kubernetes에는 user Account를 나타내는 Object가 없다. 그렇기 때문에 API 호출을 통해 일반 사용자를 추가할 수 없다. 그렇기 때문에 클러스터의 인증 기관(CA)에서 서명한 유효한 인증서를 하나의 User로 생각한다.

    ```yaml
    apiVersion: v1
    clusters:
    - cluster:
        certificate-authority-data: DATA+OMITTED
        server: https://controlplane:6443
    name: kubernetes
    contexts:
    - context:
        cluster: kubernetes
        user: kubernetes-admin
    name: kubernetes-admin@kubernetes
    current-context: kubernetes-admin@kubernetes
    kind: Config
    preferences: {}
    users:
    - name: kubernetes-admin
    user:
        client-certificate-data: DATA+OMITTED
        client-key-data: DATA+OMITTED
    ```

    ```
    $ kubectl config use-context pord-user@production
    ```
</br>


---
# Authentication
![K8s_Authentication](img/K8s_Authentication.jpg)
모든 입력은 HTTP 요청이며, 하나 이상의 인증 모듈을 가지고 인증 체계를 구성할 수 있다.

* 특징
    - k8s는 User 인증 정보를 저장하지 않고, 외부 시스템을 통해 인증(X.509 인증서, OIDC 등)을 사용 하다보니 내부 인증체계에 종속되는 부분이 거의 없다. 그렇다보니 인증 부분에 대한 확장성이 좋다.
    - Group을 통해 권한을 동일하게 사용하게 할 수 있다
* 인증 주체
    1. User Account : Cluster에 접근하는 관리자 및 사용자 (전역적이므로 모든 Namespace에 걸처 고유해야 함)
        * 인증 정보 위치 : ```$HOME/.kube/config``` 파일에 저장
        * 저장 내용
            1) clusters : 접근할 Cluster 주소 / 인증 정보
            2) credentials : User 정보 / 인증 정보
            3) context : clusters와 users 끼리 매핑
        > Kubernetes에는 User Account를 나타내는 Object가 없다. 그렇기 때문에 API 호출을 통해 일반 사용자를 추가할 수 없다. 그렇기 때문에 클러스터의 인증 기관(CA)에서 서명한 유효한 인증서를 하나의 User로 생각한다.
    2. Service Account : 사용자가 아닌 시스템, Pod에서 실행되는 Process에 대응하여 식별자(ID) 제공 (Namespace 별로 구분됨) / ex> 프로메테우스, 젠킨스
        * Service Account는 Namespace에 연결된다. 그리고 SA는 Secrets로써 저장되고 자격 증명 세트에 연결된다.
        * Secrets는 클러스터 내 프로세스가 Kubernetes API와 통신할 수 있도록 포드에 마운트시킴
        - Namespace 생성 시, Namespace 용도인 기본 ServiceAccount가 생성된다 (v1.24 이전)
            1) Namespace 용도인 기본 ServiceAccount가 생성
            2) 최소 권한을 가진 Token용 Secret 생성 (ServiceAcccountToken)
            3) Pod 생성 시, Secret을 공유할 수 있는 볼륨이 자동 마운트 된다.
            > 위치 : /var/run/secrets/kuberne
        -  
* 인증 모듈 종류
    1. Certificates (X.509 인증서, TLS, CSR)
    2. Basic Auth (ID/PW) : ```--basic-auth-file=/etc/kubernetes/pki/id_pw_auth```
    3. Tokens
        - Plain Tokens
        - Bootstrap Tokens (Bearer Type)
        - Service Account Tokens
    4. OIDC (OAuth2)
    5. Webhook Tokens
    > 일반적으로 "서비스 계정의 서비스 계정 토큰" 과 "사용자 계정을 위한 하나 이상의 다른 방법" 사용한다!
* 그룹
    - system:masters : Full Access를 가진 Admin 그룹
    - system:authenticated : 사용자 인증을 통과한 그룹
    - system:anonymous : 사용자 인증을 하지 않은 익명 그룹
    - system:bootstrappers / system:bootstrap:<Token ID> : Bootstrap
    - system:serviceaccounts and system:serviceaccounts:(NAMESPACE) / system:serviceaccount:(NAMESPACE):(SERVICEACCOUNT) : ServiceAccount

### Ref
* k8s 공식문서
* https://coffeewhale.com/kubernetes/authentication/http-auth/2020/05/03/auth02/
* https://ssup2.github.io/theory_analysis/AWS_EKS_%EC%9D%B8%EC%A6%9D/
</br>
</br>


## 1. Certificates
Kubernetes API 사용에 대하여 인증서를 통해 접근을 제어할 수 있다(X.509 등). 
* Server 인증서
* Client 인증서
* 인증서 관리
    1) 인증서 생성 (in Cluster)
    2) 인증서 생성 (Manually)
    3) 인증서 삭제
    4) 인증서 갱신
</br>

### 인증서 생성 (in Cluster) 
![K8s_Authentication_Cluster](img/K8s_Authentication_Cluster.jpg)
* easyrsa, openssl, cfssl을 이용하여 Cluster용 인증서를 생성
    ```sh
    # ca.key
    # ca.crt
    # client.key
    # client.csr

    $ openssl req -new -key ca.key -subj "/O=tester/CN=test-user" -out ca.csr
    ```
* CertificateSigningRequest 생성
    ```sh
    $ cat <<EOF | kubectl apply -f -
    apiVersion: certificates.k8s.io/v1
    kind: CertificateSigningRequest
    metadata:
        name: my-svc.my-namespace
    spec:
        request: $(cat ca.csr | base64 | tr -d '\n')
        signerName: kubernetes.io/kube-apiserver-client
        usages:
        - digital signature
        - key encipherment
        - client auth
    EOF
    ```
    - request : 인증서 코드 (Encoding base64)
    - signerName : 승인할 서명자 이름
    - usages : 인증서 용도
* CertificateSigningRequest 인증서 승인
    ```sh
    $ kubectl certificate approve my-svc.my-namespace
    $ kubectl get csr/my-svc.my-namespace -o yaml
    $ kubectl get csr my-svc.my-namespace -o jsonpath='{.status.certificate}'| base64 -d > client.crt
    ```
* Client Side : Config File Setting
    ```
    $ kubectl config --kubeconfig=$HOME/.kube/config set-cluster test-cluster  --server=https://1.2.3.4 --insecure-skip-tls-verify=true

    $ kubectl config --kubeconfig=$HOME/.kube/config set-credentials test-user --client-certificate=client.crt --client-key=client.key
    $ kubectl config --kubeconfig=$HOME/.kube/config set-context test-context --cluster test-cluster --user=test-user
    $ kubectl config --kubeconfig=$HOME/.kube/config use-context test-context

    # Check
    $ kubectl --kubeconfig=$HOME/.kube/config get pod -n aws

    $ kubectl get pod -n kube-system --client-certificate=client.crt --client-key=client.key
    ```
</br>
</br>


### 인증서 생성 (Manually)
![K8s_Authentication_Custom](img/K8s_Authentication_Custom.jpg)
* easyrsa, openssl, cfssl을 이용하여 Cluster용 인증서를 생성
    ```
    ca.key
    ca.crt
    server.key
    server.crt
    client.key
    client.crt
    ```
* API Server Side : manifests 파일 수정
    ```
    $ sudo vi /etc/kubernetes/manifests/kube-apiserver.yaml

    --client-ca-file=/etc/kubernetes/pki/ca.crt
    --tls-cert-file=/etc/kubernetes/pki/server.crt
    --tls-private-key-file=/etc/kubernetes/pki/server.key
    ```
    - client-ca-file : Client의 인증서를 확인할 인증서 파일 (Client의 인증서가 공인인증을 받지 않은 경우, 인증받을 방법이 없기 때문에 필요하며, 여기서는 같은 Root CA 인증서 파일)
    - tls-cert-file : Cluster에서 전달한 인증서 파일
    - tls-private-key-file : Cluster에서 전달한 키 파일

* Client Side : Config File Setting
    ```
    $ kubectl config --kubeconfig=$HOME/.kube/config set-cluster test-cluster --server=https://1.2.3.4 --certificate-authority=ca.crt
    $ kubectl config --kubeconfig=$HOME/.kube/config set-cluster test-cluster --server=https://1.2.3.4 --insecure-skip-tls-verify=true

    $ kubectl config --kubeconfig=$HOME/.kube/config set-credentials test-user --client-certificate=client.crt --client-key=client.key
    $ kubectl config --kubeconfig=$HOME/.kube/config set-context test-context --user=test-user

    # Check
    $ kubectl --kubeconfig=$HOME/.kube/config get pod -n aws

    $ kubectl get pod -n kube-system --client-certificate=client.crt --client-key=client.key

    $ curl -v -k https://localhost:6443/api/v1/pods -u "user1:password123"
    ```

    - certificate-authority-data : Cluster의 Certificate가 공인인증이 아닌 경우, Cluster 인증서를 인증해줄 인증서가 필요하다. 그래서 "insecure-skip-tls-verify: true"인 경우 해당 부분을 공백으로 설정. "false"인 경우, 접속하고자하는 k8s master node의 certificate-authority-data(Root Cert)를 넣어줘야 한다.
    - certificate-authority : data가 아닌 경로를 넣어준다
    - client-certificate / client-key : Root Cert로부터 사인된 하위 인증서와 비밀 키 파일 경로
    - insecure-skip-tls-verify : 인증서에 대하여 공인기관에 검증하는 과정을 건너뛴다
</br>




### 인증서 갱신
</br>
</br>



## 2. Basic Authentication
* API Server Side
    - manifests 파일 수정
    ```
    $ sudo /etc/bin/echo 'pw1@,test1,test1,system:masters' > /etc/kubernetes/pki/id_pw_auth
    $ sudo vi /etc/kubernetes/manifests/kube-apiserver.yaml

    --basic-auth-file=/etc/kubernetes/pki/basic-auth

    ```

* Client Side
    - Config file setting
    ```
    $ kubectl config --kubeconfig=$HOME/.kube/config set-cluster test-cluster --server=https://1.2.3.4
    $ kubectl config --kubeconfig=$HOME/.kube/config set-credentials test-user --username=test1 --password=pw1@
    $ kubectl config --kubeconfig=$HOME/.kube/config set-context test-context --user=test-user


    $ kubectl --kubeconfig=$HOME/.kube/config get pod -n aws
    $ kubectl get pod -n aws --username test1 --password pw1@
    ```
</br>
</br>


## 3. Tokens
토큰을 활용하여 인증을 제어한다.
> 토큰의 문제점은 권한 토큰만 있으면 사용자와 상관없이 리소스에 접근가능하다!!!

### Plain Tokens
해당 Token은 무기한 지속되며, API를 다시 시작하지 않으면 Tocken 목록을 변경할 수 없다.
* 토큰 파일은 token, user_name, uid, 선택적 group_name 등 최소 3개의 열이 있는 csv 파일이다
    ```
    token,user,uid,"group1,group2,group3"
    ```
    - token : 토큰 이름
    - user : 사용자 이름
    - uid : k8s에서 인식하는 식별자 (보통 user와 동일하게 가주감)
    - group : k8s 내부 그룹 (namespace?)
* API Server Side
    - manifests 파일 수정
    ```
    $ sudo /etc/bin/echo 'plain-token-key,test1,test1,system:masters' > /etc/kubernetes/pki/plain_token_auth
    $ sudo vi /etc/kubernetes/manifests/kube-apiserver.yaml

    --token-auth-file=/etc/kubernetes/pki/plain_token_auth
    ```
* Client Side
    - Config file setting
    ```
    $ kubectl config --kubeconfig=$HOME/.kube/config set-cluster test-cluster --server=https://1.2.3.4
    $ kubectl config --kubeconfig=$HOME/.kube/config set-credentials test-user --token plain-token-key
    $ kubectl config --kubeconfig=$HOME/.kube/config set-context test-context --user=test-user


    $ kubectl --kubeconfig=$HOME/.kube/config get pod -n aws
    $ kubectl get pod -n aws --token plain-token-key
    ```
</br>



### Bootstrap Tokens (Bearer Type)
동적으로 관리되는 Bearer 토큰 유형
* 해당 토큰은 kube-system Namespace에 저장되며, 동적으로 생성되고 관리된다.
* Controller Manager에서는 Bootstrap Token이 만료되면 삭제하는 TokenCleaner 컨트롤러가 포함되어 관리하고 있다.
* 토큰의 형식은 [a-z0-9]{6}.[a-z0-9]{16} 이다. 첫 번째 구성 요소는 Token ID이고, 두 번째 구성 요소는 Token Secret이다. 다음과 같이 HTTP 헤더에 토큰을 지정한다.
    ```
    Authorization: Bearer 781292.db7bc3a58fc5f07e
    
    # Token ID : 781292
    # Token Secret : db7bc3a58fc5f07e
    ```
</br>


### Service Account Tokens
서비스 계정은 서명된 전달자 토큰을 사용하여 요청을 확인하는 것이 자동으로 활성화된 인증자이다.

* SericeAccount 토큰 생성 방식
    1. 수동 생성 : kubectl create token
        - 수동으로 단기 토큰을 생성 (임시 사용이나 테스트 용도)
    2. Secret 생성 : Secret - typ: kubernetes.io/service-account-toke
        - 장기 토큰을 수동으로 생성합니다.
        > 보안상의 이유로 권장되지 않습니다. 공유 가능하므로
    3. Projected 볼륨을 통한 Pod 내부 볼륨 생성 및 마운트
        - Pod 스펙에 projected 볼륨을 정의하여 자동으로 토큰을 생성하고 관리
        - 가장 안전하고 권장되는 방법
        - 토큰의 수명주기가 자동으로 관리
        - 토큰은 개발자가 HTTP 요청의 Authorization 헤더에 "Bearer <token>" 형식으로 토큰을 포함시켜 사용

* Secret Token 생성 방법은 다음과 같다.
    1. Create Service Account
        ```
        $ kubectl create serviceaccount jenkins <-n namespace>
        ```
    2. Create an associated token (수동으로 생성하는 방법, 보통 Secret 생성)
        ```
        $ kubectl create token jenkins
        ```
        * 이렇게 생성된 토근은 JWT(JSON Web Token)이다
    3. Secret 생성
        ```
        $ kubectl apply -f - <<EOF
        apiVersion: v1
        kind: Secret
        metadata:
            name: jenkins-secret
            annotations:
                kubernetes.io/service-account.name: jenkins
        type: kubernetes.io/service-account-token
        EOF

        $ kubectl describe secrets/jenkins-secret
        ```
    4. Token 사용
        ```
        JWT_TOKEN=$(kubectl get secret jenkins-secret -o json | jq -r .data.token | base64 -d)
        echo $JWT_TOKEN

        kubectl api-versions --token $JWT_TOKEN
        ```
    5. 필요한 Object Spec에 설정 (RoleBinding)
        - Service Account에 RoleBinding
    > Service accounts authenticate with the username system:serviceaccount:(NAMESPACE):(SERVICEACCOUNT), and are assigned to the groups system:serviceaccounts and system:serviceaccounts:(NAMESPACE)

</br>
</br>



## 4. OIDC Tokens (OpenID Connect)
사용자가 믿을 수 있는 Google, Facebook과 같은 곳에 인증을 맡기는 형태
* 외부와 통신이 되어야 한다
* API Server Side
    - oidc-issuer-url : OIDC Provider Site URL (인증받을 사이트 주소)
    - oidc-client-id : OIDC Provier에서 접속할 ID
    - manifests 파일 수정
    ```
    $ sudo vi /etc/kubernetes/manifests/kube-apiserver.yaml

    --oidc-issuer-url=https://amazon.oidc.com
    --oidc-client-id=<<CLIENT_ID>
    --oidc-ca-file=/etc/kubernetes/pki/<<IDP_CA.crt>> 
    ```

* Client Side
    - Config file setting
    > ID Token도 인증서와 동일하며 수명이 짧기 때문에 그때마다 다시 받고 설정해야 한다..
    ```
    $ kubectl config --kubeconfig=$HOME/.kube/config set-cluster test-cluster --server=https://1.2.3.4
    $ kubectl config set-credentials oidc-user \
        --auth-provider=oidc \
        --auth-provider-arg=idp-issuer-url=( issuer url ) \
        --auth-provider-arg=client-id=( your client id ) \
        --auth-provider-arg=client-secret=( your client secret ) \
        --auth-provider-arg=refresh-token=( your refresh token ) \
        --auth-provider-arg=idp-certificate-authority=( path to your ca certificate ) \
        --auth-provider-arg=id-token=( your id_token )
    $ kubectl config --kubeconfig=$HOME/.kube/config set-context test-context --cluster=test-cluster --user=test-user


    $ kubectl --kubeconfig=$HOME/.kube/config get pod -n aws --user oidc-user
    $ kubectl get pod -n aws --token <<ID_Token_Info>>
    ```

* Role binding
    - username은 기본적으로 id_token의 ```iss#sub``` 이다
    ```
    kubectl create clusterrolebinding oidc-cluster-admin --clusterrole=cluster-admin --user='https://amazon.oidc.com/qwer1234zxcv-asdfzxcv-da#7e-4818-ae58-39add6bd7e6a'
    ```
</br>


## 5. Webhook Tokens
Kubernetes가 제공하는 것이 아닌 완전히 다른 외부 인증을 수행할 수 있다.
</br>
</br>


## 6. Proxy
</br>
</br>




---
---
# Authorization
k8s에서는 여러 인증 모드를 제공한다.
1. Node
2. ABAC (Attribute-based access control) == AWS IAM User/Group에 Policy 추가하는 방식
3. RBAC (Role-based access control) == AWS IAM Role
4. Webhook : 정책 관리를 다른 외부에다가 요청하는 형태
5. IAM
* 해당 권한이 있는지 확인하는 명령어
    ```
    $ kubectl auth can-i create deployments --namespace dev
    ```
* Authorization Mode 할성화
    ```
    $ kube-apiserver --authorization-mode=Example,RBAC --other-options --more-options
    ```
    - 쉼표로 분리
</br>



### Ref
* https://aws-diary.tistory.com/129
* https://docs.aws.amazon.com/eks/latest/userguide/add-user-role.html
</br>

## Node
노드 인증은 kubelet에서 수행한 API 요청을 특별히 인증하는 특수 목적의 인증 모드


## RBAC (Role-based access control)
역할을 기반으로 컴퓨터 또는 네트워크 리소스에 대한 액세스를 규제하는 방법
* Role, ClusterRole, RoleBinding, ClusterRoleBinding Object 생성이 필요
* Role / RoleBinding : Namespace에 종속되어 있기 때문에, 특정 Namespace 안에 있는 Object에 대해서 역할 부여
* ClusterRole / ClusterRoleBinding : Cluster 전반에 걸처 Object에 대해서 역할 부여 (PV 등)
    - apiGroups : Object Spec의 속성에 있는 apiVersion에서 버전을 뺀 앞부분 (ex> "app", "rbac.authorization.k8s.io" 등)
    - resource : 
    - verbs : 
    > core api는 apiGroups가 없다. core api에는 pod, service, pv, pvc, node, namespace, configmap, endpoint 등이 있다.
</br>


## ABAC (Attribute-based access control)
속성 기반 액세스 제어는 속성을 결합하는 정책을 사용하여 사용자에게 권한을 부여
* Policy 정책 파일 사용
</br>


## Webhook
WebHook은 HTTP 콜백으로 어떤 일이 발생할 때 발생하는 HTTP POST이다(간단한 이벤트 알림). WebHooks을 구현하는 웹 애플리케이션은 특정 상황이 발생하면 URL에 메시지를 게시한다.
</br>
 






---
# Admission Control
요청을 가로채 유효성을 검사하거나, 수정, 거부할 수 있는 소프트웨어 모듈 (입장 통제)
* 객체를 생성, 수정, 삭제 또는 연결하는 요청에 따라 작동한다.
* Plugin 활성화 방법
    - Plugin 활성화
        ```
        $ kube-apiserver --enable-admission-plugins=NamespaceLifecycle,LimitRanger ...
        ```
    - Plugin 비활성화
        ```
        $ kube-apiserver --disable-admission-plugins=PodNodeSelector,AlwaysDeny ...
        ```
    - Plugin 리스트 확인
        ```
        $ kube-apiserver -h | grep enable-admission-plugins
        ```

