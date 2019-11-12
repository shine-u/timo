package com.shine.api.common.entity;

import com.shine.api.common.utils.StatusEnum;
import com.shine.api.common.utils.StatusUtil;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author shine
 * @date 2019/11/12
 */
@Data
@Entity
@Table(name="tb_user")
@EntityListeners(AuditingEntityListener.class)
@Where(clause = StatusUtil.NOT_DELETE)
public class User implements Serializable {
    // 主键ID
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    // 账号
    private String account;
    // 密码
    private String password;
    // 电话
    private Long phone;
    // 邮箱
    private String email;
    // 地址
    private String address;
    // IP地址
    private String ip;
    // 头像地址
    private String imgUrl;
    // 登陆次数
    private Integer loginCount;
    // 创建时间
    @CreatedDate
    private Date createDate;
    // 更新时间
    @UpdateTimestamp
    private Date updateDate;
    // 数据状态
    private Byte status = StatusEnum.OK.getCode();
}