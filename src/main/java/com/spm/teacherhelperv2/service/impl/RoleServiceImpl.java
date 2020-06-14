package com.spm.teacherhelperv2.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spm.teacherhelperv2.dao.RoleMapper;
import com.spm.teacherhelperv2.dao.RoleMenuMapper;
import com.spm.teacherhelperv2.entity.CourseFrequencyDO;
import com.spm.teacherhelperv2.entity.RoleDO;
import com.spm.teacherhelperv2.entity.RoleMenuDO;
import com.spm.teacherhelperv2.entity.UserRoleDO;
import com.spm.teacherhelperv2.manager.GetEntity;
import com.spm.teacherhelperv2.service.RoleMenuService;
import com.spm.teacherhelperv2.service.RoleService;
import com.spm.teacherhelperv2.service.UserRoleService;
import org.apache.commons.beanutils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * RoleServiceImpl服务实现类
 * @author zhangjr
 * @since 2020-05-16 17:28:26
 */
@Service
@Component("RoleService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {
	private Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	private static final ObjectMapper objectMapper=new ObjectMapper();
	@Autowired(required = false)
	private RoleMapper roleMapper;

	@Autowired(required = false)
	@Qualifier("UserRoleService")
	private UserRoleServiceImpl userRoleService;

	@Autowired
	@Qualifier("RoleMenuService")
	private RoleMenuServiceImpl roleMenuService;
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

		roleDOs.forEach( role->{
            List<RoleMenuDO> roleMenuDOList= this.roleMenuService.listRoleMenuByOther(role.getRoleId().toString(),"roleId",null,null);
            List<Long> MenuList=roleMenuDOList.stream().map(RoleMenuDO::getMenuId).collect(Collectors.toList());
            role.setMenuIdList(MenuList);
        });
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
	public RoleDO insertRole(String data) {
		//分离“menuIdList”
		JSONObject fieldJson = JSONObject.parseObject(data);
		fieldJson.remove("menuIdList");
		//插入角色
		String roledata=JSONObject.toJSONString(fieldJson);
		RoleDO roleDO=JSONObject.parseObject(roledata,RoleDO.class);
	    roleDO.setCreateTime(new Date());
		roleDO.setModifyTime(new Date());
	    this.roleMapper.insert(roleDO);
	    //操作关联表
		JSONObject fieldJsonAll = JSONObject.parseObject(data);
		fieldJsonAll.put("roleId",roleDO.getRoleId());
		this.insertRoleMenu(fieldJsonAll.toJSONString());

		logger.info("receive:[roleDO:"+roleDO+"];--return:"+roleDO);
		return roleDO;
	}

	/**
	 * 操作关联表
	 * @param data
	 * @return
	 */
	private RoleDO insertRoleMenu(String data){
		JSONObject fieldJson = JSONObject.parseObject(data);
		JSONArray jsonArray = (JSONArray) fieldJson.get("menuIdList");
		fieldJson.remove("menuIdList");
		data=JSONObject.toJSONString(fieldJson);
		RoleDO roleDO=JSONObject.parseObject(data,RoleDO.class);

		//更新关联表rolemenu
		List<RoleMenuDO> roleMenuDOs=new ArrayList<>();
		jsonArray.forEach(menuID->{
			RoleMenuDO roleMenuDO=new RoleMenuDO();
			roleMenuDO.setMenuId(objectMapper.convertValue(menuID,Long.class));
			roleMenuDO.setRoleId(roleDO.getRoleId());
			roleMenuDOs.add(roleMenuDO);
		});
		this.roleMenuService.insertRoleMenuByBatchId(roleMenuDOs);

		return roleDO;
	}
	
	/**
	 *  实现updateRole()方法
	 *  用于更新RoleDO数据
	 */
	@Override
	@Async
	public RoleDO updateRole(String data) {
		//操作关联表
		RoleDO roleDO=this.insertRoleMenu(data);

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
		JSONObject fieldJson = JSONObject.parseObject(data);
		JSONArray jsonArray = (JSONArray) fieldJson.get("menuIdList");

		//更新关联表rolemenu
		List<RoleMenuDO> roleMenuDOs=new ArrayList<>();
		jsonArray.forEach(menuID->{
			RoleMenuDO roleMenuDO=new RoleMenuDO();
			roleMenuDO.setMenuId(objectMapper.convertValue(menuID,Long.class));
			roleMenuDO.setRoleId(roleId);
			roleMenuDOs.add(roleMenuDO);
		});
		roleMenuService.insertRoleMenuByBatchId(roleMenuDOs);
		//更新role表
		//移除menuId
		fieldJson.remove("menuIdList");
		data=JSONObject.toJSONString(fieldJson);
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
		//需要用事务操作（待完成）
		Boolean flag = false;
		Long Id = Long.valueOf(roleId);
		int singleDelete = this.roleMapper.deleteById(Id);
		this.userRoleService.deleteUserRoleByMap("ROLE_ID",roleId);
        this.roleMenuService.deleteRoleMenuByMap("ROLE_ID",roleId);

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
