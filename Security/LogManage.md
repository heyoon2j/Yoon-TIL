# Log Manage

## Log 관리

* VPC Flow Log
    - 네트워크 트래픽
    - VPC 설정 / Log Archive Account : 개별 계정에서 설정
* 서버 접근에 대한 Log 
    - 3rd Party Solution 사용
* OS System Log
    - Syslog
    - 3rd Party Solution 사용 (IBM Qradar 등)
* Application Log
    - 중요 로그는 따로 백업 / Application별 S3에 저장
* Resource Monitoring Log
    - CloudWatch / AWS 자체적으로 저장
    - Config 설정 / Log Archive Account : S3 (AVM)
    - Monitroing Tool 사용 / 
* AWS 계정 관리 Log
    - CloudTrail에서 관리
* AWS Service Evnet Log
    - CloudTrail에서 관리
    - CloudTrail 추적 사용 / Log Archive Account : S3 (AVM)
* Security Hub Log
    - 보안 분석
* Guard duty : 보안 검사 설정


서버 접근 제어 Log
CloudTrail Log
CloudWath Resource Log
OS에 대한 Log
VPC Flow Log
Security Hub Log
계정 관리 Log
