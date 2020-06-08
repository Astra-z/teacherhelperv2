package com.spm.teacherhelperv2.controller;

import com.alibaba.fastjson.JSONObject;
import com.spm.teacherhelperv2.entity.NoteDO;
import com.spm.teacherhelperv2.manager.RespondResult;
import com.spm.teacherhelperv2.service.NoteService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  NoteController前端控制器
 * @author zhangjr
 * @since 2020-06-08 16:33:17
 */

@RestController
@RequestMapping("${global.version}/notes")
@Api(tags = "s_note表操作API")	
public class NoteController {
    private Logger logger = LoggerFactory.getLogger(NoteController.class);
    @Autowired
	@Qualifier("NoteService")
    private NoteService noteService;	
	
	/**
	 * 获取满足某些条件的全部数据列表 
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @param page 页数
	 * @param limit 每页限制条数
	 * @return noteDO实体类数据列表
	 */
	@GetMapping("/")
//	@RequiresPermissions({ "note:list" })
	@ApiOperation(value = "获取满足某些条件的全部数据列", httpMethod = "GET", notes = "用于通过指定条件,查询s_note表对应所用数据")
	@ApiImplicitParams({ @ApiImplicitParam(name = "page", value = "请求页码", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "limit", value = "每页数据条数", paramType = "query", dataType = "String"), 
			@ApiImplicitParam(name = "fieldValue", value = "查询条件值", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "fieldName", value = "查询条件值属性名", paramType = "query", dataType = "String")})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
			@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
			@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult listNoteByOther(@RequestParam(value = "page", required = false) String page,
                                         @RequestParam(value = "limit", required = false) String limit,
                                         @RequestParam(value = "fieldValue", required = false) String fieldValue,
                                         @RequestParam(value = "fieldName", required = false) String fieldName) {
		logger.info("receive:[page:"+page+"--limit:"+limit+"--fieldValue:"+fieldValue+"--fieldName:"+fieldName+"]");
		try {
			List<NoteDO> notes = noteService.listNoteByOther(fieldValue, fieldName, page, limit);
			return RespondResult.success("查找成功",notes);
		}catch (Exception e){
    		return RespondResult.error("查找失败");
    	}


	}


    /**
     * 根据ID查找数据
     * @return noteDO实体类数据
     */
	@GetMapping("/{noteId}")
//	@RequiresPermissions({"note:list" })
    @ApiOperation(value = "根据ID查找数据",httpMethod = "GET",notes = "用于通过noteId，查询s_note表中对应的一条数据")
	@ApiImplicitParam(name = "noteId", value = "noteId", paramType = "path", dataType = "String")
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult getNoteById(@ApiParam(value = "noteId", required = true)@PathVariable("noteId")String noteId) {
		logger.info("receive:[noteId:"+noteId+"]");
		try {
			NoteDO noteDO = noteService.getNoteById(Long.valueOf(noteId));
			return RespondResult.success("查找成功",noteDO);
		}catch (Exception e){
    		return RespondResult.error("查找失败");
    	}
	}
	
	/**
	 * 根据其他查找数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @return noteDO实体类数据
	 */
	@GetMapping("/note")
//	@RequiresPermissions({ "note:list" })
	@ApiOperation(value = "根据其他查找数据", httpMethod = "GET", notes = "用于通过指定字段，查询uuuec_user表中对应的一条数据")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "fieldValue", value = "查询条件值", paramType = "query", dataType = "String"),
	@ApiImplicitParam(name = "fieldName", value = "查询条件值属性名", paramType = "query", dataType = "String")
	})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
			@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
			@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult getNoteByOther(@RequestParam(value = "fieldValue", required = false) String fieldValue
			, @RequestParam(value = "fieldName", required = false) String fieldName) {
		logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"]");
		try {
			NoteDO noteDO = noteService.getNoteByOther(fieldValue,fieldName);
			return RespondResult.success("查找成功",noteDO);
		}catch (Exception e){
    		return RespondResult.error("查找失败");
    	}

	}
	
    /**
     * 添加数据
     * @return noteDO实体类数据
     */
	@PostMapping("/")
