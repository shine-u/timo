package com.shine.modules.system.service.impl;

import com.shine.common.data.PageSort;
import com.shine.common.enums.StatusEnum;
import com.shine.modules.system.domain.Dept;
import com.shine.modules.system.domain.SysUser;
import com.shine.modules.system.repository.SysUserRepository;
import com.shine.modules.system.service.DeptService;
import com.shine.modules.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shine
 * @date 2018/8/14
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserRepository sysSysUserRepository;

    @Autowired
    private DeptService deptService;

    /**
     * 根据用户名查询用户数据
     * @param username 用户名
     * @return 用户数据
     */
    @Override
    public SysUser getByName(String username) {
        return sysSysUserRepository.findByUsername(username);
    }

    /**
     * 用户名是否存在
     * @param user 用户对象
     * @return 用户数据
     */
    @Override
    public Boolean repeatByUsername(SysUser user) {
        Long id = user.getId() != null ? user.getId() : Long.MIN_VALUE;
        return sysSysUserRepository.findByUsernameAndIdNot(user.getUsername(), id) != null;
    }

    /**
     * 根据用户ID获取用户信息
     * @param id 用户ID
     */
    @Override
    public SysUser getById(Long id) {
        return sysSysUserRepository.findById(id).orElse(null);
    }

    /**
     * 获取分页列表数据
     * @param user 实体对象
     * @return 返回分页数据
     */
    @Override
    @Transactional
    public Page<SysUser> getPageList(SysUser user) {
        // 创建分页对象
        PageRequest page = PageSort.pageRequest(Sort.Direction.ASC);

        // 使用Specification复杂查询
        return sysSysUserRepository.findAll(new Specification<SysUser>(){

            @Override
            public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> preList = new ArrayList<>();
                if(user.getId() != null){
                    preList.add(cb.equal(root.get("id").as(Long.class), user.getId()));
                }
                if(user.getUsername() != null){
                    preList.add(cb.equal(root.get("username").as(String.class), user.getUsername()));
                }
                if(user.getNickname() != null){
                    preList.add(cb.like(root.get("nickname").as(String.class), "%"+ user.getNickname() + "%"));
                }
                if(user.getDept() != null){
                    // 联级查询部门
                    Dept dept = user.getDept();
                    List<Long> deptIn = new ArrayList<>();
                    deptIn.add(dept.getId());
                    List<Dept> deptList = deptService.getListByPidLikeOk(dept.getId());
                    deptList.forEach(item -> deptIn.add(item.getId()));

                    Join<SysUser, Dept> join = root.join("dept", JoinType.INNER);
                    CriteriaBuilder.In<Long> in = cb.in(join.get("id").as(Long.class));
                    deptIn.forEach(in::value);
                    preList.add(in);
                }

                // 数据状态
                if(user.getStatus() != null){
                    preList.add(cb.equal(root.get("status").as(Byte.class), user.getStatus()));
                }

                Predicate[] pres = new Predicate[preList.size()];
                return query.where(preList.toArray(pres)).getRestriction();
            }

        }, page);
    }

    /**
     * 保存用户
     * @param user 用户实体类
     */
    @Override
    public SysUser save(SysUser user){
        return sysSysUserRepository.save(user);
    }

    /**
     * 保存用户列表
     * @param userList 用户实体类
     */
    @Override
    @Transactional
    public List<SysUser> save(List<SysUser> userList){
        return sysSysUserRepository.saveAll(userList);
    }

    /**
     * 状态(启用，禁用，删除)/批量状态处理
     */
    @Override
    @Transactional
    public Boolean updateStatus(StatusEnum statusEnum, List<Long> ids){
        // 联级删除与角色之间的关联
        if(statusEnum == StatusEnum.DELETE){
            return sysSysUserRepository.deleteByIdIn(ids) > 0;
        }
        return sysSysUserRepository.updateStatus(statusEnum.getCode(), ids) > 0;
    }
}
