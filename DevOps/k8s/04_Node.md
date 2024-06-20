# Node

---
## Pod 배치 전략 (Management)
크게 Cluster 에서 설정할 수 있는 ```cordon / drain```, Node에서는 ```nodeName / nodeSelector / affinity / taint,toleration```이 있다.
1. Node 자원 보호하기 (Node 선택 불가)
    * 해당 Node에 여러 문제가 발생할 수 있다. 이럴 때는 Node에 Pod가 생성하는 것이 좋지 않다.
    * 해당 Node에 Pod를 생성시키지 않기 위해서는 cordon 명령어를 사용한다. 명령어 사용시, 해당 Node에는 더이상 Scheduling이 적용되지 않는다(SchedulingDisabled)
        ```
        $ kubectl cordon <node_name>
        ```
2. Node 유지보수하기 (Node에서 Pod 옮기기)
    * 유지보수를 위해서 Node를 중지해야 되는 경우가 있을 수 있다.
    * 해당 Node에 있는 Pod를 다른 Node로 이동시키기 위해서는 drain 명령어를 사용한다. 명령어 사용시, 모든 Pod를 삭제하고 새로운 Node에 Pod를 생성한다.
        ```
        $ kubectl drain <node_name>
        $ kubectl drain <node_name> --ignore-daemonsets       # DaemonSet은 기본 drain으로 삭제
        
        ```
        * DaemonSet은 특성상 drain으로 삭제가 되지 않는다.
    > cordon 과 drain의 차이는 해당 Node 안에 Pod가 존재하냐 안하냐의 차이다!!
3. Pod를 특정 Node 선택하여 배포하기
    * nodeName : 동일한 이름을 가진 Node를 선택 
    * nodeSelector : 동일한 Label을 가진 Node를 선택
    * affinity : 더 디테일하게 Node를 선택 가능
    > nodeSelector vs affinity : 소규모이거나 간단하게 구분하는 경우 nodeSelector, 대규모에서는 affinity
4. Node에서 특정 Pod 제외하고 배포하기
    * taint : Node에다가 적용, "taint key:value" 등록
    * toleration : Pod에다가 적용, "taint" 등록
    * Effect 종류
        1) NoSchedule : Toleration가 일치하지 않은 Node에는 배포하지 못한다 (새로운 Pod에만 적용됨). 즉, 일치해야 배포된다
        2) NoExecute : 실행중인 Pod 중에 Toleration이 해당되면, 다른 Node로 옮기고 새로운 Pod를 Node에 배포하지 못하게 한다.
        3) PreferNoSchedule : 가급적이면 해당 Node에 배포하지 않는다는 의미이다.
    > Affinity로 Taint도 구성 가능하다! 반대로도 가능하다.
5. 
6. Pod Priority (우선순위)

</br>

### Node Affinity
Pod를 Node에 배치할 때, 선호도에 맞춰서 배치할 수 있다.
* 특징
    - Pod가 이미 스케줄링되어 특정 노드에서 실행 중이라면 중간에 해당 노드의 조건이 변경되더라도 이미 실행 중인 파드는 그대로 실행된다.
    - Node Label을 기준으로 연산을 진행한다.
* 선호도 유형
    - requiredDuringSchedulingIgnoredDuringExecution: 규칙이 충족되지 않으면 스케줄러는 Pod를 예약하지 않는다.
    - preferredDuringSchedulingIgnoredDuringExecution: 스케줄러는 규칙을 충족하는 노드를 찾으려고 시도하고, 일치하는 노드가 없는 경우 우선순위에 따라 Pod를 Node에 예약한다.
* 연산자 종류
    - In / NotIn
    - Exists / DoesNotExist
    - Gt / Lt
</br>

### Pod Affinity
기존에 배포된 Pod를 기준으로 Node를 결정한다.


> Master Pod가 배포된 Node는 피해서 Slave Pod를 배포한다.

</br>
</br>



---
## Management
기본적으로 Pod는 Sehlf-Healing 기능이 있다. (아직 잘 모르겠다!!!!!~~~?????)

1. Pod Update
    * Rolling Update를 진행하며, 기본 값은 전체의 "25%"이며 최솟값은 "1"이다
    * Image를 등록하고, 이미지를 바꾼다
        ```
        $ kubectl set image deployment <dpy_name> <containers_name>:<containers_image>
        $ kubectl set image deployment rollout-nginx nginx=nginx:1.16.0 --record

        # Object spec 수정 후
        $ kubectl apply -f ~/dpy-template.yaml --record
        ```
2. Pod Update 실패 시 복구
    * 배포 시 실수 또는 실패를 할 수 있다. 그렇기 때문에 복구할 수 있는 방법을 알고 있어야 한다.
    * rollout undo 명령어를 통해 이전 명령어 상태를 다시 적용한다 
        ```
        $ kubectl rollout history deployment rollout-nginx
        $ kubectl rollout undo deployment rollout-nginx

        $ kubectl describe deployment rollout-nginx
        ```
3. 특정 시점으로 복구
    * 전 단계가 아닌 특정 단계를 적용하기 위해서는 --to-revision 옵션을 사용한다.
        ```
        $ kubectl rollout undo deployment rollout-nginx --to-revision=1
        ```


## Affinity vs Selector
Pod를 생성하고 Node에 배치할 때, 어떤 Node에 배치할지 정해야 한다.