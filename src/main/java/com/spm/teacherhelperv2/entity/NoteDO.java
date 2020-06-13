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
import java.util.Objects;

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
    private Long noteId;
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
    private Integer noteType;
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
    @TableField("NOTE_SWITCH")
    private Boolean noteSwitch;
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
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss:SSS")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteDO noteDO = (NoteDO) o;
        return Objects.equals(modifyTime, noteDO.modifyTime)&&
                Objects.equals(noteId, noteDO.noteId) &&
                Objects.equals(noteName, noteDO.noteName) &&
                Objects.equals(noteType, noteDO.noteType) &&
                Objects.equals(startTime, noteDO.startTime) &&
                Objects.equals(endTime, noteDO.endTime) &&
                Objects.equals(userId, noteDO.userId) &&
                Objects.equals(remark, noteDO.remark) &&
                Objects.equals(fileid, noteDO.fileid) &&
                Objects.equals(noteSwitch, noteDO.noteSwitch) &&
                Objects.equals(createTime, noteDO.createTime) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteId, noteName, noteType, startTime, endTime, userId, remark, fileid, noteSwitch, createTime, modifyTime);
    }
}
