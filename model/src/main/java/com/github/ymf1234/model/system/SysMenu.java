package com.github.ymf1234.model.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.github.ymf1234.model.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SysMenu extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("所属上级")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty("名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("类型(1:菜单,2:按钮)")
    @TableField("type")
    private Integer type;

    @ApiModelProperty("路由地址")
    @TableField("path")
    private String path;

    @ApiModelProperty("组件路径")
    @TableField("component")
    private String component;

    @ApiModelProperty("权限标识")
    @TableField("perms")
    private String perms;

    @ApiModelProperty("图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "排序")
    @TableField("sort_value")
    private Integer sortValue;

    @ApiModelProperty(value = "状态(0:禁止,1:正常)")
    @TableField("status")
    private Integer status;

    // 下级列表
    @TableField(exist = false)
    private List<SysMenu> children;

    // 是否选中
    @TableField(exist = false)
    private boolean isSelect;

}
