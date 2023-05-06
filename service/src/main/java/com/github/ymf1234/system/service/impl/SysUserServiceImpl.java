package com.github.ymf1234.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.ymf1234.model.system.SysUser;
import com.github.ymf1234.model.vo.RouterVo;
import com.github.ymf1234.model.vo.SysUserQueryVo;
import com.github.ymf1234.system.mapper.SysUserMapper;
import com.github.ymf1234.system.service.SysMenuService;
import com.github.ymf1234.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo sysUserQueryVo) {
        return baseMapper.selectPage(pageParam, sysUserQueryVo);
    }

    @Override
    public SysUser getByUsername(String username) {
        return baseMapper.selectOne(new QueryWrapper<SysUser>().eq("username", username));
    }

    @Override
    public Map<String, Object> getUserInfo(Long id) {
        SysUser sysUser = baseMapper.selectOne(new QueryWrapper<SysUser>().eq("id", id));
        //根据userid查询菜单权限值
        List<RouterVo> routerVolist = sysMenuService.getUserMenuList(sysUser.getId());
        //根据userid查询按钮权限值
        List<String> permsList = sysMenuService.getUserButtonList(sysUser.getId());

        Map<String,Object> result = new HashMap<>();
        result.put("name",sysUser.getUsername());
        result.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        result.put("roles","[\"admin\"]");
        //菜单权限数据
        result.put("routers",routerVolist);
        //按钮权限数据
        result.put("buttons",permsList);
        return result;
    }
}
