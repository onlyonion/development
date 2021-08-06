package com.onion.test.java.lang.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author lijicong
 * @since 2021-08-04
 */
@Slf4j
public class DoubleTest {

    @Test
    public void test() {
        double d = 301353.05;
        BigDecimal d1 = new BigDecimal(d);
        BigDecimal d2 = BigDecimal.valueOf(d);

        log.info("d={}", d);
        log.info("d1={}", d1);
        log.info("d2={}", d2);
    }
}
