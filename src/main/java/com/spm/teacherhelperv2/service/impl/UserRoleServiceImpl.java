package com.spm.teacherhelperv2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spm.teacherhelperv2.dao.UserRoleMapper;
import com.spm.teacherhelperv2.entity.UserRoleDO;
import com.spm.teacherhelperv2.manager.GetEntity;
import com.spm.teacherhelperv2.service.UserRoleService;
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
 * UserRoleServiceImpl服务实现类
 * @author zhangjr
 * @since 2020-05-16 17:28:27
 */
@Service
@Component("UserRoleService")
public class UserRoleServiceImpl implements UserRoleService {
	private Logger logger = LoggerFactory.getLogger(UserRoleServiceImpl.class);
	
	@Autowired(required = false)
	private UserRoleMapper userRoleMapper;
	private GetEntity getEntity = new GetEntity();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");




	/**
	**/
	public List<UserRoleDO> listUserRoleByRoleId(String roleID) {
		Integer id = Integer.valueOf(roleID);
		QueryWrapper<UserRoleDO> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(UserRoleDO::getRoleId,roleID);
		return this.userRoleMapper.selectList(queryWrapper);

	}


		/**
         * 实现listUserRoleByOther()方法
         * 用于根据特定条件值查询所有 UserRoleDO数据
         */
	@Override
	public List<UserRoleDO> listUserRoleByOther(String fieldValue, String fieldName, String page, String limit) {
	    List<UserRoleDO> userRoleDOs = new ArrayList<UserRoleDO>();
	    Field[] fields = new UserRoleDO().getClass().getDeclaredFields();
		String annotationValue = null;
		if(fieldName != null) {
		    annotationValue = getEntity.getAnnotationValue(fields, fieldName);
		    if(limit == null) {
		        userRoleDOs = this.userRoleMapper.selectList(new QueryWrapper<UserRoleDO>()
					.eq(annotationValue, fieldValue));
			} else {
			    Page<UserRoleDO> lpage = new Page<UserRoleDO>(Integer.valueOf(page),Integer.valueOf(limit));
	            userRoleDOs = this.userRoleMapper.selectPage(lpage,new QueryWrapper<UserRoleDO>()
					.eq(annotationValue, fieldValue)).getRecords();
			}
		}else {
		    if(limit == null) {
		        userRoleDOs = this.userRoleMapper.selectList(null);
			} else {
			    Page<UserRoleDO> lpage = new Page<UserRoleDO>(Integer.valueOf(page),Integer.valueOf(limit));
	            userRoleDOs = this.userRoleMapper.selectPage(lpage,null).getRecords();
			}
		}
        logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"--page:"+page+"--limit:"+limit+"];Intermediate variable:[--annotationValue:"+annotationValue+"];--return:"+userRoleDOs);
        return userRoleDOs;
	}
	
	    /**
     * 实现getUserRoleById()方法
     * 用于根据Id查询对应单条数据 
     */
	@Override
	public UserRoleDO getUserRoleById(Long userRoleId) {
		UserRoleDO userRoleDO=this.userRoleMapper.selectById(userRoleId);
		logger.info("receive:[userRoleId:"+userRoleId+"];--return:"+userRoleDO);
		return userRoleDO;
	}
	
    /**
     * 实现getUserRoleByOther方法
     * 用于根据其他信息查询对应单条数据 
     */
	@Override
	public UserRoleDO getUserRoleByOther(String fieldValue, String fieldName) {
		Field[] fields = new UserRoleDO().getClass().getDeclaredFields();
		try{
		    String annotationValue = getEntity.getAnnotationValue(fields, fieldName);
		    UserRoleDO userRoleDO = this.userRoleMapper.selectOne(new QueryWrapper<UserRoleDO>()
				    .eq(annotationValue, fieldValue));
		    logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"];Intermediate variable:[--annotationValue:"+annotationValue+"];--return:"+userRoleDO);
		    return userRoleDO;
		} catch (Exception e) {
		   return null;
		}
	}
	
    /**
     *  实现insertUserRole()方法
     *  用于插入一条数据
     */
	@Override
	@Async
	public UserRoleDO insertUserRole(UserRoleDO userRoleDO) {

	    this.userRoleMapper.insert(userRoleDO);
		logger.info("receive:[userRoleDO:"+userRoleDO+"];--return:"+userRoleDO);
		return userRoleDO;
	}
	
	/**
	 *  实现updateUserRole()方法
	 *  用于更新UserRoleDO数据
	 */
	@Override
	@Async
	public UserRoleDO updateUserRole(UserRoleDO userRoleDO) {

		this.userRoleMapper.updateById(userRoleDO);
		logger.info("receive:[userRoleDO:"+userRoleDO+"];--return:"+userRoleDO);
		return userRoleDO;	
	}

	/**
	 *  实现updateUserRoleField()方法
	 *  用于更新UserRoleDO部分数据
	 */
	@Override
	@Async
	public UserRoleDO updateUserRoleField(String data, Long userRoleId) {
		Field[] fields = new UserRoleDO().getClass().getDeclaredFields();
		UserRoleDO userRoleDO = (UserRoleDO) getEntity.setTableField(
				data, UserRoleDO.class, fields, this.userRoleMapper.selectById(userRoleId));
		this.userRoleMapper.updateById(userRoleDO);
		logger.info("receive:[data:"+data+"--userRoleId:"+userRoleId+"];Intermediate variable:[--userRoleDO:"+userRoleDO+"];--return:"+userRoleDO);
		return userRoleDO;	
	}	
	/**
	 *  实现deleteUserRoleById()方法
	 *  用于删除对应Id的数据 
	 */
	@Override
	@Async
	public Boolean deleteUserRoleById(String userRoleId) {
		Boolean flag = false;
        int singleDelete = this.userRoleMapper.deleteById(userRoleId);	
        if(singleDelete == 1){
           flag = true; 
        }     	    
        logger.info("receive:[userRoleId:"+userRoleId+"];Intermediate variable:[--singleDelete:"+singleDelete+"];--return:"+flag);
		return flag;	
	}
}
