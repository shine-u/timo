package com.shine.api.controller;

import com.shine.api.common.converter.OrderFormToOrderDTO;
import com.shine.api.common.dto.OrderDTO;
import com.shine.api.common.exception.OrderException;
import com.shine.api.common.form.OrderForm;
import com.shine.api.common.utils.ResponseCode;
import com.shine.api.common.utils.ServerResponse;
import com.shine.api.servcice.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 订单控制器.
 *
 * @author shineYu
 * @Date 2019/5/31 14:03
 */
@Slf4j
@RestController
@RequestMapping("/api/order")
public class OrderController {

    /**
     * 订单服务.
     */
    @Autowired
    private OrderService orderService;

    /**
     * 1.参数校验
     * 2.查询信息
     * 3.计算总价
     * 4.扣减库存
     * 5.订单入库.
     *
     * @param orderForm     订单接收参数.
     * @param bindingResult 错误结果.
     * @return ServerResponse 通用返回对象.
     */
    @PostMapping("/create")
    public ServerResponse createOrder(@Valid OrderForm orderForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.error("\n【创建订单】参数不正确，orderForm={}", orderForm);
            throw new OrderException(
                    bindingResult.getFieldError().getDefaultMessage(),
                    ResponseCode.PARAM_ERROR.getCode());
        }
        OrderDTO orderDTO = OrderFormToOrderDTO.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetails())) {
            log.error("\n【创建订单】购物车信息为空");
            throw new OrderException(ResponseCode.CART_EMPTY);
        }

        return orderService.create(orderDTO);
    }

}
