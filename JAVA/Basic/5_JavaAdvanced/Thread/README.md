# Thread
1. 프로세스(Process)
    * 운영체제(OS)로 부터 시스템 자원을 할당받는 작업의 단위
    * 실행되고 있는 프로그램(프로그램과 프로세트의 차이)
      * 프로그램은 코드와 정적인 데이터의 묶음
    * Code, Data, Stack, Heap 할당

2. 쓰레드(Thread)
    * 프로세스 내에서 실행되는 작업의 최소 단위
    * Code, Data, Heap 영역은 공유 / Stack은 각 각 할당

## Thread 구현 
1. **extends Thread**
    * Thread Class를 상속받아 하위 Thread 클래스를 생성
    ```java
    class myThread extends Thread{
       public void run(){
           // 실행 코드
       }
    }
    class Test{
       public static void main(String[] args){
           Thread thread = new myThread();
           thread.start();
       }     
    }
    ``` 

2. **implements Runnable**
    * Runnable Interface를 구현하는 방법
    ```java
      class myThread implements Runnable{
        public void run(){
         // 실행 코드
        }
    }
    class Test{
       public static void main(String[] args){
           Thread thread = new Thread(new myThread());
           thread.start();
       }     
    }   
    ```     

## Multi-Thread 프로그래밍
* 동시에 여러 개의 Thread가 수행되는 프로그래밍
    * **동시성(Concurrency)**: 멀티 작업을 위해서 하나의 코어에서 Multi Thread가 번갈아가며 실행하는 성질
    * **병렬성(Parallelism)**: 멀티 작업을 위해 멀티 코어에서 개별 Thread를 동시에 실행하는 성질
* Thread는 각각의 작업공간(Context)를 가짐
    * Thread가 Switching이 되면, Context Switching이 발생
* 공유 자원이 있는 경우, Race Condition이 발생
* Critical Section에 대한 동기화(Synchronization)의 구현이 필요

## Thread 상태
![ThreadStatus](ThreadStatus.jpg)
* **NEW** : 쓰레드 객체 생성
* **RUNNABLE** : 실행 대기
* **RUN** : 실행
* **WAITING** : 일시 정지
* **TIMED_WAITING** : 주어진 시간 동안 일시정지
* **BLOCKED** : 동기화 블록에서 Lock이 된 상태
* **TERMINATED** : 종료

### Thread Method
* **start()** : Thread 실행(정확히는 실행 대기 상태로 만든다)
* **yield()** : 실행 중에 우선순위가 동일한 다른 Thread에게 양보하고 실행 대기 상태로 전환
* **sleep()** : 주어진 시간 동안 Thread는 일시 정지가 되고, 주어진 시간 후 실행 대기 상태로 전환
* **join()** : join() 메소드를 호출한 Thread는 일시 정지되며, join()이 가리킨 Thread가 종료되거나, 주어진 시간이 끝나야 실행되기 상태가 된다.
* **wait()** : 동기화 블록 내에서 스레드를 일시 정지 상태로 만든다.
    * 주어진 시간이 지나면 실행대기 상태로 전환
    * 시간이 주어지지 않는 경우, notify(), notifyAll()에 의해 일시 정지 상태에 있는 Thread를 실행대기 상태로 전환
* **notify(), notifyAll()** : 동기화 블록 내에서 wait()에 의해 일시 정지 상태에 있는 Thread를 실행대기 상태로 전환
    * notify() 보다는 nofityAll()을 사용하는 것이 scheduler에 CPU를 점유하는 것이 좀 더 공평하다고 본다.
* **interrupt()** : 일시 정지 상태의 Thread에서 InterruptedException 예외를 발생시켜, 예외처리 코드(catch)에서
실행 대기 상태로 가거나 종료 상태로 갈 수 있도록 한다.
> Thread가 RUN 또는 RUNNABLE 상태일 때, interrupt를 발생시키면 미래에 Thread가 일시 정지 상태가 되면 InterruptedException 예외 발생
>> 그렇기 때문에 일시 정지가 아닐 때는 다른 방법을 사용해야 되는데, STOP 플래그 or isInterrupted() or interrupted()를 이용하는 방법이 있다.

