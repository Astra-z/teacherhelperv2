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
 * UserRoleDO实体类
 * @author zhangjr
 * @since 2020-05-16 17:28:27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("s_user_role")
@ApiModel(value="UserRoleDO对象model", description="UserRoleDO原始数据类型，与表中字段一一对应")
public class UserRoleDO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     *  用户ID
     */
    @ApiModelProperty(value = "用户ID")
    @TableId(value = "USER_ID", type = IdType.AUTO)
    private Long userId;
    /**
     *  角色ID
     */
    @ApiModelProperty(value = "角色ID")
    @TableField("ROLE_ID")
    private Long roleId;

    
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "UserRoleDO{" +
         "userId=" + userId
         +", roleId=" + roleId 
        +"}";
    }
}
