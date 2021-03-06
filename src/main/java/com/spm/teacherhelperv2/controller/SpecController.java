package com.spm.teacherhelperv2.controller;

import com.alibaba.fastjson.JSONObject;
import com.spm.teacherhelperv2.entity.SpecDO;
import com.spm.teacherhelperv2.manager.RespondResult;
import com.spm.teacherhelperv2.service.SpecService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  SpecController前端控制器
 * @author zhangjr
 * @since 2020-05-18 13:13:11
 */

@RestController
@RequestMapping("${global.version}/specs")
@Api(tags = "s_spec表操作API")	
public class SpecController {
    private Logger logger = LoggerFactory.getLogger(SpecController.class);
    @Autowired
	@Qualifier("SpecService")
    private SpecService specService;
	
	/**
	 * 获取满足某些条件的全部数据列表 
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @param page 页数
	 * @param limit 每页限制条数
	 * @return specDO实体类数据列表
	 */
	@GetMapping("/")
	@RequiresPermissions({ "spec:list" })
	@ApiOperation(value = "获取满足某些条件的全部数据列", httpMethod = "GET", notes = "用于通过指定条件,查询s_spec表对应所用数据")
	@ApiImplicitParams({ @ApiImplicitParam(name = "page", value = "请求页码", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "limit", value = "每页数据条数", paramType = "query", dataType = "String"), 
			@ApiImplicitParam(name = "fieldValue", value = "查询条件值", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "fieldName", value = "查询条件值属性名", paramType = "query", dataType = "String")})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
			@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
			@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult listSpecByOther(@RequestParam(value = "page", required = false) String page,
                                         @RequestParam(value = "limit", required = false) String limit,
                                         @RequestParam(value = "fieldValue", required = false) String fieldValue,
                                         @RequestParam(value = "fieldName", required = false) String fieldName) {
		logger.info("receive:[page:"+page+"--limit:"+limit+"--fieldValue:"+fieldValue+"--fieldName:"+fieldName+"]");
		try {
			List<SpecDO> specs = specService.listSpecByOther(fieldValue, fieldName, page, limit);
			return RespondResult.success("查找成功",specs);
		}catch (Exception e){
    		return RespondResult.error("查找失败");
    	}


	}


    /**
     * 根据ID查找数据
     * @return specDO实体类数据
     */
	@GetMapping("/{specId}")
	@RequiresPermissions({"spec:list" })
    @ApiOperation(value = "根据ID查找数据",httpMethod = "GET",notes = "用于通过specId，查询s_spec表中对应的一条数据")
	@ApiImplicitParam(name = "specId", value = "specId", paramType = "path", dataType = "String")
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult getSpecById(@ApiParam(value = "specId", required = true)@PathVariable("specId")String specId) {
		logger.info("receive:[specId:"+specId+"]");
		try {
			SpecDO specDO = specService.getSpecById(Long.valueOf(specId));
			return RespondResult.success("查找成功",specDO);
		}catch (Exception e){
    		return RespondResult.error("查找失败");
    	}
	}
	
