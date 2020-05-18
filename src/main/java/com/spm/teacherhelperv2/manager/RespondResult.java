package com.spm.teacherhelperv2.manager;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("列表对象")
public class RespondResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	// 响应业务状态
    @ApiModelProperty(value = "返回码",dataType = "int")
    private Integer status;

    // 响应消息
    @ApiModelProperty(value = "信息",dataType = "String")
    private String msg;
    
    @ApiModelProperty(value = "返回数据信息")
    private T data;

    public static RespondResult<Object> error(String msg) {
        RespondResult<Object> listResult = new RespondResult();
        listResult.setStatus(500);
        listResult.setMsg(msg);
        return listResult;
    }

    public static RespondResult<Object> success(String msg,Object data) {
        RespondResult<Object> listResult = new RespondResult();
        listResult.setStatus(200);
        listResult.setData(data);
        listResult.setMsg(msg);
        return listResult;
    }
}