# Auto Scaling (AS)
* 설정에 따라 자동으로 EC2의 수량을 조절하는 서비스
* Scale이란 컴퓨팅 성능을 변경하는 것을 의미한다. 성능을 어떤 식으로 변경하냐에 따라 아래와 같이 얘기한다.
    * Scale Up : CPU, Memory 등의 컴퓨팅 능력을 높여 성능을 올리는 방법 (CPU를 4 Core --> 8 Core로 변경)
    * Scale Down : CPU, Memory 등의 컴퓨팅 능력을 낮춰 성능을 낮추는 방법 (Memory를 1경 GiB --> 4 GiB로 변경)
    * Scale Out : 컴퓨터의 수를 늘려, 처리를 여러 대가 처리하도록 함으로써 전체 컴퓨팅 성능을 향상시키는 방법
    * Scale In : 컴퓨터의 수를 줄여, 기존보다 적은 수가 처리함으로써 전체 컴퓨팅 성능을 줄이는 방법
* 사용 목적은 다음과 같다.
    * 정책에 따른 인스턴스 수량 조절 (최소, 최대 수 관리, 증감 조건 등)
    * Auto Scaling은 활성화된 Availabililty Zone에 대해 균등하게 분산한다(__Availability Zone rebalancing__)
* 구성 요소는 다음과 같다.
    * 그룹 : 구성 템플릿, 조정 옵션이 적용되는 하나의 집합
    * 구성 템플릿 : 인스턴스에 대한 설정
    * 조정 옵션 : 어떤 조건에서 어떻게 Scaling할 것인지 설정
</br>
</br>


---
## Auto Scaling 그룹 설정
Auto Scaling 그룹 설정 시 다음 항목을 신경쓰면 된다.
* Lifecycle
    * Scaling을 위한 전체 Lifecycle이 존재하며, 각 Process를 이해해야 Auto Scaling을 잘 활용할 수 있다.
    * Hooking은 UserData 또는 EventBride(Lambda) 등을 통해 사용 
* Health Check
    * 모든 인스턴스는 Healthy 상태로 시작하며 해당 인스턴스가 비정상 상태라는 알림을 수신하지 않는 한 인스턴스는 정상 상태로 간주된다.
    * 기본적으로 EC2 상태 검사를 통해 확인한다.
    * LB 연결 시, LB으 Health Check도 포함된다.
* 인스턴스 템플릿 구성
    * 시작 템플릿 구성 : OS, 볼륨 및 인스턴스 옵션을 설정한다.
    * 인스턴스 시작 옵션 : 네트워크, 인스턴스 타입 및 구매 옵션 설정
* Scaling 정책 구성
    1. Target tracking scaling
    2. Step Scaling
    3. Simple scaling
* 그 외
    * 알림 추가
    * 태그 추가
</br>
</br>



---
## Life Cycle
![AutoScalingLifeCycle](../img/AutoScalingLifeCycle.png)
1. (After scaling action) : Cold Time 동안 다른 Scaling action을 진행하지 않고 대기 (Default: 300s)  
2. (Pending) : Launch Template 사용하여 인스턴스 구성
3. (Pending:Wait) : Lifecycle Hook 실행 후 종료되기까지 대기
    * ```complte-lifecycle-action``` CLI 명령어 또는 ```CompleteLifecycleAction``` 작업을 사용하여 Lifecycle 작업을 완료 신호를 주지 않으면 제한 시간이(HeartBeat Time) 끝날 때까지 인스턴스는 대기 상태로 유지됨 (Default: 1 hour)
4. (Pending:Proceed) : LB에 등록 시작
    * LB에 등록 시작 후 바로 InService로 넘어가는데, 초기화 및 UserData 실행 등으로 LB Health Check가 되기까지 시간이 걸리기 때문에 InService로 넘어가도 LB Health Check가 완료되어 있지 않을 수 있다. 이를 해결하기 위해 Grace Period를 설정해야 한다.!!!
5. (InService)
    * Auto Scaling Group에 등록
    * Warm-up Time 동안 CloudWatch 지표에 등록하지 않음
    * Grace Time 동안 LB의 Health Check를 AS에 포함시키지 않는다.
    * Cold Time > Warm-up Time >= Grace Time 이 좋아 보임. Health Check가 되는 순간부터 실질적으로 트래픽이 흘러가기 때문에 이후에 지표에 등록되는 것이 맞아 보인다.
