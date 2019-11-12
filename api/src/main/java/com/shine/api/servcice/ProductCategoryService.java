package com.shine.api.servcice;

import com.shine.api.common.entity.ProductCategory;
import com.shine.api.common.utils.ServerResponse;
import com.shine.api.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产品类目服务.
 *
 * @author shineYu
 * @Date 2019/5/27 17:13
 */
@Service
public class ProductCategoryService {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    /**
     * 查找类目.
     *
     * @param categoryId 类目编码
     * @return ProductCategory 结果
     */
    public ServerResponse findOne(Integer categoryId){
        return ServerResponse.success();
    }

    /**
     * 查找所有类目.
     *
     * @return List<ProductCategory> 结果
     */
    public ServerResponse findAll(){
        return ServerResponse.success();
    }

    /**
     * 按编码查找所有类目.
     *
     * @param categoryList 编码集合
     * @return List<ProductCategory> 结果
     */
    public ServerResponse findByCategoryTypeIn(List<Integer> categoryList){
        return ServerResponse.success();
    }

    /**
     * 保存商品类目.
     *
     * @param productCategory 商品类目
     * @return List<ProductCategory> 结果
     */
    public ServerResponse save(ProductCategory productCategory){
        return ServerResponse.success();
    }
}
