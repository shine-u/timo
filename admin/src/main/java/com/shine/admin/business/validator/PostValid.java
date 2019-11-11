package com.shine.admin.business.validator;

import lombok.Data;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;

/**
 * @author shine
 * @date 2019/11/11
 */
@Data
public class PostValid implements Serializable {
    @NotEmpty(message = "标题不能为空")
    private String postTitle;
    @NotEmpty(message = "内容不能为空")
    private String content;
}