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
 * UserDO实体类
 * @author zhangjr
 * @since 2020-05-15 22:42:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("s_user")
@ApiModel(value="UserDO对象model", description="UserDO原始数据类型，与表中字段一一对应")
public class UserDO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableId(value = "USER_ID", type = IdType.AUTO)
    private Long userId;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("SID")
    private Long sid;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("USERNAME")
    private String username;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("PASSWORD")
    private String password;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("REALNAME")
    private String realname;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("SEX")
    private String sex;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("BIRTH")
    private String birth;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("ENROLL_DATE")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date enrollDate;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("USER_CLASS")
    private String userClass;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("POLITICS_STATUS")
    private String politicsStatus;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("EMAIL")
    private String email;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("ADRESS")
    private String adress;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("COLLEGE_ID")
    private Integer collegeId;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("SPEC_ID")
    private Integer specId;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("CREATE_TIME")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("MODIFY_TIME")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    @Transient
    @TableField(exist = false)
    private List<String> roleName;

    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "UserDO{" +
         "userId=" + userId
         +", sid=" + sid 
         +", username=" + username 
         +", password=" + password 
         +", realname=" + realname 
         +", sex=" + sex 
         +", birth=" + birth 
         +", enrollDate=" + enrollDate 
         +", class=" + userClass
         +", politicsStatus=" + politicsStatus 
         +", email=" + email 
         +", adress=" + adress 
         +", collegeId=" + collegeId 
         +", specId=" + specId 
         +", createTime=" + createTime 
         +", modifyTime=" + modifyTime 
        +"}";
    }
}
