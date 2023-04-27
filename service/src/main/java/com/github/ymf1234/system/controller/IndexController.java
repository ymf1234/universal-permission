package com.github.ymf1234.system.controller;

import com.github.ymf1234.common.result.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 后台登录等出
 */
@Api(tags="后台登录管理")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

    /**
     * 登录
     * @return
     */
    @PostMapping("/login")
    public Result login() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", "admin");
        return Result.ok(map);
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("/info")
    public Result info() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("roles", "[admin]");
        map.put("name", "admin");
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.ok(map);
    }

    /**
     * 退出
     * @return
     */
    @PostMapping("/logout")
    public Result logout() {
        return Result.ok();
    }

}