6. (Terminating) : 정책 또는 상황에 따라 종료되어야 할 Instance를 선정 및 AS에서 분리와 LB에서 등록 취소 진행(Deregistration Delay 포함)
7. (Terminating:Wait) : Lifecycle Hook 실행 후 종료되기 까지 대기
8. (Terminating:Proceed) : 인스턴스 종료

* Health Check Control을 위한 시간
    * 모든 인스턴스는 Healthy 상태로 시작하며 해당 인스턴스가 비정상 상태라는 알림을 수신하지 않는 한 인스턴스는 정상 상태로 간주된다.
    * Grace Period : LB의 Health Check를 AS에 포함시키기 까지의 유예 기간. 도중에 Instance의 상태가 running이 아닌 경우에는 Instance가 종료된다.
* Scaling Action Control을 위한 시간
    * Warm-up Time : 새로 시작된 인스턴스가 InService 상태에 도달하게 되면, 즉시 CloudWatch 지표에서 계산이 된다. 이런 경우, 서버는 InService에 도달했다고해서 바로 서비스 요청을 처리하고 있는 것이 아닌데 지표에 영향을 주기 때문에 Dynamic Auto Scliang은 서버는 추가되었지만 각 각의 인스턴스 리소스는 임계값을 넘었기 때문에 리소스가 부족하다고 잘못 판단할 수 있다. 이를 방지하기 위해 지정된 워밍업 시간이 만료될 때까지 인스턴스는 Auto Scaling Group의 집계된 지표에 계산되지 않도록 한다. 이를 통해 리소스 사용량에 영향을 주지 않도록 하여 필요한 것보다 더 많은 인스턴스를 추가하지 않게 해야 한다. 즉, 인스턴스가 InService 상태에 도달한 후 집계된 지표에 사용 데이터를 제공하기 전에 대기하는 시간을 의미
    * Cold Time : 다음 Scaling 활동이 시작되기 전에 휴지 기간이 완료될 때까지 기다린다. 이를 통해 추가적인 Scaling 작업이 반복되지 않게 도움을 준다.
</br>
</br>
---

## Lifecycle Hook
인스턴스가 시작되거나 종료될 때 사용자 지정 작업을 수행할 수 있도록 Auto Scaling 그룹에 수명 주기 후크를 추가할 수 있다.
> 가장 중요한 것은 Hook 사용시 완료 신호를 주는 것이다. 작업이 완료되면 complete-lifecycle-action 명령어를 꼭 사용하자. 신호가 없다면 설정 대기 시간까지 기다리게 된다!!!!!
* Hook 종류
    * ```autoscaling:EC2_INSTANCE_LAUNCHING ```: Pending 상태에서 Hook 추가
    * ```autoscaling:EC2_INSTANCE_TERMINATING```: Terminating 상태에서 Hook 추가
* Hook 사용 방법
    1) EventBridge/Lambda/SSM : https://brunch.co.kr/@alden/65
    2) Userdata
* 사용되는 예시
    1) 예기치 않은 장애가 발생할 경우, 시작을 포기하도록 시작 수명주기 후크를 구성.
    2) 예기치 않은 장애가 발생할 경우, 종료 시 로그를 s3에 저장하도록 후크를 구성.
</br>

### Lifecycle Hook 사용되는 경우
* 해당 Lifecycle에 Lambda 등을 실행하게 할 경우
* 기본적으로 ```User Data```를 사용하는 경우, Pending에 대한 Hook을 사용할 필요가 없어 보인다.
1. Pending Lifecycle Hook
    * Lambda를 이용하여 애플리케이션의 동작 또는 Traffic 수신이 정상적인지 확인하여, 실패할 때 알람 보내기 (미리 테스트하겠지만 중간에 오류가 발생할 수도 있으니 테스트해보는 것도 나쁘지 않은 거 같다)
2. Terminating Lifecycle Hook
    * 인스턴스가 종료되기 전에 Amazon EventBridge를 사용하여 알림을 보낸다.
    * 인스턴스가 대기 상태에 있는 동안 AWS Lambda 또는 다른 인스턴스에 연결하여 인스턴스가 완전히 종료되기 전에 로그 또는 데이터를 다운로드한다.
* 알람으로는 EventBride(권장) or SNS/SQS 사용
* https://brunch.co.kr/@alden/65
</br>
</br>


