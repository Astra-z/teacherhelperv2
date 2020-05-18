package com.spm.teacherhelperv2.service;

import com.spm.teacherhelperv2.entity.UserRoleDO;

import java.util.List;

/**
 * 服务类接口
 *
 * @author lxq
 * @since 2020-05-16
 */
public interface UserRoleService {
	
	/**
	 * 根据其他信息查询数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @param page 页数
	 * @param limit 每页限制条数
     * @return Page<UserRoleDO>
	 */
	public List<UserRoleDO> listUserRoleByOther(String fieldValue, String fieldName, String page, String limit);
	
	/**
	  * 根据Id查询数据
	 * @param userRoleId userRoleId
     * @return UserRoleDO
	 */
	public UserRoleDO getUserRoleById(Long userRoleId);
	
	/**
	   * 根据其他信息查询数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
     * @return UserRoleDO
	 */
	public UserRoleDO getUserRoleByOther(String fieldValue, String fieldName);
	
	/**
	 * 插入新的数据
	 * @param userRoleDO UserRoleDO实体对象
     * @return String 
	 */
	public UserRoleDO insertUserRole(UserRoleDO userRoleDO);
		
	/**
	 * 更新数据
	 * @param userRoleDO UserRoleDO实体对象
     * @return String
	 */
	public UserRoleDO updateUserRole(UserRoleDO userRoleDO);
	
	/**
	 * 更新部分数据
	 * @param data 修改部分的信息
	 * @param userRoleId userRoleId
     * @return String
	 */
	public UserRoleDO updateUserRoleField(String data, Long userRoleId);
	
	/**
	 * 根据Id删除数据
	 * @param userRoleId userRoleId
     * @return String
	 */
	public Boolean deleteUserRoleById(String userRoleId);	
}