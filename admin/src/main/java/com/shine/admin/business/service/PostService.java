package com.shine.admin.business.service;

import com.shine.admin.business.domain.Post;
import com.shine.common.enums.StatusEnum;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author shine
 * @date 2019/11/11
 */
public interface PostService {

    /**
     * 获取分页列表数据
     * @param example 查询实例
     * @return 返回分页数据
     */
    Page<Post> getPageList(Example<Post> example);

    /**
     * 根据ID查询数据
     * @param id 主键ID
     */
    Post getById(Long id);

    /**
     * 保存数据
     * @param post 实体对象
     */
    Post save(Post post);

    /**
     * 状态(启用，禁用，删除)/批量状态处理
     */
    @Transactional
    Boolean updateStatus(StatusEnum statusEnum, List<Long> idList);
}