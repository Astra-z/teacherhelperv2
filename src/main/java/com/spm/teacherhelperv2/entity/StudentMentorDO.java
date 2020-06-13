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

import javax.persistence.Transient;
import java.io.Serializable;

/**
 * StudentMentorDO实体类
 * @author zhangjr
 * @since 2020-06-13 20:45:02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("s_student_mentor")
@ApiModel(value="StudentMentorDO对象model", description="StudentMentorDO原始数据类型，与表中字段一一对应")
public class StudentMentorDO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableId(value = "STUDENT_ID")
    private Integer studentId;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("MENTOR_ID")
    private Integer mentorId;
    /**
     *
     */
    @Transient
    @TableField(exist = false)
    private UserDO studentInfo;


    
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "StudentMentorDO{" +
         "studentId=" + studentId
         +", mentorId=" + mentorId
        +"}";
    }
}
