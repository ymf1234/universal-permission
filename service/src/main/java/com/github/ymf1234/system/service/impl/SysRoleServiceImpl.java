package com.github.ymf1234.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.ymf1234.model.system.SysRole;
import com.github.ymf1234.model.system.SysUserRole;
import com.github.ymf1234.model.vo.AssignRoleVo;
import com.github.ymf1234.model.vo.SysRoleQueryVo;
import com.github.ymf1234.system.mapper.SysRoleMapper;
import com.github.ymf1234.system.mapper.SysUserRoleMapper;
import com.github.ymf1234.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service

public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo sysRoleQueryVo) {
        return baseMapper.selectPage(pageParam, sysRoleQueryVo);
    }

    @Override
    public Map<String, Object> getRolesByUserId(Long userId) {
        // 获取所有角色
        List<SysRole> roles = baseMapper.selectList(null);
        // 根据用户id查询
        QueryWrapper<SysUserRole> sysUserRoleQueryWrapper = new QueryWrapper<>();
        sysUserRoleQueryWrapper.eq("user_id", userId);
        // 获取用户已分配的角色
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectList(sysUserRoleQueryWrapper);
        // 获取用户已分配的角色id
        List<Long> userRoleIds = new ArrayList<>();
        for (SysUserRole userRole : sysUserRoles) {
            userRoleIds.add(userRole.getUserId());
        }
        // 创建返回的Map
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("allRoles", roles);
        returnMap.put("userRoleIds", userRoleIds);
        return returnMap;
    }

    @Override
    public void doAssign(AssignRoleVo assignRoleVo) {
        // 根据用户id删除原来分配的角色
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", assignRoleVo.getUserId());
        sysUserRoleMapper.delete(queryWrapper);
        // 获取所有的角色id
        List<Long> roleIdList = assignRoleVo.getRoleIdList();
        for (Long roleId : roleIdList) {
            if (roleId != null) {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(assignRoleVo.getUserId());
                sysUserRole.setRoleId(roleId);
                // 保存
                sysUserRoleMapper.insert(sysUserRole);
            }
        }
    }
}
