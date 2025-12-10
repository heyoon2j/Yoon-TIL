/*
00. CLI 명령어

- go mod init <모듈명>: 새로운 Go 모듈 초기화

- go fmt <파일명>: 해당 Go 파일의 코드를 포맷팅
- go get <패키지명>: 외부 패키지를 다운로드 및 설치
- go test <패키지명>: 해당 패키지의 테스트 코드 실행

- go mod tidy: 사용되지 않는 모듈 정리 및 필요한 모듈 추가	


- 빌드
	- go run <파일명>: 해당 Go 파일을 컴파일하고 실행 (임시 파일 생성)
	- go build <파일명>: 해당 Go 파일을 컴파일하여 실행 파일 생성
	- go build -o <출력파일명> <파일명>: 출력 파일명을 지정하여 컴파일
	- go build -modfile=<파일명> <파일명>: 특정 모듈 파일(mod)을 사용하여 컴파일
	- go build -mod=vendor <파일명>: vendor 디렉토리를 사용하여 컴파일

- 크로스 빌드
	GOOS=linux GOARCH=amd64 go build -o app .
	GOOS=darwin GOARCH=arm64 go build -o app .
	CGO_ENABLED=0 go build -o app .
*/


```sh
$ go mod init main

```


/*
Directory Structure
- src/: 소스 코드 디렉토리
- bin/: 컴파일된 실행 파일 디렉토리
- pkg/: 패키지 파일 디렉토리
- vendor/: 외부 패키지 디렉토리
- go.mod: 모듈 정의 파일
- go.sum: 모듈 의존성 체크섬 파일
*/


/*


*/

