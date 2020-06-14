package com.spm.teacherhelperv2.service;

import com.alibaba.fastjson.JSONArray;
import com.spm.teacherhelperv2.entity.StudentMentorDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 服务类接口
 *
 * @author lxq
 * @since 2020-06-13
 */
public interface StudentMentorService {
	
	/**
	 * 根据其他信息查询数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @param page 页数
	 * @param limit 每页限制条数
     * @return Page<StudentMentorDO>
	 */
	public List<StudentMentorDO> listStudentMentorByOther(String fieldValue, String fieldName, String page, String limit);
	
	/**
	  * 根据Id查询数据
	 * @param studentMentorId studentMentorId
     * @return StudentMentorDO
	 */
	public StudentMentorDO getStudentMentorById(Long studentMentorId);
	
	/**
	   * 根据其他信息查询数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
     * @return StudentMentorDO
	 */
	public StudentMentorDO getStudentMentorByOther(String fieldValue, String fieldName);
	
	/**
	 * 插入新的数据
	 * @param studentMentorDO StudentMentorDO实体对象
     * @return String 
	 */
	public StudentMentorDO insertStudentMentor(StudentMentorDO studentMentorDO);
		
	/**
	 * 更新数据
	 * @param studentMentorDO StudentMentorDO实体对象
     * @return String
	 */
	public StudentMentorDO updateStudentMentor(StudentMentorDO studentMentorDO);
	
	/**
	 * 更新部分数据
	 * @param data 修改部分的信息
	 * @param studentMentorId studentMentorId
     * @return String
	 */
	public StudentMentorDO updateStudentMentorField(String data, Long studentMentorId);
	
	/**
	 * 根据Id删除数据
	 * @param studentMentorId studentMentorId
     * @return String
	 */
	public Boolean deleteStudentMentorById(String studentMentorId);

	public List<StudentMentorDO> findAllMyStudent(Integer userId);

	public void sendNote(JSONArray NoteList);

	public List<String> getMyFileList(String mentorId,String studentId);

    public Boolean uploadfile(MultipartFile file,String mentorId,String studentId);
}