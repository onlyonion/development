package com.onion.test.framework.spring.mvc.asyn.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

/**
 * CompletionStage /CompletableFuture Controller层
 */
@RestController
@Slf4j
public class CompletableAsynController {

    @GetMapping("/completion-stage")
    public CompletionStage<String> completionStage() {

        log.info("主线程 helloWorld");

        return CompletableFuture.supplyAsync(() -> {
            //模拟处理时间
            log.info("异步线程 开始 CompletableFuture");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("异步线程 结束 CompletableFuture");
            return "Hello World from OtherAsynController"; // 异步执行结果
        });
    }

}
