package com.github.ymf1234.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.ymf1234.model.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
}
