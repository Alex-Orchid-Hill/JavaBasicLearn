package com.alaili.thread;

import cn.hutool.core.date.StopWatch;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.*;

/**
 * @program: JavaBasicLearn
 * @ClassName: ThreadPoolExecutorDemo
 * @description: 线程池异步处理示例程序
 * @author: BaoYee
 * @create: 2024-08-09 11:36
 */
public class ThreadPoolExecutorDemo {

    private static final Integer maxPoolSize = 100;

    public static ThreadPoolExecutor  createThreadPoolExecutor() {
        int kernelCount = Runtime.getRuntime().availableProcessors();
        kernelCount = kernelCount > 10  ? kernelCount : kernelCount * 2;
        // 创建一个阻塞队列用于存放待执行的任务
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(100);
        // 创建ThreadPoolExecutor
        // 参数依次为：核心线程数、最大线程数、空闲线程存活时间、时间单位、工作队列、线程工厂、拒绝策略
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                kernelCount,          // 核心线程数
                maxPoolSize,         // 最大线程数
                60L,        // 空闲线程存活时间
                TimeUnit.SECONDS,
                workQueue,  // 存放待执行任务的队列
                Executors.defaultThreadFactory(), // 使用默认的线程工厂
                new ThreadPoolExecutor.AbortPolicy() // 拒绝策略，当队列和线程池都满时，新任务将被拒绝并抛出RejectedExecutionException
        );
        return executor;
    }

    public static void main(String[] args) {
        StopWatch watch=new StopWatch();
        watch.start();
        ThreadPoolExecutor threadPoolExecutor = createThreadPoolExecutor();
        List<String> taskList = Lists.newArrayList("任务1", "任务2", "任务3", "任务4", "任务5", "任务6", "任务7", "任务8");
        CountDownLatch countDownLatch = new CountDownLatch(taskList.size());
        taskList.forEach(oneTask->{
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(100);
                    System.out.println("线程组【" + Thread.currentThread().getThreadGroup() + "】>>>>" + "线程【" + Thread.currentThread().getName() + "】:当前正在执行的任务是：" + oneTask);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }finally {
                    countDownLatch.countDown();
                }
            });
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        watch.stop();
        System.out.println("************All tasks is finished!**************");
        System.out.println(">>>>>>>>所有任务执行完成，耗时为：" + watch.prettyPrint(TimeUnit.MILLISECONDS));
    }
}
