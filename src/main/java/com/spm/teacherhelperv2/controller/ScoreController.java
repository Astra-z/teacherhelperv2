package com.spm.teacherhelperv2.controller;

import com.alibaba.fastjson.JSONObject;
import com.spm.teacherhelperv2.entity.ScoreDO;
import com.spm.teacherhelperv2.manager.RespondResult;
import com.spm.teacherhelperv2.service.ScoreService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  ScoreController前端控制器
 * @author zhangjr
 * @since 2020-05-18 13:13:11
 */

@RestController
@RequestMapping("${global.version}/scores")
@Api(tags = "s_score表操作API")	
public class ScoreController {
    private Logger logger = LoggerFactory.getLogger(ScoreController.class);
    @Autowired
	@Qualifier("ScoreService")
    private ScoreService scoreService;
	
	/**
	 * 获取满足某些条件的全部数据列表 
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @param page 页数
	 * @param limit 每页限制条数
	 * @return scoreDO实体类数据列表
	 */
	@GetMapping("/")
	@RequiresPermissions({ "score:list" })
	@ApiOperation(value = "获取满足某些条件的全部数据列", httpMethod = "GET", notes = "用于通过指定条件,查询s_score表对应所用数据")
	@ApiImplicitParams({ @ApiImplicitParam(name = "page", value = "请求页码", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "limit", value = "每页数据条数", paramType = "query", dataType = "String"), 
			@ApiImplicitParam(name = "fieldValue", value = "查询条件值", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "fieldName", value = "查询条件值属性名", paramType = "query", dataType = "String")})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
			@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
			@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult listScoreByOther(@RequestParam(value = "page", required = false) String page,
                                          @RequestParam(value = "limit", required = false) String limit,
                                          @RequestParam(value = "fieldValue", required = false) String fieldValue,
                                          @RequestParam(value = "fieldName", required = false) String fieldName) {
		logger.info("receive:[page:"+page+"--limit:"+limit+"--fieldValue:"+fieldValue+"--fieldName:"+fieldName+"]");
		try {
			List<ScoreDO> scores = scoreService.listScoreByOther(fieldValue, fieldName, page, limit);
			return RespondResult.success("查找成功",scores);
		}catch (Exception e){
    		return RespondResult.error("查找失败");
    	}


	}


    /**
     * 根据ID查找数据
     * @return scoreDO实体类数据
     */
	@GetMapping("/{scoreId}")
	@RequiresPermissions({"score:list" })
    @ApiOperation(value = "根据ID查找数据",httpMethod = "GET",notes = "用于通过scoreId，查询s_score表中对应的一条数据")
	@ApiImplicitParam(name = "scoreId", value = "scoreId", paramType = "path", dataType = "String")
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult getScoreById(@ApiParam(value = "scoreId", required = true)@PathVariable("scoreId")String scoreId) {
		logger.info("receive:[scoreId:"+scoreId+"]");
		try {
			ScoreDO scoreDO = scoreService.getScoreById(Long.valueOf(scoreId));
			return RespondResult.success("查找成功",scoreDO);
		}catch (Exception e){
    		return RespondResult.error("查找失败");
    	}
	}
	
