package com.onion.test.common.guava;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.Test;
import com.google.common.collect.Lists;

public class CollectionTest {

    @Test
    public void listTest() throws ExecutionException, InterruptedException {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<List<Integer>> partition = Lists.partition(list, 2);
        System.out.println(partition);


        List<CompletableFuture<String>> futures = Lists.newArrayList();

        for (List<Integer> id : partition) {
            futures.add(CompletableFuture.supplyAsync(() -> getData(id)));
        }

        CompletableFuture<String>[] completableFutures = new CompletableFuture[0];
        CompletableFuture.allOf(futures.toArray(completableFutures)).get();

    }

    private String getData(List<Integer> id) {
        return "store-" + id;
    }


}
