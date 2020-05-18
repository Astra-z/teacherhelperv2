package com.spm.teacherhelperv2.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spm.teacherhelperv2.entity.RoleDO;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * RoleDOMapper 接口
 * @author zhangjr
 * @since 2020-05-16 17:28:26
 */
public interface RoleMapper extends BaseMapper<RoleDO> {
    @Select("select r.* from s_role r left join s_user_role ur on (r.role_id=ur.role_id) left join s_user u on (u.user_id=ur.user_id) where u.username=#{username} ")
    @Results({
            @Result(column="ROLE_ID", property="roleId", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="ROLE_NAME", property="roleName", jdbcType=JdbcType.VARCHAR),
            @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="MODIFY_TIME", property="modifyTime", jdbcType=JdbcType.TIMESTAMP)
    })
    public List<RoleDO> findUserRole(String username);
}