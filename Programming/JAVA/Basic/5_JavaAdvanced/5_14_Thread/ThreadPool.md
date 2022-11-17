# Thread Pool
* 스레드를 직접 만들어 사용할 경우, 작업을 마친 스레드는 종료됨
    * 멀티 스레드 작업을 계속할 경우, 스레드를 생성/삭제하는 Overhead가 있다.
* 스레드 풀은 미리 스레드를 생성해 두고, 작업만 스레드에 할당하여 동작
* 미리 생성해 둔 스레드의 집합을 스레드 풀이라고 함
* 배치 작업(모아두고 한번에 처리하는 작업)에 많이 사용됨

## Thread Pool 종류
### newCachedThreadPool
* 초기 스레드가 0개
* 코어 스레드가 0개, 코어 스레드가 일하지 않아도 살려두는 스레드
* 요청이 작업 스레드보다 많아지면 새 스레드를 생성
* 60초 동안 일하지 않으면 스레드는 제거

#### Callable
* newCachedThreadPool은 Runnable 뿐만 아니라 Callable을 상속한 스레드 작업을 요청할 수 있다.
* Runnable과 다르게 Callable은 Future 클래스를 이용하여 값을 반환할 수 있다.


### newFixedThreadPool
* 최대 스레드 nThread 개
* 코어 스레드 nThread 개
* 주어진 스레드 개수만큼 생성하고 그 수를 유지. 작업하지 않아도 스레드를 제거하지 않는다.

## 스레드 풀 사용 방법
1. 스레드 풀 생성
    ```java
    ExecutorService pool1 = Executors.newCachedThreadPool();
    ExecutorService pool2 = Executors.newFixedThreadPool(15);
    ExecutorService es = new ThreadPoolExecutor(
                    10, // 코어 스레드 개수
                    100, // 최개 스레드 개수
                    120, // 대기 시간
                    TimeUnit.SECONDS, // Time Unit
                    new SynchronousQueue<Runnable>() // 요청 -> 작업을 쌓아 두는 큐 -> 스레드 풀
            );
    ```

2. 스레드 작업 생성
    ```
    class Work implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++)
                System.out.print("~");
        }
    }
    
    class CallableWork implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "작업 종료";
        }
    }
    ```

3. 스레드에 작업 요청
    ```java
    Future<String> future;
    future = pool1.submit(new CallableWork());
    for (int i = 0; i < 100; i++){
       pool1.submit(new Work());   
    }
   
    // Callable 경우, 값을 반환받을 수 있다.
    // get()은 Blocking Method로, 영원히 기다릴 수 있으므로 timeout 설정이 가능
    try {
       future.get();
    } catch (InterruptedException e) {
        e.printStackTrace();   
    } catch (ExecutionException e) {
       e.printStackTrace();   
    }
    ```

4. 스레드 작업 종료
    * 스레드 풀은 자동 종료가 되지 않기 때문에 직접 스레드 풀을 종료해야 한다.
    ```java
    // Thread.join()과 마찬가지로 모든 작업이 모두 끝나기를 가다린다.
    pool1.shutdown();
    
    // 해당 스레드의 작업을 취소
    future.cancel(true);   // true : 동작하고 있는 Thread에 Interrupt 발생시키고 취소한다.
                           // false : Future를 취소한다. Cancel Exception을 발생시킨다.
                           // 그렇기 때문에 안의 동작에 Interrupt or Cancel Exception을 처리 해주는 것이 좋다.
    
    // 작업이 취소됐는지 확인
    future.isCancelled();
   
    // 작업이 끝났는지 확인
    future.isDone();
    ```

