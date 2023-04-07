package com.github.ymf1234.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.ymf1234.model.system.SysRole;
import com.github.ymf1234.model.vo.SysRoleQueryVo;

public interface SysRoleService extends IService<SysRole> {
    // 条件分页查询
    IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo sysRoleQueryVo);
}
