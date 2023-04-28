package com.github.ymf1234.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.ymf1234.common.result.Result;
import com.github.ymf1234.model.system.SysRole;
import com.github.ymf1234.model.system.SysUser;
import com.github.ymf1234.model.vo.SysUserQueryVo;
import com.github.ymf1234.system.service.SysUserService;
import com.mysql.cj.log.Log;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/admin/system/sysUser")
@CrossOrigin
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "获取分页列表")
    @GetMapping("/{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "roleQueryVo", value = "查询对象", required = false)
            SysUserQueryVo userQueryVo
    ) {
        Page<SysUser> pagePage = new Page<>(page, limit);
        IPage<SysUser> sysUserIPage = sysUserService.selectPage(pagePage, userQueryVo);

        return Result.ok(sysUserIPage);
    }

    // 获取用户
    @ApiOperation(value = "获取用户")
    @GetMapping("/{id}")
    public Result save(@PathVariable Long id) {
        SysUser user = sysUserService.getById(id);
        return Result.ok(user);
    }

    // 保存用户
    @ApiOperation(value = "保存用户")
    @PostMapping("/save")
    public Result save(@RequestBody SysUser user) {
        sysUserService.save(user);
        return Result.ok();
    }

    // 更新用户
    @ApiOperation(value = "更新用户")
    @PostMapping("/update")
    public Result updateById(@RequestBody SysUser user) {
        sysUserService.updateById(user);
        return Result.ok();
    }

    // 删除用户
    @ApiOperation(value = "删除角色")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable Long id) {
        sysUserService.removeById(id);
        return Result.ok();
    }

    // 批量删除
    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        sysUserService.removeByIds(idList);
        return Result.ok();
    }
}
