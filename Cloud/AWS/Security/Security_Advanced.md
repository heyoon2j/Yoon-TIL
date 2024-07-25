

Firewall Manager ---> Security Hub (알람 전송)




Pre-Shared Key : 대칭키
RSA : 공개키 암호화 알고리즘 (Public Key / Private Key)




인증서 구조
- 인증서 정보 : 인증서 관련 정보
- 서명 (Signature) : 인증서 정보를 상위 인증서의 Private Key로 암호화한 Hash 값으로 무결성 검증용으로 사용
- 인증서 Public Key : 인증서에 제공되는 Public Key. Private Key에 의해 암호화된 데이터 복호화 및 전송할 데이터 암호화를 위한 키

    * Ref : https://eunhyee.tistory.com/238


1) Client가 인증서 획득
2) 해당 인증서를 상위 인증기관으로부터 확인
3) 인증이 완료되면, 통신 시 사용할 대칭키(Pre-Shared Key)를 인증서 Public Key로 암호화하고 Server에게 전달
4) Server는 Private Key를 사용하여 전달받은 데이터를 복호화하여 대칭키를 획득
5) 해당 대칭키를 이용해 서로 TLS/SSL 통신

> Root 인증기관에서 인증을 보통 못받는 경우에는 자체 사인, Root 인증서에, Key를  



## GuardDuty
AWS Account를 보호하기 위한 지능형 Discovery 보안 솔루션으로 머신러닝 알고리즘을 통해 위협을 찾아낸다.
- Input data
    - CloudTrail Event Logs
    - VPC Flow Logs
    - DNS Logs
    - 그 외 데이터 (EKS Audit Logs, RDS 로그인 등)
- Alarm
    - EventBridge, Lambda, SNS 통해 알람설정
- Multi-Account Strategy
    - 위임을 통해 다른 계정의 GuardDuty까지 관리가 가능하다

