package com.shine.api.servcice;

import com.shine.api.common.dto.CartDTO;
import com.shine.api.common.entity.ProductCategory;
import com.shine.api.common.entity.ProductInfo;
import com.shine.api.common.exception.ProductException;
import com.shine.api.common.utils.Const;
import com.shine.api.common.utils.ResponseCode;
import com.shine.api.common.utils.ServerResponse;
import com.shine.api.common.vo.ProductInfoVO;
import com.shine.api.common.vo.ProductVO;
import com.shine.api.repository.ProductCategoryRepository;
import com.shine.api.repository.ProductInfoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 商品详情服务.
 *
 * @author shineYu
 * @Date 2019/5/27 18:00
 */
@Service
public class ProductInfoService {

    @Autowired
    ProductInfoRepository productInfoRepository;
    @Autowired
    ProductCategoryRepository productCategoryRepository;

    /**
     * 查找商品.
     *
     * @param productId 商品id
     * @return ProductInfo 结果
     */
    public ServerResponse findOne(String productId) {
        return ServerResponse.success();
    }

    /**
     * 查找所有上架商品.
     *
     * @return List<ProductInfo> 结果
     */
    public ServerResponse findUpAll() {

        //数据定义.
        ProductVO result = new ProductVO();
        List<ProductVO> results = new ArrayList<>();
        List<ProductInfo> productInfoList = new ArrayList<>();
        List<ProductCategory> productCategoryList = new ArrayList<>();
        List<ProductInfoVO> productInfoVOS = new ArrayList<>();
        ProductInfoVO productInfoVO = new ProductInfoVO();
        //ProductInfo productInfo;

        //获取所有商品.
        productInfoList = productInfoRepository.findByProductStatus(Const.ProductStatusEnum.ON_SALE.getCode());

        //获取分类下的所有商品.
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType()).collect(Collectors.toList());
        productCategoryList = productCategoryRepository.findByCategoryTypeIn(categoryTypeList);

        //数据拼装.
        //类目信息转换.
        for (ProductCategory productCategory : productCategoryList) {
            result.setCategoryType(productCategory.getCategoryType());
            result.setCategoryName(productCategory.getCategoryName());
        }

        //商品信息转换.
        for (ProductInfo productInfo : productInfoList) {
            if (productInfo.getCategoryType().equals(result.getCategoryType())) {
                BeanUtils.copyProperties(productInfo, productInfoVO);
                productInfoVOS.add(productInfoVO);
            }
        }

        //设置类目商品列表.
        result.setProductInfoVOS(productInfoVOS);

        results.add(result);

        return ServerResponse.success("商品列表获取成功", results);
    }

    /**
     * 按编码查找所有类目.
     *
     * @param pageable 分页参数
     * @return List<ProductInfo> 结果
     */
    public ServerResponse findAll(Pageable pageable) {
        return ServerResponse.success();
    }

    /**
     * 添加商品信息.
     *
     * @param productInfo 商品信息
     * @return List<ProductCategory> 结果
     */
    public ServerResponse save(ProductInfo productInfo) {
        return ServerResponse.success();
    }

    /**
     * 加库存.
     *
     * @param productInfo 商品信息
     * @return List<ProductCategory> 结果
     */
    //ProductInfo save(ProductInfo productInfo);

    /**
     * 减库存.
     *
     * @param cartDTO 商品信息
     * @return List<ProductCategory> 结果
     */
    public ServerResponse decreaseStock(CartDTO cartDTO) {

        ProductInfo productInfo = productInfoRepository.getOne(cartDTO.getProductId());

        //判断商品是否存在
        if (productInfo == null) {
            throw new ProductException(ResponseCode.PRODUCT_NOT_EXIST);
        }
        Integer stock = productInfo.getProductStock() - cartDTO.getProductQuantity();
        //判断库存是否正确
        if (stock < 0) {
            throw new ProductException(ResponseCode.PRODUCT_STOCK_ERROR);
        }

        productInfo.setProductStock(stock);
        ProductInfo result = productInfoRepository.save(productInfo);
        if (result == null) {
            return ServerResponse.errorMessage("减库存异常");
        }

        return ServerResponse.success("减库存成功");

    }

}
