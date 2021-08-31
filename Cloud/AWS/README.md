# Amazon Web Services

## AWS Global Infra
* 전 세계 여러 
1. Region (리전)
   * 두 개 이상의 AZ로 구성
2. Available Zone (가용 영역)
   * 하나 이상의 DataCenter로 구성 (최대 6개)
   * 내결함성을 갖도록 설계
   * 다른 AZ의 장애로부터 격리되어 있지만(물리적 격리), Private Link를 통해 다른 가용 영역과 상호 연결된다.
3. DataCenter (데이터 센터)
   * 전 세계 여러 리전에 Cluster 형태로 구축
4. Edge Location
    * 최종 사용자에게 더 짧은 지연 시간으로 콘텐츠를 전송하기 위한 캐싱 서비스
</br>
</br>

# On-premise / AWS 비교
| On-premise           | AWS           | 비고                                                                       |
| -------------------- | ------------- | -------------------------------------------------------------------------- |
| VM (Vertual Machine) | EC2           | HCI(hypervisor converged infrastructure), Hypervisor 등을 이용하여 VM 생성 |
 HIWARE, Active Directory freeNAC      | IAM           | 통합 접근 및 계정권한 관리                                                 |
| HIWARE, Active Directory               | Organizations | 중앙 집중식 환경 관리 및 규제                                              |
| Firewall(Hardware) | ACL | 트래픽 필터링, 하드웨어 방화벽 |
| Firewall(Software) | SG | 트래픽 필터링, 소프트웨어 방화벽 |
|  | CloudWatch | 모니터링 |
| crontab | CloudWatch Rule | 작업 예약 스케줄러 |

