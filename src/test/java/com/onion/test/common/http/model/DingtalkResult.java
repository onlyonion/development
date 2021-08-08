package com.onion.test.common.http.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lijicong
 * @since 2021-03-31
 */
@Data
public class DingtalkResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer errcode;
    private String errmsg;
}
