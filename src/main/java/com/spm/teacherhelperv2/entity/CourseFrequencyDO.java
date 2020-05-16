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

import java.time.LocalTime;
import java.util.Date;

/**
 * CourseFrequencyDO实体类
 * @author zhangjr
 * @since 2020-05-15 22:42:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("s_course_frequency")
@ApiModel(value="CourseFrequencyDO对象model", description="CourseFrequencyDO原始数据类型，与表中字段一一对应")
public class CourseFrequencyDO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableId(value = "COURSE_FREQUENCY_ID", type = IdType.AUTO)
    private Integer courseFrequencyId;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("COURSEID")
    private Integer courseid;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("WEEKDAY")
    private Integer weekday;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("START_TIME")
    private Date startTime;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("END_TIME")
    private Date endTime;
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
        return "CourseFrequencyDO{" +
         "courseFrequencyId=" + courseFrequencyId
         +", courseid=" + courseid 
         +", weekday=" + weekday 
         +", startTime=" + startTime 
         +", endTime=" + endTime 
         +", createTime=" + createTime 
         +", modifyTime=" + modifyTime 
        +"}";
    }
}