//	@RequiresPermissions({ "note:list", "note:add"})
	@ApiOperation(value = "添加数据",httpMethod = "POST",notes = "用于在s_note表中插入对应的一条数据，为异步方法，结果会回调到异步地址中\n;"
			+ "NoteDO实体类数据{\n"
			+ "变量名：\"noteName\",类型：String,注释：\n,"
			+ "变量名：\"noteType\",类型：Boolean,注释：\n,"
			+ "变量名：\"userId\",类型：Integer,注释：\n,"
			+ "变量名：\"remark\",类型：String,注释：\n,"
			+ "变量名：\"fileid\",类型：String,注释：\n,"
	        + "}\n")
	@ApiImplicitParam(name = "data", value = "noteDO实体类数据", paramType = "body", dataType = "String")
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult insertNote(@RequestBody(required=true)String data) {
		logger.info("receive:[data:"+data+"]");
		try {
			NoteDO noteDO= JSONObject.parseObject(data, NoteDO.class);
			noteService.insertNote(noteDO);
			return RespondResult.success("添加数据成功",noteDO);
		}catch (Exception e){
    		return RespondResult.error("添加数据失败");
    	}

	}

	
    /**
     * 更新数据
     * @param data noteDO实体类数据
     * @return noteDO实体类数据
     */
	@PutMapping("/{noteId}")
	@RequiresPermissions({"note:list", "note:update"})
	@ApiOperation(value = "更新数据",httpMethod = "PUT",notes = "用于更新s_note表中对应的一条数据，为异步方法，结果会回调到异步地址中\n"
			+ "NoteDO实体类数据{\n"
			+ "变量名：\"noteName\",类型：String,注释：\n,"
			+ "变量名：\"noteType\",类型：Boolean,注释：\n,"
			+ "变量名：\"userId\",类型：Integer,注释：\n,"
			+ "变量名：\"remark\",类型：String,注释：\n,"
			+ "变量名：\"fileid\",类型：String,注释：\n,"
	        + "}\n")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "noteId", value = "noteId", paramType = "path", dataType = "String"),
	@ApiImplicitParam(name = "data", value = "noteDO实体类数据", paramType = "body", dataType = "String")
	})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult updateNoteDO(@RequestBody(required=true)String data) {
		logger.info("receive:[data:"+data+"]");
		try {
			NoteDO noteDO= JSONObject.parseObject(data, NoteDO.class);
			noteDO = noteService.updateNote(noteDO);
			return RespondResult.success("更新数据成功",noteDO);
		}catch (Exception e){
    		return RespondResult.error("更新数据失败");
    	}

     }

    /**
     * 更新部分数据
     * @param noteId noteId
     * @param data noteDO部分信息
     * @return GetResult<Boolean>(suatus:状态码,msg:消息,data:处理结果)
     */
	@PatchMapping("/{noteId}")
	@RequiresPermissions({ "note:list", "note:update"})
	@ApiOperation(value = "更新部分数据",httpMethod = "PATCH",notes = "用于更新s_note表中对应的一条数据，为异步方法，结果会回调到异步地址中")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "noteId", value = "noteId", paramType = "path", dataType = "String"),
	@ApiImplicitParam(name = "data", value = "noteDO实体类数据", paramType = "body", dataType = "String")
	})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult updateNoteForField(@ApiParam(value = "noteId", required = true)@PathVariable("noteId")String noteId,
                                            @RequestParam(value = "data",required=false) String data) {
		logger.info("receive:[noteId:"+noteId+"-:deleteata:"+data+"]");
		try {
			NoteDO noteDO = noteService.updateNoteField(data, Long.valueOf(noteId));
			return RespondResult.success("更新部分数据成功",noteDO);
		}catch (Exception e){
		return RespondResult.error("更新部分数据失败");
		}
     }
	
    /**
     * 根据Id删除数据
     * @param noteDOId noteDOId
     * @return GetResult<Boolean>(suatus:状态码,msg:消息,data:处理结果)
     */
	@DeleteMapping("/{noteId}")
	@RequiresPermissions({"note:list", "note:delete"})
	@ApiOperation(value = "根据Id删除数据",httpMethod = "DELETE",notes = "用于通过noteDOId，删除s_note表中对应的一条数据，为异步方法，结果会回调到异步地址中")
	@ApiImplicitParam(name = "noteId", value = "noteId", paramType = "path", dataType = "String")
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RespondResult deleteNoteDOById(@ApiParam(value = "noteIdListString", required = true)@PathVariable("noteId")String noteId) {
		Boolean flag = null;
		logger.info("receive:[noteId:"+noteId+"]");
        try {
			flag = noteService.deleteNoteById(noteId);
        	return RespondResult.success("删除成功",flag);
        }catch (Exception e){
        	return RespondResult.error("删除失败");
        }

	}	
}