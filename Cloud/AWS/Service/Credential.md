# Credential

## 설정 방법
1. __환경 변수 설정__
   ```
   #export AWS_ACCESS_KEY_ID="<AWS_ACCESS_KEY_ID>"         // access_key
   #export AWS_SECRET_ACCESS_KEY="<AWS_SECRET_ACCESS_KEY>" // secret_key
   #export AWS_DEFAULT_REGION="ap-northeast-2"             // region
   ```
</br>

2. __파일 설정__
   * config 파일과 credentials 파일 이용
   ```
   vi ~/.aws/config
   [default]
   region = ap-northeast-2

   vi ~/.aws/credentials
   [default]
   aws_secret_access_key = <SECRET_KEY>
   aws_access_key_id = <KEY_ID>
   ```
</br>

3. aws cli 이용
   * https://www.daleseo.com/aws-cli-configure/