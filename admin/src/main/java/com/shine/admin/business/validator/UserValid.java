package com.shine.admin.business.validator;

import lombok.Data;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;

/**
 * @author shine
 * @date 2019/11/12
 */
@Data
public class UserValid implements Serializable {
    @NotEmpty(message = "账号不能为空")
    private String account;
}