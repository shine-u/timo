package com.shine.api.repository;

import com.shine.api.common.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * 商品信息Dao.
 *
 * @author shineYu
 * @Date 2019/5/27 17:34
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    /**
     * 获取上架商品.
     *
     * @return List<ProductInfo> 结果
     */
    List<ProductInfo> findByProductStatus(Integer status);

}
