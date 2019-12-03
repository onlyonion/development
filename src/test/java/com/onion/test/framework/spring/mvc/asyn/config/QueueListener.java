package com.onion.test.framework.spring.mvc.asyn.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.context.request.async.DeferredResult;

import com.onion.test.framework.spring.mvc.asyn.web.SimilarQueueHolder;
import lombok.extern.slf4j.Slf4j;

/**
 * 使用监听器来模拟消息队列处理
 */
@Configuration
@Slf4j
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private SimilarQueueHolder similarQueueHolder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        new Thread(() -> {
            while (true) {
                try {
                    //从队列中取出DeferredResult
                    DeferredResult<String> deferredResult = similarQueueHolder.getBlockingDeque().take();
                    log.info("开始DeferredResult异步处理");
                    //模拟处理时间
                    TimeUnit.SECONDS.sleep(3);
                    log.info("结束DeferredResult异步处理");
                    //模拟处理完成赋值
                    deferredResult.setResult("Hello World from DeferredResult");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
