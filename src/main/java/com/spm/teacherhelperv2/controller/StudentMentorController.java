package com.spm.teacherhelperv2.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import com.spm.teacherhelperv2.entity.StudentMentorDO;
import com.spm.teacherhelperv2.manager.RespondResult;
import com.spm.teacherhelperv2.service.StudentMentorService;
import com.spm.teacherhelperv2.util.MyFileUtils;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *  StudentMentorController前端控制器
 * @author zhangjr
 * @since 2020-06-13 20:45:02
 */

@RestController
@RequestMapping("${global.version}/studentmentors")
@Api(tags = "s_student_mentor表操作API")	
public class StudentMentorController {
    private Logger logger = LoggerFactory.getLogger(StudentMentorController.class);
    @Autowired
	@Qualifier("StudentMentorService")
    private StudentMentorService studentMentorService;	
	
	/**
	 * 获取满足某些条件的全部数据列表 
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @param page 页数
	 * @param limit 每页限制条数
	 * @return studentMentorDO实体类数据列表
	 */
	@GetMapping("/")
//	@RequiresPermissions({ "studentmentor:list" })
	@ApiOperation(value = "获取满足某些条件的全部数据列", httpMethod = "GET", notes = "用于通过指定条件,查询s_student_mentor表对应所用数据")
	@ApiImplicitParams({ @ApiImplicitParam(name = "page", value = "请求页码", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "limit", value = "每页数据条数", paramType = "query", dataType = "String"), 
			@ApiImplicitParam(name = "fieldValue", value = "查询条件值", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "fieldName", value = "查询条件值属性名", paramType = "query", dataType = "String")})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
			@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
			@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult listStudentMentorByOther(@RequestParam(value = "page", required = false) String page,
                                                  @RequestParam(value = "limit", required = false) String limit,
                                                  @RequestParam(value = "fieldValue", required = false) String fieldValue,
                                                  @RequestParam(value = "fieldName", required = false) String fieldName) {
		logger.info("receive:[page:"+page+"--limit:"+limit+"--fieldValue:"+fieldValue+"--fieldName:"+fieldName+"]");
		try {
			List<StudentMentorDO> studentMentors = studentMentorService.listStudentMentorByOther(fieldValue, fieldName, page, limit);
			return RespondResult.success("查找成功",studentMentors);
		}catch (Exception e){
    		return RespondResult.error("查找失败");
    	}


	}


    /**
     * 根据ID查找数据
     * @return studentMentorDO实体类数据
     */
	@GetMapping("/{studentMentorId}")
	@RequiresPermissions({"studentmentor:list" })
    @ApiOperation(value = "根据ID查找数据",httpMethod = "GET",notes = "用于通过studentMentorId，查询s_student_mentor表中对应的一条数据")
	@ApiImplicitParam(name = "studentMentorId", value = "studentMentorId", paramType = "path", dataType = "String")
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult getStudentMentorById(@ApiParam(value = "studentMentorId", required = true)@PathVariable("studentMentorId")String studentMentorId) {
		logger.info("receive:[studentMentorId:"+studentMentorId+"]");
		try {
			List<StudentMentorDO> studentMentorDO = studentMentorService.findAllMyStudent(Integer.valueOf(studentMentorId));
			return RespondResult.success("查找成功",studentMentorDO);
		}catch (Exception e){
    		return RespondResult.error("查找失败");
    	}
	}
	
