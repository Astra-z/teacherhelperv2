package com.spm.teacherhelperv2.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spm.teacherhelperv2.dao.CourseFrequencyMapper;
import com.spm.teacherhelperv2.dao.CourseMapper;
import com.spm.teacherhelperv2.dao.SpecMapper;
import com.spm.teacherhelperv2.entity.CourseDO;
import com.spm.teacherhelperv2.entity.CourseFrequencyDO;
import com.spm.teacherhelperv2.entity.ScoreDO;
import com.spm.teacherhelperv2.manager.GetEntity;
import com.spm.teacherhelperv2.service.CourseFrequencyService;
import com.spm.teacherhelperv2.service.CourseService;
import com.spm.teacherhelperv2.service.ScoreService;
import com.spm.teacherhelperv2.util.MyFileUtils;
import org.apache.tomcat.util.security.Escape;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CourseServiceImpl服务实现类
 * @author zhangjr
 * @since 2020-05-15 22:42:58
 */
@Service
@Component("CourseService")
public class CourseServiceImpl implements CourseService {
	private Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
	
	@Autowired(required = false)
	private CourseMapper courseMapper;

	@Autowired(required = false)
	private SpecMapper specMapper;


	@Autowired
	@Qualifier("CourseFrequencyService")
	private CourseFrequencyService courseFrequencyService;

	@Autowired
	@Qualifier("ScoreService")
	private ScoreService scoreService;

