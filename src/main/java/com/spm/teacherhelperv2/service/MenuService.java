package com.spm.teacherhelperv2.service;

import com.spm.teacherhelperv2.entity.MenuDO;
import com.spm.teacherhelperv2.entity.Tree;

import java.util.List;

/**
 * 服务类接口
 *
 * @author lxq
 * @since 2020-05-16
 */
public interface MenuService {
	
	/**
	 * 根据其他信息查询数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @param page 页数
	 * @param limit 每页限制条数
     * @return Page<MenuDO>
	 */
	public List<MenuDO> listMenuByOther(String fieldValue, String fieldName, String page, String limit);
	
	/**
	  * 根据Id查询数据
	 * @param menuId menuId
     * @return MenuDO
	 */
	public MenuDO getMenuById(Long menuId);
	
	/**
	   * 根据其他信息查询数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
     * @return MenuDO
	 */
	public MenuDO getMenuByOther(String fieldValue, String fieldName);
	
	/**
	 * 插入新的数据
	 * @param menuDO MenuDO实体对象
     * @return String 
	 */
	public MenuDO insertMenu(MenuDO menuDO);
		
	/**
	 * 更新数据
	 * @param menuDO MenuDO实体对象
     * @return String
	 */
	public MenuDO updateMenu(MenuDO menuDO);
	
	/**
	 * 更新部分数据
	 * @param data 修改部分的信息
	 * @param menuId menuId
     * @return String
	 */
	public MenuDO updateMenuField(String data, Long menuId);
	
	/**
	 * 根据Id删除数据
	 * @param menuId menuId
     * @return String
	 */
	public Boolean deleteMenuById(String menuId);


	/**
	 * 根据username获取用户权限
	 * @param username
	 * @return
	 */
	public List<MenuDO> findPermsByUsername(String username);


	public Tree<MenuDO> findUserMenu(String username);
}