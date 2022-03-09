# CloudFormation







## vs Terraform


* 비교 표
    | CloudFromation     | Terraform       |
    | ------------------ | ------------- |
    |             | Playbook/Role |
    | Resource            | Module        |
    | Resource Group/TAG | Inventory     |
    |  |  |
    |  |  |
    |  |  |
    |  |  |
    |  |  |
    |  |  |
    |  |  |
    |  |  |
    |  |  |
    |  |  |




## Template Syntax
### Template Fragment
```yaml
---
AWSTemplateFormatVersion: "version date"

Description:
  String

Metadata:
  template metadata

Parameters:
  set of parameters

Rules:
  set of rules

Mappings:
  set of mappings

Conditions:
  set of conditions

Transform:
  set of transforms

Resources:
  set of resources

Outputs:
  set of outputs

```
* __Format Version__ : AWS CloudFormation Template Version
* __Description__ : Template에 대한 설명
* __Metadata__ : Template에 대한 Metadata 정보를 제공하는 객체
* __Parameters__ : 실행 시간에 템플릿에 전달하는 값으로 ```Resources```, ```Outputs``` 섹션에서 파라미터 참조 가능
* __Rules__ : Stack 생성하거나 업데이트할 때, Tamplate에 전달된 파라미터 또는 파라미터 조합을 검증
* __Mappings__ : 조건부 파라미터 값을 지정하는데 사용할 수 있는 키와 관련된 값의 매핑으로 조회 테이블과 비슷. ```Resources```, ```Outputs``` 섹션에서 ```Fn::FindInMap``` 내장 함수를 사용하여 키를 해당 값으로 매핑 가능
* __Conditions__ : 스택 생성 또는 업데이트 시 특정 리소스 속성에 값이 할당되는지 또는 특정 리소스가 생성되는지 여부를 제어하는 조건 (예를 들면 스택이 프로덕션용인지 테스트 환경용인지에 따라 달라지는 리소스를 조건부로 생성)
* __Transform__ : Serverless Application인 경우 버전 지정
* __Resources__ : 리소스 및 해당 속성 지정. ```Resources```, ```Outputs``` 섹션에서 리소스 참조 가능
* __Outputs__ : 스택의 속성을 볼 때마다 반환되는 값 지정
</br>


### Format Version
* 기본적으로 값을 지정하지 않으면 최신 템플릿 포맷 버전이라고 가정한다.
> 무조건 적어주는 것이 좋다.
* Example
    ```yaml
    AWSTemplateFormatVersion: "2010-09-09"
    ```
</br>


### Descriptoin
* 템플릿에 대한 설명을 지정한다.
* Description은 0 ~ 1023 Byte 길이의 리터럴 문자열만 가능하다.
> 무조건 적어주는 것이 좋다.
* Example
    ```yaml
    Description: >
      Here are some
      details about
      the template.
    ```
</br>


### Metadata
* 템플릿에 대한 세부 정보를 제공하는 임의 JSON 또는 YAML 객체를 포함할 수 있다.
* CloudFormation은 Metadata 섹션에 포함된 정보를 변환, 수정 또는 삭제하지 않는다. 이러한 이유 때문에 이 섹션을 사용하여 암호나 보안 암호와 같은 민감한 정보를 저장하지 않는 것이 좋다.
> 리소스를 추가, 삭제, 변경을 할 때만 업데이트를 할 수 있다. 그렇기 때문에 변하지 않는 정보를 저장하면 될 거 같다.

> UserData 또는 OS CLI에서 사용할 정보, cfn-init을 활용 => AWS::CloudFormation::Init

> 입력 파라미터에 대한 그룹화 및 순서 정의 => AWS::CloudFormation::Interface
* Example
    ```
    Metadata:
      Instances:
        Description: "Information about the instances"
      Databases: 
        Description: "Information about the databases"

    Metadata: 
      AWS::CloudFormation::Interface: 
        ParameterGroups: 
          - 
            Label: 
              default: "Network Configuration"
            Parameters: 
              - VPCID
              - SubnetId
              - SecurityGroupID
          - 
            Label: 
              default: "Amazon EC2 Configuration"
            Parameters: 
              - InstanceType
              - KeyName
        ParameterLabels: 
          VPCID: 
            default: "Which VPC should this be deployed to?"
    ```
    * ```ParameterGroups```: 그룹 이름, 각 그룹의 파라미터 및 파라미터가 표시되는 순서를 지정
    * ```ParameterLabels```: Stack을 생성하거나 업데이트할 때 CloudFormation Console에 표시되는 파라미터 및 해당 표시 이름의 매핑

