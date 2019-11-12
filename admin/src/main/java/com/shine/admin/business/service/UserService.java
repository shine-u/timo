package com.shine.admin.business.service;

import com.shine.admin.business.domain.User;
import com.shine.common.enums.StatusEnum;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author shine
 * @date 2019/11/12
 */
public interface UserService {

    /**
     * 获取分页列表数据
     * @param example 查询实例
     * @return 返回分页数据
     */
    Page<User> getPageList(Example<User> example);

    /**
     * 根据ID查询数据
     * @param id 主键ID
     */
    User getById(Long id);

    /**
     * 保存数据
     * @param user 实体对象
     */
    User save(User user);

    /**
     * 状态(启用，禁用，删除)/批量状态处理
     */
    @Transactional
    Boolean updateStatus(StatusEnum statusEnum, List<Long> idList);
}