---
## Health Check
Health Check 유형은 다음과 같다.
* Amazon EC2 상태 확인 및 예약된 이벤트 (오토 스케일링의 기본 상태 확인 유형)
    * 인스턴스가 실행 중인지 확인
    * 인스턴스를 손상시킬 수 있는 기본 하드웨어 또는 소프트웨어 문제 확인
* Elastic Load Balancing 상태 확인
    * 요청을 처리하는 데 인스턴스를 사용할 수 있는지 점검하기 위해 로드 밸런서가 인스턴스를 정상으로 보고하는지 확인
    * 이 상태 확인 유형을 실행하려면 오토 스케일링에 대해 활성화해야 합니다.
* VPC Lattice
    * VPC Lattice
    * (무슨 내용인지 아직 안읽음...!ㅎㅎ)
    * 이 상태 확인 유형을 실행하려면 오토 스케일링에 대해 활성화해야 합니다.
* 사용자 지정 상태 확인
    * 사용자 지정 상태 확인에 따라 인스턴스 상태 문제를 나타낼 수 있는 다른 문제가 있는지 확인
</br>
</br>



---
## Instance Control
* Attaching : 하나 이상의 EC2 인스턴스를 기존 Auto Scaling Group에 연결할 수 있다.
* Detaching : Auto Scaling Group에서 인스턴스를 제거할 수 있으며, 이후에는 독립적으로 Instance를 관리할 수 있다.
* Standby : 문제가 있거나 업데이트가 필요한 인스턴스의 경우, ```Standby``` 상태에서 해결하거나 변경한 다음에 다시 서비스할 수 있다. ```Standby``` 상태의 인스턴스는 계속 Auto Scaling Group에서 관리하지만, 다시 시작할 때까지 애플리케이션이 활성화되지 않는다.
</br>
</br>


---
## Scale Control
Scale 정책은 다음과 같다.
1. Scaling 방식
2. 용량 선택 방법
3. Scale Out Policy
4. Scale In Policy
</br>

### Scaling 방식
* Manual Scaling : 수동, Console을 이용하여 Attach/Dettach
* Dynamic Scaling : 자동, Auto Scling이 특정 CloudWatch 지표를 추적하도록 지시하고 연결된 CloudWatch 경보가 Alarm일 때 취해야 할 조치를 정의한다.
* Scheduled Scaling : Scaling을 예약할 수 있으며, 하루만 확장되거나 반복되는 일정에 따라 확장되는 작업을 예약할 수 있다 (cron 형식)
</br>

### 용량 선택 방법
1. 인스턴스 단위
    * Auto Scaling Group의 최대 용량 및 최소 용량을 초과하거나 미만으로 인스턴스를 조정할 수 없다. 정책에서 갯수가 설정이 되어있더라도 자동으로 중지된다.
2. 인스턴스 가중치
    * Auto Scaling Group의 최대 용량 및 최소 용량과 상관없이 가중치를 비교하여 할당. 할당 정책을 고수하는 방법이다.
</br>

### Scale Out Policy
1. Target tracking scaling
    * 특정 Metric에 대한 값을 기반으로 Group의 Scaling 작업을 한다.
    * 기본에서 제공하는 Metric 말고도 사용자가 원하는 Metric으로 구성하여 설정할 수 있다.
    * Metric이 시간이 지남에 따라 어떻게 변하는지 학습하고 해당 정보를 사용하여 Scaling 최적화 알고리즘을 사용
    * Warm-up Time을 통해 확장 제어
    * ex> 자동으로 Scaling
2. Step Scaling
    * 경보 위반의 크기에 따라 달라지는 Scaling 작업 (CloudWatch Alarm 생성 필요)
    * Metric에 대한 Multiple Threshold 지정
    * Warm-up Time을 통해 확장 제어
    * ex> 임계값 A - CPU 사용률이 40%에서 50% 사이일 때 인스턴스 1개 추가 / 임계값 B - CPU 사용률이 50%에서 70% 사이일 때 2개의 인스턴스 추가
3. Simple scaling
    * Metric에 대한 Single Threshold 지정 (CloudWatch Alarm 생성 필요)
    * Cold Time을 사용하여 Scaling 속도를 제어
    * ex> CPU 사용률이 40%에서 50% 사이일 때 인스턴스 1개 추가
> Cold Time은 권장하지 않는 설정이며, 간단한 조정 정책을 사용할 때만 사용한다.

> Metric의 지표를 활용하는 경우 Target tracking 정책, 이외에는 Step Scaling 정책을 권장한다.

