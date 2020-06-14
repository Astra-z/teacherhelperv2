package com.spm.teacherhelperv2.controller;

import com.alibaba.fastjson.JSONObject;
import com.spm.teacherhelperv2.entity.CourseDO;
import com.spm.teacherhelperv2.manager.RespondResult;
import com.spm.teacherhelperv2.service.CourseService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *  CourseController前端控制器
 * @author zhangjr
 * @since 2020-05-18 13:13:11
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
	@RequiresPermissions({ "course:list" })
	@ApiOperation(value = "获取满足某些条件的全部数据列", httpMethod = "GET", notes = "用于通过指定条件,查询s_course表对应所用数据")
	@ApiImplicitParams({ @ApiImplicitParam(name = "page", value = "请求页码", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "limit", value = "每页数据条数", paramType = "query", dataType = "String"), 
			@ApiImplicitParam(name = "fieldValue", value = "查询条件值", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "fieldName", value = "查询条件值属性名", paramType = "query", dataType = "String")})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
			@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
			@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult listCourseByOther(@RequestParam(value = "page", required = false) String page,
                                           @RequestParam(value = "limit", required = false) String limit,
                                           @RequestParam(value = "fieldValue", required = false) String fieldValue,
                                           @RequestParam(value = "fieldName", required = false) String fieldName) {
		logger.info("receive:[page:"+page+"--limit:"+limit+"--fieldValue:"+fieldValue+"--fieldName:"+fieldName+"]");
		try {
			List<CourseDO> courses = courseService.listCourseByOther(fieldValue, fieldName, page, limit);
			return RespondResult.success("查找成功",courses);
		}catch (Exception e){
    		return RespondResult.error("查找失败");
    	}


	}


    /**
     * 根据ID查找数据
     * @return courseDO实体类数据
     */
	@GetMapping("/{courseId}")
	@RequiresPermissions({"course:list" })
    @ApiOperation(value = "根据ID查找数据",httpMethod = "GET",notes = "用于通过courseId，查询s_course表中对应的一条数据")
	@ApiImplicitParam(name = "courseId", value = "courseId", paramType = "path", dataType = "String")
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult getCourseById(@ApiParam(value = "courseId", required = true)@PathVariable("courseId")String courseId) {
		logger.info("receive:[courseId:"+courseId+"]");
		try {
			CourseDO courseDO = courseService.getCourseById(Long.valueOf(courseId));
			return RespondResult.success("查找成功",courseDO);
		}catch (Exception e){
    		return RespondResult.error("查找失败");
    	}
	}
	
	/**
	 * 根据其他查找数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @return courseDO实体类数据
	 */
	@GetMapping("/course")
	@RequiresPermissions({ "course:list" })
	@ApiOperation(value = "根据其他查找数据", httpMethod = "GET", notes = "用于通过指定字段，查询uuuec_user表中对应的一条数据")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "fieldValue", value = "查询条件值", paramType = "query", dataType = "String"),
	@ApiImplicitParam(name = "fieldName", value = "查询条件值属性名", paramType = "query", dataType = "String")
	})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
			@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
			@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult getCourseByOther(@RequestParam(value = "fieldValue", required = false) String fieldValue
			, @RequestParam(value = "fieldName", required = false) String fieldName) {
		logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"]");
		try {
			CourseDO courseDO = courseService.getCourseByOther(fieldValue,fieldName);
			return RespondResult.success("查找成功",courseDO);
		}catch (Exception e){
    		return RespondResult.error("查找失败");
    	}

	}
	
    /**
     * 添加数据
     * @return courseDO实体类数据
     */
	@PostMapping("/")
	@RequiresPermissions({ "course:list", "course:add"})
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
	public RespondResult insertCourse(@RequestBody(required=true)String data) {
		logger.info("receive:[data:"+data+"]");
		try {
			CourseDO courseDO= JSONObject.parseObject(data, CourseDO.class);
			courseService.insertCourse(courseDO);
			return RespondResult.success("添加数据成功",courseDO);
		}catch (Exception e){
    		return RespondResult.error("添加数据失败");
    	}
	}

	
    /**
     * 更新数据
     * @param data courseDO实体类数据
     * @return courseDO实体类数据
     */
	@PutMapping("/{courseId}")
	@RequiresPermissions({"course:list", "course:update"})
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
	public RespondResult updateCourseDO(@RequestBody(required=true)String data) {
		logger.info("receive:[data:"+data+"]");
		try {
			CourseDO courseDO= JSONObject.parseObject(data, CourseDO.class);
			courseDO = courseService.updateCourse(courseDO);
			return RespondResult.success("更新数据成功",courseDO);
		}catch (Exception e){
    		return RespondResult.error("更新数据失败");
    	}

     }

    /**
     * 更新部分数据
     * @param courseId courseId
     * @param data courseDO部分信息
     * @return GetResult<Boolean>(suatus:状态码,msg:消息,data:处理结果)
     */
	@PatchMapping("/{courseId}")
	@RequiresPermissions({ "course:list", "course:update"})
	@ApiOperation(value = "更新部分数据",httpMethod = "PATCH",notes = "用于更新s_course表中对应的一条数据，为异步方法，结果会回调到异步地址中")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "courseId", value = "courseId", paramType = "path", dataType = "String"),
	@ApiImplicitParam(name = "data", value = "courseDO实体类数据", paramType = "body", dataType = "String")
	})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult updateCourseForField(@ApiParam(value = "courseId", required = true)@PathVariable("courseId")String courseId,
                                              @RequestBody String data) {
		logger.info("receive:[courseId:"+courseId+"-:deleteata:"+data+"]");
		try {
			CourseDO courseDO = courseService.updateCourseField(data, Long.valueOf(courseId));
			return RespondResult.success("更新部分数据成功",courseDO);
		}catch (Exception e){
		return RespondResult.error("更新部分数据失败");
		}
     }
	
    /**
     * 根据Id删除数据
     * @param
     * @return GetResult<Boolean>(suatus:状态码,msg:消息,data:处理结果)
     */
	@DeleteMapping("/{courseId}")
	@RequiresPermissions({"course:list", "course:delete"})
	@ApiOperation(value = "根据Id删除数据",httpMethod = "DELETE",notes = "用于通过courseDOId，删除s_course表中对应的一条数据，为异步方法，结果会回调到异步地址中")
	@ApiImplicitParam(name = "courseId", value = "courseId", paramType = "path", dataType = "String")
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult deleteCourseDOById(@ApiParam(value = "courseIdListString", required = true)@PathVariable("courseId")String courseId) {
		Boolean flag = null;
		Boolean flag2=null;
		logger.info("receive:[courseId:"+courseId+"]");
        try {
			flag = courseService.deleteCourseById(courseId);

        	return RespondResult.success("删除成功",flag);
        }catch (Exception e){
        	return RespondResult.error("删除失败");
        }
	}

	@PostMapping("/uploadhomework")
	@ResponseBody
	public RespondResult uploadCourseHomework(@RequestParam("file") MultipartFile file,
											  @RequestParam("username") String username,
											  @RequestParam("courseId") String courseId,
											  @RequestParam("courseHomeworkId")String courseHomeworkId){
		Boolean flag=null;
		flag=this.courseService.uploadCourseHomework(file,courseId,courseHomeworkId,username);
		if(flag){
			return RespondResult.success("上传成功",null);
		}
		else {
			return RespondResult.error("上传失败");
		}
	}

	@GetMapping("/homeworklist")
	@ResponseBody
	public RespondResult gethomeworklist(@RequestParam("courseId")String courseId,
										 @RequestParam("courseHomeworkId")String courseHomeworkId,
										 @RequestParam(value = "username",required = false) String username){
		try {
			return RespondResult.success("查询成功",this.courseService.getMyCourseHomeworkList(courseId,courseHomeworkId,username));
		}
		catch(Exception e) {
			return RespondResult.error("查询失败");
		}
	}


	@GetMapping("/getMyCourse/{studentId}")
	@ResponseBody
	public RespondResult getMyCourselist(@PathVariable(value = "studentId")String studentId){
		try {
			return RespondResult.success("查询成功",this.courseService.getMyCourseList(studentId));
		}
		catch(Exception e) {
			return RespondResult.error("查询失败");
		}
	}

}