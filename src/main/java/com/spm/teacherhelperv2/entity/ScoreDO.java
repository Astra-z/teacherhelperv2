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
 * ScoreDO实体类
 * @author zhangjr
 * @since 2020-05-15 22:42:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("s_score")
@ApiModel(value="ScoreDO对象model", description="ScoreDO原始数据类型，与表中字段一一对应")
public class ScoreDO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableId(value = "SCORE_ID", type = IdType.AUTO)
    private Integer scoreId;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("STUDENT_ID")
    private Integer studentId;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("COURSE_ID")
    private Integer courseId;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("TEACHER_ID")
    private Integer teacherId;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("CLASS_ID")
    private Integer classId;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("SCORE")
    private Float score;
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
        return "ScoreDO{" +
         "scoreId=" + scoreId
         +", studentId=" + studentId 
         +", courseId=" + courseId 
         +", teacherId=" + teacherId 
         +", classId=" + classId 
         +", score=" + score 
         +", createTime=" + createTime 
         +", modifyTime=" + modifyTime 
        +"}";
    }
}
