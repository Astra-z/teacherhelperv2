package com.spm.teacherhelperv2.controller;

import com.alibaba.fastjson.JSONObject;
import com.spm.teacherhelperv2.entity.RoleDO;
import com.spm.teacherhelperv2.service.RoleService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  RoleController前端控制器
 * @author zhangjr
 * @since 2020-05-16 17:28:26
 */

@RestController
@RequestMapping("${global.version}/roles")
@Api(tags = "s_role表操作API")	
public class  RoleController {
    private Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Qualifier("RoleService")
    @Autowired
    private RoleService roleService;
	
	/**
	 * 获取满足某些条件的全部数据列表 
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @param page 页数
	 * @param limit 每页限制条数
	 * @return roleDO实体类数据列表
	 */
	@GetMapping("/")
	@RequiresPermissions({ "role-r" })
	@ApiOperation(value = "获取满足某些条件的全部数据列", httpMethod = "GET", notes = "用于通过指定条件,查询s_role表对应所用数据")
	@ApiImplicitParams({ @ApiImplicitParam(name = "page", value = "请求页码", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "limit", value = "每页数据条数", paramType = "query", dataType = "String"), 
			@ApiImplicitParam(name = "fieldValue", value = "查询条件值", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "fieldName", value = "查询条件值属性名", paramType = "query", dataType = "String")})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
			@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
			@ApiResponse(code = 555, message = "请求超时，请重试") })
	public List<RoleDO> listRoleByOther(@RequestParam(value = "page", required = false) String page,
                                        @RequestParam(value = "limit", required = false) String limit,
                                        @RequestParam(value = "fieldValue", required = false) String fieldValue,
                                        @RequestParam(value = "fieldName", required = false) String fieldName) {
		logger.info("receive:[page:"+page+"--limit:"+limit+"--fieldValue:"+fieldValue+"--fieldName:"+fieldName+"]");
		List<RoleDO> roles = roleService.listRoleByOther(fieldValue, fieldName, page, limit);
		return roles;
	}
	
    /**
     * 根据ID查找数据
     * @return roleDO实体类数据
     */
	@GetMapping("/{roleId}")
	@RequiresPermissions({"role-r" })
    @ApiOperation(value = "根据ID查找数据",httpMethod = "GET",notes = "用于通过roleId，查询s_role表中对应的一条数据")
	@ApiImplicitParam(name = "roleId", value = "roleId", paramType = "path", dataType = "String")
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RoleDO getRoleById(@ApiParam(value = "roleId", required = true)@PathVariable("roleId")String roleId) {
		logger.info("receive:[roleId:"+roleId+"]");
		RoleDO roleDO = roleService.getRoleById(Long.valueOf(roleId));
		return roleDO;		
	}
	
	/**
	 * 根据其他查找数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @return roleDO实体类数据
	 */
	@GetMapping("/role")
	@RequiresPermissions({ "role-r" })
	@ApiOperation(value = "根据其他查找数据", httpMethod = "GET", notes = "用于通过指定字段，查询uuuec_user表中对应的一条数据")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "fieldValue", value = "查询条件值", paramType = "query", dataType = "String"),
	@ApiImplicitParam(name = "fieldName", value = "查询条件值属性名", paramType = "query", dataType = "String")
	})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
			@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
			@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RoleDO getRoleByOther(@RequestParam(value = "fieldValue", required = false) String fieldValue
			, @RequestParam(value = "fieldName", required = false) String fieldName) {
		logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"]");
		RoleDO roleDO = roleService.getRoleByOther(fieldValue,fieldName);
		return roleDO;
	}
	
    /**
     * 添加数据
     * @return roleDO实体类数据
     */
	@PostMapping("/")
	@RequiresPermissions({ "role-r", "role-c"})
	@ApiOperation(value = "添加数据",httpMethod = "POST",notes = "用于在s_role表中插入对应的一条数据，为异步方法，结果会回调到异步地址中\n;"
			+ "RoleDO实体类数据{\n"
			+ "变量名：\"roleName\",类型：String,注释：角色名称\n,"
			+ "变量名：\"remark\",类型：String,注释：角色描述\n,"
	        + "}\n")
	@ApiImplicitParam(name = "data", value = "roleDO实体类数据", paramType = "body", dataType = "String")
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RoleDO insertRole(@RequestBody(required=true)String data) {
		logger.info("receive:[data:"+data+"]");		
		RoleDO roleDO= JSONObject.parseObject(data, RoleDO.class);
		roleService.insertRole(roleDO);		
		return roleDO;		
	}

	
    /**
     * 更新数据
     * @param data roleDO实体类数据
     * @return roleDO实体类数据
     */
	@PutMapping("/{roleId}")
	@RequiresPermissions({"role-r", "role-u"})
	@ApiOperation(value = "更新数据",httpMethod = "PUT",notes = "用于更新s_role表中对应的一条数据，为异步方法，结果会回调到异步地址中\n"
			+ "RoleDO实体类数据{\n"
			+ "变量名：\"roleName\",类型：String,注释：角色名称\n,"
			+ "变量名：\"remark\",类型：String,注释：角色描述\n,"
	        + "}\n")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "roleId", value = "roleId", paramType = "path", dataType = "String"),
	@ApiImplicitParam(name = "data", value = "roleDO实体类数据", paramType = "body", dataType = "String")
	})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RoleDO updateRoleDO(@RequestBody(required=true)String data) {
		logger.info("receive:[data:"+data+"]");
		RoleDO roleDO= JSONObject.parseObject(data, RoleDO.class);
		roleDO = roleService.updateRole(roleDO);		
        return roleDO;
     }

    /**
     * 更新部分数据
     * @param roleId roleId
     * @param data roleDO部分信息
     * @return GetResult<Boolean>(suatus:状态码,msg:消息,data:处理结果)
     */
	@PatchMapping("/{roleId}")
	@RequiresPermissions({ "role-r", "role-u"})
	@ApiOperation(value = "更新部分数据",httpMethod = "PATCH",notes = "用于更新s_role表中对应的一条数据，为异步方法，结果会回调到异步地址中")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "roleId", value = "roleId", paramType = "path", dataType = "String"),
	@ApiImplicitParam(name = "data", value = "roleDO实体类数据", paramType = "body", dataType = "String")
	})
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public RoleDO updateRoleForField(@ApiParam(value = "roleId", required = true)@PathVariable("roleId")String roleId,
                                     @RequestParam(value = "data",required=false) String data) {
		    logger.info("receive:[roleId:"+roleId+"--data:"+data+"]");		    		
			RoleDO roleDO = roleService.updateRoleField(data, Long.valueOf(roleId));
        return roleDO;
     }
	
    /**
     * 根据Id删除数据
     * @param roleDOId roleDOId
     * @return GetResult<Boolean>(suatus:状态码,msg:消息,data:处理结果)
     */
	@DeleteMapping("/{roleId}")
	@RequiresPermissions({"role-r", "role-d"})
	@ApiOperation(value = "根据Id删除数据",httpMethod = "DELETE",notes = "用于通过roleDOId，删除s_role表中对应的一条数据，为异步方法，结果会回调到异步地址中")
	@ApiImplicitParam(name = "roleId", value = "roleId", paramType = "path", dataType = "String")
	@ApiResponses({ @ApiResponse(code = 551, message = "第三方平台错误"), @ApiResponse(code = 552, message = "本平台错误"),
		@ApiResponse(code = 553, message = "权限不够"), @ApiResponse(code = 554, message = "请求数据有误"),
		@ApiResponse(code = 555, message = "请求超时，请重试") })
	public Boolean deleteRoleDOById(@ApiParam(value = "roleIdListString", required = true)@PathVariable("roleId")String roleId) {
		Boolean flag = null;
		logger.info("receive:[roleId:"+roleId+"]");
		flag = roleService.deleteRoleById(roleId);
		return flag;		
	}	
}