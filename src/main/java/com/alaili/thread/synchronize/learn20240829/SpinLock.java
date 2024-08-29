package com.alaili.thread.synchronize.learn20240829;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @program: JavaBasicLearn
 * @ClassName: SpinLock
 * @description: java中自旋锁的实现
 * 在Java中，实现自旋锁通常需要使用AtomicReference或AtomicInteger等原子类来确保锁的状态被安全地访问和修改
 * @author: BaoYee
 * @create: 2024-08-29 16:31
 */
public class SpinLock {

    // 使用AtomicReference来存储锁的状态，这里简单地用true表示锁被占用，false表示锁未被占用
    private final AtomicReference<Boolean> isLocked = new AtomicReference<>(false);

    // 尝试获取锁
    public void lock() {
        int spinCount = 0;
        // 自旋，直到成功获取锁
        while (!isLocked.compareAndSet(false, true)) {
            spinCount++;
            System.out.println(Thread.currentThread().getName() + " 已经自旋了" + spinCount+"次！");
            // 这里可以进行一些简单的优化，比如引入暂停（Thread.yield()）或空转一定次数后暂停
        }
    }

    // 释放锁
    public void unlock() {
        // 释放锁，将锁的状态设置为false
        isLocked.set(false);
    }

    // 测试代码
    public static void main(String[] args) {
        final SpinLock lock = new SpinLock();

        // 线程1尝试获取锁
        new Thread(() -> {
            lock.lock();
            try {
                // 模拟业务逻辑处理
                Thread.sleep(1000);
                System.out.println("Thread 1 is running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        // 线程2尝试获取锁
        new Thread(() -> {
            lock.lock();
            try {
                // 模拟业务逻辑处理
                System.out.println("Thread 2 is running");
            } finally {
                lock.unlock();
            }
        }).start();
    }
}
