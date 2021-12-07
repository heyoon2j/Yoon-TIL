## AMI Migration
* 가상 이미지를 AMI로 변환하는 작업
* 공식: 
* AWS CloudEndure 사용해도 된다.
</br>

### OVF
* Open Virtualization Format
* 개방형 가상화 포맷, 가상 머신에서 구동되는 이미지의 표준화이다.
* 기본적으로 특정 소프트웨어(ex> Vmware)를 통해 이미지를 만드는 경우, 해당 이미지에 특정 소프트웨어에 최적화된 메타데이터가 포함되어 만들어진다. 그렇기 때문에 해당 이미지만으로는 다른 소프트웨어에서 이미지를 사용하기 힘들다(최적화 문제!).
</br>


### Prerequisites
* 기본적으로 S3가 있어야 한다.
* S3에 접근할 IAM Role
* 특정 소프트웨어(VMWare 등) Import에 역할 위임 및 S3에 접근에 필요한 IAM Role
* AWS CLI 설치
</br>
</br>


## VMWare -> OVF -> OVA -> AMI
* 공식
    * https://docs.aws.amazon.com/vm-import/latest/userguide/vmimport-image-import.html
    * https://docs.aws.amazon.com/vm-import/latest/userguide/vmie_prereqs.html#vmimport-role
* Reference
    * https://cloudest.oopy.io/posting/055
    * https://www.megazone.com/vmware/
1. Export OVF
    * VMWare Client에서 Export OVF Template Click
2. OVF convert to OVA
    * https://onlyit.tistory.com/entry/How-to-convert-ovf-to-ova-and-verse
    * VMWare OVF Tool 설치 : https://developer.vmware.com/web/tool/4.4.0/ovf
    * OVF Tool로 이동 : ```cd "C:\ProgramFiles\VMware\VMware OVF Tool"```
    * OVF를 OVA로 변환 : ```ovftool.exe -tt=ova "<Path>\test.ovf" "<Path>"```
    * OVA 파일이 있는 Dir으로 이동
    * SFTP 전송
        ```
        $ sftp -P 40022 <account>@<ip>
        $ put test.ova
        ```
3. S3 객체 생성
4. IAM 생성
    * 전환 작업을 위한 Role 생성 : vmimport sts assume & ec2 sts assume / s3 access policy / kms policy / ec2 policy
    * sts assume policy
        ```
        {
            "Version": "2012-10-17",
            "Statement": [
                {
                    "Effect": "Allow",
                    "Principal": {
                        "Service": "ec2.amazonaws.com"
                    },
                    "Action": "sts:AssumeRole"
                },
                {
                    "Effect": "Allow",
                    "Principal": {
                        "Service": "vmie.amazonaws.com"
                    },
                    "Action": "sts:AssumeRole",
                    "Condition": {
                        "StringEquals": {
                        "sts:Externalid": "vmimport"
                        }
                    }
                }
            ]
        }
        ```
    * s3 access policy
        ```
        {
            "Version": "2012-10-17",
            "Statement": [
                {
                    "Effect": "Allow",
                    "Action": "s3:*",
                    "Resource": [
                        "arn:aws:s3:::<Bucket_Name>/*",
                        "arn:aws:s3:::<Bucket_Name>"
                    ]
                }
            ]
        }
        ```
    * ec2 import policy
        ```
        {
            "Sid": "VisualEditor2",
            "Effect": "Allow",
            "Action": [
                "ec2:CopySnapshot",
                "ec2:Describe*",
                "ec2:ModifySnapshotAttribute",
                "ec2:RegisterImage",
                "ec2:ImportImage",
                "ec2:CancelImportTask"
            ],
            "Resource": "*"
        }
        ```
    * kms policy
        ```
        {
            "Version": "2012-10-17",
            "Statement": [
                {
                    "Effect": "Allow",
                    "Action": [
                        "kms:Decrypt",
                        "kms:DescribeKey",
                        "kms:Encrypt",
                        "kms:GenerateDataKey*",
                        "kms:ReEncrypt*"
                    ],
                    "Resource": "<s3_kms_arn>"
                }
            ]
        }
        ```
5. AWS CLI
    * 디스크를 S3에 저장
        * Metadata 
        ```
        # aws s3 cp <LocalPath> <S3Uri> --acl bucket-owner-full-control

        $ aws s3 cp ~/i-oneNet-ES5.0.ova s3://s3-test-convert --acl bucket-owner-full-control
        ```
    * s3에 있는 ovf를 ami로 import
        * encrypted, kms-key-id option을 통해 암호화된 Root Volume을 가진 이미지를 ami로 import 할 수 있다.
        * 
        ```
        # aws ec2 import-image --description "My server disks" --disk-containers "file://C:\import\containers.json"

        $ aws ec2 import-image --description "My server VM" --disk-containers "file://~/containers.json"
        $ aws ec2 import-image --description "My server VM" --disk-containers "file://~/containers.json" --role-name vmimport
        ```
    * import 진행상황 확인
        ```
        # aws ec2 describe-import-image-tasks --import-task-ids import-ami-<>
        $ aws ec2 describe-import-image-tasks --import-task-ids import-ami-09d7ec2bfc0e464a8
        ```

6. 고려해야할 상황
    * 일부 운영체제는 Import 중 향상된 네트워킹 Driver가 자동으로 설치가 되지 않는다. 그렇기 때문에 활성화가 필요 (https://docs.aws.amazon.com/ko_kr/vm-import/latest/userguide/vmimport-image-import.html)
        * 유형에 따라 ENA / intel 82599 VF Interface를 사용해야 한다.
        * ENA가 지원되는 커널 버전 (https://github.com/amzn/amzn-drivers/blob/master/kernel/linux/ena/RELEASENOTES.md)
        * intel이 지원되는 커널 버전 ()
</br>