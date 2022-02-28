Type: AWS::RDS::DBCluster
Properties: 
  AssociatedRoles: 
    - DBClusterRole
  AvailabilityZones: 
    - String
  BacktrackWindow: Long
  BackupRetentionPeriod: Integer
  CopyTagsToSnapshot: Boolean
  DatabaseName: String
  
  DBClusterIdentifier: String
  DBClusterParameterGroupName: String


  ReplicationSourceIdentifier: String


  SnapshotIdentifier: String
  SourceDBClusterIdentifier: String
  SourceRegion: String

  UseLatestRestorableTime: Boolean
  VpcSecurityGroupIds: 
    - String





CharacterSetName
DBClusterIdentifier
DBName
DeleteAutomatedBackups
EnablePerformanceInsights
KmsKeyId
StorageEncrypted
MasterUsername

MonitoringInterval

MonitoringRoleArn

PerformanceInsightsKMSKeyId

PerformanceInsightsRetentionPeriod


SourceDBInstanceIdentifier

SourceRegion
Timezone


########################
engine
- mariadb / mysql / postgres
- oracle
- sqlserver


SQL인경우
* LicencseModel 
    license-included
* TimeZone
    * q
    * w
    * e
    * 
* CA Certificate

Oracle인 경우
* LicencseModel 
    bring-your-own-license
    license-included
* TimeZone
    * q
    * w
    * e
* CA Certificate






TimezoneParam
CharacterSetNameParam
CACertificateIdentifierParam


DBSnapshotIdentifierParam
SourceDBInstanceIdentifierParam
SourceRegionParam



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


