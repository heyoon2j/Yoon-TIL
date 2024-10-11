# Basic

## 구성 요소
```
.
└── .github
    ├── workflows
    │   ├── ci
    │   │   ├── build.yml
    │   │   ├── lint.yml
    │   │   └── test.yml
    │   ├── cd
    │   │   ├── deploy-staging.yml
    │   │   └── deploy-production.yml
    │   └── cron
    │       ├── nightly-build.yml
    │       └── cleanup.yml
    └── CODEOWNERS

```
- .github : GitHub Actions뿐만 아니라 다른 GitHub 관련 설정 파일을 포함할 수 있는 디렉토리 (ex> 이슈 템플릿, 풀 리퀘스트 템플릿, 코드 오너십 파일 등 포함)
- .github/workflows : GitHub Actions 워크플로우 파일들이 저장되는 디렉토리입니다.
- build.yml : 일반적으로 빌드 프로세스를 정의하며, 코드 컴파일, 패키징 등 처리
- test.yml : 테스트 관련 워크플로우를 정의하며, 유닛 테스트, 통합 테스트 등 자동화
- deploy.yml : 배포 관련 워크플로우를 정의하며, 애플리케이션을 스테이징 또는 프로덕션 환경에 배포하는 작업을 처리
- CODEOWNERS 파일 : 코드 오너십을 정의하여 특정 파일이나 디렉토리에 대한 코드 리뷰를 누가 해야 하는지 지정 가능