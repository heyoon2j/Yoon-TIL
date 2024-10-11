# Monitoring
Kubernetes Resource를 모니터링하기 위해서는 Scheduler, Kubelet, Controller와 같은 Cluster 구성 요소에서 Metric을 수집하여 통합하고 시각화를 해야 한다(수집 - 통합 - 시각화)


## cAdvisor
Kubernetes에서 Container의 자원 현황을 모니터링하기 위해서 "cAdvisor" 솔루션을 기본으로 제공한다. "cAdvisor"는 컨테이너에 대한 정보를 수집, 처리 등을 하는 DaemonSets이다.
- kubelet에 기본적으로 탑재되어 있는 데몬이다.
- kubelet에서 해당 데이터들을 가져오기 위해서는 "Mecrics Server"가 별도로 필요하다!
    1) Metrics Server: 기본 지표 확인 가능 ("kubectl top node" 사용 가능)
    2) Prometheus: kubelet을 통해 상세 지표를 가지고 옴 (커스터마이징 가능)