> 필요한 경우 여러 개의 정책을 적용할 수 있다. 하지만 충돌 현상이 생길 수 있으므로 주의하는 것이 좋다. ref: https://docs.aws.amazon.com/autoscaling/ec2/userguide/as-scale-based-on-demand.html
</br>

### Scale In Policy
* Scale In Event 발생 시, 종료되는 인스턴스 제어하는 정책
* Scale In Event에 대한 보호도 가능하다. 보호를 활성화하면 그 이후에 시작된 모든 새 인스턴스에 활성화가 된다.
* 정책 종s류
    1) __Default__: 인스턴스가 가장 많은 가용 영역을 확인 후, 축소로부터 보호되지 않는 인스턴스를 선택
    2) __OldestInstance__: Group에서 가장 오래된 인스턴스 종료
    3) __NewestInstance__: Group에서 가장 최신 인스턴스 종료
    4) __OldestLaunchConfiguration__: 가장 오래된 시작 구성이 있는 인스턴스 종료
    5) __ClosestToNextInstanceHour__: 다음 청구 시간에 가장 가까운 인스턴스를 종료합니다. 이 정책은 시간당 요금이 부과되는 인스턴스의 사용을 극대화하는 데 도움이 된다.
    6) __OldestLaunchTemplate__: 가장 오래된 시작 템플릿이 있는 인스턴스를 종료. 그룹을 업데이트하고 이전 구성에서 인스턴스를 단계적으로 제거할 때 유용
    7) __AllocationStrategy__: 
        * Spot 할당 전략이 lowest-price인 경우, 각 인스턴스 유형 풀에서 가장 저렴한 N개의 스팟 인스터스로 점진적으로 재조정한다.
        * Spot 할당 전략이 capacity-optimized인 경우, 각 풀에서 가격을 확인하지 않고 최적의 용량 볼륨을 찾아 인스턴스를 선택한다.
        * https://medium.com/cloudzone/aws-autoscaling-group-spot-capacity-optimised-allocation-strategy-df666345498
</br>
</br>


---
## Others
### Capacity Rebalancing
* 스팟 인스턴스의 가용성에 영향을 미치는 변경 사항을 모니터링하고 자동으로 응답하도록 Auto Scaling을 구성할 수 있다.
* Rebalancing은 실행 중인 인스턴스가 EC2에 의해 중단되기 전에 새 스팟 인스턴스로 플릿을 사전에 확장하여 워크로드 가용성을 유지하는데 도움을 준다.
* 작동 방식
    1) EC2 Capacity Rebalancing 알림을 인식
    2) 중단될 스팟 인스턴스를 사전에 중단 위험이 높지 않은 새로운 스팟 인스턴스로 재조정한다.
</br>

### Instance Refresh (배포 방법)
* 한 번에 몇 개의 인스턴스를 수동으로 교체하는 대신 Instance refresh를 사용하여 Auto Scaling Group의 인스턴스를 업데이트할 수 있다.
* 작동 방식
    1) 먼저 새 AMI 또는 User Data Script를 지정하는 새 Launch Template를 생성한다.
    2) 최소 정상 상태 비율, 인스턴스 워밍업 및 체크포인트를 구성하여 Instance Refresh 진행
    3) Auto Scaling은 Rolling 교체를 시작. 서비스를 중단한 인스턴스 집합을 가져와 종료하고 원하는 새 구성으로 인스턴스를 시작한다.
    4) 그런 다음 워밍업 및 인스턴스 Health Check가 완료되면 다른 인스턴스로 교체한다.
    5) Group의 특정 비율이 교체된 후 체크포인트에 도달하고, 체크포인트가 있을 때마다 Auto Scaling은 인스턴스 교체를 일시적으로 중지하고 알림을 보내고 지정된 시간 동안 기다린다.
</br>

---
## Warm Pool
* Warm Pool은 미리 인스턴스를 프로비저닝하여 Scale out 시 바로 서비스할 수 있게 해준다.
* Warm Pool을 사용할 시 인스턴스를 ```Running```과 ```Stopped``` 상태로 유지할 수 있는데, ```Stopped``` 상태로 유지하는 것이 비용을 최소화하는 방법이다. 해당 상태인 경우 인스턴스 비용을 제외한 볼륨과 EIP 등의 비용만 지불하면 된다.
> 하지만 필요하지 않을 때는 비용이 발생하므로 부팅시간!!!으로 인해 애플리케이션이 크게 영향을 받지 않는다면 사용할 필요가 없다!!!
