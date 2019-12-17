package com.onion.test.framework.spring.core;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.springframework.util.IdGenerator;
import org.springframework.util.SimpleIdGenerator;

/**
 * @author lijicong
 * @since 2019-12-12
 */
public class UtilTest {

    @Test
    public void testId() {
        IdGenerator idGenerator = new SimpleIdGenerator();
        for (int i = 0; i < 100; i++) {
            System.out.println(idGenerator.generateId());
        }
    }

    @Test
    public void test() {
        System.out.println(DigestUtils.getMd5Digest().digest("123456".getBytes()));
    }

}
