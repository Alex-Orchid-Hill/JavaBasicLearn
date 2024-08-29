package com.alaili.thread.synchronize.learn20240829;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: JavaBasicLearn
 * @ClassName: CASTest
 * @description: CAS演示Demo
 * @author: BaoYee
 * @create: 2024-08-29 15:49
 */
public class CASTest {
    public static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {
        //线程T1慢一些去执行
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("当前线程" + Thread.currentThread().getName() + ",比较交换结果："
                    + atomicInteger.compareAndSet(1, 10) + ",现在值为：" + atomicInteger.get());
        }, "T1").start();

        new Thread(() -> {
            System.out.println("当前线程" + Thread.currentThread().getName() + ",比较交换结果："
                    + atomicInteger.compareAndSet(1, 20) + ",现在值为：" + atomicInteger.get());
        }, "T2").start();
    }
}
