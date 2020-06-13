package com.spm.teacherhelperv2.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spm.teacherhelperv2.entity.StudentMentorDO;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * StudentMentorDOMapper 接口
 * @author zhangjr
 * @since 2020-06-13 20:45:02
 */
public interface StudentMentorMapper extends BaseMapper<StudentMentorDO> {
    @Select("select s.*,u.* from s_student_mentor s left join s_user u on (s.student_id=u.user_id) where s.mentor_id=#{userId}")
    @Results(value = {
            @Result(column="student_id", property="studentId", jdbcType= JdbcType.INTEGER),
            @Result(column="mentor_id", property="mentorId", jdbcType= JdbcType.INTEGER),
            @Result(property="studentInfo",javaType=com.spm.teacherhelperv2.entity.UserDO.class,column = "user_id",
                    one=@One(select="com.spm.teacherhelperv2.dao.UserMapper.selectById"))
    })
    public List<StudentMentorDO> findAllMyStudent(Integer userId);
}