	/**
	 * 根据其他查找数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @return scoreDO实体类数据
	 */
	@GetMapping("/score")
	@RequiresPermissions({ "score:list" })
	@ApiOperation(value = "根据其他查找数据", httpMethod = "GET", notes = "用于通过指定字段，查询uuuec_user表中对应的一条数据")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "fieldValue", value = "查询条件值", paramType = "query", dataType = "String"),
	@ApiImplicitParam(name = "fieldName", value = "查询条件值属性名", paramType = "query", dataType = "String")
	})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
			@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
			@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult getScoreByOther(@RequestParam(value = "fieldValue", required = false) String fieldValue
			, @RequestParam(value = "fieldName", required = false) String fieldName) {
		logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"]");
		try {
			ScoreDO scoreDO = scoreService.getScoreByOther(fieldValue,fieldName);
			return RespondResult.success("查找成功",scoreDO);
		}catch (Exception e){
    		return RespondResult.error("查找失败");
    	}

	}
	
    /**
     * 添加数据
     * @return scoreDO实体类数据
     */
	@PostMapping("/")
	@RequiresPermissions({ "score:list", "score:add"})
	@ApiOperation(value = "添加数据",httpMethod = "POST",notes = "用于在s_score表中插入对应的一条数据，为异步方法，结果会回调到异步地址中\n;"
			+ "ScoreDO实体类数据{\n"
			+ "变量名：\"studentId\",类型：Integer,注释：\n,"
			+ "变量名：\"courseId\",类型：Integer,注释：\n,"
			+ "变量名：\"teacherId\",类型：Integer,注释：\n,"
			+ "变量名：\"classId\",类型：Integer,注释：\n,"
			+ "变量名：\"score\",类型：Float,注释：\n,"
	        + "}\n")
	@ApiImplicitParam(name = "data", value = "scoreDO实体类数据", paramType = "body", dataType = "String")
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult insertScore(@RequestBody(required=true)String data) {
		logger.info("receive:[data:"+data+"]");
		try {
			ScoreDO scoreDO= JSONObject.parseObject(data, ScoreDO.class);
			scoreService.insertScore(scoreDO);
			return RespondResult.success("添加数据成功",scoreDO);
		}catch (Exception e){
    		return RespondResult.error("添加数据失败");
    	}

	}

	
    /**
     * 更新数据
     * @param data scoreDO实体类数据
     * @return scoreDO实体类数据
     */
	@PutMapping("/{scoreId}")
	@RequiresPermissions({"score:list", "score:update"})
	@ApiOperation(value = "更新数据",httpMethod = "PUT",notes = "用于更新s_score表中对应的一条数据，为异步方法，结果会回调到异步地址中\n"
			+ "ScoreDO实体类数据{\n"
			+ "变量名：\"studentId\",类型：Integer,注释：\n,"
			+ "变量名：\"courseId\",类型：Integer,注释：\n,"
			+ "变量名：\"teacherId\",类型：Integer,注释：\n,"
			+ "变量名：\"classId\",类型：Integer,注释：\n,"
			+ "变量名：\"score\",类型：Float,注释：\n,"
	        + "}\n")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "scoreId", value = "scoreId", paramType = "path", dataType = "String"),
	@ApiImplicitParam(name = "data", value = "scoreDO实体类数据", paramType = "body", dataType = "String")
	})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult updateScoreDO(@RequestBody(required=true)String data) {
		logger.info("receive:[data:"+data+"]");
		try {
			ScoreDO scoreDO= JSONObject.parseObject(data, ScoreDO.class);
			scoreDO = scoreService.updateScore(scoreDO);
			return RespondResult.success("更新数据成功",scoreDO);
		}catch (Exception e){
    		return RespondResult.error("更新数据失败");
    	}

     }

    /**
     * 更新部分数据
     * @param scoreId scoreId
     * @param data scoreDO部分信息
     * @return GetResult<Boolean>(suatus:状态码,msg:消息,data:处理结果)
     */
	@PatchMapping("/{scoreId}")
	@RequiresPermissions({ "score:list", "score:update"})
	@ApiOperation(value = "更新部分数据",httpMethod = "PATCH",notes = "用于更新s_score表中对应的一条数据，为异步方法，结果会回调到异步地址中")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "scoreId", value = "scoreId", paramType = "path", dataType = "String"),
	@ApiImplicitParam(name = "data", value = "scoreDO实体类数据", paramType = "body", dataType = "String")
	})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult updateScoreForField(@ApiParam(value = "scoreId", required = true)@PathVariable("scoreId")String scoreId,
                                             @RequestBody String data) {
		logger.info("receive:[scoreId:"+scoreId+"-:deleteata:"+data+"]");
		try {
			ScoreDO scoreDO = scoreService.updateScoreField(data, Long.valueOf(scoreId));
			return RespondResult.success("更新部分数据成功",scoreDO);
		}catch (Exception e){
		return RespondResult.error("更新部分数据失败");
		}
     }
	
    /**
     * 根据Id删除数据
     * @param scoreDOId scoreDOId
     * @return GetResult<Boolean>(suatus:状态码,msg:消息,data:处理结果)
     */
	@DeleteMapping("/{scoreId}")
	@RequiresPermissions({"score:list", "score:delete"})
	@ApiOperation(value = "根据Id删除数据",httpMethod = "DELETE",notes = "用于通过scoreDOId，删除s_score表中对应的一条数据，为异步方法，结果会回调到异步地址中")
	@ApiImplicitParam(name = "scoreId", value = "scoreId", paramType = "path", dataType = "String")
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult deleteScoreDOById(@ApiParam(value = "scoreIdListString", required = true)@PathVariable("scoreId")String scoreId) {
		Boolean flag = null;
		logger.info("receive:[scoreId:"+scoreId+"]");
        try {
			flag = scoreService.deleteScoreById(scoreId);
        	return RespondResult.success("删除成功",flag);
        }catch (Exception e){
        	return RespondResult.error("删除失败");
        }

	}	
}