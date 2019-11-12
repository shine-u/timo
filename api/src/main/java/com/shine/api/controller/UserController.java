package com.shine.api.controller;

import com.shine.api.common.utils.ServerResponse;
import com.shine.api.servcice.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 用户控制器.
 *
 * @author shineYu
 * @Date 2019/6/12 10:01
 */
@Slf4j
@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/{pageName}")
    public ModelAndView login(ModelAndView mv, @PathVariable String pageName) {
        mv.setViewName(pageName);
        return mv;
    }

    @RequestMapping(value = "/")
    public ModelAndView login(ModelAndView mv) {
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping(value = "/loginPage")
    public ModelAndView login(String redirect, ModelAndView mv, HttpServletRequest request) {
        mv.addObject("redirect", redirect);
        HttpSession session = request.getSession();
        session.setAttribute("redirect", redirect);
        mv.setViewName("loginPage");
        return mv;
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public ServerResponse userLogin(String account, String password,
                                  HttpServletRequest request, HttpServletResponse response, ModelAndView mv)
            throws Exception {

        return userService.userLogin(account, password, request, response);

    }

}
