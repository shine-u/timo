package com.shine.api.common.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 商品VO.
 *
 * @author shineYu
 * @Date 2019/5/28 9:35
 */
@Data
public class ProductVO {

    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
    @JsonProperty("goods")
    private List<ProductInfoVO> productInfoVOS;

}
