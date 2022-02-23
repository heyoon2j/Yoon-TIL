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

MasterUsername

MonitoringInterval

MonitoringRoleArn

PerformanceInsightsKMSKeyId

PerformanceInsightsRetentionPeriod

PromotionTier

SourceDBInstanceIdentifier

SourceRegion

StorageEncrypted

Timezone