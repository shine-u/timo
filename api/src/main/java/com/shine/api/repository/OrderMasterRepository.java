package com.shine.api.repository;

import com.shine.api.common.entity.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 订单Dao.
 *
 * @author shineYu
 * @Date 2019/5/31 14:08
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
}
