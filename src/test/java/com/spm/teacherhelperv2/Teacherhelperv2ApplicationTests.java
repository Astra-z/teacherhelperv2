package com.spm.teacherhelperv2;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.spm.teacherhelperv2.dao.MenuMapper;
import com.spm.teacherhelperv2.dao.StudentMentorMapper;
import com.spm.teacherhelperv2.entity.MenuDO;
import com.spm.teacherhelperv2.service.MenuService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
class Teacherhelperv2ApplicationTests {
    @Autowired
    @Qualifier("MenuService")
    MenuService roleService;


    @Autowired
    MenuMapper mapper;

    @Autowired
    StudentMentorMapper studentMentorMapper;
    @Test
    void contextLoads() {
        List<MenuDO> menuDOS=roleService.findPermsByUsername("zhangsan");
        List<String> perms= menuDOS.stream().map(MenuDO::getPerms).collect(Collectors.toList());
        perms.forEach(p->{
            System.out.println(p);
        });
    }

    @Test
    void mybatispulsSelectcount(){
        System.out.println(mapper.selectCount(new QueryWrapper<>()));
    }

    @Test
    void testMentor(){
        System.out.println(studentMentorMapper.findAllMyStudent(1));
    }

}
