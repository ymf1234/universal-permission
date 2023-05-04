package com.github.ymf1234.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.ymf1234.model.system.SysRole;
import com.github.ymf1234.model.vo.AssignRoleVo;
import com.github.ymf1234.model.vo.SysRoleQueryVo;

import java.util.Map;

public interface SysRoleService extends IService<SysRole> {
    // 条件分页查询
    IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo sysRoleQueryVo);

    /**
     * 根据用户获取角色数据
     * @param userId
     * @return
     */
    Map<String, Object> getRolesByUserId(Long userId);

    /**
     * 分配角色
     * @param assignRoleVo
     */
    void doAssign(AssignRoleVo assignRoleVo);
}
