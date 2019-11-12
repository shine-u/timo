package com.shine.api.common.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品详情VO.
 *
 * @author shineYu
 * @Date 2019/5/28 9:39
 */
@Data
public class ProductInfoVO {

    /**
     * 商品编码.
     */
    @JsonProperty("id")
    private String productId;
    /**
     * 商品名称.
     */
    @JsonProperty("name")
    private String productName;
    /**
     * 商品价格.
     */
    @JsonProperty("price")
    private BigDecimal productPrice;
    /**
     * 商品简介.
     */
    @JsonProperty("description")
    private String productDescription;
    /**
     * 商品图标.
     */
    @JsonProperty("icon")
    private String productIcon;

}
