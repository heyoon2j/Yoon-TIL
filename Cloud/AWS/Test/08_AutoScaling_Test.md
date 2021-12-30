# Auto Scaling Test
1. 인스턴스 Attach & Detach
2. Standby 상태에서 접속하기
3. Lifecycle Hook 사용
    * 시작전에 업데이트 필요한가 User Data 사용하면 되는데? Lambda같은 거 실행해야 되는 경우
    * 종료 전에 인스턴스 로그 저장하기 무조건 필요
    * 작업 완료 시 complete-lifecycle-action 실행
    * 작업: https://docs.aws.amazon.com/autoscaling/ec2/userguide/lifecycle-hooks.html#lifecycle-hooks-overview
        1) Shell Script
        2) Lambda 함수 호출
        3) Metadata 이용하여 Shell Script 검색
5. Scale 정책
6. 에러가 발생했을 때 알람
        