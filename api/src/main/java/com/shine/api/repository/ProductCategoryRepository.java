package com.shine.api.repository;

import com.shine.api.common.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 商品类目Dao.
 *
 * @author shineYu
 * @Date 2019/5/27 16:33
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    /**
     *  通过类目集合查询类目.
     *
     * @param categoryList 类目集合
     * @return List<ProductCategory> 结果
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryList);
}
