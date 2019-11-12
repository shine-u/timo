package com.shine.api.common.exception;

import com.shine.api.common.utils.ResponseCode;

/**
 * 商品异常类.
 *
 * @author shineYu
 * @Date 2019/6/3 11:07
 */
public class ProductException extends RuntimeException{


    private Integer code;

    public ProductException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public ProductException(ResponseCode responseCode) {
        super(responseCode.getDesc());
        this.code = responseCode.getCode();
    }

}
