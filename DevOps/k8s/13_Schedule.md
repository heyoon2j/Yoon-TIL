# Scheduling




기본적으로 컨테이너는 노드 리소스 사용에 대한 제한이 걸려있지 않다. 즉, 한계까지 사용할 수 있기 때문에 조절이 피룡할 수 있다.




## Scheduling 과정
1) 우선순위가 높은거부터 순차적으로 Pod 작업 진행 (Scheduling Queue) - PrioritySort
2) Pod 설정에 따라 노드 필터링 진행 (Filtering) - NodeResourcesFit / NodeName / NodeUnschedulable
3) 노드에 상황에 따라 가장 적합한 순서대로 점수가 매겨짐 (Scoring) - NodeResourceFit / ImageLocality
4) 가장 점수에 점수에 Pod가 배포됨 (Binding) - DefaultBinder

> 각 플로그인을 통해 확장도 가능하고, 커스텀도 가능하다

