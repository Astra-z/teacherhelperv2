package com.spm.teacherhelperv2.service;

import com.spm.teacherhelperv2.entity.UserDO;

import java.util.List;

/**
 * 服务类接口
 *
 * @author zhangjr
 * @since 2020-05-15
 */
public interface UserService {
	
	/**
	 * 根据其他信息查询数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @param page 页数
	 * @param limit 每页限制条数
     * @return Page<UserDO>
	 */
	public List<UserDO> listUserByOther(String fieldValue, String fieldName, String page, String limit);
	
	/**
	  * 根据Id查询数据
	 * @param userId userId
     * @return UserDO
	 */
	public UserDO getUserById(Long userId);
	
	/**
	   * 根据其他信息查询数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
     * @return UserDO
	 */
	public UserDO getUserByOther(String fieldValue, String fieldName);
	
	/**
	 * 插入新的数据
	 * @param userDO UserDO实体对象
     * @return String 
	 */
	public UserDO insertUser(UserDO userDO);
		
	/**
	 * 更新数据
	 * @param userDO UserDO实体对象
     * @return String
	 */
	public UserDO updateUser(UserDO userDO);
	
	/**
	 * 更新部分数据
	 * @param data 修改部分的信息
	 * @param userId userId
     * @return String
	 */
	public UserDO updateUserField(String data, Long userId);
	
	/**
	 * 根据Id删除数据
	 * @param userId userId
     * @return String
	 */
	public Boolean deleteUserById(String userId);


	public List<UserDO> findUsersByRole(String roleName);
}