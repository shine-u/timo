package com.shine.admin.business.controller;

import com.shine.admin.business.domain.User;
import com.shine.admin.business.service.UserService;
import com.shine.admin.business.validator.UserValid;
import com.shine.common.enums.StatusEnum;
import com.shine.common.utils.EntityBeanUtil;
import com.shine.common.utils.ResultVoUtil;
import com.shine.common.utils.StatusUtil;
import com.shine.common.vo.ResultVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author shine
 * @date 2019/11/12
 */
@Controller
@RequestMapping("/business/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 列表页面
     */
    @GetMapping("/index")
    @RequiresPermissions("business:user:index")
    public String index(Model model, User user) {

        // 创建匹配器，进行动态查询匹配
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("account", match -> match.contains());

        // 获取数据列表
        Example<User> example = Example.of(user, matcher);
        Page<User> list = userService.getPageList(example);

        // 封装数据
        model.addAttribute("list", list.getContent());
        model.addAttribute("page", list);
        return "/business/user/index";
    }

    /**
     * 跳转到添加页面
     */
    @GetMapping("/add")
    @RequiresPermissions("business:user:add")
    public String toAdd() {
        return "/business/user/add";
    }

    /**
     * 跳转到编辑页面
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("business:user:edit")
    public String toEdit(@PathVariable("id") User user, Model model) {
        model.addAttribute("user", user);
        return "/business/user/add";
    }

    /**
     * 保存添加/修改的数据
     * @param valid 验证对象
     */
    @PostMapping("/save")
    @RequiresPermissions({"business:user:add", "business:user:edit"})
    @ResponseBody
    public ResultVo save(@Validated UserValid valid, User user) {
        // 复制保留无需修改的数据
        if (user.getId() != null) {
            User beUser = userService.getById(user.getId());
            EntityBeanUtil.copyProperties(beUser, user);
        }

        // 保存数据
        userService.save(user);
        return ResultVoUtil.SAVE_SUCCESS;
    }

    /**
     * 跳转到详细页面
     */
    @GetMapping("/detail/{id}")
    @RequiresPermissions("business:user:detail")
    public String toDetail(@PathVariable("id") User user, Model model) {
        model.addAttribute("user",user);
        return "/business/user/detail";
    }

    /**
     * 设置一条或者多条数据的状态
     */
    @RequestMapping("/status/{param}")
    @RequiresPermissions("business:user:status")
    @ResponseBody
    public ResultVo status(
            @PathVariable("param") String param,
            @RequestParam(value = "ids", required = false) List<Long> ids) {
        // 更新状态
        StatusEnum statusEnum = StatusUtil.getStatusEnum(param);
        if (userService.updateStatus(statusEnum, ids)) {
            return ResultVoUtil.success(statusEnum.getMessage() + "成功");
        } else {
            return ResultVoUtil.error(statusEnum.getMessage() + "失败，请重新操作");
        }
    }
}