package com.shine.api.controller;

import com.shine.api.common.dto.CartDTO;
import com.shine.api.common.form.UserFrom;
import com.shine.api.common.utils.ServerResponse;
import com.shine.api.servcice.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 商品控制器.
 *
 * @author shineYu
 * @Date 2019/5/27 18:30
 */
@Controller
@RequestMapping("/api/product")
public class ProductController {

    /**
     * 商品信息服务.
     */
    @Autowired
    ProductInfoService productInfoService;

    /**
     * 获取全部在架商品.
     *
     * @return ServerResponse 通用放回结果.
     */
    @GetMapping("/list")
    public ServerResponse getAllProduct(UserFrom userFrom) {
        return productInfoService.findUpAll();
    }

    /**
     * 获取全部在架商品.
     *
     * @return ServerResponse 通用放回结果.
     */
    @PostMapping("/login")
    public String login(UserFrom userFrom, ModelMap map) {
        map.addAttribute("user", "123");
        System.out.println(userFrom);
        return "file";
    }

    /**
     * 减库存.
     *
     * @return ServerResponse 通用放回结果.
     */
    @PostMapping("/decreaseStock")
    public ServerResponse decreaseStock(CartDTO cartDTO) {
        return productInfoService.decreaseStock(cartDTO);
    }

}
