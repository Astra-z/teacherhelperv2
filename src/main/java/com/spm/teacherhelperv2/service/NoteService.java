package com.spm.teacherhelperv2.service;

import com.spm.teacherhelperv2.entity.NoteDO;

import java.util.List;

/**
 * 服务类接口
 *
 * @author lxq
 * @since 2020-06-08
 */
public interface NoteService {
	
	/**
	 * 根据其他信息查询数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @param page 页数
	 * @param limit 每页限制条数
     * @return Page<NoteDO>
	 */
	public List<NoteDO> listNoteByOther(String fieldValue, String fieldName, String page, String limit);
	
	/**
	  * 根据Id查询数据
	 * @param noteId noteId
     * @return NoteDO
	 */
	public NoteDO getNoteById(Long noteId);
	
	/**
	   * 根据其他信息查询数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
     * @return NoteDO
	 */
	public NoteDO getNoteByOther(String fieldValue, String fieldName);
	
	/**
	 * 插入新的数据
	 * @param noteDO NoteDO实体对象
     * @return String 
	 */
	public NoteDO insertNote(NoteDO noteDO);
		
	/**
	 * 更新数据
	 * @param noteDO NoteDO实体对象
     * @return String
	 */
	public NoteDO updateNote(NoteDO noteDO);
	
	/**
	 * 更新部分数据
	 * @param data 修改部分的信息
	 * @param noteId noteId
     * @return String
	 */
	public NoteDO updateNoteField(String data, Long noteId);
	
	/**
	 * 根据Id删除数据
	 * @param noteId noteId
     * @return String
	 */
	public Boolean deleteNoteById(String noteId);	
}