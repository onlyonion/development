package com.onion.test.distributed.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;

public class RecipesDistAtomicInt {

    static String path = "/curator_recipes_distatomicint_path";

    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("my.vm.zk:2181")
            .retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

    public static void main(String[] args) {
        client.start();

        DistributedAtomicInteger atomicInteger = new DistributedAtomicInteger(client, path, new RetryNTimes(3, 1000));

        try {
            AtomicValue<Integer> rc = atomicInteger.add(8);
            System.out.println(rc.succeeded());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


