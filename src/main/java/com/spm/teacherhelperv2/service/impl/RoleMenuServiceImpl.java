package com.spm.teacherhelperv2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spm.teacherhelperv2.dao.RoleMenuMapper;
import com.spm.teacherhelperv2.entity.RoleMenuDO;
import com.spm.teacherhelperv2.manager.GetEntity;
import com.spm.teacherhelperv2.service.RoleMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * RoleMenuServiceImpl服务实现类
 * @author zhangjr
 * @since 2020-05-16 17:28:26
 */
@Service
@Component("RoleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper,RoleMenuDO> implements RoleMenuService {
	private Logger logger = LoggerFactory.getLogger(RoleMenuServiceImpl.class);
	
	@Autowired(required = false)
	private RoleMenuMapper roleMenuMapper;
	private GetEntity getEntity = new GetEntity();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
    /**
     * 实现listRoleMenuByOther()方法
     * 用于根据特定条件值查询所有 RoleMenuDO数据
     */
	@Override
	public List<RoleMenuDO> listRoleMenuByOther(String fieldValue, String fieldName, String page, String limit) {
	    List<RoleMenuDO> roleMenuDOs = new ArrayList<RoleMenuDO>();
	    Field[] fields = new RoleMenuDO().getClass().getDeclaredFields();
		String annotationValue = null;
		if(fieldName != null) {
		    annotationValue = getEntity.getAnnotationValue(fields, fieldName);
		    if(limit == null) {
		        roleMenuDOs = this.roleMenuMapper.selectList(new QueryWrapper<RoleMenuDO>()
					.eq(annotationValue, fieldValue));
			} else {
			    Page<RoleMenuDO> lpage = new Page<RoleMenuDO>(Integer.valueOf(page),Integer.valueOf(limit));
	            roleMenuDOs = this.roleMenuMapper.selectPage(lpage,new QueryWrapper<RoleMenuDO>()
					.eq(annotationValue, fieldValue)).getRecords();
			}
		}else {
		    if(limit == null) {
		        roleMenuDOs = this.roleMenuMapper.selectList(null);
			} else {
			    Page<RoleMenuDO> lpage = new Page<RoleMenuDO>(Integer.valueOf(page),Integer.valueOf(limit));
	            roleMenuDOs = this.roleMenuMapper.selectPage(lpage,null).getRecords();
			}
		}
        logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"--page:"+page+"--limit:"+limit+"];Intermediate variable:[--annotationValue:"+annotationValue+"];--return:"+roleMenuDOs);
        return roleMenuDOs;
	}

	    /**
     * 实现getRoleMenuById()方法
     * 用于根据Id查询对应单条数据 
     */
	@Override
	public RoleMenuDO getRoleMenuById(Long roleMenuId) {
		RoleMenuDO roleMenuDO=this.roleMenuMapper.selectById(roleMenuId);
		logger.info("receive:[roleMenuId:"+roleMenuId+"];--return:"+roleMenuDO);
		return roleMenuDO;
	}
	
    /**
     * 实现getRoleMenuByOther方法
     * 用于根据其他信息查询对应单条数据 
     */
	@Override
	public RoleMenuDO getRoleMenuByOther(String fieldValue, String fieldName) {
		Field[] fields = new RoleMenuDO().getClass().getDeclaredFields();
		try{
		    String annotationValue = getEntity.getAnnotationValue(fields, fieldName);
		    RoleMenuDO roleMenuDO = this.roleMenuMapper.selectOne(new QueryWrapper<RoleMenuDO>()
				    .eq(annotationValue, fieldValue));
		    logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"];Intermediate variable:[--annotationValue:"+annotationValue+"];--return:"+roleMenuDO);
		    return roleMenuDO;
		} catch (Exception e) {
		   return null;
		}
	}
	
    /**
     *  实现insertRoleMenu()方法
     *  用于插入一条数据
     */
	@Override
	@Async
	public RoleMenuDO insertRoleMenu(RoleMenuDO roleMenuDO) {
	    this.roleMenuMapper.insert(roleMenuDO);
		logger.info("receive:[roleMenuDO:"+roleMenuDO+"];--return:"+roleMenuDO);
		return roleMenuDO;
	}

	public Boolean insertRoleMenuByBatchId(List<RoleMenuDO> roleMenuDOs) {
		Long roleids=roleMenuDOs.get(0).getRoleId();
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("ROLE_ID",roleids);
		this.roleMenuMapper.deleteByMap(columnMap);
		this.saveBatch(roleMenuDOs);
		return true;
	}

	
	/**
	 *  实现updateRoleMenu()方法
	 *  用于更新RoleMenuDO数据
	 */
	@Override
	@Async
	public RoleMenuDO updateRoleMenu(RoleMenuDO roleMenuDO) {
		this.roleMenuMapper.updateById(roleMenuDO);
		logger.info("receive:[roleMenuDO:"+roleMenuDO+"];--return:"+roleMenuDO);
		return roleMenuDO;	
	}

	/**
	 *  实现updateRoleMenuField()方法
	 *  用于更新RoleMenuDO部分数据
	 */
	@Override
	@Async
	public RoleMenuDO updateRoleMenuField(String data, Long roleMenuId) {
		Field[] fields = new RoleMenuDO().getClass().getDeclaredFields();
		RoleMenuDO roleMenuDO = (RoleMenuDO) getEntity.setTableField(
				data, RoleMenuDO.class, fields, this.roleMenuMapper.selectById(roleMenuId));
		this.roleMenuMapper.updateById(roleMenuDO);
		logger.info("receive:[data:"+data+"--roleMenuId:"+roleMenuId+"];Intermediate variable:[--roleMenuDO:"+roleMenuDO+"];--return:"+roleMenuDO);
		return roleMenuDO;	
	}	
	/**
	 *  实现deleteRoleMenuById()方法
	 *  用于删除对应Id的数据 
	 */
	@Override
	@Async
	public Boolean deleteRoleMenuById(String roleMenuId) {
		Boolean flag = false;
        int singleDelete = this.roleMenuMapper.deleteById(roleMenuId);	
        if(singleDelete == 1){
           flag = true; 
        }     	    
        logger.info("receive:[roleMenuId:"+roleMenuId+"];Intermediate variable:[--singleDelete:"+singleDelete+"];--return:"+flag);
		return flag;	
	}
}
