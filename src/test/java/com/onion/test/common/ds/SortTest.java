package com.onion.test.common.ds;

import java.util.Arrays;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SortTest {
    
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 插入 直接插入、拆半插入、希尔排序
     * 交换 冒泡、快速
     * 选择 简单选择排序、堆排序
     * 归并
     * 基数
     */
    // ********************** 插入 直接插入、拆半插入、希尔排序 **********************

    // ********************** 交换 冒泡、快速 **********************
    // 冒泡排序
    @Test
    public void bubbleSortTest() {
        int[] arr = { 4, 7, 6, 8, 5, 1 };
        bubbleSort(arr, arr.length);
        log.info("sort arr={}", Arrays.toString(arr));

    }

    static void bubbleSort(int[] arr, int length) {
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
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

    /**
     * 快速排序 递归
     */
    @Test
    public void quickSortTest() {
        int[] array = { 22, 34, 13, 2, 55, 4 };
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
            while (low < high && array[high] >= pivot)
                --high;
            array[low] = array[high];
            System.out.println(Arrays.toString(array));

            while (low < high && array[low] <= pivot)
                ++low;
            array[high] = array[low];
            System.out.println(Arrays.toString(array));

        }
        array[low] = pivot;
        System.out.println("partition" + Arrays.toString(array));
        return low;
    }

    // ********************** 选择 简单选择排序、堆排序 **********************
    @Test
    public void selectSortTest() {
        int[] arr = { 32, 12, 5, 3, 55 };
        selectSort(arr);
        log.info("sort arr={}", Arrays.toString(arr));
    }

    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length - 1; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
                if (min != i) {
                    swap(arr, i, min);
                }
                log.info("sort i={}, min={}, arr={}", i, min, Arrays.toString(arr));
            }
        }
    }

    @Test
    public void heapSortTest() {
        int[] arr = { 0, 32, 12, 5, 3, 55 };
        heapSort(arr, arr.length - 1);
        log.info("sort arr={}", Arrays.toString(arr));
    }

    // 建大根堆
    public static void buildMaxheap(int[] arr, int len) {
        for (int i = len / 2; i > 0; i--) {
            adjustDown(arr, i, len);
        }
    }

    // 将元素k向下调整
    private static void adjustDown(int[] arr, int k, int len) {
        arr[0] = arr[k];
        for (int i = 2 * k; i <= len; i *= 2) {
            if (i < len && arr[i] < arr[i + 1]) {
                i++;
            }
            if (arr[0] >= arr[i]) {
                break;
            } else {
                arr[k] = arr[i];
                k = i;
            }
        }
        arr[k] = arr[0];
    }

    public static void heapSort(int[] arr, int len) {
        buildMaxheap(arr, len);
        for (int i = len; i > 1; i--) {
            swap(arr, i, 1);
            adjustDown(arr, 1, i - 1);
        }
    }

    // ********************** 归并 **********************
    /*
     * 讲两个或两个以上的有序表合并成一个新的有序表
     *
     */
    @Test
    public void mergeSortTest() {
        int[] arr = { 32, 12, 5, 6, 55 };
        mergeSort(arr);
        log.info("sort arr={}", Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr) {
        int mid = arr.length / 2;
        int[] temp = new int[arr.length];

        int i = 0;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j < arr.length) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
            //temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j < arr.length) {
            temp[k++] = arr[j++];
        }

    }

    @Test
    public void mergeSort2Test() {
        int[] arr = { 32, 12, 5, 6, 55 };
        mergeSort2(arr, 0, arr.length / 2);
        log.info("sort arr={}", Arrays.toString(arr));
    }

    public static void merge(int[] arr, int low, int mid, int high) {
        int[] temp = new int[arr.length];
        for (int k = low; k < high; k++) {
            temp[k] = arr[k];
        }

        int i = low, j = mid + 1, k = i;

        for (; i < mid && j <= high; k++) {
            if (temp[i] < temp[j]) {
                arr[k] = temp[i++];
            } else {
                arr[k] = temp[j++];
            }
        }
        while (i <= mid) {
            arr[k++] = temp[i++];
        }
        while (j <= high) {
            arr[k++] = temp[j++];
        }
    }

    public static void mergeSort2(int[] arr, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort2(arr, low, mid);
            mergeSort2(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }
    }

    @Test
    public void joinTest() {
        int[] a = { 1, 3, 5 };
        int[] b = { 8, 9 };
        int[] join = join(a, b);
        log.info("join={}", Arrays.toString(join));
    }

    // 两个有序序列合并
    static int[] join(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length - 1 && j < b.length - 1) {
            if (a[i] <= b[j]) {
                c[k++] = a[i++];
            } else {
                c[k++] = b[j++];
            }
        }

        while (i < a.length) {
            c[k++] = a[i++];
        }
        while (j < b.length) {
            c[k++] = b[j++];
        }
        return c;
    }

}
