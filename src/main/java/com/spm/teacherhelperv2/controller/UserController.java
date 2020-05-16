package com.spm.teacherhelperv2.controller;

import com.alibaba.fastjson.JSONObject;
import com.spm.teacherhelperv2.entity.UserDO;
import com.spm.teacherhelperv2.service.UserService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  UserController前端控制器
 * @author zhangjr
 * @since 2020-05-15 22:42:58
 */

@RestController
@RequestMapping("${global.version}/users")
@Api(tags = "s_user表操作API")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
	@Qualifier("UserService")
    private UserService userService;
	
	/**
	 * 获取满足某些条件的全部数据列表 
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @param page 页数
	 * @param limit 每页限制条数
	 * @return userDO实体类数据列表
	 */
	@GetMapping("/")
	@RequiresPermissions({ "user-r" })
	@ApiOperation(value = "获取满足某些条件的全部数据列", httpMethod = "GET", notes = "用于通过指定条件,查询s_user表对应所用数据")
	@ApiImplicitParams({ @ApiImplicitParam(name = "page", value = "请求页码", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "limit", value = "每页数据条数", paramType = "query", dataType = "String"), 
			@ApiImplicitParam(name = "fieldValue", value = "查询条件值", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "fieldName", value = "查询条件值属性名", paramType = "query", dataType = "String")})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
			@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
			@ApiResponse(code = 555, message = "请求超时，请重试") })
	public List<UserDO> listUserByOther(@RequestParam(value = "page", required = false) String page,
                                        @RequestParam(value = "limit", required = false) String limit,
                                        @RequestParam(value = "fieldValue", required = false) String fieldValue,
                                        @RequestParam(value = "fieldName", required = false) String fieldName) {
		logger.info("receive:[page:"+page+"--limit:"+limit+"--fieldValue:"+fieldValue+"--fieldName:"+fieldName+"]");
		List<UserDO> users = userService.listUserByOther(fieldValue, fieldName, page, limit);
		return users;
	}
	
    /**
     * 根据ID查找数据
     * @return userDO实体类数据
     */
	@GetMapping("/{userId}")
	@RequiresPermissions({"user-r" })
    @ApiOperation(value = "根据ID查找数据",httpMethod = "GET",notes = "用于通过userId，查询s_user表中对应的一条数据")
	@ApiImplicitParam(name = "userId", value = "userId", paramType = "path", dataType = "String")
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public UserDO getUserById(@ApiParam(value = "userId", required = true)@PathVariable("userId")String userId) {
		logger.info("receive:[userId:"+userId+"]");
		UserDO userDO = userService.getUserById(Long.valueOf(userId));
		return userDO;		
	}
	
	/**
	 * 根据其他查找数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @return userDO实体类数据
	 */
	@GetMapping("/user")
	@RequiresPermissions({ "user-r" })
	@ApiOperation(value = "根据其他查找数据", httpMethod = "GET", notes = "用于通过指定字段，查询uuuec_user表中对应的一条数据")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "fieldValue", value = "查询条件值", paramType = "query", dataType = "String"),
	@ApiImplicitParam(name = "fieldName", value = "查询条件值属性名", paramType = "query", dataType = "String")
	})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
			@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
			@ApiResponse(code = 555, message = "请求超时，请重试") })
	public UserDO getUserByOther(@RequestParam(value = "fieldValue", required = false) String fieldValue
			, @RequestParam(value = "fieldName", required = false) String fieldName) {
		logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"]");
		UserDO userDO = userService.getUserByOther(fieldValue,fieldName);
		return userDO;
	}
	
    /**
     * 添加数据
     * @return userDO实体类数据
     */
	@PostMapping("/")
	@RequiresPermissions({ "user-r", "user-c"})
	@ApiOperation(value = "添加数据",httpMethod = "POST",notes = "用于在s_user表中插入对应的一条数据，为异步方法，结果会回调到异步地址中\n;"
			+ "UserDO实体类数据{\n"
			+ "变量名：\"sid\",类型：Long,注释：\n,"
			+ "变量名：\"username\",类型：String,注释：\n,"
			+ "变量名：\"password\",类型：String,注释：\n,"
			+ "变量名：\"realname\",类型：String,注释：\n,"
			+ "变量名：\"sex\",类型：String,注释：\n,"
			+ "变量名：\"birth\",类型：String,注释：\n,"
			+ "变量名：\"class\",类型：String,注释：\n,"
			+ "变量名：\"politicsStatus\",类型：String,注释：\n,"
			+ "变量名：\"email\",类型：String,注释：\n,"
			+ "变量名：\"adress\",类型：String,注释：\n,"
			+ "变量名：\"collegeId\",类型：Integer,注释：\n,"
			+ "变量名：\"specId\",类型：Integer,注释：\n,"
	        + "}\n")
	@ApiImplicitParam(name = "data", value = "userDO实体类数据", paramType = "body", dataType = "String")
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public UserDO insertUser(@RequestBody(required=true)String data) {
		logger.info("receive:[data:"+data+"]");		
		UserDO userDO=JSONObject.parseObject(data, UserDO.class);
		userService.insertUser(userDO);		
		return userDO;		
	}

	
    /**
     * 更新数据
     * @param data userDO实体类数据
     * @return userDO实体类数据
     */
	@PutMapping("/{userId}")
	@RequiresPermissions({"user-r", "user-u"})
	@ApiOperation(value = "更新数据",httpMethod = "PUT",notes = "用于更新s_user表中对应的一条数据，为异步方法，结果会回调到异步地址中\n"
			+ "UserDO实体类数据{\n"
			+ "变量名：\"sid\",类型：Long,注释：\n,"
			+ "变量名：\"username\",类型：String,注释：\n,"
			+ "变量名：\"password\",类型：String,注释：\n,"
			+ "变量名：\"realname\",类型：String,注释：\n,"
			+ "变量名：\"sex\",类型：String,注释：\n,"
			+ "变量名：\"birth\",类型：String,注释：\n,"
			+ "变量名：\"class\",类型：String,注释：\n,"
			+ "变量名：\"politicsStatus\",类型：String,注释：\n,"
			+ "变量名：\"email\",类型：String,注释：\n,"
			+ "变量名：\"adress\",类型：String,注释：\n,"
			+ "变量名：\"collegeId\",类型：Integer,注释：\n,"
			+ "变量名：\"specId\",类型：Integer,注释：\n,"
	        + "}\n")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "userId", value = "userId", paramType = "path", dataType = "String"),
	@ApiImplicitParam(name = "data", value = "userDO实体类数据", paramType = "body", dataType = "String")
	})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public UserDO updateUserDO(@RequestBody(required=true)String data) {
		logger.info("receive:[data:"+data+"]");
		UserDO userDO=JSONObject.parseObject(data, UserDO.class);
		userDO = userService.updateUser(userDO);		
        return userDO;
     }

    /**
     * 更新部分数据
     * @param userId userId
     * @param data userDO部分信息
     * @return GetResult<Boolean>(suatus:状态码,msg:消息,data:处理结果)
     */
	@PatchMapping("/{userId}")
	@RequiresPermissions({ "user-r", "user-u"})
	@ApiOperation(value = "更新部分数据",httpMethod = "PATCH",notes = "用于更新s_user表中对应的一条数据，为异步方法，结果会回调到异步地址中")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "userId", value = "userId", paramType = "path", dataType = "String"),
	@ApiImplicitParam(name = "data", value = "userDO实体类数据", paramType = "body", dataType = "String")
	})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public UserDO updateUserForField(@ApiParam(value = "userId", required = true)@PathVariable("userId")String userId,
                                     @RequestParam(value = "data",required=false) String data) {
		    logger.info("receive:[userId:"+userId+"--data:"+data+"]");		    		
			UserDO userDO = userService.updateUserField(data, Long.valueOf(userId));
        return userDO;
     }
	
    /**
     * 根据Id删除数据
     * @param userDOId userDOId
     * @return GetResult<Boolean>(suatus:状态码,msg:消息,data:处理结果)
     */
	@DeleteMapping("/{userId}")
	@RequiresPermissions({"user-r", "user-d"})
	@ApiOperation(value = "根据Id删除数据",httpMethod = "DELETE",notes = "用于通过userDOId，删除s_user表中对应的一条数据，为异步方法，结果会回调到异步地址中")
	@ApiImplicitParam(name = "userId", value = "userId", paramType = "path", dataType = "String")
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public Boolean deleteUserDOById(@ApiParam(value = "userIdListString", required = true)@PathVariable("userId")String userId) {		
		Boolean flag = null;
		logger.info("receive:[userId:"+userId+"]");
		flag = userService.deleteUserById(userId);
		return flag;		
	}	
}