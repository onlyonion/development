package com.onion.test.distributed.rocketmq.example;

import org.apache.rocketmq.client.ClientConfig;

public class MQUtil {

    public static void setNamesrvAddr(ClientConfig clientConfig) {
        clientConfig.setNamesrvAddr("localhost:9876");
    }
}
