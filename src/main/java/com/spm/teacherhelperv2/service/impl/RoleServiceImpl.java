package com.spm.teacherhelperv2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spm.teacherhelperv2.dao.RoleMapper;
import com.spm.teacherhelperv2.entity.RoleDO;
import com.spm.teacherhelperv2.manager.GetEntity;
import com.spm.teacherhelperv2.service.RoleService;
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
 * RoleServiceImpl服务实现类
 * @author zhangjr
 * @since 2020-05-16 17:28:26
 */
@Service
@Component("RoleService")
public class RoleServiceImpl implements RoleService {
	private Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	
	@Autowired(required = false)
	private RoleMapper roleMapper;
	private GetEntity getEntity = new GetEntity();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
    /**
     * 实现listRoleByOther()方法
     * 用于根据特定条件值查询所有 RoleDO数据
     */
	@Override
	public List<RoleDO> listRoleByOther(String fieldValue, String fieldName, String page, String limit) {
	    List<RoleDO> roleDOs = new ArrayList<RoleDO>();
	    Field[] fields = new RoleDO().getClass().getDeclaredFields();
		String annotationValue = null;
		if(fieldName != null) {
		    annotationValue = getEntity.getAnnotationValue(fields, fieldName);
		    if(limit == null) {
		        roleDOs = this.roleMapper.selectList(new QueryWrapper<RoleDO>()
					.eq(annotationValue, fieldValue));
			} else {
			    Page<RoleDO> lpage = new Page<RoleDO>(Integer.valueOf(page),Integer.valueOf(limit));
	            roleDOs = this.roleMapper.selectPage(lpage,new QueryWrapper<RoleDO>()
					.eq(annotationValue, fieldValue)).getRecords();
			}
		}else {
		    if(limit == null) {
		        roleDOs = this.roleMapper.selectList(null);
			} else {
			    Page<RoleDO> lpage = new Page<RoleDO>(Integer.valueOf(page),Integer.valueOf(limit));
	            roleDOs = this.roleMapper.selectPage(lpage,null).getRecords();
			}
		}
        logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"--page:"+page+"--limit:"+limit+"];Intermediate variable:[--annotationValue:"+annotationValue+"];--return:"+roleDOs);
        return roleDOs;
	}
	
	    /**
     * 实现getRoleById()方法
     * 用于根据Id查询对应单条数据 
     */
	@Override
	public RoleDO getRoleById(Long roleId) {
		RoleDO roleDO=this.roleMapper.selectById(roleId);
		logger.info("receive:[roleId:"+roleId+"];--return:"+roleDO);
		return roleDO;
	}
	
    /**
     * 实现getRoleByOther方法
     * 用于根据其他信息查询对应单条数据 
     */
	@Override
	public RoleDO getRoleByOther(String fieldValue, String fieldName) {
		Field[] fields = new RoleDO().getClass().getDeclaredFields();
		try{
		    String annotationValue = getEntity.getAnnotationValue(fields, fieldName);
		    RoleDO roleDO = this.roleMapper.selectOne(new QueryWrapper<RoleDO>()
				    .eq(annotationValue, fieldValue));
		    logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"];Intermediate variable:[--annotationValue:"+annotationValue+"];--return:"+roleDO);
		    return roleDO;
		} catch (Exception e) {
		   return null;
		}
	}
	
    /**
     *  实现insertRole()方法
     *  用于插入一条数据
     */
	@Override
	@Async
	public RoleDO insertRole(RoleDO roleDO) {
	    roleDO.setCreateTime(new Date());
		roleDO.setModifyTime(new Date());
	    this.roleMapper.insert(roleDO);
		logger.info("receive:[roleDO:"+roleDO+"];--return:"+roleDO);
		return roleDO;
	}
	
	/**
	 *  实现updateRole()方法
	 *  用于更新RoleDO数据
	 */
	@Override
	@Async
	public RoleDO updateRole(RoleDO roleDO) {
	    roleDO.setModifyTime(new Date());
		this.roleMapper.updateById(roleDO);
		logger.info("receive:[roleDO:"+roleDO+"];--return:"+roleDO);
		return roleDO;	
	}

	/**
	 *  实现updateRoleField()方法
	 *  用于更新RoleDO部分数据
	 */
	@Override
	@Async
	public RoleDO updateRoleField(String data, Long roleId) {
		Field[] fields = new RoleDO().getClass().getDeclaredFields();
		RoleDO roleDO = (RoleDO) getEntity.setTableField(
				data, RoleDO.class, fields, this.roleMapper.selectById(roleId));
		roleDO.setModifyTime(new Date());
		this.roleMapper.updateById(roleDO);
		logger.info("receive:[data:"+data+"--roleId:"+roleId+"];Intermediate variable:[--roleDO:"+roleDO+"];--return:"+roleDO);
		return roleDO;	
	}	
	/**
	 *  实现deleteRoleById()方法
	 *  用于删除对应Id的数据 
	 */
	@Override
	@Async
	public Boolean deleteRoleById(String roleId) {
		Boolean flag = false;
        int singleDelete = this.roleMapper.deleteById(roleId);	
        if(singleDelete == 1){
           flag = true; 
        }     	    
        logger.info("receive:[roleId:"+roleId+"];Intermediate variable:[--singleDelete:"+singleDelete+"];--return:"+flag);
		return flag;	
	}

    @Override
    public List<RoleDO> findUserRole(String username) {
        return roleMapper.findUserRole(username);
    }
}
