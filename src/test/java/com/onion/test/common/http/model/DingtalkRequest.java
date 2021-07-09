package com.onion.test.common.http.model;

import lombok.Data;

import java.util.List;

/**
 * @author lijicong
 * @since 2021-03-31
 */
@Data
public class DingtalkRequest {

    private String msgtype;
    private Text text;
    private At at;

    @Data
    public static class Text {
        private String content;
    }

    @Data
    public static class At {
        private Boolean isAtAll;
        private List<String> atMobiles;
    }
}
