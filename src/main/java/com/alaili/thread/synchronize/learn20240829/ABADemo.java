package com.alaili.thread.synchronize.learn20240829;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @program: JavaBasicLearn
 * @ClassName: ABADemo
 * @description: java处理ABA问题的Demo
 * @author: BaoYee
 * @create: 2024-08-29 15:56
 */
public class ABADemo {

    public static void main(String[] args) {
        // 初始对象和标记
        String initialRef = "A";
        int initialStamp = 0;

        // 创建AtomicStampedReference对象
        AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference<>(initialRef, initialStamp);

        // 获取当前引用和标记
        String ref = atomicStampedReference.getReference();
        int stamp = atomicStampedReference.getStamp();
        System.out.println("Initial Reference: " + ref + ", Initial Stamp: " + stamp);

        // 模拟ABA问题
        if (atomicStampedReference.compareAndSet(ref, "B", stamp, stamp + 1)) {
            System.out.println("Updated Reference from A to B");
        }

        if (atomicStampedReference.compareAndSet("B", "A", stamp + 1, stamp + 2)) {
            System.out.println("Updated Reference from B back to A");
        }

        // 尝试使用旧的引用和标记更新
        boolean success = atomicStampedReference.compareAndSet(ref, "C", stamp, stamp + 1);
        System.out.println("Was the update from A to C successful? " + success);
        System.out.println("Current Reference: " + atomicStampedReference.getReference());
        System.out.println("Current Stamp: " + atomicStampedReference.getStamp());
    }
}
