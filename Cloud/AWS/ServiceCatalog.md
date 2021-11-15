# AWS Service Catalog
* 협업을 위한 서비스로 중앙에서 관리함으로써 협업을 편리하게 해준다.
    * 협업은 계정과 계정뿐만 아니라 계정과 서비스, 서비스와 서비스 등 다양한 관계를 의미.
* 그러면 협업(연동)을 해야 되는 이유는 보통 서비스(인프라)가 분리되어 있기 때문이다. 인프라가 서로 분리되어야 하는 이유는 각 프로젝트(인프라)의 특성이 서로 다르고, 보안 이슈 등등으로 분리되어야 한다.
> 이렇게 분리가 되면 문제는 각 프로젝트마다 보안이나 구축/운영 방식이 달라질 수 있고, 결과적으로 관리가 힘들어질 수 있기 때문에 통일성이 필요하다.
</br>


## Service Catalog 요소
## Portfolio
* 버전관리 중인 Products 모임.
* Portfolio에서 사용할 Products 관리와 PortFolio를 관리할 수 있는 권한 설정.
> 현재 AWS Landing Zone Account Vending Machine Product를 사용 중.

## Product
* AWS Resource Template
> Landing Zone 기준으로 AWS Resource는 AWS Account에 해당함.


## Provisioning Product
* Portfolio에 따라 Product 생성.
* 필요한 내용을 설정하면 생성이 된다. 여기에서는 Product Template에 필요한 값이나, Product를 사용할 사용자 등을 설정한다.
> Landing Zone Product Template 기준으로 SSO 계정을 설정 값에 넣으면, 해당 SSO 계정이 해당 Product를 사용할 수 있는 것으로 보인다. 그리고 SSO 계정에 Email을 추가하여 연동.


## Portal
* 사용자가 접속할 입구
* 사용자는 Portalf를 통해 Portfolio들과 사용할 수 있는 Product를 볼 수 있다.



### Landing Zone 기준 사용되는 서비스
* Account : SSO
* Permission : IAM


