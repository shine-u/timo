package com.shine.api.common.exception;

import com.shine.api.common.utils.ResponseCode;

/**
 * *^_^*
 *
 * @author shineYu
 * @Date 2019/6/3 1:48
 */
public class OrderException extends RuntimeException {

    private Integer code;

    public OrderException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public OrderException(ResponseCode responseCode) {
        super(responseCode.getDesc());
        this.code = responseCode.getCode();
    }
}