	@Autowired(required = false)
	private CourseFrequencyMapper courseFrequencyMapper;
	private GetEntity getEntity = new GetEntity();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
    /**
     * 实现listCourseByOther()方法
     * 用于根据特定条件值查询所有 CourseDO数据
     */
	@Override
	public List<CourseDO> listCourseByOther(String fieldValue, String fieldName, String page, String limit) {
	    List<CourseDO> courseDOs = new ArrayList<CourseDO>();
	    Field[] fields = new CourseDO().getClass().getDeclaredFields();
		String annotationValue = null;
		if(fieldName != null) {
		    annotationValue = getEntity.getAnnotationValue(fields, fieldName);
		    if(limit == null) {
		        courseDOs = this.courseMapper.selectList(new QueryWrapper<CourseDO>()
					.eq(annotationValue, fieldValue));
			} else {
			    Page<CourseDO> lpage = new Page<CourseDO>(Integer.valueOf(page),Integer.valueOf(limit));
	            courseDOs = this.courseMapper.selectPage(lpage,new QueryWrapper<CourseDO>()
					.eq(annotationValue, fieldValue)).getRecords();
			}
		}else {
		    if(limit == null) {
		        courseDOs = this.courseMapper.selectList(null);
			} else {
			    Page<CourseDO> lpage = new Page<CourseDO>(Integer.valueOf(page),Integer.valueOf(limit));
	            courseDOs = this.courseMapper.selectPage(lpage,null).getRecords();
			}
		}
		courseDOs.forEach(courseDO -> {
			courseDO.setSpecDO(specMapper.selectById(courseDO.getSpecId()));
			courseDO.setCourseTimeList(courseFrequencyMapper
					.selectList(new QueryWrapper<CourseFrequencyDO>()
					.eq("courseId",courseDO.getCourseId())));
		});
        logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"--page:"+page+"--limit:"+limit+"];Intermediate variable:[--annotationValue:"+annotationValue+"];--return:"+courseDOs);
        return courseDOs;
	}
	
	/**
     * 实现getCourseById()方法
     * 用于根据Id查询对应单条数据 
     */
	@Override
	public CourseDO getCourseById(Long courseId) {
		CourseDO courseDO=this.courseMapper.selectById(courseId);
		courseDO.setCourseTimeList(this.courseFrequencyMapper
				.selectList(new QueryWrapper<CourseFrequencyDO>()
						.eq("courseId",courseDO.getCourseId())));
		courseDO.setSpecDO(this.specMapper.selectById(courseDO.getSpecId()));
		logger.info("receive:[courseId:"+courseId+"];--return:"+courseDO);
		return courseDO;
	}
	
    /**
     * 实现getCourseByOther方法
     * 用于根据其他信息查询对应单条数据 
     */
	@Override
	public CourseDO getCourseByOther(String fieldValue, String fieldName) {
		Field[] fields = new CourseDO().getClass().getDeclaredFields();
		try{
		    String annotationValue = getEntity.getAnnotationValue(fields, fieldName);
		    CourseDO courseDO = this.courseMapper.selectOne(new QueryWrapper<CourseDO>()
				    .eq(annotationValue, fieldValue));
		    logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"];Intermediate variable:[--annotationValue:"+annotationValue+"];--return:"+courseDO);
		    return courseDO;
		} catch (Exception e) {
		   return null;
		}
	}
	
    /**
     *  实现insertCourse()方法
     *  用于插入一条数据
     */
	@Override
	@Async
	public CourseDO insertCourse(CourseDO courseDO) {
	    courseDO.setCreateTime(new Date());
		courseDO.setModifyTime(new Date());
		List<CourseFrequencyDO> courseTimeList=courseDO.getCourseTimeList();
		courseDO.setCourseTimeList(null);
	    this.courseMapper.insert(courseDO);
	    if(courseTimeList!=null){
	    	courseTimeList.forEach(courseTime->{
	    		courseTime.setCourseid(courseDO.getCourseId());
	    		this.courseFrequencyService.insertCourseFrequency(courseTime);
			});
		}
		logger.info("receive:[courseDO:"+courseDO+"];--return:"+courseDO);
		return courseDO;
	}
	
	/**
	 *  实现updateCourse()方法
	 *  用于更新CourseDO数据
	 */
	@Override
	@Async
	public CourseDO updateCourse(CourseDO courseDO) {
	    courseDO.setModifyTime(new Date());
		this.courseMapper.updateById(courseDO);
		logger.info("receive:[courseDO:"+courseDO+"];--return:"+courseDO);
		return courseDO;	
	}

	/**
	 *  实现updateCourseField()方法
	 *  用于更新CourseDO部分数据
	 */
	@Override
	@Async
	public CourseDO updateCourseField(String data, Integer courseId) {
		Field[] fields = new CourseDO().getClass().getDeclaredFields();
		CourseDO courseDO = (CourseDO) getEntity.setTableField(
				data, CourseDO.class, fields, this.courseMapper.selectById(courseId));
		courseDO.setModifyTime(new Date());
		this.courseMapper.updateById(courseDO);
		JSONObject courseData = JSONObject.parseObject(data);
		if(courseData.containsKey("courseTimeList")){
			JSONArray courseTimeList= (JSONArray) courseData.get("courseTimeList");
			courseTimeList.forEach( courseTime->{
				this.courseFrequencyService.updateCourseTime(JSONObject.toJSONString(courseTime));
			});
		}

		logger.info("receive:[data:"+data+"--courseId:"+courseId+"];Intermediate variable:[--courseDO:"+courseDO+"];--return:"+courseDO);
		return courseDO;	
	}	
	/**
	 *  实现deleteCourseById()方法
	 *  用于删除对应Id的数据 
	 */
	@Override
	@Async
	public Boolean deleteCourseById(String courseId) {
		Boolean flag = false;
		Integer Id=Integer.valueOf(courseId);
		int singleDelete = this.courseMapper.deleteById(Id);
		QueryWrapper<CourseFrequencyDO> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(CourseFrequencyDO::getCourseid,Id);
		int frequencyDelete=this.courseFrequencyMapper.delete(queryWrapper);
        if(singleDelete == 1){
           flag = true; 
        }     	    
        logger.info("receive:[courseId:"+courseId+"];Intermediate variable:[--singleDelete:"+singleDelete+"];--return:"+flag);
		return flag;	
	}

	@Override
	public Boolean uploadCourseHomework(MultipartFile file,String courseId,String courseHomeworkId,String username){
		if (file.isEmpty()) {
			return false;
		}
		String fileName = file.getOriginalFilename();
		String filePath = "F:\\IJ\\teacherhelperv2\\courses\\"+courseId+"\\"+courseHomeworkId+"\\"+username+"\\";
		File dest = new File(filePath + fileName);
		File parentDir= dest.getParentFile();
		try {
			if(!parentDir.exists()){
				parentDir.mkdirs();
			}
			file.transferTo(dest);
			logger.info("上传成功");
			return true;
		} catch (IOException e) {
			logger.error(e.toString(), e);
		}
		return false;
	}

	@Override
	public List<String> getMyCourseHomeworkList(String courseId,String courseHomeworkId,String username){
		if(username!=null){
			String filePath = "F:\\IJ\\teacherhelperv2\\courses\\"+courseId+"\\"+courseHomeworkId+"\\"+username+"\\";
			File coursefile=new File(filePath);
			if(!coursefile.exists()){
				coursefile.mkdirs();
			}
			List<String> courseHomeworkList=MyFileUtils.findAllMyFileNames(coursefile);
			return courseHomeworkList;
		}
		else {
			String filePath = "F:\\IJ\\teacherhelperv2\\courses\\"+courseId+"\\"+courseHomeworkId+"\\";
			File coursefile=new File(filePath);
			if(!coursefile.exists()){
				coursefile.mkdirs();
			}
			List<String> courseHomeworkList=new ArrayList<>();
			courseHomeworkList=MyFileUtils.findAllFileNames(coursefile,"",courseHomeworkList);
			return courseHomeworkList;
		}
	}

	@Override
	public List<CourseDO> getMyCourseList(String studentId){
		List<ScoreDO> scoreDOList=this.scoreService.listScoreByOther(studentId,"studentId",null,null);
		List<Integer> courseIdList=scoreDOList.stream().map(ScoreDO::getCourseId).collect(Collectors.toList());
		List<CourseDO> courseDOs= this.courseMapper.selectBatchIds(courseIdList);
		courseDOs.forEach(courseDO -> {
			courseDO.setSpecDO(specMapper.selectById(courseDO.getSpecId()));
			courseDO.setCourseTimeList(courseFrequencyMapper
					.selectList(new QueryWrapper<CourseFrequencyDO>()
							.eq("courseId",courseDO.getCourseId())));
		});
		return courseDOs;
	}

}
