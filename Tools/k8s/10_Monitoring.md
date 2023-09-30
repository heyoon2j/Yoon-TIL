# Monitoring
Kubernetes Resource를 모니터링하기 위해서는 Scheduler, Kubelet, Controller와 같은 Cluster 구성 요소에서 Metric을 수집하여 통합하고 시각화를 해야 한다(수집 - 통합 - 시각화)
* Container 자원 현황 수집
    * cAdvisor


## cAd황isor
Kubernetes에서 Container의 자원 현황을 모니터링하기 위해서 "cAdvisor" 솔루션을 기본으로 제공한다. "cAdvisor"는 컨테이너에 대한 정보를 수집, 처리 등을 하는 Daemon이다.
* kubelet에 기본적으로 탑재되어 있으



