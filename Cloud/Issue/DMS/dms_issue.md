# DMS Issue 정리

## DMS 사용 시
* https://docs.aws.amazon.com/dms/latest/userguide/CHAP_Security.html
* 기본적으로 ```dms-vpc-role, dms-cloudwatch-logs-role``` 이 필요하다.
* 생성하지 않으면 자동으로 만들어 버린다...
* 

## DMS Redshift 연동
* Using an Amazon Redshift database as a target for AWS Database Migration Service
* 연동은 다음과 같이 진행: DMS -> S3 <- Redshift 
* 그렇기 때문에 DMS는 Redshift에 대한 Endpoint 생성 시 S3가 필요한데, S3를 옵션으로 설정해 놓지 않으면 DMS가 Account의 IAM 권한을 사용하여 임시 S3 및 필요한 권한을 생성한다 (dms-vpc-role, ```dms-access-for-endpoint```)
* S3 설정은 Consol에서 Wizard 또는 Json으로 추가하거나 CLI를 사용하여 추가 옵션을 설정한다.
* dms-access-for-endpoint Role은 Redshift의 Role에 추가해줘야 한다.
* https://docs.aws.amazon.com/dms/latest/userguide/CHAP_Target.Redshift.html#CHAP_Target.Redshift.ConnectionAttrib




## 



