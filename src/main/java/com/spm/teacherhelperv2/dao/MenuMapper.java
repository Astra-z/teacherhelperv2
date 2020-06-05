package com.spm.teacherhelperv2.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spm.teacherhelperv2.entity.MenuDO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * MenuDOMapper 接口
 * @author zhangjr
 * @since 2020-05-16 17:28:25
 */
public interface MenuMapper extends BaseMapper<MenuDO> {
    @Select("select m.* from s_menu m left join s_role_menu sm on (m.menu_id=sm.menu_id) " +
            "left join s_role r on (sm.role_id=r.role_id) " +
            "left join s_user_role ur on (r.role_id=ur.role_id) " +
            "left join s_user u on (ur.user_id=u.user_id) " +
            "where u.username=#{username} and m.perms is not null and m.perms!=''")
    public List<MenuDO> findPermsByUsername(String username);

    @Select("select m.* from s_menu m left join s_role_menu sm on (m.menu_id=sm.menu_id) " +
            "left join s_role r on (sm.role_id=r.role_id) " +
            "left join s_user_role ur on (r.role_id=ur.role_id) " +
            "left join s_user u on (ur.user_id=u.user_id) " +
            "where u.username=#{username}")
    public List<MenuDO> findMenusByUsername(String username);

    @Select("select * from s_menu" )
    public List<MenuDO> findAllMenus();
}