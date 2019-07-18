package com.onion.test.common.ds;

import java.util.Arrays;

import org.junit.Test;

public class SimpleSortTest {

    static void bubble(int[] arr, int n) {
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                SortTest.swap(arr, i, i + 1);
            }
        }
    }

    static void bubbleSort(int[] arr, int n) {
        for (int i = n; i >= 1; i--) {
            bubble(arr, n);
        }
    }

    @Test
    public void bubbleSortTest() {
        int[] arr = { 9, 8, 2, 4, 3, 1 };
        bubbleSort(arr, 6);
        System.out.println(Arrays.toString(arr));
    }

    static int findMaxPos(int[] arr, int n) {
        int max = arr[0];
        int pos = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
                pos = i;
            }
        }
        return pos;
    }

    static void selectionSort(int[] arr, int n) {
        while (n > 1) {
            int pos = findMaxPos(arr, n);
            SortTest.swap(arr, pos, n - 1);
            n--;
        }
    }

    @Test
    public void selectionSortTest() {
        int[] arr = { 9, 8, 2, 4, 3, 1 };
        selectionSort(arr, 6);
        System.out.println(Arrays.toString(arr));
    }

    static void insert(int[] arr, int j) {
        int key = arr[j];
        int i = j;
        while (arr[i - 1] > key) {
            arr[i] = arr[i - 1];
            i--;
            if (i == 0) {
                break;
            }
        }
        arr[i] = key;
    }

    static void insertSort(int[] arr, int n) {
        for (int i = 1; i < n; i++) {
            insert(arr, i);
        }
    }

    @Test
    public void insertSortTest() {
        int[] arr = { 9, 8, 2, 4, 3, 1 };
        insertSort(arr, 6);
        System.out.println(Arrays.toString(arr));
    }
}
