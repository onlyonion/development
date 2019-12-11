package com.onion.test.java.util.concurrent;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author lijicong
 * @since 2019-12-09
 */
public class ForkJoinTest {

    private static double[] d;

    static class ForkJoinTask extends RecursiveTask<Integer> {
        private int first;
        private int last;

        public ForkJoinTask(int first, int last) {
            this.first = first;
            this.last = last;
        }

        @Override
        protected Integer compute() {
            int subCount;
            if (last - first < 10) {
                subCount = 0;
                for (int i = first; i <= last; i++) {
                    if (d[i] < 0.5) {
                        subCount++;
                    }
                }
            } else {
                int mid = (first + last) >>> 1;
                ForkJoinTask left = new ForkJoinTask(first, mid);
                left.join();
                ForkJoinTask right = new ForkJoinTask(mid + 1, last);
                right.join();
                subCount = left.join();
                subCount += right.join();
            }
            return subCount;
        }
    }

    public static void main(String[] args) {
        double[] d = new double[1000];
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            d[i] = random.nextDouble();
        }
        Integer count = new ForkJoinPool().invoke(new ForkJoinTask(0, 999));
        System.out.println(count);
    }
}
