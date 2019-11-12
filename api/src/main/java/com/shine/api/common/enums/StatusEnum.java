package com.shine.api.common.enums;

import com.shine.api.common.utils.StatusConst;
import lombok.Getter;

/**
 * 数据状态枚举-用于逻辑删除控制
 * @author shine
 * @date 2018/8/14
 */
@Getter
public enum StatusEnum {

    /**
     * 正常的数据
     */
    OK(StatusConst.OK, "启用"),
    /**
     * 被禁用的数据，不可用
     */
    FREEZED(StatusConst.FREEZED, "禁用"),
    /**
     * 数据已被删除
     */
    DELETE(StatusConst.DELETE, "删除");

    private Byte code;

    private String message;

    StatusEnum(Byte code, String message) {
        this.code = code;
        this.message = message;
    }
}

