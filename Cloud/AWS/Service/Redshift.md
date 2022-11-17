# AWS Redshift
* AWS 관리형 데이터 하우스 서비스




## Node Type
1. RA3

2. DC2





### Resizing
* https://aws.amazon.com/ko/premiumsupport/knowledge-center/redshift-elastic-resize/
* 슬라이스 매핑을 사용하여 Cluster 크기를 조정
1. 탄력적 크기 조정 (Elastic resize change)
    * Cluster는 유지한 채, Node만 추가한다. 그렇기 때문에 Cluster의 Endpoint가 변경되지 않는다.
    * 전체 데이터 슬라이스를 슬라이스 매핑된 노드에 복제된다.
2. 클래식 크기 조정 (Classic resize change)
    * 새로운 Cluster를 생성하고, 모든 행을 Cluseter에 저장한다. 그 후에 노드에 매핑하여 분포 설정에 따라 분할한다.
    * Cluster의 Enpoint가 바뀌는거 같다.
</br>



ami-0e0f72498373d693d
t2.medium
<powershell>
net user Administrator “koreanre12!”
$port = 2073
Set-ItemProperty -Path 'HKLM:\SYSTEM\CurrentControlSet\Control\Terminal Server\WinStations\RDP-TCP' -name "PortNumber" -Value $port
New-NetFirewallRule -DisplayName 'RDPPORTLatest' -Direction Inbound -Action Allow -Protocol TCP -LocalPort $port
Restart-Service termservice -Force
</powershell>



## 구축 방법
* 
1. q
2. w
3. e
4. r









