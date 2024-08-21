package com.alaili.thread.synchronize.learn20240820;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: JavaBasicLearn
 * @ClassName: SynchronizedExample
 * @description: synchronized同步示例
 * @author: BaoYee
 * @create: 2024-08-20 15:26
 */
public class SynchronizedExample {

    //同步作用于同一个对象，会进行同步，不同对象则不会
    public void func1() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        }
    }

    //同步作用于同一个类的不同不同对象，会进行同步
    public void func2() {
        synchronized (SynchronizedExample.class) {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        }
    }

    //同步静态方法
    public synchronized  static void func3() {
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
        }
    }

    public static void main(String[] args) {

//        SynchronizedExample example = new SynchronizedExample();
//        ExecutorService executorService= Executors.newCachedThreadPool();
//        executorService.execute(example::func1);
//        executorService.execute(example::func1);


        SynchronizedExample example1 = new SynchronizedExample();
        SynchronizedExample example2 = new SynchronizedExample();
        ExecutorService executorServiceNew= Executors.newCachedThreadPool();
        executorServiceNew.execute(example1::func2);
        executorServiceNew.execute(example2::func2);
    }
}
