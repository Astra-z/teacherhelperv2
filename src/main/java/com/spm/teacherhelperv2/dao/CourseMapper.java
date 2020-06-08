package com.spm.teacherhelperv2.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spm.teacherhelperv2.entity.CourseDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * CourseDOMapper 接口
 * @author zhangjr
 * @since 2020-05-15 22:42:58
 */

public interface CourseMapper extends BaseMapper<CourseDO> {

    @Update("UPDATE s_course SET student_num = student_num+1 WHERE course_id = #{id} ")
    public int addStundentNum(Integer id);

}