package com.alaili.thread.synchronize.learn20240821;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: JavaBasicLearn
 * @ClassName: WaitNotifyExample
 * @description: 等待唤醒wait示例
 * 调用 wait() 使得线程等待某个条件满足，线程在等待时会被挂起，当其他线程的运行使得这个条件满足时，其它线程会调用 notify() 或者 notifyAll() 来唤醒挂起的线程。
 * 它们都属于 Object 的一部分，而不属于 Thread。只能用在同步方法或者同步控制块中使用，否则会在运行时抛出 IllegalMonitorStateException。
 * @author: BaoYee
 * @create: 2024-08-22 16:52
 */
public class WaitNotifyExample {

    public synchronized void before() {
        System.out.println("before");
        notifyAll();
    }

    public synchronized void after() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after");
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        WaitNotifyExample example = new WaitNotifyExample();
        executorService.execute(() -> example.after());
        executorService.execute(() -> example.before());
    }
}
