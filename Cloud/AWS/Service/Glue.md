# AWS Glue
* AWS 관리형 ETL 서비스 / Extract, Transform, Load
* AWS Glue는 Apache Spark환경에서 실행된다(PySpark or Scala 사용 가능)
* csv와 같은 텍스트 기반 데이터를 처리하려면 AWS Glue 용으로 UTF-8 인코딩해야 한다.


## Glue 용어 정리
* __Data Store__ : 데이터를 영구적으로 저장하기 위한 Repository. 결국 저장 장소를 의미한다.
* __Data Source__ : 변환의 입력 값으로 사용되는 데이터 스토어.
* __Data Target__ : 변환 출력 값이 쓰이는 데이터 스토어.
* __Data Catalog__ : AWS Glue의 Metadata Store. Glue 환경 구성을 위한 정의들을 관리한다.
    * __Connection__ : 특정 Data Store에 연결하는데 필요한 속성을 포함하는 Data Catalog 객체 메타데이터
    * __Database__ : 논리 그룹으로 구성된 일련의 연결된 Data Catalog 테이블 정의 메타데이터.
    * __Table__ : 데이터 스키마를 정의 메타데이터.
    * __Crawler__ : Data Store에서 어떤 데이터 스키마를 가지고 올지 결정하여 AWS Glue Data Catalog에 Metadata Table 생성
* __Job__ : ETL 작업을 수행하는데 필요한 비즈니스 로직. 변환 스크립트, 데이터 소스 및 대상으로 구성된다.
* __Trigger__ : ETL 작업 시작에 대한 Event Trigger 설정 가능.
</br>


## Glue 동작 과정



</br>


## Glue 구축 과정
r
### Data Catalog 




### Create Job




### Execute Job




### Monitoring




</br>
</br>



