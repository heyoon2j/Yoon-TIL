




* https://iamondemand.com/blog/elb-vs-alb-vs-nlb-choosing-the-best-aws-load-balancer-for-your-needs/
* ALB는 HTTP Header 분석으로 인한 Overhead가 존재
* NLB는 TCP까지만 분석하므로 속도가 빠르다.

* NLB는 ALB가 다루지 않는 모든 것에 사용됩니다. 일반적인 사용 사례는 거의 실시간 데이터 스트리밍 서비스(비디오, 주식 시세 등)입니다. 또 다른 일반적인 경우는 애플리케이션이 HTTP가 아닌 프로토콜을 사용하는 경우 NLB를 사용해야 한다는 것입니다.