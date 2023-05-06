package com.github.ymf1234.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.ymf1234.model.system.SysRole;
import com.github.ymf1234.model.system.SysUser;
import com.github.ymf1234.model.vo.SysRoleQueryVo;
import com.github.ymf1234.model.vo.SysUserQueryVo;

import java.util.Map;

public interface SysUserService extends IService<SysUser> {
    // 条件分页查询
    IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo sysUserQueryVo);

    SysUser getByUsername(String username);

    //根据用户名称获取用户信息（基本信息 和 菜单权限 和 按钮权限数据）
    Map<String, Object> getUserInfo(Long id);
}
