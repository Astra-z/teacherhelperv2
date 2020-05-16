package com.spm.teacherhelperv2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spm.teacherhelperv2.dao.SpecMapper;
import com.spm.teacherhelperv2.entity.SpecDO;
import com.spm.teacherhelperv2.manager.GetEntity;
import com.spm.teacherhelperv2.service.SpecService;
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
 * SpecServiceImpl服务实现类
 * @author zhangjr
 * @since 2020-05-15 22:42:58
 */
@Service
@Component("SpecService")
public class SpecServiceImpl implements SpecService {
	private Logger logger = LoggerFactory.getLogger(SpecServiceImpl.class);
	
	@Autowired(required = false)
	private SpecMapper specMapper;   
	private GetEntity getEntity = new GetEntity();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
    /**
     * 实现listSpecByOther()方法
     * 用于根据特定条件值查询所有 SpecDO数据
     */
	@Override
	public List<SpecDO> listSpecByOther(String fieldValue, String fieldName, String page, String limit) {
	    List<SpecDO> specDOs = new ArrayList<SpecDO>();
	    Field[] fields = new SpecDO().getClass().getDeclaredFields();
		String annotationValue = null;
		if(fieldName != null) {
		    annotationValue = getEntity.getAnnotationValue(fields, fieldName);
		    if(limit == null) {
		        specDOs = this.specMapper.selectList(new QueryWrapper<SpecDO>()
					.eq(annotationValue, fieldValue));
			} else {
			    Page<SpecDO> lpage = new Page<SpecDO>(Integer.valueOf(page),Integer.valueOf(limit));
	            specDOs = this.specMapper.selectPage(lpage,new QueryWrapper<SpecDO>()
					.eq(annotationValue, fieldValue)).getRecords();
			}
		}else {
		    if(limit == null) {
		        specDOs = this.specMapper.selectList(null);
			} else {
			    Page<SpecDO> lpage = new Page<SpecDO>(Integer.valueOf(page),Integer.valueOf(limit));
	            specDOs = this.specMapper.selectPage(lpage,null).getRecords();
			}
		}
        logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"--page:"+page+"--limit:"+limit+"];Intermediate variable:[--annotationValue:"+annotationValue+"];--return:"+specDOs);
        return specDOs;
	}
	
	    /**
     * 实现getSpecById()方法
     * 用于根据Id查询对应单条数据 
     */
	@Override
	public SpecDO getSpecById(Long specId) {
		SpecDO specDO=this.specMapper.selectById(specId);
		logger.info("receive:[specId:"+specId+"];--return:"+specDO);
		return specDO;
	}
	
    /**
     * 实现getSpecByOther方法
     * 用于根据其他信息查询对应单条数据 
     */
	@Override
	public SpecDO getSpecByOther(String fieldValue, String fieldName) {
		Field[] fields = new SpecDO().getClass().getDeclaredFields();		
		try{
		    String annotationValue = getEntity.getAnnotationValue(fields, fieldName);
		    SpecDO specDO = this.specMapper.selectOne(new QueryWrapper<SpecDO>()
				    .eq(annotationValue, fieldValue));
		    logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"];Intermediate variable:[--annotationValue:"+annotationValue+"];--return:"+specDO);
		    return specDO;
		} catch (Exception e) {
		   return null;
		}
	}
	
    /**
     *  实现insertSpec()方法
     *  用于插入一条数据
     */
	@Override
	@Async
	public SpecDO insertSpec(SpecDO specDO) {
	    specDO.setCreateTime(new Date());
		specDO.setModifyTime(new Date());
	    this.specMapper.insert(specDO);
		logger.info("receive:[specDO:"+specDO+"];--return:"+specDO);
		return specDO;
	}
	
	/**
	 *  实现updateSpec()方法
	 *  用于更新SpecDO数据
	 */
	@Override
	@Async
	public SpecDO updateSpec(SpecDO specDO) {	
	    specDO.setModifyTime(new Date());
		this.specMapper.updateById(specDO);
		logger.info("receive:[specDO:"+specDO+"];--return:"+specDO);
		return specDO;	
	}

	/**
	 *  实现updateSpecField()方法
	 *  用于更新SpecDO部分数据
	 */
	@Override
	@Async
	public SpecDO updateSpecField(String data, Long specId) {
		Field[] fields = new SpecDO().getClass().getDeclaredFields();				
		SpecDO specDO = (SpecDO) getEntity.setTableField(
				data, SpecDO.class, fields, this.specMapper.selectById(specId));		
		specDO.setModifyTime(new Date());
		this.specMapper.updateById(specDO);
		logger.info("receive:[data:"+data+"--specId:"+specId+"];Intermediate variable:[--specDO:"+specDO+"];--return:"+specDO);
		return specDO;	
	}	
	/**
	 *  实现deleteSpecById()方法
	 *  用于删除对应Id的数据 
	 */
	@Override
	@Async
	public Boolean deleteSpecById(String specId) {
		Boolean flag = false;
        int singleDelete = this.specMapper.deleteById(specId);	
        if(singleDelete == 1){
           flag = true; 
        }     	    
        logger.info("receive:[specId:"+specId+"];Intermediate variable:[--singleDelete:"+singleDelete+"];--return:"+flag);
		return flag;	
	}
}
