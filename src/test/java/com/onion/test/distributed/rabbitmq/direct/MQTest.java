package com.onion.test.distributed.rabbitmq.direct;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MQTest {
    @Autowired
    private Sender sender;
    @Test
    public void driectTest() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        sender.driectSend("Driect Data：" + sf.format(new Date()));
    }
}
