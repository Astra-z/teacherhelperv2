package com.spm.teacherhelperv2;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class Teacherhelperv2ApplicationTests {


    @Test
    void contextLoads() {
        String username="1234";
        String password="124";
        Subject subject=SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e){
            e.printStackTrace();
        }
        catch (IncorrectCredentialsException e){
            e.printStackTrace();
        }
        System.out.println("成功");
    }

}
