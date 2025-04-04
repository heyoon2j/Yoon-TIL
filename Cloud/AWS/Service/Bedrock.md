# Bedrock

생성형 AI : 

도전과제
- 출력 결과의 신뢰성
    - 허구의 정보 (환각 현상)
    - 편향된 결과물 제시 (고정된 답변)
    - 일관성 없는 응답
- 데이터 보안
    - 민감 정보 유출 위험
    - 데이터 전송 구간 보안
- 규제 준수와 거버넌스
    - AI 윤리 가이드라인 준수
    - 민감한 답변 제한
    - 설명 가능성 확보 필요


데이터 흐름
Prompt 사용자로부터 입력을 받음
앱이 사용자 입력 및 고객 데이터를 프롬프트로 변환
생성된 프롬프트 모델에 전달
모델 응답 수신
사용자에게 응답 전름


보안 구조
- AI 애플리케이션의 보안
    - 사용자 인증, 인가
    - 감사 및 모니터링
    - 데이터 암호화
    - 취약점 관리
- 책임감 있는 AI 정책 구성
    - AI 정책에 따라 유해 컨텐츠 필터링
    - 민감정보 삭제
    - 특정 주제 허용, 차단
    - 모델 평가를 통한 최적의 Foundation Model 선택

* 가드레일
    - Denied Topics (허용하지 않을 토픽)
    - Content Filters (콘텐츠 필터)
    - PII Redaction(개인식별 정보 교정)
    - Word Filter (단어 필터)
    - Contextual grounding check (문맥적 근거 확인)
    - Work Filter
    - 자동 추론 검사 (할루시네이션을 막는 방법 / 수학적 공식을 통해)
* 모델평가
    - 특정 사용 사례에 대한

    - 평가를 위한
        - 입력 데이터 세트에는 JSONL 형식으로 진행
        - 
    - 평가 방법
        - Programmatic evaluation : 프로그래밍 방법을 통해 진행
        - Human evaluation : 인간이 수동으로 진행
        - LLM-as-a-judge : =========================================================
            - 12가지 평가 지표가 있음
            - 프로세스
                - 생성 모델 선택
                - 평가 모델 선택
                - 성능 평가 지표 선택
                - 데이터셋 업로드
                - 모델 평가 작업 실행 (Job 생성)
                - 평가 보고서 및 검토
* RAG 평가
    - 평가 모델 선택
    - 지식 저장소 선택
    - 평가 유형 선택
    - 생성 모델 선택
    - 평가 지표 선택
    - 프롬프트 데이터셋 업로드
    - 추론 및 평가 수행
    - 결화 학인
    - JSONL 형식 (아래 항목이 포함되어야 함)
        - Promp인
        - reference



WAF + API Gateway + Bedrock + CloudWatch




## Bedrock vs SageMaker AI
- Level
    - High - AWS Q (== ChatGPT)
    - Middle - AWS Bedrock
    - Low - AWS SageMaker AI

- 모델링
    - Bedrock : 기존에 정의된 모델을 사용
    - SageMaker : 특수한 AL/ML 요구 사항에 맞게 모델을 최적화할 수 있음



---
## FM (Foundation Model)
대규모 데이터로 사전 학습된 범용 모델
- 학습 가능 데이터
    - 텍스트
    - 이미지
    - 음성
    - 그 외
- 모델
    - ㅂ
    - ㅈ


해당 구매처에 해당하는 혜택, 쿠폰, 프로모션


## LLM (Large Language Model)
FM 중에서 텍스트를 이해하고 생성하는 데 특화된 모델 (ex> chatGPT, Gemini 등)
- NLP (Natural Launguage Processing, 자연어 처리) 자연어(텍스트 또는 음성)를 처리하는 전체 기술/분야를 의미하며, LLM은 NLP에서 사용하는 모델 중 한 종류이다.


## RAG (Retrieval Augmented Generation)
모델을 재학습 시키지 않아도, 정확성과 신뢰성을 높이기 위해 외부 정보를 활용하는 기술. 최적화를 위한 방법
- 동작 과정
    1) 외부 데이터베이스에서 최신 정보를 검색 (Retrieval)
    2) 이를 기반으로 응답을 생성 (Generation)
