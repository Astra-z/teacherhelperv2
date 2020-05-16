package com.spm.teacherhelperv2.service;

import com.spm.teacherhelperv2.entity.CourseFrequencyDO;

import java.util.List;

/**
 * 服务类接口
 *
 * @author zhangjr
 * @since 2020-05-15
 */
public interface CourseFrequencyService {
	
	/**
	 * 根据其他信息查询数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @param page 页数
	 * @param limit 每页限制条数
     * @return Page<CourseFrequencyDO>
	 */
	public List<CourseFrequencyDO> listCourseFrequencyByOther(String fieldValue, String fieldName, String page, String limit);
	
	/**
	  * 根据Id查询数据
	 * @param courseFrequencyId courseFrequencyId
     * @return CourseFrequencyDO
	 */
	public CourseFrequencyDO getCourseFrequencyById(Long courseFrequencyId);
	
	/**
	   * 根据其他信息查询数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
     * @return CourseFrequencyDO
	 */
	public CourseFrequencyDO getCourseFrequencyByOther(String fieldValue, String fieldName);
	
	/**
	 * 插入新的数据
	 * @param courseFrequencyDO CourseFrequencyDO实体对象
     * @return String 
	 */
	public CourseFrequencyDO insertCourseFrequency(CourseFrequencyDO courseFrequencyDO);
		
	/**
	 * 更新数据
	 * @param courseFrequencyDO CourseFrequencyDO实体对象
     * @return String
	 */
	public CourseFrequencyDO updateCourseFrequency(CourseFrequencyDO courseFrequencyDO);
	
	/**
	 * 更新部分数据
	 * @param data 修改部分的信息
	 * @param courseFrequencyId courseFrequencyId
     * @return String
	 */
	public CourseFrequencyDO updateCourseFrequencyField(String data, Long courseFrequencyId);
	
	/**
	 * 根据Id删除数据
	 * @param courseFrequencyId courseFrequencyId
     * @return String
	 */
	public Boolean deleteCourseFrequencyById(String courseFrequencyId);	
}