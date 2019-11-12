package com.shine.api.repository;

import com.shine.api.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户Dao.
 *
 * @author shineYu
 * @Date 2019/5/28 14:48
 */
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * 通过用户账号获取用户.
     *
     * @param userAccount 用户账号
     * @return FileInfo 结果
     */
    User getByAccount(String userAccount);

    /**
     * 通过用户账号修改用户.
     *
     * @param userAccount 用户账号
     * @return FileInfo 结果
     */
    User save(String userAccount);

}
