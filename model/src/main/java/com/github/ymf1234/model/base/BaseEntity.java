package com.github.ymf1234.model.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @TableName：表名注解，标识实体类对应的表
 *
 * @TableId：主键注解，type = IdType.AUTO（数据库 ID 自增）
 *
 * @TableField：字段注解（非主键）
 *
 * @TableLogic：逻辑删除
 */
@Data
public class BaseEntity implements Serializable {

    /**
     * 1、 ID_WORKER
     *     MyBatis-Plus默认的主键策略是：ID_WORKER  *全局唯一ID*
     * 2、自增策略
     *  要想主键自增需要配置如下主键策略
     *  需要在创建数据表的时候设置主键自增
     *  实体字段中配置 @TableId(type = IdType.AUTO)
     *
     */
    @TableId(type = IdType.AUTO)
    private int id;

    // 创建时间
    @TableField("create_time")
    private Date createTime;

    // 修改时间
    @TableField("update_time")
    private Date updateTime;

    @TableLogic  //逻辑删除 默认效果 0 没有删除 1 已经删除
    @TableField("is_deleted")
    private Integer isDeleted;

    @TableField(exist = false)
    private Map<String,Object> param = new HashMap<>();

}
