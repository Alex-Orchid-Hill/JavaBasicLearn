package com.alaili.thread.synchronize.learn20240822;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: JavaBasicLearn
 * @ClassName: MyReentrantLockThread
 * @description: 可重入锁简单Demo
 * @author: BaoYee
 * @create: 2024-08-27 13:46
 */
public class MyReentrantLockThread extends Thread {
    private Lock lock;

    public MyReentrantLockThread(String name, Lock lock) {
        super(name);
        this.lock = lock;
    }

    public void run() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread() + " running");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Lock lock = new ReentrantLock(true);
        MyReentrantLockThread t1 = new MyReentrantLockThread("t1", lock);
        MyReentrantLockThread t2 = new MyReentrantLockThread("t2", lock);
        MyReentrantLockThread t3 = new MyReentrantLockThread("t3", lock);
        t1.start();
        t2.start();
        t3.start();
    }
}
