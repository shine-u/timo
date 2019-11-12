package com.shine.component.shiro.config;

import com.shine.modules.system.domain.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * 审核员自动赋值配置
 * @author shine
 * @date 2018/8/14
 */
@Configuration
public class AuditorConfig implements AuditorAware<SysUser> {
    @Override
    public Optional<SysUser> getCurrentAuditor() {
        Subject subject = SecurityUtils.getSubject();
        SysUser user = (SysUser) subject.getPrincipal();
        return Optional.ofNullable(user);
    }
}
