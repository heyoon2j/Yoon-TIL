```
[AppStream]
name = Rocky Linux-$releasever - AppStream
baseurl = http://xxx.test.com/rocky/$releasever/AppStream/$basearch/os/
gpgcheck=0
enabled=1

```
- $releasever: OS 릴리스 버전
- $basearch: 시스템 아키텍처 (ex> x86_64)
- gpgcheck: GPG(GNU Privacy Guard) 서명 검사. 내부 레포지토리인 경우, 필요에 따라 비활성화 가능
    - gpgkey 설정 필요 (활성화시)
- enabled: 레포지토리 활성화 여부

* 그 외
    - mirrorsite: 다운로드 사이트 (baseurl 대체 가능)
