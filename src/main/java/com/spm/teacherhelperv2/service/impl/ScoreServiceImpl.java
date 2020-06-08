package com.spm.teacherhelperv2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spm.teacherhelperv2.dao.CourseMapper;
import com.spm.teacherhelperv2.dao.ScoreMapper;
import com.spm.teacherhelperv2.entity.CourseDO;
import com.spm.teacherhelperv2.entity.ScoreDO;
import com.spm.teacherhelperv2.manager.GetEntity;
import com.spm.teacherhelperv2.service.CourseService;
import com.spm.teacherhelperv2.service.ScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ScoreServiceImpl服务实现类
 * @author zhangjr
 * @since 2020-05-15 22:42:58
 */
@Service
@Component("ScoreService")
public class ScoreServiceImpl implements ScoreService {
	private Logger logger = LoggerFactory.getLogger(ScoreServiceImpl.class);
	
	@Autowired(required = false)
	private ScoreMapper scoreMapper;

	@Autowired
	@Qualifier("CourseService")
	private CourseService courseService;

	@Autowired(required = false)
	private CourseMapper courseMapper;
	private GetEntity getEntity = new GetEntity();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
    /**
     * 实现listScoreByOther()方法
     * 用于根据特定条件值查询所有 ScoreDO数据
     */
	@Override
	public List<ScoreDO> listScoreByOther(String fieldValue, String fieldName, String page, String limit) {
	    List<ScoreDO> scoreDOs = new ArrayList<ScoreDO>();
	    Field[] fields = new ScoreDO().getClass().getDeclaredFields();
		String annotationValue = null;
		if(fieldName != null) {
		    annotationValue = getEntity.getAnnotationValue(fields, fieldName);
		    if(limit == null) {
		        scoreDOs = this.scoreMapper.selectList(new QueryWrapper<ScoreDO>()
					.eq(annotationValue, fieldValue));
			} else {
			    Page<ScoreDO> lpage = new Page<ScoreDO>(Integer.valueOf(page),Integer.valueOf(limit));
	            scoreDOs = this.scoreMapper.selectPage(lpage,new QueryWrapper<ScoreDO>()
					.eq(annotationValue, fieldValue)).getRecords();
			}
		}else {
		    if(limit == null) {
		        scoreDOs = this.scoreMapper.selectList(null);
			} else {
			    Page<ScoreDO> lpage = new Page<ScoreDO>(Integer.valueOf(page),Integer.valueOf(limit));
	            scoreDOs = this.scoreMapper.selectPage(lpage,null).getRecords();
			}
		}
        logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"--page:"+page+"--limit:"+limit+"];Intermediate variable:[--annotationValue:"+annotationValue+"];--return:"+scoreDOs);
        return scoreDOs;
	}
	
	    /**
     * 实现getScoreById()方法
     * 用于根据Id查询对应单条数据 
     */
	@Override
	public ScoreDO getScoreById(Long scoreId) {
		ScoreDO scoreDO=this.scoreMapper.selectById(scoreId);
		logger.info("receive:[scoreId:"+scoreId+"];--return:"+scoreDO);
		return scoreDO;
	}
	
    /**
     * 实现getScoreByOther方法
     * 用于根据其他信息查询对应单条数据 
     */
	@Override
	public ScoreDO getScoreByOther(String fieldValue, String fieldName) {
		Field[] fields = new ScoreDO().getClass().getDeclaredFields();
		try{
		    String annotationValue = getEntity.getAnnotationValue(fields, fieldName);
		    ScoreDO scoreDO = this.scoreMapper.selectOne(new QueryWrapper<ScoreDO>()
				    .eq(annotationValue, fieldValue));
		    logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"];Intermediate variable:[--annotationValue:"+annotationValue+"];--return:"+scoreDO);
		    return scoreDO;
		} catch (Exception e) {
		   return null;
		}
	}
	
    /**
     *  实现insertScore()方法
     *  用于插入一条数据
     */
	@Override
	@Async
	public ScoreDO insertScore(ScoreDO scoreDO) {
		//同步检查选课人数是否已满（待做）
		synchronized(this){
			CourseDO courseDO= courseMapper.selectById(scoreDO.getCourseId());
			if(courseDO.getStudentNum()>=courseDO.getMaxNum()){
				try {
					throw new Exception("课程已满");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			courseMapper.addStundentNum(scoreDO.getCourseId());
		}
	    scoreDO.setCreateTime(new Date());
		scoreDO.setModifyTime(new Date());
	    this.scoreMapper.insert(scoreDO);
		logger.info("receive:[scoreDO:"+scoreDO+"];--return:"+scoreDO);
		return scoreDO;
	}
	
	/**
	 *  实现updateScore()方法
	 *  用于更新ScoreDO数据
	 */
	@Override
	@Async
	public ScoreDO updateScore(ScoreDO scoreDO) {
	    scoreDO.setModifyTime(new Date());
		this.scoreMapper.updateById(scoreDO);
		logger.info("receive:[scoreDO:"+scoreDO+"];--return:"+scoreDO);
		return scoreDO;	
	}

	/**
	 *  实现updateScoreField()方法
	 *  用于更新ScoreDO部分数据
	 */
	@Override
	@Async
	public ScoreDO updateScoreField(String data, Long scoreId) {
		Field[] fields = new ScoreDO().getClass().getDeclaredFields();
		ScoreDO scoreDO = (ScoreDO) getEntity.setTableField(
				data, ScoreDO.class, fields, this.scoreMapper.selectById(scoreId));
		scoreDO.setModifyTime(new Date());
		this.scoreMapper.updateById(scoreDO);
		logger.info("receive:[data:"+data+"--scoreId:"+scoreId+"];Intermediate variable:[--scoreDO:"+scoreDO+"];--return:"+scoreDO);
		return scoreDO;	
	}	
	/**
	 *  实现deleteScoreById()方法
	 *  用于删除对应Id的数据 
	 */
	@Override
	@Async
	public Boolean deleteScoreById(String scoreId) {
		Boolean flag = false;
        int singleDelete = this.scoreMapper.deleteById(scoreId);	
        if(singleDelete == 1){
           flag = true; 
        }     	    
        logger.info("receive:[scoreId:"+scoreId+"];Intermediate variable:[--singleDelete:"+singleDelete+"];--return:"+flag);
		return flag;	
	}
}
