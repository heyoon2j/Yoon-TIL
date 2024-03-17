# Athena

* 동시 Query 수 == 20개 (Burst 80개)


## Monitoring
* DPUAllocated - 쿼리를 실행하기 위해 용량 예약에 프로비저닝된 총 데이터 처리 단위(DPU) 수입니다.

* DPUConsumed - 예약에서 특정 시간에 RUNNING 상태의 쿼리가 소비하는 DPU 수입니다. 작업 그룹이 용량 예약과 연결되어 있고 예약과 연결된 모든 작업 그룹을 포함하는 경우에만 생성되는 지표입니다.

* DPUCount - 쿼리가 소비하는 최대 DPU 수로, 쿼리가 완료될 때 정확히 한 번 게시됩니다.

* EngineExecutionTime - 쿼리를 실행하는 데 걸린 시간(밀리초)입니다.

* ProcessedBytes - DML 쿼리당 Athena가 스캔한 바이트 수입니다.

* QueryPlanningTime - Athena가 쿼리 처리 흐름을 계획하는 데 걸린 시간(밀리초)입니다.

* QueryQueueTime - 쿼리가 리소스를 기다리면서 쿼리 대기열에 있던 시간(밀리초)입니다.

* ServicePreProcessingTime - Athena가 쿼리 엔진에 쿼리를 제출하기 전에 쿼리를 사전 처리하는 데 걸린 시간(밀리초)입니다.

* ServiceProcessingTime - 쿼리 엔진이 쿼리 실행을 완료한 후 Athena가 쿼리 결과를 처리하는 데 걸린 시간(밀리초)입니다.

* TotalExecutionTime - Athena가 DDL 또는 DML 쿼리를 실행하는 데 걸린 시간(밀리초)입니다.





하기까지