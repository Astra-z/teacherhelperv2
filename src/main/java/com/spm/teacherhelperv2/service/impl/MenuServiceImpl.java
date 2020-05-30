package com.spm.teacherhelperv2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spm.teacherhelperv2.dao.MenuMapper;
import com.spm.teacherhelperv2.entity.MenuDO;
import com.spm.teacherhelperv2.entity.Tree;
import com.spm.teacherhelperv2.manager.GetEntity;
import com.spm.teacherhelperv2.service.MenuService;
import com.spm.teacherhelperv2.util.TreeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * MenuServiceImpl服务实现类
 * @author zhangjr
 * @since 2020-05-16 17:28:25
 */
@Service
@Component("MenuService")
public class MenuServiceImpl implements MenuService {
	private Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
	
	@Autowired(required = false)
	private MenuMapper menuMapper;
	private GetEntity getEntity = new GetEntity();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
    /**
     * 实现listMenuByOther()方法
     * 用于根据特定条件值查询所有 MenuDO数据
     */
	@Override
	public List<MenuDO> listMenuByOther(String fieldValue, String fieldName, String page, String limit) {
	    List<MenuDO> menuDOs = new ArrayList<MenuDO>();
	    Field[] fields = new MenuDO().getClass().getDeclaredFields();
		String annotationValue = null;
		if(fieldName != null) {
		    annotationValue = getEntity.getAnnotationValue(fields, fieldName);
		    if(limit == null) {
		        menuDOs = this.menuMapper.selectList(new QueryWrapper<MenuDO>()
					.eq(annotationValue, fieldValue));
			} else {
			    Page<MenuDO> lpage = new Page<MenuDO>(Integer.valueOf(page),Integer.valueOf(limit));
	            menuDOs = this.menuMapper.selectPage(lpage,new QueryWrapper<MenuDO>()
					.eq(annotationValue, fieldValue)).getRecords();

			}
		}else {
		    if(limit == null) {
		        menuDOs = this.menuMapper.selectList(null);
			} else {
			    Page<MenuDO> lpage = new Page<MenuDO>(Integer.valueOf(page),Integer.valueOf(limit));
	            menuDOs = this.menuMapper.selectPage(lpage,null).getRecords();
			}
		}
        logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"--page:"+page+"--limit:"+limit+"];Intermediate variable:[--annotationValue:"+annotationValue+"];--return:"+menuDOs);
        return menuDOs;
	}

	@Override
	public Integer countAllMenus() {
		return menuMapper.selectCount(new QueryWrapper<>());
	}

	/**
     * 实现getMenuById()方法
     * 用于根据Id查询对应单条数据 
     */
	@Override
	public MenuDO getMenuById(Long menuId) {
		MenuDO menuDO=this.menuMapper.selectById(menuId);
		logger.info("receive:[menuId:"+menuId+"];--return:"+menuDO);
		return menuDO;
	}
	
    /**
     * 实现getMenuByOther方法
     * 用于根据其他信息查询对应单条数据 
     */
	@Override
	public MenuDO getMenuByOther(String fieldValue, String fieldName) {
		Field[] fields = new MenuDO().getClass().getDeclaredFields();
		try{
		    String annotationValue = getEntity.getAnnotationValue(fields, fieldName);
		    MenuDO menuDO = this.menuMapper.selectOne(new QueryWrapper<MenuDO>()
				    .eq(annotationValue, fieldValue));
		    logger.info("receive:[fieldValue:"+fieldValue+"--fieldName:"+fieldName+"];Intermediate variable:[--annotationValue:"+annotationValue+"];--return:"+menuDO);
		    return menuDO;
		} catch (Exception e) {
		   return null;
		}
	}
	
    /**
     *  实现insertMenu()方法
     *  用于插入一条数据
     */
	@Override
	@Async
	public MenuDO insertMenu(MenuDO menuDO) {
	    menuDO.setCreateTime(new Date());
		menuDO.setModifyTime(new Date());
	    this.menuMapper.insert(menuDO);
		logger.info("receive:[menuDO:"+menuDO+"];--return:"+menuDO);
		return menuDO;
	}
	
	/**
	 *  实现updateMenu()方法
	 *  用于更新MenuDO数据
	 */
	@Override
	@Async
	public MenuDO updateMenu(MenuDO menuDO) {
	    menuDO.setModifyTime(new Date());
		this.menuMapper.updateById(menuDO);
		logger.info("receive:[menuDO:"+menuDO+"];--return:"+menuDO);
		return menuDO;	
	}

	/**
	 *  实现updateMenuField()方法
	 *  用于更新MenuDO部分数据
	 */
	@Override
	@Async
	public MenuDO updateMenuField(String data, Long menuId) {
		Field[] fields = new MenuDO().getClass().getDeclaredFields();
		MenuDO menuDO = (MenuDO) getEntity.setTableField(
				data, MenuDO.class, fields, this.menuMapper.selectById(menuId));
		menuDO.setModifyTime(new Date());
		this.menuMapper.updateById(menuDO);
		logger.info("receive:[data:"+data+"--menuId:"+menuId+"];Intermediate variable:[--menuDO:"+menuDO+"];--return:"+menuDO);
		return menuDO;	
	}	
	/**
	 *  实现deleteMenuById()方法
	 *  用于删除对应Id的数据 
	 */
	@Override
	@Async
	public Boolean deleteMenuById(String menuId) {
		Boolean flag = false;
		Long Id=Long.valueOf(menuId);
        int singleDelete = this.menuMapper.deleteById(Id);
        if(singleDelete == 1){
           flag = true; 
        }     	    
        logger.info("receive:[menuId:"+menuId+"];Intermediate variable:[--singleDelete:"+singleDelete+"];--return:"+flag);
		return flag;	
	}

    @Override
    public List<MenuDO> findPermsByUsername(String username) {
        return menuMapper.findPermsByUsername(username);
    }

    @Override
    public List<MenuDO> findMenusByUsername(String username) {
        return menuMapper.findMenusByUsername(username);
    }


    @Override
    public Tree<MenuDO> findUserMenu(String username){
	    List<MenuDO> menuDOList=this.findMenusByUsername(username);
	    List<Tree<MenuDO>> trees=new ArrayList<>();
	    menuDOList.forEach(menuDO -> {
	        Tree<MenuDO> tree=new Tree<>();
	        tree.setId(menuDO.getMenuId().toString());
	        tree.setUrl(menuDO.getUrl());
	        tree.setParentId(menuDO.getParentId().toString());
	        tree.setText(menuDO.getMenuName());
	        tree.setIcon(menuDO.getIcon());
	        tree.setOrder(menuDO.getOrderNum());
	        trees.add(tree);
        });
	    return TreeUtils.buildTree(trees);
    }

    @Override
    public Tree<MenuDO> findAllMenus() {
        List<MenuDO> menuDOList=this.menuMapper.findAllMenus();
        List<Tree<MenuDO>> trees=new ArrayList<>();
        menuDOList.forEach(menuDO -> {
            Tree<MenuDO> tree=new Tree<>();
            tree.setId(menuDO.getMenuId().toString());
            tree.setUrl(menuDO.getUrl());
            tree.setParentId(menuDO.getParentId().toString());
            tree.setText(menuDO.getMenuName());
            tree.setIcon(menuDO.getIcon());
            tree.setOrder(menuDO.getOrderNum());
            tree.setPerms(menuDO.getPerms());
            trees.add(tree);
        });
        return TreeUtils.buildTree(trees);
    }
}