	/**
	 * 根据其他查找数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @return specDO实体类数据
	 */
	@GetMapping("/spec")
	@RequiresPermissions({ "spec:list" })
	@ApiOperation(value = "根据其他查找数据", httpMethod = "GET", notes = "用于通过指定字段，查询uuuec_user表中对应的一条数据")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "fieldValue", value = "查询条件值", paramType = "query", dataType = "String"),
	@ApiImplicitParam(name = "fieldName", value = "查询条件值属性名", paramType = "query", dataType = "String")
	})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
			@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
			@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult getSpecByOther(@RequestParam(value = "fieldValue", required = false) String fieldValue
			, @RequestParam(value = "fieldName", required = false) String fieldName) {
		logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"]");
		try {
			SpecDO specDO = specService.getSpecByOther(fieldValue,fieldName);
			return RespondResult.success("查找成功",specDO);
		}catch (Exception e){
    		return RespondResult.error("查找失败");
    	}

	}
	
    /**
     * 添加数据
     * @return specDO实体类数据
     */
	@PostMapping("/")
	@RequiresPermissions({ "spec:list", "spec:add"})
	@ApiOperation(value = "添加数据",httpMethod = "POST",notes = "用于在s_spec表中插入对应的一条数据，为异步方法，结果会回调到异步地址中\n;"
			+ "SpecDO实体类数据{\n"
			+ "变量名：\"collegeId\",类型：Integer,注释：\n,"
			+ "变量名：\"specName\",类型：String,注释：\n,"
	        + "}\n")
	@ApiImplicitParam(name = "data", value = "specDO实体类数据", paramType = "body", dataType = "String")
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult insertSpec(@RequestBody(required=true)String data) {
		logger.info("receive:[data:"+data+"]");
		try {
			SpecDO specDO= JSONObject.parseObject(data, SpecDO.class);
			specService.insertSpec(specDO);
			return RespondResult.success("添加数据成功",specDO);
		}catch (Exception e){
    		return RespondResult.error("添加数据失败");
    	}

	}

	
    /**
     * 更新数据
     * @param data specDO实体类数据
     * @return specDO实体类数据
     */
	@PutMapping("/{specId}")
	@RequiresPermissions({"spec:list", "spec:update"})
	@ApiOperation(value = "更新数据",httpMethod = "PUT",notes = "用于更新s_spec表中对应的一条数据，为异步方法，结果会回调到异步地址中\n"
			+ "SpecDO实体类数据{\n"
			+ "变量名：\"collegeId\",类型：Integer,注释：\n,"
			+ "变量名：\"specName\",类型：String,注释：\n,"
	        + "}\n")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "specId", value = "specId", paramType = "path", dataType = "String"),
	@ApiImplicitParam(name = "data", value = "specDO实体类数据", paramType = "body", dataType = "String")
	})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult updateSpecDO(@RequestBody(required=true)String data) {
		logger.info("receive:[data:"+data+"]");
		try {
			SpecDO specDO= JSONObject.parseObject(data, SpecDO.class);
			specDO = specService.updateSpec(specDO);
			return RespondResult.success("更新数据成功",specDO);
		}catch (Exception e){
    		return RespondResult.error("更新数据失败");
    	}

     }

    /**
     * 更新部分数据
     * @param specId specId
     * @param data specDO部分信息
     * @return GetResult<Boolean>(suatus:状态码,msg:消息,data:处理结果)
     */
	@PatchMapping("/{specId}")
	@RequiresPermissions({ "spec:list", "spec:update"})
	@ApiOperation(value = "更新部分数据",httpMethod = "PATCH",notes = "用于更新s_spec表中对应的一条数据，为异步方法，结果会回调到异步地址中")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "specId", value = "specId", paramType = "path", dataType = "String"),
	@ApiImplicitParam(name = "data", value = "specDO实体类数据", paramType = "body", dataType = "String")
	})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult updateSpecForField(@ApiParam(value = "specId", required = true)@PathVariable("specId")String specId,
                                            @RequestBody String data) {
		logger.info("receive:[specId:"+specId+"-:deleteata:"+data+"]");
		try {
			SpecDO specDO = specService.updateSpecField(data, Long.valueOf(specId));
			return RespondResult.success("更新部分数据成功",specDO);
		}catch (Exception e){
		return RespondResult.error("更新部分数据失败");
		}
     }
	
    /**
     * 根据Id删除数据
     * @param specDOId specDOId
     * @return GetResult<Boolean>(suatus:状态码,msg:消息,data:处理结果)
     */
	@DeleteMapping("/{specId}")
	@RequiresPermissions({"spec:list", "spec:delete"})
	@ApiOperation(value = "根据Id删除数据",httpMethod = "DELETE",notes = "用于通过specDOId，删除s_spec表中对应的一条数据，为异步方法，结果会回调到异步地址中")
	@ApiImplicitParam(name = "specId", value = "specId", paramType = "path", dataType = "String")
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult deleteSpecDOById(@ApiParam(value = "specIdListString", required = true)@PathVariable("specId")String specId) {
		Boolean flag = null;
		logger.info("receive:[specId:"+specId+"]");
        try {
			flag = specService.deleteSpecById(specId);
        	return RespondResult.success("删除成功",flag);
        }catch (Exception e){
        	return RespondResult.error("删除失败");
        }

	}	
}