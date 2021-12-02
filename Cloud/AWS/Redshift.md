# AWS Redshift
* AWS 관리형 데이터 하우스 서비스



## Node Type
1. RA3

2. DC2





ami-0e0f72498373d693d
t2.medium
<powershell>
net user Administrator “koreanre12!”
$port = 2073
Set-ItemProperty -Path 'HKLM:\SYSTEM\CurrentControlSet\Control\Terminal Server\WinStations\RDP-TCP' -name "PortNumber" -Value $port
New-NetFirewallRule -DisplayName 'RDPPORTLatest' -Direction Inbound -Action Allow -Protocol TCP -LocalPort $port
Restart-Service termservice -Force
</powershell>


