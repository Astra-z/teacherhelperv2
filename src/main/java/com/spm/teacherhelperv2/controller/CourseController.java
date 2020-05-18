package com.spm.teacherhelperv2.controller;

import com.alibaba.fastjson.JSONObject;
import com.spm.teacherhelperv2.entity.CourseDO;
import com.spm.teacherhelperv2.service.CourseService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  CourseController前端控制器
 * @author zhangjr
 * @since 2020-05-15 22:42:58
 */

@RestController
@RequestMapping("${global.version}/courses")
@Api(tags = "s_course表操作API")	
public class CourseController {
    private Logger logger = LoggerFactory.getLogger(CourseController.class);
    @Autowired
	@Qualifier("CourseService")
    private CourseService courseService;	
	
	/**
	 * 获取满足某些条件的全部数据列表 
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @param page 页数
	 * @param limit 每页限制条数
	 * @return courseDO实体类数据列表
	 */
	@GetMapping("/")
	@RequiresPermissions({ "course-r" })
	@ApiOperation(value = "获取满足某些条件的全部数据列", httpMethod = "GET", notes = "用于通过指定条件,查询s_course表对应所用数据")
	@ApiImplicitParams({ @ApiImplicitParam(name = "page", value = "请求页码", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "limit", value = "每页数据条数", paramType = "query", dataType = "String"), 
			@ApiImplicitParam(name = "fieldValue", value = "查询条件值", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "fieldName", value = "查询条件值属性名", paramType = "query", dataType = "String")})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
			@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
			@ApiResponse(code = 555, message = "请求超时，请重试") })
	public List<CourseDO> listCourseByOther(@RequestParam(value = "page", required = false) String page,
                                            @RequestParam(value = "limit", required = false) String limit,
                                            @RequestParam(value = "fieldValue", required = false) String fieldValue,
                                            @RequestParam(value = "fieldName", required = false) String fieldName) {
		logger.info("receive:[page:"+page+"--limit:"+limit+"--fieldValue:"+fieldValue+"--fieldName:"+fieldName+"]");
		List<CourseDO> courses = courseService.listCourseByOther(fieldValue, fieldName, page, limit);
		return courses;
	}
	
    /**
     * 根据ID查找数据
     * @return courseDO实体类数据
     */
	@GetMapping("/{courseId}")
	@RequiresPermissions({"course-r" })
    @ApiOperation(value = "根据ID查找数据",httpMethod = "GET",notes = "用于通过courseId，查询s_course表中对应的一条数据")
	@ApiImplicitParam(name = "courseId", value = "courseId", paramType = "path", dataType = "String")
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public CourseDO getCourseById(@ApiParam(value = "courseId", required = true)@PathVariable("courseId")String courseId) {
		logger.info("receive:[courseId:"+courseId+"]");
		CourseDO courseDO = courseService.getCourseById(Long.valueOf(courseId));
		return courseDO;		
	}
	
	/**
	 * 根据其他查找数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @return courseDO实体类数据
	 */
	@GetMapping("/course")
	@RequiresPermissions({ "course-r" })
	@ApiOperation(value = "根据其他查找数据", httpMethod = "GET", notes = "用于通过指定字段，查询uuuec_user表中对应的一条数据")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "fieldValue", value = "查询条件值", paramType = "query", dataType = "String"),
	@ApiImplicitParam(name = "fieldName", value = "查询条件值属性名", paramType = "query", dataType = "String")
	})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
			@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
			@ApiResponse(code = 555, message = "请求超时，请重试") })
	public CourseDO getCourseByOther(@RequestParam(value = "fieldValue", required = false) String fieldValue
			, @RequestParam(value = "fieldName", required = false) String fieldName) {
		logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"]");
		CourseDO courseDO = courseService.getCourseByOther(fieldValue,fieldName);
		return courseDO;
	}
	
    /**
     * 添加数据
     * @return courseDO实体类数据
     */
	@PostMapping("/")
	@RequiresPermissions({ "course-r", "course-c"})
	@ApiOperation(value = "添加数据",httpMethod = "POST",notes = "用于在s_course表中插入对应的一条数据，为异步方法，结果会回调到异步地址中\n;"
			+ "CourseDO实体类数据{\n"
			+ "变量名：\"courseName\",类型：String,注释：\n,"
			+ "变量名：\"term\",类型：String,注释：\n,"
			+ "变量名：\"courseTeacher\",类型：String,注释：\n,"
			+ "变量名：\"specId\",类型：Integer,注释：\n,"
			+ "变量名：\"classId\",类型：Integer,注释：\n,"
	        + "}\n")
	@ApiImplicitParam(name = "data", value = "courseDO实体类数据", paramType = "body", dataType = "String")
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public CourseDO insertCourse(@RequestBody(required=true)String data) {
		logger.info("receive:[data:"+data+"]");		
		CourseDO courseDO=JSONObject.parseObject(data, CourseDO.class);
		courseService.insertCourse(courseDO);		
		return courseDO;		
	}

	
    /**
     * 更新数据
     * @param data courseDO实体类数据
     * @return courseDO实体类数据
     */
	@PutMapping("/{courseId}")
	@RequiresPermissions({"course-r", "course-u"})
	@ApiOperation(value = "更新数据",httpMethod = "PUT",notes = "用于更新s_course表中对应的一条数据，为异步方法，结果会回调到异步地址中\n"
			+ "CourseDO实体类数据{\n"
			+ "变量名：\"courseName\",类型：String,注释：\n,"
			+ "变量名：\"term\",类型：String,注释：\n,"
			+ "变量名：\"courseTeacher\",类型：String,注释：\n,"
			+ "变量名：\"specId\",类型：Integer,注释：\n,"
			+ "变量名：\"classId\",类型：Integer,注释：\n,"
	        + "}\n")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "courseId", value = "courseId", paramType = "path", dataType = "String"),
	@ApiImplicitParam(name = "data", value = "courseDO实体类数据", paramType = "body", dataType = "String")
	})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public CourseDO updateCourseDO(@RequestBody(required=true)String data) {
		logger.info("receive:[data:"+data+"]");
		CourseDO courseDO=JSONObject.parseObject(data, CourseDO.class);
		courseDO = courseService.updateCourse(courseDO);		
        return courseDO;
     }

    /**
     * 更新部分数据
     * @param courseId courseId
     * @param data courseDO部分信息
     * @return RespondResult<Boolean>(suatus:状态码,msg:消息,data:处理结果)
     */
	@PatchMapping("/{courseId}")
	@RequiresPermissions({ "course-r", "course-u"})
	@ApiOperation(value = "更新部分数据",httpMethod = "PATCH",notes = "用于更新s_course表中对应的一条数据，为异步方法，结果会回调到异步地址中")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "courseId", value = "courseId", paramType = "path", dataType = "String"),
	@ApiImplicitParam(name = "data", value = "courseDO实体类数据", paramType = "body", dataType = "String")
	})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public CourseDO updateCourseForField(@ApiParam(value = "courseId", required = true)@PathVariable("courseId")String courseId,
                                         @RequestParam(value = "data",required=false) String data) {
		    logger.info("receive:[courseId:"+courseId+"--data:"+data+"]");		    		
			CourseDO courseDO = courseService.updateCourseField(data, Long.valueOf(courseId));
        return courseDO;
     }
	
    /**
     * 根据Id删除数据
     * @param courseDOId courseDOId
     * @return RespondResult<Boolean>(suatus:状态码,msg:消息,data:处理结果)
     */
	@DeleteMapping("/{courseId}")
	@RequiresPermissions({"course-r", "course-d"})
	@ApiOperation(value = "根据Id删除数据",httpMethod = "DELETE",notes = "用于通过courseDOId，删除s_course表中对应的一条数据，为异步方法，结果会回调到异步地址中")
	@ApiImplicitParam(name = "courseId", value = "courseId", paramType = "path", dataType = "String")
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public Boolean deleteCourseDOById(@ApiParam(value = "courseIdListString", required = true)@PathVariable("courseId")String courseId) {		
		Boolean flag = null;
		logger.info("receive:[courseId:"+courseId+"]");
		flag = courseService.deleteCourseById(courseId);
		return flag;		
	}	
}