	/**
	 * 根据其他查找数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @return studentMentorDO实体类数据
	 */
	@GetMapping("/studentmentor")
	@RequiresPermissions({ "studentmentor:list" })
	@ApiOperation(value = "根据其他查找数据", httpMethod = "GET", notes = "用于通过指定字段，查询uuuec_user表中对应的一条数据")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "fieldValue", value = "查询条件值", paramType = "query", dataType = "String"),
	@ApiImplicitParam(name = "fieldName", value = "查询条件值属性名", paramType = "query", dataType = "String")
	})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
			@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
			@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult getStudentMentorByOther(@RequestParam(value = "fieldValue", required = false) String fieldValue
			, @RequestParam(value = "fieldName", required = false) String fieldName) {
		logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"]");
		try {
			StudentMentorDO studentMentorDO = studentMentorService.getStudentMentorByOther(fieldValue,fieldName);
			return RespondResult.success("查找成功",studentMentorDO);
		}catch (Exception e){
    		return RespondResult.error("查找失败");
    	}

	}
	
    /**
     * 添加数据
     * @return studentMentorDO实体类数据
     */
	@PostMapping("/")
	@RequiresPermissions({ "studentmentor:list", "studentmentor:add"})
	@ApiOperation(value = "添加数据",httpMethod = "POST",notes = "用于在s_student_mentor表中插入对应的一条数据，为异步方法，结果会回调到异步地址中\n;"
			+ "StudentMentorDO实体类数据{\n"
			+ "变量名：\"mentorId\",类型：Integer,注释：\n,"
			+ "变量名：\"filePath\",类型：String,注释：\n,"
	        + "}\n")
	@ApiImplicitParam(name = "data", value = "studentMentorDO实体类数据", paramType = "body", dataType = "String")
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult insertStudentMentor(@RequestBody(required=true)String data) {
		logger.info("receive:[data:"+data+"]");
		try {
			StudentMentorDO studentMentorDO= JSONObject.parseObject(data, StudentMentorDO.class);
			studentMentorService.insertStudentMentor(studentMentorDO);
			return RespondResult.success("添加数据成功",studentMentorDO);
		}catch (Exception e){
    		return RespondResult.error("添加数据失败");
    	}

	}

	
    /**
     * 更新数据
     * @param data studentMentorDO实体类数据
     * @return studentMentorDO实体类数据
     */
	@PutMapping("/{studentMentorId}")
	@RequiresPermissions({"studentmentor:list", "studentmentor:update"})
	@ApiOperation(value = "更新数据",httpMethod = "PUT",notes = "用于更新s_student_mentor表中对应的一条数据，为异步方法，结果会回调到异步地址中\n"
			+ "StudentMentorDO实体类数据{\n"
			+ "变量名：\"mentorId\",类型：Integer,注释：\n,"
			+ "变量名：\"filePath\",类型：String,注释：\n,"
	        + "}\n")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "studentMentorId", value = "studentMentorId", paramType = "path", dataType = "String"),
	@ApiImplicitParam(name = "data", value = "studentMentorDO实体类数据", paramType = "body", dataType = "String")
	})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult updateStudentMentorDO(@RequestBody(required=true)String data) {
		logger.info("receive:[data:"+data+"]");
		try {
			StudentMentorDO studentMentorDO= JSONObject.parseObject(data, StudentMentorDO.class);
			studentMentorDO = studentMentorService.updateStudentMentor(studentMentorDO);
			return RespondResult.success("更新数据成功",studentMentorDO);
		}catch (Exception e){
    		return RespondResult.error("更新数据失败");
    	}

     }

    /**
     * 更新部分数据
     * @param studentMentorId studentMentorId
     * @param data studentMentorDO部分信息
     * @return GetResult<Boolean>(suatus:状态码,msg:消息,data:处理结果)
     */
	@PatchMapping("/{studentMentorId}")
	@RequiresPermissions({ "studentmentor:list", "studentmentor:update"})
	@ApiOperation(value = "更新部分数据",httpMethod = "PATCH",notes = "用于更新s_student_mentor表中对应的一条数据，为异步方法，结果会回调到异步地址中")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "studentMentorId", value = "studentMentorId", paramType = "path", dataType = "String"),
	@ApiImplicitParam(name = "data", value = "studentMentorDO实体类数据", paramType = "body", dataType = "String")
	})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult updateStudentMentorForField(@ApiParam(value = "studentMentorId", required = true)@PathVariable("studentMentorId")String studentMentorId,
                                                     @RequestParam(value = "data",required=false) String data) {
		logger.info("receive:[studentMentorId:"+studentMentorId+"-:deleteata:"+data+"]");
		try {
			StudentMentorDO studentMentorDO = studentMentorService.updateStudentMentorField(data, Long.valueOf(studentMentorId));
			return RespondResult.success("更新部分数据成功",studentMentorDO);
		}catch (Exception e){
		return RespondResult.error("更新部分数据失败");
		}
     }
	
    /**
     * 根据Id删除数据
     * @param studentMentorDOId studentMentorDOId
     * @return GetResult<Boolean>(suatus:状态码,msg:消息,data:处理结果)
     */
	@DeleteMapping("/{studentMentorId}")
	@RequiresPermissions({"studentmentor:list", "studentmentor:delete"})
	@ApiOperation(value = "根据Id删除数据",httpMethod = "DELETE",notes = "用于通过studentMentorDOId，删除s_student_mentor表中对应的一条数据，为异步方法，结果会回调到异步地址中")
	@ApiImplicitParam(name = "studentMentorId", value = "studentMentorId", paramType = "path", dataType = "String")
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult deleteStudentMentorDOById(@ApiParam(value = "studentMentorIdListString", required = true)@PathVariable("studentMentorId")String studentMentorId) {
		Boolean flag = null;
		logger.info("receive:[studentMentorId:"+studentMentorId+"]");
        try {
			flag = studentMentorService.deleteStudentMentorById(studentMentorId);
        	return RespondResult.success("删除成功",flag);
        }catch (Exception e){
        	return RespondResult.error("删除失败");
        }
	}

	@PostMapping("/sendNote")
	public RespondResult sendNote(@RequestBody String NoteList){
		JSONArray array=JSONArray.parseArray(NoteList);
		try {
			this.studentMentorService.sendNote(array);
			return RespondResult.success("发送提醒成功",null);
		}catch (Exception e){
			return RespondResult.error("发送提醒失败");
		}
	}

	@PostMapping("/uploadfile")
	@ResponseBody
	public RespondResult uploadCourseHomework(@RequestParam("file") MultipartFile file,
											  @RequestParam("mentorId") String mentorId,
											  @RequestParam("studentId") String studentId){
		Boolean flag;
		flag=this.studentMentorService.uploadfile(file,mentorId,studentId);
		if(flag){
			return RespondResult.success("上传成功",null);
		}
		else {
			return RespondResult.error("上传失败");
		}
	}

	@GetMapping("/studenfilelist")
	@ResponseBody
	public RespondResult getFileList(@RequestParam("mentorId")String mentorId,
										 @RequestParam(value = "studentId",required = false)String studentId){
		try {
			return RespondResult.success("查询成功",this.studentMentorService.getMyFileList(mentorId,studentId));
		}
		catch(Exception e) {
			return RespondResult.error("查询失败");
		}
	}

	@GetMapping("/filesdownloads")
	public ResponseEntity<byte[]> fileDownloads(@RequestParam("fileName")String filename,
												@RequestParam("mentorId") String mentorId,
												@RequestParam("studentId") String studentId) throws IOException {
		String doenLoadPath  = MyFileUtils.STUDENT_FILE_PATH +mentorId+"\\"+studentId+"\\"+filename;
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