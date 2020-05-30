package com.spm.teacherhelperv2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * RoleDO实体类
 * @author zhangjr
 * @since 2020-05-16 17:28:26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("s_role")
@ApiModel(value="RoleDO对象model", description="RoleDO原始数据类型，与表中字段一一对应")
public class RoleDO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     *  角色ID
     */
    @ApiModelProperty(value = "角色ID")
    @TableId(value = "ROLE_ID", type = IdType.AUTO)
    private Long roleId;
    /**
     *  角色名称
     */
    @ApiModelProperty(value = "角色名称")
    @TableField("ROLE_NAME")
    private String roleName;
    /**
     *  角色描述
     */
    @ApiModelProperty(value = "角色描述")
    @TableField("REMARK")
    private String remark;
    /**
     *  创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     *  修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @TableField("MODIFY_TIME")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    @Transient
    @TableField(exist = false)
    private List<Long> menuIdList;
    
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "RoleDO{" +
         "roleId=" + roleId
         +", roleName=" + roleName 
         +", remark=" + remark 
         +", createTime=" + createTime 
         +", modifyTime=" + modifyTime 
        +"}";
    }
}
