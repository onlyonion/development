package com.onion.test.distributed.zookeeper.curator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class RecipesLock {

    static String lockPath = "/curator_recipes_lock_path";

    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("my.vm.zk:2181")
            .retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

    public static void main(String[] args) {
        client.start();

        InterProcessMutex lock = new InterProcessMutex(client, lockPath);
        CountDownLatch down = new CountDownLatch(1);
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {

                try {
                    down.await();
                    lock.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
                String orderNo = sdf.format(new Date());
                System.out.println(orderNo);

                try {
                    lock.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

            down.countDown();
        }

    }

}
