package com.github.ymf1234.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.ymf1234.common.result.ResultCodeEnum;
import com.github.ymf1234.model.system.SysMenu;
import com.github.ymf1234.system.handler.GuiguException;
import com.github.ymf1234.system.mapper.SysMenuMapper;
import com.github.ymf1234.system.service.SysMenuService;
import com.github.ymf1234.system.utils.MenuHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;

@Transactional
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;


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
    public boolean removeById(Serializable id) {
        int count = this.count(new QueryWrapper<SysMenu>().eq("parent_id", id));

        if (count > 0) {
            throw new GuiguException(ResultCodeEnum.NODE_ERROR.getCode(), ResultCodeEnum.NODE_ERROR.getMessage());
        }

        sysMenuMapper.deleteById(id);
        return false;
    }
}
