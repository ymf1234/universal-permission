package com.github.ymf1234.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.ymf1234.model.system.SysUser;
import com.github.ymf1234.model.vo.SysUserQueryVo;
import com.github.ymf1234.system.mapper.SysUserMapper;
import com.github.ymf1234.system.service.SysUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo sysUserQueryVo) {
        return baseMapper.selectPage(pageParam, sysUserQueryVo);
    }
}
