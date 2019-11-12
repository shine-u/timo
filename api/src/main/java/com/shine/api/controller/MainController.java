package com.shine.api.controller;

import com.shine.api.common.form.UserFrom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * *^_^*
 *
 * @author shineYu
 * @Date 2019/6/10 11:37
 */
@Slf4j
@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping("/")
    public String main(){
        return "login";
    }

    @GetMapping("/login")
    public String login(UserFrom userFrom){
        log.info("userFrom : "+userFrom.toString());
        return "file";
    }

}
