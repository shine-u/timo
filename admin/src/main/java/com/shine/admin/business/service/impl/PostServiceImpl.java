package com.shine.admin.business.service.impl;

import com.shine.admin.business.domain.Post;
import com.shine.admin.business.repository.PostRepository;
import com.shine.admin.business.service.PostService;
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
 * @date 2019/11/11
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    /**
     * 根据ID查询数据
     * @param id 主键ID
     */
    @Override
    @Transactional
    public Post getById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    /**
     * 获取分页列表数据
     * @param example 查询实例
     * @return 返回分页数据
     */
    @Override
    public Page<Post> getPageList(Example<Post> example) {
        // 创建分页对象
        PageRequest page = PageSort.pageRequest();
        return postRepository.findAll(example, page);
    }

    /**
     * 保存数据
     * @param post 实体对象
     */
    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    /**
     * 状态(启用，冻结，删除)/批量状态处理
     */
    @Override
    @Transactional
    public Boolean updateStatus(StatusEnum statusEnum, List<Long> idList) {
        return postRepository.updateStatus(statusEnum.getCode(), idList) > 0;
    }
}