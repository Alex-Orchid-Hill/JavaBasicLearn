package com.alaili.thread.synchronize.learn20240820;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: JavaBasicLearn
 * @ClassName: LockExample
 * @description: Lock的使用示例
 * @author: BaoYee
 * @create: 2024-08-20 15:49
 */
public class LockExample {
    private Lock lock=new ReentrantLock();
    public void func() {
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        LockExample example=new LockExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(example::func);
        executorService.execute(example::func);
    }
}
