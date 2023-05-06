package com.github.ymf1234.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.ymf1234.model.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    //根据userid查找菜单权限数据
    List<SysMenu> findMenuListUserId(@Param("userId") Long userId);
}
