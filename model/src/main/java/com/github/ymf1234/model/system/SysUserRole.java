package com.github.ymf1234.model.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.github.ymf1234.model.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysUserRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色id")
    @TableField("role_id")
    private Long roleId;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Long userId;
}
