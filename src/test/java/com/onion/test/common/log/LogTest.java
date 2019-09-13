package com.onion.test.common.log;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.junit.Test;
import org.slf4j.MDC;

public class LogTest {

    @Test
    public void test() throws UnknownHostException {
        String ip = MDC.get("ip");
        System.out.println(ip);

        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        System.out.println(hostAddress);
    }

}
