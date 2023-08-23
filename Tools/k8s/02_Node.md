# Node


## Management
1. Node 자원 보호하기
    * 해당 Node에 여러 문제가 발생할 수 있다. 이럴 때는 Node에 Pod가 생성하는 것이 좋지 않다.
    * 해당 Node에 Pod를 생성시키지 않기 위해서는 cordon 명령어를 사용한다. 명령어 사용시, 해당 Node에는 더이상 Scheduling이 적용되지 않는다(SchedulingDisabled)
        ```
        $ kubectl cordon <node_name>
        ```
2. Node 유지보수하기
    * 유지보수를 위해서 Node를 중지해야 되는 경우가 있을 수 있다.
    * 해당 Node에 있는 Pod를 다른 Node로 이동시키기 위해서는 drain 명령어를 사용한다. 명령어 사용시, 모든 Pod를 삭제하고 새로운 Node에 Pod를 생성한다.
        ```
        $ kubectl drain <node_name>
        $ kubectl drain <node_name> --ignore-daemonsets       # DamonSet은 기본 drain으로 삭제
        
        ```
        * DaemonSet은 특성상 drain으로 삭제가 되지 않는다.
> cordon 과 drain의 차이는 해당 Node 안에 Pod가 존재하냐 안하냐의 차이다!!
</br>





---
# Pod

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
