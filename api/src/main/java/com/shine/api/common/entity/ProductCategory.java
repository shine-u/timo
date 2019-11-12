package com.shine.api.common.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 产品类目.
 *
 * @author  shineYu
 * @Date 2019/5/27 16:23
 */
@Data
@Entity
@DynamicUpdate
public class ProductCategory {

    /**
     * 类目编码.
     */
    @Id
    @GeneratedValue
    private Integer categoryId;
    /**
     * 类目名称.
     */
    private String categoryName;
    /**
     * 类目别名.
     */
    private String categoryAlias;
    /**
     * 类目编号.
     */
    private Integer categoryType;

}
