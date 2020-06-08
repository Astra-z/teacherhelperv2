package com.spm.teacherhelperv2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spm.teacherhelperv2.dao.CourseHomeworkMapper;
import com.spm.teacherhelperv2.entity.CourseHomeworkDO;
import com.spm.teacherhelperv2.manager.GetEntity;
import com.spm.teacherhelperv2.service.CourseHomeworkService;
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
 * CourseHomeworkServiceImpl服务实现类
 * @author zhangjr
 * @since 2020-06-07 10:43:26
 */
@Service
@Component("CourseHomeworkService")
public class CourseHomeworkServiceImpl implements CourseHomeworkService {
	private Logger logger = LoggerFactory.getLogger(CourseHomeworkServiceImpl.class);
	
	@Autowired(required = false)
	private CourseHomeworkMapper courseHomeworkMapper;
	private GetEntity getEntity = new GetEntity();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
    /**
     * 实现listCourseHomeworkByOther()方法
     * 用于根据特定条件值查询所有 CourseHomeworkDO数据
     */
	@Override
	public List<CourseHomeworkDO> listCourseHomeworkByOther(String fieldValue, String fieldName, String page, String limit) {
	    List<CourseHomeworkDO> courseHomeworkDOs = new ArrayList<CourseHomeworkDO>();
	    Field[] fields = new CourseHomeworkDO().getClass().getDeclaredFields();
		String annotationValue = null;
		if(fieldName != null) {
		    annotationValue = getEntity.getAnnotationValue(fields, fieldName);
		    if(limit == null) {
		        courseHomeworkDOs = this.courseHomeworkMapper.selectList(new QueryWrapper<CourseHomeworkDO>()
					.eq(annotationValue, fieldValue));
			} else {
			    Page<CourseHomeworkDO> lpage = new Page<CourseHomeworkDO>(Integer.valueOf(page),Integer.valueOf(limit));
	            courseHomeworkDOs = this.courseHomeworkMapper.selectPage(lpage,new QueryWrapper<CourseHomeworkDO>()
					.eq(annotationValue, fieldValue)).getRecords();
			}
		}else {
		    if(limit == null) {
		        courseHomeworkDOs = this.courseHomeworkMapper.selectList(null);
			} else {
			    Page<CourseHomeworkDO> lpage = new Page<CourseHomeworkDO>(Integer.valueOf(page),Integer.valueOf(limit));
	            courseHomeworkDOs = this.courseHomeworkMapper.selectPage(lpage,null).getRecords();
			}
		}
        logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"--page:"+page+"--limit:"+limit+"];Intermediate variable:[--annotationValue:"+annotationValue+"];--return:"+courseHomeworkDOs);
        return courseHomeworkDOs;
	}
	
	    /**
     * 实现getCourseHomeworkById()方法
     * 用于根据Id查询对应单条数据 
     */
	@Override
	public CourseHomeworkDO getCourseHomeworkById(Long courseHomeworkId) {
		CourseHomeworkDO courseHomeworkDO=this.courseHomeworkMapper.selectById(courseHomeworkId);
		logger.info("receive:[courseHomeworkId:"+courseHomeworkId+"];--return:"+courseHomeworkDO);
		return courseHomeworkDO;
	}
	
    /**
     * 实现getCourseHomeworkByOther方法
     * 用于根据其他信息查询对应单条数据 
     */
	@Override
	public CourseHomeworkDO getCourseHomeworkByOther(String fieldValue, String fieldName) {
		Field[] fields = new CourseHomeworkDO().getClass().getDeclaredFields();
		try{
		    String annotationValue = getEntity.getAnnotationValue(fields, fieldName);
		    CourseHomeworkDO courseHomeworkDO = this.courseHomeworkMapper.selectOne(new QueryWrapper<CourseHomeworkDO>()
				    .eq(annotationValue, fieldValue));
		    logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"];Intermediate variable:[--annotationValue:"+annotationValue+"];--return:"+courseHomeworkDO);
		    return courseHomeworkDO;
		} catch (Exception e) {
		   return null;
		}
	}
	
    /**
     *  实现insertCourseHomework()方法
     *  用于插入一条数据
     */
	@Override
	@Async
	public CourseHomeworkDO insertCourseHomework(CourseHomeworkDO courseHomeworkDO) {
	    courseHomeworkDO.setCreateTime(new Date());
		courseHomeworkDO.setModifyTime(new Date());
	    this.courseHomeworkMapper.insert(courseHomeworkDO);
		logger.info("receive:[courseHomeworkDO:"+courseHomeworkDO+"];--return:"+courseHomeworkDO);
		return courseHomeworkDO;
	}
	
	/**
	 *  实现updateCourseHomework()方法
	 *  用于更新CourseHomeworkDO数据
	 */
	@Override
	@Async
	public CourseHomeworkDO updateCourseHomework(CourseHomeworkDO courseHomeworkDO) {
	    courseHomeworkDO.setModifyTime(new Date());
		this.courseHomeworkMapper.updateById(courseHomeworkDO);
		logger.info("receive:[courseHomeworkDO:"+courseHomeworkDO+"];--return:"+courseHomeworkDO);
		return courseHomeworkDO;	
	}

	/**
	 *  实现updateCourseHomeworkField()方法
	 *  用于更新CourseHomeworkDO部分数据
	 */
	@Override
	@Async
	public CourseHomeworkDO updateCourseHomeworkField(String data, Long courseHomeworkId) {
		Field[] fields = new CourseHomeworkDO().getClass().getDeclaredFields();
		CourseHomeworkDO courseHomeworkDO = (CourseHomeworkDO) getEntity.setTableField(
				data, CourseHomeworkDO.class, fields, this.courseHomeworkMapper.selectById(courseHomeworkId));
		courseHomeworkDO.setModifyTime(new Date());
		this.courseHomeworkMapper.updateById(courseHomeworkDO);
		logger.info("receive:[data:"+data+"--courseHomeworkId:"+courseHomeworkId+"];Intermediate variable:[--courseHomeworkDO:"+courseHomeworkDO+"];--return:"+courseHomeworkDO);
		return courseHomeworkDO;	
	}	
	/**
	 *  实现deleteCourseHomeworkById()方法
	 *  用于删除对应Id的数据 
	 */
	@Override
	@Async
	public Boolean deleteCourseHomeworkById(String courseHomeworkId) {
		Boolean flag = false;
        int singleDelete = this.courseHomeworkMapper.deleteById(courseHomeworkId);	
        if(singleDelete == 1){
           flag = true; 
        }     	    
        logger.info("receive:[courseHomeworkId:"+courseHomeworkId+"];Intermediate variable:[--singleDelete:"+singleDelete+"];--return:"+flag);
		return flag;	
	}
}
