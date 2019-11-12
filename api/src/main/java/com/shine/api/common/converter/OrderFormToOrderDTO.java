package com.shine.api.common.converter;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.shine.api.common.dto.OrderDTO;
import com.shine.api.common.entity.OrderDetail;
import com.shine.api.common.exception.OrderException;
import com.shine.api.common.form.OrderForm;
import com.shine.api.common.utils.ResponseCode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 参数转换.
 *
 * @author shineYu
 * @Date 2019/6/3 1:56
 */
@Slf4j
public class OrderFormToOrderDTO {

    /**
     * 接收参数转换为传输对象.
     *
     * @param orderForm
     * @return
     */
    public static OrderDTO convert(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetails = new ArrayList<>();
        Gson gson = new Gson();
        try {
            orderDetails = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (JsonSyntaxException e) {
            log.error("\n【json转换】错误，String={}",orderForm.getItems());
            throw new OrderException(ResponseCode.PARAM_ERROR);
        }
        orderDTO.setOrderDetails(orderDetails);

        return orderDTO;
    }

}
