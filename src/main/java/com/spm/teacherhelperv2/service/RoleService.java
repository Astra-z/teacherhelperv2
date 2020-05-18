package com.spm.teacherhelperv2.service;

import com.spm.teacherhelperv2.entity.RoleDO;

import java.util.List;

/**
 * 服务类接口
 *
 * @author lxq
 * @since 2020-05-16
 */
public interface RoleService {
	
	/**
	 * 根据其他信息查询数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @param page 页数
	 * @param limit 每页限制条数
     * @return Page<RoleDO>
	 */
	public List<RoleDO> listRoleByOther(String fieldValue, String fieldName, String page, String limit);
	
	/**
	  * 根据Id查询数据
	 * @param roleId roleId
     * @return RoleDO
	 */
	public RoleDO getRoleById(Long roleId);
	
	/**
	   * 根据其他信息查询数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
     * @return RoleDO
	 */
	public RoleDO getRoleByOther(String fieldValue, String fieldName);
	
	/**
	 * 插入新的数据
	 * @param roleDO RoleDO实体对象
     * @return String 
	 */
	public RoleDO insertRole(RoleDO roleDO);
		
	/**
	 * 更新数据
	 * @param roleDO RoleDO实体对象
     * @return String
	 */
	public RoleDO updateRole(RoleDO roleDO);
	
	/**
	 * 更新部分数据
	 * @param data 修改部分的信息
	 * @param roleId roleId
     * @return String
	 */
	public RoleDO updateRoleField(String data, Long roleId);
	
	/**
	 * 根据Id删除数据
	 * @param roleId roleId
     * @return String
	 */
	public Boolean deleteRoleById(String roleId);


	public List<RoleDO> findUserRole(String username);
}