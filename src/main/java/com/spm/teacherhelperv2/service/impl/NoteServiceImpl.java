package com.spm.teacherhelperv2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spm.teacherhelperv2.config.DelayedSender;
import com.spm.teacherhelperv2.dao.NoteMapper;
import com.spm.teacherhelperv2.entity.NoteDO;
import com.spm.teacherhelperv2.manager.GetEntity;
import com.spm.teacherhelperv2.service.NoteService;
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
import java.util.concurrent.Delayed;

/**
 * NoteServiceImpl服务实现类
 * @author zhangjr
 * @since 2020-06-08 16:33:17
 */
@Service
@Component("NoteService")
public class NoteServiceImpl implements NoteService {
	private Logger logger = LoggerFactory.getLogger(NoteServiceImpl.class);
	
	@Autowired(required = false)
	private NoteMapper noteMapper;

	@Autowired
	private DelayedSender delayedSender;
	private GetEntity getEntity = new GetEntity();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
    /**
     * 实现listNoteByOther()方法
     * 用于根据特定条件值查询所有 NoteDO数据
     */
	@Override
	public List<NoteDO> listNoteByOther(String fieldValue, String fieldName, String page, String limit) {
	    List<NoteDO> noteDOs = new ArrayList<NoteDO>();
	    Field[] fields = new NoteDO().getClass().getDeclaredFields();
		String annotationValue = null;
		if(fieldName != null) {
		    annotationValue = getEntity.getAnnotationValue(fields, fieldName);
		    if(limit == null) {
		        noteDOs = this.noteMapper.selectList(new QueryWrapper<NoteDO>()
					.eq(annotationValue, fieldValue));
			} else {
			    Page<NoteDO> lpage = new Page<NoteDO>(Integer.valueOf(page),Integer.valueOf(limit));
	            noteDOs = this.noteMapper.selectPage(lpage,new QueryWrapper<NoteDO>()
					.eq(annotationValue, fieldValue)).getRecords();
			}
		}else {
		    if(limit == null) {
		        noteDOs = this.noteMapper.selectList(null);
			} else {
			    Page<NoteDO> lpage = new Page<NoteDO>(Integer.valueOf(page),Integer.valueOf(limit));
	            noteDOs = this.noteMapper.selectPage(lpage,null).getRecords();
			}
		}
        logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"--page:"+page+"--limit:"+limit+"];Intermediate variable:[--annotationValue:"+annotationValue+"];--return:"+noteDOs);
        return noteDOs;
	}
	
	    /**
     * 实现getNoteById()方法
     * 用于根据Id查询对应单条数据 
     */
	@Override
	public NoteDO getNoteById(Long noteId) {
		NoteDO noteDO=this.noteMapper.selectById(noteId);
		logger.info("receive:[noteId:"+noteId+"];--return:"+noteDO);
		return noteDO;
	}
	
    /**
     * 实现getNoteByOther方法
     * 用于根据其他信息查询对应单条数据 
     */
	@Override
	public NoteDO getNoteByOther(String fieldValue, String fieldName) {
		Field[] fields = new NoteDO().getClass().getDeclaredFields();
		try{
		    String annotationValue = getEntity.getAnnotationValue(fields, fieldName);
		    NoteDO noteDO = this.noteMapper.selectOne(new QueryWrapper<NoteDO>()
				    .eq(annotationValue, fieldValue));
		    logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"];Intermediate variable:[--annotationValue:"+annotationValue+"];--return:"+noteDO);
		    return noteDO;
		} catch (Exception e) {
		   return null;
		}
	}
	
    /**
     *  实现insertNote()方法
     *  用于插入一条数据
     */
	@Override
	@Async
	public NoteDO insertNote(NoteDO noteDO) {
	    noteDO.setCreateTime(new Date());
		noteDO.setModifyTime(new Date());
	    this.noteMapper.insert(noteDO);
		delayedSender.send(noteDO,noteDO.getEndTime().getTime()-noteDO.getCreateTime().getTime());
		logger.info("receive:[noteDO:"+noteDO+"];--return:"+noteDO);
		return noteDO;
	}

	/**
	 *  实现updateNote()方法
	 *  用于更新NoteDO数据
	 */
	@Override
	@Async
	public NoteDO updateNote(NoteDO noteDO) {
	    noteDO.setModifyTime(new Date());
		this.noteMapper.updateById(noteDO);
		logger.info("receive:[noteDO:"+noteDO+"];--return:"+noteDO);
		return noteDO;	
	}

	/**
	 *  实现updateNoteField()方法
	 *  用于更新NoteDO部分数据
	 */
	@Override
	@Async
	public NoteDO updateNoteField(String data, Long noteId) {
		Field[] fields = NoteDO.class.getDeclaredFields();
		NoteDO noteDO = (NoteDO) getEntity.setTableField(
				data, NoteDO.class, fields, this.noteMapper.selectById(noteId));
		noteDO.setModifyTime(new Date());
		this.noteMapper.updateById(noteDO);
		if(noteDO.getNoteSwitch()){
			delayedSender.send(noteDO,noteDO.getEndTime().getTime()-noteDO.getModifyTime().getTime());
		}
		logger.info("receive:[data:"+data+"--noteId:"+noteId+"];Intermediate variable:[--noteDO:"+noteDO+"];--return:"+noteDO);
		return noteDO;	
	}	
	/**
	 *  实现deleteNoteById()方法
	 *  用于删除对应Id的数据 
	 */
	@Override
	@Async
	public Boolean deleteNoteById(String noteId) {
		Boolean flag = false;
        int singleDelete = this.noteMapper.deleteById(Long.valueOf(noteId));
        if(singleDelete == 1){
           flag = true; 
        }     	    
        logger.info("receive:[noteId:"+noteId+"];Intermediate variable:[--singleDelete:"+singleDelete+"];--return:"+flag);
		return flag;	
	}
}
