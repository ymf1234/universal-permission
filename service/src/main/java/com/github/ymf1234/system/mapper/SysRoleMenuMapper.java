package com.github.ymf1234.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.ymf1234.model.system.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
}
