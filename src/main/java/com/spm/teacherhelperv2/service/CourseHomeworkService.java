package com.spm.teacherhelperv2.service;

import com.spm.teacherhelperv2.entity.CourseHomeworkDO;

import java.util.List;

/**
 * 服务类接口
 *
 * @author lxq
 * @since 2020-06-07
 */
public interface CourseHomeworkService {
	
	/**
	 * 根据其他信息查询数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @param page 页数
	 * @param limit 每页限制条数
     * @return Page<CourseHomeworkDO>
	 */
	public List<CourseHomeworkDO> listCourseHomeworkByOther(String fieldValue, String fieldName, String page, String limit);
	
	/**
	  * 根据Id查询数据
	 * @param courseHomeworkId courseHomeworkId
     * @return CourseHomeworkDO
	 */
	public CourseHomeworkDO getCourseHomeworkById(Long courseHomeworkId);
	
	/**
	   * 根据其他信息查询数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
     * @return CourseHomeworkDO
	 */
	public CourseHomeworkDO getCourseHomeworkByOther(String fieldValue, String fieldName);
	
	/**
	 * 插入新的数据
	 * @param courseHomeworkDO CourseHomeworkDO实体对象
     * @return String 
	 */
	public CourseHomeworkDO insertCourseHomework(CourseHomeworkDO courseHomeworkDO);
		
	/**
	 * 更新数据
	 * @param courseHomeworkDO CourseHomeworkDO实体对象
     * @return String
	 */
	public CourseHomeworkDO updateCourseHomework(CourseHomeworkDO courseHomeworkDO);
	
	/**
	 * 更新部分数据
	 * @param data 修改部分的信息
	 * @param courseHomeworkId courseHomeworkId
     * @return String
	 */
	public CourseHomeworkDO updateCourseHomeworkField(String data, Long courseHomeworkId);
	
	/**
	 * 根据Id删除数据
	 * @param courseHomeworkId courseHomeworkId
     * @return String
	 */
	public Boolean deleteCourseHomeworkById(String courseHomeworkId);	
}