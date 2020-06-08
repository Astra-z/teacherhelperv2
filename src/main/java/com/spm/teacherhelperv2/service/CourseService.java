package com.spm.teacherhelperv2.service;

import com.spm.teacherhelperv2.entity.CourseDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 服务类接口
 *
 * @author zhangjr
 * @since 2020-05-15
 */
public interface CourseService {
	
	/**
	 * 根据其他信息查询数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @param page 页数
	 * @param limit 每页限制条数
     * @return Page<CourseDO>
	 */
	public List<CourseDO> listCourseByOther(String fieldValue, String fieldName, String page, String limit);
	
	/**
	  * 根据Id查询数据
	 * @param courseId courseId
     * @return CourseDO
	 */
	public CourseDO getCourseById(Long courseId);
	
	/**
	   * 根据其他信息查询数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
     * @return CourseDO
	 */
	public CourseDO getCourseByOther(String fieldValue, String fieldName);
	
	/**
	 * 插入新的数据
	 * @param courseDO CourseDO实体对象
     * @return String 
	 */
	public CourseDO insertCourse(CourseDO courseDO);
		
	/**
	 * 更新数据
	 * @param courseDO CourseDO实体对象
     * @return String
	 */
	public CourseDO updateCourse(CourseDO courseDO);
	
	/**
	 * 更新部分数据
	 * @param data 修改部分的信息
	 * @param courseId courseId
     * @return String
	 */
	public CourseDO updateCourseField(String data, Long courseId);
	
	/**
	 * 根据Id删除数据
	 * @param courseId courseId
     * @return String
	 */
	public Boolean deleteCourseById(String courseId);


	/**
	 * 上传作业
	 * @param file
	 * @return
	 */
	public Boolean uploadCourseHomework(MultipartFile file,String courseId,String courseHomeworkId,String username);


	/**
	 *
	 * @param courseId
	 * @param courseHomeworkId
	 * @param username
	 */
	public List<String> getMyCourseHomeworkList(String courseId,String courseHomeworkId,String username);


	/**
	 *
	 * @param studentId
	 * @return
	 */
	public List<CourseDO> getMyCourseList(String studentId);
}