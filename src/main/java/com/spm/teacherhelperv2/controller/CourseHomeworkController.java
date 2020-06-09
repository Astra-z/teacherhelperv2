package com.spm.teacherhelperv2.controller;

import com.alibaba.fastjson.JSONObject;
import com.spm.teacherhelperv2.entity.CourseHomeworkDO;
import com.spm.teacherhelperv2.manager.RespondResult;
import com.spm.teacherhelperv2.service.CourseHomeworkService;
import io.swagger.annotations.*;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *  CourseHomeworkController前端控制器
 * @author zhangjr
 * @since 2020-06-07 10:43:26
 */

@RestController
@RequestMapping("${global.version}/coursehomeworks")
@Api(tags = "s_course_homework表操作API")	
public class CourseHomeworkController {
    private Logger logger = LoggerFactory.getLogger(CourseHomeworkController.class);
    @Autowired
	@Qualifier("CourseHomeworkService")
    private CourseHomeworkService courseHomeworkService;	
	
	/**
	 * 获取满足某些条件的全部数据列表 
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @param page 页数
	 * @param limit 每页限制条数
	 * @return courseHomeworkDO实体类数据列表
	 */
	@GetMapping("/")
	@RequiresPermissions({ "coursehomework:list" })
	@ApiOperation(value = "获取满足某些条件的全部数据列", httpMethod = "GET", notes = "用于通过指定条件,查询s_course_homework表对应所用数据")
	@ApiImplicitParams({ @ApiImplicitParam(name = "page", value = "请求页码", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "limit", value = "每页数据条数", paramType = "query", dataType = "String"), 
			@ApiImplicitParam(name = "fieldValue", value = "查询条件值", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "fieldName", value = "查询条件值属性名", paramType = "query", dataType = "String")})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
			@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
			@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult listCourseHomeworkByOther(@RequestParam(value = "page", required = false) String page,
                                                   @RequestParam(value = "limit", required = false) String limit,
                                                   @RequestParam(value = "fieldValue", required = false) String fieldValue,
                                                   @RequestParam(value = "fieldName", required = false) String fieldName) {
		logger.info("receive:[page:"+page+"--limit:"+limit+"--fieldValue:"+fieldValue+"--fieldName:"+fieldName+"]");
		try {
			List<CourseHomeworkDO> courseHomeworks = courseHomeworkService.listCourseHomeworkByOther(fieldValue, fieldName, page, limit);
			return RespondResult.success("查找成功",courseHomeworks);
		}catch (Exception e){
    		return RespondResult.error("查找失败");
    	}


	}


    /**
     * 根据ID查找数据
     * @return courseHomeworkDO实体类数据
     */
	@GetMapping("/{courseHomeworkId}")
	@RequiresPermissions({"coursehomework:list" })
    @ApiOperation(value = "根据ID查找数据",httpMethod = "GET",notes = "用于通过courseHomeworkId，查询s_course_homework表中对应的一条数据")
	@ApiImplicitParam(name = "courseHomeworkId", value = "courseHomeworkId", paramType = "path", dataType = "String")
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult getCourseHomeworkById(@ApiParam(value = "courseHomeworkId", required = true)@PathVariable("courseHomeworkId")String courseHomeworkId) {
		logger.info("receive:[courseHomeworkId:"+courseHomeworkId+"]");
		try {
			CourseHomeworkDO courseHomeworkDO = courseHomeworkService.getCourseHomeworkById(Long.valueOf(courseHomeworkId));
			return RespondResult.success("查找成功",courseHomeworkDO);
		}catch (Exception e){
    		return RespondResult.error("查找失败");
    	}
	}
	
