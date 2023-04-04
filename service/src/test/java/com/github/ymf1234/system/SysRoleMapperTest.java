package com.github.ymf1234.system;

import com.github.ymf1234.model.system.SysRole;
import com.github.ymf1234.system.mapper.SysRoleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class SysRoleMapperTest {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    // 列表
    @Test
    public void testSelectList() {
        System.out.println(("----- selectAll method test ------"));
        //UserMapper 中的 selectList() 方法的参数为 MP 内置的条件封装器 Wrapper
        //所以不填写就是无任何条件
        List<SysRole> sysRoles = sysRoleMapper.selectList(null);
        for (SysRole sysRole : sysRoles) {
            System.out.println("sysRole = " + sysRole);
        }
    }

    // 添加
    @Test
    public void testInsert() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("管理员");
        sysRole.setRoleCode("role");
        sysRole.setDescription("角色管理员");
        System.out.println(sysRole);
        int result = sysRoleMapper.insert(sysRole);
        System.out.println(result); //影响的行数
        System.out.println(sysRole.getId()); //id自动回填
    }

    // 更新
    @Test
    public void testUpdateById() {
        SysRole sysRole = new SysRole();

        sysRole.setId(9);

        sysRole.setRoleName("管理员2");

        int result = sysRoleMapper.updateById(sysRole);
        System.out.println(result);
    }

    // 删除
    @Test
    public void testDeleteBatchId() {
        int result = sysRoleMapper.deleteById(9L);
        System.out.println(result);
    }

    // 批量删除
    @Test
    public void testDeleteBatchIds() {
        int result = sysRoleMapper.deleteBatchIds(Arrays.asList(8, 9));
        System.out.println(result);
    }
}