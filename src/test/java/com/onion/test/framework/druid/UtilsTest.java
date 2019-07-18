package com.onion.test.framework.druid;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.alibaba.druid.util.Utils;
import com.alibaba.fastjson.JSON;

public class UtilsTest {

    @Test
    public void test() throws IOException {
        String s = Utils.readFromResource("app.json");
        Page page = JSON.parseObject(s, Page.class);
        for (Data row : page.getRows()) {
            if (row.getScm_url() != null)
                System.out.println(row.getScm_url().replaceAll("git@git.weidai.work:", ""));
        }
        //System.out.println(s);
    }

    @lombok.Data
    private static class Page {
        private List<Data> rows;
    }

    @lombok.Data
    private static class Data {
        private String scm_url;
    }
    //git.exe clone --progress -v "http://lijicong@git.weidai.work/pay/site-account.git" "E:\workspace\http-git\pay\site-account"
}
