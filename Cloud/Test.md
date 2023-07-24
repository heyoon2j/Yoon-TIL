# 배포 전략


## Rolling
![Rolling](aws/img/RollingTest.png)
* 일반적인 배포를 의미
* Old version -> New version으로 점진적으로 전환하는 배포방법이다.
* 배포 중 한쪽의 Server 수가 감소되므로 서버 처리 용량을 미리 고려해야 한다.


## Blue/Green
![BlueGreen](aws/img/BlueGreen.png)
* Old version을 Blue, New version을 Green으로 명명한다.
* Old version, New version Server를 동시에 나란히 구성하여 배포 시점에 트래픽을 일제히 전환한다.
* 빠른 Rollback이 가능하고, 운영환경에 영향을 주지 않기 때문에 실제 production 환경에서 New version Test가 가능하다.
* 하지만 이 경우 시스템 자원을 두배로 필요로 하기 때문에 비용이 더 많이 발생한다.


## Canary
![Canary](aws/img/Canary.png)
* 해당 배포는 위험을 빠르게 감지할 수 있는 배포 전략이다.
* 지정한 Server 또는 지정한 User에게만 배포했다가 정상적이면 전체를 배포한다.
* Server의 트래픽 일부를 New version으로 분산하여 오류 여부를 확인할 수 있고, A/B Test가 가능하다.



### Reference
* https://reference-m1.tistory.com/211



