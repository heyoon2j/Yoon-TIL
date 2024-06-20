# Inventory
Ansible은 인벤토리를 사용하여 노드(관리 대상)들에 대한 호스트 정보를 관리한다. 설정할 목록은 다음과 같다.
* 정적 호스트 설정
* 동적 호스트 설정
* 호스트 변수
* 그룹 변수
</br>
</br>

## Directory Structure
```
inventories/
   production/
      hosts               # inventory file for production servers
      group_vars/
         group1.yml       # here we assign variables to particular groups
         group2.yml
      host_vars/
         hostname1.yml    # here we assign variables to particular systems
         hostname2.yml

   staging/
      hosts               # inventory file for staging environment
      group_vars/
         group1.yml       # here we assign variables to particular groups
         group2.yml
      host_vars/
         stagehost1.yml   # here we assign variables to particular systems
         stagehost2.yml
```
* 



## 정적 호스팅





</br>
</br>

## 동적 호스팅








