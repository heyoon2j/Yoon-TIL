# CloudWatch
* 애플리케이션 및 인프라 모니터링 서비스
* 로그, 지표 및 이벤트 양식으로 모니터링 및 운영 데이터를 수집하고, 자동화된 대시보드를 사용하여 시각화한다.
</br>


## Monitoring List
### 지표 (Metric)
* 시스템 성능에 대한 데이터, 많은 AWS 서비스에 대해 무료 지표를 기본적으로 제공한다.
* 지표 데이터는 15개월 동안 보관된다.
1. __내장된 지표__
    * AWS 서비스에서 기본 지표를 수집할 수 있다.
2. __사용자 지정 지표__
    * 자체 애플리케이션으로부터 사용자 지정 지표를 수집하여 운영 성능을 모니터링하고 문제를 해결하고 추세를 파악할 수 있다.
3. __컨테이너 지표 및 로그__
    * Container Insights를 활용하면 큐레이트된 지표 및 컨테이너 에코시스템 로그를 간단히 수집하고 집계할 수 있다.
4. __Lambda 지표 및 로그__
    * CloudWatch Lambda Insights를 활용하면 AWS Lambda 함수에서 큐레이트된 지표 및 로그를 간단히 수집하고 집계할 수 있다.
</br>

### 로그 (Log)
* AWS 서비스로부터 로그 파일을 모니터링, 저장 및 액세스할 수 있다.
1. __Vended Log__
    * 고객을 대신하여 AWS 서비스가 기본적으로 게시하는 로그
    * 현재 지원되는 유형은 Amazon VPC Flow Log, Amazon Route 53 Log
2. __AWS Service Log__
    * AWS 서비스에서 게시하는 로그
    * Amazon API Gateway, AWS Lambda, AWS CloudTrail 등
3. __사용자 지정 Log__
    * 고객 자체 애플리케이션 및 온프레미스 리소스의 로그
    * AWS Systems Manager를 사용하여 CloudWatch Agent를 설치하거나 PutLogData API 작업을 사용하여 손쉽게 로그를 게시
</br>
</br>


## Alarm & Event
![CloudWatchEvent](../img/CloudWatchEvent.png)
* 지표랑 로그에 정보들이 저장 -> 특정 상태 변화, 유지 시 경보 발생 -> 규칙을 통해 이벤트를 대상으로 라우팅 -> 대상은 해당 이벤트를 실행


### 경보 (Alarm)
* 지정한 기간에 단일 지표를 감시하고 시간에 따른 임계값 대비 지표 값을 기준으로 지정된 작업을 하나 이상 수행.
* 경보는 특정 상태가 되었다고 바로 작업을 수행하지 않고, 상태가 변경되고 지정된 기간 동안 변경된 상태가 유지되어야 한다.


### 이벤트 (Event)
* AWS 리소스의 상태 변경시 이벤트 작동
* 규칙(Rule)
    * Event와 일치하는 것을 찾아서 대상에게 라우팅한다.
    * 여러 개의 대상에게로 라우팅할 수 있으며, 모두 병렬 처리된다.
* 대상(Target)
    * 이벤트를 처리하는 대상을 의미한다.
</br>
</br>



## CloudWatch 모니터링 방법
1. Custom Metric 생성
    * https://blog.leedoing.com/70
    * https://aws.amazon.com/ko/blogs/korea/amazon-cloudwatch-custom-metrics/
    * 기본적으로 Consol 에서는 생성이 불가
    1) EC2 등 리소스에 자격 증명 부여
    2) AWS CLI를 이용하여 생성
       * ```$ aws cloudwatch put-metric-data --metric-name PageViewCount --namespace "MyService" --value 2 --timestamp 2016-01-15T12:00:00.000Z``` 
    3) 
    4) 
    5) 
    6) 
    7) 
2. Dashboard 생성
    * 
3. 



</br>
</br>



## Cost (비용)




</br>
</br>


# VPC Flow Log

</br>
</br>


# CloudTrail

</br>
</br>