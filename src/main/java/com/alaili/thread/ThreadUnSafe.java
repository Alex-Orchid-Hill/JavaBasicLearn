package com.alaili.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: JavaBasicLearn
 * @ClassName: ThreadUnSafe
 * @description: 线程不安全示例类,并发访问共享数据，没有采取同步措施，导致的线程输出问题
 * @author: BaoYee
 * @create: 2024-08-14 10:01
 */
public class ThreadUnSafe {
    private int cnt = 0;

    public void add() {
        cnt++;
    }

    public int getCount() {
        return cnt;
    }

    public static void main(String[] args) throws InterruptedException {
        final int countSize = 1000;
        ThreadUnSafe threadUnSafe = new ThreadUnSafe();
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(countSize);
        for (int i = 0; i < countSize; i++) {
            executorService.execute(() -> {
                threadUnSafe.add();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(threadUnSafe.getCount());
    }
}
