# Config
구성 설정, 환경 변수, 인증 등의 정보들을 사용하는 방법에는 여러가지가 있다.

* 환경 변수
    - Container에서 필요한 환경 변수를 저장하는 용도
* ConfigMap
    - 구성 설정, 환경 변수 등의 정보를 저장하는 용도
    - 데이터는 1MiB로 제한
    - DB에 저장
* Secret
    - Password, OAuth Token, SSH KEY와 같은 민감한 정보를 저장하는 용도
    - 크기는 1 MiB로 제한
    - kubelet 메모리에 저장됨. 그렇기 때문에 매우 큰 Secret 생성은은 메모리를 고갈시키거나 Overflow시킬 수 있다. 그러나, 작은 크기의 Secret을 많이 만드는 것도 메모리를 고갈시킬 수 있다
        > 리소스 쿼터를 사용하여 한 네임스페이스의 시크릿 (또는 다른 리소스) 수를 제한할 수 있다.
    - 메모리에 저장??
</br>







## ConfigMap
* 용도
    - 구성 및 설정 정보를 YAML 파일 또는 환경 변수로 저장
    - 데이터를 볼륨에 마운트시켜 파일로 저장
* 저장 데이터
    - data : UTF-8 문자열
    - binaryData : base64로 인코딩된 문자열




</br>
</br>



---
## Secret
암호화가 필요한 내용을 저장할 때 사용하는 Object
* Secret이지만 실제로는 인코딩만되고 암호화되지 않는다 (https://cwal.tistory.com/47)
* 암호화 방법 : https://kubernetes.io/docs/tasks/administer-cluster/encrypt-data/
    1) EncryptionConfiguration 구성 : etcd 저장 시에 암호화하도록 적용시킨다.
    2) Authentication & Athorization을 통해 Secret에 접근을 제한한다.
    > 하지만 암호화 방법도 etcd에 저장할때만 암호화되므로, API Server가 해킹당하면 말짱 꽝이다. (보안 적용 필요!!!)
1. EncryptionConfiguration 구성
    - etcd에 저장 시 암호화하는 방식
    - 암호화할 Object 지정 가능
