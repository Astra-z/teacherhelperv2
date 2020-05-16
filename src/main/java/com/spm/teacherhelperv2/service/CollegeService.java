package com.spm.teacherhelperv2.service;

import com.spm.teacherhelperv2.entity.CollegeDO;

import java.util.List;

/**
 * 服务类接口
 *
 * @author zhangjr
 * @since 2020-05-15
 */
public interface CollegeService {
	
	/**
	 * 根据其他信息查询数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @param page 页数
	 * @param limit 每页限制条数
     * @return Page<CollegeDO>
	 */
	public List<CollegeDO> listCollegeByOther(String fieldValue, String fieldName, String page, String limit);
	
	/**
	  * 根据Id查询数据
	 * @param collegeId collegeId
     * @return CollegeDO
	 */
	public CollegeDO getCollegeById(Long collegeId);
	
	/**
	   * 根据其他信息查询数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
     * @return CollegeDO
	 */
	public CollegeDO getCollegeByOther(String fieldValue, String fieldName);
	
	/**
	 * 插入新的数据
	 * @param collegeDO CollegeDO实体对象
     * @return String 
	 */
	public CollegeDO insertCollege(CollegeDO collegeDO);
		
	/**
	 * 更新数据
	 * @param collegeDO CollegeDO实体对象
     * @return String
	 */
	public CollegeDO updateCollege(CollegeDO collegeDO);
	
	/**
	 * 更新部分数据
	 * @param data 修改部分的信息
	 * @param collegeId collegeId
     * @return String
	 */
	public CollegeDO updateCollegeField(String data, Long collegeId);
	
	/**
	 * 根据Id删除数据
	 * @param collegeId collegeId
     * @return String
	 */
	public Boolean deleteCollegeById(String collegeId);	
}