- RAG 나온 배경
    - 기존 구조의 문제점
        - 모델을 학습시키기 위해서는 GPU 고성능 하드웨어가 필요 (비용 문제)
        - 모든 정보를 학습시키면 모델이 너무 커져서 응답 속도 저하뿐만 아니라 배포 및 유지보수가 어려워짐 (모델 크기 문제)
        - 기본적으론 FM/LLM은 학습되어진 시점의 정보만 알고 있음 (최신 정보 반영 문제)
    


Vector Database에 있는 데이터에서 찾아서,


### Conventional RAG (일반적인) vs GraphRAG
기본 RAG 동작 과정

1. 기존 데이터를 Vector로 변환하여 백터 저장소에 저장
    - Vector embeding : 데이터를 Vector로 변환하는 과정
    - Vector 계산 방법 : Cosine distance vs Euclidean distance - 어떤게 더 적합한지 (ex> kNN Algorithm(하나하나 비교하여 비교), ANN Algorithm(근사치))
    - kNN은 검색이 느리나 속도가 느림 / ANN은 정확성이 조금 떨어지나 속도가 빠름
    - ANN
        - Graph-based
        - Cluster-based
        - 많이 쓰이는 faiss HNSW 조합을 많이 사용함 ======================

    - OpenSearch
        - Semantic Search : 
        - Lexical Search : 
        - Hybid Search : Sematic + Lexical

> 백터 근사치로만 한다면 문제는 미묘한 논리적 관계나 근거 기반 검색에서 놓치는 부분이 많아짐


Graph RAG 동작 과정
관계 기반, 추론 기반, 근거 기반의 검색이 가능해짐

Knowledge Graph
- Noe (개체) ---Edge(rhksrP) - (팩트)--- Entity()


- Amazon Neptune를 활용해야됨



Data --> Document Chunks --> Vector Embedding --> Knowledge repository


Neptune or OpenSearch를 사용해야 하지만
요거를 Bedrock Knowledge Bases를 사용하면 됨



Vector : 질문에 대한 빠른 문서 검색 & 응답 생성이 필요한 경우
GraphRAG : 복잡한 관계를 맺고 있을때의 데이터 검색 혹은 추론이 필요한 경우

###
1. Vector : 

2. GraphRAG : 추천






---
## AI 아키텍처
* FM/LLM + RAG
    - 핵심 정보만 FM/LLM Fine-tuning
        - 변하지 않는 핵심 정보: 제품 사양, 기업 정책, 보안 기준 등
    - 자주 변하는 정보는 RAG로 처리
        - 자주 변하는 정보: 실시간 뉴스, 고객 데이터 등





---
# Kafethon
Multi-modal Model


# Bedrock
Nova API 호출 모델
- Invoke Model : 단일 프롬프트 (=> 우리가 사용할거)
- Coverse API : 대화에 맞게 


* 이미지를 만들어준다고 하면, 바코드 생성도 가능할까? => 근데 요거는 바코드가 개인고유 바코드이기 때문에 안되지 않을까?


* 모델 사용방법
    - Console: Bedrock - Bedrock Configurations -  모델활성화
    - API 호출: Dock 확인
* Message는 휘발성이기때문에 DynamoDB
* Quota 존재: RPM / TPM 확인 필요 - 모델별로 다름


* Prompt Engineering
    - 기존 학습되어 있는 내용으로 생성 진행
* RAG: 지식 저장소를 붙이는 거
    - Vector Store / Sematic serach
    - S3 (자료 저장소)
    - 임베딩 모델 (Titan)
    - 벡터 저정소 (Opensearch Serverless)
    - Knwlege Bases
        - Data Sources
        - Parsing Strategy
        - Chunking Strategy ("영희는 학교를 갔습니다" 기준으로 어뛓게 분리할것인지)
            - Fixed 
        - Embeding Model
        - Vector Store

* Bedrock Agent
    - 액션에 대한 WorkFlow를 구성해서 실행시켜주는 역할
    - 구성 시에 Instruction에 Lambda Function을 지정해줘야 한다

    - WorkFlow 기능이 있음

# SageMaker JumpStart

- Bedrock 보다 세부적인 설정이 가능
    - 


* 보안
    - 데이터 보호
    - 인증 및 승인 : VPC Config
    - 감사 가능성 : CloudTrail


