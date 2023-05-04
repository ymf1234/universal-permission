package com.github.ymf1234.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.ymf1234.model.system.SysMenu;

import java.util.List;

public interface SysMenuService extends IService<SysMenu> {
    List<SysMenu> findNodes();
}
