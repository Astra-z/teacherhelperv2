package com.spm.teacherhelperv2.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spm.teacherhelperv2.config.WebSocketServer;
import com.spm.teacherhelperv2.dao.StudentMentorMapper;
import com.spm.teacherhelperv2.entity.NoteDO;
import com.spm.teacherhelperv2.entity.StudentMentorDO;
import com.spm.teacherhelperv2.entity.UserNoteDO;
import com.spm.teacherhelperv2.manager.GetEntity;
import com.spm.teacherhelperv2.service.StudentMentorService;
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
 * StudentMentorServiceImpl服务实现类
 * @author zhangjr
 * @since 2020-06-13 20:45:02
 */
@Service
@Component("StudentMentorService")
public class StudentMentorServiceImpl implements StudentMentorService {
	private Logger logger = LoggerFactory.getLogger(StudentMentorServiceImpl.class);
	@Autowired
    private WebSocketServer webSocket;

	@Autowired(required = false)
	private StudentMentorMapper studentMentorMapper;
	private GetEntity getEntity = new GetEntity();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
    /**
     * 实现listStudentMentorByOther()方法
     * 用于根据特定条件值查询所有 StudentMentorDO数据
     */
	@Override
	public List<StudentMentorDO> listStudentMentorByOther(String fieldValue, String fieldName, String page, String limit) {
	    List<StudentMentorDO> studentMentorDOs = new ArrayList<StudentMentorDO>();
	    Field[] fields = StudentMentorDO.class.getDeclaredFields();
		String annotationValue = null;
		if(fieldName != null) {
		    annotationValue = getEntity.getAnnotationValue(fields, fieldName);
		    if(limit == null) {
		        studentMentorDOs = this.studentMentorMapper.selectList(new QueryWrapper<StudentMentorDO>()
					.eq(annotationValue, fieldValue));
			} else {
			    Page<StudentMentorDO> lpage = new Page<StudentMentorDO>(Integer.valueOf(page),Integer.valueOf(limit));
	            studentMentorDOs = this.studentMentorMapper.selectPage(lpage,new QueryWrapper<StudentMentorDO>()
					.eq(annotationValue, fieldValue)).getRecords();
			}
		}else {
		    if(limit == null) {
		        studentMentorDOs = this.studentMentorMapper.selectList(null);
			} else {
			    Page<StudentMentorDO> lpage = new Page<StudentMentorDO>(Integer.valueOf(page),Integer.valueOf(limit));
	            studentMentorDOs = this.studentMentorMapper.selectPage(lpage,null).getRecords();
			}
		}
        logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"--page:"+page+"--limit:"+limit+"];Intermediate variable:[--annotationValue:"+annotationValue+"];--return:"+studentMentorDOs);
        return studentMentorDOs;
	}
	
	    /**
     * 实现getStudentMentorById()方法
     * 用于根据Id查询对应单条数据 
     */
	@Override
	public StudentMentorDO getStudentMentorById(Long studentMentorId) {
		StudentMentorDO studentMentorDO=this.studentMentorMapper.selectById(studentMentorId);
		logger.info("receive:[studentMentorId:"+studentMentorId+"];--return:"+studentMentorDO);
		return studentMentorDO;
	}

    @Override
    public List<StudentMentorDO> findAllMyStudent(Integer userId) {
        return this.studentMentorMapper.findAllMyStudent(userId);
    }

    /**
     * 实现getStudentMentorByOther方法
     * 用于根据其他信息查询对应单条数据 
     */
	@Override
	public StudentMentorDO getStudentMentorByOther(String fieldValue, String fieldName) {
		Field[] fields = new StudentMentorDO().getClass().getDeclaredFields();
		try{
		    String annotationValue = getEntity.getAnnotationValue(fields, fieldName);
		    StudentMentorDO studentMentorDO = this.studentMentorMapper.selectOne(new QueryWrapper<StudentMentorDO>()
				    .eq(annotationValue, fieldValue));
		    logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"];Intermediate variable:[--annotationValue:"+annotationValue+"];--return:"+studentMentorDO);
		    return studentMentorDO;
		} catch (Exception e) {
		   return null;

		}
	}
	
    /**
     *  实现insertStudentMentor()方法
     *  用于插入一条数据
     */
	@Override
	@Async
	public StudentMentorDO insertStudentMentor(StudentMentorDO studentMentorDO) {
	    this.studentMentorMapper.insert(studentMentorDO);
		logger.info("receive:[studentMentorDO:"+studentMentorDO+"];--return:"+studentMentorDO);
		return studentMentorDO;
	}
	
	/**
	 *  实现updateStudentMentor()方法
	 *  用于更新StudentMentorDO数据
	 */
	@Override
	@Async
	public StudentMentorDO updateStudentMentor(StudentMentorDO studentMentorDO) {
		this.studentMentorMapper.updateById(studentMentorDO);
		logger.info("receive:[studentMentorDO:"+studentMentorDO+"];--return:"+studentMentorDO);
		return studentMentorDO;	
	}

	/**
	 *  实现updateStudentMentorField()方法
	 *  用于更新StudentMentorDO部分数据
	 */
	@Override
	@Async
	public StudentMentorDO updateStudentMentorField(String data, Long studentMentorId) {
		Field[] fields = new StudentMentorDO().getClass().getDeclaredFields();
		StudentMentorDO studentMentorDO = (StudentMentorDO) getEntity.setTableField(
				data, StudentMentorDO.class, fields, this.studentMentorMapper.selectById(studentMentorId));
		this.studentMentorMapper.updateById(studentMentorDO);
		logger.info("receive:[data:"+data+"--studentMentorId:"+studentMentorId+"];Intermediate variable:[--studentMentorDO:"+studentMentorDO+"];--return:"+studentMentorDO);
		return studentMentorDO;	
	}	
	/**
	 *  实现deleteStudentMentorById()方法
	 *  用于删除对应Id的数据 
	 */
	@Override
	@Async
	public Boolean deleteStudentMentorById(String studentMentorId) {
		Boolean flag = false;
        int singleDelete = this.studentMentorMapper.deleteById(studentMentorId);	
        if(singleDelete == 1){
           flag = true; 
        }     	    
        logger.info("receive:[studentMentorId:"+studentMentorId+"];Intermediate variable:[--singleDelete:"+singleDelete+"];--return:"+flag);
		return flag;	
	}

    @Override
    public void sendNote(JSONArray NoteList) {
        for (Object note : NoteList) {
            NoteDO noteDO=  JSONObject.parseObject(JSONObject.toJSONString(note),NoteDO.class);
            Boolean flag= webSocket.sendObjMessage(noteDO.getUserId().toString(),noteDO);
            if(!flag){
                if(UserNoteDO.userNoteList.containsKey(noteDO.getUserId())){
                    UserNoteDO.userNoteList.get(noteDO.getUserId()).add(noteDO);
                }else{
                    List<NoteDO> userNoteList=new ArrayList<>();
                    userNoteList.add(noteDO);
                    UserNoteDO.userNoteList.put(noteDO.getUserId(),userNoteList);
                }
            }
        }
    }
}