</br>


### Parameters
* 템플릿에 대한 사용자 지정 값을 입력할 수 있다.
> 기본적으로 Resource에 대한 파라미터 값을 지정. 생성 중인 리전과 계정에 있는 Resource 값인 경우, AWS에서 제공하는 파라미터 유형 이용.

> 보안이 준수되는 값인 경우 또는 동적 참조가 필요한 경우, SSM(secure) 파라미터 사용 사용
* Example
    ```
    Parameters:
      InstanceTypeParameter:
        Type: String
        Default: t2.micro
        AllowedValues:
          - t2.micro
          - m1.small
          - m1.large
        Description: Enter t2.micro, m1.small, or m1.large. Default is t2.micro.
    ```
1) ```AllowedPattern``` : Parameter 패턴에 대한 정규 표현식. 허용된 패턴과 일치하지 않으면 실행이 시작되지 않는다.
    * ex> ```AllowedPattern: "^i-[a-z0-9]{8,17}$"```
2) ```AllowedValues``` : Parameter에 허용된 값의 배열. 허용되지 않은 값을 입력하면 실행되지 않는다.
    * ex>
        ```yaml
        allowedValues:
          - AdConnector
          - AwsMad
          - SimpleAd
        ```
3) ```ConstraintDescription``` : 제약 위반 시 해당 제약을 설명하는 문자열.
    * ex> ```Malformed input-Parameter MyParameter must only contain uppercase and lowercase letters and numbers```
4) ```Default``` : 파라미터 기본 값
5) ```Type```
    * ```String``` : ```"ABC"```
    * ```Number``` : ```1234```
    * ```List<Number>``` : ```[80,22] ```
    * ```CommaDelimitedList``` : ```["dev","prd","stg"]```
    * AWS 특정 파라미터 : https://docs.aws.amazon.com/ko_kr/AWSCloudFormation/latest/UserGuide/parameters-section-structure.html#aws-specific-parameter-types
    * SSM 파라미터 : https://docs.aws.amazon.com/ko_kr/AWSCloudFormation/latest/UserGuide/parameters-section-structure.html#aws-ssm-parameter-types
6) 그 외 : MaxLength, MinLength, MaxValue, MinValue, NoEcho
7) 동적 참조
    * SSM Store 같이 스택 템플릿의 다른 서비스에서 저장 및 관리되는 외부 값을 지정하는 경우에 사용
    * ex> ```AccessControl: '{{resolve:ssm:S3AccessControl:2}}' ```
    * https://docs.aws.amazon.com/ko_kr/AWSCloudFormation/latest/UserGuide/dynamic-references.html
</br>


### Ruels
* https://docs.aws.amazon.com/ko_kr/AWSCloudFormation/latest/UserGuide/rules-section-structure.html
> 파라미터에 대한 검증이 필요한 경우
* Example
    ```
    ```
</br>



### Mappings
* 키를 해당하는 명명된 값 세트와 맞춘다. 해당 값의 경우, 파라미터, 가상 파라미터 또는 내장 함수를 포함할 수 없다.
* ```Fn::FindInMap``` 내장 함수를 사용하여 맵에서 값을 불러올 수 있다.
> 파라미터에 대하여 다양한 내용을 전달하고 싶을 때 사용
* Example
    ```
    AWSTemplateFormatVersion: "2010-09-09"
    Mappings: 
      RegionMap: 
        us-east-1:
          HVM64: ami-0ff8a91507f77f867
          HVMG2: ami-0a584ac55a7631c0c
        us-west-1:
          HVM64: ami-0bdb828fd58c52235
          HVMG2: ami-066ee5fd4a9ef77f1
        eu-west-1:
          HVM64: ami-047bb4163c506cd98
          HVMG2: ami-0a7c483d527806435
        ap-northeast-1:
          HVM64: ami-06cd52961ce9f0d85
          HVMG2: ami-053cdd503598e4a9d
        ap-southeast-1:
          HVM64: ami-08569b978cc4dfa10
          HVMG2: ami-0be9df32ae9f92309
    Resources:
      myEC2Instance: 
        Type: "AWS::EC2::Instance"
        Properties: 
          ImageId: !FindInMap [RegionMap, !Ref "AWS::Region", HVM64]
          InstanceType: m1.small
    ```
    * ```Fn::FindInMap: [ MapName, TopLevelKey, SecondLevelKey ]```
