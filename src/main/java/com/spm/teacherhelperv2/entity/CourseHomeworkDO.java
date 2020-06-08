package com.spm.teacherhelperv2.entity;

/**
 * description: CourseHomeworkDO
 * date: 2020/6/7 10:29
 * author: Zhangjr
 * version: 1.0
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("s_course_homework")
@ApiModel(value="CourseHomeworkDO对象model", description="CourseHomeworkDO原始数据类型，与表中字段一一对应")
public class CourseHomeworkDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty(value = "")
    @TableId(value = "COURSE_HOMEWORK_ID", type = IdType.AUTO)
    private Integer courseHomeworkId;
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
    @TableField("COURSE_HOMEWORK_NAME")
    private String courseHomeworkName;
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
        return "CourseHomeworkDO{" +
                "courseHomeworkId=" + courseHomeworkId +
                ", courseId=" + courseId +
                ", courseHomeworkName='" + courseHomeworkName + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}

