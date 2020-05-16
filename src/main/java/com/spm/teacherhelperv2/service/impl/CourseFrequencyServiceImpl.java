package com.spm.teacherhelperv2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spm.teacherhelperv2.dao.CourseFrequencyMapper;
import com.spm.teacherhelperv2.entity.CourseFrequencyDO;
import com.spm.teacherhelperv2.manager.GetEntity;
import com.spm.teacherhelperv2.service.CourseFrequencyService;
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
 * CourseFrequencyServiceImpl服务实现类
 * @author zhangjr
 * @since 2020-05-15 22:42:58
 */
@Service
@Component("CourseFrequencyService")
public class CourseFrequencyServiceImpl implements CourseFrequencyService {
	private Logger logger = LoggerFactory.getLogger(CourseFrequencyServiceImpl.class);
	
	@Autowired(required = false)
	private CourseFrequencyMapper courseFrequencyMapper;
	private GetEntity getEntity = new GetEntity();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
    /**
     * 实现listCourseFrequencyByOther()方法
     * 用于根据特定条件值查询所有 CourseFrequencyDO数据
     */
	@Override
	public List<CourseFrequencyDO> listCourseFrequencyByOther(String fieldValue, String fieldName, String page, String limit) {
	    List<CourseFrequencyDO> courseFrequencyDOs = new ArrayList<CourseFrequencyDO>();
	    Field[] fields = new CourseFrequencyDO().getClass().getDeclaredFields();
		String annotationValue = null;
		if(fieldName != null) {
		    annotationValue = getEntity.getAnnotationValue(fields, fieldName);
		    if(limit == null) {
		        courseFrequencyDOs = this.courseFrequencyMapper.selectList(new QueryWrapper<CourseFrequencyDO>()
					.eq(annotationValue, fieldValue));
			} else {
			    Page<CourseFrequencyDO> lpage = new Page<CourseFrequencyDO>(Integer.valueOf(page),Integer.valueOf(limit));
	            courseFrequencyDOs = this.courseFrequencyMapper.selectPage(lpage,new QueryWrapper<CourseFrequencyDO>()
					.eq(annotationValue, fieldValue)).getRecords();
			}
		}else {
		    if(limit == null) {
		        courseFrequencyDOs = this.courseFrequencyMapper.selectList(null);
			} else {
			    Page<CourseFrequencyDO> lpage = new Page<CourseFrequencyDO>(Integer.valueOf(page),Integer.valueOf(limit));
	            courseFrequencyDOs = this.courseFrequencyMapper.selectPage(lpage,null).getRecords();
			}
		}
        logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"--page:"+page+"--limit:"+limit+"];Intermediate variable:[--annotationValue:"+annotationValue+"];--return:"+courseFrequencyDOs);
        return courseFrequencyDOs;
	}
	
	    /**
     * 实现getCourseFrequencyById()方法
     * 用于根据Id查询对应单条数据 
     */
	@Override
	public CourseFrequencyDO getCourseFrequencyById(Long courseFrequencyId) {
		CourseFrequencyDO courseFrequencyDO=this.courseFrequencyMapper.selectById(courseFrequencyId);
		logger.info("receive:[courseFrequencyId:"+courseFrequencyId+"];--return:"+courseFrequencyDO);
		return courseFrequencyDO;
	}
	
    /**
     * 实现getCourseFrequencyByOther方法
     * 用于根据其他信息查询对应单条数据 
     */
	@Override
	public CourseFrequencyDO getCourseFrequencyByOther(String fieldValue, String fieldName) {
		Field[] fields = new CourseFrequencyDO().getClass().getDeclaredFields();
		try{
		    String annotationValue = getEntity.getAnnotationValue(fields, fieldName);
		    CourseFrequencyDO courseFrequencyDO = this.courseFrequencyMapper.selectOne(new QueryWrapper<CourseFrequencyDO>()
				    .eq(annotationValue, fieldValue));
		    logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"];Intermediate variable:[--annotationValue:"+annotationValue+"];--return:"+courseFrequencyDO);
		    return courseFrequencyDO;
		} catch (Exception e) {
		   return null;
		}
	}
	
    /**
     *  实现insertCourseFrequency()方法
     *  用于插入一条数据
     */
	@Override
	@Async
	public CourseFrequencyDO insertCourseFrequency(CourseFrequencyDO courseFrequencyDO) {
	    courseFrequencyDO.setCreateTime(new Date());
		courseFrequencyDO.setModifyTime(new Date());
	    this.courseFrequencyMapper.insert(courseFrequencyDO);
		logger.info("receive:[courseFrequencyDO:"+courseFrequencyDO+"];--return:"+courseFrequencyDO);
		return courseFrequencyDO;
	}
	
	/**
	 *  实现updateCourseFrequency()方法
	 *  用于更新CourseFrequencyDO数据
	 */
	@Override
	@Async
	public CourseFrequencyDO updateCourseFrequency(CourseFrequencyDO courseFrequencyDO) {
	    courseFrequencyDO.setModifyTime(new Date());
		this.courseFrequencyMapper.updateById(courseFrequencyDO);
		logger.info("receive:[courseFrequencyDO:"+courseFrequencyDO+"];--return:"+courseFrequencyDO);
		return courseFrequencyDO;	
	}

	/**
	 *  实现updateCourseFrequencyField()方法
	 *  用于更新CourseFrequencyDO部分数据
	 */
	@Override
	@Async
	public CourseFrequencyDO updateCourseFrequencyField(String data, Long courseFrequencyId) {
		Field[] fields = new CourseFrequencyDO().getClass().getDeclaredFields();
		CourseFrequencyDO courseFrequencyDO = (CourseFrequencyDO) getEntity.setTableField(
				data, CourseFrequencyDO.class, fields, this.courseFrequencyMapper.selectById(courseFrequencyId));
		courseFrequencyDO.setModifyTime(new Date());
		this.courseFrequencyMapper.updateById(courseFrequencyDO);
		logger.info("receive:[data:"+data+"--courseFrequencyId:"+courseFrequencyId+"];Intermediate variable:[--courseFrequencyDO:"+courseFrequencyDO+"];--return:"+courseFrequencyDO);
		return courseFrequencyDO;	
	}	
	/**
	 *  实现deleteCourseFrequencyById()方法
	 *  用于删除对应Id的数据 
	 */
	@Override
	@Async
	public Boolean deleteCourseFrequencyById(String courseFrequencyId) {
		Boolean flag = false;
        int singleDelete = this.courseFrequencyMapper.deleteById(courseFrequencyId);	
        if(singleDelete == 1){
           flag = true; 
        }     	    
        logger.info("receive:[courseFrequencyId:"+courseFrequencyId+"];Intermediate variable:[--singleDelete:"+singleDelete+"];--return:"+flag);
		return flag;	
	}
}
