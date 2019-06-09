package com.onion.test.common.ds.sort;

import java.util.Arrays;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SortTest {

    // 插入 直接插入、拆半插入、希尔排序

    // 交换 冒泡、快速
    @Test
    public void bubbleSort() {
        int[] arr = { 4, 7, 6, 8, 5, 1 };

        log.info("sort arr={}", Arrays.toString(arr));

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
                log.info("sort i={}, j={}, arr={}", i, j, Arrays.toString(arr));
            }
            log.info("sort i={}, arr={}", i, Arrays.toString(arr));
        }

        log.info("sort arr={}", Arrays.toString(arr));
    }

    // 选择 简单选择排序、堆排序

    // 归并

    // 基数
}
