package com.github.ymf1234.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.ymf1234.model.system.SysMenu;
import com.github.ymf1234.model.vo.AssignMenuVo;
import com.github.ymf1234.model.vo.RouterVo;

import java.util.List;

public interface SysMenuService extends IService<SysMenu> {
    List<SysMenu> findNodes();

    /**
     * 根据角色获取授权权限数据
     */
    List<SysMenu> findSysMenuByRoleId(Long roleId);

    /**
     * 保存角色权限
     * @param assignMenuVo
     */
    void doAssign(AssignMenuVo assignMenuVo);

    List<RouterVo> getUserMenuList(Long id);

    //根据userid查询按钮权限值
    List<String> getUserButtonList(Long id);
}
