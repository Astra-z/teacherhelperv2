package com.spm.teacherhelperv2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jdk.management.resource.internal.TotalResourceContext;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * MenuDO实体类
 * @author zhangjr
 * @since 2020-05-16 17:28:25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("s_menu")
@ApiModel(value="MenuDO对象model", description="MenuDO原始数据类型，与表中字段一一对应")
public class MenuDO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     *  菜单/按钮ID
     */
    @ApiModelProperty(value = "菜单/按钮ID")
    @TableId(value = "MENU_ID", type = IdType.AUTO)
    private Long menuId;
    /**
     *  上级菜单ID
     */
    @ApiModelProperty(value = "上级菜单ID")
    @TableField("PARENT_ID")
    private Long parentId;
    /**
     *  菜单/按钮名称
     */
    @ApiModelProperty(value = "菜单/按钮名称")
    @TableField("MENU_NAME")
    private String menuName;
    /**
     *  菜单URL
     */
    @ApiModelProperty(value = "菜单URL")
    @TableField("URL")
    private String url;
    /**
     *  权限标识
     */
    @ApiModelProperty(value = "权限标识")
    @TableField("PERMS")
    private String perms;
    /**
     *  图标
     */
    @ApiModelProperty(value = "图标")
    @TableField("ICON")
    private String icon;
    /**
     *  类型 0菜单 1按钮
     */
    @ApiModelProperty(value = "类型 0菜单 1按钮")
    @TableField("TYPE")
    private String type;
    /**
     *  排序
     */
    @ApiModelProperty(value = "排序")
    @TableField("ORDER_NUM")
    private Long orderNum;
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



    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "MenuDO{" +
         "menuId=" + menuId
         +", parentId=" + parentId 
         +", menuName=" + menuName 
         +", url=" + url 
         +", perms=" + perms 
         +", icon=" + icon 
         +", type=" + type 
         +", orderNum=" + orderNum 
         +", createTime=" + createTime 
         +", modifyTime=" + modifyTime 
        +"}";
    }
}
