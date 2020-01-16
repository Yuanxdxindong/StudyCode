package 并发编程;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

    private Lock lock = new ReentrantLock();

    public void func() {
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }

        } finally {
            lock.unlock(); //解锁的时候都写到finally 里面防止异常解锁失败产生死锁
        }
    }


    public static void main(String[] args) {
        ReentrantLockExample lockExample2 = new ReentrantLockExample();
        ReentrantLockExample lockExample = new ReentrantLockExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> lockExample.func());
        executorService.execute(() -> lockExample2.func());
    }
}
