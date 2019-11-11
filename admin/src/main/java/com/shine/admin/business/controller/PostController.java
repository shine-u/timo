package com.shine.admin.business.controller;

import com.shine.admin.business.domain.Post;
import com.shine.admin.business.service.PostService;
import com.shine.admin.business.validator.PostValid;
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
 * @date 2019/11/11
 */
@Controller
@RequestMapping("/business/post")
public class PostController {

    @Autowired
    private PostService postService;

    /**
     * 列表页面
     */
    @GetMapping("/index")
    @RequiresPermissions("business:post:index")
    public String index(Model model, Post post) {

        // 创建匹配器，进行动态查询匹配
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("postTitle", match -> match.contains());

        // 获取数据列表
        Example<Post> example = Example.of(post, matcher);
        Page<Post> list = postService.getPageList(example);

        // 封装数据
        model.addAttribute("list", list.getContent());
        model.addAttribute("page", list);
        return "/business/post/index";
    }

    /**
     * 跳转到添加页面
     */
    @GetMapping("/add")
    @RequiresPermissions("business:post:add")
    public String toAdd() {
        return "/business/post/add";
    }

    /**
     * 跳转到编辑页面
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("business:post:edit")
    public String toEdit(@PathVariable("id") Post post, Model model) {
        model.addAttribute("post", post);
        return "/business/post/add";
    }

    /**
     * 保存添加/修改的数据
     * @param valid 验证对象
     */
    @PostMapping("/save")
    @RequiresPermissions({"business:post:add", "business:post:edit"})
    @ResponseBody
    public ResultVo save(@Validated PostValid valid, Post post) {
        // 复制保留无需修改的数据
        if (post.getId() != null) {
            Post bePost = postService.getById(post.getId());
            EntityBeanUtil.copyProperties(bePost, post);
        }

        // 保存数据
        postService.save(post);
        return ResultVoUtil.SAVE_SUCCESS;
    }

    /**
     * 跳转到详细页面
     */
    @GetMapping("/detail/{id}")
    @RequiresPermissions("business:post:detail")
    public String toDetail(@PathVariable("id") Post post, Model model) {
        model.addAttribute("post",post);
        return "/business/post/detail";
    }

    /**
     * 设置一条或者多条数据的状态
     */
    @RequestMapping("/status/{param}")
    @RequiresPermissions("business:post:status")
    @ResponseBody
    public ResultVo status(
            @PathVariable("param") String param,
            @RequestParam(value = "ids", required = false) List<Long> ids) {
        // 更新状态
        StatusEnum statusEnum = StatusUtil.getStatusEnum(param);
        if (postService.updateStatus(statusEnum, ids)) {
            return ResultVoUtil.success(statusEnum.getMessage() + "成功");
        } else {
            return ResultVoUtil.error(statusEnum.getMessage() + "失败，请重新操作");
        }
    }
}