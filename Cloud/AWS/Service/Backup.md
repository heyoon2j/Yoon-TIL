# AWS Backup

## 필요한 IAM
* https://docs.aws.amazon.com/ko_kr/aws-backup/latest/devguide/setting-up.html
* 모든 supported AWS resources에 대한 백업과 복구에 대한 권한이 필요.
    * Assume Role : ```AWS Backup```
    * Policy : ```AWSBackupServiceRolePolicyForBackup```, ```AWSBackupServiceRolePolicyForRestores```
</br>
</br>


## 작업 과정
1. Create Backup Vault
    * Backup 저장 공간
    * KMS Key를 통해 암호화 가능
2. Create Backup Plan
    * Backup Rule 및 Resource Assignment 설정
    * Plan은 Rule과 Assignment Module을 관리하는 Interface
3. Create Backup Rule
    * 저장할 Vault, 백업 주기, 보존 기간, 리전 선택 등 설정
4. Create Resource Assignment
    * 백업할 Resource Type 및 적용 Tag 설정
</br>
</br>


## Across multiple AWS accounts 
* https://docs.aws.amazon.com/aws-backup/latest/devguide/manage-cross-account.html
* AWS Backup은 AWS Organization을 통해 "cross-account management"를 할 수 있다.
1. Enable cross-account management
    * 백업 정책 활성화 선택
    * 교차 계정 모니터링 활성화 선택
2. Create a backup policy
    * 위의 작업 과정과 동일


