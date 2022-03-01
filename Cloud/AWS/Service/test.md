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
* oracle ()