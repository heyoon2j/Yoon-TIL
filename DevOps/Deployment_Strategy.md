# 배포 전략

## Rolling
![Rolling](aws/img/RollingTest.png)
Application의 Old version -> New version으로 점진적으로 전환하는 배포방법

* 배포 중 한쪽의 Server 수가 감소되므로 서버 처리 용량을 미리 고려해야 한다.
* 장점
    - 효율적인 리소스 사용 (=> 추가하는 것이 아닌 기존 것을 가지고 변경하기 때문에)
    - 다운타임이 거의 없음
* 단점
    - 일관성 문제 발생 (=> old, new 버전이 동시에 동작하므로 일관성이 유지되지 않을 수 있음)
    - 롤백의 복잡성 (=> 중간에 장애가 발생하는 경우, 일부는 old, 일부는 new일 수 있음)
    - 배포 시간이 김 (=> 순차적으로 진행됨으로)
    - 리소스 증가 가능성 (=> 배포 중 한쪽의 Server 수가 감소되므로 서버 처리 용량을 미리 고려 필요)
</br>


## Blue/Green
![BlueGreen](aws/img/BlueGreen.png)

Old version, New version의 환경(Server, Network)을 동시에 나란히 구성하여 배포 시점에 트래픽을 일제히 전환하는 배포방법
* Old version : Blue / New version : Green
* 장점
    - 운영환경에 영향을 주지 않으면서 환경 테스트 가능
    - Downtime 최소화 (=> 트래픽 전환만 이루어지기 때문에 다운타임이 거의 없다)
    - 즉각적인 롤백 가능 (=> 트래픽 전환만 하면 됨)
* 단점
    - 리소스 소모 (=> 같은 환경을 유지해야되므로 비용이 2배로 듬)
    - 데이터베이스 호환성 문제
</br>


## Canary
![Canary](aws/img/Canary.png)

New version을 소수의 사용자에게만 먼저 배포하고 문제가 없는 경우 점진적으로 배포하는 전략
* Server의 트래픽 일부를 New version으로 분산하여 오류 여부를 확인할 수 있고, A/B Test가 가능하다.
* Blue/Green과 Rolling 전략의 장점은 가져가고, 단점을 줄이는 전략이다!
* 장점
    - 소규모로 Blue/Green 테스트 진행 가능 (=> 리소스 소모를 줄임) / Rolling + Blue/Green 장점
    - 다운타임이 거의없음 (=> 점진적으로 진행하므로) / Rolling 장점
* 단점
    - 세밀한 트래픽 조정이 필요 (복잡성을 요함)
    - 리소스 증가 가능성 (=> 배포 중 한쪽의 Server 수가 감소되므로 서버 처리 용량을 미리 고려 필요) / Rolling 단점
    - 데이터베이스 호환성 문제 / Blue/Green 단점
</br>


### Reference
* https://reference-m1.tistory.com/211



