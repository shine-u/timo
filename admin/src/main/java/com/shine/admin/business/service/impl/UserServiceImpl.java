package com.shine.admin.business.service.impl;

import com.shine.admin.business.domain.User;
import com.shine.admin.business.repository.UserRepository;
import com.shine.admin.business.service.UserService;
import com.shine.common.data.PageSort;
import com.shine.common.enums.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author shine
 * @date 2019/11/12
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 根据ID查询数据
     * @param id 主键ID
     */
    @Override
    @Transactional
    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * 获取分页列表数据
     * @param example 查询实例
     * @return 返回分页数据
     */
    @Override
    public Page<User> getPageList(Example<User> example) {
        // 创建分页对象
        PageRequest page = PageSort.pageRequest();
        return userRepository.findAll(example, page);
    }

    /**
     * 保存数据
     * @param user 实体对象
     */
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * 状态(启用，禁用，删除)/批量状态处理
     */
    @Override
    @Transactional
    public Boolean updateStatus(StatusEnum statusEnum, List<Long> idList) {
        return userRepository.updateStatus(statusEnum.getCode(), idList) > 0;
    }
}