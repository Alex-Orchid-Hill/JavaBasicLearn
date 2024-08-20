package com.alaili.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: JavaBasicLearn
 * @ClassName: CASDemo
 * @description: CAS原理说明与Demo示例
 * CAS（Compare-And-Swap）操作是一种用于实现线程安全操作的原子指令
 * 当且仅当内存位置的值与预期原值相匹配时，CAS操作才会将该内存位置的值更新为新值。如果内存位置的值与预期原值不匹配，则CAS操作不会执行任何更新，并返回当前内存位置的值。
 * 在Java中，CAS操作主要通过java.util.concurrent.atomic包下的类来实现，这些类提供了基于CAS的线程安全变量。例如，AtomicInteger、AtomicLong、AtomicReference等。
 * @author: BaoYee
 * @create: 2024-08-19 10:23
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);

        int expectedValue = 0;
        int newValue = 1;
        boolean isUpdated = atomicInteger.compareAndSet(expectedValue, newValue);

        if (isUpdated) {
            System.out.println("更新成功，当前值：" + atomicInteger.get());
        } else {
            System.out.println("更新失败，当前值：" + atomicInteger.get());
        }

        // 尝试将值从1更新为2（假设此时值仍为1）
        expectedValue = 1;
        newValue = 2;
        isUpdated = atomicInteger.compareAndSet(expectedValue, newValue);

        if (isUpdated) {
            System.out.println("更新成功，当前值：" + atomicInteger.get());
        } else {
            System.out.println("更新失败，当前值：" + atomicInteger.get());
        }
    }
}
