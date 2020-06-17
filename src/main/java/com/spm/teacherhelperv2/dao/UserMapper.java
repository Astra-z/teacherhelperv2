package com.spm.teacherhelperv2.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spm.teacherhelperv2.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * UserDOMapper 接口
 * @author zhangjr
 * @since 2020-05-15 22:42:58
 */

public interface UserMapper extends BaseMapper<UserDO> {

    @Select("select u.* from s_user u left join  s_user_role sr on  (u.USER_ID=sr.USER_ID)  left join  s_role  r on (r.ROLE_ID=sr.ROLE_ID) WHERE r.ROLE_NAME=#{roleName}")
    public List<UserDO> findUsersByRole(String roleName);

}