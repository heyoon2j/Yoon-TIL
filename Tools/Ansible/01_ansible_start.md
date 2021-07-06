# Ansible Start

## Install
* 공식문서 : https://docs.ansible.com/ansible-core/devel/installation_guide/intro_installation.html#control-node-requirements
* 설치시, 필요한 ```ansible```, ```ansible-core```와 ```Python``` version을 체크해야 한다.
* Ansible은 community package ```ansible```과 ```ansible-core```을 포함하고 있다(2.10 version에서는 ```ansible-base```로 사용된다)

1. Install Ansible
   * Python ```pip```를 이용하여 설치
   * 명령어: ```$ python -m pip install --user ansible```

2. Install paramiko module
    * connection plugin or module들에서 사용되어진다.
    * 명령어: ```$ python -m pip install --user paramiko```


## Basic
* Anible의 기본 언어는 YAML 언어이다.
* Ansible은 크게 2개의 Component를 가진다.
*  Controller Node: Ansible 설치된 모든 시스템. Windows machine은 Control Node가 될 수 없다.
* Managed Node: Ansible로 관리되는 장치. "hosts"라고도 한다.
* Inventory: Managed node들의 IP address 목록을 정의하는 파일, "hostfile"이라고 말한다. Managed node들의 IP address나 그룹화 등의 정보가 저장된다.
* Role: Ansible을 모듈화시키는가 저장된다. 핵심 구성 요소로 Service와 Playbook의 전반적인 코드를 충분히 재사용하도록 할 수 있다.
* Playbook: Anisble 구성, 배포 및 오케스트레이션이 포함된 파일. 해당 파일을 작성함으로 OS 구성에서 응용 프로그램 배포와 모니터링까지 시스템의 상태를 순차적으로 정의할 수 있다.
* Module: Ansible에서 정의해둔 실행 단위로 실행할 수 있는 라이브러리를 의미.
* Ansible Config: Ansible 환경변수 정의 파일.


## Anisble 그 외 명령외
* ```ansible-doc <command>```: <command> 모듈에 대한 문서를 확인할 수 있다.




## Using Ansible
## Ansible Config 파일 설정
* Ansible 환경변수 정의 파일. 
* ```/etc/ansible/ansible.cfg``` 파일에 정의한다.


## 1. Role 만들기
* 역할 스크립트는 https://galaxy.ansible.com/과 같은 커뮤티니 사이트에서 지원을 받을 수 있다.
* ```ansible-galaxy``` 명령어를 사용해 커뮤니티에서 지원하는 역할을 임포트할 수 있다.
* Role 사용 방법 
    ```
    # Role Initialization
    # ansible-galaxy init [option] <role_name>
    $ ansible-galaxy init nodejs

    # Role Structure
    .
    ├── defaults
    │   └── main.yml
    ├── files
    ├── handlers
    │   └── main.yml
    ├── meta
    │   └── main.yml
    ├── README.md
    ├── tasks
    │   └── main.yml
    ├── templates
    ├── tests
    │   ├── inventory
    │   └── test.yml
    └── vars
        └── main.yml


    # task 작성
    $ cd nodejs
    $ vi task/main.yml

    # task 작성 방법: 
    ```
    * defaults: Role의 기본 변수를 포함. 우선순위가 가장 낮다.
    * task: 해당 Role의 역할 정의. Ansible이 Playbook을 실행할 때 tasks/main.yml에 있는 코드를 실행한다.
    * template: 해당 Role의 
    * vars: 해당 Role의 전역 변수 정의. defaults 디렉토리의 변수보다 우선순위가 높다.
    * files: 원격 호스트에 복사할 파일들이 저장되어 있다.
    * meta: 저자, 지원 플랫폼, 종속성과 같은 Role의 메타 데이터를 포함
    * handlers: "notify" 지시문에 의해 호출될 수 있고, 서비스와 연관된 Handler를 포함
    * 





## 2. Inventory 파일 생성
* 어떤 Managed Node를 관리할지 Inventory 파일에 작성한다.
* 기본적으로 ```/etc/ansible/hosts``` 파일에 정의한다.
* 





### Reference
* Dir 구조: https://youngmind.tistory.com/entry/Ansible-%EA%B5%AC%EC%A1%B0%EC%9D%98-%EC%9D%B4%ED%95%B4