## Critical Section & Synchronization
* Critical Section : 두 개 이상의 Thread가 동시에 접근하게 되는 리소스 영역
* Synchronization : Critical Section에 여러 Thread가 접근하는 경우, 하나의 Thread가 수행하는 동안 공유 자원을 Lock을 걸어
 다른 Thraed의 접근을 막을 수 있다.
> Synchronization을 잘못 구현하면 Deadlock에 빠질 수 있다.

### Synchronization 구현 방법
* synchronized 수행문과 synchronized 메소드를 이용하는 방법이 있다.
    * synchronized 수행문 : object에 해당되는 객체에 locak을 건다.
    ```java
    class SynchronizedTest{
      public static void main(String[] args){ 
          Object object = new Object();
          synchronized (object){  // object에는 공유할 자원을 넣는다.
            // 실행 코드
        }
      } 
    }
    ``` 
    * synchronized 메소드 : 현재 이 메소드가 속해 있는 객체에 Lock을 건다.
    ```java
    public synchronized void method(){
      // Critical Section
      // synchronized 메소드 내에서 다른 synchronized 메소드를 호출하지 않는다(Deadlock 방지)
    }
    ```



## DeadLock
* 교착 상태란 하나 또는 둘 이상의 프로세스가 자원을 할당 및 해제 할 수 없는 자원을 계속 기다리는 상태. 
* 교착 상태 발생의 필요 조건은 아래와 같다. 교착 상태가 발생하는 경우, 해당 조건은 항상 만족한다는 의미지, 조건이 만족한다고 해서 항상 발생하는 것이 아니다. 

### Deadlock Necessary Condition(교착 상태 발생의 필요 조건)
1. **Mutual Exclusion(상호 배제)**
    * At least one resource must be held in a non-sharable way.
    * 하나의 공유 자원을 다른 프로세스들과 공유하지 않고, 자기만 사용하는 경우
    
2. **Hold and Wait(점유와 대기)**
    * A process must be holding a resource and waiting for another.
    * 하나의 프로세스가 하나의 공유 자원을 사용하는 도중 다른 공유 자원을 사용하는 경우
    
3. **No Preemption(비선점)**
    * Resource cannot be preempted.
    * Resource를 프로세스로 부터 도중에 해체가 되지 않는 경우.
    
4. **Circular Wait(환형 대기)**
    * A waits for B, B waits for C, C waits for A
    * 영어 그래도 생각하면 된다. A가 B를 기다리고, B는 C를, C는 A를 기다리는 경우.

* Example
![MutualExclusion.png](MutualExclusion.png)
    
### Deadlock을 피하는 방법
1. **Prevention**
    * Ensure that the system will never enter a deadlock state
    * 교착 상태의 필요조건을 부정함으로써 교착 상태가 발생하지 않도록 미리 예방
    > ex> synchronization 블록에서 다른 synchronization 블록을 호출하지 않는다.

2. **Deadlock Avoidance**
    * Ensure that the system will never enter an unsafe state
    * 교착 상태 가능성을 배제하지 않고, 적절히 피해나가는 방법
    > ex> 조건문 등을 이용하여 synchronization 블록이 호출되지 않도록 한다.
    
3. **Detection**
    * Allow the system to enter a deadlock state and then recover
    * 교착 상태를 허용하고, 교착 상태에 빠지면 회복하는 방법
    * Deadlock이 발생했는지 검사하려고 하는 검사 알고리즘이 필요하며, 최소 비용으로 중지시키는 방법을 찾아야 한다(희생자 선택의 원칙)
    > ex> 교착 상태 프로세스들을 모두 중지 or 교착 상태가 해결될 때까지 프로세스를 하나씩 종료하여 회복
 
4. **Do Nothing**
    * Ignore the problem and let the user or system administrator respond to the problem; used by most
    operating systems, including Windows and UNIX
    * 모든 문제를 허용하고, 교착 상태를 허용하는 방법




### Reference
* https://jhnyang.tistory.com/3
* https://jhnyang.tistory.com/4?category=815411