# Scheduling

## Scheduling 과정
1) 우선순위가 높은거부터 순차적으로 Pod 작업 진행 (Scheduling Queue) - PrioritySort
2) Pod 설정에 따라 노드 필터링 진행 (Filtering) - NodeResourcesFit / NodeName / NodeUnschedulable
3) 노드에 상황에 따라 가장 적합한 순서대로 점수가 매겨짐 (Scoring) - NodeResourceFit / ImageLocality
4) 가장 점수에 점수에 Pod가 배포됨 (Binding) - DefaultBinder

> 각 플로그인을 통해 확장도 가능하고, 커스텀도 가능하다









기본적으로 컨테이너는 노드 리소스 사용에 대한 제한이 걸려있지 않다. 즉, 한계까지 사용할 수 있기 때문에 조절이 피룡할 수 있다.



## Multi Scheduler
* 기본 kube-scheduler가 아닌 사용자 정의 스케줄러를 사용할 수 있다.




---
## Pod 스케줄링
크게 Cluster 에서 설정할 수 있는 ```cordon / uncordon```, Node에서는 ```nodeName / nodeSelector / affinity / taint,toleration```이 있다.

nodeName을 제외하고 Scheduler가 관리되며, 우선순위는 다음과 같다. cordon > taint/toleration > nodeSelector > Affinity 순이다. nodeName은 Scheduler가 관리하지 않기 때문에 기존 설정과 충돌되는 경우가 있다 (ex> 생성/삭제 무한 반복)

1. Node 스케줄링 불가 (Node 선택 불가)
    * 해당 Node에 여러 문제가 발생할 수 있다. 이럴 때는 Node에 Pod가 생성하는 것이 좋지 않다.
    * 해당 Node에 Pod를 생성시키지 않기 위해서는 cordon 명령어를 사용한다. 명령어 사용시, 해당 Node에는 더이상 Scheduling이 적용되지 않는다 (SchedulingDisabled)
        > 기존 Pod는 영향을 받지 않는다!
        ```sh
        $ kubectl cordon <node_name>

        $ kubectl uncordon node-1
        ```
2. Node 스케줄링 불가 - 감염
    * taint : Node에다가 적용, "taint key:value" 등록
    * toleration : Pod에다가 적용, "taint" 등록
    * Effect 종류
        1) NoSchedule : Toleration가 일치하지 않은 Node에는 배포하지 못한다 (새로운 Pod에만 적용됨). 즉, 일치해야 배포된다
        2) NoExecute : 실행중인 Pod 중에 Toleration이 적용되어 있지 않으면, 다른 Node로 옮기고 새로운 Pod를 Node에 배포하지 못하게 한다.
        3) PreferNoSchedule : 가급적이면 해당 Node에 배포하지 않는다는 의미이다.
3. Pod를 특정 Node 선택하여 배포하기
    * nodeName : 동일한 이름을 가진 Node를 선택 
    * nodeSelector : 동일한 Label을 가진 Node를 선택
    > nodeSelector vs affinity : 소규모이거나 간단하게 구분하는 경우 nodeSelector, 대규모에서는 affinity
4. Affinity : 더 디테일하게 Node를 선택 가능
    * Node affinity
    * Pod affinity
5. Pod Priority (우선순위)
6. Node 유지보수하기 (Node에서 Pod 옮기기)
    * 유지보수를 위해서 Node를 중지해야 되는 경우가 있을 수 있다.
    * 해당 Node에 있는 Pod를 다른 Node로 이동시키기 위해서는 drain 명령어를 사용한다. 명령어 사용시, 모든 Pod를 삭제하고 새로운 Node에 Pod를 생성한다.
        ```
        $ kubectl drain <node_name>
        $ kubectl drain <node_name> --ignore-daemonsets       # DaemonSet은 기본 drain으로 삭제
        
        ```
        * DaemonSet은 특성상 drain으로 삭제가 되지 않는다.
    > 옮길 곳이 없는 경우, 삭제된다!!!

    > cordon 과 drain의 차이는 해당 Node 안에 Pod가 존재하냐 안하냐의 차이다!!

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

잘 사용되지 않음!

> Master Pod가 배포된 Node는 피해서 Slave Pod를 배포한다.

</br>
</br>
