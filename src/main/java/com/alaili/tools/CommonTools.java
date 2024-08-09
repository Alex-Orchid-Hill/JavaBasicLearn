package com.alaili.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @program: JavaBasicLearn
 * @ClassName: CommonTools
 * @description: 通用的工具类
 * @author: BaoYee
 * @create: 2024-07-26 14:26
 */
public class CommonTools {

    /**
     * @Author BaoYee
     * @Description 对数据进行分批，经典写法
     * @Date 2024/7/26
     * @param list
     * @param batchSize
     * @return List<List<T>>
     */
    public static   <T> List<List<T>> splitListIntoBatches(List<T> list, int batchSize) {
        List<List<T>> batches = new ArrayList<>();
        for (int i = 0; i < list.size(); i += batchSize) {
            int end = Math.min(list.size(), i + batchSize);
            batches.add(list.subList(i, end));
        }
        return batches;
    }

    /**
     * @Author BaoYee
     * @Description 对数据进行分批，java8 高级写法
     * @Date 2024/7/26
     * @param list
     * @param batchSize
     * @return List<List<T>>
     */
    public static   <T> List<List<T>> splitListIntoBatchesSenior(List<T> list, int batchSize) {
        return IntStream.range(0, (int) Math.ceil((double) list.size() / batchSize))
                .mapToObj(i -> list.stream()
                        .skip(i * batchSize)
                        .limit(batchSize)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }
}