2. Authentication & Athorization
    - Secret 접근 자체를 인증을 통해서만 접근 가능하도록 하는 방식
    - Type은 기존 인증방식과 동일 (ref : https://kubernetes.io/docs/concepts/configuration/secret/)
</br>
</br>


### 사용 방법
1. 환경 변수 사용방법
    ```yaml
    apiVersion: v1
    kind: Pod
    metadata:
      name: dapi-envars-resourcefieldref
    spec:
    containers:
      - name: test-container
        image: registry.k8s.io/busybox:1.24
        command: [ "sh", "-c"]
        args:
        - while true; do
            echo -en '\n';
            echo $EFAULT_ENV_TEST;
            printenv MY_NODE_NAME MY_POD_NAME MY_POD_NAMESPACE MY_POD_IP MY_POD_SERVICE_ACCOUNT;
            printenv MY_CPU_REQUEST MY_CPU_LIMIT;
            sleep 10;
            done;
        resources:
          requests:
          memory: "32Mi"
          cpu: "125m"
          limits:
          memory: "64Mi"
          cpu: "250m"
        env:
          - name: DEFAULT_ENV_TEST              # Default Environment
            value: 123
          - name: MY_NODE_NAME                  # Pod 정보 가지고오기
            valueFrom:
              fieldRef:
                fieldPath: spec.nodeName
          - name: MY_POD_NAME
            valueFrom:
              fieldRef:
                fieldPath: metadata.name
          - name: MY_POD_NAMESPACE
            valueFrom:
              fieldRef:
                fieldPath: metadata.namespace
          - name: MY_POD_IP
            valueFrom:
              fieldRef:
                fieldPath: status.podIP
          - name: MY_POD_SERVICE_ACCOUNT
            valueFrom:
              fieldRef:
                fieldPath: spec.serviceAccountName        
          - name: MY_CPU_REQUEST                # 컨테이너 정보 가지고오기
            valueFrom:
              resourceFieldRef:
                containerName: test-container
                resource: requests.cpu
          - name: MY_CPU_LIMIT
            valueFrom:
              resourceFieldRef:
                containerName: test-container
                resource: limits.cpu
    restartPolicy: Never
    ``` 
    - ```valueFrom``` : Pod & Container 정보를 가지고 올 수 있다.
    - ```fieldRef``` : Pod 정보를 가지고 올 수 있다.
    - ```resourceFieldRef``` : Container 정보를 가지고 올 수 있다. 

2. ConfigMap 사용
    ```yaml
    apiVersion: v1
    kind: ConfigMap
    metadata:
      name: game-demo
    data:
      # 속성과 비슷한 키; 각 키는 간단한 값으로 매핑됨
      player_initial_lives: "3"
      ui_properties_file_name: "user-interface.properties"

      # 파일과 비슷한 키
      game.properties: |
        enemy.types=aliens,monsters
        player.maximum-lives=5    
      user-interface.properties: |
        color.good=purple
        color.bad=yellow
        allow.textmode=true
    immutable: true                         # 해당 옵션이 있으면, 변경이 적용되지 않는다 (적용 불가능)
    ```
    ```yaml
    apiVersion: v1
    kind: Pod
    metadata:
      name: configmap-demo-pod
    spec:
    containers:
      - name: demo
        image: alpine
        command: ["sleep", "3600"]
        
        # 환경 변수 정의
        #envFrom:
        #  - configMapRef:              # ConfigMap 전체를 한번에 하는 방법
        #      name : game-demo
        env:
          - name: PLAYER_INITIAL_LIVES # 참고로 여기서는 컨피그맵의 키 이름과
          # 대소문자가 다르다.
            valueFrom:
              configMapKeyRef:
                name: game-demo           # 이 값의 컨피그맵.
                key: player_initial_lives # 가져올 키.
          - name: UI_PROPERTIES_FILE_NAME
            valueFrom:
              configMapKeyRef:
                name: game-demo
                key: ui_properties_file_name
        volumeMounts:
          - name: config
            mountPath: "/config"
            readOnly: true
    volumes:
      # 파드 레벨에서 볼륨을 설정한 다음, 해당 파드 내의 컨테이너에 마운트한다.
      # ConfigMap의 키와 일치하는 이름을 가진 디렉터리 파일이 생성 (특정 Key만으로도 사용 가능)
      - name: config
        configMap:
          # 마운트하려는 컨피그맵의 이름을 제공한다.
          name: game-demo
          
          # 특정 Key만 설정 가능
          # 컨피그맵에서 파일로 생성할 키 배열
          items:                                # 2개의 파일이 생성된다. 
            - key: "game.properties"
              path: "game.properties"               # /config/game.properties 파일 생성, 해당 Key의 값이 저장됨
            - key: "user-interface.properties"
              path: "user-interface.properties"     # /config/user-interface.properties 파일 생성,  해당 Key의 값이 저장됨
    ```
    - ```configMapKeyRef``` : ConfigMap 정보를 가지고 올 수 있다.
    - Volumes에 ConfigMap을 사용하는 것은 파일을 해당 위치에 마운트 시키는 것이다.




3. Secret 사용
    ```yaml
    apiVersion: v1
    kind: Secret
    metadata:
      name: secret-sa-sample
      annotations:
        kubernetes.io/service-account.name: "sa-name"
    type: kubernetes.io/service-account-token
    data:
      # You can include additional key value pairs as you do with Opaque Secrets
      extra: YmFyCg==
    ``` 
    ```yaml
    apiVersion: v1
    kind: Pod
    metadata:
      name: secret-env-pod
    spec:
      containers:
        - name: mycontainer
          image: redis
          env:
            - name: SECRET_USERNAME
              valueFrom:
                secretKeyRef:
                  name: mysecret
                  key: username
                  optional: false # 기본값과 동일하다
            - name: SECRET_PASSWORD
              valueFrom:
                secretKeyRef:
                  name: mysecret
                  key: password
                  optional: false # 기본값과 동일하다
    restartPolicy: Never

    volumes:
      - name: foo
        secret:
          secretName: secret-sa-sample
          defaultMode: 0400                     # 파일 권한 설정
        optional: false                         # 기본값
      - name: bar
        secret:
          secretName: secret-sa-sample
          items:
          - key: extra
            path: my-group/my-username
    ```
    - ```secretKeyRef``` : Secret 정보를 가지고 올 수 있다.
    - Volumes에 Secret을 사용하는 것은 파일을 해당 위치에 마운트 시키는 것이다.
    - ```defaultMode: 0400``` 파일 권한 설정 가능

4. x
    ```
    ``` 







