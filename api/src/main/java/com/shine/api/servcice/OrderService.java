package com.shine.api.servcice;

import com.shine.api.common.dto.OrderDTO;
import com.shine.api.common.entity.OrderMaster;
import com.shine.api.common.utils.Const;
import com.shine.api.common.utils.KeyUtils;
import com.shine.api.common.utils.ServerResponse;
import com.shine.api.repository.OrderDetailRepository;
import com.shine.api.repository.OrderMasterRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 订单服务.
 *
 * @author shineYu
 * @Date 2019/6/3 1:16
 */
@Service
public class OrderService {

    @Autowired
    OrderMasterRepository orderMasterRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    /**
     *
     *      * 1.参数校验
     *      * 2.查询信息
     *      * 3.计算总价
     *      * 4.扣减库存
     *      * 5.订单入库.
     *
     * @param orderDTO
     * @return
     */
    public ServerResponse create(OrderDTO orderDTO){

        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderId(KeyUtils.genUniqueMasterKey());
        orderMaster.setOrderAmount(new BigDecimal(5));
        orderMaster.setOrderStatus(Const.OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(Const.PayStatusEnum.NO_PAY.getCode());

        OrderMaster result = orderMasterRepository.save(orderMaster);

        if (result!=null) {
            return ServerResponse.success("创建订单成功",result.getOrderId());
        }else{
            return ServerResponse.errorMessage("创建订单失败");
        }
    }

}
