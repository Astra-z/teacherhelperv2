package com.spm.teacherhelperv2.service;

import com.spm.teacherhelperv2.entity.SpecDO;

import java.util.List;

/**
 * 服务类接口
 *
 * @author zhangjr
 * @since 2020-05-15
 */
public interface SpecService {
	
	/**
	 * 根据其他信息查询数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @param page 页数
	 * @param limit 每页限制条数
     * @return Page<SpecDO>
	 */
	public List<SpecDO> listSpecByOther(String fieldValue, String fieldName, String page, String limit);
	
	/**
	  * 根据Id查询数据
	 * @param specId specId
     * @return SpecDO
	 */
	public SpecDO getSpecById(Long specId);
	
	/**
	   * 根据其他信息查询数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
     * @return SpecDO
	 */
	public SpecDO getSpecByOther(String fieldValue, String fieldName);
	
	/**
	 * 插入新的数据
	 * @param specDO SpecDO实体对象
     * @return String 
	 */
	public SpecDO insertSpec(SpecDO specDO);
		
	/**
	 * 更新数据
	 * @param specDO SpecDO实体对象
     * @return String
	 */
	public SpecDO updateSpec(SpecDO specDO);
	
	/**
	 * 更新部分数据
	 * @param data 修改部分的信息
	 * @param specId specId
     * @return String
	 */
	public SpecDO updateSpecField(String data, Long specId);
	
	/**
	 * 根据Id删除数据
	 * @param specId specId
     * @return String
	 */
	public Boolean deleteSpecById(String specId);	
}