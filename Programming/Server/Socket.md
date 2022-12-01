# Socket

## Server
```
import socket

host = '0.0.0.0'
port = 80

server_sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_sock.bind((host, port))
server_sock.listen(1)
print("Waiting")

    
while True:
    client_sock, addr = server_sock.accept()
    print("Connecting", addr)

```
* socket : 통신을 위한 Socket 생성
* bind : 생성한 Socket에 IP/Port를 부여
* listen : 클라이언트 접속 대기
* accept : 접속한 클라이언트 정보 반환


## Client
```
import socket

# 접속 정보 설정
host = '127.0.0.1'
port = 5050
SIZE = 1024
SERVER_ADDR = (SERVER_IP, SERVER_PORT)

# 클라이언트 소켓 설정
with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as client_socket:
    client_socket.connect(SERVER_ADDR)  # 서버에 접속
    client_socket.send('hi'.encode())  # 서버에 메시지 전송
    msg = client_socket.recv(SIZE)  # 서버로부터 응답받은 메시지 반환
    print("resp from server : {}".format(msg))  # 서버로부터 응답받은 메시지 출력
```
* socket : 통신을 위한 Socket 생성
* q
* w
* e