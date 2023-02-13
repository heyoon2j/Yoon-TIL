# Switch




---
## L4 Switch
기본적으로 L4 Switch는 Source NAT처리를 하지 않으나 네트워크 구성에 따라 처리가 필요할 수 있기 때문에, 전반적인 아키텍처와 통신 흐름을 알 필요가 있다 (https://aws-hyoh.tistory.com/entry/L4-%EC%8A%A4%EC%9C%84%EC%B9%98-%EC%89%BD%EA%B2%8C-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0-5)

## In-Line
'Standing in Line' 이라고도 부르며, 네트워크 장비들을 일렬로 세운 구성이다.
* Internet - Backbone Switch - L4 Switch - L2 Switch - Servers
* 장점 : 모든 트래픽이 L4 Switch를 지나가기 때문에, 장애나 이슈에 대해 파악이 쉬우며 직관적이다.
* 단점 : 모든 트래픽이 L4 Switch를 지나가기 때문에, L4 Switch에 부하를 가중시킨다. 서버에 접속하기 위해서 L4 Switch를 거처가야 한다.
</br>

## One-Arm
L4 Switch를 L2, Server들과 직접 연결하지 않고, Backbone Switch를 통해 통신을 하는 구성이다.
* 장점 : L4 Switch와 서버 접속에 대한 트래픽이 분리가 되기 때문에 L4 Switch에 부하가 줄어든다.
* 단점 : 모든 트래픽이 L4 Switch를 지나가지 않기 때문에, 장애나 이슈에 대해 파악이 어려워질 수 있다.
</br>


## Direct Server Return (DSR)
'One-Arm' 구성에서 Server가 Response에 대해 L4 Switch를 거치지 않고, Backbone Switch를 통해서 전달하는 구성(https://aws-hyoh.tistory.com/m/144)
* Destination NAT 처리 해제, L4 Switch 경유 의무화 해제, Loopbak IP 설정과 GARP 사용 취소 설정 필요
* 장점 : Response가 L4 Switch를 거치지 않기 때문에, 부하적인 측면이나 속도적인 측면이 향상된다.
* 단점 : Response가 L4 Switch를 거치지 않기 때문에, 트러블 슈팅에 어려움이 있을 수 있다.
</br>

### Idle time 초과 동작
* L4 : 자신만 세션을 종료시키며, 알지 못하는 Client는 동일한 Connection으로 트래픽을 보냈을 때 RST를 받게 된다. 'Reset On Port Fail' 옵션을 설정을 하게 되면 Client쪽에만 RST를 보낸다. Target 쪽에만 Zombie Session이 남는다. 설정을 하게 되면 L4 Switch에 부하가 발생하기 때문에 상황에 맞게 설정해야한다.
* L7 : Client와 Target에 FIN을 보내 세션 테이블을 종료 시킨다.
* https://tech.kakao.com/2014/05/30/l4/
* https://medium.com/tenable-techblog/lessons-from-aws-nlb-timeouts-5028a8f65dda

