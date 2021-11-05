# Structure

## File

### Binary Log vs Redo Log
* https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=parkjy76&logNo=220918956412

|        | Redo Log | BinLog |
|--------|------------|----------|
| 출력원 | Engine Level | Server Level |
| 구분 | 물리적 로그 | SQL에 대응하는 논리적인 로그 |
| 용도 | Engine의 갱신 정보의 일시적 보존. ACID를 보장. Recovery | DB의 데이터 복원. Replication |