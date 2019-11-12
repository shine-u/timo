package com.shine.modules.system.repository;

import com.shine.modules.system.domain.Dept;
import com.shine.modules.system.domain.SysUser;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author shine
 * @date 2018/8/14
 */
public interface SysUserRepository extends BaseRepository<SysUser, Long>, JpaSpecificationExecutor<SysUser> {

    /**
     * 根据用户名查询用户数据
     * @param username 用户名
     * @return 用户数据
     */
    public SysUser findByUsername(String username);

    /**
     * 根据用户名查询用户数据,且排查指定ID的用户
     * @param username 用户名
     * @param id 排除的用户ID
     * @return 用户数据
     */
    public SysUser findByUsernameAndIdNot(String username, Long id);

    /**
     * 查找多个相应部门的用户列表
     * @param dept 部门对象
     * @return 用户列表
     */
    public List<SysUser> findByDept(Dept dept);

    /**
     * 删除多条数据
     * @param ids ID列表
     * @return 影响行数
     */
    public Integer deleteByIdIn(List<Long> ids);
}
