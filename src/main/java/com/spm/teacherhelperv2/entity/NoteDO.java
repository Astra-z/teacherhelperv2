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

import java.io.Serializable;
import java.util.Date;

/**
 * NoteDO实体类
 * @author zhangjr
 * @since 2020-06-08 16:33:17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("s_note")
@ApiModel(value="NoteDO对象model", description="NoteDO原始数据类型，与表中字段一一对应")
public class NoteDO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableId(value = "NOTE_ID", type = IdType.AUTO)
    private Integer noteId;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("NOTE_NAME")
    private String noteName;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("NOTE_TYPE")
    private Boolean noteType;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("START_TIME")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("END_TIME")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("USER_ID")
    private Integer userId;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("REMARK")
    private String remark;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("FILEID")
    private String fileid;
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

    
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "NoteDO{" +
         "noteId=" + noteId
         +", noteName=" + noteName 
         +", noteType=" + noteType 
         +", startTime=" + startTime 
         +", endTime=" + endTime 
         +", userId=" + userId 
         +", remark=" + remark 
         +", fileid=" + fileid 
         +", createTime=" + createTime 
         +", modifyTime=" + modifyTime 
        +"}";
    }
}
