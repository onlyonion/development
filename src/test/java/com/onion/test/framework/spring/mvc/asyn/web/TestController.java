package com.onion.test.framework.spring.mvc.asyn.web;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping("/async")
public class TestController {

    @RequestMapping("/{testUrl}")
    public DeferredResult<ResponseEntity<String>> testProcess(@PathVariable String testUrl) {
        final DeferredResult<ResponseEntity<String>> deferredResult = new DeferredResult<>();
        // 业务逻辑异步处理,将处理结果 set 到 DeferredResult
        new Thread(new AsyncTask(deferredResult)).start();
        return deferredResult;
    }

    @AllArgsConstructor
    private static class AsyncTask implements Runnable {
        private DeferredResult result;

        @Override
        public void run() {
            //业务逻辑START
            //...
            //业务逻辑END
            result.setResult(result);
        }
    }
}

