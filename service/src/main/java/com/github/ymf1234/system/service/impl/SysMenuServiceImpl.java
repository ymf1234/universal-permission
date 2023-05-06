package com.github.ymf1234.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.ymf1234.common.result.ResultCodeEnum;
import com.github.ymf1234.model.system.SysMenu;
import com.github.ymf1234.model.system.SysRoleMenu;
import com.github.ymf1234.model.vo.AssignMenuVo;
import com.github.ymf1234.model.vo.RouterVo;
import com.github.ymf1234.system.handler.GuiguException;
import com.github.ymf1234.system.mapper.SysMenuMapper;
import com.github.ymf1234.system.mapper.SysRoleMenuMapper;
import com.github.ymf1234.system.service.SysMenuService;
import com.github.ymf1234.system.utils.MenuHelper;
import com.github.ymf1234.system.utils.RouterHelper;
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

    // 根据用户id查询菜单权限值
    @Override
    public List<RouterVo> getUserMenuList(Long id) {
        //admin是超级管理员，操作所有内容
        List<SysMenu> sysMenuList = null;
        // 判断userId是1代表超级管理员,查询所有权限数据
        if ("1".equals(id.toString())) {
            QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
            wrapper.eq("status",1);
            wrapper.orderByAsc("sort_value");
            sysMenuList = baseMapper.selectList(wrapper);
        } else {
            //如果userid不是1，其他类型用户，查询这个用户权限
            sysMenuList = baseMapper.findMenuListUserId(id);
        }

        //构建是树形结构
        List<SysMenu> sysMenuTreeList = MenuHelper.buildTree(sysMenuList);

        //转换成前端路由要求格式数据
        List<RouterVo> routerVoList = RouterHelper.buildRouters(sysMenuTreeList);
        return routerVoList;
    }

    //根据userid查询按钮权限值
    @Override
    public List<String> getUserButtonList(Long userId) {
        List<SysMenu> sysMenuList = null;
        //判断是否管理员
        if("1".equals(userId.toString())) {
            sysMenuList =
                    baseMapper.selectList(new QueryWrapper<SysMenu>().eq("status",1));
        } else {
            sysMenuList = baseMapper.findMenuListUserId(userId);
        }
        //sysMenuList遍历
        List<String> permissionList = new ArrayList<>();
        for (SysMenu sysMenu:sysMenuList) {
            // type=2
            if(sysMenu.getType()==2) {
                String perms = sysMenu.getPerms();
                permissionList.add(perms);
            }
        }
        return permissionList;
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
