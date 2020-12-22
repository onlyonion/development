package com.onion.test.java.util.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static com.onion.test.java.util.concurrent.ThreadHelper.print;

@Slf4j
public class CompletableFutureTest {

    //无返回值
    public static void runAsync() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println("run end ...");
        });

        future.get();

        future.thenAccept(empty -> {

        });
    }

    //有返回值
    public static void supplyAsync() throws Exception {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println("run end ...");
            return System.currentTimeMillis();
        });

        long time = future.get();
        System.out.println("time = " + time);
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 120;
        });
        CompletableFuture<Integer> future = future1.thenCombine(future2, (integer, integer2) -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return integer + integer2;
        });
        System.out.println(future.get());
    }

    @Test
    public void whenComplete() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 100);
        future.whenComplete((l, r) -> System.out.println(l));
    }

    @Test
    public void thenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 100);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 100);

        future.thenApply(i -> -i);
        CompletableFuture<String> f = future.thenApplyAsync(integer -> integer * 100).thenApply(s -> s.toString());
        System.out.println(f.get());

        CompletableFuture<Integer> fc = future.thenCompose(integer -> CompletableFuture.supplyAsync(() -> integer * 100));
        System.out.println(fc.get());

        CompletableFuture<Integer> f3 = future.thenCombine(future2, (integer, integer2) -> integer * integer2);
        Integer join = f3.join();
        Integer integer = f3.get();
    }

    @Test
    public void thenAccept() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 100);
        future.thenAccept(System.out::println);
    }

    @Test
    public void thenRun() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 100);
        future.thenRun(() -> System.out.println("Done"));
    }

    @Test
    public void thenAcceptBoth() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 100);
        CompletableFuture<Integer> other = CompletableFuture.supplyAsync(() -> 200);
        future.thenAcceptBoth(other, (x, y) -> System.out.println(x + y));
    }

    @Test
    public void acceptEither() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 100);
        CompletableFuture<Integer> other = CompletableFuture.supplyAsync(() -> 200);
        future.acceptEither(other, System.out::println);

    }

    @Test
    public void allOf() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> ThreadHelper.doBiz(100));
        CompletableFuture<Integer> second = CompletableFuture.supplyAsync(() -> ThreadHelper.doBiz(200));
        CompletableFuture<Integer> third = CompletableFuture.supplyAsync(() -> ThreadHelper.doBiz(300));
        CompletableFuture<Void> f = CompletableFuture.allOf(future, second, third);
        System.out.println(f.get());
    }

    @Test
    public void anyOf() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> ThreadHelper.doBiz(100));
        CompletableFuture<Integer> second = CompletableFuture.supplyAsync(() -> ThreadHelper.doBiz(200));
        CompletableFuture<Integer> third = CompletableFuture.supplyAsync(() -> ThreadHelper.doBiz(300));
        CompletableFuture<Object> f = CompletableFuture.anyOf(future, second, third);
        System.out.println(f.get());
    }


    @Test
    public void combine() {
//        CompletableFuture<Integer> first = CompletableFuture.supplyAsync(() -> ThreadHelper.doBiz(1));
//        CompletableFuture<Object> objectCompletableFuture = first.thenCombine(CompletableFuture.runAsync(() -> ThreadHelper.doBiz(1)),
//                (a, b) -> print(integer)
//        );
//        System.out.println(objectCompletableFuture.join());
    }

    @Test
    public void exceptionally() {
        CompletableFuture<Integer> f0 = CompletableFuture.supplyAsync(() -> {
            ThreadHelper.print(1);
            return 1;
        }).thenApplyAsync(r -> {
            ThreadHelper.print(1);
            return r * 10;
        }).thenApplyAsync(r -> {
            ThreadHelper.print(1);
            return r * 10;
        }).exceptionally(e -> {
            ThreadHelper.print(1);
            return 0;
        });
        System.out.println(f0.join());
    }

    @Test
    public void comboText() throws ExecutionException, InterruptedException {
        CompletableFuture<String> comboText = CompletableFuture.supplyAsync(() -> {
            //可以注释掉做快速返回 start
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            log.info("👍");
            //可以注释掉做快速返回 end
            return "赞";
        }).thenApply(first -> {
            log.info("在看");
            return first + ", 在看";
        }).thenApply(second -> second + ", 转发");

        log.info("三连有没有？");
        log.info(comboText.get());
    }

    @Test
    public void thenCombineCompletableFuture() throws ExecutionException, InterruptedException {
        CompletableFuture<Double> weightFuture = CompletableFuture.supplyAsync(() -> 65.0);
        CompletableFuture<Double> heightFuture = CompletableFuture.supplyAsync(() -> 183.8);

        CompletableFuture<Double> combinedFuture = weightFuture
                .thenCombine(heightFuture, (weight, height) -> {
                    Double heightInMeter = height / 100;
                    return weight / (heightInMeter * heightInMeter);
                });

        log.info("身体BMI指标 - " + combinedFuture.get());
    }

    @Test
    public void handleCompletableFuture() throws ExecutionException, InterruptedException {
        Integer age = -18;

        CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
            if (age < 0) {
                throw new IllegalArgumentException("何方神圣？");
            }
            if (age > 18) {
                return "大家都是成年人";
            } else {
                return "未成年禁止入内";
            }
        }).thenApply((str) -> {
            log.info("游戏开始");
            return str;
        }).exceptionally(ex -> {
            if (ex != null) {
                log.info("whenComplete 必有蹊跷，来者" + ex.getMessage());
                throw new IllegalArgumentException("何方神圣111？");
            }
            return "nuu";
        })/*.handle((res, ex) -> {
            System.out.println(res);
            if(ex != null) {
                log.info("handle 必有蹊跷，来者" + ex.getMessage());
                return "Unknown!";
            }
            return res;
        })*/.whenComplete((s, ex) -> {
            System.out.println("whenComplete " + s);
            if (ex != null) {
                log.info("whenComplete 必有蹊跷，来者" + ex.getMessage());
            }
        });

        log.info("-------------- result={}", maturityFuture.get());
    }


}
