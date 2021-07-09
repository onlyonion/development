package com.onion.test.common.http;

import org.apache.http.conn.util.InetAddressUtils;
import org.junit.Test;

public class IpTest {

    @Test
    public void test() {
        System.out.println(InetAddressUtils.isIPv4Address("192.168.1.2361"));
        System.out.println(InetAddressUtils.isIPv4Address("192.168.1.236"));
    }
}
