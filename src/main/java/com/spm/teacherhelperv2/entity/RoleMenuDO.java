package com.spm.teacherhelperv2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * RoleMenuDO实体类
 * @author zhangjr
 * @since 2020-05-16 17:28:26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("s_role_menu")
@ApiModel(value="RoleMenuDO对象model", description="RoleMenuDO原始数据类型，与表中字段一一对应")
public class RoleMenuDO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     *  角色ID
     */
    @ApiModelProperty(value = "角色ID")
    @TableId(value = "ROLE_ID", type = IdType.AUTO)
    private Long roleId;
    /**
     *  菜单/按钮ID
     */
    @ApiModelProperty(value = "菜单/按钮ID")
    @TableField("MENU_ID")
    private Long menuId;

    
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "RoleMenuDO{" +
         "roleId=" + roleId
         +", menuId=" + menuId 
        +"}";
    }
}
