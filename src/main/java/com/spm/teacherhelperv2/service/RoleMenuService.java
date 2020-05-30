package com.spm.teacherhelperv2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spm.teacherhelperv2.entity.RoleMenuDO;

import java.util.List;

/**
 * 服务类接口
 *
 * @author lxq
 * @since 2020-05-16
 */
public interface RoleMenuService  {
	
	/**
	 * 根据其他信息查询数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @param page 页数
	 * @param limit 每页限制条数
     * @return Page<RoleMenuDO>
	 */
	public List<RoleMenuDO> listRoleMenuByOther(String fieldValue, String fieldName, String page, String limit);
	
	/**
	  * 根据Id查询数据
	 * @param roleMenuId roleMenuId
     * @return RoleMenuDO
	 */
	public RoleMenuDO getRoleMenuById(Long roleMenuId);
	
	/**
	   * 根据其他信息查询数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
     * @return RoleMenuDO
	 */
	public RoleMenuDO getRoleMenuByOther(String fieldValue, String fieldName);
	
	/**
	 * 插入新的数据
	 * @param roleMenuDO RoleMenuDO实体对象
     * @return String 
	 */
	public RoleMenuDO insertRoleMenu(RoleMenuDO roleMenuDO);
		
	/**
	 * 更新数据
	 * @param roleMenuDO RoleMenuDO实体对象
     * @return String
	 */
	public RoleMenuDO updateRoleMenu(RoleMenuDO roleMenuDO);
	
	/**
	 * 更新部分数据
	 * @param data 修改部分的信息
	 * @param roleMenuId roleMenuId
     * @return String
	 */
	public RoleMenuDO updateRoleMenuField(String data, Long roleMenuId);
	
	/**
	 * 根据Id删除数据
	 * @param roleMenuId roleMenuId
     * @return String
	 */
	public Boolean deleteRoleMenuById(String roleMenuId);	
}