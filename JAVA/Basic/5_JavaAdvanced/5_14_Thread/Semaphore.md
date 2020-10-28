# Semaphore
* 사전적 의미는 횟대 (깃발)
* n 개의 깃발을 놓고, 여러 스레드가 경쟁하도록 하는 sync 기법
* n = 1이면, BinarySemaphore라고 하며, Lock과 유사하게 동작

## Semaphore Method
* permits을 입력하지 않으면, 기본적으로 1개이다.

| Method | Description |
|--------|-------------|
| acquire(int permits) | permits 만큼 Semaphore 요구, 해당 개수가 만족되지 않으면 대기하게 된다 |
| boolean tryAcquire(int permits) | acquire() 처럼 요구하지만, 해당 개수가 만족하지 않는 경우 대기하지 않고 false를 반환한다  |
| boolean tryAcquire(int timeout, TimeUnit unit) | 요청에 대해 해당 시간만큼 대기하며 시도한다 |
| acquireUninterruptibly() | interrupt() 메서드에 반응하지 않고 대기한다 |
| int availablePermits() | 사용 가능한 Semaphore 개수 반환 |
| void release(int permits) | 해당 permits 개수만큼 Semaphore 반환 |


### Example
```java
public class Main {
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(1);

        System.out.println(sem.availablePermits());
//
//        try { // Blocking으로 동작
//            sem.acquire(12);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        sem.acquireUninterruptibly(); // interrupt()에 반응하지 않음

        System.out.println(sem.tryAcquire()); // Blocking하지 않고, 실패하면 false 리턴
        try {
            System.out.println(sem.tryAcquire(2000, TimeUnit.MILLISECONDS)); // Blocking하지 않고, 실패하면 false 리턴
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println(sem.availablePermits());

        sem.release();

        System.out.println(sem.availablePermits());
    }
}
```

### Dining Philosopher
```java
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class Philosopher extends Thread {
    private final int id;
    private final Fork left;
    private final Fork right;

    public Philosopher(int id, Fork left, Fork right) {
        this.id = id;
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (true) {
            try {
                left.acquire();
//                System.out.println(id + ": left taken.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                if (!right.tryAcquire(1000, TimeUnit.MILLISECONDS)) {
                    left.release();
                    Thread.yield();
                    continue;
                }
//                System.out.println(id + ": right taken.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                System.out.println(id + " is eating.");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            left.release();
            right.release();
            Thread.yield();
        }
    }
}

class Fork extends Semaphore {

    public Fork() {
        super(1);
    }
}

public class Main {
    public static void main(String[] args) {
        Philosopher[] phils = new Philosopher[5];
        Fork[] forks = new Fork[5];

        for (int i = 0; i < 5; i++) {
            forks[i] = new Fork();
        }

        for (int i = 0; i < 5 - 1; i++) {
            phils[i] = new Philosopher(i, forks[i], forks[(i + 1) % 5]);
        }
        phils[4] = new Philosopher(4, forks[0], forks[4]);

        for (int i = 0; i < 5; i++) {
            phils[i].start();
        }
    }
}
```