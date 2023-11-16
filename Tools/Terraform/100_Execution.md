
```

```



## init (초기화)
```
# terraform plan [options]

### 일반 옵션
# -input=true           / 입력 (비)활성화. false인 경우 입력이 필요한 경우 오류가 발생
# -lock=false            / 상태 파일에 대해 Lock 비활성화
# -lock-timeout=DURATION      / 일정 기간 동안만 상태 파일에 대해 Lock 활성화 (-lock=false 인 경우 사용 불가)
# -no-color 출력에서 ​​색상 코드를 비활성화
# -chdir=environments/production  / 작업 디렉토리 전환


### 모듈
# -from-module=MODULE-SOURCE     / i사it 시에 모듈을 init을 실행한 Directory에 복사
# -upgrade                       / 설치 단계의 모듈과 플러그인을 업그레이드


### Backend 초기화
# --backend-config=<PATH>           / Backend 설정 파일 지정
# -migrate-state or -reconfigure    / 기존 상태를 새 백에드에 복사
# -force-copy                       / -migreate-state를 메시지 없이 실행할지 여부. 해당 옵션을 활성화하면 -migrate-state는 활성화한다


### Plugin
# -upgrade                          / 설치 단계의 모듈과 플러그인을 업그레이드
# -plugin-dir=PATH                  / Plugin 위치
# -lockfile=readonly                / ???
```
* init 동작
    - Provider Pligin을 자동으로 찾아서 다운로드하고 설치
    - Backend 설정 확인
    - 모듈 확인 후 복사


</br>
</br>


## plan (계획)
```
# terraform plan [options]
# -out=FILE   / Normal Mode. 생성 계획 결과 출력
# -destroy   / Destroy Mode. 파괴 계획 결과 출력

terraform plan -refresh-only
=> Refresh-only Mode : Terraform 코드가 아닌 외부에(ex> tfvars에 있는 변수 값등) 의해 변동된 Terraform state와 root module output values를 업데이트 하기 위해
```




## apply (적용)
```
# terraform show [options]
# -json               / 결과에 대해 JSON 출력
# -input=false            / 대화형 프롬프트 비활성화
# -auto-approve            / 대화형 승인을 건너뛰고, 자동 승인
# -compact-warnings         / 경고 메시지가 요약된 형태로 출력
# -lock=false            / 상태 파일에 대해 Lock 비활성화
# -lock-timeout=DURATION      / 일정 기간 동안만 상태 파일에 대해 Lock 활성화 (-lock=false 인 경우 사용 불가)
# -parallelism=n         / 사용하지 않느다고 함!! 그래프 탐색을 n개 병렬로 수행 (그래프 탐색 : 리소스 생성을 위해서 모든 종속성 확인이 필요)


# Example
terraform apply -input=false -auto-approve         # non-interactive context(비대화형) 자동화 실행하는 방법

terraform apply -lock=true -lock-timeout=3s         # lock=false이면 lock-timeout 사용 불가 (Defaults to 10.)

terraform apply -parallelism=10
```
</br>




## destroy (초기화)
```
# terraform destroy [options]      # == terraform apply -destroy

terraform destroy
```
</br>



## show 
```
# terraform show [options] [file]


terraform show -json


```

</br>
</br>










11 / 30 배당일