package com.onion.test.common.ds;

import java.util.Arrays;

import org.junit.Test;

public class QuickSortTest {

    @Test
    public void test() {
        int[] array = {22, 34, 13, 2, 55, 4};
        System.out.println(Arrays.toString(array));

        quickSort(array, 0, array.length - 1);

        System.out.println(Arrays.toString(array));
    }

    public static void quickSort(int[] array, int low, int high) {
        if (low < high) { // 长度大于1
            int pivotLoc = partition(array, low, high); // 一分为二，pivotLoc为轴元素排好序的位置
            quickSort(array, low, pivotLoc - 1); // 对低子表递归排序
            System.out.println("低子表递归排序 " + Arrays.toString(array));

            quickSort(array, pivotLoc + 1, high); // 对高子表递归排序
            System.out.println("高子表递归排序 " + Arrays.toString(array));
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[low];
        System.out.println("pivot: " + pivot + " low: " + low + " high: " + high);
        while (low < high) {
            while (low < high && array[high] >= pivot) --high;
            array[low] = array[high];
            System.out.println(Arrays.toString(array));

            while (low < high && array[low] <= pivot) ++low;
            array[high] = array[low];
            System.out.println(Arrays.toString(array));

        }
        array[low] = pivot;
        System.out.println("partition" + Arrays.toString(array));
        return low;
    }

}
