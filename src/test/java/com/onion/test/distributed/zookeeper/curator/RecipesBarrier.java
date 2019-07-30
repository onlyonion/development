package com.onion.test.distributed.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class RecipesBarrier {

    static String path = "/curator_recipes_distatomicint_path";

    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("my.vm.zk:2181")
            .retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

    public static void main(String[] args) throws Exception {
        client.start();

        client.create().forPath("/curator", "recipes".getBytes());

    }
}


