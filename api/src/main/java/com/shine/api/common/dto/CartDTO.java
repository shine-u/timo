package com.shine.api.common.dto;

import lombok.Data;

/**
 * 购物车传输对象.
 *
 * @author shineYu
 * @Date 2019/6/3 11:02
 */
@Data
public class CartDTO {

    /**商品id.*/
    private String productId;
    /**商品数量.*/
    private Integer productQuantity;

}
