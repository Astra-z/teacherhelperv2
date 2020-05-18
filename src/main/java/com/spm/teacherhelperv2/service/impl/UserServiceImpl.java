package com.spm.teacherhelperv2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spm.teacherhelperv2.dao.RoleMapper;
import com.spm.teacherhelperv2.dao.UserMapper;
import com.spm.teacherhelperv2.entity.RoleDO;
import com.spm.teacherhelperv2.entity.UserDO;
import com.spm.teacherhelperv2.manager.GetEntity;
import com.spm.teacherhelperv2.service.UserService;
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
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * UserServiceImpl服务实现类
 * @author zhangjr
 * @since 2020-05-15 22:42:58
 */
@Service
@Component("UserService")
public class UserServiceImpl implements UserService {
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired(required = false)
	private UserMapper userMapper;

	@Autowired(required = false)
	private RoleMapper roleMapper;
	private GetEntity getEntity = new GetEntity();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
    /**
     * 实现listUserByOther()方法
     * 用于根据特定条件值查询所有 UserDO数据
     */
	@Override
	public List<UserDO> listUserByOther(String fieldValue, String fieldName, String page, String limit) {
	    List<UserDO> userDOs = new ArrayList<UserDO>();
	    Field[] fields = new UserDO().getClass().getDeclaredFields();
		String annotationValue = null;
		if(fieldName != null) {
		    annotationValue = getEntity.getAnnotationValue(fields, fieldName);
		    if(limit == null) {
		        userDOs = this.userMapper.selectList(new QueryWrapper<UserDO>()
					.eq(annotationValue, fieldValue));
		        for(UserDO userDO:userDOs){
                    List<RoleDO> roleList=roleMapper.findUserRole(userDO.getUsername());
                    List<String> roleNames=roleList.stream().map(RoleDO::getRoleName).collect(Collectors.toList());
                    userDO.setRoleName(roleNames);
                }
			} else {
			    Page<UserDO> lpage = new Page<UserDO>(Integer.valueOf(page),Integer.valueOf(limit));
	            userDOs = this.userMapper.selectPage(lpage,new QueryWrapper<UserDO>()
					.eq(annotationValue, fieldValue)).getRecords();
                for(UserDO userDO:userDOs){
                    List<RoleDO> roleList=roleMapper.findUserRole(userDO.getUsername());
                    List<String> roleNames=roleList.stream().map(RoleDO::getRoleName).collect(Collectors.toList());
                    userDO.setRoleName(roleNames);
                }
			}
		}else {
		    if(limit == null) {
		        userDOs = this.userMapper.selectList(null);
                for(UserDO userDO:userDOs){
                    List<RoleDO> roleList=roleMapper.findUserRole(userDO.getUsername());
                    List<String> roleNames=roleList.stream().map(RoleDO::getRoleName).collect(Collectors.toList());
                    userDO.setRoleName(roleNames);
                }
			} else {
			    Page<UserDO> lpage = new Page<UserDO>(Integer.valueOf(page),Integer.valueOf(limit));
	            userDOs = this.userMapper.selectPage(lpage,null).getRecords();
                for(UserDO userDO:userDOs){
                    List<RoleDO> roleList=roleMapper.findUserRole(userDO.getUsername());
                    List<String> roleNames=roleList.stream().map(RoleDO::getRoleName).collect(Collectors.toList());
                    userDO.setRoleName(roleNames);
                }
			}
		}
        logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"--page:"+page+"--limit:"+limit+"];Intermediate variable:[--annotationValue:"+annotationValue+"];--return:"+userDOs);
        return userDOs;
	}
	
	    /**
     * 实现getUserById()方法
     * 用于根据Id查询对应单条数据 
     */
	@Override
	public UserDO getUserById(Long userId) {
		UserDO userDO=this.userMapper.selectById(userId);
		List<RoleDO> roleList=roleMapper.findUserRole(userDO.getUsername());
		List<String> roleNames=roleList.stream().map(RoleDO::getRoleName).collect(Collectors.toList());
		userDO.setRoleName(roleNames);
		logger.info("receive:[userId:"+userId+"];--return:"+userDO);
		return userDO;
	}
	
    /**
     * 实现getUserByOther方法
     * 用于根据其他信息查询对应单条数据 
     */
	@Override
	public UserDO getUserByOther(String fieldValue, String fieldName) {
		Field[] fields = new UserDO().getClass().getDeclaredFields();		
		try{
		    String annotationValue = getEntity.getAnnotationValue(fields, fieldName);
		    UserDO userDO = this.userMapper.selectOne(new QueryWrapper<UserDO>()
				    .eq(annotationValue, fieldValue));
			List<RoleDO> roleList=roleMapper.findUserRole(userDO.getUsername());
			List<String> roleNames=roleList.stream().map(RoleDO::getRoleName).collect(Collectors.toList());
			userDO.setRoleName(roleNames);
		    logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"];Intermediate variable:[--annotationValue:"+annotationValue+"];--return:"+userDO);
		    return userDO;
		} catch (Exception e) {
		   return null;
		}
	}
	
    /**
     *  实现insertUser()方法
     *  用于插入一条数据
     */
	@Override
	@Async
	public UserDO insertUser(UserDO userDO) {
	    userDO.setCreateTime(new Date());
		userDO.setModifyTime(new Date());
	    this.userMapper.insert(userDO);
		logger.info("receive:[userDO:"+userDO+"];--return:"+userDO);
		return userDO;
	}
	
	/**
	 *  实现updateUser()方法
	 *  用于更新UserDO数据
	 */
	@Override
	@Async
	public UserDO updateUser(UserDO userDO) {	
	    userDO.setModifyTime(new Date());
		this.userMapper.updateById(userDO);
		logger.info("receive:[userDO:"+userDO+"];--return:"+userDO);
		return userDO;	
	}

	/**
	 *  实现updateUserField()方法
	 *  用于更新UserDO部分数据
	 */
	@Override
	@Async
	public UserDO updateUserField(String data, Long userId) {
		Field[] fields = new UserDO().getClass().getDeclaredFields();				
		UserDO userDO = (UserDO) getEntity.setTableField(
				data, UserDO.class, fields, this.userMapper.selectById(userId));		
		userDO.setModifyTime(new Date());
		this.userMapper.updateById(userDO);
		logger.info("receive:[data:"+data+"--userId:"+userId+"];Intermediate variable:[--userDO:"+userDO+"];--return:"+userDO);
		return userDO;	
	}	
	/**
	 *  实现deleteUserById()方法
	 *  用于删除对应Id的数据 
	 */
	@Override
	@Async
	public Boolean deleteUserById(String userId) {
		Boolean flag = false;
        int singleDelete = this.userMapper.deleteById(userId);	
        if(singleDelete == 1){
           flag = true; 
        }     	    
        logger.info("receive:[userId:"+userId+"];Intermediate variable:[--singleDelete:"+singleDelete+"];--return:"+flag);
		return flag;	
	}
}
