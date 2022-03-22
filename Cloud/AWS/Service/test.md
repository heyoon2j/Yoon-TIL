* Snapshot 지우기

MasterUsername
MasterUserPassword
CharacterSetName
DBClusterIdentifier
DBName
DeleteAutomatedBackups
EnablePerformanceInsights
KmsKeyId
MonitoringInterval
MonitoringRoleArn
PromotionTier
PerformanceInsightsKMSKeyId
PerformanceInsightsRetentionPeriod
SourceDBInstanceIdentifier
SourceRegion
StorageEncrypted
Timezone

postgres (o)
oracle
mssql


########################
engine
- mariadb / mysql / postgres
- oracle
- sqlserver

Postgres는 no option?


SQL인경우
* TimeZone
    * Korea Standard Time
    * UTC
    * UTC-02
    * UTC-09
    * UTC-08
    * UTC-11
    * UTC+12
    * UTC+13
CharacterSetNameParam
    SQL_Latin1_General_CP1_CI_AS
LicenseModel

    
Oracle인 경우
* LicenseModel 
    bring-your-own-license
    license-included
CharacterSetNameParam
    AL32UTF8
    UTF8

DBInstanceIdentifier
DBInstanceClass
Engine: !Ref EngineParam
EngineVersion: 
# LicenseModel: 


#  DBSnapshotIdentifierParam:
#  SourceDBInstanceIdentifierParam:
#  SourceRegionParam:


DBName
MasterUsername
MasterUserPassword
Port
DBParameterGroupName
OptionGroupName
# TimezoneParam
# CharacterSetNameParam
CACertificateIdentifierParam


# DBSnapshotIdentifierParam
# SourceDBInstanceIdentifierParam
# SourceRegionParam


StorageTypeParam
StorageSizeParam
MaxAllocatedStorageParam
KmsKeyParam


PubliclyAccessibleParam
SubnetGroupNameParam
AzParam
SecurityGroupListParam


MultiAz


BackupRetentionPeriodParam
PreferredBackupWindowParam


CloudwatchLogsExportParam
MonitoringIntervalParam
MonitoringRoleArnParam
PreferredMaintenanceWindowParam


# Test 진행
* postgres (o)
* mysql ()
* mssql (o)
* oracle (o)


############################
Cluster
#  * GlobalClusterIdentifier
  * MasterUsername
#  * ReplicationSourceIdentifier
#  * RestoreType
 # * SourceDBClusterIdentifier
 # * SourceRegion
 # * StorageEncrypted
 # * UseLatestRestorableTime


kmsKyeparam
databasename
masterusernameparam
masteruserpasswordparam


response = client.restore_db_cluster_from_snapshot(
    AvailabilityZones=[
        'string',
    ],
    DBClusterIdentifier='string',
    SnapshotIdentifier='string',
    Engine='string',
    EngineVersion='string',
    Port=123,
    DBSubnetGroupName='string',
    DatabaseName='string',
    OptionGroupName='string',

    VpcSecurityGroupIds=[
        'string',
    ],
    Tags=[
        {
            'Key': 'string',
            'Value': 'string'
        },
    ],
    KmsKeyId='string',
    EnableIAMDatabaseAuthentication=True|False,
    BacktrackWindow=123,
    EnableCloudwatchLogsExports=[
        'string',
    ],
    EngineMode='string',
    ScalingConfiguration={
        'MinCapacity': 123,
        'MaxCapacity': 123,
        'AutoPause': True|False,
        'SecondsUntilAutoPause': 123
    },
    DBClusterParameterGroupName='string',
    DeletionProtection=True|False
)



response = client.create_db_instance(
    DBName='string',
    DBInstanceIdentifier='string',
    AllocatedStorage=123,
    DBInstanceClass='string',
    Engine='string',
    MasterUsername='string',
    MasterUserPassword='string',
    DBSecurityGroups=[
        'string',
    ],
    VpcSecurityGroupIds=[
        'string',
    ],
    AvailabilityZone='string',
    DBSubnetGroupName='string',
    PreferredMaintenanceWindow='string',
    DBParameterGroupName='string',
    BackupRetentionPeriod=123,
    PreferredBackupWindow='string',
    Port=123,
    MultiAZ=True|False,
    EngineVersion='string',
    AutoMinorVersionUpgrade=True|False,
    LicenseModel='string',
    Iops=123,
    OptionGroupName='string',
    CharacterSetName='string',
    PubliclyAccessible=True|False,
    Tags=[
        {
            'Key': 'string',
            'Value': 'string'
        },
    ],
    DBClusterIdentifier='string',
    StorageType='string',
    TdeCredentialArn='string',
    TdeCredentialPassword='string',
    StorageEncrypted=True|False,
    KmsKeyId='string',
    Domain='string',
    CopyTagsToSnapshot=True|False,
    MonitoringInterval=123,
    MonitoringRoleArn='string',
    DomainIAMRoleName='string',
    PromotionTier=123,
    Timezone='string',
    EnableIAMDatabaseAuthentication=True|False,
    EnablePerformanceInsights=True|False,
    PerformanceInsightsKMSKeyId='string',
    PerformanceInsightsRetentionPeriod=123,
    EnableCloudwatchLogsExports=[
        'string',
    ],
    ProcessorFeatures=[
        {
            'Name': 'string',
            'Value': 'string'
        },
    ],
    DeletionProtection=True|False
)






LBTypeParam:
Description: 'Load Balancer Type'
Type: String
Default: 'application'
AllowedValues:
  - 'application'
  - 'gateway'
  - 'network'

LBNameParam:
Description: 'Load Balancer Name (Maximun: 36)'
Type: String
MaxLength: 32
MinLength: 1
AllowedPattern: "^[^(internal-)][\w-]{1,32}$"

SchemeParam:
Description: 'Load Balancer Scheme : internet-facing / internal'
Type: String
Default: 'internal'
AllowedValues:
  - 'internet-facing'
  - 'internal'

IpAddressTypeParam:
Description: 'IP Address Type : dualstack / ipv4'
Type: String
Default: 'ipv4'
AllowedValues:
  - 'dualstack'
  - 'ipv4'


SubnetListParam:
Description: 'Subnet List'
Type: CommaDelimitedList


SecurityGroupListParam:
Description: 'Security Group List'
TYpe: CommaDelimitedList
































