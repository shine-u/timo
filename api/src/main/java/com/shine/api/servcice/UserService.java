package com.shine.api.servcice;

import com.shine.api.common.entity.User;
import com.shine.api.common.form.UserFrom;
import com.shine.api.common.utils.CookieUtils;
import com.shine.api.common.utils.JsonUtils;
import com.shine.api.common.utils.MD5Utils;
import com.shine.api.common.utils.ServerResponse;
import com.shine.api.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * 用户服务.
 *
 * @author shineYu
 * @Date 2019/5/28 15:54
 */
@Slf4j
@Service
@Transactional
public class UserService {

    /**
     * 用户Dao.
     */
    @Autowired
    UserRepository userRepository;

    @Autowired
    StringRedisTemplate redisTemplate;

    //public User queryUserByToken(String token) throws Exception {
    //    // 根据token从redis中查询用户信息
    //    String json = redisUtil.get("REDIS_USER_SESSION:" + token);
    //    // 判断是否为空
    //    if (StringUtils.isEmpty(json)) {
    //        return null;
    //    }
    //    // 更新过期时间
    //    redisUtil.expire("REDIS_USER_SESSION:" + token, 7,TimeUnit.DAYS);
    //    // 返回用户信息
    //    return JsonUtils.objectFromJsonStr(json, User.class);
    //}

    public User getByUserAccountAndUserPassword(UserFrom user) {

        User result = userRepository.getByAccount(user.getAccount());

        return result;
    }

    public ServerResponse userLogin(String account, String password,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 判断账号密码是否正确
        User user = userRepository.getByAccount(account);
        if (user == null) {
            return ServerResponse.errorCodeMessage(400, "用户名不存在");
        }
        //比较密码
        String md5Pass = MD5Utils.MD5Encode(password + user.getId());
        if (!user.getPassword().equals(md5Pass)) {
            return ServerResponse.errorCodeMessage(400, "密码错误");
        }
        // 生成token
        String token = UUID.randomUUID().toString();
        // 把用户信息写入 redis
        redisTemplate.opsForValue().set("Token:"+user.getId().toString(), token);
        redisTemplate.opsForValue().set("User:"+token, JsonUtils.objectToJsonStr(user));
        // 设置 session 的过期时间
        redisTemplate.expire("REDIS_USER_SESSION:" + token, 7, TimeUnit.DAYS);
        // 添加写 cookie 的逻辑，cookie 的有效期是关闭浏览器就失效。
        CookieUtils.setCookie(request, response, "USER_TOKEN", token);
        log.info("\ntoken : " + token);
        // 返回token
        //更新用户数据
        user.setLoginCount(user.getLoginCount()+1);
        return ServerResponse.success(token);
    }

}

