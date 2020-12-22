package com.onion.test.java.util.concurrent.forkjoin;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

public class CalculatorTest {

    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            testLongStream();
            testForLoopCalculator();
            testExecutorServiceCalculator();
            testForkJoinCalculator();
        }
    }


    @Test
    public void testLongStream() {
        Instant start = Instant.now();
        long result = LongStream.rangeClosed(0, 10000000L).parallel().reduce(0, Long::sum);
        Instant end = Instant.now();
        System.out.println("testLongStream 耗时：" + Duration.between(start, end).toMillis() + "ms 结果为：" + result);
    }


    @Test
    public void testForLoopCalculator() {
        long[] numbers = LongStream.rangeClosed(1, 10000000).toArray();

        Instant start = Instant.now();
        Calculator calculator = new ForLoopCalculator();
        long result = calculator.sumUp(numbers);
        Instant end = Instant.now();
        System.out.println("testForLoopCalculator 耗时：" + Duration.between(start, end).toMillis() + "ms 结果为：" + result);
    }

    @Test
    public void testExecutorServiceCalculator() {
        long[] numbers = LongStream.rangeClosed(1, 10000000).toArray();

        Instant start = Instant.now();
        Calculator calculator = new ExecutorServiceCalculator();
        long result = calculator.sumUp(numbers);
        Instant end = Instant.now();
        System.out.println("testExecutorServiceCalculator 耗时：" + Duration.between(start, end).toMillis() + "ms 结果为：" + result);
    }

    @Test
    public void testForkJoinCalculator() {
        long[] numbers = LongStream.rangeClosed(1, 10000000).toArray();

        Instant start = Instant.now();
        Calculator calculator = new ForkJoinCalculator();
        long result = calculator.sumUp(numbers);
        Instant end = Instant.now();
        System.out.println("testForkJoinCalculator 耗时：" + Duration.between(start, end).toMillis() + "ms 结果为：" + result);
    }
}
