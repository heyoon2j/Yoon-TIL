# Ansible
* Ansible은 오픈 소스 소프트웨어로, 여러 대의 서버 관리를 자동화하기 위한 구성 관리 도구이다.

## IaC
* Infrastructure as Code
* Code를 이용해 Infra를 관리한다는 의미이다.
* IaC를 위한 Tool로는 Terraform, AWS CloudFormation 등이 있다.

## 멱등성
* 연산을 여러 번 적용하더라도 결과가 달라지지 않는 성질을 의미
* 기존 Script 같은 경우, 조건문을 이용하여 결과가 같아지도록 만들 수 있지만, 일반적으로
여러 번 실행에 대해 동일한 결과를 보장하지 않는다.
* 요즘에 나오는 Tools(Terraform, Ansible 등)은 멱등성을 제공한다


