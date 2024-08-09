package com.alaili.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: JavaBasicLearn
 * @ClassName: FutureTaskDemoNew
 * @description: 异步任务线程-新
 * @author: BaoYee
 * @create: 2024-07-31 10:23
 */
public class FutureTaskDemoNew {
    private static AtomicInteger count = new AtomicInteger(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 准备10个线程
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // 收集任务
        List<Callable<Integer>> taskList = new ArrayList<>();
        // 收集结果
        List<Future<Integer>> resultList = new ArrayList<>();

        long start = System.currentTimeMillis();

        // 先准备10个任务
        for (int i = 0; i < 10; i++) {
            Callable<Integer> task = () -> {
                // 模拟任务耗时 0~4秒
                int seconds = ThreadLocalRandom.current().nextInt(5);
                TimeUnit.SECONDS.sleep(seconds);
                System.out.println("task is completed! cost:" + seconds + "s left: " + count.decrementAndGet());
                // 模拟返回结果
                return 1;
            };
            // 收集任务
            taskList.add(task);
        }

        // 10个任务并行执行
        for (int i = 0; i < 10; i++) {
            // 从任务列表取出任务，丢到线程池执行
            Future<Integer> partResult = executorService.submit(taskList.get(i));
            // 收集异步结果
            resultList.add(partResult);
        }

        // 最终结果，用于累加
        int result = 0;

        // 是否全部结束，否则一直循环等待
        while (notFinished(resultList)) {
            // wait for all task to be completed...
        }

        // 主线程执行到这，肯定全部任务已经结束，所以get()会立即返回结果，不会再阻塞等待
        for (Future<Integer> future : resultList) {
            result += future.get();
        }

        // 最终全部任务完成，总耗时取决于最耗时的任务时长
        System.out.println("all task is completed! result=" + result + " cost: " + (System.currentTimeMillis() - start) + "ms");
    }

    /**
     * 是否全部完成
     *
     * @param list
     * @return
     */
    private static boolean notFinished(List<Future<Integer>> list) {
        for (Future<Integer> future : list) {
            if (!future.isDone()) {
                return true;
            }
        }
        return false;
    }
}
