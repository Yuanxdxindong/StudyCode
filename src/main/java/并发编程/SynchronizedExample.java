package 并发编程;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedExample {
    public void func1() {
        lockObject();
//        lockCodeBlock();
    }

    /**
     * 使用Sychronized锁代码块
     */
    private void lockCodeBlock() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        }
    }

    /**
     * 锁对象类
     */
    private void lockObject() {
        synchronized (SynchronizedExample.class) {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        }
    }

    public static void main(String[] args) {
        notSameSychronizedObject();

        CachedPool();
    }

    /**
     * 调用两个同步代码块
     * 两个交替执行
     */
    private static void notSameSychronizedObject() {
        SynchronizedExample e1 = new SynchronizedExample();
        SynchronizedExample e2 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> e1.func1());
        executorService.execute(() -> e2.func1());
    }

    /**
     * 两个线程调用同一个代码块
     * 一个执行完另一个才能执行
     */
    private static void CachedPool() {
        SynchronizedExample e1 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> e1.func1());
        executorService.execute(() -> e1.func1());
    }

    /**
     * 锁加多个线程执行
     */



}