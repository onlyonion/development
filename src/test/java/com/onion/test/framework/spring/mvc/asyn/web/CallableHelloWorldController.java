package com.onion.test.framework.spring.mvc.asyn.web;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * Callback Controller层
 */
@RestController
@Slf4j
public class CallableHelloWorldController {

    @GetMapping("/callable/hello")
    public Callable<String> helloWorld() {
        log.info("主线程 helloWorld");
        return () -> {
            //模拟处理时间
            log.info("异步线程 开始 helloWorld");
            TimeUnit.SECONDS.sleep(3);
            log.info("异步线程 结束 helloWorld");
            return "Hello World from Callable";
        };

    }

}
