package com.onion.test.distributed.zookeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

public class ZookeeperHelloWorld implements Watcher {

    private static CountDownLatch c = new CountDownLatch(1);

    public static void main(String[] args) throws IOException {
        ZooKeeper zooKeeper = new ZooKeeper("my.vm.zk:2181", 5000, new ZookeeperHelloWorld());

        System.out.println(zooKeeper.getState());
        try {
            c.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("session finished");
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("Receive watched event: " + event);
        if (KeeperState.SyncConnected == event.getState()) {
            c.countDown();
        }
    }
}
