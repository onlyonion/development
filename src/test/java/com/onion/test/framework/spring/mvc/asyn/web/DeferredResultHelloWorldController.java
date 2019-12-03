package com.onion.test.framework.spring.mvc.asyn.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DeferredResultHelloWorldController {

    @Autowired
    private SimilarQueueHolder similarQueueHolder;

    @GetMapping("/deferred/result")
    public DeferredResult<String> deferredResultHelloWolrd() {
        log.info("主线程 deferredResultHelloWolrd");
        //声明异步DeferredResult
        DeferredResult<String> deferredResult = new DeferredResult<>();
        //模拟放入消息队列
        similarQueueHolder.getBlockingDeque().offer(deferredResult);
        log.info("主线程 deferredResultHelloWolrd");
        return deferredResult;
    }

}
