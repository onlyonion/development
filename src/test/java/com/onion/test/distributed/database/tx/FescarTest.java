package com.onion.test.distributed.database.tx;

import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FescarTest {

    private StorageService storageService = new StorageService();
    private OrderService orderService = new OrderService();

    @GlobalTransactional(timeoutMills = 300000, name = "dubbo-demo-tx")
    public void purchase(String userId, String commodityCode, int orderCount) {
        log.info("purchase begin ... xid: " + RootContext.getXID());
        storageService.deduct(commodityCode, orderCount);
        orderService.create(userId, commodityCode, orderCount);
        throw new RuntimeException("xxx");

    }

    class StorageService {
        public void deduct(String commodityCode, int orderCount) {
        }
    }

    class OrderService {
        public void create(String userId, String commodityCode, int orderCount) {
        }
    }

}
