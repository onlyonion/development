package com.onion.test.common.apache;

import org.apache.commons.lang3.BooleanUtils;
import org.junit.Test;

public class BooleanUtilTest {

    @Test
    public void test() {
        boolean b = BooleanUtils.toBoolean(null, 1, null);
        System.out.println(b);
    }

}
