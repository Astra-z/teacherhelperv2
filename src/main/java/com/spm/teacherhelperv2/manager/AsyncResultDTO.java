package com.spm.teacherhelperv2.manager;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="AsnayResultDTO对象model", description="异步回调结果数据类型")
public class AsyncResultDTO {
    /**
     *  状态码
     */
    @ApiModelProperty(value = "状态码，200为数据操作成功，551为发生错误")
	private int status;
    
    /**
     *  操作完成时间
     */
    @ApiModelProperty(value = "操作完成时间，格式为yyyy-MM-dd HH:mm:ss")
	private String time;
    
    /**
     *  操作内容
     */
    @ApiModelProperty(value = "操作内容")
    private String operation;
    
    /**
     *  操作数据
     */
    @ApiModelProperty(value = "操作数据")
    private Object dataValue;
    
    /**
     *  操作结果
     */
    @ApiModelProperty(value = "操作结果")
    private Boolean flag;
    
    /**
     *  错误原因
     */
    @ApiModelProperty(value = "错误原因")
    private String error;
	
    public String toString() {
    	return "AsnayResultDTO{" +
    	         "status=" + status
    	         +", time=" + time 
    	         +", operation=" + operation 
    	         +", dataValue=" + dataValue 
    	         +", flag=" + flag 
    	         +", error=" + error 
    	        +"}";
    }
}
