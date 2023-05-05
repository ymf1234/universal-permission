package com.github.ymf1234.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.ymf1234.common.result.ResultCodeEnum;
import com.github.ymf1234.model.system.SysMenu;
import com.github.ymf1234.model.system.SysRoleMenu;
import com.github.ymf1234.model.vo.AssignMenuVo;
import com.github.ymf1234.system.handler.GuiguException;
import com.github.ymf1234.system.mapper.SysMenuMapper;
import com.github.ymf1234.system.mapper.SysRoleMenuMapper;
import com.github.ymf1234.system.service.SysMenuService;
import com.github.ymf1234.system.utils.MenuHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;


    @Override
    public List<SysMenu> findNodes() {
        // 全部权限列表
        List<SysMenu> sysMenuList = this.list();
        if (CollectionUtils.isEmpty(sysMenuList)) return null;

        // 构建树形结构
        List<SysMenu> result = MenuHelper.buildTree(sysMenuList);

        return result;
    }

    @Override
    public List<SysMenu> findSysMenuByRoleId(Long roleId) {
        // 获取所有status为1的权限列表
        List<SysMenu> menuList = sysMenuMapper.selectList(new QueryWrapper<SysMenu>().eq("status", 1));
        // 根据角色id获取角色权限
        List<SysRoleMenu> roleMenus = sysRoleMenuMapper.selectList(new QueryWrapper<SysRoleMenu>().eq("role_id", roleId));
        // 获取该角色已分配的所有权限id
        List<Long> roleMenuIds = new ArrayList<>();
        for (SysRoleMenu roleMenu : roleMenus) {
            roleMenuIds.add(roleMenu.getId());
        }
        // 遍历所有权限列表
        for (SysMenu sysMenu : menuList) {
            // 设置改权限已分配
            sysMenu.setSelect(roleMenuIds.contains(sysMenu.getId()));
        }

        // 将权限列表转换为权限数
        List<SysMenu> sysMenuList = MenuHelper.buildTree(menuList);
        return sysMenuList;
    }

    @Override
    public void doAssign(AssignMenuVo assignMenuVo) {
        // 删除已分配的权限
        sysRoleMenuMapper.delete(new QueryWrapper<SysRoleMenu>().eq("role_id", assignMenuVo.getRoleId()));
        // 遍历所有已选择的权限id
        for (Long menuId : assignMenuVo.getMenuIdList()) {
            if (menuId != null) {
                // 创建SysRoleMenu对象
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setMenuId(menuId);
                sysRoleMenu.setRoleId(assignMenuVo.getRoleId());
                // 新增权限
                sysRoleMenuMapper.insert(sysRoleMenu);
            }
        }

    }

    @Override
    public boolean removeById(Serializable id) {
        int count = this.count(new QueryWrapper<SysMenu>().eq("parent_id", id));

        if (count > 0) {
            throw new GuiguException(ResultCodeEnum.NODE_ERROR.getCode(), ResultCodeEnum.NODE_ERROR.getMessage());
        }

        sysMenuMapper.deleteById(id);
        return false;
    }
}
