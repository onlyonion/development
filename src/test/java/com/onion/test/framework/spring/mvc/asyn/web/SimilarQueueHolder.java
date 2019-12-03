package com.onion.test.framework.spring.mvc.asyn.web;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * 模拟消息队列
 */
@Component
public class SimilarQueueHolder {

    //创建容量为10的阻塞队列
    private BlockingQueue<DeferredResult<String>> blockingDeque = new ArrayBlockingQueue<DeferredResult<String>>(10);

    public BlockingQueue<DeferredResult<String>> getBlockingDeque() {
        return blockingDeque;
    }

    public void setBlockingDeque(BlockingQueue<DeferredResult<String>> blockingDeque) {
        this.blockingDeque = blockingDeque;
    }
}