</br>


### Conditions
* 파라미터에 대한 조건을 설정하여, 리소스 또는 출력에 연결할 수 있다.
> 리소스를 파라미터 기준으로 추가/삭제 하고 싶을 때
* https://docs.aws.amazon.com/ko_kr/AWSCloudFormation/latest/UserGuide/conditions-section-structure.html
* Example
    ```
    Conditions:
      IsProduction: !Equals 
        - !Ref EnvType
        - prod
      CreateBucket: !Not 
        - !Equals 
          - !Ref BucketName
          - ''
      CreateBucketPolicy: !And 
        - !Condition IsProduction
        - !Condition CreateBucket
    ```
</br>


### Transform
* AWS CloudFormation에서 템플릿을 처리하는 데 사용하는 하나 이상의 매크로를 지정
* Serverless 사용하는 경우 사용
</br>


### Resources
* AWS 리소스 선언
* Example
    ```
    Resources:
      Logical ID:
        Type: Resource type
        Properties:
          Set of properties

    ```
</br>


### Outputs
* 다른 스택으로 가져오거나 응답으로 반환하는 출력 값을 선언한다.
* Example
    ```
    Outputs:
      StackVPC:
        Description: The ID of the VPC
        Value: !Ref MyVPC
        Export:
          Name: !Sub "${AWS::StackName}-VPCID"
    ```
> 교차 스택 참조를 위해서는 Export 사용
</br>
</br>


## Function
* https://docs.aws.amazon.com/ko_kr/AWSCloudFormation/latest/UserGuide/intrinsic-function-reference.html
* 연속된 Short Syntax 사용 불가
  ```
  # 불가
  !Base64 !Sub string
  !Base64 !Ref logical_ID

  # 가능
  !Base64
    "Fn::Sub": string

  Fn::Base64:
    !Sub string

  !Select [5, !Ref PriSubNameList]
  ```
</br>

### Fn::Base64
* UserData 속성을 통해 인코딩된 데이터를 EC2 Instance에 전달하는데 사용
* Fn::Base64 안에 Function 사용시, 최소한 하나는 전체 이름을 사용해야 한다.
* Example
    ```
    !Base64
      "Fn::Sub": string

    Fn::Base64:
      !Sub string
    ```
</br>


### Fn::Cidr
* CIDR 주소 블록의 Array를 반환한다.
* Example
    ```
    ```
</br>


### 조건 함수
* 조건을 기준으로 True, False 반환 => Fn::And / Fn::Equals / Fn::Not / Fn::Or
* True, False를 기준으로 결과 반환 => Fn::If
* Example
    ```
    NewVolume:
      Type: "AWS::EC2::Volume"
      Properties:
        Size:
          !If [CreateLargeSize, 100, 10]
        AvailabilityZone: !GetAtt: Ec2Instance.AvailabilityZone
      DeletionPolicy: Snapshot

    # example
    # Fn::And 와 Fn::Or의 최대 개수는 10개이다
    MyAndCondition: !And
      - !Equals ["sg-mysggroup", !Ref ASecurityGroup]
      - !Condition SomeOtherCondition   

    UseProdCondition:
      !Equals [!Ref EnvironmentType, prod]


    SecurityGroups:
      - !If [CreateNewSecurityGroup, !Ref NewSecurityGroup, !Ref ExistingSecurityGroup]         
    
    
    MyNotCondition:
      !Not [!Equals [!Ref EnvironmentType, prod]]
    ```
</br>


### Fn::FindInMap
* Mappings 섹션에서 선언된 맵의 키에 해당하는 값을 반환
* Example
    ```
    !FindInMap [ MapName, TopLevelKey, SecondLevelKey ]

    ImageId: !FindInMap [RegionMap, !Ref "AWS::Region", HVM64]
    ```
</br>



### Fn::GetAtt
*  템플릿의 리소스에서 속성 값을 반환
*  https://docs.aws.amazon.com/ko_kr/AWSCloudFormation/latest/UserGuide/intrinsic-function-reference-getatt.html
* Example
    ```
    !GetAtt logicalNameOfResource.attributeName

    SourceSecurityGroupName: !GetAtt myELB.SourceSecurityGroup.GroupName
    ```
