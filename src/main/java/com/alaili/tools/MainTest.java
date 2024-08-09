package com.alaili.tools;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * @program: JavaBasicLearn
 * @ClassName: MainTest
 * @description: 测试主类
 * @author: BaoYee
 * @create: 2024-07-26 14:29
 */
public class MainTest {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9);
        List<List<Integer>> splitedList = CommonTools.splitListIntoBatchesSenior(numbers, 4);
        for (List<Integer> integerList : splitedList) {
            System.out.println(integerList);
        }
        //使用google的guava包也可以实现列表分组的功能
        List<List<Integer>> newSplitedList = Lists.partition(numbers, 5);
        for (List<Integer> integerList : newSplitedList) {
            System.out.println(integerList);
        }
    }
}
