package com.shine.api.common.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 用户接收参数.
 *
 * @author shineYu
 * @Date 2019/6/3 1:41
 */
@Data
public class UserFrom {

    /**账号.*/
    @NotEmpty(message = "账号必填")
    private String account;
    /**密码.*/
    @NotEmpty(message = "密码必填")
    private String password;

}
