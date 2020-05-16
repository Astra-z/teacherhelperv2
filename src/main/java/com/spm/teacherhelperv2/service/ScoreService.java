package com.spm.teacherhelperv2.service;

import com.spm.teacherhelperv2.entity.ScoreDO;

import java.util.List;

/**
 * 服务类接口
 *
 * @author zhangjr
 * @since 2020-05-15
 */
public interface ScoreService {
	
	/**
	 * 根据其他信息查询数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
	 * @param page 页数
	 * @param limit 每页限制条数
     * @return Page<ScoreDO>
	 */
	public List<ScoreDO> listScoreByOther(String fieldValue, String fieldName, String page, String limit);
	
	/**
	  * 根据Id查询数据
	 * @param scoreId scoreId
     * @return ScoreDO
	 */
	public ScoreDO getScoreById(Long scoreId);
	
	/**
	   * 根据其他信息查询数据
	 * @param fieldValue 查询条件值
	 * @param fieldName 查询条件值属性名
     * @return ScoreDO
	 */
	public ScoreDO getScoreByOther(String fieldValue, String fieldName);
	
	/**
	 * 插入新的数据
	 * @param scoreDO ScoreDO实体对象
     * @return String 
	 */
	public ScoreDO insertScore(ScoreDO scoreDO);
		
	/**
	 * 更新数据
	 * @param scoreDO ScoreDO实体对象
     * @return String
	 */
	public ScoreDO updateScore(ScoreDO scoreDO);
	
	/**
	 * 更新部分数据
	 * @param data 修改部分的信息
	 * @param scoreId scoreId
     * @return String
	 */
	public ScoreDO updateScoreField(String data, Long scoreId);
	
	/**
	 * 根据Id删除数据
	 * @param scoreId scoreId
     * @return String
	 */
	public Boolean deleteScoreById(String scoreId);	
}