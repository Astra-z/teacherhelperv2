package com.spm.teacherhelperv2.controller;

import com.alibaba.fastjson.JSONObject;
import com.spm.teacherhelperv2.entity.CourseFrequencyDO;
import com.spm.teacherhelperv2.service.CourseFrequencyService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  CourseFrequencyController前端控制器
 * @author zhangjr
 * @since 2020-05-15 22:42:58
 */

@RestController
@RequestMapping("${global.version}/coursefrequencys")
@Api(tags = "s_course_frequency表操作API")	
public class CourseFrequencyController {
    private Logger logger = LoggerFactory.getLogger(CourseFrequencyController.class);
    @Autowired
	@Qualifier("CourseFrequencyService")
    private CourseFrequencyService courseFrequencyService;	
	
	/**
	 * 获取满足某些条件的全部数据列表 
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @param page 页数
	 * @param limit 每页限制条数
	 * @return courseFrequencyDO实体类数据列表
	 */
	@GetMapping("/")
	@RequiresPermissions({ "coursefrequency-r" })
	@ApiOperation(value = "获取满足某些条件的全部数据列", httpMethod = "GET", notes = "用于通过指定条件,查询s_course_frequency表对应所用数据")
	@ApiImplicitParams({ @ApiImplicitParam(name = "page", value = "请求页码", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "limit", value = "每页数据条数", paramType = "query", dataType = "String"), 
			@ApiImplicitParam(name = "fieldValue", value = "查询条件值", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "fieldName", value = "查询条件值属性名", paramType = "query", dataType = "String")})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
			@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
			@ApiResponse(code = 555, message = "请求超时，请重试") })
	public List<CourseFrequencyDO> listCourseFrequencyByOther(@RequestParam(value = "page", required = false) String page,
                                                              @RequestParam(value = "limit", required = false) String limit,
                                                              @RequestParam(value = "fieldValue", required = false) String fieldValue,
                                                              @RequestParam(value = "fieldName", required = false) String fieldName) {
		logger.info("receive:[page:"+page+"--limit:"+limit+"--fieldValue:"+fieldValue+"--fieldName:"+fieldName+"]");
		List<CourseFrequencyDO> courseFrequencys = courseFrequencyService.listCourseFrequencyByOther(fieldValue, fieldName, page, limit);
		return courseFrequencys;
	}
	
    /**
     * 根据ID查找数据
     * @return courseFrequencyDO实体类数据
     */
	@GetMapping("/{courseFrequencyId}")
	@RequiresPermissions({"coursefrequency-r" })
    @ApiOperation(value = "根据ID查找数据",httpMethod = "GET",notes = "用于通过courseFrequencyId，查询s_course_frequency表中对应的一条数据")
	@ApiImplicitParam(name = "courseFrequencyId", value = "courseFrequencyId", paramType = "path", dataType = "String")
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public CourseFrequencyDO getCourseFrequencyById(@ApiParam(value = "courseFrequencyId", required = true)@PathVariable("courseFrequencyId")String courseFrequencyId) {
		logger.info("receive:[courseFrequencyId:"+courseFrequencyId+"]");
		CourseFrequencyDO courseFrequencyDO = courseFrequencyService.getCourseFrequencyById(Long.valueOf(courseFrequencyId));
		return courseFrequencyDO;		
	}
	
	/**
	 * 根据其他查找数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @return courseFrequencyDO实体类数据
	 */
	@GetMapping("/coursefrequency")
	@RequiresPermissions({ "coursefrequency-r" })
	@ApiOperation(value = "根据其他查找数据", httpMethod = "GET", notes = "用于通过指定字段，查询uuuec_user表中对应的一条数据")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "fieldValue", value = "查询条件值", paramType = "query", dataType = "String"),
	@ApiImplicitParam(name = "fieldName", value = "查询条件值属性名", paramType = "query", dataType = "String")
	})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
			@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
			@ApiResponse(code = 555, message = "请求超时，请重试") })
	public CourseFrequencyDO getCourseFrequencyByOther(@RequestParam(value = "fieldValue", required = false) String fieldValue
			, @RequestParam(value = "fieldName", required = false) String fieldName) {
		logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"]");
		CourseFrequencyDO courseFrequencyDO = courseFrequencyService.getCourseFrequencyByOther(fieldValue,fieldName);
		return courseFrequencyDO;
	}
	
    /**
     * 添加数据
     * @return courseFrequencyDO实体类数据
     */
	@PostMapping("/")
	@RequiresPermissions({ "coursefrequency-r", "coursefrequency-c"})
	@ApiOperation(value = "添加数据",httpMethod = "POST",notes = "用于在s_course_frequency表中插入对应的一条数据，为异步方法，结果会回调到异步地址中\n;"
			+ "CourseFrequencyDO实体类数据{\n"
			+ "变量名：\"courseid\",类型：Integer,注释：\n,"
			+ "变量名：\"weekday\",类型：Integer,注释：\n,"
			+ "变量名：\"startTime\",类型：LocalTime,注释：\n,"
			+ "变量名：\"endTime\",类型：LocalTime,注释：\n,"
	        + "}\n")
	@ApiImplicitParam(name = "data", value = "courseFrequencyDO实体类数据", paramType = "body", dataType = "String")
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public CourseFrequencyDO insertCourseFrequency(@RequestBody(required=true)String data) {
		logger.info("receive:[data:"+data+"]");		
		CourseFrequencyDO courseFrequencyDO=JSONObject.parseObject(data, CourseFrequencyDO.class);
		courseFrequencyService.insertCourseFrequency(courseFrequencyDO);		
		return courseFrequencyDO;		
	}

	
    /**
     * 更新数据
     * @param data courseFrequencyDO实体类数据
     * @return courseFrequencyDO实体类数据
     */
	@PutMapping("/{courseFrequencyId}")
	@RequiresPermissions({"coursefrequency-r", "coursefrequency-u"})
	@ApiOperation(value = "更新数据",httpMethod = "PUT",notes = "用于更新s_course_frequency表中对应的一条数据，为异步方法，结果会回调到异步地址中\n"
			+ "CourseFrequencyDO实体类数据{\n"
			+ "变量名：\"courseid\",类型：Integer,注释：\n,"
			+ "变量名：\"weekday\",类型：Integer,注释：\n,"
			+ "变量名：\"startTime\",类型：LocalTime,注释：\n,"
			+ "变量名：\"endTime\",类型：LocalTime,注释：\n,"
	        + "}\n")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "courseFrequencyId", value = "courseFrequencyId", paramType = "path", dataType = "String"),
	@ApiImplicitParam(name = "data", value = "courseFrequencyDO实体类数据", paramType = "body", dataType = "String")
	})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public CourseFrequencyDO updateCourseFrequencyDO(@RequestBody(required=true)String data) {
		logger.info("receive:[data:"+data+"]");
		CourseFrequencyDO courseFrequencyDO=JSONObject.parseObject(data, CourseFrequencyDO.class);
		courseFrequencyDO = courseFrequencyService.updateCourseFrequency(courseFrequencyDO);		
        return courseFrequencyDO;
     }

    /**
     * 更新部分数据
     * @param courseFrequencyId courseFrequencyId
     * @param data courseFrequencyDO部分信息
     * @return RespondResult<Boolean>(suatus:状态码,msg:消息,data:处理结果)
     */
	@PatchMapping("/{courseFrequencyId}")
	@RequiresPermissions({ "coursefrequency-r", "coursefrequency-u"})
	@ApiOperation(value = "更新部分数据",httpMethod = "PATCH",notes = "用于更新s_course_frequency表中对应的一条数据，为异步方法，结果会回调到异步地址中")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "courseFrequencyId", value = "courseFrequencyId", paramType = "path", dataType = "String"),
	@ApiImplicitParam(name = "data", value = "courseFrequencyDO实体类数据", paramType = "body", dataType = "String")
	})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public CourseFrequencyDO updateCourseFrequencyForField(@ApiParam(value = "courseFrequencyId", required = true)@PathVariable("courseFrequencyId")String courseFrequencyId,
                                                           @RequestParam(value = "data",required=false) String data) {
		    logger.info("receive:[courseFrequencyId:"+courseFrequencyId+"--data:"+data+"]");		    		
			CourseFrequencyDO courseFrequencyDO = courseFrequencyService.updateCourseFrequencyField(data, Long.valueOf(courseFrequencyId));
        return courseFrequencyDO;
     }
	
    /**
     * 根据Id删除数据
     * @param courseFrequencyDOId courseFrequencyDOId
     * @return RespondResult<Boolean>(suatus:状态码,msg:消息,data:处理结果)
     */
	@DeleteMapping("/{courseFrequencyId}")
	@RequiresPermissions({"coursefrequency-r", "coursefrequency-d"})
	@ApiOperation(value = "根据Id删除数据",httpMethod = "DELETE",notes = "用于通过courseFrequencyDOId，删除s_course_frequency表中对应的一条数据，为异步方法，结果会回调到异步地址中")
	@ApiImplicitParam(name = "courseFrequencyId", value = "courseFrequencyId", paramType = "path", dataType = "String")
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public Boolean deleteCourseFrequencyDOById(@ApiParam(value = "courseFrequencyIdListString", required = true)@PathVariable("courseFrequencyId")String courseFrequencyId) {		
		Boolean flag = null;
		logger.info("receive:[courseFrequencyId:"+courseFrequencyId+"]");
		flag = courseFrequencyService.deleteCourseFrequencyById(courseFrequencyId);
		return flag;		
	}	
}