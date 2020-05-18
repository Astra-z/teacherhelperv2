package com.spm.teacherhelperv2.controller;

import com.alibaba.fastjson.JSONObject;
import com.spm.teacherhelperv2.entity.CollegeDO;
import com.spm.teacherhelperv2.service.CollegeService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  CollegeController前端控制器
 * @author zhangjr
 * @since 2020-05-15 22:42:58
 */

@RestController
@RequestMapping("${global.version}/colleges")
@Api(tags = "s_college表操作API")	
public class CollegeController {
    private Logger logger = LoggerFactory.getLogger(CollegeController.class);
    @Autowired
    @Qualifier("CollegeService")
    private CollegeService collegeService;	
	
	/**
	 * 获取满足某些条件的全部数据列表 
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @param page 页数
	 * @param limit 每页限制条数
	 * @return collegeDO实体类数据列表
	 */
	@GetMapping("/")
	@RequiresPermissions({ "college-r" })
	@ApiOperation(value = "获取满足某些条件的全部数据列", httpMethod = "GET", notes = "用于通过指定条件,查询s_college表对应所用数据")
	@ApiImplicitParams({ @ApiImplicitParam(name = "page", value = "请求页码", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "limit", value = "每页数据条数", paramType = "query", dataType = "String"), 
			@ApiImplicitParam(name = "fieldValue", value = "查询条件值", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "fieldName", value = "查询条件值属性名", paramType = "query", dataType = "String")})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
			@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
			@ApiResponse(code = 555, message = "请求超时，请重试") })
	public List<CollegeDO> listCollegeByOther(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "limit", required = false) String limit,
			@RequestParam(value = "fieldValue", required = false) String fieldValue,
			@RequestParam(value = "fieldName", required = false) String fieldName) {
		logger.info("receive:[page:"+page+"--limit:"+limit+"--fieldValue:"+fieldValue+"--fieldName:"+fieldName+"]");
		List<CollegeDO> colleges = collegeService.listCollegeByOther(fieldValue, fieldName, page, limit);
		return colleges;
	}
	
    /**
     * 根据ID查找数据
     * @return collegeDO实体类数据
     */
	@GetMapping("/{collegeId}")
	@RequiresPermissions({"college-r" })
    @ApiOperation(value = "根据ID查找数据",httpMethod = "GET",notes = "用于通过collegeId，查询s_college表中对应的一条数据")
	@ApiImplicitParam(name = "collegeId", value = "collegeId", paramType = "path", dataType = "String")
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public CollegeDO getCollegeById(@ApiParam(value = "collegeId", required = true)@PathVariable("collegeId")String collegeId) {	
		logger.info("receive:[collegeId:"+collegeId+"]");
		CollegeDO collegeDO = collegeService.getCollegeById(Long.valueOf(collegeId));		    
		return collegeDO;		
	}
	
	/**
	 * 根据其他查找数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @return collegeDO实体类数据
	 */
	@GetMapping("/college")
	@RequiresPermissions({ "college-r" })
	@ApiOperation(value = "根据其他查找数据", httpMethod = "GET", notes = "用于通过指定字段，查询uuuec_user表中对应的一条数据")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "fieldValue", value = "查询条件值", paramType = "query", dataType = "String"),
	@ApiImplicitParam(name = "fieldName", value = "查询条件值属性名", paramType = "query", dataType = "String")
	})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
			@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
			@ApiResponse(code = 555, message = "请求超时，请重试") })
	public CollegeDO getCollegeByOther(@RequestParam(value = "fieldValue", required = false) String fieldValue
			,@RequestParam(value = "fieldName", required = false) String fieldName) {
		logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"]");
		CollegeDO collegeDO = collegeService.getCollegeByOther(fieldValue,fieldName);
		return collegeDO;
	}
	
    /**
     * 添加数据
     * @return collegeDO实体类数据
     */
	@PostMapping("/")
	@RequiresPermissions({ "college-r", "college-c"})
	@ApiOperation(value = "添加数据",httpMethod = "POST",notes = "用于在s_college表中插入对应的一条数据，为异步方法，结果会回调到异步地址中\n;"
			+ "CollegeDO实体类数据{\n"
			+ "变量名：\"collegeName\",类型：String,注释：\n,"
	        + "}\n")
    @ApiImplicitParam(name = "data", value = "collegeDO实体类数据", paramType = "body", dataType = "String")
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public CollegeDO insertCollege(@RequestBody(required=true)String data) {
		logger.info("receive:[data:"+data+"]");		
		CollegeDO collegeDO=JSONObject.parseObject(data, CollegeDO.class);	 
		collegeService.insertCollege(collegeDO);		
		return collegeDO;		
	}

	
    /**
     * 更新数据
     * @param data collegeDO实体类数据
     * @return collegeDO实体类数据
     */
	@PutMapping("/{collegeId}")
	@RequiresPermissions({"college-r", "college-u"})
	@ApiOperation(value = "更新数据",httpMethod = "PUT",notes = "用于更新s_college表中对应的一条数据，为异步方法，结果会回调到异步地址中\n"
			+ "CollegeDO实体类数据{\n"
			+ "变量名：\"collegeName\",类型：String,注释：\n,"
	        + "}\n")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "collegeId", value = "collegeId", paramType = "path", dataType = "String"),
	@ApiImplicitParam(name = "data", value = "collegeDO实体类数据", paramType = "body", dataType = "String")
	})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public CollegeDO updateCollegeDO(@RequestBody(required=true)String data) {
		logger.info("receive:[data:"+data+"]");
		CollegeDO collegeDO=JSONObject.parseObject(data, CollegeDO.class);	    
		collegeDO = collegeService.updateCollege(collegeDO);		
        return collegeDO;
     }

    /**
     * 更新部分数据
     * @param collegeId collegeId
     * @param data collegeDO部分信息
     * @return GetResult<Boolean>(suatus:状态码,msg:消息,data:处理结果)
     */
	@PatchMapping("/{collegeId}")
	@RequiresPermissions({ "college-r", "college-u"})
	@ApiOperation(value = "更新部分数据",httpMethod = "PATCH",notes = "用于更新s_college表中对应的一条数据，为异步方法，结果会回调到异步地址中")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "collegeId", value = "collegeId", paramType = "path", dataType = "String"),
	@ApiImplicitParam(name = "data", value = "collegeDO实体类数据", paramType = "body", dataType = "String")
	})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public CollegeDO updateCollegeForField(@ApiParam(value = "collegeId", required = true)@PathVariable("collegeId")String collegeId,
			@RequestParam(value = "data",required=false) String data) {
		    logger.info("receive:[collegeId:"+collegeId+"--data:"+data+"]");		    		
			CollegeDO collegeDO = collegeService.updateCollegeField(data, Long.valueOf(collegeId));
        return collegeDO;
     }
	
    /**
     * 根据Id删除数据
     * @param collegeDOId collegeDOId
     * @return GetResult<Boolean>(suatus:状态码,msg:消息,data:处理结果)
     */
	@DeleteMapping("/{collegeId}")
//	@RequiresPermissions({"college-r", "college-d"})
	@ApiOperation(value = "根据Id删除数据",httpMethod = "DELETE",notes = "用于通过collegeDOId，删除s_college表中对应的一条数据，为异步方法，结果会回调到异步地址中")
	@ApiImplicitParam(name = "collegeId", value = "collegeId", paramType = "path", dataType = "String")
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public Boolean deleteCollegeDOById(@ApiParam(value = "collegeIdListString", required = true)@PathVariable("collegeId")String collegeId) {		
		Boolean flag = null;
		logger.info("receive:[collegeId:"+collegeId+"]");
		flag = collegeService.deleteCollegeById(collegeId);
		return flag;		
	}	
}