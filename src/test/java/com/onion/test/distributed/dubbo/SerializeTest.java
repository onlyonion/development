package com.onion.test.distributed.dubbo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.junit.Test;

import com.alibaba.com.caucho.hessian.io.Hessian2Input;
import com.alibaba.com.caucho.hessian.io.Hessian2Output;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.onion.test.common.model.User;

public class SerializeTest {

    @Test
    public void testUser() throws IOException {
        User user = new User();
        user.setId(1);
        user.setUserName("me");
        byte[] bytes = serialize(user);

        System.out.println(Arrays.toString(bytes));

        User user1 = (User) deserialize(bytes);
        System.out.println(JSON.toJSONString(user1));
    }

    @Test
    public void testMap() throws IOException {
        Map<String, Byte> map = Maps.newHashMap();
        map.put("weather", (byte) 1);
        byte[] bytes = serialize(map);
        Map<String, Byte> object = (Map<String, Byte>) deserialize(bytes);
        //这里会抛异常
        Byte weather = object.get("weather");
        System.out.println(weather);
    }

    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Hessian2Output ho = new Hessian2Output(os);
        byte[] cc = null;
        try {
            if (obj == null)
                throw new NullPointerException();
            ho.writeObject(obj);
            ho.flushBuffer();
            cc = os.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ho.close();
        }
        return cc;

    }

    public static Object deserialize(byte[] by) throws IOException {
        try {
            if (by == null)
                throw new NullPointerException();
            ByteArrayInputStream is = new ByteArrayInputStream(by);
            Hessian2Input hi = new Hessian2Input(is);
            return hi.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}
