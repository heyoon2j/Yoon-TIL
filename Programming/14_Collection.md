# Collection
자료구조를 구현한 라이브러리


## Deque
양방향 큐로, FIFO가 아닌 양쪽 끝에 요소를 추가/삭제할 수 있다.
* O(1) (기존 큐와 동일)
* 사용 방법
    ```python
    from collections import deque
    deq = deque()
    deq = deque(maxlen = 10)

    deq.append(5)       # 5
    deq.appendleft(4)   # 4, 5

    arr = [1, 0]
    deq.extend(arr)     # 4, 5, 1, 0
    deq.extendleft(arr) # 1, 0 , 4, 5, 1, 0

    deq.pop()           # 1, 0 , 4, 5, 1
    deq.popleft()       # 0, 4, 5, 1

    deq.remove(4)       # 0, 5, 1
    deq.rotate(2)       # 5, 1, 0
    deq.rotate(-2)      # 0, 5, 1
    ```