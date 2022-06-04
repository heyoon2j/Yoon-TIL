# Organization
* 중앙 집중식으로 환경 관리 및 규제하는데 사용하는 서비스
</br>

## Organization 구성
### Organization Unit (OU)
* 해당 조직에 대해 각 Unit으로 분리하여 관리할 수 있다.
* 각 Unit에 Account를 등록하고 Policy를 적용할 수 있다.
</br>

### Account
* 해당 조직에 대해 Account를 생성하거나, 기존 AWS 계정을 추가할 수 있다.
* 각 각 Account에 대해 Policy를 적용할 수 있다.
</br>

### Policy
* OUs나 Account에 적용할 정책 관리. 정책 유형은 다음과 같다.
1. 권한 부여 정책
    * SCP (Service Control Policy) : 사용 가능한 최대 권한 제어
2. 관리 정책   
    * Backup Policy : 조직 계정의 AWS 리소스에 대한 백업 계획을 중앙에서 관리하고 적용
    * Tag Policy : 조직의 계정에 있는 AWS 리소스에 연결된 태그를 표준화
    * AI Opt-out Policy : 조직의 모든 계정에 대한 AWS AI 서비스의 데이터 수집을 제어
</br>

### Service 
* Organization에 대하여 지원되는 AWS Service 액세스를 활성할 수 있다.
* 지원하는 서비스
    * GuardDuty, CloudFomration StackSets, RAM, Security Hub, Service Catalog, Single Sign-On 등
</br>
</br>


## 동작 방식
1. Create OUs
    * 조직 분류 및 OUs 생성
2. Create Accout
    * 각 OUs에 해당하는 AWS Account 생성
3. Add Account
    * 각 OUs에 AWS Account 등록
4. Enable Policies
    * Organization에서 사용할 Policy 활성화
5. Apply Policies
    * 사용할 Policy 생성
    * OUs 또는 AWS Account에 Policy 적용
6. Enable AWS services
    * Organization에서 사용할 AWS 서비스 활성화
</br>
</br>



# Single Sign-On (SSO)
* Choice 

## SSO 구성
### Identity Source
* 사용자 및 그룹을 관리할 서비스 정의. 서비스는 종류는 다음과 같다.
1. AWS SSO (AWS Organization 연동)
2. Active Directory 
3. SAML 2.0 IdP
</br>

### Permisstion Set
* 사용자 및 그룹에 대한 권한 세트를 설정.
</br>

### User & Group
* SSO에 등록할 사용자를 생성.
* 사용자를 그룹핑하기 위한 그룹을 생성.
</br>
</br>

## 동작 과정
1. Choice Identity Source
    * SSO를 관리할 Identity Source 선택
2. Create User and Group
    * ID Source에서 User 및 Group을 생성
3. Allocate User and Group to Account
    * Identity Source의 Account에 User 및 Group 할당
4. Manage Permission Set
    * Identity Source의 Account의 Permission Set 관리 (User 및 Goup에 적용할 권한)
5. Access Portal
    * User 정보를 이용하여 Portal에 접속


> Organization은 Account, SSO는 IAM User and Group으로 생각하면 된다. 그렇기 때문에 Organization에서 Account에 권한을 할당해야 SSO에서 User와 Group에게 사용 권한을 줄 수 있다.