</br>


### Fn::GetAZs
* 지정된 리전의 가용 영역을 알파벳순으로 나열하는 Array 반환
* Example
    ```
    !GetAZs region

    Fn::GetAZs: ""
    Fn::GetAZs:
      Ref: "AWS::Region"
    Fn::GetAZs: us-east-1
    ```
</br>



### Fn::ImportValue
* 다른 스택에서 내보낸 출력의 값을 반환한다.
* Example
    ```
    !ImportValue sharedValueToImport

    Fn::ImportValue:
      !Sub "${NetworkStack}-SubnetID"

    !ImportValue:
      Fn::Sub "${NetworkStack}-SubnetID"
    ```
</br>



### Fn::Join
* 지정된 구분 기호로 구분된 값을 연결한다.
* Example
    ```
    !Join [ ":", [ a, b, c ] ]

    !Join
      - ''
      - - 'arn:'
        - !Ref AWS::Partition
        - ':s3:::elasticbeanstalk-*-'
        - !Ref AWS::AccountId
    ```
</br>



### Fn::Select
* 객체 목록 중에서 객체 하나 반환
* Example
    ```
    !Select [ index, listOfObjects ]

    Parameters: 
      DbSubnetIpBlocks: 
        Description: "Comma-delimited list of three CIDR blocks"
        Type: CommaDelimitedList
        Default: "10.0.48.0/24, 10.0.112.0/24, 10.0.176.0/24"


    CidrBlock: !Select [ 0, !Ref DbSubnetIpBlocks ]

    AvailabilityZone: !Select 
      - 0
      - Fn::GetAZs: !Ref 'AWS::Region'
    ```
</br>


### Fn::Split
* 문자열을 구분 기호로 분할하여 목록 반환
* Example
    ```
    !Split [ "|" , "a|b|c" ]

    !Select [2, !Split [",", !ImportValue AccountSubnetIDs]]
    ```
</br>


### Fn::Sub
* 입력 문자열의 변수를 지정한 값으로 변환
* Example
    ```
    Name: !Sub
      - www.${Domain}
      - { Domain: !Ref RootDomainName }
    
    
    # 지정 값을 설정하지 않을 수 도 있다.
    !Sub 'arn:aws:ec2:${AWS::Region}:${AWS::AccountId}:vpc/${vpc}'
    ```
</br>


### Fn::Transform
* 매크로 지정
* https://docs.aws.amazon.com/ko_kr/AWSCloudFormation/latest/UserGuide/intrinsic-function-reference-transform.html
* Example
    ```
    ```
</br>


### Ref
* 파라미터 참조
* Example
    ```
    MyEIP:
      Type: "AWS::EC2::EIP"
      Properties:
        InstanceId: !Ref MyEC2Instance
    ```
</br>
</br>


## Pseudo parameters
* 가상 파라미터는 AWS CloudFormation에서 사전 정의된 파라미터
* ```AWS::AccountId``` : 스택이 생성되는 계정의 AWS 계정 ID를 반환(예: 123456789012).
* ```AWS::NotificationARNs``` : 현재 스택에 대한 알림 Amazon 리소스 이름(ARN) 목록을 반환
* ```AWS::NoValue``` : 반환 값으로 지정된 경우 해당 리소스 속성을 제거
* ```AWS::Partition``` : 리소스가 있는 파티션을 반환
* ```AWS::Region``` : 리소스를 생성하는 리전을 나타내는 문자열 반환(예: us-west-2).
* ```AWS::StackId``` : aws cloudformation create-stack 명령으로 지정된 스택의 ID 반환(예: arn:aws:cloudformation:us-west-2:123456789012:stack/teststack/51af3dc0-da77-11e4-872e-1234567db123).
* ```AWS::StackName``` : aws cloudformation create-stack 명령으로 지정된 스택의 이름 반환(예: teststack).
* ```AWS::URLSuffix``` : 도메인에 대한 접미사를 반환한다. 접미사는 일반적으로 amazonaws.com이지만 리전에 따라 다를 수 있고, 예를 들면 중국(베이징) 리전의 접미사는 amazonaws.com.cn 이다.
</br>
</br>


## Module


