package com.github.ymf1234.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.ymf1234.model.system.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
}
