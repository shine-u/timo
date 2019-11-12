package com.shine.api.repository;

import com.shine.api.common.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 订单详情Dao.
 *
 * @author shineYu
 * @Date 2019/5/31 14:08
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
}
