# Security


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
    - ConfigMap은 일반 평문이기 때문에, 암호화된 정보를 사용하기 위해서는 Secret을 사용해야 한다.