	/**
	 * 根据其他查找数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @return courseHomeworkDO实体类数据
	 */
	@GetMapping("/coursehomework")
	@RequiresPermissions({ "coursehomework:list" })
	@ApiOperation(value = "根据其他查找数据", httpMethod = "GET", notes = "用于通过指定字段，查询uuuec_user表中对应的一条数据")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "fieldValue", value = "查询条件值", paramType = "query", dataType = "String"),
	@ApiImplicitParam(name = "fieldName", value = "查询条件值属性名", paramType = "query", dataType = "String")
	})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
			@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
			@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult getCourseHomeworkByOther(@RequestParam(value = "fieldValue", required = false) String fieldValue
			, @RequestParam(value = "fieldName", required = false) String fieldName) {
		logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"]");
		try {
			CourseHomeworkDO courseHomeworkDO = courseHomeworkService.getCourseHomeworkByOther(fieldValue,fieldName);
			return RespondResult.success("查找成功",courseHomeworkDO);
		}catch (Exception e){
    		return RespondResult.error("查找失败");
    	}

	}
	
    /**
     * 添加数据
     * @return courseHomeworkDO实体类数据
     */
	@PostMapping("/")
	@RequiresPermissions({ "coursehomework:list", "coursehomework:add"})
	@ApiOperation(value = "添加数据",httpMethod = "POST",notes = "用于在s_course_homework表中插入对应的一条数据，为异步方法，结果会回调到异步地址中\n;"
			+ "CourseHomeworkDO实体类数据{\n"
			+ "变量名：\"courseId\",类型：Integer,注释：\n,"
			+ "变量名：\"courseHomeworkName\",类型：String,注释：\n,"
			+ "变量名：\"remark\",类型：String,注释：\n,"
	        + "}\n")
	@ApiImplicitParam(name = "data", value = "courseHomeworkDO实体类数据", paramType = "body", dataType = "String")
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult insertCourseHomework(@RequestBody(required=true)String data) {
		logger.info("receive:[data:"+data+"]");
		try {
			CourseHomeworkDO courseHomeworkDO= JSONObject.parseObject(data, CourseHomeworkDO.class);
			courseHomeworkService.insertCourseHomework(courseHomeworkDO);
			return RespondResult.success("添加数据成功",courseHomeworkDO);
		}catch (Exception e){
    		return RespondResult.error("添加数据失败");
    	}

	}

	
    /**
     * 更新数据
     * @param data courseHomeworkDO实体类数据
     * @return courseHomeworkDO实体类数据
     */
	@PutMapping("/{courseHomeworkId}")
	@RequiresPermissions({"coursehomework:list", "coursehomework:update"})
	@ApiOperation(value = "更新数据",httpMethod = "PUT",notes = "用于更新s_course_homework表中对应的一条数据，为异步方法，结果会回调到异步地址中\n"
			+ "CourseHomeworkDO实体类数据{\n"
			+ "变量名：\"courseId\",类型：Integer,注释：\n,"
			+ "变量名：\"courseHomeworkName\",类型：String,注释：\n,"
			+ "变量名：\"remark\",类型：String,注释：\n,"
	        + "}\n")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "courseHomeworkId", value = "courseHomeworkId", paramType = "path", dataType = "String"),
	@ApiImplicitParam(name = "data", value = "courseHomeworkDO实体类数据", paramType = "body", dataType = "String")
	})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult updateCourseHomeworkDO(@RequestBody(required=true)String data) {
		logger.info("receive:[data:"+data+"]");
		try {
			CourseHomeworkDO courseHomeworkDO= JSONObject.parseObject(data, CourseHomeworkDO.class);
			courseHomeworkDO = courseHomeworkService.updateCourseHomework(courseHomeworkDO);
			return RespondResult.success("更新数据成功",courseHomeworkDO);
		}catch (Exception e){
    		return RespondResult.error("更新数据失败");
    	}

     }

    /**
     * 更新部分数据
     * @param courseHomeworkId courseHomeworkId
     * @param data courseHomeworkDO部分信息
     * @return GetResult<Boolean>(suatus:状态码,msg:消息,data:处理结果)
     */
	@PatchMapping("/{courseHomeworkId}")
	@RequiresPermissions({ "coursehomework:list", "coursehomework:update"})
	@ApiOperation(value = "更新部分数据",httpMethod = "PATCH",notes = "用于更新s_course_homework表中对应的一条数据，为异步方法，结果会回调到异步地址中")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "courseHomeworkId", value = "courseHomeworkId", paramType = "path", dataType = "String"),
	@ApiImplicitParam(name = "data", value = "courseHomeworkDO实体类数据", paramType = "body", dataType = "String")
	})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult updateCourseHomeworkForField(@ApiParam(value = "courseHomeworkId", required = true)@PathVariable("courseHomeworkId")String courseHomeworkId,
                                                      @RequestParam(value = "data",required=false) String data) {
		logger.info("receive:[courseHomeworkId:"+courseHomeworkId+"-:deleteata:"+data+"]");
		try {
			CourseHomeworkDO courseHomeworkDO = courseHomeworkService.updateCourseHomeworkField(data, Long.valueOf(courseHomeworkId));
			return RespondResult.success("更新部分数据成功",courseHomeworkDO);
		}catch (Exception e){
		return RespondResult.error("更新部分数据失败");
		}
     }
	
    /**
     * 根据Id删除数据
     * @param  courseHomeworkDOId
     * @return GetResult<Boolean>(suatus:状态码,msg:消息,data:处理结果)
     */
	@DeleteMapping("/{courseHomeworkId}")
	@RequiresPermissions({"coursehomework:list", "coursehomework:delete"})
	@ApiOperation(value = "根据Id删除数据",httpMethod = "DELETE",notes = "用于通过courseHomeworkDOId，删除s_course_homework表中对应的一条数据，为异步方法，结果会回调到异步地址中")
	@ApiImplicitParam(name = "courseHomeworkId", value = "courseHomeworkId", paramType = "path", dataType = "String")
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult deleteCourseHomeworkDOById(@ApiParam(value = "courseHomeworkIdListString", required = true)@PathVariable("courseHomeworkId")String courseHomeworkId) {
		Boolean flag = null;
		logger.info("receive:[courseHomeworkId:"+courseHomeworkId+"]");
        try {
			flag = courseHomeworkService.deleteCourseHomeworkById(courseHomeworkId);
        	return RespondResult.success("删除成功",flag);
        }catch (Exception e){
        	return RespondResult.error("删除失败");
        }
	}



	@GetMapping("/filesdownloads")
	public ResponseEntity<byte[]> fileDownloads(@RequestParam("fileName")String filename,
												@RequestParam("courseHomeworkId")String courseHomeworkId,
												@RequestParam("courseId")String courseId) throws IOException {
		String[] names=filename.split("-");
		String doenLoadPath  = "F:\\IJ\\teacherhelperv2\\courses\\"+courseId+"\\"+courseHomeworkId+"\\"+names[0]+"\\"+names[1];
		File file = new File(doenLoadPath);
		if(file.exists()){
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("attachment", file.getName());
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.OK);
		}else{
			System.out.println("文件不存在,请重试...");
			return null;
		}
	}
}