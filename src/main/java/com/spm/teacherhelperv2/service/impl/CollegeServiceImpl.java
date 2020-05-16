package com.spm.teacherhelperv2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spm.teacherhelperv2.dao.CollegeMapper;
import com.spm.teacherhelperv2.entity.CollegeDO;
import com.spm.teacherhelperv2.manager.GetEntity;
import com.spm.teacherhelperv2.service.CollegeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * CollegeServiceImpl服务实现类
 * @author zhangjr
 * @since 2020-05-15 22:42:58
 */
@Service
@Component("CollegeService")
public class CollegeServiceImpl implements CollegeService {
	private Logger logger = LoggerFactory.getLogger(CollegeServiceImpl.class);
	
	@Autowired(required = false)
	private CollegeMapper collegeMapper;
	private GetEntity getEntity = new GetEntity();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
    /**
     * 实现listCollegeByOther()方法
     * 用于根据特定条件值查询所有 CollegeDO数据
     */
	@Override
	public List<CollegeDO> listCollegeByOther(String fieldValue, String fieldName, String page, String limit) {
	    List<CollegeDO> collegeDOs = new ArrayList<CollegeDO>();
	    Field[] fields = new CollegeDO().getClass().getDeclaredFields();
		String annotationValue = null;
		if(fieldName != null) {
		    annotationValue = getEntity.getAnnotationValue(fields, fieldName);
		    if(limit == null) {
		        collegeDOs = this.collegeMapper.selectList(new QueryWrapper<CollegeDO>()
					.eq(annotationValue, fieldValue));
			} else {
			    Page<CollegeDO> lpage = new Page<CollegeDO>(Integer.valueOf(page),Integer.valueOf(limit));
	            collegeDOs = this.collegeMapper.selectPage(lpage,new QueryWrapper<CollegeDO>()
					.eq(annotationValue, fieldValue)).getRecords();
			}
		}else {
		    if(limit == null) {
		        collegeDOs = this.collegeMapper.selectList(null);
			} else {
			    Page<CollegeDO> lpage = new Page<CollegeDO>(Integer.valueOf(page),Integer.valueOf(limit));
	            collegeDOs = this.collegeMapper.selectPage(lpage,null).getRecords();
			}
		}
        logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"--page:"+page+"--limit:"+limit+"];Intermediate variable:[--annotationValue:"+annotationValue+"];--return:"+collegeDOs);
        return collegeDOs;
	}
	
	    /**
     * 实现getCollegeById()方法
     * 用于根据Id查询对应单条数据 
     */
	@Override
	public CollegeDO getCollegeById(Long collegeId) {
		CollegeDO collegeDO=this.collegeMapper.selectById(collegeId);
		logger.info("receive:[collegeId:"+collegeId+"];--return:"+collegeDO);
		return collegeDO;
	}
	
    /**
     * 实现getCollegeByOther方法
     * 用于根据其他信息查询对应单条数据 
     */
	@Override
	public CollegeDO getCollegeByOther(String fieldValue, String fieldName) {
		Field[] fields = new CollegeDO().getClass().getDeclaredFields();		
		try{
		    String annotationValue = getEntity.getAnnotationValue(fields, fieldName);
		    CollegeDO collegeDO = this.collegeMapper.selectOne(new QueryWrapper<CollegeDO>()
				    .eq(annotationValue, fieldValue));
		    logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"];Intermediate variable:[--annotationValue:"+annotationValue+"];--return:"+collegeDO);
		    return collegeDO;
		} catch (Exception e) {
		   return null;
		}
	}
	
    /**
     *  实现insertCollege()方法
     *  用于插入一条数据
     */
	@Override
	@Async
	public CollegeDO insertCollege(CollegeDO collegeDO) {
	    this.collegeMapper.insert(collegeDO);
		logger.info("receive:[collegeDO:"+collegeDO+"];--return:"+collegeDO);
		return collegeDO;
	}
	
	/**
	 *  实现updateCollege()方法
	 *  用于更新CollegeDO数据
	 */
	@Override
	@Async
	public CollegeDO updateCollege(CollegeDO collegeDO) {	
		this.collegeMapper.updateById(collegeDO);
		logger.info("receive:[collegeDO:"+collegeDO+"];--return:"+collegeDO);
		return collegeDO;	
	}

	/**
	 *  实现updateCollegeField()方法
	 *  用于更新CollegeDO部分数据
	 */
	@Override
	@Async
	public CollegeDO updateCollegeField(String data, Long collegeId) {
		Field[] fields = new CollegeDO().getClass().getDeclaredFields();				
		CollegeDO collegeDO = (CollegeDO) getEntity.setTableField(
				data, CollegeDO.class, fields, this.collegeMapper.selectById(collegeId));		
		this.collegeMapper.updateById(collegeDO);
		logger.info("receive:[data:"+data+"--collegeId:"+collegeId+"];Intermediate variable:[--collegeDO:"+collegeDO+"];--return:"+collegeDO);
		return collegeDO;	
	}	
	/**
	 *  实现deleteCollegeById()方法
	 *  用于删除对应Id的数据 
	 */
	@Override
	@Async
	public Boolean deleteCollegeById(String collegeId) {
		Boolean flag = false;
        int singleDelete = this.collegeMapper.deleteById(collegeId);	
        if(singleDelete == 1){
           flag = true; 
        }     	    
        logger.info("receive:[collegeId:"+collegeId+"];Intermediate variable:[--singleDelete:"+singleDelete+"];--return:"+flag);
		return flag;	
	}
}
