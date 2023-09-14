# Authentication & Authorization (인증 & 인가)
모든 사용자와 서비스는 Kubernetes 서비스를 사용하기 위해서는 API Server(Control Plane)에 인증 및 인가 작업을 받아야 한다. 이를 위해 인증 및 인가라는 과정을 거친다.
* 과정은 다음과 같다.
    1) Authentication 
    2) Authorization
    3) Admission Control
* 용어 정리
    *  JWT : JSON Web Tokens, JSON 형태로 정보를 정의한 토큰

---
# Authentication
모든 입력은 HTTP 요청이며, 하나 이상의 인증 모듈을 가지고 인증 체계를 구성할 수 있다.
* 특징
    1) k8s는 User 인증 정보를 저장하지 않고, 외부 시스템을 통해 인증(X.509 인증서, OIDC 등)을 사용 하다보니 내부 인증체계에 종속되는 부분이 거의 없다. 그렇다보니 인증 부분에 대한 확장성이 좋다.
    2) Group을 통해 권한을 동일하게 사용하게 할 수 있다
* 인증 주체
    - User Account : Cluster에 접근하는 관리자 및 사용자 (전역적이므로 Namespace에 걸처 고유해야 함)
        * 인증 정보 위치 : ```$HOME/.kube/config``` 파일에 저장
        * 저장 내용
            1) clusters : 접근할 Cluster 주소 / 인증 정보
            2) users : User 정보 / 인증 정보
            3) context : clusters와 users 끼리 매핑
        > Kubernetes에는 user Account를 나타내는 Object가 없다. 그렇기 때문에 API 호출을 통해 일반 사용자를 추가할 수 없다. 그렇기 때문에 클러스터의 인증 기관(CA)에서 서명한 유효한 인증서를 하나의 User로 생각한다.
    - Service Account : 사용자가 아닌 시스템, Pod에서 실행되는 Process에 대응하여 식별자(ID) 제공 (Namespace 별로 구분됨)
        * Service Account는 Namespace에 연결된다. 그리고 SA는 Secrets로써 저장되고 자격 증명 세트에 연결된다.
        * Secrets는 클러스터 내 프로세스가 Kubernetes API와 통신할 수 있도록 포드에 마운트시킴
        * 
* 인증 모듈 종류
    1. Client Certificates (X.509 인증서, TLS)
    2. Tokens
        - Plain Tokens
        - Bootstrap Tokens (Bearer Type)
        - Service Account Tokens
    3. OIDC (OAuth2)
    4. Webhook Tokens
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
</br>

---
## Client Certificates
Kubernetes API 사용에 대하여 인증서를 통해 접근을 제어할 수 있다 (X.509 등)
</br>


---
## Tokens
토큰을 활용하여 인증을 제어한다.
> 토큰의 문제점은 권한 토큰만 있으면 사용자와 상관없이 리소스에 접근가능하다!!!

## Plain Tokens
해당 Token은 무기한 지속되며, API를 다시 시작하지 않으면 Tocken 목록을 변경할 수 없다.
* 토큰 파일은 token, user_name, uid, 선택적 group_name 등 최소 3개의 열이 있는 csv 파일이다
    ```
    token,user,uid,"group1,group2,group3"
    ```
</br>


## Bootstrap Tokens (Bearer Type)
동적으로 관리되는 Bearer 토큰 유형
* 해당 토큰은 kube-system Namespace에 저장되며, 동적으로 생성되고 관리된다.
* Controller Manager에서는 Bootstrap Token이 만료되면 삭제하는 TokenCleaner 컨트롤러가 포함되어 관리하고 있다.
* 토큰의 형식은 [a-z0-9]{6}.[a-z0-9]{16} 이다. 첫 번째 구성 요소는 Token ID이고, 두 번째 구성 요소는 Token Secret이다. 다음과 같이 HTTP 헤더에 토큰을 지정한다.
    ```
    Authorization: Bearer 781292.db7bc3a58fc5f07e
    ```
</br>


## Service Account Tokens
서비스 계정은 서명된 전달자 토큰을 사용하여 요청을 확인하는 것이 자동으로 활성화된 인증자이다.
* 생성 방법은 다음과 같다.
    1. Create Service Account
        ```
        $ kubectl create serviceaccount jenkins
        ```
    2. Create an associated token
        ```
        $ kubectl create token jenkins
        ```
        * 이렇게 생성된 토근은 JWT(JSON Web Token)이다
    3. 필요한 Object Spec에 설정
> Service accounts authenticate with the username system:serviceaccount:(NAMESPACE):(SERVICEACCOUNT), and are assigned to the groups system:serviceaccounts and system:serviceaccounts:(NAMESPACE)
</br>



---
## OIDC Tokens (OpenID Connect)
사용자가 믿을 수 있는 Google, Facebook과 같은 곳에 인증을 맡기는 형태
* 외부와 통신이 되어야 한다
</br>


## Webhook Tokens


</br>
</br>




---
# Authorization
k8s에서는 여러 인증 모드를 제공한다.
1. Node
2. ABAC (Attribute-based access control)
3. RBAC (Role-based access control)
4. Webhook
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

## ABAC (Attribute-based access control)
속성 기반 액세스 제어는 속성을 결합하는 정책을 사용하여 사용자에게 권한을 부여
* Policy 정책 파일 사용
 

## Webhook
WebHook은 HTTP 콜백으로 어떤 일이 발생할 때 발생하는 HTTP POST이다(간단한 이벤트 알림). WebHooks을 구현하는 웹 애플리케이션은 특정 상황이 발생하면 URL에 메시지를 게시한다.
* 






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

