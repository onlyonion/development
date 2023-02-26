package com.onion.test.java.features;

import com.onion.test.common.model.User;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Java10Test {

    @Test
    public void testVar() {
        var u = new User();
        var s = "1";

        Map<String, List<String>> map = new HashMap<>();
        var maps = new HashMap<>();
    }

    // Graal
}
