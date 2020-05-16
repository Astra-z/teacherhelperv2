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

import java.io.Serializable;

/**
 * CollegeDO实体类
 * @author zhangjr
 * @since 2020-05-15 22:42:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("s_college")
@ApiModel(value="CollegeDO对象model", description="CollegeDO原始数据类型，与表中字段一一对应")
public class CollegeDO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableId(value = "COLLEGE_ID", type = IdType.AUTO)
    private Integer collegeId;
    /**
     *  
     */
    @ApiModelProperty(value = "")
    @TableField("COLLEGE_NAME")
    private String collegeName;

    
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "CollegeDO{" +
         "collegeId=" + collegeId
         +", collegeName=" + collegeName 
        +"}";
    }
}
