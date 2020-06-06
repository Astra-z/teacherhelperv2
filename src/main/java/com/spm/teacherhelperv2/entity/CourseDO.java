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
 * CourseDO实体类
 * @author zhangjr
 * @since 2020-05-15 22:42:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("s_course")
@ApiModel(value="CourseDO对象model", description="CourseDO原始数据类型，与表中字段一一对应")
public class CourseDO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableId(value = "COURSE_ID", type = IdType.AUTO)
    private Integer courseId;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("COURSE_NAME")
    private String courseName;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("TERM")
    private String term;

    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("COURSE_TEACHER")
    private String courseTeacher;
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
    @TableField("REMARK")
    private String remark;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("COURSE_ADDRESS")
    private String courseAddress;
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
    private List<CourseFrequencyDO> courseTimeList;

    @Transient
    @TableField(exist = false)
    private SpecDO specDO;


    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "CourseDO{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", term='" + term + '\'' +
                ", courseTeacher='" + courseTeacher + '\'' +
                ", specId=" + specId +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
