package com.github.ymf1234.system.controller;

import com.github.ymf1234.common.result.Jwt;
import com.github.ymf1234.common.result.Result;
import com.github.ymf1234.common.result.ResultCodeEnum;
import com.github.ymf1234.common.utils.JwtHelper;
import com.github.ymf1234.common.utils.MD5;
import com.github.ymf1234.model.system.SysUser;
import com.github.ymf1234.model.vo.LoginVo;
import com.github.ymf1234.system.handler.GuiguException;
import com.github.ymf1234.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 后台登录等出
 */
@Api(tags="后台登录管理")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 登录
     * @return
     */
    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo) {
        //根据username查询数据
        SysUser sysUser = sysUserService.getByUsername(loginVo.getUsername());
        //如果查询为空
        if (sysUser == null) {
            throw new GuiguException(ResultCodeEnum.ACCOUNT_ERROR.getCode(), ResultCodeEnum.ACCOUNT_ERROR.getMessage());
        }
        // 判断密码是否一致
        String password = loginVo.getPassword();
        String md5Password = MD5.encrypt(password);
        if (!sysUser.getPassword().equals(md5Password)) {
            throw new GuiguException(ResultCodeEnum.PASSWORD_ERROR.getCode(), ResultCodeEnum.PASSWORD_ERROR.getMessage());
        }

        // 判断用户是否可用
        if (sysUser.getStatus() == 0) {
            throw new GuiguException(ResultCodeEnum.ACCOUNT_STOP.getCode(), ResultCodeEnum.ACCOUNT_STOP.getMessage());
        }

        // 创建token
        HashMap<String, Object> map = new HashMap<>();
        map.put(Jwt.build().getUsername(), sysUser.getUsername());
        map.put(Jwt.build().getUserId(), sysUser.getId());
        String token = JwtHelper.createToken(map);

        map.put(Jwt.build().getToken(), token);
        return Result.ok(map);
    }

    /**
     * 获取用户信息
     * @return
     */
    @ApiOperation(value = "获取用户信息")
    @GetMapping("/info")
    public Result info(HttpServletRequest request) {
        //获取请求头token字符串
        String token = request.getHeader("token");
        // 解析Jwt
        Long userId = JwtHelper.getUserId(token);

        //根据用户名称获取用户信息（基本信息 和 菜单权限 和 按钮权限数据）
        Map<String,Object> map = sysUserService.getUserInfo(userId);
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
