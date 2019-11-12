package com.shine.api.common.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 商品详情.
 *
 * @author shineYu
 * @Date 2019/5/27 17:30
 */
@Data
@Entity
@DynamicUpdate
public class ProductInfo {

    /**
     * 商品编码.
     */
    @Id
    private String productId;
    /**
     * 商品名称.
     */
    private String productName;
    /**
     * 商品价格.
     */
    private BigDecimal productPrice;
    /**
     * 商品库存.
     */
    private Integer productStock;
    /**
     * 商品简介.
     */
    private String productDescription;
    /**
     * 商品图标.
     */
    private String productIcon;
    /**
     * 商品状态.
     */
    private Integer productStatus;
    /**
     * 类目编号.
     */
    private Integer categoryType;

}
